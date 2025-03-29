package fr.peaceandcube.pacprofile.gui;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.item.GuiItem;
import fr.peaceandcube.pacprofile.util.Color;
import fr.peaceandcube.pacprofile.util.Messages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
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
    protected void fillInventory() {
        this.items.clear();

        for (int i = 0; i < Color.values().length; i++) {
            Color color = Color.values()[i];
            Component name = Component.text(color.translate(), TextColor.color(0xFF55FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
            this.setItem(GuiItem.builder().slot(i).material(color.getDye())
                    .customModelData(3012)
                    .name(name)
                    .onLeftClick(() -> {
                        PACProfile.getInstance().playerData.setHomeColor(this.player.getUniqueId(), this.name, color.getName());
                        new HomesGui(this.viewer, this.player, this.page, this.maxPages).open();
                    })
                    .build());
        }
    }
}
