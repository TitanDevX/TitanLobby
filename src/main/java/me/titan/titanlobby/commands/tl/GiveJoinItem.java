package me.titan.titanlobby.commands.tl;

import me.titan.titanlobby.join.item.JoinItem;
import me.titan.titanlobby.staticEnums.Perms;
import org.mineacademy.fo.command.SimpleSubCommand;

public class GiveJoinItem extends SimpleSubCommand {
	public GiveJoinItem() {
		super("giveItem|gi");

		setMinArguments(1);
		setUsage("<name>");
		setDescription("Gives you a titanLobby item.");
		setPermission(Perms.ITEM_CREATE.getPerms());
	}

	@Override
	protected void onCommand() {
		checkConsole();


		String name = args[0];

		if(!JoinItem.items.containsKey(name)){
		returnTell("&cUnable to find an item with this name");
		}

		JoinItem ji = JoinItem.items.get(name);
		ji.give(getPlayer());
		tell("&aHave fun!");
	}
}
