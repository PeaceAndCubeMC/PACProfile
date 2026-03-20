package fr.peaceandcube.pacprofile.module.statistics;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.config.ConfigOption;
import fr.peaceandcube.pacprofile.gui.item.GuiItem;
import fr.peaceandcube.pacprofile.gui.item.LoreProvider;
import fr.peaceandcube.pacprofile.module.Module;
import fr.peaceandcube.pacprofile.module.statistics.data.Statistic;
import fr.peaceandcube.pacprofile.module.statistics.data.Statistics;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
                if (PACProfile.getInstance().config.isStatisticEnabled(statistic.name())) {
                    lore.add(getStatisticComponent(statistic, player));
                }
            }
            lore.add(Component.empty());
            lore.add(this.baseStatistics
                    ? LoreProvider.line(translate("statistics_click_current"))
                    : LoreProvider.line(translate("statistics_click_base"))
            );

            return GuiItem.builder().slot(9).material(Material.ENCHANTED_BOOK)
                    .customModelData(3004)
                    .name(translate("statistics"), 0xFF55FF)
                    .lore(lore)
                    .onLeftClick(context -> {
                        this.baseStatistics = !this.baseStatistics;
                        context.fillInventory();
                    })
                    .build();
        };
    }

    @Override
    protected void registerConfigOptions() {
        for (Statistic statistic : Statistics.ALL) {
            configOptions.put(statistic.name(), ConfigOption.object(Map.of(
                    "enabled", ConfigOption.bool(true)
            )));
        }
    }

    @Override
    protected void registerDefaultTranslations() {
        defaultTranslations.put("statistics", "Statistics");
        defaultTranslations.put("statistics_base", "Base statistics");
        defaultTranslations.put("statistics_current", "Current statistics");
        defaultTranslations.put("statistics_health", "❤ Health: ");
        defaultTranslations.put("statistics_max_health", "❤ Max health: ");
        defaultTranslations.put("statistics_oxygen_bonus", "🫧 Oxygen bonus: ");
        defaultTranslations.put("statistics_armor", "\uD83D\uDEE1 Armor: ");
        defaultTranslations.put("statistics_armor_toughness", "\uD83D\uDEE1 Armor toughness: ");
        defaultTranslations.put("statistics_knockback_resistance", "❄ Knockback resistance: ");
        defaultTranslations.put("statistics_speed", "👟 Speed: ");
        defaultTranslations.put("statistics_sneaking_speed", "👟 Sneaking speed: ");
        defaultTranslations.put("statistics_attack_damage", "\uD83D\uDDE1 Attack damage: ");
        defaultTranslations.put("statistics_attack_speed", "\uD83D\uDDE1 Attack speed: ");
        defaultTranslations.put("statistics_mining_efficiency", "⛏ Mining efficiency: ");
        defaultTranslations.put("statistics_luck", "☘ Luck: ");
        defaultTranslations.put("statistics_click_base", "⇒ Click to show base statistics");
        defaultTranslations.put("statistics_click_current", "⇒ Click to show current statistics");
    }

    private Component getStatisticComponent(Statistic statistic, Player player) {
        double value = this.baseStatistics ? statistic.baseValue(player) : statistic.currentValue(player);
        double diff = Math.round((statistic.currentValue(player) - statistic.baseValue(player)) * 100.0) / 100.0;
        Component component = LoreProvider.line(translate("statistics_" + statistic.name()), value);
        if (!this.baseStatistics && diff != 0) {
            TextColor diffColor = diff > 0 ? NamedTextColor.GREEN : NamedTextColor.RED;
            // if the difference is positive, add a plus sign
            component = component.append(Component.text(" (" + (diff > 0 ? "+" : "") + diff + ")", diffColor));
        }
        return component;
    }
}
