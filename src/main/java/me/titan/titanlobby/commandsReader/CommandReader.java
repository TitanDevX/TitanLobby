package me.titan.titanlobby.commandsReader;

import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class CommandReader  {

	public static List<LobbyCommand> readCommands(ConfigurationSection section){

		List<LobbyCommand> result = new ArrayList<>();
		boolean party = section.getBoolean("Check_Party_On_Click");
		for(String p : section.getStringList("Run_As_Player_CMDs")){
			result.add(new LobbyCommand(p, CommandType.RUN_PLAYER, party));
		}
		for(String c : section.getStringList("Run_As_Console_CMDs")){
			result.add(new LobbyCommand(c, CommandType.RUN_CONSOLE, party));
		}
		return result;
	}

}
