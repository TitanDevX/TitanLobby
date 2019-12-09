package me.titan.titanlobby.commands.tl;

import me.titan.titanlobby.join.item.JoinItem;
import me.titan.titanlobby.staticEnums.Perms;
import org.mineacademy.fo.command.SimpleSubCommand;

public class CreateJoinItem extends SimpleSubCommand {
	public CreateJoinItem() {
		super("createItem|ci");

		setMinArguments(1);
		setUsage("<name>");
		setDescription("Creates a new item, you can configure it in it's own config file inside items folder.");
		setPermission(Perms.ITEM_CREATE.getPerms());
	}

	@Override
	protected void onCommand() {
		checkConsole();


		String name = args[0];

		if(JoinItem.items.containsKey(name)){
		returnTell("&cA item with this name already exists.");
		}

		JoinItem ji = new JoinItem(name);
		JoinItem.items.put(name, ji);
		tell("&aYou have successfully created a new item with the name: &c" + name + "&a.");
	}
}
