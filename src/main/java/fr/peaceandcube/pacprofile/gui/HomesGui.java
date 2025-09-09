package fr.peaceandcube.pacprofile.gui;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.gui.dialog.DialogItem;
import fr.peaceandcube.pacprofile.gui.dialog.TextInputDialog;
import fr.peaceandcube.pacprofile.item.GuiItem;
import fr.peaceandcube.pacprofile.order.Order;
import fr.peaceandcube.pacprofile.order.OrderSet;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.util.Color;
import fr.peaceandcube.pacprofile.util.Messages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class HomesGui extends UnmodifiableGui {
    private final int page;
    private final int maxPages;
    private List<String> homes;
    private final OrderSet orderSet;

    public HomesGui(Player viewer, Player player, int page, int maxPages) {
        this(viewer, player, page, maxPages, Order.DEFAULT);
    }

    public HomesGui(Player viewer, Player player, int page, int maxPages, Order order) {
        super(6, Component.text(String.format(Messages.HOMES_TITLE, player.getName(), Math.max(1, page), Math.max(1, maxPages))), viewer, player);
        this.page = Math.max(1, page);
        this.maxPages = maxPages;
        this.homes = this.user.getHomes();
        this.orderSet = new OrderSet(order, Order.DEFAULT, Order.NAME_AZ, Order.NAME_ZA, Order.COLOR);
        this.fillInventory();
        Bukkit.getPluginManager().registerEvents(this, PACProfile.getInstance());
    }

    @Override
    protected void fillInventory() {
        this.items.clear();

        switch (this.orderSet.currentOrder()) {
            case DEFAULT -> this.homes = this.user.getHomes();
            case NAME_AZ -> this.homes.sort(String::compareToIgnoreCase);
            case NAME_ZA -> this.homes.sort((home1, home2) -> home2.compareToIgnoreCase(home1));
            case COLOR -> this.homes.sort(Comparator.comparingInt(home ->
                    Color.byName(PACProfile.getInstance().playerData.getHomeColor(this.player.getUniqueId(), home)).ordinal()
            ));
        }

        int homeCount = this.homes.size();
        int maxHomeOnPage = this.page * 10;
        int homesOnPage = homeCount >= maxHomeOnPage ? maxHomeOnPage : (homeCount - (this.page - 1) * 10);

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
            Color color = Color.byName(PACProfile.getInstance().playerData.getHomeColor(this.player.getUniqueId(), name));

            List<Component> bedLore = new ArrayList<>();
            bedLore.add(Component.empty());
            bedLore.add(LoreComponents.HOME_WORLD.append(Component.text(world, TextColor.color(0xFFFF55), TextDecoration.BOLD)));
            bedLore.add(LoreComponents.HOME_X.append(Component.text(x, TextColor.color(0xFFFF55), TextDecoration.BOLD)));
            bedLore.add(LoreComponents.HOME_Y.append(Component.text(y, TextColor.color(0xFFFF55), TextDecoration.BOLD)));
            bedLore.add(LoreComponents.HOME_Z.append(Component.text(z, TextColor.color(0xFFFF55), TextDecoration.BOLD)));
            bedLore.add(Component.empty());
            if (PACProfile.getInstance().config.isHomeTeleportationEnabled()) {
                bedLore.add(LoreComponents.HOME_CLICK_LEFT);
            }
            if (PACProfile.getInstance().config.isHomeDeletionEnabled()) {
                bedLore.add(LoreComponents.HOME_CLICK_RIGHT);
            }
            this.setItem(GuiItem.builder().slot(slot).material(color.getBed())
                    .customModelData(3010)
                    .name(name, 0x5555FF)
                    .lore(bedLore)
                    .onLeftClick(() -> {
                        if (PACProfile.getInstance().config.isHomeTeleportationEnabled()) {
                            if (this.player.equals(this.viewer)) {
                                this.dispatchCommand("home " + name);
                            } else {
                                this.dispatchCommand("home " + this.player.getName() + ":" + name);
                            }
                        }
                    })
                    .onRightClick(() -> {
                        if (PACProfile.getInstance().config.isHomeDeletionEnabled()) {
                            String command;
                            if (this.player.equals(this.viewer)) {
                                command = "delhome " + name;
                            } else {
                                command = "delhome " + this.player.getName() + ":" + name;
                            }
                            new ConfirmationGui(this.viewer, this.player, this, () -> {
                                dispatchCommand(command);
                                new HomesGui(this.viewer, this.player, this.page, this.maxPages, this.orderSet.currentOrder()).open();
                            }).open();
                        }
                    })
                    .build());

            List<Component> notesLore = new ArrayList<>();
            notesLore.add(Component.empty());
            notesLore.addAll(getNotesLore(name));
            notesLore.add(Component.empty());
            notesLore.add(LoreComponents.HOME_NOTES_CLICK_LEFT);
            notesLore.add(LoreComponents.HOME_NOTES_CLICK_RIGHT);
            this.setItem(GuiItem.builder().slot(slot + 1).material(Material.PAPER)
                    .customModelData(3011)
                    .name(Messages.HOME_NOTES, 0x00AA00)
                    .lore(notesLore)
                    .onLeftClick(() -> TextInputDialog.builder()
                            .player(this.viewer)
                            .title(Messages.HOMES, 0x5555FF)
                            .bodyItem(new DialogItem(color.getBed(), 3010))
                            .bodyText(name)
                            .inputLabel(Messages.HOME_NOTES_TITLE)
                            .inputValue(PACProfile.getInstance().playerData.getHomeNotes(this.player.getUniqueId(), name))
                            .inputSize(8, 80)
                            .onConfirm(newValue -> {
                                PACProfile.getInstance().playerData.setHomeNotes(this.player.getUniqueId(), name, newValue);
                                new HomesGui(this.viewer, this.player, this.page, this.maxPages, this.orderSet.currentOrder()).open();
                            })
                            .build()
                            .show())
                    .onRightClick(() -> new ConfirmationGui(this.viewer, this.player, this, () -> {
                        PACProfile.getInstance().playerData.removeHomeNotes(this.player.getUniqueId(), name);
                        new HomesGui(this.viewer, this.player, this.page, this.maxPages, this.orderSet.currentOrder()).open();
                    }).open())
                    .build());

            this.setItem(GuiItem.builder().slot(slot + 2).material(color.getDye())
                    .customModelData(3012)
                    .name(Messages.HOME_COLOR, 0x00AAAA)
                    .lore(Component.empty(), LoreComponents.HOME_COLOR_CLICK)
                    .onLeftClick(() -> new HomeColorGui(this.viewer, this.player, name, this.page, this.maxPages).open())
                    .build());
        }

        this.setItem(GuiItem.builder().slot(51).material(Material.HOPPER)
                .customModelData(3013)
                .name(Messages.HOMES_ORDER, 0x00AA00)
                .lore(Component.empty(), LoreComponents.ORDER_BY.append(this.orderSet.currentOrder().getText()))
                .lore(Component.empty(), LoreComponents.ORDER_CLICK)
                .onLeftClick(() -> {
                    this.orderSet.next();
                    this.fillInventory();
                })
                .build());

        this.setItem(GuiItem.builder().slot(45).material(Material.ARROW)
                .customModelData(3002)
                .name(Messages.PAGE_PREVIOUS, 0xFF55FF)
                .onLeftClick(() -> {
                    if (this.page == 1) {
                        new ProfileGui(this.viewer, this.player).open();
                    } else {
                        new HomesGui(this.viewer, this.player, this.page - 1, this.maxPages,
                                this.orderSet.currentOrder()).open();
                    }
                })
                .build());

        this.setItem(GuiItem.builder().slot(49).material(Material.BARRIER)
                .customModelData(3002)
                .name(Messages.EXIT, 0xFF5555)
                .onLeftClick(this.inv::close)
                .build());

        // if it's not the last page
        if (homeCount > maxHomeOnPage) {
            this.setItem(GuiItem.builder().slot(53).material(Material.ARROW)
                    .customModelData(3003)
                    .name(Messages.PAGE_NEXT, 0xFF55FF)
                    .onLeftClick(() -> new HomesGui(this.viewer, this.player, this.page + 1, this.maxPages,
                            this.orderSet.currentOrder()).open())
                    .build());
        }
    }

    private List<TextComponent> getNotesLore(String name) {
        String notes = PACProfile.getInstance().playerData.getHomeNotes(this.player.getUniqueId(), name);
        if (!notes.isEmpty()) {
            return Stream.of(notes.split("\\R"))
                    .map(note -> Component.text(note, TextColor.color(0xFFFF55)).decoration(TextDecoration.ITALIC, false))
                    .toList();
        }
        return List.of(Component.text(Messages.NOT_DEFINED, TextColor.color(0xFFFF55), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false));
    }
}
