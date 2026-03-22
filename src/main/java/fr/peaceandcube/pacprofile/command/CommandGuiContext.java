package fr.peaceandcube.pacprofile.command;

import fr.peaceandcube.pacprofile.gui.GuiContext;
import fr.peaceandcube.pacprofile.order.OrderSet;
import org.bukkit.entity.Player;

public record CommandGuiContext(Player viewer, Player player) implements GuiContext {

    @Override
    public int page() {
        return 0;
    }

    @Override
    public int maxPages() {
        return 0;
    }

    @Override
    public OrderSet orderSet() {
        return null;
    }

    @Override
    public void fillInventory() {
    }

    @Override
    public void close() {
    }

    @Override
    public void dispatchCommand(String command) {
        if (command != null && !command.isBlank()) {
            viewer.chat("/" + command);
        }
    }
}
