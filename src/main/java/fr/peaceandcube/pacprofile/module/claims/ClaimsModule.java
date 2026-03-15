package fr.peaceandcube.pacprofile.module.claims;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.item.GuiItem;
import fr.peaceandcube.pacprofile.module.Module;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.util.Messages;
import me.ryanhamshire.GriefPrevention.PlayerData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.function.Function;

public class ClaimsModule extends Module {

    public ClaimsModule() {
        super("claims");
    }

    @Override
    protected Function<Player, GuiItem> createGuiItem() {
        return player -> {
            PlayerData playerData = PACProfile.getGriefPrevention().dataStore.getPlayerData(player.getUniqueId());
            int totalClaimCount = playerData.getClaims().size();
            int remainingClaimBlocks = playerData.getRemainingClaimBlocks();
            int accruedClaimBlocks = playerData.getAccruedClaimBlocks();
            int bonusClaimBlocks = playerData.getBonusClaimBlocks();
            int totalClaimsBlocks = accruedClaimBlocks + bonusClaimBlocks;
            int usedClaimBlocks = totalClaimsBlocks - remainingClaimBlocks;
            int blocksAccruedPerHour = PACProfile.getGriefPrevention().config_claims_blocksAccruedPerHour_default;
            int maxClaimPages = (int) Math.ceil(totalClaimCount / 10.0f);

            List<Component> lore = List.of(
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

            return GuiItem.builder().slot(30).material(Material.GOLDEN_SHOVEL)
                    .customModelData(3004)
                    .name(Messages.CLAIMS, 0x00AA00)
                    .lore(lore)
                    .onLeftClick(context -> new ClaimsGui(context.viewer(), context.player(), 1, maxClaimPages).open())
                    .build();
        };
    }
}
