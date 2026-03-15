package fr.peaceandcube.pacprofile.module.onlineplayers;

import fr.peaceandcube.pacprofile.PACProfile;
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
}
