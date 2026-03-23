package fr.peaceandcube.pacprofile.module.settings;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.gui.GuiContext;
import fr.peaceandcube.pacprofile.gui.ProfileGui;
import fr.peaceandcube.pacprofile.gui.BaseGui;
import fr.peaceandcube.pacprofile.gui.item.GuiItem;
import fr.peaceandcube.pacprofile.gui.item.LoreProvider;
import fr.peaceandcube.pacprofile.lang.TranslationManager;
import fr.peaceandcube.pacprofile.module.Module;
import fr.peaceandcube.pacprofile.module.settings.enums.PTimeType;
import fr.peaceandcube.pacprofile.module.settings.enums.PWeatherType;
import fr.peaceandcube.pacutilities.PACUtilities;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingsGui extends BaseGui {
    private final Module module;

    public SettingsGui(Module module, Player viewer, Player player) {
        super(1, Component.text(module.translate("settings_title")), viewer, player);
        this.module = module;
        this.fillInventory();
        Bukkit.getPluginManager().registerEvents(this, PACProfile.getInstance());
    }

    @Override
    public void fillInventory() {
        this.items.clear();

        if (hasPermission("essentials.msgtoggle")) {
            boolean msgtoggleEnabled = !PACProfile.getEssentials().getUser(this.player).isIgnoreMsg();
            Component msgtoggle = msgtoggleEnabled
                    ? LoreProvider.line(module.translate("settings_enabled"), NamedTextColor.GREEN)
                    : LoreProvider.line(module.translate("settings_disabled"), NamedTextColor.RED);
            this.setItem(GuiItem.builder().slot(2).material(Material.PAPER)
                    .customModelData(3050)
                    .glint(msgtoggleEnabled)
                    .name(module.translate("settings_msgtoggle"), 0x5555FF)
                    .lore(Component.empty(), msgtoggle)
                    .lore(Component.empty(), LoreProvider.line(module.translate("settings_msgtoggle_click")))
                    .onLeftClick(context -> {
                        PACProfile.getEssentials().getUser(context.player()).setIgnoreMsg(msgtoggleEnabled);
                        context.fillInventory();
                    })
                    .build());
        }

        if (hasPermission("pacutilities.togglemsgsound")) {
            boolean togglemsgsoundEnabled = PACUtilities.playersFile.isMsgSoundEnabled(this.player.getUniqueId().toString());
            Component togglemsgsound = togglemsgsoundEnabled
                    ? LoreProvider.line(module.translate("settings_enabled"), NamedTextColor.GREEN)
                    : LoreProvider.line(module.translate("settings_disabled"), NamedTextColor.RED);
            this.setItem(GuiItem.builder().slot(3).material(Material.JUKEBOX)
                    .customModelData(3050)
                    .glint(togglemsgsoundEnabled)
                    .name(module.translate("settings_togglemsgsound"), 0x5555FF)
                    .lore(Component.empty(), togglemsgsound)
                    .lore(Component.empty(), LoreProvider.line(module.translate("settings_togglemsgsound_click")))
                    .onLeftClick(context -> {
                        PACUtilities.playersFile.setMsgSound(context.player().getUniqueId().toString(), !togglemsgsoundEnabled);
                        context.fillInventory();
                    })
                    .build());
        }

        if (hasPermission("essentials.ptime")) {
            // ptime
            PTimeType currentTime = PTimeType.fromTicks(this.player.getPlayerTime() % 24000);
            List<Component> ptimeLore = new ArrayList<>();
            ptimeLore.add(Component.empty());
            Arrays.stream(PTimeType.values()).forEach(time -> {
                String text = module.translate("settings_ptime_" + time.name().toLowerCase());
                Component component = LoreProvider.line(text);
                if (time == currentTime && this.player.getPlayerTimeOffset() != 0) {
                    component = component.color(NamedTextColor.YELLOW).decoration(TextDecoration.BOLD, true);
                }
                ptimeLore.add(component);
            });
            ptimeLore.add(Component.empty());
            ptimeLore.add(LoreProvider.line(module.translate("settings_ptime_click_left")));
            ptimeLore.add(LoreProvider.line(module.translate("settings_ptime_click_right")));
            this.setItem(GuiItem.builder().slot(5).material(Material.CLOCK)
                    .customModelData(3050)
                    .name(module.translate("settings_ptime"), 0x5555FF)
                    .lore(ptimeLore)
                    .onLeftClick(context -> {
                        PTimeType newTime = currentTime.next();
                        if (context.player().equals(context.viewer())) {
                            context.dispatchCommand("ptime " + newTime.name().toLowerCase());
                        } else {
                            context.dispatchCommand("ptime " + newTime.name().toLowerCase() + " " + context.player().getName());
                        }
                        context.fillInventory();
                    })
                    .onRightClick(context -> {
                        if (context.player().equals(context.viewer())) {
                            context.dispatchCommand("ptime reset");
                        } else {
                            context.dispatchCommand("ptime reset " + context.player().getName());
                        }
                        context.fillInventory();
                    })
                    .build());
        }

        if (hasPermission("essentials.pweather")) {
            // pweather
            PWeatherType currentWeather = PWeatherType.fromWeatherType(this.player.getPlayerWeather());
            List<Component> pweatherLore = new ArrayList<>();
            pweatherLore.add(Component.empty());
            Arrays.stream(PWeatherType.values()).forEach(weather -> {
                String text = module.translate("settings_pweather_" + weather.name().toLowerCase());
                Component component = LoreProvider.line(text);
                if (weather == currentWeather && this.player.getPlayerWeather() != null) {
                    component = component.color(NamedTextColor.YELLOW).decoration(TextDecoration.BOLD, true);
                }
                pweatherLore.add(component);
            });
            pweatherLore.add(Component.empty());
            pweatherLore.add(LoreProvider.line(module.translate("settings_pweather_click_left")));
            pweatherLore.add(LoreProvider.line(module.translate("settings_pweather_click_right")));
            this.setItem(GuiItem.builder().slot(6).material(Material.SUNFLOWER)
                    .customModelData(3050)
                    .name(module.translate("settings_pweather"), 0x5555FF)
                    .lore(pweatherLore)
                    .onLeftClick(context -> {
                        PWeatherType newWeather = currentWeather.next();
                        if (context.player().equals(context.viewer())) {
                            context.dispatchCommand("pweather " + newWeather.name().toLowerCase());
                        } else {
                            context.dispatchCommand("pweather " + newWeather.name().toLowerCase() + " " + context.player().getName());
                        }
                        context.fillInventory();
                    })
                    .onRightClick(context -> {
                        if (context.player().equals(context.viewer())) {
                            context.dispatchCommand("pweather reset");
                        } else {
                            context.dispatchCommand("pweather reset " + context.player().getName());
                        }
                        context.fillInventory();
                    })
                    .build());
        }

        this.setItem(GuiItem.builder().slot(0).material(Material.ARROW)
                .customModelData(3002)
                .name(TranslationManager.translate("page_previous"), 0xFF55FF)
                .onLeftClick(context -> new ProfileGui(context.viewer(), context.player()).open())
                .build());

        this.setItem(GuiItem.builder().slot(8).material(Material.BARRIER)
                .customModelData(3002)
                .name(TranslationManager.translate("exit"), 0xFF5555)
                .onLeftClick(GuiContext::close)
                .build());
    }

    private boolean hasPermission(String permission) {
        User user = PACProfile.getLuckPerms().getUserManager().getUser(this.player.getUniqueId());
        return user != null && user.getCachedData().getPermissionData().checkPermission(permission).asBoolean();
    }
}
