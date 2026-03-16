package fr.peaceandcube.pacprofile.module;

import fr.peaceandcube.pacprofile.config.ConfigOption;
import fr.peaceandcube.pacprofile.item.GuiItem;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class Module {

    private final String name;
    private final Function<Player, GuiItem> guiItem;
    protected final Map<String, ConfigOption> configOptions;
    protected final Map<String, String> defaultTranslations;

    protected Module(String name) {
        this.name = name;
        this.guiItem = createGuiItem();
        this.configOptions = new LinkedHashMap<>();
        this.defaultTranslations = new LinkedHashMap<>();

        registerConfigOptions();
        registerDefaultTranslations();
    }

    public String name() {
        return name;
    }

    public GuiItem guiItem(Player player) {
        return guiItem.apply(player);
    }

    public Map<String, ConfigOption> configOptions() {
        return configOptions;
    }

    public Map<String, String> defaultTranslations() {
        return defaultTranslations;
    }

    public boolean isEnabled() {
        return true;
    }

    protected abstract Function<Player, GuiItem> createGuiItem();

    protected abstract void registerConfigOptions();

    protected abstract void registerDefaultTranslations();
}
