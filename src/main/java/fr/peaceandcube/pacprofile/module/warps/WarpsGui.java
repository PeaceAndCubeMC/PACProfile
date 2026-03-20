package fr.peaceandcube.pacprofile.module.warps;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.file.WarpEntry;
import fr.peaceandcube.pacprofile.gui.GuiContext;
import fr.peaceandcube.pacprofile.gui.PaginatedGui;
import fr.peaceandcube.pacprofile.gui.ProfileGui;
import fr.peaceandcube.pacprofile.gui.item.GuiItem;
import fr.peaceandcube.pacprofile.gui.item.LoreProvider;
import fr.peaceandcube.pacprofile.logging.Logger;
import fr.peaceandcube.pacprofile.module.Module;
import fr.peaceandcube.pacprofile.order.Order;
import fr.peaceandcube.pacprofile.order.OrderSet;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.util.Messages;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class WarpsGui extends PaginatedGui {
    private final Module module;
    private List<WarpEntry> warps;

    public WarpsGui(Module module, Player viewer, Player player, int page, int maxPages) {
        this(module, viewer, player, page, maxPages, Order.DEFAULT);
    }

    public WarpsGui(Module module, Player viewer, Player player, int page, int maxPages, Order order) {
        super(Component.text(module.translate("warps_title").formatted(player.getName(), Math.max(1, page), Math.max(1, maxPages))),
                viewer,
                player,
                page,
                maxPages,
                new OrderSet(order, Order.DEFAULT, Order.NAME_AZ, Order.NAME_ZA, Order.CATEGORY_AZ, Order.CATEGORY_ZA)
        );
        this.module = module;
        this.warps = PACProfile.getInstance().config.getWarps();
        this.fillInventory();
        Bukkit.getPluginManager().registerEvents(this, PACProfile.getInstance());
    }

    @Override
    public void fillInventory() {
        this.items.clear();

        switch (this.orderSet().currentOrder()) {
            case DEFAULT -> this.warps = PACProfile.getInstance().config.getWarps();
            case NAME_AZ -> this.warps.sort((warp1, warp2) -> warp1.name().compareToIgnoreCase(warp2.name()));
            case NAME_ZA -> this.warps.sort((warp1, warp2) -> warp2.name().compareToIgnoreCase(warp1.name()));
            case CATEGORY_AZ -> this.warps.sort((warp1, warp2) -> warp1.category().compareToIgnoreCase(warp2.category()));
            case CATEGORY_ZA -> this.warps.sort((warp1, warp2) -> warp2.category().compareToIgnoreCase(warp1.category()));
        }

        int warpCount = this.warps.size();
        int maxWarpsOnPage = this.page() * 35;
        int warpsOnPage = warpCount >= maxWarpsOnPage ? maxWarpsOnPage : (warpCount - (this.page() - 1) * 35);

        for (int i = 0; i < Math.min(warpsOnPage, 35); i++) {
            int index = maxWarpsOnPage - 35 + i;
            int slot = (i / 7) * 9 + (i % 7 + 1);

            WarpEntry warp = this.warps.get(index);

            String command = String.format("/warp %s", warp.name());
            List<Component> warpLore = List.of(
                    Component.empty(),
                    LoreProvider.line(module.translate("warp_command"), command),
                    LoreProvider.line(module.translate("warp_category"), warp.category()),
                    Component.empty(),
                    LoreProvider.line(module.translate("warp_click"))
            );
            this.setItem(GuiItem.builder().slot(slot).material(warp.icon())
                    .customModelData(3040)
                    .name(warp.title(), 0x5555FF)
                    .lore(warpLore)
                    .onLeftClick(context -> {
                        Logger.debug("%s teleported to warp %s".formatted(context.viewer().getName(), warp.name()));
                        context.dispatchCommand("warp " + warp.name());
                        context.close();
                    })
                    .build());
        }

        this.setItem(GuiItem.builder().slot(51).material(Material.HOPPER)
                .customModelData(3041)
                .name(module.translate("warps_order"), 0x00AA00)
                .lore(Component.empty(), LoreComponents.ORDER_BY.append(this.orderSet().currentOrder().getText()))
                .lore(Component.empty(), LoreComponents.ORDER_CLICK)
                .onLeftClick(context -> {
                    context.orderSet().next();
                    context.fillInventory();
                })
                .build());

        this.setItem(GuiItem.builder().slot(45).material(Material.ARROW)
                .customModelData(3002)
                .name(Messages.PAGE_PREVIOUS, 0xFF55FF)
                .onLeftClick(context -> {
                    if (context.page() == 1) {
                        new ProfileGui(context.viewer(), context.player()).open();
                    } else {
                        new WarpsGui(
                                module,
                                context.viewer(),
                                context.player(),
                                context.page() - 1,
                                context.maxPages(),
                                context.orderSet().currentOrder()
                        ).open();
                    }
                })
                .build());

        this.setItem(GuiItem.builder().slot(49).material(Material.BARRIER)
                .customModelData(3002)
                .name(Messages.EXIT, 0xFF5555)
                .onLeftClick(GuiContext::close)
                .build());

        // if it's not the last page
        if (warpCount > maxWarpsOnPage) {
            this.setItem(GuiItem.builder().slot(53).material(Material.ARROW)
                    .customModelData(3003)
                    .name(Messages.PAGE_NEXT, 0xFF55FF)
                    .onLeftClick(context -> new WarpsGui(
                            module,
                            context.viewer(),
                            context.player(),
                            context.page() + 1,
                            context.maxPages(),
                            context.orderSet().currentOrder()
                    ).open())
                    .build());
        }
    }
}
