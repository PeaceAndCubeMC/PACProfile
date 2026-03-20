package fr.peaceandcube.pacprofile.gui.item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class LoreProvider {

    public static Component line(String text, NamedTextColor color) {
        return Component.text(text, color).decoration(TextDecoration.ITALIC, false);
    }

    public static Component line(String text) {
        return line(text, NamedTextColor.GRAY);
    }

    public static Component line(String text, String value) {
        return line(text).append(Component.text(value, NamedTextColor.YELLOW, TextDecoration.BOLD));
    }

    public static Component line(String text, int value) {
        return line(text, String.valueOf(value));
    }

    public static Component line(String text, double value) {
        return line(text, String.valueOf(value));
    }
}
