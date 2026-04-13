package fr.peaceandcube.pacprofile.lang;

import java.util.LinkedHashMap;
import java.util.Map;

public class GlobalTranslations {

    public static Map<String, String> defaultTranslations() {
        Map<String, String> translations = new LinkedHashMap<>();
        translations.put("command_profile_sender_not_player", "Command sender must be a player!");
        translations.put("command_profile_module_not_found", "Module was not found.");
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
        translations.put("color.white", "White");
        translations.put("color.light_gray", "Light Gray");
        translations.put("color.gray", "Gray");
        translations.put("color.black", "Black");
        translations.put("color.brown", "Brown");
        translations.put("color.red", "Red");
        translations.put("color.orange", "Orange");
        translations.put("color.yellow", "Yellow");
        translations.put("color.lime", "Lime");
        translations.put("color.green", "Green");
        translations.put("color.cyan", "Cyan");
        translations.put("color.light_blue", "Light Blue");
        translations.put("color.blue", "Blue");
        translations.put("color.purple", "Purple");
        translations.put("color.magenta", "Magenta");
        translations.put("color.pink", "Pink");
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
