package fr.peaceandcube.pacprofile.file;

import fr.peaceandcube.pacprofile.config.ConfigOption;
import fr.peaceandcube.pacprofile.config.GlobalConfigOptions;
import fr.peaceandcube.pacprofile.module.Module;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ConfigFile extends YamlFile {
    private static final List<ConfigEntry> DEFAULT_CONFIGS = new ArrayList<>();

    static {
        DEFAULT_CONFIGS.add(new ConfigEntry("head_tickets_scoreboard", "", null));
        DEFAULT_CONFIGS.add(new ConfigEntry("coins", "", "commands_on_click"));
        DEFAULT_CONFIGS.add(new ConfigEntry("head_tickets", "", "commands_on_click"));
        DEFAULT_CONFIGS.add(new ConfigEntry("mails", "mail read", "commands_on_click"));
        DEFAULT_CONFIGS.add(new ConfigEntry("rules", "", "commands_on_click"));
        DEFAULT_CONFIGS.add(new ConfigEntry("links", "", "commands_on_click"));
        DEFAULT_CONFIGS.add(new ConfigEntry("dynmap", "", "commands_on_click"));
        DEFAULT_CONFIGS.add(new ConfigEntry("name", "Spawn", "warps.spawn"));
        DEFAULT_CONFIGS.add(new ConfigEntry("icon", "grass_block", "warps.spawn"));
        DEFAULT_CONFIGS.add(new ConfigEntry("category", "Spawn", "warps.spawn"));
    }

    public ConfigFile(String name, Plugin plugin, List<Module> modules) {
        super(name, plugin);
        this.initConfig(modules);
    }

    private void initConfig(List<Module> modules) {
        createConfigOptions(config, GlobalConfigOptions.configOptions());

        DEFAULT_CONFIGS.forEach(entry -> {
            if (entry.section() != null) {
                ConfigurationSection section = getOrCreateSection(config, entry.section());
                if (!section.isSet(entry.key())) {
                    section.set(entry.key(), entry.defaultValue());
                }
            } else {
                if (!this.config.isSet(entry.key())) {
                    this.config.set(entry.key(), entry.defaultValue());
                }
            }
        });

        for (Module module : modules) {
            if (!module.configOptions().isEmpty()) {
                ConfigurationSection section = getOrCreateSection(config, module.name());
                createConfigOptions(section, module.configOptions());
            }
        }

        this.save();
    }

    private ConfigurationSection getOrCreateSection(ConfigurationSection parent, String name) {
        ConfigurationSection section = parent.getConfigurationSection(name);
        if (section == null) {
            section = parent.createSection(name);
        }
        return section;
    }

    private void createConfigOptions(ConfigurationSection section, Map<String, ConfigOption> options) {
        options.forEach((key, option) -> {
            switch (option.type()) {
                case BOOLEAN -> {
                    if (!section.isBoolean(key)) {
                        section.set(key, option.value());
                    }
                }
                case STRING -> {
                    if (!section.isString(key)) {
                        section.set(key, option.value());
                    }
                }
                case OBJECT -> {
                    ConfigurationSection subSection = getOrCreateSection(section, key);
                    createConfigOptions(subSection, mapOptions(option.value()));
                }
            }
        });
    }

    private Map<String, ConfigOption> mapOptions(Object object) {
        Map<String, ConfigOption> options = new LinkedHashMap<>();
        if (object instanceof Map<?,?> map) {
            map.forEach((k, v) -> {
                if (k instanceof String key && v instanceof ConfigOption value) {
                    options.put(key, value);
                }
            });
        }
        return options;
    }

    public boolean hasDebugLogging() {
        return this.config.getBoolean("debug_logging", false);
    }

    public String getDateFormat() {
        return this.config.getString("date_format");
    }

    public String getFirstTimeAdvancementName() {
        return this.config.getString("first_time_advancement_name");
    }

    public String getHeadTicketsScoreboard() {
        return this.config.getString("head_tickets_scoreboard");
    }

    public String getQuestsScoreboard() {
        return this.config.getConfigurationSection("head_tickets").getString("quests_scoreboard");
    }

    public String getCommandOnClickCoins() {
        return this.config.getConfigurationSection("commands_on_click").getString("coins");
    }

    public String getCommandOnClickHeadTickets() {
        return this.config.getConfigurationSection("commands_on_click").getString("head_tickets");
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

    public String getCommandOnClickDynmap() {
        return this.config.getConfigurationSection("commands_on_click").getString("dynmap");
    }

    public List<WarpEntry> getWarps() {
        List<WarpEntry> warps = new ArrayList<>();
        ConfigurationSection section = this.config.getConfigurationSection("warps");
        if (section != null) {
            for (String key : section.getKeys(false)) {
                ConfigurationSection warpSection = section.getConfigurationSection(key);
                if (warpSection != null) {
                    Material icon = warpSection.getString("icon") != null ? Material.getMaterial(warpSection.getString("icon").toUpperCase()) : Material.ENDER_PEARL;
                    warps.add(new WarpEntry(key, warpSection.getString("name"), icon, warpSection.getString("category")));
                }
            }
        }
        return warps;
    }

    public String getDefaultHomeColor() {
        return this.config.getConfigurationSection("homes").getString("default_color", "red");
    }

    public boolean isHomeTeleportationEnabled() {
        return this.config.getConfigurationSection("homes").getBoolean("enable_teleportation", true);
    }

    public boolean isHomeDeletionEnabled() {
        return this.config.getConfigurationSection("homes").getBoolean("enable_deletion", true);
    }

    public boolean isOnlinePlayerTeleportationEnabled() {
        return this.config.getConfigurationSection("online_players").getBoolean("enable_teleportation", true);
    }

    public boolean isStatisticEnabled(String name) {
        return this.config.getConfigurationSection("statistics").getConfigurationSection(name).getBoolean("enabled", true);
    }
}
