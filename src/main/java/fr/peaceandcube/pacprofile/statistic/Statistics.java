package fr.peaceandcube.pacprofile.statistic;

import org.bukkit.attribute.Attribute;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
    public static final List<Statistic> ALL = new ArrayList<>();

    static {
        register(new HealthStatistic());
        register(new Statistic("max_health", Attribute.MAX_HEALTH));
        register(new Statistic("oxygen_bonus", Attribute.OXYGEN_BONUS));
        register(new Statistic("armor", Attribute.ARMOR));
        register(new Statistic("armor_toughness", Attribute.ARMOR_TOUGHNESS));
        register(new Statistic("knockback_resistance", Attribute.KNOCKBACK_RESISTANCE));
        register(new Statistic("speed", Attribute.MOVEMENT_SPEED));
        register(new Statistic("sneaking_speed", Attribute.SNEAKING_SPEED));
        register(new Statistic("attack_damage", Attribute.ATTACK_DAMAGE));
        register(new Statistic("attack_speed", Attribute.ATTACK_SPEED));
        register(new Statistic("mining_efficiency", Attribute.MINING_EFFICIENCY));
        register(new Statistic("luck", Attribute.LUCK));
    }

    private static void register(Statistic statistic) {
        ALL.add(statistic);
    }
}
