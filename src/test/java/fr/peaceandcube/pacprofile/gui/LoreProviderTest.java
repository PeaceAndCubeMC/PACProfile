package fr.peaceandcube.pacprofile.gui;

import fr.peaceandcube.pacprofile.gui.item.LoreProvider;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoreProviderTest {

    @Test
    public void testCreateSimpleLine() {
        Component loreLine = LoreProvider.line("Lore line");

        Assertions.assertInstanceOf(TextComponent.class, loreLine);
        Assertions.assertEquals("Lore line", ((TextComponent) loreLine).content());
        Assertions.assertEquals(NamedTextColor.GRAY, loreLine.color());
        Assertions.assertFalse(loreLine.hasDecoration(TextDecoration.ITALIC));
    }

    @Test
    public void testCreateLineStringValue() {
        Component loreLine = LoreProvider.line("Lore line", "Value");

        assertParentAndChild(loreLine, "Value");
    }

    @Test
    public void testCreateLineIntValue() {
        Component loreLine = LoreProvider.line("Lore line", 3);

        assertParentAndChild(loreLine, "3");
    }

    @Test
    public void testCreateLineDoubleValue() {
        Component loreLine = LoreProvider.line("Lore line", 7.0d);

        assertParentAndChild(loreLine, "7.0");
    }

    private void assertParentAndChild(Component loreLine, String expectedValue) {
        Assertions.assertInstanceOf(TextComponent.class, loreLine);
        Assertions.assertEquals("Lore line", ((TextComponent) loreLine).content());
        Assertions.assertEquals(NamedTextColor.GRAY, loreLine.color());
        Assertions.assertFalse(loreLine.hasDecoration(TextDecoration.ITALIC));

        Assertions.assertInstanceOf(TextComponent.class, loreLine.children().getFirst());
        Assertions.assertEquals(expectedValue, ((TextComponent) loreLine.children().getFirst()).content());
        Assertions.assertEquals(NamedTextColor.YELLOW, loreLine.children().getFirst().color());
        Assertions.assertTrue(loreLine.children().getFirst().hasDecoration(TextDecoration.BOLD));
        Assertions.assertFalse(loreLine.children().getFirst().hasDecoration(TextDecoration.ITALIC));
    }
}
