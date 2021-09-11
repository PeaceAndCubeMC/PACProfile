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
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.scoreboard.Objective;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProfileGui extends UnmodifiableGui {

    public ProfileGui(Player viewer, Player player) {
        super(6, Component.text(String.format(Messages.PROFILE, player.getName())), viewer, player);
        this.fillInventory();
        Bukkit.getPluginManager().registerEvents(this, PACProfile.getInstance());
    }

    @Override
    protected void fillInventory() {
        String rank = this.getRank();
        String nickname = this.user.getNickname() != null ? this.user.getNickname() : Messages.NOT_DEFINED;
        String birthday = this.getBirthday();
        String joinDate = this.getFirstPlayed();
        this.setPlayerHead(4, Component.text(this.player.getName(), TextColor.color(0x55FFFF), TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false), List.of(
                Component.empty(),
                LoreComponents.PROFILE_RANK.append(Component.text(rank, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                Component.empty(),
                LoreComponents.PROFILE_NICKNAME.append(Component.text(nickname, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                LoreComponents.PROFILE_BIRTHDAY.append(Component.text(birthday, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                Component.empty(),
                LoreComponents.PROFILE_JOIN_DATE.append(Component.text(joinDate, TextColor.color(0xFFFF55), TextDecoration.BOLD))
        ));

        double coinCount = this.user.getMoney().doubleValue();
        this.setItem(20, Material.SUNFLOWER, NameComponents.COINS, List.of(
                Component.empty(),
                LoreComponents.COINS_NUMBER.append(Component.text(coinCount, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                Component.empty(),
                LoreComponents.COINS_CLICK
        ));

        Objective objective = this.player.getScoreboard().getObjective(PACProfile.getInstance().config.getHeadTicketsScoreboard());
        int headTicketCount = objective != null ? objective.getScore(this.player.getName()).getScore() : 0;
        this.setItem(22, Material.NAME_TAG, NameComponents.HEAD_TICKETS, List.of(
                Component.empty(),
                LoreComponents.HEAD_TICKETS_NUMBER.append(Component.text(headTicketCount, TextColor.color(0xFFFF55), TextDecoration.BOLD))
        ));

        int mailCount = this.user.getMailAmount();
        int unreadMailCount = this.user.getUnreadMailAmount();
        this.setItem(24, Material.WRITABLE_BOOK, NameComponents.MAILS, List.of(
                Component.empty(),
                LoreComponents.MAILS_TOTAL.append(Component.text(mailCount, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                LoreComponents.MAILS_UNREAD.append(Component.text(unreadMailCount, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                Component.empty(),
                LoreComponents.MAILS_CLICK
        ));

        int totalHomeCount = PACProfile.getEssentials().getSettings().getHomeLimit(this.user);
        int usedHomeCount = this.user.getHomes().size();
        int remainingHomeCount = totalHomeCount - usedHomeCount;
        this.setItem(30, Material.RED_BED, NameComponents.HOMES, List.of(
                Component.empty(),
                LoreComponents.HOMES_TOTAL.append(Component.text(usedHomeCount, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                LoreComponents.HOMES_REMAINING.append(Component.text(remainingHomeCount, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                LoreComponents.HOMES_MAX_AVAILABLE.append(Component.text(totalHomeCount, TextColor.color(0xFFFF55), TextDecoration.BOLD)),
                Component.empty(),
                LoreComponents.HOMES_CLICK
        ));

        PlayerData playerData = PACProfile.getGriefPrevention().dataStore.getPlayerData(this.player.getUniqueId());
        int totalClaimCount = playerData.getClaims().size();
        int remainingClaimBlocks = playerData.getRemainingClaimBlocks();
        int accruedClaimBlocks = playerData.getAccruedClaimBlocks();
        int bonusClaimBlocks = playerData.getBonusClaimBlocks();
        int totalClaimsBlocks = accruedClaimBlocks + bonusClaimBlocks;
        int usedClaimBlocks = totalClaimsBlocks - remainingClaimBlocks;
        int blocksAccruedPerHour = PACProfile.getGriefPrevention().config_claims_blocksAccruedPerHour_default;
        this.setItem(32, Material.GOLDEN_SHOVEL, NameComponents.CLAIMS, List.of(
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

        this.setItem(45, Material.KNOWLEDGE_BOOK, NameComponents.RULES, List.of(
                Component.empty(),
                LoreComponents.RULES_CLICK
        ));

        this.setItem(46, Material.CHAIN, NameComponents.LINKS, List.of(
                Component.empty(),
                LoreComponents.LINKS_CLICK
        ));

        this.setItem(53, Material.BARRIER, NameComponents.EXIT, List.of());

        this.fillStainedGlassPanes();
    }

    private void fillStainedGlassPanes() {
        this.setItem(0, Material.RED_STAINED_GLASS_PANE);
        this.setItem(1, Material.RED_STAINED_GLASS_PANE);
        this.setItem(2, Material.RED_STAINED_GLASS_PANE);
        this.setItem(3, Material.ORANGE_STAINED_GLASS_PANE);
        this.setItem(5, Material.ORANGE_STAINED_GLASS_PANE);
        this.setItem(6, Material.LIME_STAINED_GLASS_PANE);
        this.setItem(7, Material.LIME_STAINED_GLASS_PANE);
        this.setItem(8, Material.LIME_STAINED_GLASS_PANE);
    }

    private String getRank() {
        User user = PACProfile.getLuckPerms().getUserManager().getUser(this.player.getUniqueId());
        if (user != null) {
            Group group = PACProfile.getLuckPerms().getGroupManager().getGroup(user.getPrimaryGroup());
            if (group != null) {
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

    @Override
    protected void onSlotLeftClick(int slot) {
        switch (slot) {
            case 20 -> {
                this.dispatchCommand(PACProfile.getInstance().config.getCommandOnClickCoins());
                this.inv.close();
            }
            case 24 -> {
                this.dispatchCommand(PACProfile.getInstance().config.getCommandOnClickMails());
                this.inv.close();
            }
            case 30 -> new HomesGui(this.viewer, this.player, 1).open();
            case 32 -> {
                this.dispatchCommand(PACProfile.getInstance().config.getCommandOnClickClaims());
                this.inv.close();
            }
            case 45 -> {
                this.dispatchCommand(PACProfile.getInstance().config.getCommandOnClickRules());
                this.inv.close();
            }
            case 46 -> {
                this.dispatchCommand(PACProfile.getInstance().config.getCommandOnClickLinks());
                this.inv.close();
            }
            case 53 -> this.inv.close();
        }
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e) {
        if (e.getInventory().equals(this.inv)) {
            PACProfile.getInstance().playerData.removeOutdatedHomes(this.player.getUniqueId(), this.user.getHomes());
        }
    }
}
