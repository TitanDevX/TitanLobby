package me.titan.titanlobby.join.menu.designer.conv;

import me.titan.titanlobby.join.menu.JoinMenuConfig;
import me.titan.titanlobby.join.menu.designer.MenuManageMenu;
import me.titan.titanlobby.join.menu.menuReader.MenuButton;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.Valid;
import org.mineacademy.fo.conversation.SimpleConversation;
import org.mineacademy.fo.conversation.SimplePrompt;

public class SetSlotConv extends SimpleConversation {

	MenuButton btn;
	JoinMenuConfig confg;

	public SetSlotConv(MenuButton btn, JoinMenuConfig confg){
		this.btn = btn;
		this.confg = confg;
	}

	@Override
	protected Prompt getFirstPrompt() {
		return new prompt();
	}

	public class prompt extends SimplePrompt{

		@Override
		protected String getPrompt(ConversationContext ctx) {
			return "What do you want to change the slot to?";
		}

		@Override
		protected boolean isInputValid(ConversationContext context, String input) {
			return Valid.isInteger(input);
		}

		@Override
		protected String getFailedValidationText(ConversationContext context, String invalidInput) {
			return "&cInvalid input: slot must be a number.";
		}

		@Override
		protected @Nullable Prompt acceptValidatedInput(@NotNull ConversationContext context, @NotNull String input) {
			btn.setSlot(Integer.parseInt(input));
			Common.tellConversing(context.getForWhom(), "&aYou have successfully set the slot to &c" + input + "&a.");
			new MenuManageMenu(confg, btn);
			return null;
		}
	}
}
