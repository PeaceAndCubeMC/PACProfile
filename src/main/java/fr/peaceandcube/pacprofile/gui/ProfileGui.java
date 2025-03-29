package fr.peaceandcube.pacprofile.gui;

import fr.peaceandcube.pacbirthday.PACBirthday;
import fr.peaceandcube.pacbirthday.util.LocalizedMonth;
import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.item.GuiItem;
import fr.peaceandcube.pacprofile.statistic.Statistic;
import fr.peaceandcube.pacprofile.statistic.Statistics;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.util.Messages;
import me.ryanhamshire.GriefPrevention.PlayerData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.scoreboard.Objective;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProfileGui extends UnmodifiableGui {
    private final PlayerData playerData;
    private int maxHomePages;
    private int maxClaimPages;
    private int maxOnlinePlayersPages;
    private int maxWarpsPages;
    private boolean baseStatistics;

    public ProfileGui(Player viewer, Player player) {
        super(6, Component.text(String.format(Messages.PROFILE, player.getName())), viewer, player);
        this.playerData = PACProfile.getGriefPrevention().dataStore.getPlayerData(this.player.getUniqueId());
        this.baseStatistics = false;
        this.fillInventory();
        Bukkit.getPluginManager().registerEvents(this, PACProfile.getInstance());
    }

    @Override
    protected void fillInventory() {
        this.items.clear();

        String rank = this.getRank();
        String rankExpiration = this.getRankExpiration();
        String nickname = this.user.getNickname();
        String birthday = this.getBirthday();
        String joinDate = this.getFirstPlayed();
        List<Component> playerLore = new ArrayList<>();
        playerLore.add(Component.empty());
        playerLore.add(LoreComponents.PROFILE_RANK.append(Component.text(rank, TextColor.color(0xFFFF55), TextDecoration.BOLD)));
        if (rankExpiration != null) {
            playerLore.add(LoreComponents.PROFILE_RANK_EXPIRATION.append(Component.text(rankExpiration, TextColor.color(0xFFFF55), TextDecoration.BOLD)));
        }
        playerLore.add(Component.empty());
        if (nickname != null) {
            playerLore.add(LoreComponents.PROFILE_NICKNAME.append(Component.text(nickname, TextColor.color(0xFFFF55), TextDecoration.BOLD)));
        }
        playerLore.add(LoreComponents.PROFILE_BIRTHDAY.append(Component.text(birthday, TextColor.color(0xFFFF55), TextDecoration.BOLD)));
        playerLore.add(Component.empty());
        playerLore.add(LoreComponents.PROFILE_JOIN_DATE.append(Component.text(joinDate, TextColor.color(0xFFFF55), TextDecoration.BOLD)));
        this.setItem(GuiItem.builder().slot(4).material(Material.PLAYER_HEAD).player(this.player)
                .customModelData(3004)
                .name(this.player.getName(), 0x55FFFF)
                .lore(playerLore)
                .build());

        List<Component> statsLore = new ArrayList<>();
        statsLore.add(this.baseStatistics ? LoreComponents.STATISTICS_BASE : LoreComponents.STATISTICS_CURRENT);
        statsLore.add(Component.empty());
        for (Statistic statistic : Statistics.ALL) {
            if (PACProfile.getInstance().config.isStatisticEnabled(statistic.getName())) {
                statsLore.add(getStatisticComponent(statistic));
            }
        }
        statsLore.add(Component.empty());
        statsLore.add(this.baseStatistics ? LoreComponents.STATISTICS_CLICK_CURRENT : LoreComponents.STATISTICS_CLICK_BASE);
        this.setItem(GuiItem.builder().slot(9).material(Material.ENCHANTED_BOOK)
                .customModelData(3004)
                .name(Messages.STATISTICS, 0xFF55FF)
                .lore(statsLore)
                .onLeftClick(() -> {
                    this.baseStatistics = !this.baseStatistics;
                    this.fillInventory();
                })
                .build());

        this.setItem(GuiItem.builder().slot(17).material(Material.COMPARATOR)
                .customModelData(3004)
                .name(Messages.SETTINGS, 0x555555)
                .lore(Component.empty(), LoreComponents.SETTINGS_CLICK)
                .onLeftClick(() -> new SettingsGui(this.viewer, this.player).open())
                .build());

        double coinCount = this.user.getMoney().doubleValue();
        List<Component> coinLore = new ArrayList<>();
        coinLore.add(Component.empty());
        coinLore.add(LoreComponents.COINS_NUMBER.append(Component.text(coinCount, TextColor.color(0xFFFF55), TextDecoration.BOLD)));
        if (!PACProfile.getInstance().config.getCommandOnClickCoins().isBlank()) {
            coinLore.add(Component.empty());
            coinLore.add(LoreComponents.COINS_CLICK);
        }
        this.setItem(GuiItem.builder().slot(20).material(Material.SUNFLOWER)
                .customModelData(3004)
                .name(Messages.COINS, 0xFFAA00)
                .lore(coinLore)
                .onLeftClick(() -> {
                    if (!PACProfile.getInstance().config.getCommandOnClickCoins().isBlank()) {
                        this.dispatchCommand(PACProfile.getInstance().config.getCommandOnClickCoins());
                        this.inv.close();
                    }
                })
                .build());

        Objective objective = this.player.getScoreboard().getObjective(PACProfile.getInstance().config.getHeadTicketsScoreboard());
        int headTicketCount = objective != null ? objective.getScore(this.player.getName()).getScore() : 0;
        List<Component> headTicketLore = new ArrayList<>();
        headTicketLore.add(Component.empty());
        headTicketLore.add(LoreComponents.HEAD_TICKETS_NUMBER.append(Component.text(headTicketCount, TextColor.color(0xFFFF55), TextDecoration.BOLD)));
        if (!PACProfile.getInstance().config.getCommandOnClickHeadTickets().isBlank()) {
            headTicketLore.add(Component.empty());
            headTicketLore.add(LoreComponents.HEAD_TICKETS_CLICK);
        }
        this.setItem(GuiItem.builder().slot(22).material(Material.NAME_TAG)
                .customModelData(3004)
                .name(Messages.HEAD_TICKETS, 0x00AAAA)
                .lore(headTicketLore)
                .onLeftClick(() -> {
                    if (!PACProfile.getInstance().config.getCommandOnClickHeadTickets().isBlank()) {
                        this.dispatchCommand(PACProfile.getInstance().config.getCommandOnClickHeadTickets());
                        this.inv.close();
                    }
                })
                .build());

        int mailCount = this.user.getMailAmount();
        int unreadMailCount = this.user.getUnreadMailAmount();
        List<Component> mailLore = new ArrayList<>();
        mailLore.add(Component.empty());
        mailLore.add(LoreComponents.MAILS_TOTAL.append(Component.text(mailCount, TextColor.color(0xFFFF55), TextDecoration.BOLD)));
        mailLore.add(LoreComponents.MAILS_UNREAD.append(Component.text(unreadMailCount, TextColor.color(0xFFFF55), TextDecoration.BOLD)));
        if (!PACProfile.getInstance().config.getCommandOnClickMails().isBlank()) {
            mailLore.add(Component.empty());
            mailLore.add(LoreComponents.MAILS_CLICK);
        }
        this.setItem(GuiItem.builder().slot(24).material(Material.WRITABLE_BOOK)
                .customModelData(3004)
                .name(Messages.MAILS, 0xAA00AA)
                .lore(mailLore)
                .onLeftClick(() -> {
                    if (!PACProfile.getInstance().config.getCommandOnClickMails().isBlank()) {
                        this.dispatchCommand(PACProfile.getInstance().config.getCommandOnClickMails());
                        this.inv.close();
                    }
                })
                .build());

        int totalHomeCount = PACProfile.getEssentials().getSettings().getHomeLimit(this.user);
        int usedHomeCount = this.user.getHomes().size();
        int remainingHomeCount = Math.max(0, totalHomeCount - usedHomeCount);
        this.maxHomePages = (int) Math.ceil(usedHomeCount / 10.0f);
        List<Component> homesLore = List.of(
                Component.empty(),
                LoreComponents.HOMES_TOTAL.append(Component.text(usedHomeCount, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                LoreComponents.HOMES_REMAINING.append(Component.text(remainingHomeCount, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                LoreComponents.HOMES_MAX_AVAILABLE.append(Component.text(totalHomeCount, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                Component.empty(),
                LoreComponents.HOMES_CLICK
        );
        this.setItem(GuiItem.builder().slot(28).material(Material.RED_BED)
                .customModelData(3004)
                .name(Messages.HOMES, 0x5555FF)
                .lore(homesLore)
                .onLeftClick(() -> new HomesGui(this.viewer, this.player, 1, this.maxHomePages).open())
                .build());

        int totalClaimCount = this.playerData.getClaims().size();
        int remainingClaimBlocks = this.playerData.getRemainingClaimBlocks();
        int accruedClaimBlocks = this.playerData.getAccruedClaimBlocks();
        int bonusClaimBlocks = this.playerData.getBonusClaimBlocks();
        int totalClaimsBlocks = accruedClaimBlocks + bonusClaimBlocks;
        int usedClaimBlocks = totalClaimsBlocks - remainingClaimBlocks;
        int blocksAccruedPerHour = PACProfile.getGriefPrevention().config_claims_blocksAccruedPerHour_default;
        this.maxClaimPages = (int) Math.ceil(totalClaimCount / 10.0f);
        List<Component> claimsLore = List.of(
                Component.empty(),
                LoreComponents.CLAIMS_TOTAL.append(Component.text(totalClaimCount, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                LoreComponents.CLAIMS_CB_USED.append(Component.text(usedClaimBlocks, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                LoreComponents.CLAIMS_CB_REMAINING.append(Component.text(remainingClaimBlocks, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                Component.empty(),
                LoreComponents.CLAIMS_CB_ACCRUED.append(Component.text(accruedClaimBlocks, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                LoreComponents.CLAIMS_CB_BONUS.append(Component.text(bonusClaimBlocks, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                LoreComponents.CLAIMS_CB_TOTAL.append(Component.text(totalClaimsBlocks, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                Component.empty(),
                LoreComponents.CLAIMS_CB_PER_HOUR.append(Component.text(blocksAccruedPerHour, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                Component.empty(),
                LoreComponents.CLAIMS_CLICK
        );
        this.setItem(GuiItem.builder().slot(30).material(Material.GOLDEN_SHOVEL)
                .customModelData(3004)
                .name(Messages.CLAIMS, 0x00AA00)
                .lore(claimsLore)
                .onLeftClick(() -> new ClaimsGui(this.viewer, this.player, 1, this.maxClaimPages).open())
                .build());

        int onlinePlayersCount = Bukkit.getOnlinePlayers().stream().filter(p -> !PACProfile.getEssentials().getUser(p).isVanished()).toList().size();
        this.maxOnlinePlayersPages = (int) Math.ceil(onlinePlayersCount / 10.0f);
        List<Component> onlinePlayersLore = List.of(
                Component.empty(),
                LoreComponents.ONLINE_PLAYERS_COUNT.append(Component.text(onlinePlayersCount, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                Component.empty(),
                LoreComponents.ONLINE_PLAYERS_CLICK
        );
        this.setItem(GuiItem.builder().slot(32).material(Material.PLAYER_HEAD)
                .customModelData(3005)
                .name(Messages.ONLINE_PLAYERS, 0x55FF55)
                .lore(onlinePlayersLore)
                .onLeftClick(() -> new OnlinePlayersGui(this.viewer, this.player, 1, this.maxOnlinePlayersPages).open())
                .build());

        this.maxWarpsPages = (int) Math.ceil(PACProfile.getInstance().config.getWarps().size() / 35.0f);
        this.setItem(GuiItem.builder().slot(34).material(Material.ENDER_PEARL)
                .customModelData(3004)
                .name(Messages.WARPS, 0xFFFF55)
                .lore(Component.empty(), LoreComponents.WARPS_CLICK)
                .onLeftClick(() -> new WarpsGui(this.viewer, this.player, 1, this.maxWarpsPages).open())
                .build());

        if (!PACProfile.getInstance().config.getCommandOnClickRules().isBlank()) {
            this.setItem(GuiItem.builder().slot(45).material(Material.KNOWLEDGE_BOOK)
                    .customModelData(3004)
                    .name(Messages.RULES, 0xFF55FF)
                    .lore(Component.empty(), LoreComponents.RULES_CLICK)
                    .onLeftClick(() -> {
                        this.dispatchCommand(PACProfile.getInstance().config.getCommandOnClickRules());
                        this.inv.close();
                    })
                    .build());
        }

        if (!PACProfile.getInstance().config.getCommandOnClickLinks().isBlank()) {
            this.setItem(GuiItem.builder().slot(46).material(Material.CHAIN)
                    .customModelData(3004)
                    .name(Messages.LINKS, 0xFF55FF)
                    .lore(Component.empty(), LoreComponents.LINKS_CLICK)
                    .onLeftClick(() -> {
                        this.dispatchCommand(PACProfile.getInstance().config.getCommandOnClickLinks());
                        this.inv.close();
                    })
                    .build());
        }

        if (!PACProfile.getInstance().config.getCommandOnClickDynmap().isBlank()) {
            this.setItem(GuiItem.builder().slot(47).material(Material.MAP)
                    .customModelData(3004)
                    .name(Messages.DYNMAP, 0xFF55FF)
                    .lore(Component.empty(), LoreComponents.DYNMAP_CLICK)
                    .onLeftClick(() -> {
                        this.dispatchCommand(PACProfile.getInstance().config.getCommandOnClickDynmap());
                        this.inv.close();
                    })
                    .build());
        }

        this.setItem(GuiItem.builder().slot(53).material(Material.BARRIER)
                .customModelData(3002)
                .name(Messages.EXIT, 0xFF5555)
                .onLeftClick(this.inv::close)
                .build());

        this.fillStainedGlassPanes();
    }

    private void fillStainedGlassPanes() {
        GuiItem.Builder builder = GuiItem.builder().customModelData(3001).hideTooltip();
        this.setItem(builder.slot(0).material(Material.RED_STAINED_GLASS_PANE).build());
        this.setItem(builder.slot(1).material(Material.RED_STAINED_GLASS_PANE).build());
        this.setItem(builder.slot(2).material(Material.RED_STAINED_GLASS_PANE).build());
        this.setItem(builder.slot(3).material(Material.ORANGE_STAINED_GLASS_PANE).build());
        this.setItem(builder.slot(5).material(Material.ORANGE_STAINED_GLASS_PANE).build());
        this.setItem(builder.slot(6).material(Material.LIME_STAINED_GLASS_PANE).build());
        this.setItem(builder.slot(7).material(Material.LIME_STAINED_GLASS_PANE).build());
        this.setItem(builder.slot(8).material(Material.LIME_STAINED_GLASS_PANE).build());
    }

    private String getRank() {
        User user = PACProfile.getLuckPerms().getUserManager().getUser(this.player.getUniqueId());
        if (user != null) {
            Group group = PACProfile.getLuckPerms().getGroupManager().getGroup(user.getPrimaryGroup());
            if (group != null && group.getDisplayName() != null) {
                return group.getDisplayName();
            }
        }
        return Messages.NOT_DEFINED;
    }

    private String getRankExpiration() {
        User user = PACProfile.getLuckPerms().getUserManager().getUser(this.player.getUniqueId());
        if (user != null) {
            for (Node node : user.getNodes()) {
                if (node.getKey().equals("group." + user.getPrimaryGroup())) {
                    if (node.getExpiry() != null) {
                        long timestamp = node.getExpiry().toEpochMilli();
                        SimpleDateFormat formatter = new SimpleDateFormat(PACProfile.getInstance().config.getDateFormat());
                        return formatter.format(new Date(timestamp));
                    }
                }
            }
        }
        return null;
    }

    private String getBirthday() {
        String birthday = PACBirthday.birthdaysFile.getBirthday(this.player.getUniqueId().toString());
        if (birthday != null) {
            String day = birthday.substring(0, 2);
            String month = LocalizedMonth.fromNumber(Integer.parseInt(birthday.substring(3, 5))).getLocalizedName();
            return day + " " + month;
        }
        return Messages.NOT_DEFINED;
    }

    private String getFirstPlayed() {
        long timestamp = this.player.getFirstPlayed();
        return new SimpleDateFormat(PACProfile.getInstance().config.getDateFormat()).format(new Date(timestamp));
    }

    private Component getStatisticComponent(Statistic statistic) {
        double value = this.baseStatistics ? statistic.getBaseValue(player) : statistic.getCurrentValue(player);
        double diff = Math.round((statistic.getCurrentValue(player) - statistic.getBaseValue(player)) * 100.0) / 100.0;
        Component component = statistic.getTextComponent().append(Component.text(value, TextColor.color(0xFFFF55), TextDecoration.BOLD));
        if (!this.baseStatistics && diff != 0) {
            TextColor diffColor = diff > 0 ? TextColor.color(0x55FF55) : TextColor.color(0xFF5555);
            // if the difference is positive, add a plus sign
            component = component.append(Component.text(" (" + (diff > 0 ? "+" : "") + diff + ")", diffColor));
        }
        return component;
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e) {
        if (e.getInventory().equals(this.inv)) {
            PACProfile.getInstance().playerData.removeOutdatedHomes(this.player.getUniqueId(), this.user.getHomes());
            PACProfile.getInstance().playerData.removeOutdatedClaims(this.player.getUniqueId(), this.playerData.getClaims());
        }
    }
}
