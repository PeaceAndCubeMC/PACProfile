package fr.peaceandcube.pacprofile.gui;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.text.NameComponents;
import fr.peaceandcube.pacprofile.util.Messages;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ConfirmationGui extends UnmodifiableGui {
    private final String command;
    private final UnmodifiableGui previousGui;

    public ConfirmationGui(Player viewer, Player player, String command, UnmodifiableGui previousGui) {
        super(1, Component.text(Messages.CONFIRMATION_TITLE), viewer, player);
        this.command = command;
        this.previousGui = previousGui;
        this.fillInventory();
        Bukkit.getPluginManager().registerEvents(this, PACProfile.getInstance());
    }

    @Override
    protected void fillInventory() {
        this.setItem(3, Material.GREEN_TERRACOTTA, 3006, NameComponents.CONFIRMATION_YES);
        this.setItem(5, Material.RED_TERRACOTTA, 3006, NameComponents.CONFIRMATION_NO);
    }

    @Override
    protected void onSlotLeftClick(int slot) {
        switch (slot) {
            // yes
            case 3 -> {
                this.dispatchCommand(this.command);
                this.inv.close();
            }
            // no
            case 5 -> this.previousGui.open();
        }
    }
}
