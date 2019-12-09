package me.titan.titanlobby.commands;

import me.titan.titanlobby.commands.tl.*;
import org.mineacademy.fo.command.SimpleCommandGroup;

public class TitanLobbyCommands extends SimpleCommandGroup {
	@Override
	protected void registerSubcommands() {
		registerSubcommand(new CreateJoinNPCCommand());
		registerSubcommand(new SpawnJoinNPCCommand());

		registerSubcommand(new CreateJoinMenu());
		registerSubcommand(new OpenMenuCommand());
		registerSubcommand(new ReloadCommand());
		registerSubcommand(new DesignCommand());
		registerSubcommand(new CreateJoinItem());
		registerSubcommand(new GiveJoinItem());

	}
}
