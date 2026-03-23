package fr.peaceandcube.pacprofile.file;

import fr.peaceandcube.pacprofile.logging.Logger;
import fr.peaceandcube.pacprofile.module.Module;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class YamlFile {
    private final String name;
    protected final List<Module> modules;
    protected final File file;
    protected YamlConfiguration config;

    public YamlFile(String name, Plugin plugin, List<Module> modules) {
        this.name = name;
        this.modules = modules;
        this.file = new File(plugin.getDataFolder(), name);
        this.config = YamlConfiguration.loadConfiguration(this.file);
        init();
    }

    public void save() {
        saveToDisk();
    }

    protected abstract void init();

    public void reload() {
        this.config = YamlConfiguration.loadConfiguration(this.file);
        init();
        saveToDisk();
    }

    private void saveToDisk() {
        if (this.config != null && this.file != null) {
            try {
                this.config.save(this.file);
            } catch (IOException e) {
                Logger.error("Unable to save %s to disk".formatted(this.name));
            }
        }
    }
}
