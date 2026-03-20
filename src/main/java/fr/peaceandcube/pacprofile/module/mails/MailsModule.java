package fr.peaceandcube.pacprofile.module.mails;

import com.earth2me.essentials.User;
import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.gui.item.GuiItem;
import fr.peaceandcube.pacprofile.gui.item.LoreProvider;
import fr.peaceandcube.pacprofile.module.Module;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MailsModule extends Module {

    public MailsModule() {
        super("mails");
    }

    @Override
    protected Function<Player, GuiItem> createGuiItem() {
        return player -> {
            User user = PACProfile.getEssentials().getUser(player);
            int mailCount = user.getMailAmount();
            int unreadMailCount = user.getUnreadMailAmount();

            List<Component> lore = new ArrayList<>();
            lore.add(Component.empty());
            lore.add(LoreProvider.line(translate("mails_total"), mailCount));
            lore.add(LoreProvider.line(translate("mails_unread"), unreadMailCount));
            if (!PACProfile.getInstance().config.getCommandOnClickMails().isBlank()) {
                lore.add(Component.empty());
                lore.add(LoreProvider.line(translate("mails_click")));
            }

            return GuiItem.builder().slot(24).material(Material.WRITABLE_BOOK)
                    .customModelData(3004)
                    .name(translate("mails"), 0xAA00AA)
                    .lore(lore)
                    .onLeftClick(context -> {
                        if (!PACProfile.getInstance().config.getCommandOnClickMails().isBlank()) {
                            context.dispatchCommand(PACProfile.getInstance().config.getCommandOnClickMails());
                            context.close();
                        }
                    })
                    .build();
        };
    }

    @Override
    protected void registerConfigOptions() {
    }

    @Override
    protected void registerDefaultTranslations() {
        defaultTranslations.put("mails", "Mails");
        defaultTranslations.put("mails_total", "Total: ");
        defaultTranslations.put("mails_unread", "Unread: ");
        defaultTranslations.put("mails_click", "⇒ Click to see mails");
    }
}
