package fr.peaceandcube.pacprofile.file;

import fr.peaceandcube.pacprofile.PACProfile;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.UUID;

public class PlayerDataFile extends YamlFile {
    private static final String NOTES = "notes";
    private static final String COLOR = "color";

    public PlayerDataFile(String name, Plugin plugin) {
        super(name, plugin);
    }

    public String getHomeNotes(UUID player, String home) {
        ConfigurationSection topSection = this.config.getConfigurationSection(player.toString());

        if (topSection != null) {
            ConfigurationSection section = topSection.getConfigurationSection(home);

            if (section != null) {
                return section.getString(NOTES, "");
            }
        }

        return "";
    }

    public void setHomeNotes(UUID player, String home, String notes) {
        ConfigurationSection topSection;
        if (this.config.getConfigurationSection(player.toString()) != null) {
            topSection = this.config.getConfigurationSection(player.toString());
        } else {
            topSection = this.config.createSection(player.toString());
        }

        ConfigurationSection section;
        if (topSection.getConfigurationSection(home) != null) {
            section = topSection.getConfigurationSection(home);
        } else {
            section = topSection.createSection(home);
        }

        section.set(NOTES, notes);
        this.save();
    }

    public String getHomeColor(UUID player, String home) {
        ConfigurationSection topSection = this.config.getConfigurationSection(player.toString());

        if (topSection != null) {
            ConfigurationSection section = topSection.getConfigurationSection(home);

            if (section != null) {
                return section.getString(COLOR, PACProfile.getInstance().config.getDefaultHomeColor());
            }
        }

        return PACProfile.getInstance().config.getDefaultHomeColor();
    }

    public void setHomeColor(UUID player, String home, String color) {
        ConfigurationSection topSection;
        if (this.config.getConfigurationSection(player.toString()) != null) {
            topSection = this.config.getConfigurationSection(player.toString());
        } else {
            topSection = this.config.createSection(player.toString());
        }

        ConfigurationSection section;
        if (topSection.getConfigurationSection(home) != null) {
            section = topSection.getConfigurationSection(home);
        } else {
            section = topSection.createSection(home);
        }

        section.set(COLOR, color);
        this.save();
    }

    public void removeOutdatedHomes(UUID player, List<String> homes) {
        ConfigurationSection playerSection = this.config.getConfigurationSection(player.toString());

        if (playerSection != null) {
            for (String fileHome : playerSection.getKeys(false)) {
                if (!homes.contains(fileHome)) {
                    playerSection.set(fileHome, null);
                    this.save();
                }
            }
        }
    }
}
