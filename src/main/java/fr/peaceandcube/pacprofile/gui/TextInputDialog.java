package fr.peaceandcube.pacprofile.gui;

import fr.peaceandcube.pacprofile.util.Messages;
import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.action.DialogAction;
import io.papermc.paper.registry.data.dialog.body.DialogBody;
import io.papermc.paper.registry.data.dialog.input.DialogInput;
import io.papermc.paper.registry.data.dialog.input.TextDialogInput;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickCallback;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.function.Consumer;

public class TextInputDialog {
    private final Dialog dialog;
    private final Player player;

    private TextInputDialog(Dialog dialog, Player player) {
        this.dialog = dialog;
        this.player = player;
    }

    public void show() {
        this.player.showDialog(dialog);
    }

    public static TextInputDialog of(Player player, Component title, Material material, String bodyText, String inputLabel, String inputValue, int inputLines, int inputHeight, Consumer<String> callback) {
        ItemStack itemStack = ItemStack.of(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setHideTooltip(true);
        itemStack.setItemMeta(itemMeta);
        return TextInputDialog.of(player, title, itemStack, bodyText, inputLabel, inputValue, inputLines, inputHeight, callback);
    }

    @SuppressWarnings("UnstableApiUsage")
    public static TextInputDialog of(Player player, Component title, ItemStack itemStack, String bodyText, String inputLabel, String inputValue, int inputLines, int inputHeight, Consumer<String> callback) {
        Dialog dialog = Dialog.create(builder -> builder.empty()
                .base(DialogBase.builder(title)
                        .body(List.of(
                                DialogBody.item(itemStack).description(DialogBody.plainMessage(Component.text(bodyText))).build()
                        ))
                        .inputs(List.of(
                                DialogInput.text("value", Component.text(inputLabel))
                                        .initial(inputValue)
                                        .maxLength(1024)
                                        .multiline(TextDialogInput.MultilineOptions.create(inputLines, inputHeight)).build()
                        ))
                        .build()
                ).type(DialogType.confirmation(
                        ActionButton.builder(Component.text(Messages.CONFIRMATION_YES))
                                .action(DialogAction.customClick((response, audience) -> {
                                    String value = response.getText("value");
                                    if (audience == player) {
                                        callback.accept(value);
                                    }
                                }, ClickCallback.Options.builder().build()))
                                .build(),
                        ActionButton.builder(Component.text(Messages.CONFIRMATION_NO))
                                .build()
                ))
        );

        return new TextInputDialog(dialog, player);
    }
}
