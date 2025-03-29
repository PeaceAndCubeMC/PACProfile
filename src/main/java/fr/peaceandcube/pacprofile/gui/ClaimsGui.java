package fr.peaceandcube.pacprofile.gui;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.item.GuiItem;
import fr.peaceandcube.pacprofile.order.Order;
import fr.peaceandcube.pacprofile.order.OrderSet;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.text.NameComponents;
import fr.peaceandcube.pacprofile.util.Messages;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.PlayerData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.IntStream;

public class ClaimsGui extends UnmodifiableGui {
    private final PlayerData playerData;
    private final int page;
    private final int maxPages;
    private Vector<Claim> claims;
    private final OrderSet orderSet;

    public ClaimsGui(Player viewer, Player player, int page, int maxPages) {
        this(viewer, player, page, maxPages, Order.DEFAULT);
    }

    public ClaimsGui(Player viewer, Player player, int page, int maxPages, Order order) {
        super(6, Component.text(String.format(Messages.CLAIMS_TITLE, player.getName(), Math.max(1, page), Math.max(1, maxPages))), viewer, player);
        this.playerData = PACProfile.getGriefPrevention().dataStore.getPlayerData(this.player.getUniqueId());
        this.page = Math.max(1, page);
        this.maxPages = maxPages;
        this.claims = this.playerData.getClaims();
        this.orderSet = new OrderSet(order, Order.DEFAULT, Order.NAME_AZ, Order.NAME_ZA, Order.AREA_ASC, Order.AREA_DESC);
        this.fillInventory();
        Bukkit.getPluginManager().registerEvents(this, PACProfile.getInstance());
    }

    @Override
    protected void fillInventory() {
        this.items.clear();

        switch (this.orderSet.currentOrder()) {
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
        int maxClaimOnPage = this.page * 10;
        int claimsOnPage = claimCount >= maxClaimOnPage ? maxClaimOnPage : (claimCount - (this.page - 1) * 10);

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

            List<Component> claimLore = List.of(
                    Component.empty(),
                    LoreComponents.CLAIM_WORLD.append(Component.text(world, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                    LoreComponents.CLAIM_GREATER_CORNER.append(Component.text(greaterCorner.getBlockX() + " " + greaterCorner.getBlockY() + " " + greaterCorner.getBlockZ(), TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                    LoreComponents.CLAIM_LESSER_CORNER.append(Component.text(lesserCorner.getBlockX() + " " + lesserCorner.getBlockY() + " " + lesserCorner.getBlockZ(), TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                    Component.empty(),
                    LoreComponents.CLAIM_WIDTH.append(Component.text(width, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                    LoreComponents.CLAIM_LENGTH.append(Component.text(length, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                    LoreComponents.CLAIM_AREA.append(Component.text(area, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                    Component.empty()
            );
            this.setItem(GuiItem.builder().slot(slot).material(Material.GOLDEN_SHOVEL)
                    .customModelData(world.equals("world_nether") ? 3021 : 3020)
                    .name(getNameLore(claimId))
                    .lore(claimLore)
                    .build());

            ArrayList<String> builders = new ArrayList<>();
            ArrayList<String> containers = new ArrayList<>();
            ArrayList<String> accessors = new ArrayList<>();
            ArrayList<String> managers = new ArrayList<>();
            claim.getPermissions(builders, containers, accessors, managers);
            List<Component> permissionsLore = new ArrayList<>();
            permissionsLore.add(Component.empty());
            this.getPermissionLore(permissionsLore, LoreComponents.CLAIM_PERMISSIONS_BUILDERS, builders);
            this.getPermissionLore(permissionsLore, LoreComponents.CLAIM_PERMISSIONS_CONTAINERS, containers);
            this.getPermissionLore(permissionsLore, LoreComponents.CLAIM_PERMISSIONS_ACCESSORS, accessors);
            this.getPermissionLore(permissionsLore, LoreComponents.CLAIM_PERMISSIONS_MANAGERS, managers);
            permissionsLore.add(Component.empty());
            this.setItem(GuiItem.builder().slot(slot + 1).material(Material.KNOWLEDGE_BOOK)
                    .customModelData(3022)
                    .name(NameComponents.CLAIM_PERMISSIONS)
                    .lore(permissionsLore)
                    .build());

            this.setItem(GuiItem.builder().slot(slot + 2).material(Material.PAPER)
                    .customModelData(3023)
                    .name(NameComponents.CLAIM_NAME)
                    .lore(Component.empty(), LoreComponents.CLAIM_NAME_CLICK)
                    .onLeftClick(() -> new AnvilGUI.Builder().plugin(PACProfile.getInstance())
                            .title(Messages.CLAIM_NAME_TITLE)
                            .text(PACProfile.getInstance().playerData.getClaimName(this.player.getUniqueId(), claimId))
                            .itemLeft(new ItemStack(Material.PAPER))
                            .onClick((anvilSlot, stateSnapshot) -> {
                                if (anvilSlot != AnvilGUI.Slot.OUTPUT) {
                                    return List.of();
                                }
                                PACProfile.getInstance().playerData.setClaimName(stateSnapshot.getPlayer().getUniqueId(), claimId, stateSnapshot.getText());
                                new ClaimsGui(this.viewer, this.player, this.page, this.maxPages, this.orderSet.currentOrder()).open();
                                return List.of(AnvilGUI.ResponseAction.close());
                            })
                            .open(this.viewer)
                    )
                    .build());
        }

        this.setItem(GuiItem.builder().slot(51).material(Material.HOPPER)
                .customModelData(3024)
                .name(NameComponents.CLAIMS_ORDER)
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
                        new ClaimsGui(this.viewer, this.player, this.page - 1, this.maxPages,
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
        if (claimCount > maxClaimOnPage) {
            this.setItem(GuiItem.builder().slot(53).material(Material.ARROW)
                    .customModelData(3003)
                    .name(NameComponents.PAGE_NEXT)
                    .onLeftClick(() -> new ClaimsGui(this.viewer, this.player, this.page + 1, this.maxPages,
                            this.orderSet.currentOrder()).open())
                    .build());
        }
    }

    private String getName(String claimId) {
        String name = PACProfile.getInstance().playerData.getClaimName(this.player.getUniqueId(), claimId);
        return name.isEmpty() ? Messages.NOT_DEFINED : name;
    }

    private Component getNameLore(String claimId) {
        return Component.text(this.getName(claimId), TextColor.color(0xFFFF55), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
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
            components.add(Component.text(subListStr, TextColor.color(0xFFFF55), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false));
        }
    }
}
