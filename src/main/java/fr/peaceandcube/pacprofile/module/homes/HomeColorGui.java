package fr.peaceandcube.pacprofile.module.homes;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.gui.UnmodifiableGui;
import fr.peaceandcube.pacprofile.item.GuiItem;
import fr.peaceandcube.pacprofile.module.homes.enums.HomeColor;
import fr.peaceandcube.pacprofile.util.Messages;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class HomeColorGui extends UnmodifiableGui {
    private final String name;
    private final int page;
    private final int maxPages;

    public HomeColorGui(Player viewer, Player player, String name, int page, int maxPages) {
        super(2, Component.text(Messages.HOME_COLOR_TITLE), viewer, player);
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
                        new HomesGui(context.viewer(), context.player(), this.page, this.maxPages).open();
                    })
                    .build());
        }
    }
}
