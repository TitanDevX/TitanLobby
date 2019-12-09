package me.titan.titanlobby.commands.party;

import me.titan.titanlobby.player.ITitanPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.mineacademy.fo.command.SimpleSubCommand;

public class PartyAcceptCommand extends SimpleSubCommand {
	public PartyAcceptCommand() {
		super("accept");

		setUsage("<player>");
		setMinArguments(1);
	}

	@Override
	protected void onCommand() {
		checkConsole();

		String tn = args[0];
		ITitanPlayer ip = ITitanPlayer.getPlayer(getPlayer());


		Player t = Bukkit.getPlayer(tn);
		if(t == null){

			ip.a("&cThis player does not exist, or not online.");
			return;

		}

			ITitanPlayer itp = ITitanPlayer.getPlayer(t);

		if(!ip.getInvites().containsKey(t.getUniqueId())){
			returnTell("&cYou don't have any pending invites from this player.");
		}
		itp.acceptPartyInvite(ip.getInvites().get(t.getUniqueId()));


	}
}
