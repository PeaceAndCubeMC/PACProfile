package fr.peaceandcube.pacprofile.gui;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.item.GuiItem;
import fr.peaceandcube.pacprofile.settings.PTimeType;
import fr.peaceandcube.pacprofile.settings.PWeatherType;
import fr.peaceandcube.pacprofile.text.LoreComponents;
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
        this.items.clear();

        if (hasPermission("essentials.msgtoggle")) {
            boolean msgtoggleEnabled = !PACProfile.getEssentials().getUser(this.player).isIgnoreMsg();
            Component msgtoggle = msgtoggleEnabled ? LoreComponents.SETTINGS_ENABLED : LoreComponents.SETTINGS_DISABLED;
            this.setItem(GuiItem.builder().slot(2).material(Material.PAPER)
                    .customModelData(3050)
                    .glint(msgtoggleEnabled)
                    .name(Messages.SETTINGS_MSGTOGGLE, 0x5555FF)
                    .lore(Component.empty(), msgtoggle)
                    .lore(Component.empty(), LoreComponents.SETTINGS_MSGTOGGLE_CLICK)
                    .onLeftClick(() -> {
                        PACProfile.getEssentials().getUser(this.player).setIgnoreMsg(msgtoggleEnabled);
                        this.fillInventory();
                    })
                    .build());
        }

        if (hasPermission("pacutilities.togglemsgsound")) {
            boolean togglemsgsoundEnabled = PACUtilities.playersFile.isMsgSoundEnabled(this.player.getUniqueId().toString());
            Component togglemsgsound = togglemsgsoundEnabled ? LoreComponents.SETTINGS_ENABLED : LoreComponents.SETTINGS_DISABLED;
            this.setItem(GuiItem.builder().slot(3).material(Material.JUKEBOX)
                    .customModelData(3050)
                    .glint(togglemsgsoundEnabled)
                    .name(Messages.SETTINGS_TOGGLEMSGSOUND, 0x5555FF)
                    .lore(Component.empty(), togglemsgsound)
                    .lore(Component.empty(), LoreComponents.SETTINGS_TOGGLEMSGSOUND_CLICK)
                    .onLeftClick(() -> {
                        PACUtilities.playersFile.setMsgSound(this.player.getUniqueId().toString(), !togglemsgsoundEnabled);
                        this.fillInventory();
                    })
                    .build());
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
            this.setItem(GuiItem.builder().slot(5).material(Material.CLOCK)
                    .customModelData(3050)
                    .name(Messages.SETTINGS_PTIME, 0x5555FF)
                    .lore(ptimeLore)
                    .onLeftClick(() -> {
                        PTimeType newTime = currentTime.next();
                        if (this.player.equals(this.viewer)) {
                            this.dispatchCommand("ptime " + newTime.name().toLowerCase());
                        } else {
                            this.dispatchCommand("ptime " + newTime.name().toLowerCase() + " " + this.player.getName());
                        }
                        this.fillInventory();
                    })
                    .onRightClick(() -> {
                        if (this.player.equals(this.viewer)) {
                            this.dispatchCommand("ptime reset");
                        } else {
                            this.dispatchCommand("ptime reset " + this.player.getName());
                        }
                        this.fillInventory();
                    })
                    .build());
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
            this.setItem(GuiItem.builder().slot(6).material(Material.SUNFLOWER)
                    .customModelData(3050)
                    .name(Messages.SETTINGS_PWEATHER, 0x5555FF)
                    .lore(pweatherLore)
                    .onLeftClick(() -> {
                        PWeatherType newWeather = currentWeather.next();
                        if (this.player.equals(this.viewer)) {
                            this.dispatchCommand("pweather " + newWeather.name().toLowerCase());
                        } else {
                            this.dispatchCommand("pweather " + newWeather.name().toLowerCase() + " " + this.player.getName());
                        }
                        this.fillInventory();
                    })
                    .onRightClick(() -> {
                        if (this.player.equals(this.viewer)) {
                            this.dispatchCommand("pweather reset");
                        } else {
                            this.dispatchCommand("pweather reset " + this.player.getName());
                        }
                        this.fillInventory();
                    })
                    .build());
        }

        this.setItem(GuiItem.builder().slot(0).material(Material.ARROW)
                .customModelData(3002)
                .name(Messages.PAGE_PREVIOUS, 0xFF55FF)
                .onLeftClick(() -> new ProfileGui(this.viewer, this.player).open())
                .build());

        this.setItem(GuiItem.builder().slot(8).material(Material.BARRIER)
                .customModelData(3002)
                .name(Messages.EXIT, 0xFF5555)
                .onLeftClick(this.inv::close)
                .build());
    }

    private boolean hasPermission(String permission) {
        User user = PACProfile.getLuckPerms().getUserManager().getUser(this.player.getUniqueId());
        return user != null && user.getCachedData().getPermissionData().checkPermission(permission).asBoolean();
    }
}
