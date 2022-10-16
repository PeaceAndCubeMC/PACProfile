package fr.peaceandcube.pacprofile.gui;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.file.WarpEntry;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.text.NameComponents;
import fr.peaceandcube.pacprofile.util.Messages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WarpsGui extends UnmodifiableGui {
    private final Map<Integer, WarpEntry> WARPS_SLOTS = new LinkedHashMap<>();
    private final int page;
    private final int maxPages;
    private List<WarpEntry> warps;
    private Order order;

    public WarpsGui(Player viewer, Player player, int page, int maxPages) {
        this(viewer, player, page, maxPages, Order.DEFAULT);
    }

    public WarpsGui(Player viewer, Player player, int page, int maxPages, Order order) {
        super(6, Component.text(String.format(Messages.WARPS_TITLE, player.getName(), Math.max(1, page), Math.max(1, maxPages))), viewer, player);
        this.page = Math.max(1, page);
        this.maxPages = maxPages;
        this.warps = PACProfile.getInstance().config.getWarps();
        this.order = order;
        this.fillInventory();
        Bukkit.getPluginManager().registerEvents(this, PACProfile.getInstance());
    }

    @Override
    protected void fillInventory() {
        if (this.order == Order.DEFAULT) {
            this.warps = PACProfile.getInstance().config.getWarps();
        } else if (this.order == Order.NAME_AZ) {
            this.warps.sort((warp1, warp2) -> warp1.name().compareToIgnoreCase(warp2.name()));
        } else if (this.order == Order.NAME_ZA) {
            this.warps.sort((warp1, warp2) -> warp2.name().compareToIgnoreCase(warp1.name()));
        } else if (this.order == Order.CATEGORY_AZ) {
            this.warps.sort((warp1, warp2) -> warp1.category().compareToIgnoreCase(warp2.category()));
        } else if (this.order == Order.CATEGORY_ZA) {
            this.warps.sort((warp1, warp2) -> warp2.category().compareToIgnoreCase(warp1.category()));
        }

        int warpCount = this.warps.size();
        int maxWarpsOnPage = this.page * 35;
        int warpsOnPage = warpCount >= maxWarpsOnPage ? maxWarpsOnPage : (warpCount - (this.page - 1) * 35);

        for (int i = 0; i < Math.min(warpsOnPage, 35); i++) {
            int index = maxWarpsOnPage - 35 + i;
            int slot = (i / 7) * 9 + (i % 7 + 1);

            WarpEntry warp = this.warps.get(index);

            WARPS_SLOTS.put(slot, warp);

            Component title = Component.text(warp.title(), TextColor.color(0x5555FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
            String command = String.format("/warp %s", warp.name());
            this.setItem(slot, warp.icon(), 3040, title, List.of(
                    Component.empty(),
                    LoreComponents.WARP_COMMAND.append(Component.text(command, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                    LoreComponents.WARP_CATEGORY.append(Component.text(warp.category(), TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                    Component.empty(),
                    LoreComponents.WARP_CLICK
            ));
        }

        this.setItem(51, Material.HOPPER, 3041, NameComponents.WARPS_ORDER, List.of(
                Component.empty(),
                LoreComponents.ORDER_BY.append(this.order.getText()),
                Component.empty(),
                LoreComponents.ORDER_CLICK
        ));

        this.setItem(45, Material.ARROW, 3002, NameComponents.PAGE_PREVIOUS);
        this.setItem(49, Material.BARRIER, 3002, NameComponents.EXIT);
        // if it's not the last page
        if (warpCount > maxWarpsOnPage) {
            this.setItem(53, Material.ARROW, 3003, NameComponents.PAGE_NEXT);
        }
    }

    @Override
    protected void onSlotLeftClick(int slot) {
        // previous page
        if (slot == 45) {
            if (this.page == 1) {
                new ProfileGui(this.viewer, this.player).open();
            } else {
                new WarpsGui(this.viewer, this.player, this.page - 1, this.maxPages, this.order).open();
            }
        }

        // exit
        else if (slot == 49) {
            this.inv.close();
        }

        // next page
        else if (slot == 53) {
            int warpCount = this.warps.size();
            int maxWarpsOnPage = this.page * 35;
            if (warpCount > maxWarpsOnPage) {
                new WarpsGui(this.viewer, this.player, this.page + 1, this.maxPages, this.order).open();
            }
        }

        // order
        else if (slot == 51) {
            this.order = this.order.next();
            this.fillInventory();
        }

        // warps
        else if (WARPS_SLOTS.containsKey(slot)) {
            this.dispatchCommand("warp " + WARPS_SLOTS.get(slot).name());
            this.inv.close();
        }
    }

    enum Order {
        DEFAULT(LoreComponents.ORDER_DEFAULT),
        NAME_AZ(LoreComponents.ORDER_NAME_AZ),
        NAME_ZA(LoreComponents.ORDER_NAME_ZA),
        CATEGORY_AZ(LoreComponents.ORDER_CATEGORY_AZ),
        CATEGORY_ZA(LoreComponents.ORDER_CATEGORY_ZA);

        private final Component text;

        Order(Component text) {
            this.text = text;
        }

        public Component getText() {
            return this.text;
        }

        public Order next() {
            return switch (this) {
                case DEFAULT -> NAME_AZ;
                case NAME_AZ -> NAME_ZA;
                case NAME_ZA -> CATEGORY_AZ;
                case CATEGORY_AZ -> CATEGORY_ZA;
                case CATEGORY_ZA -> DEFAULT;
            };
        }
    }
}
