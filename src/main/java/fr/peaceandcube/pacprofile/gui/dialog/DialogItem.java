package fr.peaceandcube.pacprofile.gui.dialog;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

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

    public ItemStack toItemStack() {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setHideTooltip(true);
        meta.setCustomModelData(customModelData);
        if (meta instanceof SkullMeta skullMeta) {
            skullMeta.setOwningPlayer(player);
        }
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
