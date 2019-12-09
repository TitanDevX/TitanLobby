package me.titan.titanlobby.commands.tl;

import me.titan.titanlobby.join.menu.JoinMenuConfig;
import me.titan.titanlobby.join.menu.LobbyMenu;
import me.titan.titanlobby.staticEnums.Perms;
import org.mineacademy.fo.command.SimpleSubCommand;

public class OpenMenuCommand extends SimpleSubCommand {
	public OpenMenuCommand() {
		super("openMenu|om");

		setMinArguments(1);
		setUsage("<name>");
		setDescription("Opens a TitanLobby menu.");
		setPermission(Perms.MENU_OPEN.getPerms());
	}

	@Override
	protected void onCommand() {
		checkConsole();


		String name = args[0];

		if(!JoinMenuConfig.menus.containsKey(name)){
		returnTell("&cUnable to find a menu with this name.");
		}

		JoinMenuConfig jmc = JoinMenuConfig.menus.get(name);
		new LobbyMenu(jmc).displayTo(getPlayer());
	}
}
