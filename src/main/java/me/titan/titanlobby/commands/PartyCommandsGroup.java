package me.titan.titanlobby.commands;

import me.titan.titanlobby.commands.party.*;
import org.mineacademy.fo.command.SimpleCommandGroup;

public class PartyCommandsGroup extends SimpleCommandGroup {
	@Override
	protected void registerSubcommands() {


		//Common.runLater(20, () -> {
			registerSubcommand(new PartyInviteCommand());
			registerSubcommand(new PartyAcceptCommand());
			registerSubcommand(new PartyListCommand());
			registerSubcommand(new PartyKickCommand());
			registerSubcommand(new PartyLeaveCommand());
			registerSubcommand(new PartySettingsCommand());
		//});

	}

	@Override
	protected String getCredits() {
		return "&7Run &r/party ? &7for commands list.";
	}
}
