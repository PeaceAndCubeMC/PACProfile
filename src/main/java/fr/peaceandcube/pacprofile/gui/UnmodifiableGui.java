package fr.peaceandcube.pacprofile.gui;

import com.earth2me.essentials.User;
import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.item.GuiItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public abstract class UnmodifiableGui implements Listener {
    protected final Inventory inv;
    protected final Player viewer;
    protected final Player player;
    protected final User user;
    protected final List<GuiItem> items;

    protected UnmodifiableGui(int rows, Component title, Player viewer, Player player) {
        this.inv = Bukkit.createInventory(null, 9 * rows, title);
        this.viewer = viewer;
        this.player = player;
        this.user = PACProfile.getEssentials().getUser(player);
        this.items = new ArrayList<>();
    }

    protected abstract void fillInventory();

    public void open() {
        this.viewer.openInventory(this.inv);
    }

    protected void setItem(GuiItem item) {
        this.items.add(item);
        this.inv.setItem(item.getSlot(), item.getStack());
    }

    protected void dispatchCommand(String command) {
        if (command != null && !command.isEmpty()) {
            // Get as close as possible to player-run commands, in order to respect permissions
            this.viewer.chat("/" + command);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(this.inv)) {
            if (!e.isShiftClick() && e.isLeftClick()) {
                for (GuiItem item : this.items) {
                    if (item.getLeftClickAction() != null && item.getSlot() == e.getSlot()) {
                        Bukkit.getScheduler().runTask(PACProfile.getInstance(), item.getLeftClickAction());
                    }
                }
            } else if (!e.isShiftClick() && e.isRightClick()) {
                for (GuiItem item : this.items) {
                    if (item.getRightClickAction() != null && item.getSlot() == e.getSlot()) {
                        Bukkit.getScheduler().runTask(PACProfile.getInstance(), item.getRightClickAction());
                    }
                }
            }
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent e) {
        if (e.getInventory().equals(this.inv)) {
            e.setCancelled(true);
        }
    }
}
