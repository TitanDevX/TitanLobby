package me.titan.titanlobby.commands.party;

import me.titan.titanlobby.api.Party;
import me.titan.titanlobby.api.events.PlayerPartyInviteReceiveEvent;
import me.titan.titanlobby.model.IParty;
import me.titan.titanlobby.model.IPartySetting;
import me.titan.titanlobby.player.ITitanPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.command.SimpleSubCommand;

public class PartyInviteCommand extends SimpleSubCommand {
	public PartyInviteCommand() {
		super("invite");

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

		if(itp.getParty() != null && ip.getParty() != null && itp.getParty().equals(ip.getParty())){
			itp.a("&cThis player is already in your party!");
			return;
		}
		if(ip.getParty() != null && !ip.getParty().isCreator(getPlayer())){
			if(!IPartySetting.ALL_INVITE.has(ip.getParty())){
				ip.a("&cYou are not permitted to invite players.");
				return;
			}
		}
		if(!itp.canReceivePartyInvites(getPlayer())){

			ip.a("&cYou cannot send party invites to this player.");
			return;
		}

		Party p = IParty.getParty(ip);
		PlayerPartyInviteReceiveEvent ev = new PlayerPartyInviteReceiveEvent(ip, itp, p);
		Common.callEvent(ev);
		if(!ev.isCancelled()) {
			itp.receivePartyInvite(ev);
			ip.a("&aYou have successfully sent a party invite to " + t.getName() + ", they have 5 minutes to accept.");
		}
	}
}
