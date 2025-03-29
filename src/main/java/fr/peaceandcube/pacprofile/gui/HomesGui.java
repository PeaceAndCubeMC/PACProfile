package fr.peaceandcube.pacprofile.gui;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.order.Order;
import fr.peaceandcube.pacprofile.order.OrderSet;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.text.NameComponents;
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

import java.util.*;
import java.util.stream.Stream;

public class HomesGui extends UnmodifiableGui {
    private final Map<Integer, String> HOME_SLOTS = new LinkedHashMap<>();
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

            HOME_SLOTS.put(slot, name);

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
            this.setItem(slot, color.getBed(), 3010,
                    Component.text(name, TextColor.color(0x5555FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false),
                    bedLore
            );

            List<Component> notesLore = new ArrayList<>();
            notesLore.add(Component.empty());
            notesLore.addAll(getNotesLore(name));
            notesLore.add(Component.empty());
            notesLore.add(LoreComponents.HOME_NOTES_CLICK_LEFT);
            notesLore.add(LoreComponents.HOME_NOTES_CLICK_RIGHT);
            this.setItem(slot + 1, Material.PAPER, 3011, NameComponents.HOME_NOTES, notesLore);

            this.setItem(slot + 2, color.getDye(), 3012, NameComponents.HOME_COLOR, List.of(
                    Component.empty(),
                    LoreComponents.HOME_COLOR_CLICK
            ));
        }

        this.setItem(51, Material.HOPPER, 3013, NameComponents.HOMES_ORDER, List.of(
                Component.empty(),
                LoreComponents.ORDER_BY.append(this.orderSet.currentOrder().getText()),
                Component.empty(),
                LoreComponents.ORDER_CLICK
        ));

        this.setItem(45, Material.ARROW, 3002, NameComponents.PAGE_PREVIOUS);
        this.setItem(49, Material.BARRIER, 3002, NameComponents.EXIT);
        // if it's not the last page
        if (homeCount > maxHomeOnPage) {
            this.setItem(53, Material.ARROW, 3003, NameComponents.PAGE_NEXT);
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

    @Override
    protected void onSlotLeftClick(int slot) {
        // previous page
        if (slot == 45) {
            if (this.page == 1) {
                new ProfileGui(this.viewer, this.player).open();
            } else {
                new HomesGui(this.viewer, this.player, this.page - 1, this.maxPages, this.orderSet.currentOrder()).open();
            }
        }

        // exit
        else if (slot == 49) {
            this.inv.close();
        }

        // next page
        else if (slot == 53) {
            int homeCount = this.user.getHomes().size();
            int maxHomeOnPage = this.page * 10;
            if (homeCount > maxHomeOnPage) {
                new HomesGui(this.viewer, this.player, this.page + 1, this.maxPages, this.orderSet.currentOrder()).open();
            }
        }

        // order
        else if (slot == 51) {
            this.orderSet.next();
            this.fillInventory();
        }

        // home beds
        else if (HOME_SLOTS.containsKey(slot)) {
            if (PACProfile.getInstance().config.isHomeTeleportationEnabled()) {
                if (this.player.equals(this.viewer)) {
                    this.dispatchCommand("home " + HOME_SLOTS.get(slot));
                } else {
                    this.dispatchCommand("home " + this.player.getName() + ":" + HOME_SLOTS.get(slot));
                }
            }
        }

        // home notes
        else if (HOME_SLOTS.containsKey(slot - 1)) {
            Color homeColor = Color.byName(PACProfile.getInstance().playerData.getHomeColor(this.player.getUniqueId(), HOME_SLOTS.get(slot - 1)));
            TextInputDialog.builder()
                    .player(this.viewer)
                    .title(NameComponents.HOMES)
                    .bodyItem(homeColor.getBed())
                    .bodyText(HOME_SLOTS.get(slot - 1))
                    .inputLabel(Messages.HOME_NOTES_TITLE)
                    .inputValue(PACProfile.getInstance().playerData.getHomeNotes(this.player.getUniqueId(), HOME_SLOTS.get(slot - 1)))
                    .inputSize(8, 80)
                    .onConfirm(newValue -> {
                        PACProfile.getInstance().playerData.setHomeNotes(this.player.getUniqueId(), HOME_SLOTS.get(slot - 1), newValue);
                        new HomesGui(this.viewer, this.player, this.page, this.maxPages, this.orderSet.currentOrder()).open();
                    })
                    .build()
                    .show();
        }

        // home colors
        else if (HOME_SLOTS.containsKey(slot - 2)) {
            new HomeColorGui(this.viewer, this.player, HOME_SLOTS.get(slot - 2), this.page, this.maxPages).open();
        }
    }

    @Override
    protected void onSlotRightClick(int slot) {
        // home beds
        if (HOME_SLOTS.containsKey(slot)) {
            if (PACProfile.getInstance().config.isHomeDeletionEnabled()) {
                String command;
                if (this.player.equals(this.viewer)) {
                    command = "delhome " + HOME_SLOTS.get(slot);
                } else {
                    command = "delhome " + this.player.getName() + ":" + HOME_SLOTS.get(slot);
                }
                new ConfirmationGui(this.viewer, this.player, this, () -> {
                    dispatchCommand(command);
                    new HomesGui(this.viewer, this.player, this.page, this.maxPages, this.orderSet.currentOrder()).open();
                }).open();
            }
        }

        // home notes
        else if (HOME_SLOTS.containsKey(slot - 1)) {
            new ConfirmationGui(this.viewer, this.player, this, () -> {
                PACProfile.getInstance().playerData.removeHomeNotes(this.player.getUniqueId(), HOME_SLOTS.get(slot - 1));
                new HomesGui(this.viewer, this.player, this.page, this.maxPages, this.orderSet.currentOrder()).open();
            }).open();
        }
    }
}
