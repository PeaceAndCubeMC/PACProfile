package fr.peaceandcube.pacprofile.item;

import fr.peaceandcube.pacprofile.gui.GuiContext;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class GuiItem {
    private final int slot;
    private final ItemStack stack;
    private final Consumer<GuiContext> leftClickAction;
    private final Consumer<GuiContext> rightClickAction;

    private GuiItem(int slot, ItemStack stack, Consumer<GuiContext> leftClickAction, Consumer<GuiContext> rightClickAction) {
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

    public Consumer<GuiContext> getLeftClickAction() {
        return leftClickAction;
    }

    public Consumer<GuiContext> getRightClickAction() {
        return rightClickAction;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer slot;
        private Material material;
        private Player player;
        private int customModelData;
        private boolean glint;
        private boolean hideTooltip;
        private GuiItemName name;
        private final List<Component> lore;
        private Consumer<GuiContext> leftClickAction;
        private Consumer<GuiContext> rightClickAction;

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

        public Builder player(Player player) {
            this.player = player;
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

        public Builder name(String text, int color) {
            this.name = new GuiItemName(text, color);
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

        public Builder onLeftClick(Consumer<GuiContext> action) {
            this.leftClickAction = action;
            return this;
        }

        public Builder onRightClick(Consumer<GuiContext> action) {
            this.rightClickAction = action;
            return this;
        }

        public GuiItem build() {
            if (slot == null || material == null) {
                return null;
            }

            ItemStack stack = new ItemStack(material);
            ItemMeta meta = stack.getItemMeta();
            if (name != null) {
                meta.customName(Component
                        .text(name.text(), TextColor.color(name.color()), TextDecoration.BOLD)
                        .decoration(TextDecoration.ITALIC, false));
            }
            meta.lore(lore);
            meta.setCustomModelData(customModelData);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
            meta.setEnchantmentGlintOverride(glint);
            meta.setHideTooltip(hideTooltip);
            if (meta instanceof SkullMeta skullMeta) {
                skullMeta.setOwningPlayer(player);
            }
            stack.setItemMeta(meta);

            return new GuiItem(slot, stack, leftClickAction, rightClickAction);
        }
    }
}
