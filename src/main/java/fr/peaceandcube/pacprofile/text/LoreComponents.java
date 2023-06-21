package fr.peaceandcube.pacprofile.text;

import fr.peaceandcube.pacprofile.util.Messages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class LoreComponents {
    public static Component PROFILE_RANK = Component.text(Messages.PROFILE_RANK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component PROFILE_RANK_EXPIRATION = Component.text(Messages.PROFILE_RANK_EXPIRATION, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component PROFILE_NICKNAME = Component.text(Messages.PROFILE_NICKNAME, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component PROFILE_BIRTHDAY = Component.text(Messages.PROFILE_BIRTHDAY, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component PROFILE_JOIN_DATE = Component.text(Messages.PROFILE_JOIN_DATE, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);

    public static Component STATISTICS_BASE = Component.text(Messages.STATISTICS_BASE, TextColor.color(0xFF5555)).decoration(TextDecoration.ITALIC, false);
    public static Component STATISTICS_CURRENT = Component.text(Messages.STATISTICS_CURRENT, TextColor.color(0x55FF55)).decoration(TextDecoration.ITALIC, false);
    public static Component STATISTICS_HEALTH = Component.text(Messages.STATISTICS_HEALTH, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component STATISTICS_MAX_HEALTH = Component.text(Messages.STATISTICS_MAX_HEALTH, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component STATISTICS_ARMOR = Component.text(Messages.STATISTICS_ARMOR, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component STATISTICS_ARMOR_TOUGHNESS = Component.text(Messages.STATISTICS_ARMOR_TOUGHNESS, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component STATISTICS_KNOCKBACK_RESISTANCE = Component.text(Messages.STATISTICS_KNOCKBACK_RESISTANCE, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component STATISTICS_SPEED = Component.text(Messages.STATISTICS_SPEED, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component STATISTICS_ATTACK_DAMAGE = Component.text(Messages.STATISTICS_ATTACK_DAMAGE, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component STATISTICS_ATTACK_SPEED = Component.text(Messages.STATISTICS_ATTACK_SPEED, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component STATISTICS_LUCK = Component.text(Messages.STATISTICS_LUCK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component STATISTICS_CLICK_BASE = Component.text(Messages.STATISTICS_CLICK_BASE, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component STATISTICS_CLICK_CURRENT = Component.text(Messages.STATISTICS_CLICK_CURRENT, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);

    public static Component SETTINGS_CLICK = Component.text(Messages.SETTINGS_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component SETTINGS_ENABLED = Component.text(Messages.SETTINGS_ENABLED, TextColor.color(0x55FF55), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component SETTINGS_DISABLED = Component.text(Messages.SETTINGS_DISABLED, TextColor.color(0xFF5555), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component SETTINGS_MSGTOGGLE_CLICK = Component.text(Messages.SETTINGS_MSGTOGGLE_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component SETTINGS_TOGGLEMSGSOUND_CLICK = Component.text(Messages.SETTINGS_TOGGLEMSGSOUND_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component SETTINGS_PTIME_CLICK_LEFT = Component.text(Messages.SETTINGS_PTIME_CLICK_LEFT, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component SETTINGS_PTIME_CLICK_RIGHT = Component.text(Messages.SETTINGS_PTIME_CLICK_RIGHT, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component SETTINGS_PWEATHER_CLICK_LEFT = Component.text(Messages.SETTINGS_PWEATHER_CLICK_LEFT, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component SETTINGS_PWEATHER_CLICK_RIGHT = Component.text(Messages.SETTINGS_PWEATHER_CLICK_RIGHT, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);

    public static Component COINS_NUMBER = Component.text(Messages.COINS_NUMBER, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component COINS_CLICK = Component.text(Messages.COINS_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);

    public static Component HEAD_TICKETS_NUMBER = Component.text(Messages.HEAD_TICKETS_NUMBER, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component HEAD_TICKETS_CLICK = Component.text(Messages.HEAD_TICKETS_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);

    public static Component MAILS_TOTAL = Component.text(Messages.MAILS_TOTAL, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component MAILS_UNREAD = Component.text(Messages.MAILS_UNREAD, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component MAILS_CLICK = Component.text(Messages.MAILS_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);

    public static Component HOMES_TOTAL = Component.text(Messages.HOMES_TOTAL, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component HOMES_REMAINING = Component.text(Messages.HOMES_REMAINING, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component HOMES_MAX_AVAILABLE = Component.text(Messages.HOMES_MAX_AVAILABLE, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component HOMES_CLICK = Component.text(Messages.HOMES_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component HOME_WORLD = Component.text(Messages.HOME_WORLD, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component HOME_X = Component.text(Messages.HOME_X, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component HOME_Y = Component.text(Messages.HOME_Y, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component HOME_Z = Component.text(Messages.HOME_Z, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component HOME_CLICK_LEFT = Component.text(Messages.HOME_CLICK_LEFT, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component HOME_CLICK_RIGHT = Component.text(Messages.HOME_CLICK_RIGHT, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component HOME_NOTES_CLICK_LEFT = Component.text(Messages.HOME_NOTES_CLICK_LEFT, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component HOME_NOTES_CLICK_RIGHT = Component.text(Messages.HOME_NOTES_CLICK_RIGHT, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component HOME_COLOR_CLICK = Component.text(Messages.HOME_COLOR_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);

    public static Component CLAIMS_TOTAL = Component.text(Messages.CLAIMS_TOTAL, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component CLAIMS_CB_USED = Component.text(Messages.CLAIMS_CB_USED, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component CLAIMS_CB_REMAINING = Component.text(Messages.CLAIMS_CB_REMAINING, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component CLAIMS_CB_ACCRUED = Component.text(Messages.CLAIMS_CB_ACCRUED, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component CLAIMS_CB_BONUS = Component.text(Messages.CLAIMS_CB_BONUS, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component CLAIMS_CB_TOTAL = Component.text(Messages.CLAIMS_CB_TOTAL, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component CLAIMS_CB_PER_HOUR = Component.text(Messages.CLAIMS_CB_PER_HOUR, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component CLAIMS_CLICK = Component.text(Messages.CLAIMS_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component CLAIM_WORLD = Component.text(Messages.CLAIM_WORLD, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component CLAIM_GREATER_CORNER = Component.text(Messages.CLAIM_GREATER_CORNER, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component CLAIM_LESSER_CORNER = Component.text(Messages.CLAIM_LESSER_CORNER, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component CLAIM_WIDTH = Component.text(Messages.CLAIM_WIDTH, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component CLAIM_LENGTH = Component.text(Messages.CLAIM_LENGTH, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component CLAIM_AREA = Component.text(Messages.CLAIM_AREA, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component CLAIM_PERMISSIONS_BUILDERS = Component.text(Messages.CLAIM_PERMISSIONS_BUILDERS, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component CLAIM_PERMISSIONS_CONTAINERS = Component.text(Messages.CLAIM_PERMISSIONS_CONTAINERS, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component CLAIM_PERMISSIONS_ACCESSORS = Component.text(Messages.CLAIM_PERMISSIONS_ACCESSORS, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component CLAIM_PERMISSIONS_MANAGERS = Component.text(Messages.CLAIM_PERMISSIONS_MANAGERS, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component CLAIM_NAME_CLICK = Component.text(Messages.CLAIM_NAME_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);

    public static Component ONLINE_PLAYERS_COUNT = Component.text(Messages.ONLINE_PLAYERS_COUNT, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component ONLINE_PLAYERS_CLICK = Component.text(Messages.ONLINE_PLAYERS_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component ONLINE_PLAYER_TRUST_COUNT_1 = Component.text(Messages.ONLINE_PLAYER_TRUST_COUNT_1, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component ONLINE_PLAYER_TRUST_COUNT_2 = Component.text(Messages.ONLINE_PLAYER_TRUST_COUNT_2, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component ONLINE_PLAYER_CLICK = Component.text(Messages.ONLINE_PLAYER_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component ONLINE_PLAYER_NOTES_CLICK_LEFT = Component.text(Messages.ONLINE_PLAYER_NOTES_CLICK_LEFT, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component ONLINE_PLAYER_NOTES_CLICK_RIGHT = Component.text(Messages.ONLINE_PLAYER_NOTES_CLICK_RIGHT, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);

    public static Component WARPS_CLICK = Component.text(Messages.WARPS_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component WARP_COMMAND = Component.text(Messages.WARP_COMMAND, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component WARP_CATEGORY = Component.text(Messages.WARP_CATEGORY, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component WARP_CLICK = Component.text(Messages.WARP_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);

    public static Component RULES_CLICK = Component.text(Messages.RULES_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);

    public static Component LINKS_CLICK = Component.text(Messages.LINKS_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);

    public static Component DYNMAP_CLICK = Component.text(Messages.DYNMAP_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);

    public static Component ORDER_BY = Component.text(Messages.ORDER_BY, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    public static Component ORDER_DEFAULT = Component.text(Messages.ORDER_DEFAULT, TextColor.color(0xFFFF55), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component ORDER_NAME_AZ = Component.text(Messages.ORDER_NAME_AZ, TextColor.color(0xFFFF55), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component ORDER_NAME_ZA = Component.text(Messages.ORDER_NAME_ZA, TextColor.color(0xFFFF55), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component ORDER_AREA_ASC = Component.text(Messages.ORDER_AREA_ASC, TextColor.color(0xFFFF55), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component ORDER_AREA_DESC = Component.text(Messages.ORDER_AREA_DESC, TextColor.color(0xFFFF55), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component ORDER_CATEGORY_AZ = Component.text(Messages.ORDER_CATEGORY_AZ, TextColor.color(0xFFFF55), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component ORDER_CATEGORY_ZA = Component.text(Messages.ORDER_CATEGORY_ZA, TextColor.color(0xFFFF55), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    public static Component ORDER_CLICK = Component.text(Messages.ORDER_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);

    public static void init() {
        PROFILE_RANK = Component.text(Messages.PROFILE_RANK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        PROFILE_RANK_EXPIRATION = Component.text(Messages.PROFILE_RANK_EXPIRATION, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        PROFILE_NICKNAME = Component.text(Messages.PROFILE_NICKNAME, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        PROFILE_BIRTHDAY = Component.text(Messages.PROFILE_BIRTHDAY, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        PROFILE_JOIN_DATE = Component.text(Messages.PROFILE_JOIN_DATE, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);

        STATISTICS_BASE = Component.text(Messages.STATISTICS_BASE, TextColor.color(0xFF5555)).decoration(TextDecoration.ITALIC, false);
        STATISTICS_CURRENT = Component.text(Messages.STATISTICS_CURRENT, TextColor.color(0x55FF55)).decoration(TextDecoration.ITALIC, false);
        STATISTICS_HEALTH = Component.text(Messages.STATISTICS_HEALTH, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        STATISTICS_MAX_HEALTH = Component.text(Messages.STATISTICS_MAX_HEALTH, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        STATISTICS_ARMOR = Component.text(Messages.STATISTICS_ARMOR, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        STATISTICS_ARMOR_TOUGHNESS = Component.text(Messages.STATISTICS_ARMOR_TOUGHNESS, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        STATISTICS_KNOCKBACK_RESISTANCE = Component.text(Messages.STATISTICS_KNOCKBACK_RESISTANCE, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        STATISTICS_SPEED = Component.text(Messages.STATISTICS_SPEED, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        STATISTICS_ATTACK_DAMAGE = Component.text(Messages.STATISTICS_ATTACK_DAMAGE, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        STATISTICS_ATTACK_SPEED = Component.text(Messages.STATISTICS_ATTACK_SPEED, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
        STATISTICS_LUCK = Component.text(Messages.STATISTICS_LUCK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
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
        ORDER_CLICK = Component.text(Messages.ORDER_CLICK, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
    }
}
