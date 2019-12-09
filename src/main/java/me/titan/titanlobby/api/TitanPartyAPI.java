package me.titan.titanlobby.api;

import lombok.NoArgsConstructor;
import me.titan.titanlobby.player.ITitanPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

@NoArgsConstructor
/**
 *
 * The core API class.
 *
 */
public class TitanPartyAPI {

	public static TitanPlayer getTitanPlayer(Player p){
		return ITitanPlayer.getPlayer(p);
	}
	public static TitanPlayer getTitanPlayer(UUID p){
		return ITitanPlayer.getPlayer(p);
	}

}
