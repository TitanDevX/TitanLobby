package me.titan.titanlobby.automaticTeleportMethod;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.SerializeUtil;

@RequiredArgsConstructor
@Getter
@Setter
public class AutomaticTeleportMethod {

	final boolean enabled;
	final Location location;
	final String server;
	final String message;

	public void teleportTo(Player p){
		p.teleport(location);

		if(!server.equalsIgnoreCase("")) {
			if (!p.performCommand("/server " + server)) {
				if (!Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "send " + p.getName() + " " + server)) {
					Common.tell(p, "&cUnable to send to server " + server + ".");
					return;
				}
			}
		}

		Common.tell(p, PlaceholderAPI.setPlaceholders(p, message));
	}
	public static AutomaticTeleportMethod read(ConfigurationSection section){
		boolean enabled = section.getBoolean("Enabled");
		Location loc = SerializeUtil.deserializeLocation(section.getString("Location"));
		String server = section.getString("Server");
		String msg = section.getString("Message_On_Teleport");

		return new AutomaticTeleportMethod(enabled, loc, server, msg);
	}


}
