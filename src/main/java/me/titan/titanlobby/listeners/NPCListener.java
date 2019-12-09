package me.titan.titanlobby.listeners;

import me.titan.titanlobby.join.joinNPC.JoinNPC;
import me.titan.titanlobby.join.joinNPC.JoinNPCConfig;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCListener implements Listener {

	@EventHandler
	public void onRIghtClick(NPCRightClickEvent e){
		NPC npc = e.getNPC();
		if(npc.data().has(JoinNPC.key)){
			JoinNPCConfig config = JoinNPCConfig.get(npc.data().get(JoinNPC.key));
			config.getNpc().onClick(e.getClicker());
		}
	}
}
