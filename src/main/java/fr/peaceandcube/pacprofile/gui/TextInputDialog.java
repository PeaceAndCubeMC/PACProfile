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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Player player;
        private Component title;
        private ItemStack bodyItem;
        private String bodyText;
        private String inputLabel;
        private String inputValue;
        private int inputMaxLines;
        private int inputHeight;
        private Consumer<String> onConfirm;

        private Builder() {
        }

        public Builder player(Player player) {
            this.player = player;
            return this;
        }

        public Builder title(Component title) {
            this.title = title;
            return this;
        }

        public Builder bodyItem(Material material) {
            ItemStack bodyItem = ItemStack.of(material);
            ItemMeta itemMeta = bodyItem.getItemMeta();
            itemMeta.setHideTooltip(true);
            bodyItem.setItemMeta(itemMeta);
            return bodyItem(bodyItem);
        }

        public Builder bodyItem(ItemStack bodyItem) {
            this.bodyItem = bodyItem;
            return this;
        }

        public Builder bodyText(String bodyText) {
            this.bodyText = bodyText;
            return this;
        }

        public Builder inputLabel(String inputLabel) {
            this.inputLabel = inputLabel;
            return this;
        }

        public Builder inputValue(String inputValue) {
            this.inputValue = inputValue;
            return this;
        }

        public Builder inputSize(int maxLines, int height) {
            this.inputMaxLines = maxLines;
            this.inputHeight = height;
            return this;
        }

        public Builder onConfirm(Consumer<String> onConfirm) {
            this.onConfirm = onConfirm;
            return this;
        }

        @SuppressWarnings("UnstableApiUsage")
        public TextInputDialog build() {
            Dialog dialog = Dialog.create(builder -> builder.empty()
                    .base(DialogBase.builder(title)
                            .body(List.of(
                                    DialogBody.item(bodyItem).description(DialogBody.plainMessage(Component.text(bodyText))).build()
                            ))
                            .inputs(List.of(
                                    DialogInput.text("value", Component.text(inputLabel))
                                            .initial(inputValue)
                                            .maxLength(1024)
                                            .multiline(TextDialogInput.MultilineOptions.create(inputMaxLines, inputHeight)).build()
                            ))
                            .build()
                    ).type(DialogType.confirmation(
                            ActionButton.builder(Component.text(Messages.CONFIRMATION_YES))
                                    .action(DialogAction.customClick((response, audience) -> {
                                        String value = response.getText("value");
                                        if (audience == player) {
                                            onConfirm.accept(value);
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
}
