package fr.peaceandcube.pacprofile.file;

import fr.peaceandcube.pacprofile.statistic.Statistic;
import fr.peaceandcube.pacprofile.statistic.Statistics;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ConfigFile extends YamlFile {
    private static final List<ConfigEntry> DEFAULT_CONFIGS = new ArrayList<>();

    static {
        DEFAULT_CONFIGS.add(new ConfigEntry("date_format", "yyyy-MM-dd HH:mm:ss", null));
        DEFAULT_CONFIGS.add(new ConfigEntry("first_time_advancement_name", "", null));
        DEFAULT_CONFIGS.add(new ConfigEntry("default_home_color", "red", null));
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

        // config for homes
        ConfigurationSection homesSection = this.config.getConfigurationSection("homes");
        if (homesSection == null) {
            homesSection = this.config.createSection("homes");
        }

        if (!homesSection.isBoolean("enable_teleportation")) {
            homesSection.set("enable_teleportation", true);
        }
        if (!homesSection.isBoolean("enable_deletion")) {
            homesSection.set("enable_deletion", true);
        }

        // config for statistics
        ConfigurationSection statsSection = this.config.getConfigurationSection("statistics");
        if (statsSection == null) {
            statsSection = this.config.createSection("statistics");
        }
        this.config.setComments("statistics", List.of("Toggles specific statistics"));

        for (Statistic statistic : Statistics.ALL) {
            ConfigurationSection statSection = statsSection.getConfigurationSection(statistic.getName());
            if (statSection == null) {
                statSection = statsSection.createSection(statistic.getName());
            }

            if (!statSection.isSet("enabled")) {
                statSection.set("enabled", true);
            }
        }

        this.save();
    }

    public String getDateFormat() {
        return this.config.getString("date_format");
    }

    public String getFirstTimeAdvancementName() {
        return this.config.getString("first_time_advancement_name");
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

    public boolean isHomeTeleportationEnabled() {
        return this.config.getConfigurationSection("homes").getBoolean("enable_teleportation", true);
    }

    public boolean isHomeDeletionEnabled() {
        return this.config.getConfigurationSection("homes").getBoolean("enable_deletion", true);
    }

    public boolean isStatisticEnabled(String name) {
        return this.config.getConfigurationSection("statistics").getConfigurationSection(name).getBoolean("enabled", true);
    }
}
