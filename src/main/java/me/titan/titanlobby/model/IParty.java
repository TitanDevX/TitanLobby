package me.titan.titanlobby.model;

import lombok.Getter;
import lombok.Setter;
import me.titan.titanlobby.api.*;
import me.titan.titanlobby.api.events.PartyKickPlayerEvent;
import me.titan.titanlobby.player.ITitanPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.function.Consumer;

@Getter
@Setter
public class IParty implements Party {

	final ITitanPlayer creator;

	int id;

	TitanMatch currentMatch;
	List<UUID> members = new ArrayList<>();

	List<IPartySetting> settings = new ArrayList<>();


	public static Map<Integer, IParty> parties = new HashMap<>();


	 private IParty(ITitanPlayer creator) {
		this.creator = creator;
		creator.setParty(this);

		this.id = parties.size();
	}
	public static IParty getParty(int id){
		IParty result = parties.get(id);

		return result;
	}
	public static IParty getParty(ITitanPlayer creator){
		IParty result = null;
		for(IParty ip : parties.values()){
			if(ip.getCreator().equals(creator)){
				result = ip;
			}
		}
		if(result == null){
			result = new IParty(creator);
			parties.put(result.getId(), result);
		}

		return result;
	}
	public void doForAllPlayers(Consumer<ITitanPlayer> con){
		for(UUID id : this.getMembers()){

			ITitanPlayer itp = ITitanPlayer.getPlayer(id);
			con.accept(itp);
		}
	}
	public void doForAllPlayersFiltered(Consumer<ITitanPlayer> con){
		for(UUID id : this.getMembers()){

			if(Bukkit.getPlayer(id) == null) continue;
			ITitanPlayer itp = ITitanPlayer.getPlayer(id);
			con.accept(itp);
		}
	}
	public void warpPlayers(TitanMatch match){
		JoinResult jr = match.allowJoin(this);
		if(!jr.isAllowed()){
			creator.tell(jr.getMessage());
			return;
		}
		for(UUID id : members){
			if(Bukkit.getOfflinePlayer(id).isOnline()){
				Player p = Bukkit.getPlayer(id);

				ITitanPlayer tp = (ITitanPlayer) TitanPartyAPI.getTitanPlayer(p);
				JoinResult jr2 = match.allowJoin(TitanPartyAPI.getTitanPlayer(p));
				if(!jr2.isAllowed()){
					tp.tell(jr2.getMessage());
					continue;
				}


				match.join(p);
			}
		}
	}

	public void broadcastMsgLined(String msg){
	 	doForAllPlayersFiltered((p) -> {
	 		p.a(msg);
		});
	}
	public void broadcast(String msg){
	 	doForAllPlayersFiltered((p) ->{
	 		p.tell(msg);
		});
	}


	@Override
	public void addPlayer(Player p) {
		this.members.add(p.getUniqueId());
		ITitanPlayer itp = ITitanPlayer.getPlayer(p);
		itp.setParty(this);

	}


	@Override
	public int getMemberCount() {
		return members.size();
	}

	@Override
	public void kickPlayer(TitanPlayer player, TitanPlayer kicker) {
		PartyKickPlayerEvent ev = new PartyKickPlayerEvent(player, kicker, this);

		if(!ev.isCancelled()){
			removePlayer(player);
			((ITitanPlayer) player).a("&cYou have been kicked from the party by &6" + kicker.getName() + "&c.");
			((ITitanPlayer) kicker).a("&cYou have kicked &6" + player.getName() + "&c.");
			broadcastMsgLined("&cPlayer &6" + player.getName() + " &chas left the party.");
		}
	}

	@Override
	public void leavePlayer(TitanPlayer player) {
	 	removePlayer(player);
		broadcastMsgLined("&cPlayer &6" + player.getName() + " &chas left the party.");

	}

	public boolean isCreator(Player p){
	 	return this.getCreator().equals(ITitanPlayer.getPlayer(p));
	}

	@Override
	public void removePlayer(TitanPlayer player) {

	 	this.members.remove(player.getUniqueId());
		((ITitanPlayer) player).setParty(null);

	}
}
