package fr.peaceandcube.pacprofile.module;

import fr.peaceandcube.pacprofile.item.GuiItem;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class Module {

    private final String name;
    private final Function<Player, GuiItem> guiItem;
    protected final Map<String, String> defaultTranslations;

    protected Module(String name) {
        this.name = name;
        this.guiItem = createGuiItem();
        this.defaultTranslations = new LinkedHashMap<>();

        registerDefaultTranslations();
    }

    public String name() {
        return name;
    }

    public GuiItem guiItem(Player player) {
        return guiItem.apply(player);
    }

    public Map<String, String> getDefaultTranslations() {
        return defaultTranslations;
    }

    public boolean isEnabled() {
        return true;
    }

    protected abstract Function<Player, GuiItem> createGuiItem();

    protected abstract void registerDefaultTranslations();
}
