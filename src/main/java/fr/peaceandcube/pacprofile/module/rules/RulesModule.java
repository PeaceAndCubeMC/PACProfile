package fr.peaceandcube.pacprofile.module.rules;

import fr.peaceandcube.pacprofile.item.GuiItem;
import fr.peaceandcube.pacprofile.module.Module;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.util.Messages;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;

public class RulesModule extends Module {

    public RulesModule() {
        super("rules");
    }

    @Override
    public boolean isEnabled() {
        return !config.getCommandOnClickRules().isBlank();
    }

    @Override
    public GuiItem createGuiItem() {
        return GuiItem.builder().slot(45).material(Material.KNOWLEDGE_BOOK)
                .customModelData(3004)
                .name(Messages.RULES, 0xFF55FF)
                .lore(Component.empty(), LoreComponents.RULES_CLICK)
                .onLeftClick(context -> {
                    context.dispatchCommand(config.getCommandOnClickRules());
                    context.close();
                })
                .build();
    }
}
