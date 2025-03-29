package fr.peaceandcube.pacprofile.gui;

import com.earth2me.essentials.User;
import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.item.GuiItem;
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

    protected void setItem(int slot, Material material, int customModelData, Component name) {
        this.setItem(slot, material, customModelData, name, List.of());
    }

    protected void setItem(int slot, Material material, int customModelData, Component name, List<Component> lore) {
        this.setItem(slot, material, customModelData, false, name, lore);
    }

    protected void setItem(int slot, Material material, int customModelData, boolean glint, Component name, List<Component> lore) {
        this.setItem(slot, material, customModelData, glint, false, name, lore);
    }

    protected void setItem(int slot, Material material, int customModelData, boolean glint, boolean hideTooltip, Component name, List<Component> lore) {
        ItemStack stack = new ItemStack(material);
        ItemMeta meta = stack.getItemMeta();
        meta.displayName(name);
        meta.lore(lore);
        meta.setCustomModelData(customModelData);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        if (glint) {
            meta.setEnchantmentGlintOverride(true);
        }
        if (hideTooltip) {
            meta.setHideTooltip(true);
        }
        stack.setItemMeta(meta);

        this.inv.setItem(slot, stack);
    }

    protected void setItem(GuiItem item) {
        this.items.add(item);
        this.inv.setItem(item.getSlot(), item.getStack());
    }

    protected void setPlayerHead(int slot, Player player, int customModelData, Component name, List<Component> lore) {
        ItemStack stack = new ItemStack(Material.PLAYER_HEAD);
        if (stack.getItemMeta() instanceof SkullMeta meta) {
            meta.displayName(name);
            meta.lore(lore);
            meta.setOwningPlayer(player);
            meta.setCustomModelData(customModelData);
            stack.setItemMeta(meta);
        }
        this.inv.setItem(slot, stack);
    }

    protected void onSlotLeftClick(int slot) {};

    protected void onSlotRightClick(int slot) {}

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
                this.onSlotLeftClick(e.getSlot()); //TODO: remove when refactor is done
                for (GuiItem item : this.items) {
                    if (item.getLeftClickAction() != null && item.getSlot() == e.getSlot()) {
                        Bukkit.getScheduler().runTask(PACProfile.getInstance(), item.getLeftClickAction());
                    }
                }
            } else if (!e.isShiftClick() && e.isRightClick()) {
                this.onSlotRightClick(e.getSlot()); //TODO: remove when refactor is done
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
