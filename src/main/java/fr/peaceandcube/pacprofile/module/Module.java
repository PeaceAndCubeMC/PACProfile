package fr.peaceandcube.pacprofile.module;

import fr.peaceandcube.pacprofile.config.ConfigOption;
import fr.peaceandcube.pacprofile.gui.item.GuiItem;
import fr.peaceandcube.pacprofile.lang.TranslationManager;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class Module {

    private final String name;
    private final boolean openable;
    private final Function<Player, GuiItem> guiItem;
    protected final Map<String, ConfigOption> configOptions;
    protected final Map<String, String> defaultTranslations;

    protected Module(String name) {
        this(name, false);
    }

    protected Module(String name, boolean openable) {
        this.name = name;
        this.openable = openable;
        this.guiItem = createGuiItem();
        this.configOptions = new LinkedHashMap<>();
        this.defaultTranslations = new LinkedHashMap<>();

        registerConfigOptions();
        registerDefaultTranslations();
    }

    public final String name() {
        return name;
    }

    public final boolean isOpenable() {
        return openable;
    }

    public final GuiItem guiItem(Player player) {
        return guiItem.apply(player);
    }

    public final Map<String, ConfigOption> configOptions() {
        return configOptions;
    }

    public final Map<String, String> defaultTranslations() {
        return defaultTranslations;
    }

    public boolean isEnabled() {
        return true;
    }

    protected abstract Function<Player, GuiItem> createGuiItem();

    protected abstract void registerConfigOptions();

    protected abstract void registerDefaultTranslations();

    public final String translate(String key) {
        return TranslationManager.translate(key);
    }
}
