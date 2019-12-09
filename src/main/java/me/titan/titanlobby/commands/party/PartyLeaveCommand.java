package me.titan.titanlobby.commands.party;

import me.titan.titanlobby.player.ITitanPlayer;
import org.mineacademy.fo.command.SimpleSubCommand;

public class PartyLeaveCommand extends SimpleSubCommand {
	public PartyLeaveCommand() {
		super("leave");


	}

	@Override
	protected void onCommand() {
		checkConsole();


		ITitanPlayer ip = ITitanPlayer.getPlayer(getPlayer());
		if(ip.getParty() == null){
			ip.a("&cYou must be in a party to perform such a command.");
			return;
		}

		ip.getParty().leavePlayer(ip);
	}
}
