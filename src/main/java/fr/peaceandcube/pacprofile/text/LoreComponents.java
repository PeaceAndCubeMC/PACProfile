package fr.peaceandcube.pacprofile.text;

import fr.peaceandcube.pacprofile.util.Messages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class LoreComponents {
    public static Component PROFILE_RANK;
    public static Component PROFILE_RANK_EXPIRATION;
    public static Component PROFILE_NICKNAME;
    public static Component PROFILE_BIRTHDAY;
    public static Component PROFILE_JOIN_DATE;

    public static Component STATISTICS_BASE;
    public static Component STATISTICS_CURRENT;
    public static Component STATISTICS_CLICK_BASE;
    public static Component STATISTICS_CLICK_CURRENT;

    public static Component SETTINGS_CLICK;
    public static Component SETTINGS_ENABLED;
    public static Component SETTINGS_DISABLED;
    public static Component SETTINGS_MSGTOGGLE_CLICK;
    public static Component SETTINGS_TOGGLEMSGSOUND_CLICK;
    public static Component SETTINGS_PTIME_CLICK_LEFT;
    public static Component SETTINGS_PTIME_CLICK_RIGHT;
    public static Component SETTINGS_PWEATHER_CLICK_LEFT;
    public static Component SETTINGS_PWEATHER_CLICK_RIGHT;

    public static Component COINS_NUMBER;
    public static Component COINS_CLICK;

    public static Component HEAD_TICKETS_NUMBER;
    public static Component HEAD_TICKETS_CLICK;

    public static Component MAILS_TOTAL;
    public static Component MAILS_UNREAD;
    public static Component MAILS_CLICK;

    public static Component HOMES_TOTAL;
    public static Component HOMES_REMAINING;
    public static Component HOMES_MAX_AVAILABLE;
    public static Component HOMES_CLICK;
    public static Component HOME_WORLD;
    public static Component HOME_X;
    public static Component HOME_Y;
    public static Component HOME_Z;
    public static Component HOME_CLICK_LEFT;
    public static Component HOME_CLICK_RIGHT;
    public static Component HOME_NOTES_CLICK_LEFT;
    public static Component HOME_NOTES_CLICK_RIGHT;
    public static Component HOME_COLOR_CLICK;

    public static Component CLAIMS_TOTAL;
    public static Component CLAIMS_CB_USED;
    public static Component CLAIMS_CB_REMAINING;
    public static Component CLAIMS_CB_ACCRUED;
    public static Component CLAIMS_CB_BONUS;
    public static Component CLAIMS_CB_TOTAL;
    public static Component CLAIMS_CB_PER_HOUR;
    public static Component CLAIMS_CLICK;
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

    public static Component ONLINE_PLAYERS_COUNT;
    public static Component ONLINE_PLAYERS_CLICK;
    public static Component ONLINE_PLAYER_TRUST_COUNT_1;
    public static Component ONLINE_PLAYER_TRUST_COUNT_2;
    public static Component ONLINE_PLAYER_CLICK;
    public static Component ONLINE_PLAYER_NOTES_CLICK_LEFT;
    public static Component ONLINE_PLAYER_NOTES_CLICK_RIGHT;

    public static Component WARPS_CLICK;
    public static Component WARP_COMMAND;
    public static Component WARP_CATEGORY;
    public static Component WARP_CLICK;

    public static Component RULES_CLICK;

    public static Component LINKS_CLICK;

    public static Component DYNMAP_CLICK;

    public static Component ORDER_BY;
    public static Component ORDER_DEFAULT;
    public static Component ORDER_NAME_AZ;
    public static Component ORDER_NAME_ZA;
    public static Component ORDER_AREA_ASC;
    public static Component ORDER_AREA_DESC;
    public static Component ORDER_CATEGORY_AZ;
    public static Component ORDER_CATEGORY_ZA;
    public static Component ORDER_COLOR;
    public static Component ORDER_CLICK;

    static {
        init();
    }

    public static void init() {
        PROFILE_RANK = Component.text(Messages.PROFILE_RANK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        PROFILE_RANK_EXPIRATION = Component.text(Messages.PROFILE_RANK_EXPIRATION, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        PROFILE_NICKNAME = Component.text(Messages.PROFILE_NICKNAME, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        PROFILE_BIRTHDAY = Component.text(Messages.PROFILE_BIRTHDAY, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        PROFILE_JOIN_DATE = Component.text(Messages.PROFILE_JOIN_DATE, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);

        STATISTICS_BASE = Component.text(Messages.STATISTICS_BASE, TextColor.color(0xFF5555)).decoration(TextDecoration.ITALIC, false);
        STATISTICS_CURRENT = Component.text(Messages.STATISTICS_CURRENT, TextColor.color(0x55FF55)).decoration(TextDecoration.ITALIC, false);
        STATISTICS_CLICK_BASE = Component.text(Messages.STATISTICS_CLICK_BASE, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        STATISTICS_CLICK_CURRENT = Component.text(Messages.STATISTICS_CLICK_CURRENT, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);

        SETTINGS_CLICK = Component.text(Messages.SETTINGS_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        SETTINGS_ENABLED = Component.text(Messages.SETTINGS_ENABLED, TextColor.color(0x55FF55), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        SETTINGS_DISABLED = Component.text(Messages.SETTINGS_DISABLED, TextColor.color(0xFF5555), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        SETTINGS_MSGTOGGLE_CLICK = Component.text(Messages.SETTINGS_MSGTOGGLE_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        SETTINGS_TOGGLEMSGSOUND_CLICK = Component.text(Messages.SETTINGS_TOGGLEMSGSOUND_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        SETTINGS_PTIME_CLICK_LEFT = Component.text(Messages.SETTINGS_PTIME_CLICK_LEFT, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        SETTINGS_PTIME_CLICK_RIGHT = Component.text(Messages.SETTINGS_PTIME_CLICK_RIGHT, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        SETTINGS_PWEATHER_CLICK_LEFT = Component.text(Messages.SETTINGS_PWEATHER_CLICK_LEFT, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        SETTINGS_PWEATHER_CLICK_RIGHT = Component.text(Messages.SETTINGS_PWEATHER_CLICK_RIGHT, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);

        COINS_NUMBER = Component.text(Messages.COINS_NUMBER, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        COINS_CLICK = Component.text(Messages.COINS_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);

        HEAD_TICKETS_NUMBER = Component.text(Messages.HEAD_TICKETS_NUMBER, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        HEAD_TICKETS_CLICK = Component.text(Messages.HEAD_TICKETS_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);

        MAILS_TOTAL = Component.text(Messages.MAILS_TOTAL, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        MAILS_UNREAD = Component.text(Messages.MAILS_UNREAD, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        MAILS_CLICK = Component.text(Messages.MAILS_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);

        HOMES_TOTAL = Component.text(Messages.HOMES_TOTAL, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        HOMES_REMAINING = Component.text(Messages.HOMES_REMAINING, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        HOMES_MAX_AVAILABLE = Component.text(Messages.HOMES_MAX_AVAILABLE, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        HOMES_CLICK = Component.text(Messages.HOMES_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        HOME_WORLD = Component.text(Messages.HOME_WORLD, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        HOME_X = Component.text(Messages.HOME_X, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        HOME_Y = Component.text(Messages.HOME_Y, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        HOME_Z = Component.text(Messages.HOME_Z, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        HOME_CLICK_LEFT = Component.text(Messages.HOME_CLICK_LEFT, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        HOME_CLICK_RIGHT = Component.text(Messages.HOME_CLICK_RIGHT, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        HOME_NOTES_CLICK_LEFT = Component.text(Messages.HOME_NOTES_CLICK_LEFT, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        HOME_NOTES_CLICK_RIGHT = Component.text(Messages.HOME_NOTES_CLICK_RIGHT, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        HOME_COLOR_CLICK = Component.text(Messages.HOME_COLOR_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);

        CLAIMS_TOTAL = Component.text(Messages.CLAIMS_TOTAL, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        CLAIMS_CB_USED = Component.text(Messages.CLAIMS_CB_USED, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        CLAIMS_CB_REMAINING = Component.text(Messages.CLAIMS_CB_REMAINING, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        CLAIMS_CB_ACCRUED = Component.text(Messages.CLAIMS_CB_ACCRUED, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        CLAIMS_CB_BONUS = Component.text(Messages.CLAIMS_CB_BONUS, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        CLAIMS_CB_TOTAL = Component.text(Messages.CLAIMS_CB_TOTAL, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        CLAIMS_CB_PER_HOUR = Component.text(Messages.CLAIMS_CB_PER_HOUR, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        CLAIMS_CLICK = Component.text(Messages.CLAIMS_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        CLAIM_WORLD = Component.text(Messages.CLAIM_WORLD, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        CLAIM_GREATER_CORNER = Component.text(Messages.CLAIM_GREATER_CORNER, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        CLAIM_LESSER_CORNER = Component.text(Messages.CLAIM_LESSER_CORNER, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        CLAIM_WIDTH = Component.text(Messages.CLAIM_WIDTH, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        CLAIM_LENGTH = Component.text(Messages.CLAIM_LENGTH, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        CLAIM_AREA = Component.text(Messages.CLAIM_AREA, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        CLAIM_PERMISSIONS_BUILDERS = Component.text(Messages.CLAIM_PERMISSIONS_BUILDERS, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        CLAIM_PERMISSIONS_CONTAINERS = Component.text(Messages.CLAIM_PERMISSIONS_CONTAINERS, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        CLAIM_PERMISSIONS_ACCESSORS = Component.text(Messages.CLAIM_PERMISSIONS_ACCESSORS, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        CLAIM_PERMISSIONS_MANAGERS = Component.text(Messages.CLAIM_PERMISSIONS_MANAGERS, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        CLAIM_NAME_CLICK = Component.text(Messages.CLAIM_NAME_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);

        ONLINE_PLAYERS_COUNT = Component.text(Messages.ONLINE_PLAYERS_COUNT, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        ONLINE_PLAYERS_CLICK = Component.text(Messages.ONLINE_PLAYERS_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        ONLINE_PLAYER_TRUST_COUNT_1 = Component.text(Messages.ONLINE_PLAYER_TRUST_COUNT_1, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        ONLINE_PLAYER_TRUST_COUNT_2 = Component.text(Messages.ONLINE_PLAYER_TRUST_COUNT_2, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        ONLINE_PLAYER_CLICK = Component.text(Messages.ONLINE_PLAYER_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        ONLINE_PLAYER_NOTES_CLICK_LEFT = Component.text(Messages.ONLINE_PLAYER_NOTES_CLICK_LEFT, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        ONLINE_PLAYER_NOTES_CLICK_RIGHT = Component.text(Messages.ONLINE_PLAYER_NOTES_CLICK_RIGHT, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);

        WARPS_CLICK = Component.text(Messages.WARPS_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        WARP_COMMAND = Component.text(Messages.WARP_COMMAND, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        WARP_CATEGORY = Component.text(Messages.WARP_CATEGORY, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        WARP_CLICK = Component.text(Messages.WARP_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);

        RULES_CLICK = Component.text(Messages.RULES_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);

        LINKS_CLICK = Component.text(Messages.LINKS_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);

        DYNMAP_CLICK = Component.text(Messages.DYNMAP_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);

        ORDER_BY = Component.text(Messages.ORDER_BY, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        ORDER_DEFAULT = Component.text(Messages.ORDER_DEFAULT, TextColor.color(0xFFFF55), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        ORDER_NAME_AZ = Component.text(Messages.ORDER_NAME_AZ, TextColor.color(0xFFFF55), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        ORDER_NAME_ZA = Component.text(Messages.ORDER_NAME_ZA, TextColor.color(0xFFFF55), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        ORDER_AREA_ASC = Component.text(Messages.ORDER_AREA_ASC, TextColor.color(0xFFFF55), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        ORDER_AREA_DESC = Component.text(Messages.ORDER_AREA_DESC, TextColor.color(0xFFFF55), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        ORDER_CATEGORY_AZ = Component.text(Messages.ORDER_CATEGORY_AZ, TextColor.color(0xFFFF55), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        ORDER_CATEGORY_ZA = Component.text(Messages.ORDER_CATEGORY_ZA, TextColor.color(0xFFFF55), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        ORDER_COLOR = Component.text(Messages.ORDER_COLOR, TextColor.color(0xFFFF55), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        ORDER_CLICK = Component.text(Messages.ORDER_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    }
}
