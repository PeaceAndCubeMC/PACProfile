package fr.peaceandcube.pacprofile.gui;

import fr.peaceandcube.pacprofile.PACProfile;
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
    private final Map<Integer, String> CLAIM_SLOTS = new LinkedHashMap<>();
    private final PlayerData playerData;
    private final int page;
    private final int maxPages;
    private Vector<Claim> claims;
    private Order order;

    public ClaimsGui(Player viewer, Player player, int page, int maxPages) {
        this(viewer, player, page, maxPages, Order.DEFAULT);
    }

    public ClaimsGui(Player viewer, Player player, int page, int maxPages, Order order) {
        super(6, Component.text(String.format(Messages.CLAIMS_TITLE, player.getName(), Math.max(1, page), Math.max(1, maxPages))), viewer, player);
        this.playerData = PACProfile.getGriefPrevention().dataStore.getPlayerData(this.player.getUniqueId());
        this.page = Math.max(1, page);
        this.maxPages = maxPages;
        this.claims = this.playerData.getClaims();
        this.order = order;
        this.fillInventory();
        Bukkit.getPluginManager().registerEvents(this, PACProfile.getInstance());
    }

    @Override
    protected void fillInventory() {
        if (this.order == Order.DEFAULT) {
            this.claims = this.playerData.getClaims();
        } else if (this.order == Order.NAME_AZ) {
            this.claims.sort((claim1, claim2) -> this.getName(claim1.getID().toString()).compareToIgnoreCase(this.getName(claim2.getID().toString())));
        } else if (this.order == Order.NAME_ZA) {
            this.claims.sort((claim1, claim2) -> this.getName(claim2.getID().toString()).compareToIgnoreCase(this.getName(claim1.getID().toString())));
        } else if (this.order == Order.AREA_ASC) {
            this.claims.sort(Comparator.comparingInt(Claim::getArea));
        } else if (this.order == Order.AREA_DESC) {
            this.claims.sort(Comparator.comparingInt(Claim::getArea).reversed());
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

            CLAIM_SLOTS.put(slot, claimId);

            int customModelData = world.equals("world_nether") ? 3021 : 3020;
            this.setItem(slot, Material.GOLDEN_SHOVEL, customModelData, this.getNameLore(claimId), List.of(
                    Component.empty(),
                    LoreComponents.CLAIM_WORLD.append(Component.text(world, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                    LoreComponents.CLAIM_GREATER_CORNER.append(Component.text(greaterCorner.getBlockX() + " " + greaterCorner.getBlockY() + " " + greaterCorner.getBlockZ(), TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                    LoreComponents.CLAIM_LESSER_CORNER.append(Component.text(lesserCorner.getBlockX() + " " + lesserCorner.getBlockY() + " " + lesserCorner.getBlockZ(), TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                    Component.empty(),
                    LoreComponents.CLAIM_WIDTH.append(Component.text(width, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                    LoreComponents.CLAIM_LENGTH.append(Component.text(length, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                    LoreComponents.CLAIM_AREA.append(Component.text(area, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                    Component.empty()
            ));

            ArrayList<String> builders = new ArrayList<>();
            ArrayList<String> containers = new ArrayList<>();
            ArrayList<String> accessors = new ArrayList<>();
            ArrayList<String> managers = new ArrayList<>();
            claim.getPermissions(builders, containers, accessors, managers);
            List<Component> components = new ArrayList<>();
            components.add(Component.empty());
            this.getPermissionLore(components, LoreComponents.CLAIM_PERMISSIONS_BUILDERS, builders);
            this.getPermissionLore(components, LoreComponents.CLAIM_PERMISSIONS_CONTAINERS, containers);
            this.getPermissionLore(components, LoreComponents.CLAIM_PERMISSIONS_ACCESSORS, accessors);
            this.getPermissionLore(components, LoreComponents.CLAIM_PERMISSIONS_MANAGERS, managers);
            components.add(Component.empty());
            this.setItem(slot + 1, Material.KNOWLEDGE_BOOK, 3022, NameComponents.CLAIM_PERMISSIONS, components);

            this.setItem(slot + 2, Material.PAPER, 3023, NameComponents.CLAIM_NAME, List.of(
                    Component.empty(),
                    LoreComponents.CLAIM_NAME_CLICK
            ));
        }

        this.setItem(51, Material.HOPPER, 3024, NameComponents.CLAIMS_ORDER, List.of(
                Component.empty(),
                LoreComponents.ORDER_BY.append(this.order.getText()),
                Component.empty(),
                LoreComponents.ORDER_CLICK
        ));

        this.setItem(45, Material.ARROW, 3002, NameComponents.PAGE_PREVIOUS);
        this.setItem(49, Material.BARRIER, 3002, NameComponents.EXIT);
        // if it's not the last page
        if (claimCount > maxClaimOnPage) {
            this.setItem(53, Material.ARROW, 3003, NameComponents.PAGE_NEXT);
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

    @Override
    protected void onSlotLeftClick(int slot) {
        // previous page
        if (slot == 45) {
            if (this.page == 1) {
                new ProfileGui(this.viewer, this.player).open();
            } else {
                new ClaimsGui(this.viewer, this.player, this.page - 1, this.maxPages, this.order).open();
            }
        }

        // exit
        else if (slot == 49) {
            this.inv.close();
        }

        // next page
        else if (slot == 53) {
            int claimCount = this.playerData.getClaims().size();
            int maxClaimOnPage = this.page * 10;
            if (claimCount > maxClaimOnPage) {
                new ClaimsGui(this.viewer, this.player, this.page + 1, this.maxPages, this.order).open();
            }
        }

        // order
        else if (slot == 51) {
            this.order = this.order.next();
            this.fillInventory();
        }

        // claim names
        else if (CLAIM_SLOTS.containsKey(slot - 2)) {
            new AnvilGUI.Builder().plugin(PACProfile.getInstance())
                    .title(Messages.CLAIM_NAME_TITLE)
                    .text(PACProfile.getInstance().playerData.getClaimName(this.player.getUniqueId(), CLAIM_SLOTS.get(slot - 2)))
                    .itemLeft(new ItemStack(Material.PAPER))
                    .onComplete(((p, s) -> {
                        PACProfile.getInstance().playerData.setClaimName(p.getUniqueId(), CLAIM_SLOTS.get(slot - 2), s);
                        new ClaimsGui(this.viewer, this.player, this.page, this.maxPages).open();
                        return AnvilGUI.Response.close();
                    }))
                    .open(this.viewer);
        }
    }

    enum Order {
        DEFAULT(LoreComponents.ORDER_DEFAULT),
        NAME_AZ(LoreComponents.ORDER_NAME_AZ),
        NAME_ZA(LoreComponents.ORDER_NAME_ZA),
        AREA_ASC(LoreComponents.ORDER_AREA_ASC),
        AREA_DESC(LoreComponents.ORDER_AREA_DESC);

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
                case NAME_ZA -> AREA_ASC;
                case AREA_ASC -> AREA_DESC;
                case AREA_DESC -> DEFAULT;
            };
        }
    }
}
