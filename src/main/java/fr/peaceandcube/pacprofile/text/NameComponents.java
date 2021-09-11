package fr.peaceandcube.pacprofile.text;

import fr.peaceandcube.pacprofile.util.Messages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class NameComponents {
    public static final Component COINS = Component.text(Messages.COINS, TextColor.color(0xFFAA00), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static final Component HEAD_TICKETS = Component.text(Messages.HEAD_TICKETS, TextColor.color(0x00AAAA), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static final Component MAILS = Component.text(Messages.MAILS, TextColor.color(0xAA00AA), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static final Component HOMES = Component.text(Messages.HOMES, TextColor.color(0x5555FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static final Component CLAIMS = Component.text(Messages.CLAIMS, TextColor.color(0x00AA00), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static final Component RULES = Component.text(Messages.RULES, TextColor.color(0xFF55FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static final Component LINKS = Component.text(Messages.LINKS, TextColor.color(0xFF55FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);

    public static final Component EXIT = Component.text(Messages.EXIT, TextColor.color(0xFF5555), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);

    public static final Component PAGE_PREVIOUS = Component.text(Messages.PAGE_PREVIOUS, TextColor.color(0xFF55FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static final Component PAGE_NEXT = Component.text(Messages.PAGE_NEXT, TextColor.color(0xFF55FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);

    public static final Component HOME_NOTES = Component.text(Messages.HOME_NOTES, TextColor.color(0x00AA00), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static final Component HOME_COLOR = Component.text(Messages.HOME_COLOR, TextColor.color(0x00AAAA), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
}
