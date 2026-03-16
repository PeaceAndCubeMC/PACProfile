package fr.peaceandcube.pacprofile.module.onlineplayers;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.config.ConfigOption;
import fr.peaceandcube.pacprofile.item.GuiItem;
import fr.peaceandcube.pacprofile.module.Module;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.util.Messages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.Function;

public class OnlinePlayersModule extends Module {

    public OnlinePlayersModule() {
        super("online_players");
    }

    @Override
    protected Function<Player, GuiItem> createGuiItem() {
        return player -> {
            int onlinePlayersCount = Bukkit.getOnlinePlayers().stream()
                    .filter(p -> !PACProfile.getEssentials().getUser(p).isVanished())
                    .toList()
                    .size();
            int maxOnlinePlayersPages = (int) Math.ceil(onlinePlayersCount / 10.0f);

            List<Component> lore = List.of(
                    Component.empty(),
                    LoreComponents.ONLINE_PLAYERS_COUNT.append(Component.text(onlinePlayersCount, NamedTextColor.YELLOW, TextDecoration.BOLD)),
                    Component.empty(),
                    LoreComponents.ONLINE_PLAYERS_CLICK
            );

            return GuiItem.builder().slot(32).material(Material.PLAYER_HEAD)
                    .customModelData(3005)
                    .name(Messages.ONLINE_PLAYERS, 0x55FF55)
                    .lore(lore)
                    .onLeftClick(context -> new OnlinePlayersGui(context.viewer(), context.player(), 1, maxOnlinePlayersPages).open())
                    .build();
        };
    }

    @Override
    protected void registerConfigOptions() {
        configOptions.put("enable_teleportation", ConfigOption.bool(true));
    }

    @Override
    protected void registerDefaultTranslations() {
        defaultTranslations.put("online_players", "Online Players");
        defaultTranslations.put("online_players_count", "Total: ");
        defaultTranslations.put("online_players_click", "⇒ Click to see online players");
        defaultTranslations.put("online_players_title", "Online Players (%2$d/%3$d)");
        defaultTranslations.put("online_players_order", "Order players");
        defaultTranslations.put("online_player_trust_count_1", "Trusted in ");
        defaultTranslations.put("online_player_trust_count_2", " claim(s)");
        defaultTranslations.put("online_player_mail_sent_1", "Has sent ");
        defaultTranslations.put("online_player_mail_sent_2", " mail(s)");
        defaultTranslations.put("online_player_click", "⇒ Click to ask for a teleportation");
        defaultTranslations.put("online_player_notes_click_left", "⇒ Left click to edit notes");
        defaultTranslations.put("online_player_notes_click_right", "⇒ Right click to clear notes");
        defaultTranslations.put("online_player_notes_title", "Edit notes");
    }
}
