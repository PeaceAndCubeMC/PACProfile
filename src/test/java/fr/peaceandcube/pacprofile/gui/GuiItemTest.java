package fr.peaceandcube.pacprofile.gui;

import fr.peaceandcube.pacprofile.gui.item.GuiItem;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.CustomModelData;
import io.papermc.paper.datacomponent.item.ItemLore;
import io.papermc.paper.datacomponent.item.ResolvableProfile;
import io.papermc.paper.datacomponent.item.TooltipDisplay;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.PlayerMock;

import java.util.List;

@SuppressWarnings("UnstableApiUsage")
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
        CustomModelData customModelData = item.getStack().getData(DataComponentTypes.CUSTOM_MODEL_DATA);
        Assertions.assertNotNull(customModelData);
        Assertions.assertEquals(123, customModelData.floats().getFirst());
        Boolean enchantmentGlintOverride = item.getStack().getData(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE);
        Assertions.assertNotNull(enchantmentGlintOverride);
        Assertions.assertFalse(enchantmentGlintOverride);
        TooltipDisplay tooltipDisplay = item.getStack().getData(DataComponentTypes.TOOLTIP_DISPLAY);
        Assertions.assertNotNull(tooltipDisplay);
        Assertions.assertFalse(tooltipDisplay.hideTooltip());
        Assertions.assertInstanceOf(TextComponent.class, item.getStack().getData(DataComponentTypes.CUSTOM_NAME));
        TextComponent customName = (TextComponent) item.getStack().getData(DataComponentTypes.CUSTOM_NAME);
        Assertions.assertNotNull(customName);
        Assertions.assertEquals("Item name", customName.content());

        TextColor color = customName.color();
        Assertions.assertNotNull(color);
        Assertions.assertEquals(0xFF55FF, color.value());
        Assertions.assertEquals(TextDecoration.State.TRUE, customName.decorations().get(TextDecoration.BOLD));
        Assertions.assertEquals(TextDecoration.State.FALSE, customName.decorations().get(TextDecoration.ITALIC));

        ItemLore lore = item.getStack().getData(DataComponentTypes.LORE);
        Assertions.assertNotNull(lore);
        Assertions.assertEquals(
                List.of(Component.text("Line 1 of item lore"), Component.text("Line 2 of item lore")),
                lore.lines()
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
        Assertions.assertNull(item.getStack().getData(DataComponentTypes.CUSTOM_NAME));
        ResolvableProfile profile = item.getStack().getData(DataComponentTypes.PROFILE);
        Assertions.assertNotNull(profile);
        Assertions.assertEquals("Player name", profile.name());
    }
}
