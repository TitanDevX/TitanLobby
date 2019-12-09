package me.titan.titanlobby.commandsReader;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.clip.placeholderapi.PlaceholderAPI;
import me.titan.titanlobby.automaticTeleportMethod.AutomaticTeleportMethod;
import me.titan.titanlobby.player.ITitanPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import org.mineacademy.fo.model.HookManager;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class LobbyCommand {

	final String content;
	final CommandType type;
	final boolean party;


	public void perform(@Nullable Player p){
		if(type == CommandType.RUN_CONSOLE){
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), getHolded(p).replace("{player}",p.getName()));
		}else {
			if(p == null) return;
			p.performCommand(getHolded(p));
		}
	}
	public String getHolded(Player p){
		if(!HookManager.isPlaceholderAPILoaded()){
		return getContent();
		}
		else {
			return PlaceholderAPI.setPlaceholders(p, getContent());
		}
	}
	public static void perform(List<LobbyCommand> commands , Player clicker, AutomaticTeleportMethod tpMethod) {

		boolean party;
		if(commands.isEmpty()) party = false; else
		 party = commands.get(0).party;
			ITitanPlayer itp = ITitanPlayer.getPlayer(clicker);

			if (party && itp.getParty() != null && itp.getParty().getMembers().size() > 1 && itp.getParty().isCreator(clicker)) {
				itp.getParty().doForAllPlayersFiltered((p) -> {
					for (LobbyCommand lc : commands) {
						lc.perform(clicker);
					}
					//CompMaterial.EGG
					if (tpMethod != null) {
						tpMethod.teleportTo(p.getPlayer());
					}
				});
			}else {
			for (LobbyCommand lc : commands) {
				lc.perform(clicker);
			}
			if (tpMethod != null) {
				tpMethod.teleportTo(clicker);
			}
		}
	}

}
