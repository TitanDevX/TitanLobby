package me.titan.titanlobby.commands.tl;

import me.titan.titanlobby.core.TitanLobbyPlugin;
import me.titan.titanlobby.staticEnums.Perms;
import org.mineacademy.fo.command.SimpleSubCommand;
import org.mineacademy.fo.plugin.SimplePlugin;
import org.mineacademy.fo.settings.SimpleLocalization;

public class ReloadCommand extends SimpleSubCommand {
	public ReloadCommand() {
		super("reload|rl");


		setDescription("Reloads the plugin.");
		setPermission(Perms.RELOAD.getPerms());
	}

	@Override
	protected void onCommand() {
		try {
			SimplePlugin.getInstance().reload();
			TitanLobbyPlugin.reloadAll();
			tell(SimpleLocalization.Commands.RELOAD_SUCCESS);
		}catch(Throwable a){
			returnTell(SimpleLocalization.Commands.RELOAD_FAIL.replace("{error}", a.getMessage()));
		}

	}
}
