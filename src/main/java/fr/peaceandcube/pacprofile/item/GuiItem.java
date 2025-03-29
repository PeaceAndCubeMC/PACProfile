package fr.peaceandcube.pacprofile.item;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GuiItem {
    private final int slot;
    private final ItemStack stack;
    private final Runnable leftClickAction;
    private final Runnable rightClickAction;

    private GuiItem(int slot, ItemStack stack, Runnable leftClickAction, Runnable rightClickAction) {
        this.slot = slot;
        this.stack = stack;
        this.leftClickAction = leftClickAction;
        this.rightClickAction = rightClickAction;
    }

    public int getSlot() {
        return slot;
    }

    public ItemStack getStack() {
        return stack;
    }

    public Runnable getLeftClickAction() {
        return leftClickAction;
    }

    public Runnable getRightClickAction() {
        return rightClickAction;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer slot;
        private Material material;
        private int customModelData;
        private boolean glint;
        private boolean hideTooltip;
        private Component name;
        private final List<Component> lore;
        private Runnable leftClickAction;
        private Runnable rightClickAction;

        private Builder() {
            this.lore = new ArrayList<>();
        }

        public Builder slot(int slot) {
            this.slot = slot;
            return this;
        }

        public Builder material(Material material) {
            this.material = material;
            return this;
        }

        public Builder customModelData(int customModelData) {
            this.customModelData = customModelData;
            return this;
        }

        public Builder glint(boolean glint) {
            this.glint = glint;
            return this;
        }

        public Builder hideTooltip() {
            this.hideTooltip = true;
            return this;
        }

        public Builder name(Component name) {
            this.name = name;
            return this;
        }

        public Builder lore(Component... lore) {
            this.lore.addAll(List.of(lore));
            return this;
        }

        public Builder lore(List<Component> lore) {
            this.lore.addAll(lore);
            return this;
        }

        public Builder onLeftClick(Runnable action) {
            this.leftClickAction = action;
            return this;
        }

        public Builder onRightClick(Runnable action) {
            this.rightClickAction = action;
            return this;
        }

        public GuiItem build() {
            if (slot == null || material == null) {
                return null;
            }

            ItemStack stack = new ItemStack(material);
            ItemMeta meta = stack.getItemMeta();
            meta.displayName(name);
            meta.lore(lore);
            meta.setCustomModelData(customModelData);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
            meta.setEnchantmentGlintOverride(glint);
            meta.setHideTooltip(hideTooltip);
            stack.setItemMeta(meta);

            return new GuiItem(slot, stack, leftClickAction, rightClickAction);
        }
    }
}
