package fr.peaceandcube.pacprofile.module.claims;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.gui.GuiContext;
import fr.peaceandcube.pacprofile.gui.PaginatedGui;
import fr.peaceandcube.pacprofile.gui.ProfileGui;
import fr.peaceandcube.pacprofile.gui.dialog.DialogItem;
import fr.peaceandcube.pacprofile.gui.dialog.TextInputDialog;
import fr.peaceandcube.pacprofile.gui.item.GuiItem;
import fr.peaceandcube.pacprofile.gui.item.LoreProvider;
import fr.peaceandcube.pacprofile.logging.Logger;
import fr.peaceandcube.pacprofile.module.Module;
import fr.peaceandcube.pacprofile.order.Order;
import fr.peaceandcube.pacprofile.order.OrderSet;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.util.Messages;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.PlayerData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.IntStream;

public class ClaimsGui extends PaginatedGui {
    private final Module module;
    private final PlayerData playerData;
    private Vector<Claim> claims;

    public ClaimsGui(Module module, Player viewer, Player player, int page, int maxPages) {
        this(module, viewer, player, page, maxPages, Order.DEFAULT);
    }

    public ClaimsGui(Module module, Player viewer, Player player, int page, int maxPages, Order order) {
        super(Component.text(module.translate("claims_title").formatted(player.getName(), Math.max(1, page), Math.max(1, maxPages))),
                viewer,
                player,
                page,
                maxPages,
                new OrderSet(order, Order.DEFAULT, Order.NAME_AZ, Order.NAME_ZA, Order.AREA_ASC, Order.AREA_DESC)
        );
        this.module = module;
        this.playerData = PACProfile.getGriefPrevention().dataStore.getPlayerData(this.player.getUniqueId());
        this.claims = this.playerData.getClaims();
        this.fillInventory();
        Bukkit.getPluginManager().registerEvents(this, PACProfile.getInstance());
    }

    @Override
    public void fillInventory() {
        this.items.clear();

        switch (this.orderSet().currentOrder()) {
            case DEFAULT -> this.claims = this.playerData.getClaims();
            case NAME_AZ -> this.claims.sort((claim1, claim2) ->
                    this.getName(claim1.getID().toString()).compareToIgnoreCase(this.getName(claim2.getID().toString()))
            );
            case NAME_ZA -> this.claims.sort((claim1, claim2) ->
                    this.getName(claim2.getID().toString()).compareToIgnoreCase(this.getName(claim1.getID().toString()))
            );
            case AREA_ASC -> this.claims.sort(Comparator.comparingInt(Claim::getArea));
            case AREA_DESC -> this.claims.sort(Comparator.comparingInt(Claim::getArea).reversed());
        }

        int claimCount = this.claims.size();
        int maxClaimOnPage = this.page() * 10;
        int claimsOnPage = claimCount >= maxClaimOnPage ? maxClaimOnPage : (claimCount - (this.page() - 1) * 10);

        for (int i = 0; i < Math.min(claimsOnPage, 10); i++) {
            int index = maxClaimOnPage - 10 + i;
            int slot;
            // first column of the page
            if (i < 5) {
                slot = i * 9 + 1;
                // second column of the page
            } else {
                slot = (i - 5) * 9 + 5;
            }

            Claim claim = this.claims.get(index);
            String claimId = claim.getID().toString();
            Location greaterCorner = claim.getGreaterBoundaryCorner();
            Location lesserCorner = claim.getLesserBoundaryCorner();
            String world = greaterCorner.getWorld().getName();
            int width = claim.getWidth();
            int length = claim.getHeight();
            int area = claim.getArea();

            int customModelData = world.equals("world_nether") ? 3021 : 3020;
            List<Component> claimLore = List.of(
                    Component.empty(),
                    LoreProvider.line(module.translate("claim_world"), world),
                    LoreProvider.line(module.translate("claim_greater_corner"), greaterCorner.getBlockX() + " " + greaterCorner.getBlockY() + " " + greaterCorner.getBlockZ()),
                    LoreProvider.line(module.translate("claim_lesser_corner"), lesserCorner.getBlockX() + " " + lesserCorner.getBlockY() + " " + lesserCorner.getBlockZ()),
                    Component.empty(),
                    LoreProvider.line(module.translate("claim_width"), width),
                    LoreProvider.line(module.translate("claim_length"), length),
                    LoreProvider.line(module.translate("claim_area"), area),
                    Component.empty()
            );
            this.setItem(GuiItem.builder().slot(slot).material(Material.GOLDEN_SHOVEL)
                    .customModelData(customModelData)
                    .name(getName(claimId), 0xFFFF55)
                    .lore(claimLore)
                    .build());

            ArrayList<String> builders = new ArrayList<>();
            ArrayList<String> containers = new ArrayList<>();
            ArrayList<String> accessors = new ArrayList<>();
            ArrayList<String> managers = new ArrayList<>();
            claim.getPermissions(builders, containers, accessors, managers);
            List<Component> permissionsLore = new ArrayList<>();
            permissionsLore.add(Component.empty());
            this.getPermissionLore(permissionsLore, LoreProvider.line(module.translate("claim_permissions_builders")), builders);
            this.getPermissionLore(permissionsLore, LoreProvider.line(module.translate("claim_permissions_containers")), containers);
            this.getPermissionLore(permissionsLore, LoreProvider.line(module.translate("claim_permissions_accessors")), accessors);
            this.getPermissionLore(permissionsLore, LoreProvider.line(module.translate("claim_permissions_managers")), managers);
            permissionsLore.add(Component.empty());
            this.setItem(GuiItem.builder().slot(slot + 1).material(Material.KNOWLEDGE_BOOK)
                    .customModelData(3022)
                    .name(module.translate("claim_permissions"), 0x00AA00)
                    .lore(permissionsLore)
                    .build());

            this.setItem(GuiItem.builder().slot(slot + 2).material(Material.PAPER)
                    .customModelData(3023)
                    .name(module.translate("claim_name"), 0x00AAAA)
                    .lore(Component.empty(), LoreProvider.line(module.translate("claim_name_click")))
                    .onLeftClick(context -> TextInputDialog.builder()
                            .player(context.viewer())
                            .title(module.translate("claims"), 0x00AA00)
                            .bodyItem(new DialogItem(Material.GOLDEN_SHOVEL, customModelData))
                            .bodyText(module.translate("claims_default_name").formatted(claimId))
                            .inputLabel(module.translate("claim_name_title"))
                            .inputValue(PACProfile.getInstance().playerData.getClaimName(context.player().getUniqueId(), claimId))
                            .inputSize(1, 18)
                            .onConfirm(newValue -> {
                                Logger.debug("%s edited name for claim %s".formatted(context.player().getName(), getName(claimId)));
                                PACProfile.getInstance().playerData.setClaimName(context.player().getUniqueId(), claimId, newValue);
                                new ClaimsGui(module, context.viewer(), context.player(), context.page(), context.maxPages(), context.orderSet().currentOrder()).open();
                            })
                            .build()
                            .show())
                    .build());
        }

        this.setItem(GuiItem.builder().slot(51).material(Material.HOPPER)
                .customModelData(3024)
                .name(module.translate("claims_order"), 0x00AA00)
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
                        new ClaimsGui(
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
        if (claimCount > maxClaimOnPage) {
            this.setItem(GuiItem.builder().slot(53).material(Material.ARROW)
                    .customModelData(3003)
                    .name(Messages.PAGE_NEXT, 0xFF55FF)
                    .onLeftClick(context -> new ClaimsGui(
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

    private String getName(String claimId) {
        String name = PACProfile.getInstance().playerData.getClaimName(this.player.getUniqueId(), claimId);
        return name.isEmpty() ? module.translate("claims_default_name").formatted(claimId) : name;
    }

    private void getPermissionLore(List<Component> components, Component baseComponent, List<String> list) {
        List<String> mappedList = list.stream().map(s -> {
            try {
                return Bukkit.getOfflinePlayer(UUID.fromString(s)).getName();
            } catch (IllegalArgumentException e) {
                return s;
            }
        }).toList();

        List<List<String>> subLists = IntStream.range(0, (mappedList.size() + 3 - 1) / 3)
                .mapToObj(i -> mappedList.subList(3 * i, Math.min(3 * i + 3, mappedList.size())))
                .toList();

        components.add(baseComponent);

        for (var subList : subLists) {
            String subListStr = String.join(" ", subList);
            components.add(Component.text(subListStr, NamedTextColor.YELLOW, TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false));
        }
    }
}
