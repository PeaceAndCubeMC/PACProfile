package fr.peaceandcube.pacprofile.text;

import fr.peaceandcube.pacprofile.util.Messages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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
    public static Component HEAD_TICKETS_QUEST_NUMBER;
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
    public static Component ONLINE_PLAYER_MAIL_SENT_1;
    public static Component ONLINE_PLAYER_MAIL_SENT_2;
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
    public static Component ORDER_CLICK;

    static {
        init();
    }

    public static void init() {
        PROFILE_RANK = Component.text(Messages.PROFILE_RANK, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        PROFILE_RANK_EXPIRATION = Component.text(Messages.PROFILE_RANK_EXPIRATION, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        PROFILE_NICKNAME = Component.text(Messages.PROFILE_NICKNAME, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        PROFILE_BIRTHDAY = Component.text(Messages.PROFILE_BIRTHDAY, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        PROFILE_JOIN_DATE = Component.text(Messages.PROFILE_JOIN_DATE, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);

        STATISTICS_BASE = Component.text(Messages.STATISTICS_BASE, NamedTextColor.RED).decoration(TextDecoration.ITALIC, false);
        STATISTICS_CURRENT = Component.text(Messages.STATISTICS_CURRENT, NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false);
        STATISTICS_CLICK_BASE = Component.text(Messages.STATISTICS_CLICK_BASE, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        STATISTICS_CLICK_CURRENT = Component.text(Messages.STATISTICS_CLICK_CURRENT, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);

        SETTINGS_CLICK = Component.text(Messages.SETTINGS_CLICK, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        SETTINGS_ENABLED = Component.text(Messages.SETTINGS_ENABLED, NamedTextColor.GREEN, TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        SETTINGS_DISABLED = Component.text(Messages.SETTINGS_DISABLED, NamedTextColor.RED, TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
        SETTINGS_MSGTOGGLE_CLICK = Component.text(Messages.SETTINGS_MSGTOGGLE_CLICK, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        SETTINGS_TOGGLEMSGSOUND_CLICK = Component.text(Messages.SETTINGS_TOGGLEMSGSOUND_CLICK, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        SETTINGS_PTIME_CLICK_LEFT = Component.text(Messages.SETTINGS_PTIME_CLICK_LEFT, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        SETTINGS_PTIME_CLICK_RIGHT = Component.text(Messages.SETTINGS_PTIME_CLICK_RIGHT, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        SETTINGS_PWEATHER_CLICK_LEFT = Component.text(Messages.SETTINGS_PWEATHER_CLICK_LEFT, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        SETTINGS_PWEATHER_CLICK_RIGHT = Component.text(Messages.SETTINGS_PWEATHER_CLICK_RIGHT, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);

        COINS_NUMBER = Component.text(Messages.COINS_NUMBER, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        COINS_CLICK = Component.text(Messages.COINS_CLICK, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);

        HEAD_TICKETS_NUMBER = Component.text(Messages.HEAD_TICKETS_NUMBER, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        HEAD_TICKETS_QUEST_NUMBER = Component.text(Messages.HEAD_TICKETS_QUEST_NUMBER, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        HEAD_TICKETS_CLICK = Component.text(Messages.HEAD_TICKETS_CLICK, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);

        MAILS_TOTAL = Component.text(Messages.MAILS_TOTAL, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        MAILS_UNREAD = Component.text(Messages.MAILS_UNREAD, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        MAILS_CLICK = Component.text(Messages.MAILS_CLICK, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);

        HOMES_TOTAL = Component.text(Messages.HOMES_TOTAL, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        HOMES_REMAINING = Component.text(Messages.HOMES_REMAINING, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        HOMES_MAX_AVAILABLE = Component.text(Messages.HOMES_MAX_AVAILABLE, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        HOMES_CLICK = Component.text(Messages.HOMES_CLICK, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        HOME_WORLD = Component.text(Messages.HOME_WORLD, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        HOME_X = Component.text(Messages.HOME_X, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        HOME_Y = Component.text(Messages.HOME_Y, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        HOME_Z = Component.text(Messages.HOME_Z, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        HOME_CLICK_LEFT = Component.text(Messages.HOME_CLICK_LEFT, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        HOME_CLICK_RIGHT = Component.text(Messages.HOME_CLICK_RIGHT, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        HOME_NOTES_CLICK_LEFT = Component.text(Messages.HOME_NOTES_CLICK_LEFT, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        HOME_NOTES_CLICK_RIGHT = Component.text(Messages.HOME_NOTES_CLICK_RIGHT, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        HOME_COLOR_CLICK = Component.text(Messages.HOME_COLOR_CLICK, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);

        CLAIMS_TOTAL = Component.text(Messages.CLAIMS_TOTAL, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        CLAIMS_CB_USED = Component.text(Messages.CLAIMS_CB_USED, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        CLAIMS_CB_REMAINING = Component.text(Messages.CLAIMS_CB_REMAINING, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        CLAIMS_CB_ACCRUED = Component.text(Messages.CLAIMS_CB_ACCRUED, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        CLAIMS_CB_BONUS = Component.text(Messages.CLAIMS_CB_BONUS, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        CLAIMS_CB_TOTAL = Component.text(Messages.CLAIMS_CB_TOTAL, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        CLAIMS_CB_PER_HOUR = Component.text(Messages.CLAIMS_CB_PER_HOUR, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        CLAIMS_CLICK = Component.text(Messages.CLAIMS_CLICK, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
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

        ONLINE_PLAYERS_COUNT = Component.text(Messages.ONLINE_PLAYERS_COUNT, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        ONLINE_PLAYERS_CLICK = Component.text(Messages.ONLINE_PLAYERS_CLICK, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        ONLINE_PLAYER_TRUST_COUNT_1 = Component.text(Messages.ONLINE_PLAYER_TRUST_COUNT_1, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        ONLINE_PLAYER_TRUST_COUNT_2 = Component.text(Messages.ONLINE_PLAYER_TRUST_COUNT_2, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        ONLINE_PLAYER_MAIL_SENT_1 = Component.text(Messages.ONLINE_PLAYER_MAIL_SENT_1, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        ONLINE_PLAYER_MAIL_SENT_2 = Component.text(Messages.ONLINE_PLAYER_MAIL_SENT_2, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        ONLINE_PLAYER_CLICK = Component.text(Messages.ONLINE_PLAYER_CLICK, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        ONLINE_PLAYER_NOTES_CLICK_LEFT = Component.text(Messages.ONLINE_PLAYER_NOTES_CLICK_LEFT, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        ONLINE_PLAYER_NOTES_CLICK_RIGHT = Component.text(Messages.ONLINE_PLAYER_NOTES_CLICK_RIGHT, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);

        WARPS_CLICK = Component.text(Messages.WARPS_CLICK, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        WARP_COMMAND = Component.text(Messages.WARP_COMMAND, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        WARP_CATEGORY = Component.text(Messages.WARP_CATEGORY, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        WARP_CLICK = Component.text(Messages.WARP_CLICK, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);

        RULES_CLICK = Component.text(Messages.RULES_CLICK, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);

        LINKS_CLICK = Component.text(Messages.LINKS_CLICK, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);

        DYNMAP_CLICK = Component.text(Messages.DYNMAP_CLICK, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);

        ORDER_BY = Component.text(Messages.ORDER_BY, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
        ORDER_CLICK = Component.text(Messages.ORDER_CLICK, NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
    }
}
