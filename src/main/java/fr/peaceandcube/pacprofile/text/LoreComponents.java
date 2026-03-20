package fr.peaceandcube.pacprofile.text;

import fr.peaceandcube.pacprofile.util.Messages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class LoreComponents {
    public static Component PROFILE_BIRTHDAY;

    public static Component STATISTICS_BASE;
    public static Component STATISTICS_CURRENT;

    public static Component SETTINGS_ENABLED;
    public static Component SETTINGS_DISABLED;
    public static Component SETTINGS_MSGTOGGLE_CLICK;
    public static Component SETTINGS_TOGGLEMSGSOUND_CLICK;
    public static Component SETTINGS_PTIME_CLICK_LEFT;
    public static Component SETTINGS_PTIME_CLICK_RIGHT;
    public static Component SETTINGS_PWEATHER_CLICK_LEFT;
    public static Component SETTINGS_PWEATHER_CLICK_RIGHT;

    public static Component HOME_WORLD;
    public static Component HOME_X;
    public static Component HOME_Y;
    public static Component HOME_Z;
    public static Component HOME_CLICK_LEFT;
    public static Component HOME_CLICK_RIGHT;
    public static Component HOME_NOTES_CLICK_LEFT;
    public static Component HOME_NOTES_CLICK_RIGHT;
    public static Component HOME_COLOR_CLICK;

    public static Component CLAIM_WORLD;
    public static Component CLAIM_GREATER_CORNER;
    public static Component CLAIM_LESSER_CORNER;
    public static Component CLAIM_WIDTH;
    public static Component CLAIM_LENGTH;
    public static Component CLAIM_AREA;
    public static Component CLAIM_PERMISSIONS_BUILDERS;
    public static Component CLAIM_PERMISSIONS_CONTAINERS;
    public static Component CLAIM_PERMISSIONS_ACCESSORS;
    public static Component CLAIM_PERMISSIONS_MANAGERS;
    public static Component CLAIM_NAME_CLICK;

    public static Component ONLINE_PLAYER_TRUST_COUNT_1;
    public static Component ONLINE_PLAYER_TRUST_COUNT_2;
    public static Component ONLINE_PLAYER_MAIL_SENT_1;
    public static Component ONLINE_PLAYER_MAIL_SENT_2;
    public static Component ONLINE_PLAYER_CLICK;
    public static Component ONLINE_PLAYER_NOTES_CLICK_LEFT;
    public static Component ONLINE_PLAYER_NOTES_CLICK_RIGHT;

    public static Component WARP_COMMAND;
    public static Component WARP_CATEGORY;
    public static Component WARP_CLICK;

    public static Component ORDER_BY;
    public static Component ORDER_CLICK;

    static {
        init();
    }

    public static void init() {
        PROFILE_BIRTHDAY = Component.text(Messages.PROFILE_BIRTHDAY, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);

        STATISTICS_BASE = Component.text(Messages.STATISTICS_BASE, NamedTextColor.RED).decoration(TextDecoration.ITALIC, false);
        STATISTICS_CURRENT = Component.text(Messages.STATISTICS_CURRENT, NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false);

        SETTINGS_ENABLED = Component.text(Messages.SETTINGS_ENABLED, NamedTextColor.GREEN, TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        SETTINGS_DISABLED = Component.text(Messages.SETTINGS_DISABLED, NamedTextColor.RED, TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        SETTINGS_MSGTOGGLE_CLICK = Component.text(Messages.SETTINGS_MSGTOGGLE_CLICK, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        SETTINGS_TOGGLEMSGSOUND_CLICK = Component.text(Messages.SETTINGS_TOGGLEMSGSOUND_CLICK, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        SETTINGS_PTIME_CLICK_LEFT = Component.text(Messages.SETTINGS_PTIME_CLICK_LEFT, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        SETTINGS_PTIME_CLICK_RIGHT = Component.text(Messages.SETTINGS_PTIME_CLICK_RIGHT, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        SETTINGS_PWEATHER_CLICK_LEFT = Component.text(Messages.SETTINGS_PWEATHER_CLICK_LEFT, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        SETTINGS_PWEATHER_CLICK_RIGHT = Component.text(Messages.SETTINGS_PWEATHER_CLICK_RIGHT, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);

        HOME_WORLD = Component.text(Messages.HOME_WORLD, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        HOME_X = Component.text(Messages.HOME_X, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        HOME_Y = Component.text(Messages.HOME_Y, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        HOME_Z = Component.text(Messages.HOME_Z, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        HOME_CLICK_LEFT = Component.text(Messages.HOME_CLICK_LEFT, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        HOME_CLICK_RIGHT = Component.text(Messages.HOME_CLICK_RIGHT, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        HOME_NOTES_CLICK_LEFT = Component.text(Messages.HOME_NOTES_CLICK_LEFT, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        HOME_NOTES_CLICK_RIGHT = Component.text(Messages.HOME_NOTES_CLICK_RIGHT, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        HOME_COLOR_CLICK = Component.text(Messages.HOME_COLOR_CLICK, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);

        CLAIM_WORLD = Component.text(Messages.CLAIM_WORLD, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        CLAIM_GREATER_CORNER = Component.text(Messages.CLAIM_GREATER_CORNER, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        CLAIM_LESSER_CORNER = Component.text(Messages.CLAIM_LESSER_CORNER, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        CLAIM_WIDTH = Component.text(Messages.CLAIM_WIDTH, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        CLAIM_LENGTH = Component.text(Messages.CLAIM_LENGTH, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        CLAIM_AREA = Component.text(Messages.CLAIM_AREA, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        CLAIM_PERMISSIONS_BUILDERS = Component.text(Messages.CLAIM_PERMISSIONS_BUILDERS, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        CLAIM_PERMISSIONS_CONTAINERS = Component.text(Messages.CLAIM_PERMISSIONS_CONTAINERS, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        CLAIM_PERMISSIONS_ACCESSORS = Component.text(Messages.CLAIM_PERMISSIONS_ACCESSORS, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        CLAIM_PERMISSIONS_MANAGERS = Component.text(Messages.CLAIM_PERMISSIONS_MANAGERS, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        CLAIM_NAME_CLICK = Component.text(Messages.CLAIM_NAME_CLICK, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);

        ONLINE_PLAYER_TRUST_COUNT_1 = Component.text(Messages.ONLINE_PLAYER_TRUST_COUNT_1, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        ONLINE_PLAYER_TRUST_COUNT_2 = Component.text(Messages.ONLINE_PLAYER_TRUST_COUNT_2, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        ONLINE_PLAYER_MAIL_SENT_1 = Component.text(Messages.ONLINE_PLAYER_MAIL_SENT_1, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        ONLINE_PLAYER_MAIL_SENT_2 = Component.text(Messages.ONLINE_PLAYER_MAIL_SENT_2, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        ONLINE_PLAYER_CLICK = Component.text(Messages.ONLINE_PLAYER_CLICK, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        ONLINE_PLAYER_NOTES_CLICK_LEFT = Component.text(Messages.ONLINE_PLAYER_NOTES_CLICK_LEFT, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        ONLINE_PLAYER_NOTES_CLICK_RIGHT = Component.text(Messages.ONLINE_PLAYER_NOTES_CLICK_RIGHT, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);

        WARP_COMMAND = Component.text(Messages.WARP_COMMAND, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        WARP_CATEGORY = Component.text(Messages.WARP_CATEGORY, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        WARP_CLICK = Component.text(Messages.WARP_CLICK, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);

        ORDER_BY = Component.text(Messages.ORDER_BY, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        ORDER_CLICK = Component.text(Messages.ORDER_CLICK, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
    }
}
