package fr.peaceandcube.pacprofile.module.coins;

import com.earth2me.essentials.User;
import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.gui.item.GuiItem;
import fr.peaceandcube.pacprofile.gui.item.LoreProvider;
import fr.peaceandcube.pacprofile.module.Module;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CoinsModule extends Module {

    public CoinsModule() {
        super("coins");
    }

    @Override
    protected Function<Player, GuiItem> createGuiItem() {
        return player -> {
            User user = PACProfile.getEssentials().getUser(player);
            double coinCount = user.getMoney().doubleValue();

            List<Component> lore = new ArrayList<>();
            lore.add(Component.empty());
            lore.add(LoreProvider.line(translate("total"), coinCount));
            if (!PACProfile.getInstance().config.getCommandOnClickCoins().isBlank()) {
                lore.add(Component.empty());
                lore.add(LoreProvider.line(translate("click")));
            }

            return GuiItem.builder().slot(20).material(Material.SUNFLOWER)
                    .customModelData(3004)
                    .name(translate("name"), 0xFFAA00)
                    .lore(lore)
                    .onLeftClick(context -> {
                        if (!PACProfile.getInstance().config.getCommandOnClickCoins().isBlank()) {
                            context.dispatchCommand(PACProfile.getInstance().config.getCommandOnClickCoins());
                            context.close();
                        }
                    })
                    .build();
        };
    }

    @Override
    protected void registerConfigOptions() {
    }

    @Override
    protected void registerDefaultTranslations() {
        defaultTranslations.put("name", "Coins");
        defaultTranslations.put("total", "Total: ");
        defaultTranslations.put("click", "⇒ Click to see more about coins");
    }
}
