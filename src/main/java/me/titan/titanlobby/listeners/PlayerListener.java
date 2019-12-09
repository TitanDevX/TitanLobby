package me.titan.titanlobby.listeners;

import me.titan.titanlobby.join.item.JoinItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		JoinItem.giveAllOnJoin(p);
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();

		if(e.getItem() == null ) return;

		JoinItem.items.values().forEach((i) -> {
			if(i.is(e.getItem())){
				i.onClick(p);
			}
		});

	}


}
