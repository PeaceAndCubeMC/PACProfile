package fr.peaceandcube.pacprofile.gui;

import org.bukkit.entity.Player;

public interface GuiContext {

    Player viewer();

    Player player();

    void fillInventory();

    void close();

    void dispatchCommand(String command);
}
