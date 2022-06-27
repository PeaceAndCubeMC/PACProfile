package fr.peaceandcube.pacprofile.gui;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.text.NameComponents;
import fr.peaceandcube.pacprofile.util.Messages;
import fr.peaceandcube.pacutilities.PACUtilities;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class SettingsGui extends UnmodifiableGui {

    public SettingsGui(Player viewer, Player player) {
        super(1, Component.text(Messages.SETTINGS_TITLE), viewer, player);
        this.fillInventory();
        Bukkit.getPluginManager().registerEvents(this, PACProfile.getInstance());
    }

    @Override
    protected void fillInventory() {

        boolean msgtoggleEnabled = !PACProfile.getEssentials().getUser(this.player).isIgnoreMsg();
        Component msgtoggle = msgtoggleEnabled ? LoreComponents.SETTINGS_ENABLED : LoreComponents.SETTINGS_DISABLED;
        this.setItem(3, Material.PAPER, 3050, msgtoggleEnabled, NameComponents.SETTINGS_MSGTOGGLE, List.of(
                Component.empty(),
                msgtoggle,
                Component.empty(),
                LoreComponents.SETTINGS_MSGTOGGLE_CLICK
        ));

        boolean togglemsgsoundEnabled = PACUtilities.playersFile.isMsgSoundEnabled(this.player.getUniqueId().toString());
        Component togglemsgsound = togglemsgsoundEnabled ? LoreComponents.SETTINGS_ENABLED : LoreComponents.SETTINGS_DISABLED;
        this.setItem(4, Material.JUKEBOX, 3050, togglemsgsoundEnabled, NameComponents.SETTINGS_TOGGLEMSGSOUND, List.of(
                Component.empty(),
                togglemsgsound,
                Component.empty(),
                LoreComponents.SETTINGS_TOGGLEMSGSOUND_CLICK
        ));

        this.setItem(0, Material.ARROW, 3002, NameComponents.PAGE_PREVIOUS);
        this.setItem(8, Material.BARRIER, 3002, NameComponents.EXIT);
    }

    @Override
    protected void onSlotLeftClick(int slot) {
        switch (slot) {
            // previous page
            case 0 -> new ProfileGui(this.viewer, this.player).open();
            // msgtoggle
            case 3 -> {
                boolean msgtoggleEnabled = PACProfile.getEssentials().getUser(this.player).isIgnoreMsg();
                PACProfile.getEssentials().getUser(this.player).setIgnoreMsg(!msgtoggleEnabled);
                this.fillInventory();
            }
            // togglemsgsound
            case 4 -> {
                boolean togglemsgsoundEnabled = PACUtilities.playersFile.isMsgSoundEnabled(this.player.getUniqueId().toString());
                PACUtilities.playersFile.setMsgSound(this.player.getUniqueId().toString(), !togglemsgsoundEnabled);
                this.fillInventory();
            }
            // exit
            case 8 -> this.inv.close();
        }
    }
}
