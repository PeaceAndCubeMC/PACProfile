package fr.peaceandcube.pacprofile.gui.item;

import fr.peaceandcube.pacprofile.gui.GuiContext;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.CustomModelData;
import io.papermc.paper.datacomponent.item.ItemLore;
import io.papermc.paper.datacomponent.item.ResolvableProfile;
import io.papermc.paper.datacomponent.item.TooltipDisplay;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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

        @SuppressWarnings("UnstableApiUsage")
        public GuiItem build() {
            if (slot == null || material == null) {
                return null;
            }

            ItemStack stack = new ItemStack(material);
            if (name != null) {
                stack.setData(DataComponentTypes.CUSTOM_NAME, Component
                        .text(name.text(), TextColor.color(name.color()), TextDecoration.BOLD)
                        .decoration(TextDecoration.ITALIC, false));
            }
            stack.setData(DataComponentTypes.LORE, ItemLore.lore(lore));
            stack.setData(DataComponentTypes.CUSTOM_MODEL_DATA, CustomModelData.customModelData()
                    .addFloat(customModelData).build());
            stack.setData(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, glint);
            stack.setData(DataComponentTypes.TOOLTIP_DISPLAY, TooltipDisplay.tooltipDisplay()
                    .hideTooltip(hideTooltip)
                    .hiddenComponents(Set.of(DataComponentTypes.ATTRIBUTE_MODIFIERS, DataComponentTypes.ENCHANTMENTS))
                    .build());
            if (player != null) {
                stack.setData(DataComponentTypes.PROFILE, ResolvableProfile.resolvableProfile(player.getPlayerProfile()));
            }

            return new GuiItem(slot, stack, leftClickAction, rightClickAction);
        }
    }
}
