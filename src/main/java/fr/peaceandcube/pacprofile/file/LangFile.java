package fr.peaceandcube.pacprofile.file;

import fr.peaceandcube.pacprofile.lang.GlobalTranslations;
import fr.peaceandcube.pacprofile.module.Module;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class LangFile extends YamlFile {

    public LangFile(String name, Plugin plugin, List<Module> modules) {
        super(name, plugin, modules);
    }

    @Override
    protected void init() {
        GlobalTranslations.defaultTranslations().forEach(this::addToFile);
        for (Module module : modules) {
            module.defaultTranslations().forEach((key, value) ->
                    addToFile("%s.%s".formatted(module.name(), key), value)
            );
        }
        this.save();
    }

    private void addToFile(String key, Object value) {
        if (!this.config.isSet(key) || this.config.getString(key, "").isBlank()) {
            this.config.set(key, value);
        }
    }

    public String translate(String key) {
        return this.config.getString(key, key);
    }
}
