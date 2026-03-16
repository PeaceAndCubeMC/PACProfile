package fr.peaceandcube.pacprofile.module.statistics;

import fr.peaceandcube.pacprofile.item.GuiItem;
import fr.peaceandcube.pacprofile.module.Module;
import fr.peaceandcube.pacprofile.module.statistics.data.Statistic;
import fr.peaceandcube.pacprofile.module.statistics.data.Statistics;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.util.Messages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class StatisticsModule extends Module {
    private boolean baseStatistics;

    public StatisticsModule() {
        super("statistics");
        this.baseStatistics = false;
    }

    @Override
    protected Function<Player, GuiItem> createGuiItem() {
        return player -> {
            List<Component> lore = new ArrayList<>();
            lore.add(this.baseStatistics ? LoreComponents.STATISTICS_BASE : LoreComponents.STATISTICS_CURRENT);
            lore.add(Component.empty());
            for (Statistic statistic : Statistics.ALL) {
                if (config.isStatisticEnabled(statistic.getName())) {
                    lore.add(getStatisticComponent(statistic, player));
                }
            }
            lore.add(Component.empty());
            lore.add(this.baseStatistics ? LoreComponents.STATISTICS_CLICK_CURRENT : LoreComponents.STATISTICS_CLICK_BASE);

            return GuiItem.builder().slot(9).material(Material.ENCHANTED_BOOK)
                    .customModelData(3004)
                    .name(Messages.STATISTICS, 0xFF55FF)
                    .lore(lore)
                    .onLeftClick(context -> {
                        this.baseStatistics = !this.baseStatistics;
                        context.fillInventory();
                    })
                    .build();
        };
    }

    private Component getStatisticComponent(Statistic statistic, Player player) {
        double value = this.baseStatistics ? statistic.getBaseValue(player) : statistic.getCurrentValue(player);
        double diff = Math.round((statistic.getCurrentValue(player) - statistic.getBaseValue(player)) * 100.0) / 100.0;
        Component component = statistic.getTextComponent().append(Component.text(value, NamedTextColor.YELLOW, TextDecoration.BOLD));
        if (!this.baseStatistics && diff != 0) {
            TextColor diffColor = diff > 0 ? NamedTextColor.GREEN : NamedTextColor.RED;
            // if the difference is positive, add a plus sign
            component = component.append(Component.text(" (" + (diff > 0 ? "+" : "") + diff + ")", diffColor));
        }
        return component;
    }
}
