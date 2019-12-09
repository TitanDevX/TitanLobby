package me.titan.titanlobby.commands.tl;

import me.titan.titanlobby.join.menu.designer.DesignerMainMenu;
import me.titan.titanlobby.staticEnums.Perms;
import org.mineacademy.fo.command.SimpleSubCommand;

public class DesignCommand extends SimpleSubCommand {
	public DesignCommand() {
		super("menusDesign|md");


		setDescription("Opens a menu you can design/edit menus from it.");
		setPermission(Perms.DESIGN.getPerms());
	}

	@Override
	protected void onCommand() {
		checkConsole();



		new DesignerMainMenu().displayTo(getPlayer());
	}
}
