package fr.peaceandcube.pacprofile.module.headtickets;

import fr.peaceandcube.pacprofile.item.GuiItem;
import fr.peaceandcube.pacprofile.module.Module;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.util.Messages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class HeadTicketsModule extends Module {

    public HeadTicketsModule() {
        super("headtickets");
    }

    @Override
    protected Function<Player, GuiItem> createGuiItem() {
        return player -> {
            int headTicketCount = getObjectiveScore(player, config.getHeadTicketsScoreboard());
            int questCount = getObjectiveScore(player, config.getQuestsScoreboard());

            List<Component> lore = new ArrayList<>();
            lore.add(Component.empty());
            lore.add(LoreComponents.HEAD_TICKETS_NUMBER.append(Component.text(headTicketCount, NamedTextColor.YELLOW, TextDecoration.BOLD)));
            lore.add(Component.empty());
            lore.add(LoreComponents.HEAD_TICKETS_QUEST_NUMBER.append(Component.text(questCount, NamedTextColor.YELLOW, TextDecoration.BOLD)));
            if (!config.getCommandOnClickHeadTickets().isBlank()) {
                lore.add(Component.empty());
                lore.add(LoreComponents.HEAD_TICKETS_CLICK);
            }

            return GuiItem.builder().slot(22).material(Material.NAME_TAG)
                    .customModelData(3004)
                    .name(Messages.HEAD_TICKETS, 0x00AAAA)
                    .lore(lore)
                    .onLeftClick(context -> {
                        if (!config.getCommandOnClickHeadTickets().isBlank()) {
                            context.dispatchCommand(config.getCommandOnClickHeadTickets());
                            context.close();
                        }
                    })
                    .build();
        };
    }

    private int getObjectiveScore(Player player, String scoreboard) {
        Objective objective = player.getScoreboard().getObjective(scoreboard);
        return objective != null ? objective.getScore(player.getName()).getScore() : 0;
    }
}
