package fr.peaceandcube.pacprofile.item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.meta.SkullMeta;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.PlayerMock;

import java.util.List;

public class GuiItemTest {
    private PlayerMock playerMock;

    @BeforeEach
    void runBefore() {
        ServerMock serverMock = MockBukkit.mock();
        playerMock = serverMock.addPlayer("Player name");
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
                .name("Item name", 0xFF55FF)
                .lore(Component.text("Line 1 of item lore"), Component.text("Line 2 of item lore"))
                .build();

        Assertions.assertNull(item);
    }

    @Test
    void testCreateGuiItemWithoutMaterial() {
        GuiItem item = GuiItem.builder()
                .slot(1)
                .customModelData(123)
                .name("Item name", 0xFF55FF)
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
                .name("Item name", 0xFF55FF)
                .lore(Component.text("Line 1 of item lore"), Component.text("Line 2 of item lore"))
                .build();

        Assertions.assertNotNull(item);
        Assertions.assertEquals(1, item.getSlot());
        Assertions.assertEquals(Material.COBBLESTONE, item.getStack().getType());
        Assertions.assertEquals(123, item.getStack().getItemMeta().getCustomModelData());
        Assertions.assertFalse(item.getStack().getItemMeta().getEnchantmentGlintOverride());
        Assertions.assertFalse(item.getStack().getItemMeta().isHideTooltip());
        Assertions.assertTrue(item.getStack().getItemMeta().customName() instanceof TextComponent);

        TextComponent customName = (TextComponent) item.getStack().getItemMeta().customName();
        Assertions.assertNotNull(customName);
        Assertions.assertEquals("Item name", customName.content());
        Assertions.assertEquals(0xFF55FF, customName.color().value());
        Assertions.assertEquals(TextDecoration.State.TRUE, customName.decorations().get(TextDecoration.BOLD));
        Assertions.assertEquals(TextDecoration.State.FALSE, customName.decorations().get(TextDecoration.ITALIC));

        Assertions.assertEquals(
                List.of(Component.text("Line 1 of item lore"), Component.text("Line 2 of item lore")),
                item.getStack().getItemMeta().lore()
        );
    }

    @Test
    void testCreateGuiItemWithPlayerAndHead() {
        GuiItem item = GuiItem.builder()
                .slot(1)
                .material(Material.PLAYER_HEAD)
                .player(playerMock)
                .build();

        Assertions.assertNotNull(item);
        Assertions.assertEquals(Material.PLAYER_HEAD, item.getStack().getType());
        Assertions.assertNull(item.getStack().getItemMeta().customName());
        Assertions.assertTrue(item.getStack().getItemMeta() instanceof SkullMeta);

        SkullMeta skullMeta = (SkullMeta) item.getStack().getItemMeta();
        Assertions.assertNotNull(skullMeta.getOwningPlayer());
        Assertions.assertEquals("Player name", skullMeta.getOwningPlayer().getName());
    }

    @Test
    void testCreateGuiItemWithPlayerButNotHead() {
        GuiItem item = GuiItem.builder()
                .slot(1)
                .material(Material.DIAMOND_BLOCK)
                .player(playerMock)
                .build();

        Assertions.assertNotNull(item);
        Assertions.assertNotEquals(Material.PLAYER_HEAD, item.getStack().getType());
        Assertions.assertNull(item.getStack().getItemMeta().customName());
        Assertions.assertFalse(item.getStack().getItemMeta() instanceof SkullMeta);
    }
}
