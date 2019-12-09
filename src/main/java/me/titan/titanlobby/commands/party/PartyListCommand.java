package me.titan.titanlobby.commands.party;

import me.titan.titanlobby.model.IParty;
import me.titan.titanlobby.player.ITitanPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.mineacademy.fo.command.SimpleSubCommand;

import java.util.UUID;

public class PartyListCommand extends SimpleSubCommand {
	public PartyListCommand() {
		super("list");


	}

	@Override
	protected void onCommand() {
		checkConsole();

		ITitanPlayer tp = ITitanPlayer.getPlayer(getPlayer());
		if(tp.getParty() == null){
			tp.a("&cYou must be in a party to perform such a command.");
			return;
		}
		IParty par = tp.getParty();
		StringBuilder msg = new StringBuilder();

		for(UUID id : par.getMembers()){
			Player p = Bukkit.getPlayer(id);
			if(p == null){
				msg.append(",&c" + p.getName());
			}else {
				msg.append(",&a" + p.getName());
			}
		}
		String f = msg.toString().substring(1);
		tp.a("&6Party Players (" + par.getMemberCount() + "): " + f + "&6.");


	}
}
