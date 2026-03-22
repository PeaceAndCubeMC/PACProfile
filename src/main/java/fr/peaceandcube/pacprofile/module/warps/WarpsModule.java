package fr.peaceandcube.pacprofile.module.warps;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.gui.item.GuiItem;
import fr.peaceandcube.pacprofile.gui.item.LoreProvider;
import fr.peaceandcube.pacprofile.module.Module;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.function.Function;

public class WarpsModule extends Module {

    public WarpsModule() {
        super("warps", true);
    }

    @Override
    protected Function<Player, GuiItem> createGuiItem() {
        return player -> {
            int maxWarpsPages = (int) Math.ceil(PACProfile.getInstance().config.getWarps().size() / 35.0f);

            return GuiItem.builder().slot(34).material(Material.ENDER_PEARL)
                    .customModelData(3004)
                    .name(translate("warps"), 0xFFFF55)
                    .lore(Component.empty(), LoreProvider.line(translate("warps_click")))
                    .onLeftClick(context -> new WarpsGui(this, context.viewer(), context.player(), 1, maxWarpsPages).open())
                    .build();
        };
    }

    @Override
    protected void registerConfigOptions() {
    }

    @Override
    protected void registerDefaultTranslations() {
        defaultTranslations.put("warps", "Warps");
        defaultTranslations.put("warps_click", "⇒ Click to see warps");
        defaultTranslations.put("warps_title", "Warps (%2$d/%3$d)");
        defaultTranslations.put("warps_order", "Order warps");
        defaultTranslations.put("warp_command", "Command: ");
        defaultTranslations.put("warp_category", "Category: ");
        defaultTranslations.put("warp_click", "⇒ Click to go to this warp");
    }
}
