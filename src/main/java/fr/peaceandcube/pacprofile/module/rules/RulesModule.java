package fr.peaceandcube.pacprofile.module.rules;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.gui.item.GuiItem;
import fr.peaceandcube.pacprofile.gui.item.LoreProvider;
import fr.peaceandcube.pacprofile.module.Module;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.function.Function;

public class RulesModule extends Module {

    public RulesModule() {
        super("rules");
    }

    @Override
    public boolean isEnabled() {
        return !PACProfile.getInstance().config.getCommandOnClickRules().isBlank();
    }

    @Override
    protected Function<Player, GuiItem> createGuiItem() {
        return player -> GuiItem.builder().slot(45).material(Material.KNOWLEDGE_BOOK)
                .customModelData(3004)
                .name(translate("name"), 0xFF55FF)
                .lore(Component.empty(), LoreProvider.line(translate("click")))
                .onLeftClick(context -> {
                    context.dispatchCommand(PACProfile.getInstance().config.getCommandOnClickRules());
                    context.close();
                })
                .build();
    }

    @Override
    protected void registerConfigOptions() {
    }

    @Override
    protected void registerDefaultTranslations() {
        defaultTranslations.put("name", "Rules");
        defaultTranslations.put("click", "⇒ Click to see the rules");
    }
}
