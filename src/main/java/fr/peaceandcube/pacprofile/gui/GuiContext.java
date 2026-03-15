package fr.peaceandcube.pacprofile.gui;

import fr.peaceandcube.pacprofile.order.OrderSet;
import org.bukkit.entity.Player;

public interface GuiContext {

    Player viewer();

    Player player();

    int page();

    int maxPages();

    OrderSet orderSet();

    void fillInventory();

    void close();

    void dispatchCommand(String command);
}
