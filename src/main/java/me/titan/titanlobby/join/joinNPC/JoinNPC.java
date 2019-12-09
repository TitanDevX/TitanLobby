package me.titan.titanlobby.join.joinNPC;


import me.titan.titanlobby.commandsReader.LobbyCommand;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;

import java.util.HashMap;
import java.util.Map;

public class JoinNPC extends AbstractJoinNPC{

	NPC npc;

	public Map<Integer, JoinNPCCopy> copies = new HashMap<>();


	public static String key = "JOIN_NPC_KEY";

	JoinNPCConfig config;

	public JoinNPC(JoinNPCConfig source){
		this.config = source;

		npc = CitizensAPI.getNPCRegistry().createNPC(source.npcType, Common.colorize(source.npcName));

		npc.data().setPersistent(key, source.getName());

	}

	@Override
	public void spawn(Location loc) {
		if(this.npc.isSpawned()){
			JoinNPCCopy copy = new JoinNPCCopy(this);
			copies.put(copy.id, copy);
		}
		npc.spawn(loc);
	}

	@Override
	public void onClick(Player clicker) {

		LobbyCommand.perform(config.getCommands(), clicker, null);
	}


}
