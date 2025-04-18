package fr.peaceandcube.pacprofile.gui;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.settings.PTimeType;
import fr.peaceandcube.pacprofile.settings.PWeatherType;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.text.NameComponents;
import fr.peaceandcube.pacprofile.util.Messages;
import fr.peaceandcube.pacutilities.PACUtilities;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingsGui extends UnmodifiableGui {

    public SettingsGui(Player viewer, Player player) {
        super(1, Component.text(Messages.SETTINGS_TITLE), viewer, player);
        this.fillInventory();
        Bukkit.getPluginManager().registerEvents(this, PACProfile.getInstance());
    }

    @Override
    protected void fillInventory() {
        if (hasPermission("essentials.msgtoggle")) {
            boolean msgtoggleEnabled = !PACProfile.getEssentials().getUser(this.player).isIgnoreMsg();
            Component msgtoggle = msgtoggleEnabled ? LoreComponents.SETTINGS_ENABLED : LoreComponents.SETTINGS_DISABLED;
            this.setItem(2, Material.PAPER, 3050, msgtoggleEnabled, NameComponents.SETTINGS_MSGTOGGLE, List.of(
                    Component.empty(),
                    msgtoggle,
                    Component.empty(),
                    LoreComponents.SETTINGS_MSGTOGGLE_CLICK
            ));
        }

        if (hasPermission("pacutilities.togglemsgsound")) {
            boolean togglemsgsoundEnabled = PACUtilities.playersFile.isMsgSoundEnabled(this.player.getUniqueId().toString());
            Component togglemsgsound = togglemsgsoundEnabled ? LoreComponents.SETTINGS_ENABLED : LoreComponents.SETTINGS_DISABLED;
            this.setItem(3, Material.JUKEBOX, 3050, togglemsgsoundEnabled, NameComponents.SETTINGS_TOGGLEMSGSOUND, List.of(
                    Component.empty(),
                    togglemsgsound,
                    Component.empty(),
                    LoreComponents.SETTINGS_TOGGLEMSGSOUND_CLICK
            ));
        }

        if (hasPermission("essentials.ptime")) {
            // ptime
            PTimeType currentTime = PTimeType.fromTicks(this.player.getPlayerTime() % 24000);
            List<Component> ptimeLore = new ArrayList<>();
            ptimeLore.add(Component.empty());
            Arrays.stream(PTimeType.values()).forEach(time -> {
                String text = PACProfile.getInstance().lang.translate("settings_ptime_" + time.name().toLowerCase());
                Component component = Component.text(text, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
                if (time == currentTime && this.player.getPlayerTimeOffset() != 0) {
                    component = component.color(TextColor.color(0xFFFF55)).decoration(TextDecoration.BOLD, true);
                }
                ptimeLore.add(component);
            });
            ptimeLore.add(Component.empty());
            ptimeLore.add(LoreComponents.SETTINGS_PTIME_CLICK_LEFT);
            ptimeLore.add(LoreComponents.SETTINGS_PTIME_CLICK_RIGHT);
            this.setItem(5, Material.CLOCK, 3050, NameComponents.SETTINGS_PTIME, ptimeLore);
        }

        if (hasPermission("essentials.pweather")) {
            // pweather
            PWeatherType currentWeather = PWeatherType.fromWeatherType(this.player.getPlayerWeather());
            List<Component> pweatherLore = new ArrayList<>();
            pweatherLore.add(Component.empty());
            Arrays.stream(PWeatherType.values()).forEach(weather -> {
                String text = PACProfile.getInstance().lang.translate("settings_pweather_" + weather.name().toLowerCase());
                Component component = Component.text(text, TextColor.color(0xAAAAAA)).decoration(TextDecoration.ITALIC, false);
                if (weather == currentWeather && this.player.getPlayerWeather() != null) {
                    component = component.color(TextColor.color(0xFFFF55)).decoration(TextDecoration.BOLD, true);
                }
                pweatherLore.add(component);
            });
            pweatherLore.add(Component.empty());
            pweatherLore.add(LoreComponents.SETTINGS_PWEATHER_CLICK_LEFT);
            pweatherLore.add(LoreComponents.SETTINGS_PWEATHER_CLICK_RIGHT);
            this.setItem(6, Material.SUNFLOWER, 3050, NameComponents.SETTINGS_PWEATHER, pweatherLore);
        }

        this.setItem(0, Material.ARROW, 3002, NameComponents.PAGE_PREVIOUS);
        this.setItem(8, Material.BARRIER, 3002, NameComponents.EXIT);
    }

    private boolean hasPermission(String permission) {
        User user = PACProfile.getLuckPerms().getUserManager().getUser(this.player.getUniqueId());
        return user != null && user.getCachedData().getPermissionData().checkPermission(permission).asBoolean();
    }

    @Override
    protected void onSlotLeftClick(int slot) {
        switch (slot) {
            // previous page
            case 0 -> new ProfileGui(this.viewer, this.player).open();
            // msgtoggle
            case 2 -> {
                if (hasPermission("essentials.msgtoggle")) {
                    boolean msgtoggleEnabled = PACProfile.getEssentials().getUser(this.player).isIgnoreMsg();
                    PACProfile.getEssentials().getUser(this.player).setIgnoreMsg(!msgtoggleEnabled);
                    this.fillInventory();
                }
            }
            // togglemsgsound
            case 3 -> {
                if (hasPermission("pacutilities.togglemsgsound")) {
                    boolean togglemsgsoundEnabled = PACUtilities.playersFile.isMsgSoundEnabled(this.player.getUniqueId().toString());
                    PACUtilities.playersFile.setMsgSound(this.player.getUniqueId().toString(), !togglemsgsoundEnabled);
                    this.fillInventory();
                }
            }
            // ptime
            case 5 -> {
                if (hasPermission("essentials.ptime")) {
                    PTimeType currentTime = PTimeType.fromTicks(this.player.getPlayerTime() % 24000);
                    PTimeType newTime = currentTime.next();
                    if (this.player.equals(this.viewer)) {
                        this.dispatchCommand("ptime " + newTime.name().toLowerCase());
                    } else {
                        this.dispatchCommand("ptime " + newTime.name().toLowerCase() + " " + this.player.getName());
                    }
                    this.fillInventory();
                }
            }
            // pweather
            case 6 -> {
                if (hasPermission("essentials.pweather")) {
                    PWeatherType currentWeather = PWeatherType.fromWeatherType(this.player.getPlayerWeather());
                    PWeatherType newWeather = currentWeather.next();
                    if (this.player.equals(this.viewer)) {
                        this.dispatchCommand("pweather " + newWeather.name().toLowerCase());
                    } else {
                        this.dispatchCommand("pweather " + newWeather.name().toLowerCase() + " " + this.player.getName());
                    }
                    this.fillInventory();
                }
            }
            // exit
            case 8 -> this.inv.close();
        }
    }

    @Override
    protected void onSlotRightClick(int slot) {
        switch (slot) {
            // ptime
            case 5 -> {
                if (hasPermission("essentials.ptime")) {
                    if (this.player.equals(this.viewer)) {
                        this.dispatchCommand("ptime reset");
                    } else {
                        this.dispatchCommand("ptime reset " + this.player.getName());
                    }
                    this.fillInventory();
                }
            }
            // pweather
            case 6 -> {
                if (hasPermission("essentials.pweather")) {
                    if (this.player.equals(this.viewer)) {
                        this.dispatchCommand("pweather reset");
                    } else {
                        this.dispatchCommand("pweather reset " + this.player.getName());
                    }
                    this.fillInventory();
                }
            }
        }
    }
}
