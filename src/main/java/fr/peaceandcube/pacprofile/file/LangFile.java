package fr.peaceandcube.pacprofile.file;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.module.Module;
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
        DEFAULT_TRANSLATIONS.put("exit", "Exit");
        DEFAULT_TRANSLATIONS.put("page_previous", "Previous Page");
        DEFAULT_TRANSLATIONS.put("page_next", "Next Page");
        DEFAULT_TRANSLATIONS.put("color_white", "White");
        DEFAULT_TRANSLATIONS.put("color_light_gray", "Light Gray");
        DEFAULT_TRANSLATIONS.put("color_gray", "Gray");
        DEFAULT_TRANSLATIONS.put("color_black", "Black");
        DEFAULT_TRANSLATIONS.put("color_brown", "Brown");
        DEFAULT_TRANSLATIONS.put("color_red", "Red");
        DEFAULT_TRANSLATIONS.put("color_orange", "Orange");
        DEFAULT_TRANSLATIONS.put("color_yellow", "Yellow");
        DEFAULT_TRANSLATIONS.put("color_lime", "Lime");
        DEFAULT_TRANSLATIONS.put("color_green", "Green");
        DEFAULT_TRANSLATIONS.put("color_cyan", "Cyan");
        DEFAULT_TRANSLATIONS.put("color_light_blue", "Light Blue");
        DEFAULT_TRANSLATIONS.put("color_blue", "Blue");
        DEFAULT_TRANSLATIONS.put("color_purple", "Purple");
        DEFAULT_TRANSLATIONS.put("color_magenta", "Magenta");
        DEFAULT_TRANSLATIONS.put("color_pink", "Pink");
        DEFAULT_TRANSLATIONS.put("order_by", "Order by: ");
        DEFAULT_TRANSLATIONS.put("order_default", "Default");
        DEFAULT_TRANSLATIONS.put("order_name_az", "Name (A-Z)");
        DEFAULT_TRANSLATIONS.put("order_name_za", "Name (Z-A)");
        DEFAULT_TRANSLATIONS.put("order_area_asc", "Area (Ascending)");
        DEFAULT_TRANSLATIONS.put("order_area_desc", "Area (Descending)");
        DEFAULT_TRANSLATIONS.put("order_category_az", "Category (A-Z)");
        DEFAULT_TRANSLATIONS.put("order_category_za", "Category (Z-A)");
        DEFAULT_TRANSLATIONS.put("order_color", "Color");
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
        for (Module module : PACProfile.getInstance().getModules()) {
            module.getDefaultTranslations().forEach((k, v) -> {
                if (!this.config.isSet(k) || this.config.getString(k).isBlank()) {
                    this.config.set(k, v);
                }
            });
        }
        this.save();
    }

    public String translate(String key) {
        return this.config.getString(key);
    }
}
