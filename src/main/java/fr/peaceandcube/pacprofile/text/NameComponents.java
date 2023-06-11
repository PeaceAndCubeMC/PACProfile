package fr.peaceandcube.pacprofile.text;

import fr.peaceandcube.pacprofile.util.Messages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class NameComponents {
    public static Component STATISTICS = Component.text(Messages.STATISTICS, TextColor.color(0xFF55FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component SETTINGS = Component.text(Messages.SETTINGS, TextColor.color(0x555555), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component COINS = Component.text(Messages.COINS, TextColor.color(0xFFAA00), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component HEAD_TICKETS = Component.text(Messages.HEAD_TICKETS, TextColor.color(0x00AAAA), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component MAILS = Component.text(Messages.MAILS, TextColor.color(0xAA00AA), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component HOMES = Component.text(Messages.HOMES, TextColor.color(0x5555FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component CLAIMS = Component.text(Messages.CLAIMS, TextColor.color(0x00AA00), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component ONLINE_PLAYERS = Component.text(Messages.ONLINE_PLAYERS, TextColor.color(0x55FF55), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component WARPS = Component.text(Messages.WARPS, TextColor.color(0xFFFF55), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component RULES = Component.text(Messages.RULES, TextColor.color(0xFF55FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component LINKS = Component.text(Messages.LINKS, TextColor.color(0xFF55FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component DYNMAP = Component.text(Messages.DYNMAP, TextColor.color(0xFF55FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);

    public static Component EXIT = Component.text(Messages.EXIT, TextColor.color(0xFF5555), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);

    public static Component PAGE_PREVIOUS = Component.text(Messages.PAGE_PREVIOUS, TextColor.color(0xFF55FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component PAGE_NEXT = Component.text(Messages.PAGE_NEXT, TextColor.color(0xFF55FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);

    public static Component CONFIRMATION_YES = Component.text(Messages.CONFIRMATION_YES, TextColor.color(0x55FF55), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component CONFIRMATION_NO = Component.text(Messages.CONFIRMATION_NO, TextColor.color(0xFF5555), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);

    public static Component SETTINGS_MSGTOGGLE = Component.text(Messages.SETTINGS_MSGTOGGLE, TextColor.color(0x5555FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component SETTINGS_TOGGLEMSGSOUND = Component.text(Messages.SETTINGS_TOGGLEMSGSOUND, TextColor.color(0x5555FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component SETTINGS_PTIME = Component.text(Messages.SETTINGS_PTIME, TextColor.color(0x5555FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component SETTINGS_PWEATHER = Component.text(Messages.SETTINGS_PWEATHER, TextColor.color(0x5555FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);

    public static Component HOMES_ORDER = Component.text(Messages.HOMES_ORDER, TextColor.color(0x00AA00), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component HOME_NOTES = Component.text(Messages.HOME_NOTES, TextColor.color(0x00AA00), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component HOME_COLOR = Component.text(Messages.HOME_COLOR, TextColor.color(0x00AAAA), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);

    public static Component CLAIMS_ORDER = Component.text(Messages.CLAIMS_ORDER, TextColor.color(0x00AA00), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component CLAIM_PERMISSIONS = Component.text(Messages.CLAIM_PERMISSIONS, TextColor.color(0x00AA00), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component CLAIM_NAME = Component.text(Messages.CLAIM_NAME, TextColor.color(0x00AAAA), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);

    public static Component WARPS_ORDER = Component.text(Messages.WARPS_ORDER, TextColor.color(0x00AA00), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);

    public static void init() {
        STATISTICS = Component.text(Messages.STATISTICS, TextColor.color(0xFF55FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        SETTINGS = Component.text(Messages.SETTINGS_TITLE, TextColor.color(0x555555), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        COINS = Component.text(Messages.COINS, TextColor.color(0xFFAA00), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        HEAD_TICKETS = Component.text(Messages.HEAD_TICKETS, TextColor.color(0x00AAAA), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        MAILS = Component.text(Messages.MAILS, TextColor.color(0xAA00AA), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        HOMES = Component.text(Messages.HOMES, TextColor.color(0x5555FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        CLAIMS = Component.text(Messages.CLAIMS, TextColor.color(0x00AA00), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        ONLINE_PLAYERS = Component.text(Messages.ONLINE_PLAYERS, TextColor.color(0x55FF55), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        WARPS = Component.text(Messages.WARPS, TextColor.color(0xFFFF55), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        RULES = Component.text(Messages.RULES, TextColor.color(0xFF55FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        LINKS = Component.text(Messages.LINKS, TextColor.color(0xFF55FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        DYNMAP = Component.text(Messages.DYNMAP, TextColor.color(0xFF55FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);

        EXIT = Component.text(Messages.EXIT, TextColor.color(0xFF5555), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);

        PAGE_PREVIOUS = Component.text(Messages.PAGE_PREVIOUS, TextColor.color(0xFF55FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        PAGE_NEXT = Component.text(Messages.PAGE_NEXT, TextColor.color(0xFF55FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);

        CONFIRMATION_YES = Component.text(Messages.CONFIRMATION_YES, TextColor.color(0x55FF55), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        CONFIRMATION_NO = Component.text(Messages.CONFIRMATION_NO, TextColor.color(0xFF5555), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);

        SETTINGS_MSGTOGGLE = Component.text(Messages.SETTINGS_MSGTOGGLE, TextColor.color(0x5555FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        SETTINGS_TOGGLEMSGSOUND = Component.text(Messages.SETTINGS_TOGGLEMSGSOUND, TextColor.color(0x5555FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        SETTINGS_PTIME = Component.text(Messages.SETTINGS_PTIME, TextColor.color(0x5555FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        SETTINGS_PWEATHER = Component.text(Messages.SETTINGS_PWEATHER, TextColor.color(0x5555FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);

        HOMES_ORDER = Component.text(Messages.HOMES_ORDER, TextColor.color(0x00AA00), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        HOME_NOTES = Component.text(Messages.HOME_NOTES, TextColor.color(0x00AA00), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        HOME_COLOR = Component.text(Messages.HOME_COLOR, TextColor.color(0x00AAAA), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);

        CLAIMS_ORDER = Component.text(Messages.CLAIMS_ORDER, TextColor.color(0x00AA00), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        CLAIM_PERMISSIONS = Component.text(Messages.CLAIM_PERMISSIONS, TextColor.color(0x00AA00), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        CLAIM_NAME = Component.text(Messages.CLAIM_NAME, TextColor.color(0x00AAAA), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);

        WARPS_ORDER = Component.text(Messages.WARPS_ORDER, TextColor.color(0x00AA00), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    }
}
