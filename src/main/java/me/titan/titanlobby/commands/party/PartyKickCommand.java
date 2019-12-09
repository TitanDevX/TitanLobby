package me.titan.titanlobby.commands.party;

import me.titan.titanlobby.player.ITitanPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.mineacademy.fo.command.SimpleSubCommand;

public class PartyKickCommand extends SimpleSubCommand {
	public PartyKickCommand() {
		super("kick");

		setUsage("<player>");
		setMinArguments(1);
	}

	@Override
	protected void onCommand() {
		checkConsole();

		String tn = args[0];
		ITitanPlayer ip = ITitanPlayer.getPlayer(getPlayer());


		if(ip.getParty() == null){

			ip.a("&cYou must be in a party to perform such a command.");
			return;
		}

		if(!ip.getParty().getCreator().equals(ip)){
			ip.a("&cYou are not permitted to kick players.");
			return;
		}

		Player t = Bukkit.getPlayer(tn);
		if(t == null){

			ip.a("&cThis player does not exist, or not online.");
			return;

		}


		ITitanPlayer itp = ITitanPlayer.getPlayer(t);
		if(!ip.getParty().getMembers().contains(t.getUniqueId())){
			ip.a("&cThis player is not in your party.");
			return;
		}

		ip.getParty().kickPlayer(itp, ip);



	}
}
