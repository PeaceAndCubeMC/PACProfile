package fr.peaceandcube.pacprofile.module.onlineplayers;

import fr.peaceandcube.pacbirthday.PACBirthday;
import fr.peaceandcube.pacbirthday.util.LocalizedMonth;
import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.gui.ConfirmationGui;
import fr.peaceandcube.pacprofile.gui.GuiContext;
import fr.peaceandcube.pacprofile.gui.PaginatedGui;
import fr.peaceandcube.pacprofile.gui.ProfileGui;
import fr.peaceandcube.pacprofile.gui.dialog.DialogItem;
import fr.peaceandcube.pacprofile.gui.dialog.TextInputDialog;
import fr.peaceandcube.pacprofile.gui.item.GuiItem;
import fr.peaceandcube.pacprofile.gui.item.LoreProvider;
import fr.peaceandcube.pacprofile.lang.TranslationManager;
import fr.peaceandcube.pacprofile.logging.Logger;
import fr.peaceandcube.pacprofile.module.Module;
import fr.peaceandcube.pacprofile.order.Order;
import fr.peaceandcube.pacprofile.order.OrderSet;
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
    private final Module module;
    private final PlayerData playerData;
    private List<Player> playerList;

    public OnlinePlayersGui(Module module, Player viewer, Player player, int page, int maxPages) {
        this(module, viewer, player, page, maxPages, Order.DEFAULT);
    }

    public OnlinePlayersGui(Module module, Player viewer, Player player, int page, int maxPages, Order order) {
        super(Component.text(module.translate("online_players_title").formatted(player.getName(), Math.max(1, page), Math.max(1, maxPages))),
                viewer,
                player,
                page,
                maxPages,
                new OrderSet(order, Order.DEFAULT, Order.NAME_AZ, Order.NAME_ZA)
        );
        this.module = module;
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
            lore.add(LoreProvider.line(TranslationManager.translate("profile_birthday"), birthday));
            if (!player.getUniqueId().equals(this.player.getUniqueId())) {
                lore.add(LoreProvider.line(module.translate("online_player_trust_count_1"), trustCount)
                        .append(LoreProvider.line(module.translate("online_player_trust_count_2")))
                );
                lore.add(LoreProvider.line(module.translate("online_player_mail_sent_1"), mailSentCount)
                        .append(LoreProvider.line(module.translate("online_player_mail_sent_2")))
                );
                lore.add(Component.empty());
                if (PACProfile.getInstance().config.isOnlinePlayerTeleportationEnabled()) {
                    lore.add(LoreProvider.line(module.translate("online_player_click")));
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
            notesLore.add(LoreProvider.line(module.translate("online_player_notes_click_left")));
            notesLore.add(LoreProvider.line(module.translate("online_player_notes_click_right")));
            this.setItem(GuiItem.builder().slot(slot + 1).material(Material.PAPER)
                    .customModelData(3031)
                    .name(module.translate("online_player_notes"), 0x00AA00)
                    .lore(notesLore)
                    .onLeftClick(context -> TextInputDialog.builder()
                            .player(context.viewer())
                            .title(module.translate("online_players"), 0x55FF55)
                            .bodyItem(new DialogItem(Material.PLAYER_HEAD, 3030, player))
                            .bodyText(player.getName())
                            .inputLabel(module.translate("online_player_notes_title"))
                            .inputValue(PACProfile.getInstance().playerData.getPlayerNotes(context.player().getUniqueId(), playerUuid))
                            .inputSize(8, 80)
                            .onConfirm(newValue -> {
                                Logger.debug("%s edited notes for player %s".formatted(context.player().getName(), player.getName()));
                                PACProfile.getInstance().playerData.setPlayerNotes(context.player().getUniqueId(), playerUuid, newValue);
                                new OnlinePlayersGui(module, context.viewer(), context.player(), context.page(), context.maxPages(), context.orderSet().currentOrder()).open();
                            })
                            .build()
                            .show())
                    .onRightClick(context -> new ConfirmationGui(context.viewer(), context.player(), this, () -> {
                        Logger.debug("%s removed notes for player %s".formatted(context.player().getName(), player.getName()));
                        PACProfile.getInstance().playerData.removePlayerNotes(context.player().getUniqueId(), player.getUniqueId().toString());
                        new OnlinePlayersGui(module, context.viewer(), context.player(), context.page(), context.maxPages(), context.orderSet().currentOrder()).open();
                    }).open())
                    .build());
        }

        this.setItem(GuiItem.builder().slot(51).material(Material.HOPPER)
                .customModelData(3013)
                .name(module.translate("online_players_order"), 0x00AA00)
                .lore(Component.empty(), LoreProvider.line(TranslationManager.translate("order_by"),
                        TranslationManager.translate("order_" + this.orderSet().currentOrder().getName())))
                .lore(Component.empty(), LoreProvider.line(TranslationManager.translate("order_click")))
                .onLeftClick(context -> {
                    context.orderSet().next();
                    context.fillInventory();
                })
                .build());

        this.setItem(GuiItem.builder().slot(45).material(Material.ARROW)
                .customModelData(3002)
                .name(TranslationManager.translate("page_previous"), 0xFF55FF)
                .onLeftClick(context -> {
                    if (context.page() == 1) {
                        new ProfileGui(context.viewer(), context.player()).open();
                    } else {
                        new OnlinePlayersGui(
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
                .name(TranslationManager.translate("exit"), 0xFF5555)
                .onLeftClick(GuiContext::close)
                .build());

        // if it's not the last page
        if (playerCount > maxPlayersOnPage) {
            this.setItem(GuiItem.builder().slot(53).material(Material.ARROW)
                    .customModelData(3003)
                    .name(TranslationManager.translate("page_next"), 0xFF55FF)
                    .onLeftClick(context -> new OnlinePlayersGui(
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

    private String getBirthday(String playerUuid) {
        String birthday = PACBirthday.birthdaysFile.getBirthday(playerUuid);
        if (birthday != null) {
            String day = birthday.substring(0, 2);
            String month = LocalizedMonth.fromNumber(Integer.parseInt(birthday.substring(3, 5))).getLocalizedName();
            return day + " " + month;
        }
        return TranslationManager.translate("not_defined");
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
        return List.of(Component.text(TranslationManager.translate("not_defined"), NamedTextColor.YELLOW, TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false));
    }
}
