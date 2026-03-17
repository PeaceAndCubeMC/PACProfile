package fr.peaceandcube.pacprofile.module.homes;

import com.earth2me.essentials.User;
import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.config.ConfigOption;
import fr.peaceandcube.pacprofile.gui.item.GuiItem;
import fr.peaceandcube.pacprofile.module.Module;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.util.Messages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.Function;

public class HomesModule extends Module {

    public HomesModule() {
        super("homes");
    }

    @Override
    protected Function<Player, GuiItem> createGuiItem() {
        return player -> {
            User user = PACProfile.getEssentials().getUser(player);
            int totalHomeCount = PACProfile.getEssentials().getSettings().getHomeLimit(user);
            int usedHomeCount = user.getHomes().size();
            int remainingHomeCount = Math.max(0, totalHomeCount - usedHomeCount);
            int maxHomePages = (int) Math.ceil(usedHomeCount / 10.0f);

            List<Component> lore = List.of(
                    Component.empty(),
                    LoreComponents.HOMES_TOTAL.append(Component.text(usedHomeCount, NamedTextColor.YELLOW, TextDecoration.BOLD)),
                    LoreComponents.HOMES_REMAINING.append(Component.text(remainingHomeCount, NamedTextColor.YELLOW, TextDecoration.BOLD)),
                    LoreComponents.HOMES_MAX_AVAILABLE.append(Component.text(totalHomeCount, NamedTextColor.YELLOW, TextDecoration.BOLD)),
                    Component.empty(),
                    LoreComponents.HOMES_CLICK
            );

            return GuiItem.builder().slot(28).material(Material.RED_BED)
                    .customModelData(3004)
                    .name(Messages.HOMES, 0x5555FF)
                    .lore(lore)
                    .onLeftClick(context -> new HomesGui(context.viewer(), context.player(), 1, maxHomePages).open())
                    .build();
        };
    }

    @Override
    protected void registerConfigOptions() {
        configOptions.put("default_color", ConfigOption.string("red"));
        configOptions.put("enable_teleportation", ConfigOption.bool(true));
        configOptions.put("enable_deletion", ConfigOption.bool(true));
    }

    @Override
    protected void registerDefaultTranslations() {
        defaultTranslations.put("homes", "Homes");
        defaultTranslations.put("homes_total", "Total: ");
        defaultTranslations.put("homes_remaining", "Remaining: ");
        defaultTranslations.put("homes_max_available", "Maximum available: ");
        defaultTranslations.put("homes_click", "⇒ Click to see homes");
        defaultTranslations.put("homes_title", "Homes of %s (%2$d/%3$d)");
        defaultTranslations.put("homes_order", "Order homes");
        defaultTranslations.put("home_world", "World: ");
        defaultTranslations.put("home_x", "X: ");
        defaultTranslations.put("home_y", "Y: ");
        defaultTranslations.put("home_z", "Z: ");
        defaultTranslations.put("home_click_left", "⇒ Left click to go to this home");
        defaultTranslations.put("home_click_right", "⇒ Right click to delete this home");
        defaultTranslations.put("home_notes", "Notes");
        defaultTranslations.put("home_notes_click_left", "⇒ Left click to edit notes");
        defaultTranslations.put("home_notes_click_right", "⇒ Right click to clear notes");
        defaultTranslations.put("home_notes_title", "Edit notes");
        defaultTranslations.put("home_color", "Color");
        defaultTranslations.put("home_color_click", "⇒ Click to change color");
        defaultTranslations.put("home_color_title", "Choose a color");
    }
}
