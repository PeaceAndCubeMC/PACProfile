package fr.peaceandcube.pacprofile.module.dynmap;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.item.GuiItem;
import fr.peaceandcube.pacprofile.module.Module;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.util.Messages;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.function.Function;

public class DynmapModule extends Module {

    public DynmapModule() {
        super("dynmap");
    }

    @Override
    public boolean isEnabled() {
        return !PACProfile.getInstance().config.getCommandOnClickDynmap().isBlank();
    }

    @Override
    protected Function<Player, GuiItem> createGuiItem() {
        return player -> GuiItem.builder().slot(47).material(Material.MAP)
                .customModelData(3004)
                .name(Messages.DYNMAP, 0xFF55FF)
                .lore(Component.empty(), LoreComponents.DYNMAP_CLICK)
                .onLeftClick(context -> {
                    context.dispatchCommand(PACProfile.getInstance().config.getCommandOnClickDynmap());
                    context.close();
                })
                .build();
    }

    @Override
    protected void registerConfigOptions() {
    }

    @Override
    protected void registerDefaultTranslations() {
        defaultTranslations.put("dynmap", "Dynmap");
        defaultTranslations.put("dynmap_click", "⇒ Click to open the dynmap");
    }
}
