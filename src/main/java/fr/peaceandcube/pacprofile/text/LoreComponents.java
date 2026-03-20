package fr.peaceandcube.pacprofile.text;

import fr.peaceandcube.pacprofile.util.Messages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class LoreComponents {
    public static Component PROFILE_BIRTHDAY;

    public static Component ORDER_BY;
    public static Component ORDER_CLICK;

    static {
        init();
    }

    public static void init() {
        PROFILE_BIRTHDAY = Component.text(Messages.PROFILE_BIRTHDAY, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);

        ORDER_BY = Component.text(Messages.ORDER_BY, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        ORDER_CLICK = Component.text(Messages.ORDER_CLICK, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
    }
}
