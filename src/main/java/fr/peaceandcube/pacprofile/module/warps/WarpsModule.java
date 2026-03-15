package fr.peaceandcube.pacprofile.module.warps;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.item.GuiItem;
import fr.peaceandcube.pacprofile.module.Module;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.util.Messages;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.function.Function;

public class WarpsModule extends Module {

    public WarpsModule() {
        super("warps");
    }

    @Override
    protected Function<Player, GuiItem> createGuiItem() {
        return player -> {
            int maxWarpsPages = (int) Math.ceil(PACProfile.getInstance().config.getWarps().size() / 35.0f);

            return GuiItem.builder().slot(34).material(Material.ENDER_PEARL)
                    .customModelData(3004)
                    .name(Messages.WARPS, 0xFFFF55)
                    .lore(Component.empty(), LoreComponents.WARPS_CLICK)
                    .onLeftClick(context -> new WarpsGui(context.viewer(), context.player(), 1, maxWarpsPages).open())
                    .build();
        };
    }
}
