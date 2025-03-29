package fr.peaceandcube.pacprofile.gui;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.file.WarpEntry;
import fr.peaceandcube.pacprofile.item.GuiItem;
import fr.peaceandcube.pacprofile.order.Order;
import fr.peaceandcube.pacprofile.order.OrderSet;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.text.NameComponents;
import fr.peaceandcube.pacprofile.util.Messages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class WarpsGui extends UnmodifiableGui {
    private final int page;
    private final int maxPages;
    private List<WarpEntry> warps;
    private final OrderSet orderSet;

    public WarpsGui(Player viewer, Player player, int page, int maxPages) {
        this(viewer, player, page, maxPages, Order.DEFAULT);
    }

    public WarpsGui(Player viewer, Player player, int page, int maxPages, Order order) {
        super(6, Component.text(String.format(Messages.WARPS_TITLE, player.getName(), Math.max(1, page), Math.max(1, maxPages))), viewer, player);
        this.page = Math.max(1, page);
        this.maxPages = maxPages;
        this.warps = PACProfile.getInstance().config.getWarps();
        this.orderSet = new OrderSet(order, Order.DEFAULT, Order.NAME_AZ, Order.NAME_ZA, Order.CATEGORY_AZ, Order.CATEGORY_ZA);
        this.fillInventory();
        Bukkit.getPluginManager().registerEvents(this, PACProfile.getInstance());
    }

    @Override
    protected void fillInventory() {
        this.items.clear();

        switch (this.orderSet.currentOrder()) {
            case DEFAULT -> this.warps = PACProfile.getInstance().config.getWarps();
            case NAME_AZ -> this.warps.sort((warp1, warp2) -> warp1.name().compareToIgnoreCase(warp2.name()));
            case NAME_ZA -> this.warps.sort((warp1, warp2) -> warp2.name().compareToIgnoreCase(warp1.name()));
            case CATEGORY_AZ -> this.warps.sort((warp1, warp2) -> warp1.category().compareToIgnoreCase(warp2.category()));
            case CATEGORY_ZA -> this.warps.sort((warp1, warp2) -> warp2.category().compareToIgnoreCase(warp1.category()));
        }

        int warpCount = this.warps.size();
        int maxWarpsOnPage = this.page * 35;
        int warpsOnPage = warpCount >= maxWarpsOnPage ? maxWarpsOnPage : (warpCount - (this.page - 1) * 35);

        for (int i = 0; i < Math.min(warpsOnPage, 35); i++) {
            int index = maxWarpsOnPage - 35 + i;
            int slot = (i / 7) * 9 + (i % 7 + 1);

            WarpEntry warp = this.warps.get(index);

            Component title = Component.text(warp.title(), TextColor.color(0x5555FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
            String command = String.format("/warp %s", warp.name());
            List<Component> warpLore = List.of(
                    Component.empty(),
                    LoreComponents.WARP_COMMAND.append(Component.text(command, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                    LoreComponents.WARP_CATEGORY.append(Component.text(warp.category(), TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                    Component.empty(),
                    LoreComponents.WARP_CLICK
            );
            this.setItem(GuiItem.builder().slot(slot).material(warp.icon())
                    .customModelData(3040)
                    .name(title)
                    .lore(warpLore)
                    .onLeftClick(() -> {
                        this.dispatchCommand("warp " + warp.name());
                        this.inv.close();
                    })
                    .build());
        }

        this.setItem(GuiItem.builder().slot(51).material(Material.HOPPER)
                .customModelData(3041)
                .name(NameComponents.WARPS_ORDER)
                .lore(Component.empty(), LoreComponents.ORDER_BY.append(this.orderSet.currentOrder().getText()))
                .lore(Component.empty(), LoreComponents.ORDER_CLICK)
                .onLeftClick(() -> {
                    this.orderSet.next();
                    this.fillInventory();
                })
                .build());

        this.setItem(GuiItem.builder().slot(45).material(Material.ARROW)
                .customModelData(3002)
                .name(NameComponents.PAGE_PREVIOUS)
                .onLeftClick(() -> {
                    if (this.page == 1) {
                        new ProfileGui(this.viewer, this.player).open();
                    } else {
                        new WarpsGui(this.viewer, this.player, this.page - 1, this.maxPages,
                                this.orderSet.currentOrder()).open();
                    }
                })
                .build());

        this.setItem(GuiItem.builder().slot(49).material(Material.BARRIER)
                .customModelData(3002)
                .name(NameComponents.EXIT)
                .onLeftClick(this.inv::close)
                .build());

        // if it's not the last page
        if (warpCount > maxWarpsOnPage) {
            this.setItem(GuiItem.builder().slot(53).material(Material.ARROW)
                    .customModelData(3003)
                    .name(NameComponents.PAGE_NEXT)
                    .onLeftClick(() -> new WarpsGui(this.viewer, this.player, this.page + 1, this.maxPages,
                            this.orderSet.currentOrder()).open())
                    .build());
        }
    }
}
