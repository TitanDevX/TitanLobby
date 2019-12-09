package me.titan.titanlobby.join.joinNPC;

import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class AbstractJoinNPC {

	NPC npc;

	public abstract void spawn(Location loc);

	public abstract void onClick(Player clicker);


}
