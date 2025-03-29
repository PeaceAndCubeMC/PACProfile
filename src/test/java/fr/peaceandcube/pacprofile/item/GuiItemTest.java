package fr.peaceandcube.pacprofile.item;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;

import java.util.List;

public class GuiItemTest {

    @BeforeEach
    void runBefore() {
        MockBukkit.mock();
    }

    @AfterEach
    void runAfter() {
        MockBukkit.unmock();
    }

    @Test
    void testCreateGuiItemWithoutSlot() {
        GuiItem item = GuiItem.builder()
                .material(Material.COBBLESTONE)
                .customModelData(123)
                .name(Component.text("Item name"))
                .lore(Component.text("Line 1 of item lore"), Component.text("Line 2 of item lore"))
                .build();

        Assertions.assertNull(item);
    }

    @Test
    void testCreateGuiItemWithoutMaterial() {
        GuiItem item = GuiItem.builder()
                .slot(1)
                .customModelData(123)
                .name(Component.text("Item name"))
                .lore(Component.text("Line 1 of item lore"), Component.text("Line 2 of item lore"))
                .build();

        Assertions.assertNull(item);
    }

    @Test
    void testCreateGuiItemValid() {
        GuiItem item = GuiItem.builder()
                .slot(1)
                .material(Material.COBBLESTONE)
                .customModelData(123)
                .name(Component.text("Item name"))
                .lore(Component.text("Line 1 of item lore"), Component.text("Line 2 of item lore"))
                .build();

        Assertions.assertNotNull(item);
        Assertions.assertEquals(1, item.getSlot());
        Assertions.assertEquals(Material.COBBLESTONE, item.getStack().getType());
        Assertions.assertEquals(123, item.getStack().getItemMeta().getCustomModelData());
        Assertions.assertFalse(item.getStack().getItemMeta().getEnchantmentGlintOverride());
        Assertions.assertFalse(item.getStack().getItemMeta().isHideTooltip());
        Assertions.assertEquals(Component.text("Item name"), item.getStack().getItemMeta().customName());
        Assertions.assertEquals(
                List.of(Component.text("Line 1 of item lore"), Component.text("Line 2 of item lore")),
                item.getStack().getItemMeta().lore()
        );
    }
}
