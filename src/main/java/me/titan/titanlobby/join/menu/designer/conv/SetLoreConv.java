package me.titan.titanlobby.join.menu.designer.conv;

import me.titan.titanlobby.join.menu.JoinMenuConfig;
import me.titan.titanlobby.join.menu.designer.MenuManageMenu;
import me.titan.titanlobby.join.menu.menuReader.MenuButton;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.conversation.SimpleConversation;
import org.mineacademy.fo.conversation.SimplePrompt;

import java.util.ArrayList;
import java.util.List;

public class SetLoreConv extends SimpleConversation {

	MenuButton btn;
	JoinMenuConfig confg;

	public SetLoreConv(MenuButton btn, JoinMenuConfig confg){
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
			return "What do you want to change the lores to? (split lines with ', ').";
		}



		@Override
		protected @Nullable Prompt acceptValidatedInput(@NotNull ConversationContext context, @NotNull String input) {

			List<String> result = new ArrayList<>();
			if(input.contains(", ")){
				String[] parts= input.split(", ");
				for(String par : parts){
					result.add(par);
				}
			}else {
				result.add(input);
			}

			btn.setLores(result);
			Common.tellConversing(context.getForWhom(), "&aYou have successfully set the slot to &c" + input + "&a.");
			new MenuManageMenu(confg, btn);
			return null;
		}
	}
}
