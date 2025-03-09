package fr.peaceandcube.pacprofile.statistic;

import fr.peaceandcube.pacprofile.PACProfile;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class Statistic {
    private final String name;
    private final Attribute attribute;

    public Statistic(String name, Attribute attribute) {
        this.name = name;
        this.attribute = attribute;
    }

    public String getName() {
        return name;
    }

    public double getBaseValue(Player player) {
        var attr = player.getAttribute(attribute);
        if (attr != null) {
            return Math.round(attr.getBaseValue() * 100.0) / 100.0;
        }
        return 0.0;
    }

    public double getCurrentValue(Player player) {
        var attr = player.getAttribute(attribute);
        if (attr != null) {
            return Math.round(attr.getValue() * 100.0) / 100.0;
        }
        return 0.0;
    }

    public Component getTextComponent() {
        String text = PACProfile.getInstance().lang.translate("statistics_" + name);
        return Component.text(text, TextColor.color(0xAAAAAA))
                .decoration(TextDecoration.ITALIC, false);
    }
}
