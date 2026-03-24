package fr.peaceandcube.pacprofile.gui.dialog;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.CustomModelData;
import io.papermc.paper.datacomponent.item.ResolvableProfile;
import io.papermc.paper.datacomponent.item.TooltipDisplay;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DialogItem {
    private final Material material;
    private final int customModelData;
    private final Player player;

    public DialogItem(Material material, int customModelData) {
        this(material, customModelData, null);
    }

    public DialogItem(Material material, int customModelData, Player player) {
        this.material = material;
        this.customModelData = customModelData;
        this.player = player;
    }

    public Material getMaterial() {
        return material;
    }

    public int getCustomModelData() {
        return customModelData;
    }

    public Player getPlayer() {
        return player;
    }

    @SuppressWarnings("UnstableApiUsage")
    public ItemStack toItemStack() {
        ItemStack stack = new ItemStack(material);
        stack.setData(DataComponentTypes.TOOLTIP_DISPLAY, TooltipDisplay.tooltipDisplay()
                .hideTooltip(true)
                .build());
        stack.setData(DataComponentTypes.CUSTOM_MODEL_DATA, CustomModelData.customModelData()
                .addFloat(customModelData)
                .build());
        stack.setData(DataComponentTypes.PROFILE, ResolvableProfile.resolvableProfile(player.getPlayerProfile()));
        return stack;
    }
}
