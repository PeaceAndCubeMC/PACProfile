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

import java.util.*;

public class HomesGui extends UnmodifiableGui {
    private final Map<Integer, String> HOME_SLOTS = new LinkedHashMap<>();
    private final int page;

    public HomesGui(Player viewer, Player player, int page) {
        super(6, Component.text(String.format(Messages.HOMES_TITLE, player.getName())), viewer, player);
        this.page = Math.max(1, page);
        this.fillInventory();
        Bukkit.getPluginManager().registerEvents(this, PACProfile.getInstance());
    }

    @Override
    protected void fillInventory() {
        int homeCount = this.user.getHomes().size();
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

            String name = this.user.getHomes().get(index);
            Location location = this.user.getHome(name);
            String world = location.getWorld().getName();
            int x = location.getBlockX();
            int y = location.getBlockY();
            int z = location.getBlockZ();
            Color color = Color.byName(PACProfile.getInstance().playerData.getHomeColor(this.player.getUniqueId(), name));

            HOME_SLOTS.put(slot, name);

            this.setItem(slot, color.getBed(), Component.text(name, TextColor.color(0x5555FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false), List.of(
                    Component.empty(),
                    LoreComponents.HOME_WORLD.append(Component.text(world, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                    LoreComponents.HOME_X.append(Component.text(x, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                    LoreComponents.HOME_Y.append(Component.text(y, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                    LoreComponents.HOME_Z.append(Component.text(z, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                    Component.empty(),
                    LoreComponents.HOME_CLICK
            ));

            this.setItem(slot + 1, Material.PAPER, NameComponents.HOME_NOTES, List.of(
                    Component.empty(),
                    this.getNotesLore(name),
                    Component.empty(),
                    LoreComponents.HOME_NOTES_CLICK
            ));

            this.setItem(slot + 2, color.getDye(), NameComponents.HOME_COLOR, List.of(
                    Component.empty(),
                    LoreComponents.HOME_COLOR_CLICK
            ));
        }

        this.setItem(45, Material.ARROW, NameComponents.PAGE_PREVIOUS, List.of());
        this.setItem(49, Material.BARRIER, NameComponents.EXIT, List.of());
        // if it's not the last page
        if (homeCount > maxHomeOnPage) {
            this.setItem(53, Material.ARROW, NameComponents.PAGE_NEXT, List.of());
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
                new HomesGui(this.viewer, this.player, this.page - 1).open();
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
                new HomesGui(this.viewer, this.player, this.page + 1).open();
            }
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
                    .title(Messages.HOME_COLOR_TITLE)
                    .text(PACProfile.getInstance().playerData.getHomeNotes(this.player.getUniqueId(), HOME_SLOTS.get(slot - 1)))
                    .itemLeft(new ItemStack(Material.PAPER))
                    .onComplete(((p, s) -> {
                        PACProfile.getInstance().playerData.setHomeNotes(p.getUniqueId(), HOME_SLOTS.get(slot - 1), s);
                        new HomesGui(this.viewer, this.player, this.page).open();
                        return AnvilGUI.Response.close();
                    }))
                    .open(this.viewer);
        }

        // home colors
        else if (HOME_SLOTS.containsKey(slot - 2)) {
            new HomeColorGui(this.viewer, this.player, HOME_SLOTS.get(slot - 2), this.page).open();
        }
    }
}
