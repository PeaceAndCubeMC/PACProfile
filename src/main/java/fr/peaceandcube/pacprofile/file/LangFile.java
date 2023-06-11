package fr.peaceandcube.pacprofile.file;

import org.bukkit.plugin.Plugin;

import java.util.LinkedHashMap;
import java.util.Map;

public class LangFile extends YamlFile {
    private static final Map<String, String> DEFAULT_TRANSLATIONS = new LinkedHashMap<>();

    static {
        DEFAULT_TRANSLATIONS.put("command_profile_sender_not_player", "Command sender must be a player!");
        DEFAULT_TRANSLATIONS.put("command_profile_player_not_found", "Target player was not found.");
        DEFAULT_TRANSLATIONS.put("command_reload_success", "PACProfile was successfully reloaded");
        DEFAULT_TRANSLATIONS.put("not_defined", "Not defined");
        DEFAULT_TRANSLATIONS.put("invalid", "Invalid");
        DEFAULT_TRANSLATIONS.put("confirmation_title", "Confirmation");
        DEFAULT_TRANSLATIONS.put("confirmation_yes", "Confirm");
        DEFAULT_TRANSLATIONS.put("confirmation_no", "Cancel");
        DEFAULT_TRANSLATIONS.put("profile", "Profile of %s");
        DEFAULT_TRANSLATIONS.put("profile_rank", "Rank: ");
        DEFAULT_TRANSLATIONS.put("profile_rank_expiration", "Expiration: ");
        DEFAULT_TRANSLATIONS.put("profile_nickname", "Nickname: ");
        DEFAULT_TRANSLATIONS.put("profile_birthday", "Birthday: ");
        DEFAULT_TRANSLATIONS.put("profile_join_date", "Joined on: ");
        DEFAULT_TRANSLATIONS.put("statistics", "Statistics");
        DEFAULT_TRANSLATIONS.put("statistics_base", "Base statistics");
        DEFAULT_TRANSLATIONS.put("statistics_current", "Current statistics");
        DEFAULT_TRANSLATIONS.put("statistics_health", "❤ Health: ");
        DEFAULT_TRANSLATIONS.put("statistics_max_health", "❤ Max health: ");
        DEFAULT_TRANSLATIONS.put("statistics_armor", "\uD83D\uDEE1 Armor: ");
        DEFAULT_TRANSLATIONS.put("statistics_armor_toughness", "\uD83D\uDEE1 Armor toughness: ");
        DEFAULT_TRANSLATIONS.put("statistics_knockback_resistance", "❄ Knockback resistance: ");
        DEFAULT_TRANSLATIONS.put("statistics_speed", "✈ Speed: ");
        DEFAULT_TRANSLATIONS.put("statistics_attack_damage", "\uD83D\uDDE1 Attack damage: ");
        DEFAULT_TRANSLATIONS.put("statistics_attack_speed", "\uD83D\uDDE1 Attack speed: ");
        DEFAULT_TRANSLATIONS.put("statistics_luck", "☘ Luck: ");
        DEFAULT_TRANSLATIONS.put("statistics_click_base", "⇒ Click to show base statistics");
        DEFAULT_TRANSLATIONS.put("statistics_click_current", "⇒ Click to show current statistics");
        DEFAULT_TRANSLATIONS.put("settings", "Settings");
        DEFAULT_TRANSLATIONS.put("settings_click", "⇒ Click to edit settings");
        DEFAULT_TRANSLATIONS.put("settings_title", "Settings");
        DEFAULT_TRANSLATIONS.put("settings_enabled", "Enabled");
        DEFAULT_TRANSLATIONS.put("settings_disabled", "Disabled");
        DEFAULT_TRANSLATIONS.put("settings_msgtoggle", "Private messages");
        DEFAULT_TRANSLATIONS.put("settings_msgtoggle_click", "⇒ Click to toggle private messages");
        DEFAULT_TRANSLATIONS.put("settings_togglemsgsound", "Private messages sound");
        DEFAULT_TRANSLATIONS.put("settings_togglemsgsound_click", "⇒ Click to toggle private messages sound");
        DEFAULT_TRANSLATIONS.put("coins", "Coins");
        DEFAULT_TRANSLATIONS.put("coins_total", "Total: ");
        DEFAULT_TRANSLATIONS.put("coins_click", "⇒ Click to see more about coins");
        DEFAULT_TRANSLATIONS.put("head_tickets", "Head Tickets");
        DEFAULT_TRANSLATIONS.put("head_tickets_total", "Total: ");
        DEFAULT_TRANSLATIONS.put("head_tickets_click", "⇒ Click to see more about head tickets");
        DEFAULT_TRANSLATIONS.put("mails", "Mails");
        DEFAULT_TRANSLATIONS.put("mails_total", "Total: ");
        DEFAULT_TRANSLATIONS.put("mails_unread", "Unread: ");
        DEFAULT_TRANSLATIONS.put("mails_click", "⇒ Click to see mails");
        DEFAULT_TRANSLATIONS.put("homes", "Homes");
        DEFAULT_TRANSLATIONS.put("homes_total", "Total: ");
        DEFAULT_TRANSLATIONS.put("homes_remaining", "Remaining: ");
        DEFAULT_TRANSLATIONS.put("homes_max_available", "Maximum available: ");
        DEFAULT_TRANSLATIONS.put("homes_click", "⇒ Click to see homes");
        DEFAULT_TRANSLATIONS.put("homes_title", "Homes of %s (%2$d/%3$d)");
        DEFAULT_TRANSLATIONS.put("homes_order", "Order homes");
        DEFAULT_TRANSLATIONS.put("home_world", "World: ");
        DEFAULT_TRANSLATIONS.put("home_x", "X: ");
        DEFAULT_TRANSLATIONS.put("home_y", "Y: ");
        DEFAULT_TRANSLATIONS.put("home_z", "Z: ");
        DEFAULT_TRANSLATIONS.put("home_click_left", "⇒ Left click to go to this home");
        DEFAULT_TRANSLATIONS.put("home_click_right", "⇒ Right click to delete this home");
        DEFAULT_TRANSLATIONS.put("home_notes", "Notes");
        DEFAULT_TRANSLATIONS.put("home_notes_click", "⇒ Click to edit notes");
        DEFAULT_TRANSLATIONS.put("home_notes_title", "Edit notes");
        DEFAULT_TRANSLATIONS.put("home_color", "Color");
        DEFAULT_TRANSLATIONS.put("home_color_click", "⇒ Click to change color");
        DEFAULT_TRANSLATIONS.put("home_color_title", "Choose a color");
        DEFAULT_TRANSLATIONS.put("claims", "Claims");
        DEFAULT_TRANSLATIONS.put("claims_total", "Total: ");
        DEFAULT_TRANSLATIONS.put("claims_cb_used", "Used claim blocks: ");
        DEFAULT_TRANSLATIONS.put("claims_cb_remaining", "Remaining claim blocks: ");
        DEFAULT_TRANSLATIONS.put("claims_cb_accrued", "Accrued claim blocks: ");
        DEFAULT_TRANSLATIONS.put("claims_cb_bonus", "Bonus claim blocks: ");
        DEFAULT_TRANSLATIONS.put("claims_cb_total", "Total claim blocks: ");
        DEFAULT_TRANSLATIONS.put("claims_cb_per_hour", "Accrued per hour: ");
        DEFAULT_TRANSLATIONS.put("claims_click", "⇒ Click to see claims");
        DEFAULT_TRANSLATIONS.put("claims_title", "Claims of %s (%2$d/%3$d)");
        DEFAULT_TRANSLATIONS.put("claims_order", "Order claims");
        DEFAULT_TRANSLATIONS.put("claim_world", "World: ");
        DEFAULT_TRANSLATIONS.put("claim_greater_corner", "Greater corner: ");
        DEFAULT_TRANSLATIONS.put("claim_lesser_corner", "Lesser corner: ");
        DEFAULT_TRANSLATIONS.put("claim_width", "Width: ");
        DEFAULT_TRANSLATIONS.put("claim_length", "Length: ");
        DEFAULT_TRANSLATIONS.put("claim_area", "Area: ");
        DEFAULT_TRANSLATIONS.put("claim_permissions", "Permissions");
        DEFAULT_TRANSLATIONS.put("claim_permissions_builders", "Builders: ");
        DEFAULT_TRANSLATIONS.put("claim_permissions_containers", "Containers: ");
        DEFAULT_TRANSLATIONS.put("claim_permissions_accessors", "Accessors: ");
        DEFAULT_TRANSLATIONS.put("claim_permissions_managers", "Managers: ");
        DEFAULT_TRANSLATIONS.put("claim_name", "Name");
        DEFAULT_TRANSLATIONS.put("claim_name_click", "⇒ Click to edit name");
        DEFAULT_TRANSLATIONS.put("claim_name_title", "Edit name");
        DEFAULT_TRANSLATIONS.put("online_players", "Online Players");
        DEFAULT_TRANSLATIONS.put("online_players_count", "Total: ");
        DEFAULT_TRANSLATIONS.put("online_players_click", "⇒ Click to see online players");
        DEFAULT_TRANSLATIONS.put("online_players_title", "Online Players (%2$d/%3$d)");
        DEFAULT_TRANSLATIONS.put("online_player_trust_count_1", "Trusted in ");
        DEFAULT_TRANSLATIONS.put("online_player_trust_count_2", " claim(s)");
        DEFAULT_TRANSLATIONS.put("online_player_click", "⇒ Click to ask for a teleportation");
        DEFAULT_TRANSLATIONS.put("online_player_notes_click", "⇒ Click to edit notes");
        DEFAULT_TRANSLATIONS.put("online_player_notes_title", "Edit notes");
        DEFAULT_TRANSLATIONS.put("warps", "Warps");
        DEFAULT_TRANSLATIONS.put("warps_click", "⇒ Click to see warps");
        DEFAULT_TRANSLATIONS.put("warps_title", "Warps (%2$d/%3$d)");
        DEFAULT_TRANSLATIONS.put("warps_order", "Order warps");
        DEFAULT_TRANSLATIONS.put("warp_command", "Command: ");
        DEFAULT_TRANSLATIONS.put("warp_category", "Category: ");
        DEFAULT_TRANSLATIONS.put("warp_click", "⇒ Click to go to this warp");
        DEFAULT_TRANSLATIONS.put("rules", "Rules");
        DEFAULT_TRANSLATIONS.put("rules_click", "⇒ Click to see the rules");
        DEFAULT_TRANSLATIONS.put("links", "Server Links");
        DEFAULT_TRANSLATIONS.put("links_click", "⇒ Click to see the links");
        DEFAULT_TRANSLATIONS.put("dynmap", "Dynmap");
        DEFAULT_TRANSLATIONS.put("dynmap_click", "⇒ Click to open the dynmap");
        DEFAULT_TRANSLATIONS.put("exit", "Exit");
        DEFAULT_TRANSLATIONS.put("page_previous", "Previous Page");
        DEFAULT_TRANSLATIONS.put("page_next", "Next Page");
        DEFAULT_TRANSLATIONS.put("color_white", "White");
        DEFAULT_TRANSLATIONS.put("color_orange", "Orange");
        DEFAULT_TRANSLATIONS.put("color_magenta", "Magenta");
        DEFAULT_TRANSLATIONS.put("color_light_blue", "Light Blue");
        DEFAULT_TRANSLATIONS.put("color_yellow", "Yellow");
        DEFAULT_TRANSLATIONS.put("color_lime", "Lime");
        DEFAULT_TRANSLATIONS.put("color_pink", "Pink");
        DEFAULT_TRANSLATIONS.put("color_gray", "Gray");
        DEFAULT_TRANSLATIONS.put("color_light_gray", "Light Gray");
        DEFAULT_TRANSLATIONS.put("color_cyan", "Cyan");
        DEFAULT_TRANSLATIONS.put("color_purple", "Purple");
        DEFAULT_TRANSLATIONS.put("color_blue", "Blue");
        DEFAULT_TRANSLATIONS.put("color_brown", "Brown");
        DEFAULT_TRANSLATIONS.put("color_green", "Green");
        DEFAULT_TRANSLATIONS.put("color_red", "Red");
        DEFAULT_TRANSLATIONS.put("color_black", "Black");
        DEFAULT_TRANSLATIONS.put("order_by", "Order by: ");
        DEFAULT_TRANSLATIONS.put("order_default", "Default");
        DEFAULT_TRANSLATIONS.put("order_name_az", "Name (A-Z)");
        DEFAULT_TRANSLATIONS.put("order_name_za", "Name (Z-A)");
        DEFAULT_TRANSLATIONS.put("order_area_asc", "Area (Ascending)");
        DEFAULT_TRANSLATIONS.put("order_area_desc", "Area (Descending)");
        DEFAULT_TRANSLATIONS.put("order_category_az", "Category (A-Z)");
        DEFAULT_TRANSLATIONS.put("order_category_za", "Category (Z-A)");
        DEFAULT_TRANSLATIONS.put("order_click", "⇒ Click to toggle order");
    }

    public LangFile(String name, Plugin plugin) {
        super(name, plugin);
        this.init();
    }

    private void init() {
        DEFAULT_TRANSLATIONS.forEach((k, v) -> {
            if (!this.config.isSet(k) || this.config.getString(k).isBlank()) {
                this.config.set(k, v);
            }
        });
        this.save();
    }

    public String translate(String key) {
        return this.config.getString(key);
    }
}
