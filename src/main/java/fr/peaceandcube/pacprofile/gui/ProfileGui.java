package fr.peaceandcube.pacprofile.gui;

import fr.peaceandcube.pacbirthday.data.BirthdayData;
import fr.peaceandcube.pacpi.date.LocalizedMonth;
import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.text.NameComponents;
import fr.peaceandcube.pacprofile.util.Messages;
import me.ryanhamshire.GriefPrevention.PlayerData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.scoreboard.Objective;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProfileGui extends UnmodifiableGui {
    private PlayerData playerData;
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
        String rank = this.getRank();
        String nickname = this.user.getNickname() != null ? this.user.getNickname() : Messages.NOT_DEFINED;
        String birthday = this.getBirthday();
        String joinDate = this.getFirstPlayed();
        this.setPlayerHead(4, this.player, 3004, Component.text(this.player.getName(), TextColor.color(0x55FFFF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false), List.of(
                Component.empty(),
                LoreComponents.PROFILE_RANK.append(Component.text(rank, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                Component.empty(),
                LoreComponents.PROFILE_NICKNAME.append(Component.text(nickname, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                LoreComponents.PROFILE_BIRTHDAY.append(Component.text(birthday, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                Component.empty(),
                LoreComponents.PROFILE_JOIN_DATE.append(Component.text(joinDate, TextColor.color(0xFFFF55), TextDecoration.BOLD))
        ));

        double[] statistics = this.getStatistics();
        Component statisticsComponent = this.baseStatistics ? LoreComponents.STATISTICS_BASE : LoreComponents.STATISTICS_CURRENT;
        Component clickComponent = this.baseStatistics ? LoreComponents.STATISTICS_CLICK_CURRENT : LoreComponents.STATISTICS_CLICK_BASE;
        this.setItem(9, Material.ENCHANTED_BOOK, 3004, NameComponents.STATISTICS, List.of(
                statisticsComponent,
                Component.empty(),
                LoreComponents.STATISTICS_HEALTH.append(Component.text(statistics[0], TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                LoreComponents.STATISTICS_MAX_HEALTH.append(Component.text(statistics[1], TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                Component.empty(),
                LoreComponents.STATISTICS_ARMOR.append(Component.text(statistics[2], TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                LoreComponents.STATISTICS_ARMOR_TOUGHNESS.append(Component.text(statistics[3], TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                Component.empty(),
                LoreComponents.STATISTICS_KNOCKBACK_RESISTANCE.append(Component.text(statistics[4], TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                Component.empty(),
                LoreComponents.STATISTICS_SPEED.append(Component.text(statistics[5], TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                Component.empty(),
                LoreComponents.STATISTICS_ATTACK_DAMAGE.append(Component.text(statistics[6], TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                LoreComponents.STATISTICS_ATTACK_SPEED.append(Component.text(statistics[7], TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                Component.empty(),
                LoreComponents.STATISTICS_LUCK.append(Component.text(statistics[8], TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                Component.empty(),
                clickComponent
        ));

        this.setItem(17, Material.COMPARATOR, 3004, NameComponents.SETTINGS, List.of(
                Component.empty(),
                LoreComponents.SETTINGS_CLICK
        ));

        double coinCount = this.user.getMoney().doubleValue();
        this.setItem(20, Material.SUNFLOWER, 3004, NameComponents.COINS, List.of(
                Component.empty(),
                LoreComponents.COINS_NUMBER.append(Component.text(coinCount, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                Component.empty(),
                LoreComponents.COINS_CLICK
        ));

        Objective objective = this.player.getScoreboard().getObjective(PACProfile.getInstance().config.getHeadTicketsScoreboard());
        int headTicketCount = objective != null ? objective.getScore(this.player.getName()).getScore() : 0;
        this.setItem(22, Material.NAME_TAG, 3004, NameComponents.HEAD_TICKETS, List.of(
                Component.empty(),
                LoreComponents.HEAD_TICKETS_NUMBER.append(Component.text(headTicketCount, TextColor.color(0xFFFF55), TextDecoration.BOLD))
        ));

        int mailCount = this.user.getMailAmount();
        int unreadMailCount = this.user.getUnreadMailAmount();
        this.setItem(24, Material.WRITABLE_BOOK, 3004, NameComponents.MAILS, List.of(
                Component.empty(),
                LoreComponents.MAILS_TOTAL.append(Component.text(mailCount, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                LoreComponents.MAILS_UNREAD.append(Component.text(unreadMailCount, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                Component.empty(),
                LoreComponents.MAILS_CLICK
        ));

        int totalHomeCount = PACProfile.getEssentials().getSettings().getHomeLimit(this.user);
        int usedHomeCount = this.user.getHomes().size();
        int remainingHomeCount = Math.max(0, totalHomeCount - usedHomeCount);
        this.maxHomePages = (int) Math.ceil(usedHomeCount / 10.0f);
        this.setItem(28, Material.RED_BED, 3004, NameComponents.HOMES, List.of(
                Component.empty(),
                LoreComponents.HOMES_TOTAL.append(Component.text(usedHomeCount, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                LoreComponents.HOMES_REMAINING.append(Component.text(remainingHomeCount, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                LoreComponents.HOMES_MAX_AVAILABLE.append(Component.text(totalHomeCount, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                Component.empty(),
                LoreComponents.HOMES_CLICK
        ));

        int totalClaimCount = this.playerData.getClaims().size();
        int remainingClaimBlocks = this.playerData.getRemainingClaimBlocks();
        int accruedClaimBlocks = this.playerData.getAccruedClaimBlocks();
        int bonusClaimBlocks = this.playerData.getBonusClaimBlocks();
        int totalClaimsBlocks = accruedClaimBlocks + bonusClaimBlocks;
        int usedClaimBlocks = totalClaimsBlocks - remainingClaimBlocks;
        int blocksAccruedPerHour = PACProfile.getGriefPrevention().config_claims_blocksAccruedPerHour_default;
        this.maxClaimPages = (int) Math.ceil(totalClaimCount / 10.0f);
        this.setItem(30, Material.GOLDEN_SHOVEL, 3004, NameComponents.CLAIMS, List.of(
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
        ));

        int onlinePlayersCount = Bukkit.getOnlinePlayers().stream().filter(p -> !PACProfile.getEssentials().getUser(p).isVanished()).toList().size();
        this.maxOnlinePlayersPages = (int) Math.ceil(onlinePlayersCount / 10.0f);
        this.setItem(32, Material.PLAYER_HEAD, 3005, NameComponents.ONLINE_PLAYERS, List.of(
                Component.empty(),
                LoreComponents.ONLINE_PLAYERS_COUNT.append(Component.text(onlinePlayersCount, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                Component.empty(),
                LoreComponents.ONLINE_PLAYERS_CLICK
        ));

        this.maxWarpsPages = (int) Math.ceil(PACProfile.getInstance().config.getWarps().size() / 35.0f);
        this.setItem(34, Material.ENDER_PEARL, 3004, NameComponents.WARPS, List.of(
                Component.empty(),
                LoreComponents.WARPS_CLICK
        ));

        this.setItem(45, Material.KNOWLEDGE_BOOK, 3004, NameComponents.RULES, List.of(
                Component.empty(),
                LoreComponents.RULES_CLICK
        ));

        this.setItem(46, Material.CHAIN, 3004, NameComponents.LINKS, List.of(
                Component.empty(),
                LoreComponents.LINKS_CLICK
        ));

        this.setItem(47, Material.MAP, 3004, NameComponents.DYNMAP, List.of(
                Component.empty(),
                LoreComponents.DYNMAP_CLICK
        ));

        this.setItem(53, Material.BARRIER, 3002, NameComponents.EXIT);

        this.fillStainedGlassPanes();
    }

    private void fillStainedGlassPanes() {
        this.setItem(0, Material.RED_STAINED_GLASS_PANE, 3001, Component.empty());
        this.setItem(1, Material.RED_STAINED_GLASS_PANE, 3001, Component.empty());
        this.setItem(2, Material.RED_STAINED_GLASS_PANE, 3001, Component.empty());
        this.setItem(3, Material.ORANGE_STAINED_GLASS_PANE, 3001, Component.empty());
        this.setItem(5, Material.ORANGE_STAINED_GLASS_PANE, 3001, Component.empty());
        this.setItem(6, Material.LIME_STAINED_GLASS_PANE, 3001, Component.empty());
        this.setItem(7, Material.LIME_STAINED_GLASS_PANE, 3001, Component.empty());
        this.setItem(8, Material.LIME_STAINED_GLASS_PANE, 3001, Component.empty());
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

    private String getBirthday() {
        String birthday = BirthdayData.getBirthday(this.player.getUniqueId().toString());
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

    private double[] getStatistics() {
        double[] statistics = new double[9];

        statistics[0] = this.baseStatistics ? this.player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() : this.player.getHealth();
        statistics[1] = this.baseStatistics ? this.player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() : this.player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        statistics[2] = this.baseStatistics ? this.player.getAttribute(Attribute.GENERIC_ARMOR).getBaseValue() : this.player.getAttribute(Attribute.GENERIC_ARMOR).getValue();
        statistics[3] = this.baseStatistics ? this.player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).getBaseValue() : this.player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).getValue();
        statistics[4] = this.baseStatistics ? this.player.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getBaseValue() : this.player.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).getValue();
        statistics[5] = this.baseStatistics ? this.player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue() : this.player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue();
        statistics[6] = this.baseStatistics ? this.player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() : this.player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue();
        statistics[7] = this.baseStatistics ? this.player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getBaseValue() : this.player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getValue();
        statistics[8] = this.baseStatistics ? this.player.getAttribute(Attribute.GENERIC_LUCK).getBaseValue() : this.player.getAttribute(Attribute.GENERIC_LUCK).getValue();

        for (int i = 0; i < statistics.length; i++) {
            statistics[i] = Math.round(statistics[i] * 100.0) / 100.0;
        }

        return statistics;
    }

    @Override
    protected void onSlotLeftClick(int slot) {
        switch (slot) {
            case 9 -> {
                this.baseStatistics = !this.baseStatistics;
                this.fillInventory();
            }
            case 17 -> new SettingsGui(this.viewer, this.player).open();
            case 20 -> {
                this.dispatchCommand(PACProfile.getInstance().config.getCommandOnClickCoins());
                this.inv.close();
            }
            case 24 -> {
                this.dispatchCommand(PACProfile.getInstance().config.getCommandOnClickMails());
                this.inv.close();
            }
            case 28 -> new HomesGui(this.viewer, this.player, 1, this.maxHomePages).open();
            case 30 -> new ClaimsGui(this.viewer, this.player, 1, this.maxClaimPages).open();
            case 32 -> new OnlinePlayersGui(this.viewer, this.player, 1, this.maxOnlinePlayersPages).open();
            case 34 -> new WarpsGui(this.viewer, this.player, 1, this.maxWarpsPages).open();
            case 45 -> {
                this.dispatchCommand(PACProfile.getInstance().config.getCommandOnClickRules());
                this.inv.close();
            }
            case 46 -> {
                this.dispatchCommand(PACProfile.getInstance().config.getCommandOnClickLinks());
                this.inv.close();
            }
            case 47 -> {
                this.dispatchCommand(PACProfile.getInstance().config.getCommandOnClickDynmap());
                this.inv.close();
            }
            case 53 -> this.inv.close();
        }
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e) {
        if (e.getInventory().equals(this.inv)) {
            PACProfile.getInstance().playerData.removeOutdatedHomes(this.player.getUniqueId(), this.user.getHomes());
            PACProfile.getInstance().playerData.removeOutdatedClaims(this.player.getUniqueId(), this.playerData.getClaims());
        }
    }
}
