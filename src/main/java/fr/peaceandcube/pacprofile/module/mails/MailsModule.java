package fr.peaceandcube.pacprofile.module.mails;

import com.earth2me.essentials.User;
import fr.peaceandcube.pacprofile.PACProfile;
import fr.peaceandcube.pacprofile.item.GuiItem;
import fr.peaceandcube.pacprofile.module.Module;
import fr.peaceandcube.pacprofile.text.LoreComponents;
import fr.peaceandcube.pacprofile.util.Messages;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
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
            lore.add(LoreComponents.MAILS_TOTAL.append(Component.text(mailCount, NamedTextColor.YELLOW, TextDecoration.BOLD)));
            lore.add(LoreComponents.MAILS_UNREAD.append(Component.text(unreadMailCount, NamedTextColor.YELLOW, TextDecoration.BOLD)));
            if (!config.getCommandOnClickMails().isBlank()) {
                lore.add(Component.empty());
                lore.add(LoreComponents.MAILS_CLICK);
            }

            return GuiItem.builder().slot(24).material(Material.WRITABLE_BOOK)
                    .customModelData(3004)
                    .name(Messages.MAILS, 0xAA00AA)
                    .lore(lore)
                    .onLeftClick(context -> {
                        if (!config.getCommandOnClickMails().isBlank()) {
                            context.dispatchCommand(config.getCommandOnClickMails());
                            context.close();
                        }
                    })
                    .build();
        };
    }
}
