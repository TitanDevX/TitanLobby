package me.titan.titanlobby.join.joinNPC;


import lombok.Getter;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;

@Getter
public class JoinNPCCopy extends AbstractJoinNPC{

	NPC npc;

	public static String key = "JOIN_NPC_KEY";

	int id;
	JoinNPC source;
	public JoinNPCCopy( JoinNPC source){

		this.source = source;
		npc = CitizensAPI.getNPCRegistry().createNPC(source.config.npcType, Common.colorize(source.config.npcName));

		id =source.copies.size();

		npc.data().setPersistent(key, source.config.getName());

	}

	@Override
	public void spawn(Location loc) {
		npc.spawn(loc);
	}

	@Override
	public void onClick(Player clicker) {

		source.onClick(clicker);
	}
}
