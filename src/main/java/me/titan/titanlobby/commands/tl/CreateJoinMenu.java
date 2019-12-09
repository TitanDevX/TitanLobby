package me.titan.titanlobby.commands.tl;

import me.titan.titanlobby.join.menu.JoinMenuConfig;
import me.titan.titanlobby.staticEnums.Perms;
import org.mineacademy.fo.command.SimpleSubCommand;

public class CreateJoinMenu extends SimpleSubCommand {
	public CreateJoinMenu() {
		super("createMenu|cm");

		setMinArguments(1);
		setUsage("<name>");
		setDescription("Creates a new join menu, you can open it by typing /tl openMenu <name>.");
		setPermission(Perms.MENU_CREATE.getPerms());
	}

	@Override
	protected void onCommand() {
		checkConsole();


		String name = args[0];

		if(JoinMenuConfig.menus.containsKey(name)){
		returnTell("&cA menu with this name already exists.");
		}

		JoinMenuConfig jmc = new JoinMenuConfig(name);
		JoinMenuConfig.menus.put(name, jmc);
		tell("&aYou have successfully created a new menu with the name: &c" + name + "&a.");
	}
}
