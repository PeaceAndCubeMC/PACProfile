package fr.peaceandcube.pacprofile.gui;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.text.NameComponents;
import fr.peaceandcube.pacprofile.util.Color;
import fr.peaceandcube.pacprofile.util.Messages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HomesGui extends UnmodifiableGui {
    private final Map<Integer, String> HOME_SLOTS = new LinkedHashMap<>();
    private final int page;
    private final int maxPages;
    private List<String> homes;
    private Order order;

    public HomesGui(Player viewer, Player player, int page, int maxPages) {
        this(viewer, player, page, maxPages, Order.DEFAULT);
    }

    public HomesGui(Player viewer, Player player, int page, int maxPages, Order order) {
        super(6, Component.text(String.format(Messages.HOMES_TITLE, player.getName(), Math.max(1, page), Math.max(1, maxPages))), viewer, player);
        this.page = Math.max(1, page);
        this.maxPages = maxPages;
        this.homes = this.user.getHomes();
        this.order = order;
        this.fillInventory();
        Bukkit.getPluginManager().registerEvents(this, PACProfile.getInstance());
    }

    @Override
    protected void fillInventory() {
        if (this.order == Order.DEFAULT) {
            this.homes = this.user.getHomes();
        } else if (this.order == Order.NAME_AZ) {
            this.homes.sort(String::compareToIgnoreCase);
        } else if (this.order == Order.NAME_ZA) {
            this.homes.sort((home1, home2) -> home2.compareToIgnoreCase(home1));
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

            this.setItem(slot, color.getBed(), 3010, Component.text(name, TextColor.color(0x5555FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false), List.of(
                    Component.empty(),
                    LoreComponents.HOME_WORLD.append(Component.text(world, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                    LoreComponents.HOME_X.append(Component.text(x, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                    LoreComponents.HOME_Y.append(Component.text(y, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                    LoreComponents.HOME_Z.append(Component.text(z, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                    Component.empty(),
                    LoreComponents.HOME_CLICK_LEFT,
                    LoreComponents.HOME_CLICK_RIGHT
            ));

            this.setItem(slot + 1, Material.PAPER, 3011, NameComponents.HOME_NOTES, List.of(
                    Component.empty(),
                    this.getNotesLore(name),
                    Component.empty(),
                    LoreComponents.HOME_NOTES_CLICK
            ));

            this.setItem(slot + 2, color.getDye(), 3012, NameComponents.HOME_COLOR, List.of(
                    Component.empty(),
                    LoreComponents.HOME_COLOR_CLICK
            ));
        }

        this.setItem(51, Material.HOPPER, 3013, NameComponents.HOMES_ORDER, List.of(
                Component.empty(),
                LoreComponents.ORDER_BY.append(this.order.getText()),
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

    private Component getNotesLore(String name) {
        String notes = PACProfile.getInstance().playerData.getHomeNotes(this.player.getUniqueId(), name);
        if (!notes.isEmpty()) {
            return Component.text(notes, TextColor.color(0xFFFF55)).decoration(TextDecoration.ITALIC, false);
        }
        return Component.text(Messages.NOT_DEFINED, TextColor.color(0xFFFF55), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    }

    @Override
    protected void onSlotLeftClick(int slot) {
        // previous page
        if (slot == 45) {
            if (this.page == 1) {
                new ProfileGui(this.viewer, this.player).open();
            } else {
                new HomesGui(this.viewer, this.player, this.page - 1, this.maxPages, this.order).open();
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
                new HomesGui(this.viewer, this.player, this.page + 1, this.maxPages, this.order).open();
            }
        }

        // order
        else if (slot == 51) {
            this.order = this.order.next();
            this.fillInventory();
        }

        // home beds
        else if (HOME_SLOTS.containsKey(slot)) {
            if (this.player.equals(this.viewer)) {
                this.dispatchCommand("home " + HOME_SLOTS.get(slot));
            } else {
                this.dispatchCommand("home " + this.player.getName() + ":" + HOME_SLOTS.get(slot));
            }
        }

        // home notes
        else if (HOME_SLOTS.containsKey(slot - 1)) {
            new AnvilGUI.Builder().plugin(PACProfile.getInstance())
                    .title(Messages.HOME_NOTES_TITLE)
                    .text(PACProfile.getInstance().playerData.getHomeNotes(this.player.getUniqueId(), HOME_SLOTS.get(slot - 1)))
                    .itemLeft(new ItemStack(Material.PAPER))
                    .onClick((anvilSlot, stateSnapshot) -> {
                        if (anvilSlot != AnvilGUI.Slot.OUTPUT) {
                            return List.of();
                        }
                        PACProfile.getInstance().playerData.setHomeNotes(stateSnapshot.getPlayer().getUniqueId(), HOME_SLOTS.get(slot - 1), stateSnapshot.getText());
                        new HomesGui(this.viewer, this.player, this.page, this.maxPages).open();
                        return List.of(AnvilGUI.ResponseAction.close());
                    })
                    .open(this.viewer);
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
            String command;
            if (this.player.equals(this.viewer)) {
                command = "delhome " + HOME_SLOTS.get(slot);
            } else {
                command = "delhome " + this.player.getName() + ":" + HOME_SLOTS.get(slot);
            }
            new ConfirmationGui(this.viewer, this.player, command, this).open();
        }
    }

    enum Order {
        DEFAULT(LoreComponents.ORDER_DEFAULT),
        NAME_AZ(LoreComponents.ORDER_NAME_AZ),
        NAME_ZA(LoreComponents.ORDER_NAME_ZA);

        private final Component text;

        Order(Component text) {
            this.text = text;
        }

        public Component getText() {
            return this.text;
        }

        public Order next() {
            return switch (this) {
                case DEFAULT -> NAME_AZ;
                case NAME_AZ -> NAME_ZA;
                case NAME_ZA -> DEFAULT;
            };
        }
    }
}
