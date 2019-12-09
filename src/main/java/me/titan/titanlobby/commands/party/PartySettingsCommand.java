package me.titan.titanlobby.commands.party;

import me.titan.titanlobby.model.IPartySetting;
import me.titan.titanlobby.player.ITitanPlayer;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.command.SimpleSubCommand;

public class PartySettingsCommand extends SimpleSubCommand {
	public PartySettingsCommand() {
		super("setting");

		setUsage("<setting>");
		setMinArguments(1);

	}

	@Override
	protected void onCommand() {
		checkConsole();

		ITitanPlayer tp = ITitanPlayer.getPlayer(getPlayer());
		if(tp.getParty() == null){
			tp.a("&cYou must be in a party to perform such a command.");
			return;
		}
		if(!tp.getParty().isCreator(getPlayer())){
			tp.a("&cYou are not permitted to modify the party settings.");
			return;
		}
		IPartySetting setting = findEnum(IPartySetting.class, args[0], "&cInvalid party settings, try again with the following: " + Common.joinToString(IPartySetting.values()).toLowerCase());

		if(setting.has(tp.getParty())){
			setting.remove(tp.getParty());
			tp.a("&aYou have disabled the setting " + setting.toString().toLowerCase() + ".");

			tp.getParty().broadcastMsgLined("&6" + setting.toString().toLowerCase() + " has been disabled." );

			return;
		}else {

			setting.add(tp.getParty());
			tp.a("&aYou have enabled the setting " + setting.toString().toLowerCase() + ".");
			tp.getParty().broadcastMsgLined("&6" + setting.toString().toLowerCase() + " has been enabled." );

			return;
		}






	}
}
