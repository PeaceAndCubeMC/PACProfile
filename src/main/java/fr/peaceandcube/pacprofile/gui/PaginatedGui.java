package fr.peaceandcube.pacprofile.gui;

import fr.peaceandcube.pacprofile.order.OrderSet;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public abstract class PaginatedGui extends BaseGui {
    private static final int ROWS = 6;
    private final int page;
    private final int maxPages;
    private final OrderSet orderSet;

    protected PaginatedGui(Component title, Player viewer, Player player, int page, int maxPages, OrderSet orderSet) {
        super(ROWS, title, viewer, player);
        this.page = Math.max(1, page);
        this.maxPages = maxPages;
        this.orderSet = orderSet;
    }

    @Override
    public int page() {
        return page;
    }

    @Override
    public int maxPages() {
        return maxPages;
    }

    @Override
    public OrderSet orderSet() {
        return orderSet;
    }
}
