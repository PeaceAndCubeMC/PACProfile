package fr.peaceandcube.pacprofile.gui;

import fr.peaceandcube.pacbirthday.PACBirthday;
import fr.peaceandcube.pacbirthday.util.LocalizedMonth;
import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.item.GuiItem;
import fr.peaceandcube.pacprofile.module.Module;
import fr.peaceandcube.pacprofile.statistic.Statistic;
import fr.peaceandcube.pacprofile.statistic.Statistics;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.util.Messages;
import me.ryanhamshire.GriefPrevention.PlayerData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProfileGui extends UnmodifiableGui {
    private final PlayerData playerData;
    private boolean baseStatistics;

    public ProfileGui(Player viewer, Player player) {
        super(6, Component.text(Messages.PROFILE.formatted(player.getName())), viewer, player);
        this.playerData = PACProfile.getGriefPrevention().dataStore.getPlayerData(this.player.getUniqueId());
        this.baseStatistics = false;
        this.fillInventory();
        Bukkit.getPluginManager().registerEvents(this, PACProfile.getInstance());
    }

    @Override
    public void fillInventory() {
        this.items.clear();

        for (Module module : PACProfile.getInstance().getModules()) {
            if (module.isEnabled()) {
                this.setItem(module.guiItem(player));
            }
        }

        String rank = this.getRank();
        String rankExpiration = this.getRankExpiration();
        String nickname = this.user.getNickname();
        String birthday = this.getBirthday();
        String joinDate = this.getFirstPlayed();
        List<Component> playerLore = new ArrayList<>();
        playerLore.add(Component.empty());
        playerLore.add(LoreComponents.PROFILE_RANK.append(Component.text(rank, NamedTextColor.YELLOW, TextDecoration.BOLD)));
        if (rankExpiration != null) {
            playerLore.add(LoreComponents.PROFILE_RANK_EXPIRATION.append(Component.text(rankExpiration, NamedTextColor.YELLOW, TextDecoration.BOLD)));
        }
        playerLore.add(Component.empty());
        if (nickname != null) {
            playerLore.add(LoreComponents.PROFILE_NICKNAME.append(Component.text(nickname, NamedTextColor.YELLOW, TextDecoration.BOLD)));
        }
        playerLore.add(LoreComponents.PROFILE_BIRTHDAY.append(Component.text(birthday, NamedTextColor.YELLOW, TextDecoration.BOLD)));
        playerLore.add(Component.empty());
        playerLore.add(LoreComponents.PROFILE_JOIN_DATE.append(Component.text(joinDate, NamedTextColor.YELLOW, TextDecoration.BOLD)));
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
                .onLeftClick(context -> {
                    this.baseStatistics = !this.baseStatistics;
                    context.fillInventory();
                })
                .build());

        int totalHomeCount = PACProfile.getEssentials().getSettings().getHomeLimit(this.user);
        int usedHomeCount = this.user.getHomes().size();
        int remainingHomeCount = Math.max(0, totalHomeCount - usedHomeCount);
        int maxHomePages = (int) Math.ceil(usedHomeCount / 10.0f);
        List<Component> homesLore = List.of(
                Component.empty(),
                LoreComponents.HOMES_TOTAL.append(Component.text(usedHomeCount, NamedTextColor.YELLOW, TextDecoration.BOLD)),
                LoreComponents.HOMES_REMAINING.append(Component.text(remainingHomeCount, NamedTextColor.YELLOW, TextDecoration.BOLD)),
                LoreComponents.HOMES_MAX_AVAILABLE.append(Component.text(totalHomeCount, NamedTextColor.YELLOW, TextDecoration.BOLD)),
                Component.empty(),
                LoreComponents.HOMES_CLICK
        );
        this.setItem(GuiItem.builder().slot(28).material(Material.RED_BED)
                .customModelData(3004)
                .name(Messages.HOMES, 0x5555FF)
                .lore(homesLore)
                .onLeftClick(context -> new HomesGui(context.viewer(), context.player(), 1, maxHomePages).open())
                .build());

        int totalClaimCount = this.playerData.getClaims().size();
        int remainingClaimBlocks = this.playerData.getRemainingClaimBlocks();
        int accruedClaimBlocks = this.playerData.getAccruedClaimBlocks();
        int bonusClaimBlocks = this.playerData.getBonusClaimBlocks();
        int totalClaimsBlocks = accruedClaimBlocks + bonusClaimBlocks;
        int usedClaimBlocks = totalClaimsBlocks - remainingClaimBlocks;
        int blocksAccruedPerHour = PACProfile.getGriefPrevention().config_claims_blocksAccruedPerHour_default;
        int maxClaimPages = (int) Math.ceil(totalClaimCount / 10.0f);
        List<Component> claimsLore = List.of(
                Component.empty(),
                LoreComponents.CLAIMS_TOTAL.append(Component.text(totalClaimCount, NamedTextColor.YELLOW, TextDecoration.BOLD)),
                LoreComponents.CLAIMS_CB_USED.append(Component.text(usedClaimBlocks, NamedTextColor.YELLOW, TextDecoration.BOLD)),
                LoreComponents.CLAIMS_CB_REMAINING.append(Component.text(remainingClaimBlocks, NamedTextColor.YELLOW, TextDecoration.BOLD)),
                Component.empty(),
                LoreComponents.CLAIMS_CB_ACCRUED.append(Component.text(accruedClaimBlocks, NamedTextColor.YELLOW, TextDecoration.BOLD)),
                LoreComponents.CLAIMS_CB_BONUS.append(Component.text(bonusClaimBlocks, NamedTextColor.YELLOW, TextDecoration.BOLD)),
                LoreComponents.CLAIMS_CB_TOTAL.append(Component.text(totalClaimsBlocks, NamedTextColor.YELLOW, TextDecoration.BOLD)),
                Component.empty(),
                LoreComponents.CLAIMS_CB_PER_HOUR.append(Component.text(blocksAccruedPerHour, NamedTextColor.YELLOW, TextDecoration.BOLD)),
                Component.empty(),
                LoreComponents.CLAIMS_CLICK
        );
        this.setItem(GuiItem.builder().slot(30).material(Material.GOLDEN_SHOVEL)
                .customModelData(3004)
                .name(Messages.CLAIMS, 0x00AA00)
                .lore(claimsLore)
                .onLeftClick(context -> new ClaimsGui(context.viewer(), context.player(), 1, maxClaimPages).open())
                .build());

        this.setItem(GuiItem.builder().slot(53).material(Material.BARRIER)
                .customModelData(3002)
                .name(Messages.EXIT, 0xFF5555)
                .onLeftClick(GuiContext::close)
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
        Component component = statistic.getTextComponent().append(Component.text(value, NamedTextColor.YELLOW, TextDecoration.BOLD));
        if (!this.baseStatistics && diff != 0) {
            TextColor diffColor = diff > 0 ? NamedTextColor.GREEN : NamedTextColor.RED;
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
