package fr.peaceandcube.pacprofile.gui;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.item.GuiItem;
import fr.peaceandcube.pacprofile.text.NameComponents;
import fr.peaceandcube.pacprofile.util.Messages;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ConfirmationGui extends UnmodifiableGui {
    private final UnmodifiableGui previousGui;
    private final Action action;

    public ConfirmationGui(Player viewer, Player player, UnmodifiableGui previousGui, Action action) {
        super(1, Component.text(Messages.CONFIRMATION_TITLE), viewer, player);
        this.previousGui = previousGui;
        this.action = action;
        this.fillInventory();
        Bukkit.getPluginManager().registerEvents(this, PACProfile.getInstance());
    }

    @Override
    protected void fillInventory() {
        this.items.clear();

        this.setItem(GuiItem.builder().slot(3).material(Material.GREEN_TERRACOTTA)
                .customModelData(3006)
                .name(NameComponents.CONFIRMATION_YES)
                .onLeftClick(this.action::onConfirm)
                .build());

        this.setItem(GuiItem.builder().slot(5).material(Material.RED_TERRACOTTA)
                .customModelData(3006)
                .name(NameComponents.CONFIRMATION_NO)
                .onLeftClick(this.previousGui::open)
                .build());
    }

    public interface Action {
        void onConfirm();
    }
}
