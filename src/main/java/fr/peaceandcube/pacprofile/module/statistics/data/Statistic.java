package fr.peaceandcube.pacprofile.module.statistics.data;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class Statistic {
    private final String name;
    private final Attribute attribute;

    public Statistic(String name, Attribute attribute) {
        this.name = name;
        this.attribute = attribute;
    }

    public String name() {
        return name;
    }

    public double baseValue(Player player) {
        var attr = player.getAttribute(attribute);
        if (attr != null) {
            return Math.round(attr.getBaseValue() * 100.0) / 100.0;
        }
        return 0.0;
    }

    public double currentValue(Player player) {
        var attr = player.getAttribute(attribute);
        if (attr != null) {
            return Math.round(attr.getValue() * 100.0) / 100.0;
        }
        return 0.0;
    }
}
