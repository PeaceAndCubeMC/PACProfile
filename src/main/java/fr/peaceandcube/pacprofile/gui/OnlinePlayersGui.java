package fr.peaceandcube.pacprofile.gui;

import fr.peaceandcube.pacbirthday.PACBirthday;
import fr.peaceandcube.pacbirthday.util.LocalizedMonth;
import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.gui.dialog.DialogItem;
import fr.peaceandcube.pacprofile.gui.dialog.TextInputDialog;
import fr.peaceandcube.pacprofile.item.GuiItem;
import fr.peaceandcube.pacprofile.logging.Logger;
import fr.peaceandcube.pacprofile.order.Order;
import fr.peaceandcube.pacprofile.order.OrderSet;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.util.Messages;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.ClaimPermission;
import me.ryanhamshire.GriefPrevention.PlayerData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OnlinePlayersGui extends PaginatedGui {
    private final PlayerData playerData;
    private List<Player> playerList;

    public OnlinePlayersGui(Player viewer, Player player, int page, int maxPages) {
        this(viewer, player, page, maxPages, Order.DEFAULT);
    }

    public OnlinePlayersGui(Player viewer, Player player, int page, int maxPages, Order order) {
        super(Component.text(Messages.ONLINE_PLAYERS_TITLE.formatted(player.getName(), Math.max(1, page), Math.max(1, maxPages))),
                viewer,
                player,
                page,
                maxPages,
                new OrderSet(order, Order.DEFAULT, Order.NAME_AZ, Order.NAME_ZA)
        );
        this.playerData = PACProfile.getGriefPrevention().dataStore.getPlayerData(this.player.getUniqueId());
        this.playerList = Bukkit.getOnlinePlayers().stream().filter(p -> !PACProfile.getEssentials().getUser(p).isVanished()).collect(Collectors.toList());
        this.fillInventory();
        Bukkit.getPluginManager().registerEvents(this, PACProfile.getInstance());
    }

    @Override
    public void fillInventory() {
        this.items.clear();

        switch (this.orderSet().currentOrder()) {
            case DEFAULT ->
                    this.playerList = Bukkit.getOnlinePlayers().stream().filter(p -> !PACProfile.getEssentials().getUser(p).isVanished()).collect(Collectors.toList());
            case NAME_AZ -> this.playerList.sort(Comparator.comparing(Player::getName));
            case NAME_ZA -> this.playerList.sort(Comparator.comparing(Player::getName).reversed());
        }

        int playerCount = this.playerList.size();
        int maxPlayersOnPage = this.page() * 10;
        int playersOnPage = playerCount >= maxPlayersOnPage ? maxPlayersOnPage : (playerCount - (this.page() - 1) * 10);

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

            List<Component> lore = new ArrayList<>();
            lore.add(Component.empty());
            lore.add(LoreComponents.PROFILE_BIRTHDAY.append(Component.text(birthday, NamedTextColor.YELLOW, TextDecoration.BOLD)));
            if (!player.getUniqueId().equals(this.player.getUniqueId())) {
                lore.add(LoreComponents.ONLINE_PLAYER_TRUST_COUNT_1
                        .append(Component.text(trustCount, NamedTextColor.YELLOW, TextDecoration.BOLD))
                        .append(LoreComponents.ONLINE_PLAYER_TRUST_COUNT_2)
                );
                lore.add(LoreComponents.ONLINE_PLAYER_MAIL_SENT_1
                        .append(Component.text(mailSentCount, NamedTextColor.YELLOW, TextDecoration.BOLD))
                        .append(LoreComponents.ONLINE_PLAYER_MAIL_SENT_2)
                );
                lore.add(Component.empty());
                if (PACProfile.getInstance().config.isOnlinePlayerTeleportationEnabled()) {
                    lore.add(LoreComponents.ONLINE_PLAYER_CLICK);
                }
            }
            this.setItem(GuiItem.builder().slot(slot).material(Material.PLAYER_HEAD).player(player)
                    .customModelData(3030)
                    .name(player.getName(), 0x5555FF)
                    .lore(lore)
                    .onLeftClick(context -> {
                        if (PACProfile.getInstance().config.isOnlinePlayerTeleportationEnabled()) {
                            if (!player.getUniqueId().equals(context.player().getUniqueId())) {
                                Logger.debug("%s asked for teleportation to player %s".formatted(context.viewer().getName(), player.getName()));
                                context.dispatchCommand("tpa " + player.getName());
                                context.close();
                            }
                        }
                    })
                    .build());

            List<Component> notesLore = new ArrayList<>();
            notesLore.add(Component.empty());
            notesLore.addAll(getNotesLore(playerUuid));
            notesLore.add(Component.empty());
            notesLore.add(LoreComponents.ONLINE_PLAYER_NOTES_CLICK_LEFT);
            notesLore.add(LoreComponents.ONLINE_PLAYER_NOTES_CLICK_RIGHT);
            this.setItem(GuiItem.builder().slot(slot + 1).material(Material.PAPER)
                    .customModelData(3031)
                    .name(Messages.HOME_NOTES, 0x00AA00)
                    .lore(notesLore)
                    .onLeftClick(context -> TextInputDialog.builder()
                            .player(context.viewer())
                            .title(Messages.ONLINE_PLAYERS, 0x55FF55)
                            .bodyItem(new DialogItem(Material.PLAYER_HEAD, 3030, player))
                            .bodyText(player.getName())
                            .inputLabel(Messages.ONLINE_PLAYER_NOTES_TITLE)
                            .inputValue(PACProfile.getInstance().playerData.getPlayerNotes(context.player().getUniqueId(), playerUuid))
                            .inputSize(8, 80)
                            .onConfirm(newValue -> {
                                Logger.debug("%s edited notes for player %s".formatted(context.player().getName(), player.getName()));
                                PACProfile.getInstance().playerData.setPlayerNotes(context.player().getUniqueId(), playerUuid, newValue);
                                new OnlinePlayersGui(context.viewer(), context.player(), context.page(), context.maxPages(), context.orderSet().currentOrder()).open();
                            })
                            .build()
                            .show())
                    .onRightClick(context -> new ConfirmationGui(context.viewer(), context.player(), this, () -> {
                        Logger.debug("%s removed notes for player %s".formatted(context.player().getName(), player.getName()));
                        PACProfile.getInstance().playerData.removePlayerNotes(context.player().getUniqueId(), player.getUniqueId().toString());
                        new OnlinePlayersGui(context.viewer(), context.player(), context.page(), context.maxPages(), context.orderSet().currentOrder()).open();
                    }).open())
                    .build());
        }

        this.setItem(GuiItem.builder().slot(51).material(Material.HOPPER)
                .customModelData(3013)
                .name(Messages.ONLINE_PLAYERS_ORDER, 0x00AA00)
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
                        new OnlinePlayersGui(
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
        if (playerCount > maxPlayersOnPage) {
            this.setItem(GuiItem.builder().slot(53).material(Material.ARROW)
                    .customModelData(3003)
                    .name(Messages.PAGE_NEXT, 0xFF55FF)
                    .onLeftClick(context -> new OnlinePlayersGui(
                            context.viewer(),
                            context.player(),
                            context.page() + 1,
                            context.maxPages(),
                            context.orderSet().currentOrder()
                    ).open())
                    .build());
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

    private List<TextComponent> getNotesLore(String playerUuid) {
        String notes = PACProfile.getInstance().playerData.getPlayerNotes(this.player.getUniqueId(), playerUuid);
        if (!notes.isEmpty()) {
            return Stream.of(notes.split("\\R"))
                    .map(note -> Component.text(note, NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false))
                    .toList();
        }
        return List.of(Component.text(Messages.NOT_DEFINED, NamedTextColor.YELLOW, TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false));
    }
}
