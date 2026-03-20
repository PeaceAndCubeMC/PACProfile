package fr.peaceandcube.pacprofile.module.homes;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.gui.ConfirmationGui;
import fr.peaceandcube.pacprofile.gui.GuiContext;
import fr.peaceandcube.pacprofile.gui.PaginatedGui;
import fr.peaceandcube.pacprofile.gui.ProfileGui;
import fr.peaceandcube.pacprofile.gui.dialog.DialogItem;
import fr.peaceandcube.pacprofile.gui.dialog.TextInputDialog;
import fr.peaceandcube.pacprofile.gui.item.GuiItem;
import fr.peaceandcube.pacprofile.gui.item.LoreProvider;
import fr.peaceandcube.pacprofile.logging.Logger;
import fr.peaceandcube.pacprofile.module.Module;
import fr.peaceandcube.pacprofile.module.homes.enums.HomeColor;
import fr.peaceandcube.pacprofile.order.Order;
import fr.peaceandcube.pacprofile.order.OrderSet;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.util.Messages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class HomesGui extends PaginatedGui {
    private final Module module;
    private List<String> homes;

    public HomesGui(Module module, Player viewer, Player player, int page, int maxPages) {
        this(module, viewer, player, page, maxPages, Order.DEFAULT);
    }

    public HomesGui(Module module, Player viewer, Player player, int page, int maxPages, Order order) {
        super(Component.text(module.translate("homes_title").formatted(player.getName(), Math.max(1, page), Math.max(1, maxPages))),
                viewer,
                player,
                page,
                maxPages,
                new OrderSet(order, Order.DEFAULT, Order.NAME_AZ, Order.NAME_ZA, Order.COLOR)
        );
        this.module = module;
        this.homes = this.user.getHomes();
        this.fillInventory();
        Bukkit.getPluginManager().registerEvents(this, PACProfile.getInstance());
    }

    @Override
    public void fillInventory() {
        this.items.clear();

        switch (this.orderSet().currentOrder()) {
            case DEFAULT -> this.homes = this.user.getHomes();
            case NAME_AZ -> this.homes.sort(String::compareToIgnoreCase);
            case NAME_ZA -> this.homes.sort((home1, home2) -> home2.compareToIgnoreCase(home1));
            case COLOR -> this.homes.sort(Comparator.comparingInt(home ->
                    HomeColor.byName(PACProfile.getInstance().playerData.getHomeColor(this.player.getUniqueId(), home)).ordinal()
            ));
        }

        int homeCount = this.homes.size();
        int maxHomeOnPage = this.page() * 10;
        int homesOnPage = homeCount >= maxHomeOnPage ? maxHomeOnPage : (homeCount - (this.page() - 1) * 10);

        for (int i = 0; i < Math.min(homesOnPage, 10); i++) {
            int index = maxHomeOnPage - 10 + i;
            int slot;
            // first column of the page
            if (i < 5) {
                slot = i * 9 + 1;
            // second column of the page
            } else {
                slot = (i - 5) * 9 + 5;
            }

            String name = this.homes.get(index);
            Location location = this.user.getHome(name);
            String world = location != null ? location.getWorld().getName() : Messages.INVALID;
            String x = location != null ? String.valueOf(location.getBlockX()) : Messages.INVALID;
            String y = location != null ? String.valueOf(location.getBlockY()) : Messages.INVALID;
            String z = location != null ? String.valueOf(location.getBlockZ()) : Messages.INVALID;
            HomeColor color = HomeColor.byName(PACProfile.getInstance().playerData.getHomeColor(this.player.getUniqueId(), name));

            List<Component> bedLore = new ArrayList<>();
            bedLore.add(Component.empty());
            bedLore.add(LoreProvider.line(module.translate("home_world"), world));
            bedLore.add(LoreProvider.line(module.translate("home_x"), x));
            bedLore.add(LoreProvider.line(module.translate("home_y"), y));
            bedLore.add(LoreProvider.line(module.translate("home_z"), z));
            bedLore.add(Component.empty());
            if (PACProfile.getInstance().config.isHomeTeleportationEnabled()) {
                bedLore.add(LoreProvider.line(module.translate("home_click_left")));
            }
            if (PACProfile.getInstance().config.isHomeDeletionEnabled()) {
                bedLore.add(LoreProvider.line(module.translate("home_click_right")));
            }
            this.setItem(GuiItem.builder().slot(slot).material(color.getBed())
                    .customModelData(3010)
                    .name(name, 0x5555FF)
                    .lore(bedLore)
                    .onLeftClick(context -> {
                        if (PACProfile.getInstance().config.isHomeTeleportationEnabled()) {
                            Logger.debug("%s teleported to home %s:%s".formatted(context.viewer().getName(), context.player().getName(), name));
                            if (context.player().equals(context.viewer())) {
                                context.dispatchCommand("home " + name);
                            } else {
                                context.dispatchCommand("home " + context.player().getName() + ":" + name);
                            }
                        }
                    })
                    .onRightClick(context -> {
                        if (PACProfile.getInstance().config.isHomeDeletionEnabled()) {
                            String command;
                            if (context.player().equals(context.viewer())) {
                                command = "delhome " + name;
                            } else {
                                command = "delhome " + context.player().getName() + ":" + name;
                            }
                            new ConfirmationGui(context.viewer(), context.player(), this, () -> {
                                Logger.debug("%s deleted home %s:%s".formatted(context.viewer().getName(), context.player().getName(), name));
                                context.dispatchCommand(command);
                                new HomesGui(module, context.viewer(), context.player(), context.page(), context.maxPages(), context.orderSet().currentOrder()).open();
                            }).open();
                        }
                    })
                    .build());

            List<Component> notesLore = new ArrayList<>();
            notesLore.add(Component.empty());
            notesLore.addAll(getNotesLore(name));
            notesLore.add(Component.empty());
            bedLore.add(LoreProvider.line(module.translate("home_notes_click_left")));
            bedLore.add(LoreProvider.line(module.translate("home_notes_click_right")));
            this.setItem(GuiItem.builder().slot(slot + 1).material(Material.PAPER)
                    .customModelData(3011)
                    .name(module.translate("home_notes"), 0x00AA00)
                    .lore(notesLore)
                    .onLeftClick(context -> TextInputDialog.builder()
                            .player(context.viewer())
                            .title(module.translate("homes"), 0x5555FF)
                            .bodyItem(new DialogItem(color.getBed(), 3010))
                            .bodyText(name)
                            .inputLabel(module.translate("home_notes_title"))
                            .inputValue(PACProfile.getInstance().playerData.getHomeNotes(context.player().getUniqueId(), name))
                            .inputSize(8, 80)
                            .onConfirm(newValue -> {
                                Logger.debug("%s edited notes for home %s".formatted(context.player().getName(), name));
                                PACProfile.getInstance().playerData.setHomeNotes(context.player().getUniqueId(), name, newValue);
                                new HomesGui(module, context.viewer(), context.player(), context.page(), context.maxPages(), context.orderSet().currentOrder()).open();
                            })
                            .build()
                            .show())
                    .onRightClick(context -> new ConfirmationGui(context.viewer(), context.player(), this, () -> {
                        Logger.debug("%s removed notes for home %s".formatted(context.player().getName(), name));
                        PACProfile.getInstance().playerData.removeHomeNotes(context.player().getUniqueId(), name);
                        new HomesGui(module, context.viewer(), context.player(), context.page(), context.maxPages(), context.orderSet().currentOrder()).open();
                    }).open())
                    .build());

            this.setItem(GuiItem.builder().slot(slot + 2).material(color.getDye())
                    .customModelData(3012)
                    .name(module.translate("home_color"), 0x00AAAA)
                    .lore(Component.empty(), LoreProvider.line(module.translate("home_color_click")))
                    .onLeftClick(context -> new HomeColorGui(module, context.viewer(), context.player(), name, context.page(), context.maxPages()).open())
                    .build());
        }

        this.setItem(GuiItem.builder().slot(51).material(Material.HOPPER)
                .customModelData(3013)
                .name(module.translate("homes_order"), 0x00AA00)
                .lore(Component.empty(), LoreComponents.ORDER_BY.append(this.orderSet().currentOrder().getText()))
                .lore(Component.empty(), LoreComponents.ORDER_CLICK)
                .onLeftClick(context -> {
                    context.orderSet().next();
                    context.fillInventory();
                })
                .build());

        this.setItem(GuiItem.builder().slot(45).material(Material.ARROW)
                .customModelData(3002)
                .name(Messages.PAGE_PREVIOUS, 0xFF55FF)
                .onLeftClick(context -> {
                    if (context.page() == 1) {
                        new ProfileGui(context.viewer(), context.player()).open();
                    } else {
                        new HomesGui(
                                module,
                                context.viewer(),
                                context.player(),
                                context.page() - 1,
                                context.maxPages(),
                                context.orderSet().currentOrder()
                        ).open();
                    }
                })
                .build());

        this.setItem(GuiItem.builder().slot(49).material(Material.BARRIER)
                .customModelData(3002)
                .name(Messages.EXIT, 0xFF5555)
                .onLeftClick(GuiContext::close)
                .build());

        // if it's not the last page
        if (homeCount > maxHomeOnPage) {
            this.setItem(GuiItem.builder().slot(53).material(Material.ARROW)
                    .customModelData(3003)
                    .name(Messages.PAGE_NEXT, 0xFF55FF)
                    .onLeftClick(context -> new HomesGui(
                            module,
                            context.viewer(),
                            context.player(),
                            context.page() + 1,
                            context.maxPages(),
                            context.orderSet().currentOrder()
                    ).open())
                    .build());
        }
    }

    private List<TextComponent> getNotesLore(String name) {
        String notes = PACProfile.getInstance().playerData.getHomeNotes(this.player.getUniqueId(), name);
        if (!notes.isEmpty()) {
            return Stream.of(notes.split("\\R"))
                    .map(note -> Component.text(note, NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false))
                    .toList();
        }
        return List.of(Component.text(Messages.NOT_DEFINED, NamedTextColor.YELLOW, TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false));
    }
}
