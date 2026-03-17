package fr.peaceandcube.pacprofile.module.links;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.gui.item.GuiItem;
import fr.peaceandcube.pacprofile.module.Module;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.util.Messages;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.function.Function;

public class LinksModule extends Module {

    public LinksModule() {
        super("links");
    }

    @Override
    public boolean isEnabled() {
        return !PACProfile.getInstance().config.getCommandOnClickLinks().isBlank();
    }

    @Override
    protected Function<Player, GuiItem> createGuiItem() {
        return player -> GuiItem.builder().slot(46).material(Material.IRON_CHAIN)
                .customModelData(3004)
                .name(Messages.LINKS, 0xFF55FF)
                .lore(Component.empty(), LoreComponents.LINKS_CLICK)
                .onLeftClick(context -> {
                    context.dispatchCommand(PACProfile.getInstance().config.getCommandOnClickLinks());
                    context.close();
                })
                .build();
    }

    @Override
    protected void registerConfigOptions() {
    }

    @Override
    protected void registerDefaultTranslations() {
        defaultTranslations.put("links", "Server Links");
        defaultTranslations.put("links_click", "⇒ Click to see the links");
    }
}
