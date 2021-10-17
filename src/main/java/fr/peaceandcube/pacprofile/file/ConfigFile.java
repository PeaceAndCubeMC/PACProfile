package fr.peaceandcube.pacprofile.file;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ConfigFile extends YamlFile {
    private static final List<ConfigEntry> DEFAULT_CONFIGS = new ArrayList<>();

    static {
        DEFAULT_CONFIGS.add(new ConfigEntry("date_format", "yyyy-MM-dd HH:mm:ss", null));
        DEFAULT_CONFIGS.add(new ConfigEntry("default_home_color", "red", null));
        DEFAULT_CONFIGS.add(new ConfigEntry("head_tickets_scoreboard", "", null));
        DEFAULT_CONFIGS.add(new ConfigEntry("coins", "", "commands_on_click"));
        DEFAULT_CONFIGS.add(new ConfigEntry("mails", "mail read", "commands_on_click"));
        DEFAULT_CONFIGS.add(new ConfigEntry("rules", "", "commands_on_click"));
        DEFAULT_CONFIGS.add(new ConfigEntry("links", "", "commands_on_click"));
    }

    public ConfigFile(String name, Plugin plugin) {
        super(name, plugin);
        this.initConfig();
    }

    private void initConfig() {
        DEFAULT_CONFIGS.forEach(entry -> {
            if (entry.section() != null) {
                ConfigurationSection section;
                if (this.config.getConfigurationSection(entry.section()) != null) {
                    section = this.config.getConfigurationSection(entry.section());
                } else {
                    section = this.config.createSection(entry.section());
                }

                if (!section.isSet(entry.key())) {
                    section.set(entry.key(), entry.defaultValue());
                }
            } else {
                if (!this.config.isSet(entry.key())) {
                    this.config.set(entry.key(), entry.defaultValue());
                }
            }
        });
        this.save();
    }

    public String getDateFormat() {
        return this.config.getString("date_format");
    }

    public String getDefaultHomeColor() {
        return this.config.getString("default_home_color");
    }

    public String getHeadTicketsScoreboard() {
        return this.config.getString("head_tickets_scoreboard");
    }

    public String getCommandOnClickCoins() {
        return this.config.getConfigurationSection("commands_on_click").getString("coins");
    }

    public String getCommandOnClickMails() {
        return this.config.getConfigurationSection("commands_on_click").getString("mails");
    }

    public String getCommandOnClickRules() {
        return this.config.getConfigurationSection("commands_on_click").getString("rules");
    }

    public String getCommandOnClickLinks() {
        return this.config.getConfigurationSection("commands_on_click").getString("links");
    }
}
