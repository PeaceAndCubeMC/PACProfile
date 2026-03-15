package fr.peaceandcube.pacprofile.module;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.file.ConfigFile;
import fr.peaceandcube.pacprofile.item.GuiItem;

public abstract class Module {

    private final String name;
    private final GuiItem guiItem;
    protected final ConfigFile config;

    protected Module(String name) {
        this.name = name;
        this.guiItem = createGuiItem();
        this.config = PACProfile.getInstance().config;
    }

    public String name() {
        return name;
    }

    public GuiItem guiItem() {
        return guiItem;
    }

    public boolean isEnabled() {
        return true;
    }

    protected abstract GuiItem createGuiItem();
}
