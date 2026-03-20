package fr.peaceandcube.pacprofile.module.headtickets;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.config.ConfigOption;
import fr.peaceandcube.pacprofile.gui.item.GuiItem;
import fr.peaceandcube.pacprofile.gui.item.LoreProvider;
import fr.peaceandcube.pacprofile.module.Module;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class HeadTicketsModule extends Module {

    public HeadTicketsModule() {
        super("head_tickets");
    }

    @Override
    protected Function<Player, GuiItem> createGuiItem() {
        return player -> {
            int headTicketCount = getObjectiveScore(player, PACProfile.getInstance().config.getHeadTicketsScoreboard());
            int questCount = getObjectiveScore(player, PACProfile.getInstance().config.getQuestsScoreboard());

            List<Component> lore = new ArrayList<>();
            lore.add(Component.empty());
            lore.add(LoreProvider.line(translate("head_tickets_total"), headTicketCount));
            lore.add(Component.empty());
            lore.add(LoreProvider.line(translate("head_tickets_quest_number"), questCount));
            if (!PACProfile.getInstance().config.getCommandOnClickHeadTickets().isBlank()) {
                lore.add(Component.empty());
                lore.add(LoreProvider.line(translate("head_tickets_click")));
            }

            return GuiItem.builder().slot(22).material(Material.NAME_TAG)
                    .customModelData(3004)
                    .name(translate("head_tickets"), 0x00AAAA)
                    .lore(lore)
                    .onLeftClick(context -> {
                        if (!PACProfile.getInstance().config.getCommandOnClickHeadTickets().isBlank()) {
                            context.dispatchCommand(PACProfile.getInstance().config.getCommandOnClickHeadTickets());
                            context.close();
                        }
                    })
                    .build();
        };
    }

    @Override
    protected void registerConfigOptions() {
        configOptions.put("quests_scoreboard", ConfigOption.string(""));
    }

    @Override
    protected void registerDefaultTranslations() {
        defaultTranslations.put("head_tickets", "Head Tickets");
        defaultTranslations.put("head_tickets_total", "Total: ");
        defaultTranslations.put("head_tickets_quest_number", "Quests done: ");
        defaultTranslations.put("head_tickets_click", "⇒ Click to see more about head tickets");
    }

    private int getObjectiveScore(Player player, String scoreboard) {
        Objective objective = player.getScoreboard().getObjective(scoreboard);
        return objective != null ? objective.getScore(player.getName()).getScore() : 0;
    }
}
