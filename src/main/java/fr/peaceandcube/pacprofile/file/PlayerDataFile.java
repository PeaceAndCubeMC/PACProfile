package fr.peaceandcube.pacprofile.file;

import fr.peaceandcube.pacprofile.PACProfile;
import me.ryanhamshire.GriefPrevention.Claim;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

public class PlayerDataFile extends YamlFile {
    private static final String HOMES = "homes";
    private static final String CLAIMS = "claims";
    private static final String PLAYERS = "players";
    private static final String NOTES = "notes";
    private static final String NAME = "name";
    private static final String COLOR = "color";

    public PlayerDataFile(String name, Plugin plugin) {
        super(name, plugin);
    }

    public String getHomeNotes(UUID player, String home) {
        return this.getNotes(player, HOMES, home, NOTES);
    }

    public String getClaimName(UUID player, String claimId) {
        return this.getNotes(player, CLAIMS, claimId, NAME);
    }

    public String getPlayerNotes(UUID player, String target) {
        return this.getNotes(player, PLAYERS, target, NOTES);
    }

    private String getNotes(UUID player, String sectionName, String subsectionName, String key) {
        ConfigurationSection playerSection = this.config.getConfigurationSection(player.toString());
        if (playerSection != null) {
            ConfigurationSection section = playerSection.getConfigurationSection(sectionName);
            if (section != null) {
                ConfigurationSection subsection = section.getConfigurationSection(subsectionName);
                if (subsection != null) {
                    return subsection.getString(key, "");
                }
            }
        }
        return "";
    }

    public void setHomeNotes(UUID player, String home, String notes) {
        this.setNotes(player, HOMES, home, notes, NOTES);
    }

    public void removeHomeNotes(UUID player, String home) {
        this.removeNotes(player, HOMES, home);
    }

    public void setClaimName(UUID player, String claimId, String notes) {
        this.setNotes(player, CLAIMS, claimId, notes, NAME);
    }

    public void setPlayerNotes(UUID player, String target, String notes) {
        this.setNotes(player, PLAYERS, target, notes, NOTES);
    }

    public void removePlayerNotes(UUID player, String target) {
        this.removeNotes(player, PLAYERS, target);
    }

    private void setNotes(UUID player, String sectionName, String subsectionName, String notes, String key) {
        ConfigurationSection playerSection;
        if (this.config.getConfigurationSection(player.toString()) != null) {
            playerSection = this.config.getConfigurationSection(player.toString());
        } else {
            playerSection = this.config.createSection(player.toString());
        }

        ConfigurationSection section;
        if (playerSection.getConfigurationSection(sectionName) != null) {
            section = playerSection.getConfigurationSection(sectionName);
        } else {
            section = playerSection.createSection(sectionName);
        }

        ConfigurationSection subsection;
        if (section.getConfigurationSection(subsectionName) != null) {
            subsection = section.getConfigurationSection(subsectionName);
        } else {
            subsection = section.createSection(subsectionName);
        }

        subsection.set(key, notes);
        this.save();
    }

    private void removeNotes(UUID player, String sectionName, String subsectionName) {
        ConfigurationSection playerSection = this.config.getConfigurationSection(player.toString());
        if (playerSection != null) {
            ConfigurationSection section = playerSection.getConfigurationSection(sectionName);
            if (section != null) {
                ConfigurationSection subsection = section.getConfigurationSection(subsectionName);
                if (subsection != null) {
                    subsection.set(NOTES, null);
                    this.save();
                }
            }
        }
    }

    public String getHomeColor(UUID player, String home) {
        ConfigurationSection playerSection = this.config.getConfigurationSection(player.toString());
        if (playerSection != null) {
            ConfigurationSection section = playerSection.getConfigurationSection(HOMES);
            if (section != null) {
                ConfigurationSection subsection = section.getConfigurationSection(home);
                if (subsection != null) {
                    return subsection.getString(COLOR, PACProfile.getInstance().config.getDefaultHomeColor());
                }
            }
        }
        return PACProfile.getInstance().config.getDefaultHomeColor();
    }

    public void setHomeColor(UUID player, String home, String color) {
        ConfigurationSection playerSection;
        if (this.config.getConfigurationSection(player.toString()) != null) {
            playerSection = this.config.getConfigurationSection(player.toString());
        } else {
            playerSection = this.config.createSection(player.toString());
        }

        ConfigurationSection section;
        if (playerSection.getConfigurationSection(HOMES) != null) {
            section = playerSection.getConfigurationSection(HOMES);
        } else {
            section = playerSection.createSection(HOMES);
        }

        ConfigurationSection subsection;
        if (section.getConfigurationSection(home) != null) {
            subsection = section.getConfigurationSection(home);
        } else {
            subsection = section.createSection(home);
        }

        subsection.set(COLOR, color);
        this.save();
    }

    public void removeOutdatedHomes(UUID player, List<String> homes) {
        ConfigurationSection playerSection = this.config.getConfigurationSection(player.toString());

        if (playerSection != null) {
            ConfigurationSection section = playerSection.getConfigurationSection(HOMES);
            if (section != null) {
                for (String fileHome : section.getKeys(false)) {
                    if (!homes.contains(fileHome)) {
                        section.set(fileHome, null);
                        this.save();
                    }
                }
            }
        }
    }

    public void removeOutdatedClaims(UUID player, Vector<Claim> claims) {
        List<String> claimIds = new ArrayList<>();
        claims.forEach(c -> claimIds.add(c.getID().toString()));
        ConfigurationSection playerSection = this.config.getConfigurationSection(player.toString());

        if (playerSection != null) {
            ConfigurationSection section = playerSection.getConfigurationSection(CLAIMS);
            if (section != null) {
                for (String fileClaim : section.getKeys(false)) {
                    if (!claimIds.contains(fileClaim)) {
                        section.set(fileClaim, null);
                        this.save();
                    }
                }
            }
        }
    }
}
