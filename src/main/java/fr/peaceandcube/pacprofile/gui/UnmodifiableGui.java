package fr.peaceandcube.pacprofile.gui;

import com.earth2me.essentials.User;
import fr.peaceandcube.pacprofile.PACProfile;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public abstract class UnmodifiableGui implements Listener {
    protected final Inventory inv;
    protected final Player viewer;
    protected final Player player;
    protected final User user;

    protected UnmodifiableGui(int rows, Component title, Player viewer, Player player) {
        this.inv = Bukkit.createInventory(null, 9 * rows, title);
        this.viewer = viewer;
        this.player = player;
        this.user = PACProfile.getEssentials().getUser(player);
    }

    protected abstract void fillInventory();

    public void open() {
        this.viewer.openInventory(this.inv);
    }

    protected void setItem(int slot, Material material, Component name) {
        this.setItem(slot, material, name, List.of());
    }

    protected void setItem(int slot, Material material, Component name, List<Component> lore) {
        ItemStack stack = new ItemStack(material);
        ItemMeta meta = stack.getItemMeta();
        meta.displayName(name);
        meta.lore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        stack.setItemMeta(meta);
        this.inv.setItem(slot, stack);
    }

    protected void setPlayerHead(int slot, Component name, List<Component> lore) {
        ItemStack stack = new ItemStack(Material.PLAYER_HEAD);
        if (stack.getItemMeta() instanceof SkullMeta meta) {
            meta.displayName(name);
            meta.lore(lore);
            meta.setOwningPlayer(this.player);
            stack.setItemMeta(meta);
        }
        this.inv.setItem(slot, stack);
    }

    protected abstract void onSlotLeftClick(int slot);

    protected void dispatchCommand(String command) {
        if (command != null && !command.isEmpty()) {
            Bukkit.dispatchCommand(this.viewer, command);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(this.inv)) {
            if (!e.isShiftClick() && e.isLeftClick()) {
                this.onSlotLeftClick(e.getSlot());
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
