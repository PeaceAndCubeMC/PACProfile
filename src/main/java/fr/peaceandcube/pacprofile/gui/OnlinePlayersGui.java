package fr.peaceandcube.pacprofile.gui;

import fr.peaceandcube.pacbirthday.PACBirthday;
import fr.peaceandcube.pacbirthday.util.LocalizedMonth;
import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.order.Order;
import fr.peaceandcube.pacprofile.order.OrderSet;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.text.NameComponents;
import fr.peaceandcube.pacprofile.util.Messages;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.ClaimPermission;
import me.ryanhamshire.GriefPrevention.PlayerData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

public class OnlinePlayersGui extends UnmodifiableGui {
    private final Map<Integer, Player> PLAYERS_SLOTS = new LinkedHashMap<>();
    private final PlayerData playerData;
    private final int page;
    private final int maxPages;
    private List<Player> playerList;
    private final OrderSet orderSet;

    public OnlinePlayersGui(Player viewer, Player player, int page, int maxPages) {
        this(viewer, player, page, maxPages, Order.DEFAULT);
    }

    public OnlinePlayersGui(Player viewer, Player player, int page, int maxPages, Order order) {
        super(6, Component.text(String.format(Messages.ONLINE_PLAYERS_TITLE, player.getName(), Math.max(1, page), Math.max(1, maxPages))), viewer, player);
        this.playerData = PACProfile.getGriefPrevention().dataStore.getPlayerData(this.player.getUniqueId());
        this.page = Math.max(1, page);
        this.maxPages = maxPages;
        this.playerList = Bukkit.getOnlinePlayers().stream().filter(p -> !PACProfile.getEssentials().getUser(p).isVanished()).collect(Collectors.toList());
        this.orderSet = new OrderSet(order, Order.DEFAULT, Order.NAME_AZ, Order.NAME_ZA);
        this.fillInventory();
        Bukkit.getPluginManager().registerEvents(this, PACProfile.getInstance());
    }

    @Override
    protected void fillInventory() {
        switch (this.orderSet.currentOrder()) {
            case DEFAULT ->
                    this.playerList = Bukkit.getOnlinePlayers().stream().filter(p -> !PACProfile.getEssentials().getUser(p).isVanished()).collect(Collectors.toList());
            case NAME_AZ -> this.playerList.sort(Comparator.comparing(Player::getName));
            case NAME_ZA -> this.playerList.sort(Comparator.comparing(Player::getName).reversed());
        }

        int playerCount = this.playerList.size();
        int maxPlayersOnPage = this.page * 10;
        int playersOnPage = playerCount >= maxPlayersOnPage ? maxPlayersOnPage : (playerCount - (this.page - 1) * 10);

        for (int i = 0; i < Math.min(playersOnPage, 10); i++) {
            int index = maxPlayersOnPage - 10 + i;
            int slot;
            // first column of the page
            if (i < 5) {
                slot = i * 9 + 1;
                // second column of the page
            } else {
                slot = (i - 5) * 9 + 5;
            }

            Player player = this.playerList.get(index);
            String playerUuid = player.getUniqueId().toString();
            String birthday = getBirthday(playerUuid);
            int trustCount = getTrustCount(playerUuid);
            long mailSentCount = getMailSentCount(playerUuid);

            PLAYERS_SLOTS.put(slot, player);

            List<Component> lore = new ArrayList<>();
            lore.add(Component.empty());
            lore.add(LoreComponents.PROFILE_BIRTHDAY.append(Component.text(birthday, TextColor.color(0xFFFF55), TextDecoration.BOLD)));
            if (!player.getUniqueId().equals(this.player.getUniqueId())) {
                lore.add(LoreComponents.ONLINE_PLAYER_TRUST_COUNT_1
                        .append(Component.text(trustCount, TextColor.color(0xFFFF55), TextDecoration.BOLD))
                        .append(LoreComponents.ONLINE_PLAYER_TRUST_COUNT_2)
                );
                lore.add(LoreComponents.ONLINE_PLAYER_MAIL_SENT_1
                        .append(Component.text(mailSentCount, TextColor.color(0xFFFF55), TextDecoration.BOLD))
                        .append(LoreComponents.ONLINE_PLAYER_MAIL_SENT_2)
                );
                lore.add(Component.empty());
                lore.add(LoreComponents.ONLINE_PLAYER_CLICK);
            }
            this.setPlayerHead(slot, player, 3030, Component.text(player.getName(), TextColor.color(0x5555FF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false), lore);

            this.setItem(slot + 1, Material.PAPER, 3031, NameComponents.HOME_NOTES, List.of(
                    Component.empty(),
                    this.getNotesLore(playerUuid),
                    Component.empty(),
                    LoreComponents.ONLINE_PLAYER_NOTES_CLICK_LEFT,
                    LoreComponents.ONLINE_PLAYER_NOTES_CLICK_RIGHT
            ));
        }

        this.setItem(51, Material.HOPPER, 3013, NameComponents.ONLINE_PLAYERS_ORDER, List.of(
                Component.empty(),
                LoreComponents.ORDER_BY.append(this.orderSet.currentOrder().getText()),
                Component.empty(),
                LoreComponents.ORDER_CLICK
        ));

        this.setItem(45, Material.ARROW, 3002, NameComponents.PAGE_PREVIOUS);
        this.setItem(49, Material.BARRIER, 3002, NameComponents.EXIT);
        // if it's not the last page
        if (playerCount > maxPlayersOnPage) {
            this.setItem(53, Material.ARROW, 3003, NameComponents.PAGE_NEXT);
        }
    }

    private String getBirthday(String playerUuid) {
        String birthday = PACBirthday.birthdaysFile.getBirthday(playerUuid);
        if (birthday != null) {
            String day = birthday.substring(0, 2);
            String month = LocalizedMonth.fromNumber(Integer.parseInt(birthday.substring(3, 5))).getLocalizedName();
            return day + " " + month;
        }
        return Messages.NOT_DEFINED;
    }

    private int getTrustCount(String playerUuid) {
        int claimCount = this.playerData.getClaims().size();
        int trustCount = 0;
        for (int i = 0; i < claimCount; i++) {
            Claim claim = this.playerData.getClaims().get(i);
            ClaimPermission permission = claim.getPermission(playerUuid);
            if (permission != null) {
                trustCount++;
            }
        }
        return trustCount;
    }

    private long getMailSentCount(String playerUuid) {
        return this.user.getMailMessages().stream()
                .filter(mailMessage -> Objects.equals(mailMessage.getSenderUUID().toString(), playerUuid))
                .count();
    }

    private Component getNotesLore(String playerUuid) {
        String notes = PACProfile.getInstance().playerData.getPlayerNotes(this.player.getUniqueId(), playerUuid);
        if (!notes.isEmpty()) {
            return Component.text(notes, TextColor.color(0xFFFF55)).decoration(TextDecoration.ITALIC, false);
        }
        return Component.text(Messages.NOT_DEFINED, TextColor.color(0xFFFF55), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    }

    @Override
    protected void onSlotLeftClick(int slot) {
        // previous page
        if (slot == 45) {
            if (this.page == 1) {
                new ProfileGui(this.viewer, this.player).open();
            } else {
                new OnlinePlayersGui(this.viewer, this.player, this.page - 1, this.maxPages, this.orderSet.currentOrder()).open();
            }
        }

        // exit
        else if (slot == 49) {
            this.inv.close();
        }

        // next page
        else if (slot == 53) {
            int playerCount = this.playerList.size();
            int maxPlayerOnPage = this.page * 10;
            if (playerCount > maxPlayerOnPage) {
                new OnlinePlayersGui(this.viewer, this.player, this.page + 1, this.maxPages, this.orderSet.currentOrder()).open();
            }
        }

        // order
        else if (slot == 51) {
            this.orderSet.next();
            this.fillInventory();
        }

        // players
        else if (PLAYERS_SLOTS.containsKey(slot)) {
            if (!PLAYERS_SLOTS.get(slot).getUniqueId().equals(this.player.getUniqueId())) {
                this.dispatchCommand("tpa " + PLAYERS_SLOTS.get(slot).getName());
                this.inv.close();
            }
        }

        // player notes
        else if (PLAYERS_SLOTS.containsKey(slot - 1)) {
            new AnvilGUI.Builder().plugin(PACProfile.getInstance())
                    .title(Messages.ONLINE_PLAYER_NOTES_TITLE)
                    .text(PACProfile.getInstance().playerData.getPlayerNotes(this.player.getUniqueId(), PLAYERS_SLOTS.get(slot - 1).getUniqueId().toString()))
                    .itemLeft(new ItemStack(Material.PAPER))
                    .onClick((anvilSlot, stateSnapshot) -> {
                        if (anvilSlot != AnvilGUI.Slot.OUTPUT) {
                            return List.of();
                        }
                        PACProfile.getInstance().playerData.setPlayerNotes(stateSnapshot.getPlayer().getUniqueId(), PLAYERS_SLOTS.get(slot - 1).getUniqueId().toString(), stateSnapshot.getText());
                        new OnlinePlayersGui(this.viewer, this.player, this.page, this.maxPages, this.orderSet.currentOrder()).open();
                        return List.of(AnvilGUI.ResponseAction.close());
                    })
                    .open(this.viewer);
        }
    }

    @Override
    protected void onSlotRightClick(int slot) {
        // player notes
        if (PLAYERS_SLOTS.containsKey(slot - 1)) {
            new ConfirmationGui(this.viewer, this.player, this, () -> {
                PACProfile.getInstance().playerData.removePlayerNotes(this.player.getUniqueId(), PLAYERS_SLOTS.get(slot - 1).getUniqueId().toString());
                new OnlinePlayersGui(this.viewer, this.player, this.page, this.maxPages, this.orderSet.currentOrder()).open();
            }).open();
        }
    }
}
