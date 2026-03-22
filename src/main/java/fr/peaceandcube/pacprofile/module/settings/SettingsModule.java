package fr.peaceandcube.pacprofile.module.settings;

import fr.peaceandcube.pacprofile.gui.item.GuiItem;
import fr.peaceandcube.pacprofile.gui.item.LoreProvider;
import fr.peaceandcube.pacprofile.module.Module;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.function.Function;

public class SettingsModule extends Module {

    public SettingsModule() {
        super("settings", true);
    }

    @Override
    protected Function<Player, GuiItem> createGuiItem() {
        return player -> GuiItem.builder().slot(17).material(Material.COMPARATOR)
                .customModelData(3004)
                .name(translate("settings"), 0x555555)
                .lore(Component.empty(), LoreProvider.line(translate("settings_click")))
                .onLeftClick(context -> new SettingsGui(this, context.viewer(), context.player()).open())
                .build();
    }

    @Override
    protected void registerConfigOptions() {
    }

    @Override
    protected void registerDefaultTranslations() {
        defaultTranslations.put("settings", "Settings");
        defaultTranslations.put("settings_click", "⇒ Click to edit settings");
        defaultTranslations.put("settings_title", "Settings");
        defaultTranslations.put("settings_enabled", "Enabled");
        defaultTranslations.put("settings_disabled", "Disabled");
        defaultTranslations.put("settings_msgtoggle", "Private messages");
        defaultTranslations.put("settings_msgtoggle_click", "⇒ Click to toggle private messages");
        defaultTranslations.put("settings_togglemsgsound", "Private messages sound");
        defaultTranslations.put("settings_togglemsgsound_click", "⇒ Click to toggle private messages sound");
        defaultTranslations.put("settings_ptime", "Player time");
        defaultTranslations.put("settings_ptime_sunrise", "Sunrise (5 AM)");
        defaultTranslations.put("settings_ptime_day", "Day (6 AM)");
        defaultTranslations.put("settings_ptime_morning", "Morning (7 AM)");
        defaultTranslations.put("settings_ptime_noon", "Noon (12 PM)");
        defaultTranslations.put("settings_ptime_afternoon", "Afternoon (3 PM)");
        defaultTranslations.put("settings_ptime_sunset", "Sunset (6 PM)");
        defaultTranslations.put("settings_ptime_night", "Night (8 PM)");
        defaultTranslations.put("settings_ptime_midnight", "Midnight (12 AM)");
        defaultTranslations.put("settings_ptime_click_left", "⇒ Left click to change player time");
        defaultTranslations.put("settings_ptime_click_right", "⇒ Right click to reset player time");
        defaultTranslations.put("settings_pweather", "Player weather");
        defaultTranslations.put("settings_pweather_sun", "Sun");
        defaultTranslations.put("settings_pweather_storm", "Storm");
        defaultTranslations.put("settings_pweather_click_left", "⇒ Left click to change player weather");
        defaultTranslations.put("settings_pweather_click_right", "⇒ Right click to reset player weather");
    }
}
