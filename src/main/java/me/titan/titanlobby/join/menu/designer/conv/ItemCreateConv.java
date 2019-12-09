package me.titan.titanlobby.join.menu.designer.conv;

import me.titan.titanlobby.core.TitanLobbyPlugin;
import me.titan.titanlobby.join.menu.JoinMenuConfig;
import me.titan.titanlobby.join.menu.menuReader.MenuButton;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.Valid;
import org.mineacademy.fo.conversation.SimpleConversation;
import org.mineacademy.fo.conversation.SimplePrompt;
import org.mineacademy.fo.remain.CompMaterial;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemCreateConv extends SimpleConversation {

private JoinMenuConfig confg;
	CompMaterial material;
	String displayName;
	int slot;
	List<String> lores = new ArrayList<>();
	public ItemCreateConv(JoinMenuConfig confg, CompMaterial material){
		this.confg = confg;
		this.material = material;
	}

	@Override
	protected Prompt getFirstPrompt() {
		return new prompt();
	}

	public class prompt extends SimplePrompt{

		@Override
		protected String getPrompt(ConversationContext ctx) {
			return "What do you want to set the display name to?";
		}

		@Override
		protected @Nullable Prompt acceptValidatedInput(@NotNull ConversationContext context, @NotNull String input) {
			displayName = input;
			Common.tellConversing(context.getForWhom(), "&aYou have successfully set the display name to &c" + input + "&a.");

			return new SlotPrompt();
		}
	}
	public class SlotPrompt extends SimplePrompt{

		@Override
		protected String getPrompt(ConversationContext ctx) {
			return "What do you want to set the slot to?";
		}

		@Override
		protected boolean isInputValid(ConversationContext context, String input) {
			return Valid.isInteger(input);
		}

		@Override
		protected String getFailedValidationText(ConversationContext context, String invalidInput) {
			return "&cSlot must be a number.";
		}

		@Override
		protected @Nullable Prompt acceptValidatedInput(@NotNull ConversationContext context, @NotNull String input) {
			slot = Integer.parseInt(input);
			Common.tellConversing(context.getForWhom(), "&aYou have successfully set the display name to &c" + input + "&a.");

			return new LoresPrompt();
		}
	}
	public class LoresPrompt extends SimplePrompt{

		@Override
		protected String getPrompt(ConversationContext ctx) {
			return "What do you want to set the lores to? (split lines with ', ').";
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

			lores = result;
			Common.tellConversing(context.getForWhom(), "&aYou have successfully set the slot to &c" + input + "&a.");

			MenuButton mb = new MenuButton(Common.stripColors(displayName).toLowerCase(), material, slot, displayName);
			mb.setLores(lores);
			try {
				mb.setOn(confg);
			} catch (IOException e) {
				e.printStackTrace();
				Common.tellConversing(context.getForWhom(), "&cUnable to save the new button on the file. please check the console for more information.");
				return null;
			}
			confg.getButtons().put(mb.getName(), mb);
			JoinMenuConfig.menus.put(confg.getName(),confg);

			TitanLobbyPlugin.reloadAll();
			System.out.println(confg.getButtons());
			Common.tellConversing(context.getForWhom(), "&aYou have successfully created a new menu button.");
			return null;
		}
	}
}
