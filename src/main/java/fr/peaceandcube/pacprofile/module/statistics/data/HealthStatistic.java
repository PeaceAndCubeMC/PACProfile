package fr.peaceandcube.pacprofile.module.statistics.data;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class HealthStatistic extends Statistic {

    public HealthStatistic() {
        super("health", Attribute.MAX_HEALTH);
    }

    @Override
    public double currentValue(Player player) {
        return player.getHealth();
    }
}
