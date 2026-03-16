package fr.peaceandcube.pacprofile.module.identity;

import fr.peaceandcube.pacbirthday.PACBirthday;
import fr.peaceandcube.pacbirthday.util.LocalizedMonth;
import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.item.GuiItem;
import fr.peaceandcube.pacprofile.module.Module;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.util.Messages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class IdentityModule extends Module {

    public IdentityModule() {
        super("identity");
    }

    @Override
    protected Function<Player, GuiItem> createGuiItem() {
        return player -> {
            com.earth2me.essentials.User user = PACProfile.getEssentials().getUser(player);
            String rank = getRank(player);
            String rankExpiration = getRankExpiration(player);
            String nickname = user.getNickname();
            String birthday = getBirthday(player);
            String joinDate = getFirstPlayed(player);

            List<Component> lore = new ArrayList<>();
            lore.add(Component.empty());
            lore.add(LoreComponents.PROFILE_RANK.append(Component.text(rank, NamedTextColor.YELLOW, TextDecoration.BOLD)));
            if (rankExpiration != null) {
                lore.add(LoreComponents.PROFILE_RANK_EXPIRATION.append(Component.text(rankExpiration, NamedTextColor.YELLOW, TextDecoration.BOLD)));
            }
            lore.add(Component.empty());
            if (nickname != null) {
                lore.add(LoreComponents.PROFILE_NICKNAME.append(Component.text(nickname, NamedTextColor.YELLOW, TextDecoration.BOLD)));
            }
            lore.add(LoreComponents.PROFILE_BIRTHDAY.append(Component.text(birthday, NamedTextColor.YELLOW, TextDecoration.BOLD)));
            lore.add(Component.empty());
            lore.add(LoreComponents.PROFILE_JOIN_DATE.append(Component.text(joinDate, NamedTextColor.YELLOW, TextDecoration.BOLD)));

            return GuiItem.builder().slot(4).material(Material.PLAYER_HEAD).player(player)
                    .customModelData(3004)
                    .name(player.getName(), 0x55FFFF)
                    .lore(lore)
                    .build();
        };
    }

    @Override
    protected void registerConfigOptions() {
    }

    @Override
    protected void registerDefaultTranslations() {
        defaultTranslations.put("profile_rank", "Rank: ");
        defaultTranslations.put("profile_rank_expiration", "Expiration: ");
        defaultTranslations.put("profile_nickname", "Nickname: ");
        defaultTranslations.put("profile_birthday", "Birthday: ");
        defaultTranslations.put("profile_join_date", "Joined on: ");
    }

    private String getRank(Player player) {
        User user = PACProfile.getLuckPerms().getUserManager().getUser(player.getUniqueId());
        if (user != null) {
            Group group = PACProfile.getLuckPerms().getGroupManager().getGroup(user.getPrimaryGroup());
            if (group != null && group.getDisplayName() != null) {
                return group.getDisplayName();
            }
        }
        return Messages.NOT_DEFINED;
    }

    private String getRankExpiration(Player player) {
        User user = PACProfile.getLuckPerms().getUserManager().getUser(player.getUniqueId());
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

    private String getBirthday(Player player) {
        String birthday = PACBirthday.birthdaysFile.getBirthday(player.getUniqueId().toString());
        if (birthday != null) {
            String day = birthday.substring(0, 2);
            String month = LocalizedMonth.fromNumber(Integer.parseInt(birthday.substring(3, 5))).getLocalizedName();
            return day + " " + month;
        }
        return Messages.NOT_DEFINED;
    }

    private String getFirstPlayed(Player player) {
        long timestamp = player.getFirstPlayed();
        return new SimpleDateFormat(PACProfile.getInstance().config.getDateFormat()).format(new Date(timestamp));
    }
}
