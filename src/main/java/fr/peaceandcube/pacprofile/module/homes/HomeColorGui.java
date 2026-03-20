package fr.peaceandcube.pacprofile.module.homes;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.gui.UnmodifiableGui;
import fr.peaceandcube.pacprofile.gui.item.GuiItem;
import fr.peaceandcube.pacprofile.module.Module;
import fr.peaceandcube.pacprofile.module.homes.enums.HomeColor;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class HomeColorGui extends UnmodifiableGui {
    private final Module module;
    private final String name;
    private final int page;
    private final int maxPages;

    public HomeColorGui(Module module, Player viewer, Player player, String name, int page, int maxPages) {
        super(2, Component.text(module.translate("home_color_title")), viewer, player);
        this.module = module;
        this.name = name;
        this.page = page;
        this.maxPages = maxPages;
        this.fillInventory();
        Bukkit.getPluginManager().registerEvents(this, PACProfile.getInstance());
    }

    @Override
    public void fillInventory() {
        this.items.clear();

        for (HomeColor color : HomeColor.values()) {
            this.setItem(GuiItem.builder().slot(color.ordinal()).material(color.getDye())
                    .customModelData(3012)
                    .name(color.translate(), 0xFF55FF)
                    .onLeftClick(context -> {
                        PACProfile.getInstance().playerData.setHomeColor(context.player().getUniqueId(), this.name, color.getName());
                        new HomesGui(module, context.viewer(), context.player(), this.page, this.maxPages).open();
                    })
                    .build());
        }
    }
}
