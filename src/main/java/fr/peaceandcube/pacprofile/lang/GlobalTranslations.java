package fr.peaceandcube.pacprofile.lang;

import java.util.LinkedHashMap;
import java.util.Map;

public class GlobalTranslations {

    public static Map<String, String> defaultTranslations() {
        Map<String, String> translations = new LinkedHashMap<>();
        translations.put("command_profile_sender_not_player", "Command sender must be a player!");
        translations.put("command_profile_player_not_found", "Target player was not found.");
        translations.put("command_reload_success", "PACProfile was successfully reloaded");
        translations.put("not_defined", "Not defined");
        translations.put("invalid", "Invalid");
        translations.put("confirmation_title", "Confirmation");
        translations.put("confirmation_yes", "Confirm");
        translations.put("confirmation_no", "Cancel");
        translations.put("profile", "Profile of %s");
        translations.put("exit", "Exit");
        translations.put("page_previous", "Previous Page");
        translations.put("page_next", "Next Page");
        translations.put("color_white", "White");
        translations.put("color_light_gray", "Light Gray");
        translations.put("color_gray", "Gray");
        translations.put("color_black", "Black");
        translations.put("color_brown", "Brown");
        translations.put("color_red", "Red");
        translations.put("color_orange", "Orange");
        translations.put("color_yellow", "Yellow");
        translations.put("color_lime", "Lime");
        translations.put("color_green", "Green");
        translations.put("color_cyan", "Cyan");
        translations.put("color_light_blue", "Light Blue");
        translations.put("color_blue", "Blue");
        translations.put("color_purple", "Purple");
        translations.put("color_magenta", "Magenta");
        translations.put("color_pink", "Pink");
        translations.put("order_by", "Order by: ");
        translations.put("order_default", "Default");
        translations.put("order_name_az", "Name (A-Z)");
        translations.put("order_name_za", "Name (Z-A)");
        translations.put("order_area_asc", "Area (Ascending)");
        translations.put("order_area_desc", "Area (Descending)");
        translations.put("order_category_az", "Category (A-Z)");
        translations.put("order_category_za", "Category (Z-A)");
        translations.put("order_color", "Color");
        translations.put("order_click", "⇒ Click to toggle order");
        return translations;
    }
}
