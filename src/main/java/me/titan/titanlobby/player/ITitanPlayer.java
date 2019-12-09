package me.titan.titanlobby.player;

import lombok.Getter;
import lombok.Setter;
import me.titan.titanlobby.Util;
import me.titan.titanlobby.api.TitanPlayer;
import me.titan.titanlobby.api.events.PlayerPartyInviteReceiveEvent;
import me.titan.titanlobby.model.IParty;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.model.SimpleComponent;
import org.mineacademy.fo.settings.YamlSectionConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class ITitanPlayer extends YamlSectionConfig implements TitanPlayer {

	UUID uniqueId;

	protected Player player;
	String name;

	IParty party;

	boolean canReceivePartyInvites = true;

	public Map<UUID, PlayerPartyInviteReceiveEvent> invites = new HashMap<>();

	public static Map<UUID, ITitanPlayer> players = new HashMap<>();

	protected ITitanPlayer(String uuid) {
		super(uuid);

		this.uniqueId = UUID.fromString(uuid);
		this.player = Bukkit.getPlayer(uniqueId);
		this.name = player.getName();

		//loadConfiguration(null, "data.db");
	}

	@Override
	protected void onLoadFinish() {

	}

	public static ITitanPlayer getPlayer(Player p){

		return getPlayer(p.getUniqueId());
	}
	public static ITitanPlayer getPlayer(UUID  p){
		ITitanPlayer itp = players.get(p);

		if(itp == null){
			itp = new ITitanPlayer(p.toString());

			players.put(p, itp);

		}
		return itp;
	}
	public Player getPlayer(){
		if(this.player == null){
			this.player = Bukkit.getPlayer(uniqueId);

			this.name = player.getName();
		}
		return player;
	}

	@Override
	public boolean canReceivePartyInvites(Player from) {
		return true;
	}

	@Override
	public void receivePartyInvite(PlayerPartyInviteReceiveEvent invite) {



		tell("&6" + Util.chatLine());
		tell("&c" + invite.getSender().getPlayer().getName() + " &ahas sent a party invite.");
		SimpleComponent.of("&eClick here to accept.").onHover("&dClick to accept the party invite").onClickRunCmd("/party accept " + invite.getSender().getPlayer().getName()).send(invite.getReceiver().getPlayer());

		((ITitanPlayer) invite.getReceiver()).invites.put(invite.getSender().getPlayer().getUniqueId(), invite);
		tell("&6" + Util.chatLine());

	}

	@Override
	public void acceptPartyInvite(PlayerPartyInviteReceiveEvent invite) {


		IParty par = (IParty) invite.getParty();
		TitanPlayer s =  invite.getSender();
		TitanPlayer r = invite.getReceiver();



		par.addPlayer(r.getPlayer());
		par.broadcastMsgLined("&6Player &c" + r.getPlayer().getName() + " &6joined the party!");

	}



	public void tell(String msg){
		if(!getPlayer().isOnline()) return;
		Common.tell(getPlayer(), msg);
	}
	public void a(String t){
		if(!getPlayer().isOnline()) return;

		Common.tell( getPlayer(),"&6" + Util.chatLine(), t,"&6" + Util.chatLine());
	}
}
