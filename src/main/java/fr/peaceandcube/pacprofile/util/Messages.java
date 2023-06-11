package fr.peaceandcube.pacprofile.util;

import fr.peaceandcube.pacprofile.PACProfile;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;

public class Messages {
    public static TextComponent SENDER_NOT_PLAYER = error(PACProfile.getInstance().lang.translate("command_profile_sender_not_player"));
    public static TextComponent PLAYER_NOT_FOUND = error(PACProfile.getInstance().lang.translate("command_profile_player_not_found"));
    public static TextComponent RELOAD_SUCCESS = success(PACProfile.getInstance().lang.translate("command_reload_success"));

    public static String NOT_DEFINED = PACProfile.getInstance().lang.translate("not_defined");
    public static String INVALID = PACProfile.getInstance().lang.translate("invalid");

    public static String CONFIRMATION_TITLE = PACProfile.getInstance().lang.translate("confirmation_title");
    public static String CONFIRMATION_YES = PACProfile.getInstance().lang.translate("confirmation_yes");
    public static String CONFIRMATION_NO = PACProfile.getInstance().lang.translate("confirmation_no");

    public static String PROFILE = PACProfile.getInstance().lang.translate("profile");
    public static String PROFILE_RANK = PACProfile.getInstance().lang.translate("profile_rank");
    public static String PROFILE_RANK_EXPIRATION = PACProfile.getInstance().lang.translate("profile_rank_expiration");
    public static String PROFILE_NICKNAME = PACProfile.getInstance().lang.translate("profile_nickname");
    public static String PROFILE_BIRTHDAY = PACProfile.getInstance().lang.translate("profile_birthday");
    public static String PROFILE_JOIN_DATE = PACProfile.getInstance().lang.translate("profile_join_date");
    public static String STATISTICS = PACProfile.getInstance().lang.translate("statistics");
    public static String STATISTICS_BASE = PACProfile.getInstance().lang.translate("statistics_base");
    public static String STATISTICS_CURRENT = PACProfile.getInstance().lang.translate("statistics_current");
    public static String STATISTICS_HEALTH = PACProfile.getInstance().lang.translate("statistics_health");
    public static String STATISTICS_MAX_HEALTH = PACProfile.getInstance().lang.translate("statistics_max_health");
    public static String STATISTICS_ARMOR = PACProfile.getInstance().lang.translate("statistics_armor");
    public static String STATISTICS_ARMOR_TOUGHNESS = PACProfile.getInstance().lang.translate("statistics_armor_toughness");
    public static String STATISTICS_KNOCKBACK_RESISTANCE = PACProfile.getInstance().lang.translate("statistics_knockback_resistance");
    public static String STATISTICS_SPEED = PACProfile.getInstance().lang.translate("statistics_speed");
    public static String STATISTICS_ATTACK_DAMAGE = PACProfile.getInstance().lang.translate("statistics_attack_damage");
    public static String STATISTICS_ATTACK_SPEED = PACProfile.getInstance().lang.translate("statistics_attack_speed");
    public static String STATISTICS_LUCK = PACProfile.getInstance().lang.translate("statistics_luck");
    public static String STATISTICS_CLICK_BASE = PACProfile.getInstance().lang.translate("statistics_click_base");
    public static String STATISTICS_CLICK_CURRENT = PACProfile.getInstance().lang.translate("statistics_click_current");
    public static String SETTINGS = PACProfile.getInstance().lang.translate("settings");
    public static String SETTINGS_CLICK = PACProfile.getInstance().lang.translate("settings_click");
    public static String SETTINGS_TITLE = PACProfile.getInstance().lang.translate("settings_title");
    public static String SETTINGS_ENABLED = PACProfile.getInstance().lang.translate("settings_enabled");
    public static String SETTINGS_DISABLED = PACProfile.getInstance().lang.translate("settings_disabled");
    public static String SETTINGS_MSGTOGGLE = PACProfile.getInstance().lang.translate("settings_msgtoggle");
    public static String SETTINGS_MSGTOGGLE_CLICK = PACProfile.getInstance().lang.translate("settings_msgtoggle_click");
    public static String SETTINGS_TOGGLEMSGSOUND = PACProfile.getInstance().lang.translate("settings_togglemsgsound");
    public static String SETTINGS_TOGGLEMSGSOUND_CLICK = PACProfile.getInstance().lang.translate("settings_togglemsgsound_click");
    public static String SETTINGS_PTIME = PACProfile.getInstance().lang.translate("settings_ptime");
    public static String SETTINGS_PTIME_CLICK_LEFT = PACProfile.getInstance().lang.translate("settings_ptime_click_left");
    public static String SETTINGS_PTIME_CLICK_RIGHT = PACProfile.getInstance().lang.translate("settings_ptime_click_right");
    public static String SETTINGS_PWEATHER = PACProfile.getInstance().lang.translate("settings_pweather");
    public static String SETTINGS_PWEATHER_CLICK_LEFT = PACProfile.getInstance().lang.translate("settings_pweather_click_left");
    public static String SETTINGS_PWEATHER_CLICK_RIGHT = PACProfile.getInstance().lang.translate("settings_pweather_click_right");
    public static String COINS = PACProfile.getInstance().lang.translate("coins");
    public static String COINS_NUMBER = PACProfile.getInstance().lang.translate("coins_total");
    public static String COINS_CLICK = PACProfile.getInstance().lang.translate("coins_click");
    public static String HEAD_TICKETS = PACProfile.getInstance().lang.translate("head_tickets");
    public static String HEAD_TICKETS_NUMBER = PACProfile.getInstance().lang.translate("head_tickets_total");
    public static String HEAD_TICKETS_CLICK = PACProfile.getInstance().lang.translate("head_tickets_click");
    public static String MAILS = PACProfile.getInstance().lang.translate("mails");
    public static String MAILS_TOTAL = PACProfile.getInstance().lang.translate("mails_total");
    public static String MAILS_UNREAD = PACProfile.getInstance().lang.translate("mails_unread");
    public static String MAILS_CLICK = PACProfile.getInstance().lang.translate("mails_click");
    public static String HOMES = PACProfile.getInstance().lang.translate("homes");
    public static String HOMES_TOTAL = PACProfile.getInstance().lang.translate("homes_total");
    public static String HOMES_REMAINING = PACProfile.getInstance().lang.translate("homes_remaining");
    public static String HOMES_MAX_AVAILABLE = PACProfile.getInstance().lang.translate("homes_max_available");
    public static String HOMES_CLICK = PACProfile.getInstance().lang.translate("homes_click");
    public static String HOMES_TITLE = PACProfile.getInstance().lang.translate("homes_title");
    public static String HOMES_ORDER = PACProfile.getInstance().lang.translate("homes_order");
    public static String HOME_WORLD = PACProfile.getInstance().lang.translate("home_world");
    public static String HOME_X = PACProfile.getInstance().lang.translate("home_x");
    public static String HOME_Y = PACProfile.getInstance().lang.translate("home_y");
    public static String HOME_Z = PACProfile.getInstance().lang.translate("home_z");
    public static String HOME_CLICK_LEFT = PACProfile.getInstance().lang.translate("home_click_left");
    public static String HOME_CLICK_RIGHT = PACProfile.getInstance().lang.translate("home_click_right");
    public static String HOME_NOTES = PACProfile.getInstance().lang.translate("home_notes");
    public static String HOME_NOTES_CLICK = PACProfile.getInstance().lang.translate("home_notes_click");
    public static String HOME_NOTES_TITLE = PACProfile.getInstance().lang.translate("home_notes_title");
    public static String HOME_COLOR = PACProfile.getInstance().lang.translate("home_color");
    public static String HOME_COLOR_CLICK = PACProfile.getInstance().lang.translate("home_color_click");
    public static String HOME_COLOR_TITLE = PACProfile.getInstance().lang.translate("home_color_title");
    public static String CLAIMS = PACProfile.getInstance().lang.translate("claims");
    public static String CLAIMS_TOTAL = PACProfile.getInstance().lang.translate("claims_total");
    public static String CLAIMS_CB_USED = PACProfile.getInstance().lang.translate("claims_cb_used");
    public static String CLAIMS_CB_REMAINING = PACProfile.getInstance().lang.translate("claims_cb_remaining");
    public static String CLAIMS_CB_ACCRUED = PACProfile.getInstance().lang.translate("claims_cb_accrued");
    public static String CLAIMS_CB_BONUS = PACProfile.getInstance().lang.translate("claims_cb_bonus");
    public static String CLAIMS_CB_TOTAL = PACProfile.getInstance().lang.translate("claims_cb_total");
    public static String CLAIMS_CB_PER_HOUR = PACProfile.getInstance().lang.translate("claims_cb_per_hour");
    public static String CLAIMS_CLICK = PACProfile.getInstance().lang.translate("claims_click");
    public static String CLAIMS_TITLE = PACProfile.getInstance().lang.translate("claims_title");
    public static String CLAIMS_ORDER = PACProfile.getInstance().lang.translate("claims_order");
    public static String CLAIM_WORLD = PACProfile.getInstance().lang.translate("claim_world");
    public static String CLAIM_GREATER_CORNER = PACProfile.getInstance().lang.translate("claim_greater_corner");
    public static String CLAIM_LESSER_CORNER = PACProfile.getInstance().lang.translate("claim_lesser_corner");
    public static String CLAIM_WIDTH = PACProfile.getInstance().lang.translate("claim_width");
    public static String CLAIM_LENGTH = PACProfile.getInstance().lang.translate("claim_length");
    public static String CLAIM_AREA = PACProfile.getInstance().lang.translate("claim_area");
    public static String CLAIM_PERMISSIONS = PACProfile.getInstance().lang.translate("claim_permissions");
    public static String CLAIM_PERMISSIONS_BUILDERS = PACProfile.getInstance().lang.translate("claim_permissions_builders");
    public static String CLAIM_PERMISSIONS_CONTAINERS = PACProfile.getInstance().lang.translate("claim_permissions_containers");
    public static String CLAIM_PERMISSIONS_ACCESSORS = PACProfile.getInstance().lang.translate("claim_permissions_accessors");
    public static String CLAIM_PERMISSIONS_MANAGERS = PACProfile.getInstance().lang.translate("claim_permissions_managers");
    public static String CLAIM_NAME = PACProfile.getInstance().lang.translate("claim_name");
    public static String CLAIM_NAME_CLICK = PACProfile.getInstance().lang.translate("claim_name_click");
    public static String CLAIM_NAME_TITLE = PACProfile.getInstance().lang.translate("claim_name_title");
    public static String ONLINE_PLAYERS = PACProfile.getInstance().lang.translate("online_players");
    public static String ONLINE_PLAYERS_COUNT = PACProfile.getInstance().lang.translate("online_players_count");
    public static String ONLINE_PLAYERS_CLICK = PACProfile.getInstance().lang.translate("online_players_click");
    public static String ONLINE_PLAYERS_TITLE = PACProfile.getInstance().lang.translate("online_players_title");
    public static String ONLINE_PLAYER_TRUST_COUNT_1 = PACProfile.getInstance().lang.translate("online_player_trust_count_1");
    public static String ONLINE_PLAYER_TRUST_COUNT_2 = PACProfile.getInstance().lang.translate("online_player_trust_count_2");
    public static String ONLINE_PLAYER_CLICK = PACProfile.getInstance().lang.translate("online_player_click");
    public static String ONLINE_PLAYER_NOTES_CLICK = PACProfile.getInstance().lang.translate("online_player_notes_click");
    public static String ONLINE_PLAYER_NOTES_TITLE = PACProfile.getInstance().lang.translate("online_player_notes_title");
    public static String WARPS = PACProfile.getInstance().lang.translate("warps");
    public static String WARPS_CLICK = PACProfile.getInstance().lang.translate("warps_click");
    public static String WARPS_TITLE = PACProfile.getInstance().lang.translate("warps_title");
    public static String WARPS_ORDER = PACProfile.getInstance().lang.translate("warps_order");
    public static String WARP_COMMAND = PACProfile.getInstance().lang.translate("warp_command");
    public static String WARP_CATEGORY = PACProfile.getInstance().lang.translate("warp_category");
    public static String WARP_CLICK = PACProfile.getInstance().lang.translate("warp_click");
    public static String RULES = PACProfile.getInstance().lang.translate("rules");
    public static String RULES_CLICK = PACProfile.getInstance().lang.translate("rules_click");
    public static String LINKS = PACProfile.getInstance().lang.translate("links");
    public static String LINKS_CLICK = PACProfile.getInstance().lang.translate("links_click");
    public static String DYNMAP = PACProfile.getInstance().lang.translate("dynmap");
    public static String DYNMAP_CLICK = PACProfile.getInstance().lang.translate("dynmap_click");
    public static String EXIT = PACProfile.getInstance().lang.translate("exit");
    public static String PAGE_PREVIOUS = PACProfile.getInstance().lang.translate("page_previous");
    public static String PAGE_NEXT = PACProfile.getInstance().lang.translate("page_next");
    public static String ORDER_BY = PACProfile.getInstance().lang.translate("order_by");
    public static String ORDER_DEFAULT = PACProfile.getInstance().lang.translate("order_default");
    public static String ORDER_NAME_AZ = PACProfile.getInstance().lang.translate("order_name_az");
    public static String ORDER_NAME_ZA = PACProfile.getInstance().lang.translate("order_name_za");
    public static String ORDER_AREA_ASC = PACProfile.getInstance().lang.translate("order_area_asc");
    public static String ORDER_AREA_DESC = PACProfile.getInstance().lang.translate("order_area_desc");
    public static String ORDER_CATEGORY_AZ = PACProfile.getInstance().lang.translate("order_category_az");
    public static String ORDER_CATEGORY_ZA = PACProfile.getInstance().lang.translate("order_category_za");
    public static String ORDER_CLICK = PACProfile.getInstance().lang.translate("order_click");

    public static void init() {
        SENDER_NOT_PLAYER = error(PACProfile.getInstance().lang.translate("command_profile_sender_not_player"));
        PLAYER_NOT_FOUND = error(PACProfile.getInstance().lang.translate("command_profile_player_not_found"));
        RELOAD_SUCCESS = success(PACProfile.getInstance().lang.translate("command_reload_success"));

        NOT_DEFINED = PACProfile.getInstance().lang.translate("not_defined");
        INVALID = PACProfile.getInstance().lang.translate("invalid");

        CONFIRMATION_TITLE = PACProfile.getInstance().lang.translate("confirmation_title");
        CONFIRMATION_YES = PACProfile.getInstance().lang.translate("confirmation_yes");
        CONFIRMATION_NO = PACProfile.getInstance().lang.translate("confirmation_no");

        PROFILE = PACProfile.getInstance().lang.translate("profile");
        PROFILE_RANK = PACProfile.getInstance().lang.translate("profile_rank");
        PROFILE_RANK_EXPIRATION = PACProfile.getInstance().lang.translate("profile_rank_expiration");
        PROFILE_NICKNAME = PACProfile.getInstance().lang.translate("profile_nickname");
        PROFILE_BIRTHDAY = PACProfile.getInstance().lang.translate("profile_birthday");
        PROFILE_JOIN_DATE = PACProfile.getInstance().lang.translate("profile_join_date");
        STATISTICS = PACProfile.getInstance().lang.translate("statistics");
        STATISTICS_BASE = PACProfile.getInstance().lang.translate("statistics_base");
        STATISTICS_CURRENT = PACProfile.getInstance().lang.translate("statistics_current");
        STATISTICS_HEALTH = PACProfile.getInstance().lang.translate("statistics_health");
        STATISTICS_MAX_HEALTH = PACProfile.getInstance().lang.translate("statistics_max_health");
        STATISTICS_ARMOR = PACProfile.getInstance().lang.translate("statistics_armor");
        STATISTICS_ARMOR_TOUGHNESS = PACProfile.getInstance().lang.translate("statistics_armor_toughness");
        STATISTICS_KNOCKBACK_RESISTANCE = PACProfile.getInstance().lang.translate("statistics_knockback_resistance");
        STATISTICS_SPEED = PACProfile.getInstance().lang.translate("statistics_speed");
        STATISTICS_ATTACK_DAMAGE = PACProfile.getInstance().lang.translate("statistics_attack_damage");
        STATISTICS_ATTACK_SPEED = PACProfile.getInstance().lang.translate("statistics_attack_speed");
        STATISTICS_LUCK = PACProfile.getInstance().lang.translate("statistics_luck");
        STATISTICS_CLICK_BASE = PACProfile.getInstance().lang.translate("statistics_click_base");
        STATISTICS_CLICK_CURRENT = PACProfile.getInstance().lang.translate("statistics_click_current");
        SETTINGS = PACProfile.getInstance().lang.translate("settings");
        SETTINGS_CLICK = PACProfile.getInstance().lang.translate("settings_click");
        SETTINGS_TITLE = PACProfile.getInstance().lang.translate("settings_title");
        SETTINGS_ENABLED = PACProfile.getInstance().lang.translate("settings_enabled");
        SETTINGS_DISABLED = PACProfile.getInstance().lang.translate("settings_disabled");
        SETTINGS_MSGTOGGLE = PACProfile.getInstance().lang.translate("settings_msgtoggle");
        SETTINGS_MSGTOGGLE_CLICK = PACProfile.getInstance().lang.translate("settings_msgtoggle_click");
        SETTINGS_TOGGLEMSGSOUND = PACProfile.getInstance().lang.translate("settings_togglemsgsound");
        SETTINGS_TOGGLEMSGSOUND_CLICK = PACProfile.getInstance().lang.translate("settings_togglemsgsound_click");
        SETTINGS_PTIME = PACProfile.getInstance().lang.translate("settings_ptime");
        SETTINGS_PTIME_CLICK_LEFT = PACProfile.getInstance().lang.translate("settings_ptime_click_left");
        SETTINGS_PTIME_CLICK_RIGHT = PACProfile.getInstance().lang.translate("settings_ptime_click_right");
        SETTINGS_PWEATHER = PACProfile.getInstance().lang.translate("settings_pweather");
        SETTINGS_PWEATHER_CLICK_LEFT = PACProfile.getInstance().lang.translate("settings_pweather_click_left");
        SETTINGS_PWEATHER_CLICK_RIGHT = PACProfile.getInstance().lang.translate("settings_pweather_click_right");
        COINS = PACProfile.getInstance().lang.translate("coins");
        COINS_NUMBER = PACProfile.getInstance().lang.translate("coins_total");
        COINS_CLICK = PACProfile.getInstance().lang.translate("coins_click");
        HEAD_TICKETS = PACProfile.getInstance().lang.translate("head_tickets");
        HEAD_TICKETS_NUMBER = PACProfile.getInstance().lang.translate("head_tickets_total");
        HEAD_TICKETS_CLICK = PACProfile.getInstance().lang.translate("head_tickets_click");
        MAILS = PACProfile.getInstance().lang.translate("mails");
        MAILS_TOTAL = PACProfile.getInstance().lang.translate("mails_total");
        MAILS_UNREAD = PACProfile.getInstance().lang.translate("mails_unread");
        MAILS_CLICK = PACProfile.getInstance().lang.translate("mails_click");
        HOMES = PACProfile.getInstance().lang.translate("homes");
        HOMES_TOTAL = PACProfile.getInstance().lang.translate("homes_total");
        HOMES_REMAINING = PACProfile.getInstance().lang.translate("homes_remaining");
        HOMES_MAX_AVAILABLE = PACProfile.getInstance().lang.translate("homes_max_available");
        HOMES_CLICK = PACProfile.getInstance().lang.translate("homes_click");
        HOMES_TITLE = PACProfile.getInstance().lang.translate("homes_title");
        HOMES_ORDER = PACProfile.getInstance().lang.translate("homes_order");
        HOME_WORLD = PACProfile.getInstance().lang.translate("home_world");
        HOME_X = PACProfile.getInstance().lang.translate("home_x");
        HOME_Y = PACProfile.getInstance().lang.translate("home_y");
        HOME_Z = PACProfile.getInstance().lang.translate("home_z");
        HOME_CLICK_LEFT = PACProfile.getInstance().lang.translate("home_click_left");
        HOME_CLICK_RIGHT = PACProfile.getInstance().lang.translate("home_click_right");
        HOME_NOTES = PACProfile.getInstance().lang.translate("home_notes");
        HOME_NOTES_CLICK = PACProfile.getInstance().lang.translate("home_notes_click");
        HOME_NOTES_TITLE = PACProfile.getInstance().lang.translate("home_notes_title");
        HOME_COLOR = PACProfile.getInstance().lang.translate("home_color");
        HOME_COLOR_CLICK = PACProfile.getInstance().lang.translate("home_color_click");
        HOME_COLOR_TITLE = PACProfile.getInstance().lang.translate("home_color_title");
        CLAIMS = PACProfile.getInstance().lang.translate("claims");
        CLAIMS_TOTAL = PACProfile.getInstance().lang.translate("claims_total");
        CLAIMS_CB_USED = PACProfile.getInstance().lang.translate("claims_cb_used");
        CLAIMS_CB_REMAINING = PACProfile.getInstance().lang.translate("claims_cb_remaining");
        CLAIMS_CB_ACCRUED = PACProfile.getInstance().lang.translate("claims_cb_accrued");
        CLAIMS_CB_BONUS = PACProfile.getInstance().lang.translate("claims_cb_bonus");
        CLAIMS_CB_TOTAL = PACProfile.getInstance().lang.translate("claims_cb_total");
        CLAIMS_CB_PER_HOUR = PACProfile.getInstance().lang.translate("claims_cb_per_hour");
        CLAIMS_CLICK = PACProfile.getInstance().lang.translate("claims_click");
        CLAIMS_TITLE = PACProfile.getInstance().lang.translate("claims_title");
        CLAIMS_ORDER = PACProfile.getInstance().lang.translate("claims_order");
        CLAIM_WORLD = PACProfile.getInstance().lang.translate("claim_world");
        CLAIM_GREATER_CORNER = PACProfile.getInstance().lang.translate("claim_greater_corner");
        CLAIM_LESSER_CORNER = PACProfile.getInstance().lang.translate("claim_lesser_corner");
        CLAIM_WIDTH = PACProfile.getInstance().lang.translate("claim_width");
        CLAIM_LENGTH = PACProfile.getInstance().lang.translate("claim_length");
        CLAIM_AREA = PACProfile.getInstance().lang.translate("claim_area");
        CLAIM_PERMISSIONS = PACProfile.getInstance().lang.translate("claim_permissions");
        CLAIM_PERMISSIONS_BUILDERS = PACProfile.getInstance().lang.translate("claim_permissions_builders");
        CLAIM_PERMISSIONS_CONTAINERS = PACProfile.getInstance().lang.translate("claim_permissions_containers");
        CLAIM_PERMISSIONS_ACCESSORS = PACProfile.getInstance().lang.translate("claim_permissions_accessors");
        CLAIM_PERMISSIONS_MANAGERS = PACProfile.getInstance().lang.translate("claim_permissions_managers");
        CLAIM_NAME = PACProfile.getInstance().lang.translate("claim_name");
        CLAIM_NAME_CLICK = PACProfile.getInstance().lang.translate("claim_name_click");
        CLAIM_NAME_TITLE = PACProfile.getInstance().lang.translate("claim_name_title");
        ONLINE_PLAYERS = PACProfile.getInstance().lang.translate("online_players");
        ONLINE_PLAYERS_COUNT = PACProfile.getInstance().lang.translate("online_players_count");
        ONLINE_PLAYERS_CLICK = PACProfile.getInstance().lang.translate("online_players_click");
        ONLINE_PLAYERS_TITLE = PACProfile.getInstance().lang.translate("online_players_title");
        ONLINE_PLAYER_TRUST_COUNT_1 = PACProfile.getInstance().lang.translate("online_player_trust_count_1");
        ONLINE_PLAYER_TRUST_COUNT_2 = PACProfile.getInstance().lang.translate("online_player_trust_count_2");
        ONLINE_PLAYER_CLICK = PACProfile.getInstance().lang.translate("online_player_click");
        ONLINE_PLAYER_NOTES_CLICK = PACProfile.getInstance().lang.translate("online_player_notes_click");
        ONLINE_PLAYER_NOTES_TITLE = PACProfile.getInstance().lang.translate("online_player_notes_title");
        WARPS = PACProfile.getInstance().lang.translate("warps");
        WARPS_CLICK = PACProfile.getInstance().lang.translate("warps_click");
        WARPS_TITLE = PACProfile.getInstance().lang.translate("warps_title");
        WARPS_ORDER = PACProfile.getInstance().lang.translate("warps_order");
        WARP_COMMAND = PACProfile.getInstance().lang.translate("warp_command");
        WARP_CATEGORY = PACProfile.getInstance().lang.translate("warp_category");
        WARP_CLICK = PACProfile.getInstance().lang.translate("warp_click");
        RULES = PACProfile.getInstance().lang.translate("rules");
        RULES_CLICK = PACProfile.getInstance().lang.translate("rules_click");
        LINKS = PACProfile.getInstance().lang.translate("links");
        LINKS_CLICK = PACProfile.getInstance().lang.translate("links_click");
        DYNMAP = PACProfile.getInstance().lang.translate("dynmap");
        DYNMAP_CLICK = PACProfile.getInstance().lang.translate("dynmap_click");
        EXIT = PACProfile.getInstance().lang.translate("exit");
        PAGE_PREVIOUS = PACProfile.getInstance().lang.translate("page_previous");
        PAGE_NEXT = PACProfile.getInstance().lang.translate("page_next");
        ORDER_BY = PACProfile.getInstance().lang.translate("order_by");
        ORDER_DEFAULT = PACProfile.getInstance().lang.translate("order_default");
        ORDER_NAME_AZ = PACProfile.getInstance().lang.translate("order_name_az");
        ORDER_NAME_ZA = PACProfile.getInstance().lang.translate("order_name_za");
        ORDER_AREA_ASC = PACProfile.getInstance().lang.translate("order_area_asc");
        ORDER_AREA_DESC = PACProfile.getInstance().lang.translate("order_area_desc");
        ORDER_CATEGORY_AZ = PACProfile.getInstance().lang.translate("order_category_az");
        ORDER_CATEGORY_ZA = PACProfile.getInstance().lang.translate("order_category_za");
        ORDER_CLICK = PACProfile.getInstance().lang.translate("order_click");
    }

    public static TextComponent error(String msg) {
        return Component.text(msg, TextColor.color(0xFF5555));
    }

    public static TextComponent success(String msg) {
        return Component.text(msg, TextColor.color(0x55FF55));
    }
}
