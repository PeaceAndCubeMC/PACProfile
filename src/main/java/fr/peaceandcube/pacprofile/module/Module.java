package fr.peaceandcube.pacprofile.module;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.file.ConfigFile;
import fr.peaceandcube.pacprofile.item.GuiItem;
import org.bukkit.entity.Player;

import java.util.function.Function;

public abstract class Module {

    private final String name;
    private final Function<Player, GuiItem> guiItem;
    protected final ConfigFile config;

    protected Module(String name) {
        this.name = name;
        this.guiItem = createGuiItem();
        this.config = PACProfile.getInstance().config;
    }

    public String name() {
        return name;
    }

    public GuiItem guiItem(Player player) {
        return guiItem.apply(player);
    }

    public boolean isEnabled() {
        return true;
    }

    protected abstract Function<Player, GuiItem> createGuiItem();
}
