package fr.peaceandcube.pacprofile.module.claims;

import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.gui.item.GuiItem;
import fr.peaceandcube.pacprofile.gui.item.LoreProvider;
import fr.peaceandcube.pacprofile.module.Module;
import me.ryanhamshire.GriefPrevention.PlayerData;
import net.kyori.adventure.text.Component;
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
                    LoreProvider.line(translate("claims_total"), totalClaimCount),
                    LoreProvider.line(translate("claims_cb_used"), usedClaimBlocks),
                    LoreProvider.line(translate("claims_cb_remaining"), remainingClaimBlocks),
                    Component.empty(),
                    LoreProvider.line(translate("claims_cb_accrued"), accruedClaimBlocks),
                    LoreProvider.line(translate("claims_cb_bonus"), bonusClaimBlocks),
                    LoreProvider.line(translate("claims_cb_total"), totalClaimsBlocks),
                    Component.empty(),
                    LoreProvider.line(translate("claims_cb_per_hour"), blocksAccruedPerHour),
                    Component.empty(),
                    LoreProvider.line(translate("claims_click"))
            );

            return GuiItem.builder().slot(30).material(Material.GOLDEN_SHOVEL)
                    .customModelData(3004)
                    .name(translate("claims"), 0x00AA00)
                    .lore(lore)
                    .onLeftClick(context -> new ClaimsGui(context.viewer(), context.player(), 1, maxClaimPages).open())
                    .build();
        };
    }

    @Override
    protected void registerConfigOptions() {
    }

    @Override
    protected void registerDefaultTranslations() {
        defaultTranslations.put("claims", "Claims");
        defaultTranslations.put("claims_total", "Total: ");
        defaultTranslations.put("claims_default_name", "Claim n°%s");
        defaultTranslations.put("claims_cb_used", "Used claim blocks: ");
        defaultTranslations.put("claims_cb_remaining", "Remaining claim blocks: ");
        defaultTranslations.put("claims_cb_accrued", "Accrued claim blocks: ");
        defaultTranslations.put("claims_cb_bonus", "Bonus claim blocks: ");
        defaultTranslations.put("claims_cb_total", "Total claim blocks: ");
        defaultTranslations.put("claims_cb_per_hour", "Accrued per hour: ");
        defaultTranslations.put("claims_click", "⇒ Click to see claims");
        defaultTranslations.put("claims_title", "Claims of %s (%2$d/%3$d)");
        defaultTranslations.put("claims_order", "Order claims");
        defaultTranslations.put("claim_world", "World: ");
        defaultTranslations.put("claim_greater_corner", "Greater corner: ");
        defaultTranslations.put("claim_lesser_corner", "Lesser corner: ");
        defaultTranslations.put("claim_width", "Width: ");
        defaultTranslations.put("claim_length", "Length: ");
        defaultTranslations.put("claim_area", "Area: ");
        defaultTranslations.put("claim_permissions", "Permissions");
        defaultTranslations.put("claim_permissions_builders", "Builders: ");
        defaultTranslations.put("claim_permissions_containers", "Containers: ");
        defaultTranslations.put("claim_permissions_accessors", "Accessors: ");
        defaultTranslations.put("claim_permissions_managers", "Managers: ");
        defaultTranslations.put("claim_name", "Name");
        defaultTranslations.put("claim_name_click", "⇒ Click to edit name");
        defaultTranslations.put("claim_name_title", "Edit name");
    }
}
