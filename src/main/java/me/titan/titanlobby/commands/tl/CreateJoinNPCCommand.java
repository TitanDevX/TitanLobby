package me.titan.titanlobby.commands.tl;

import me.titan.titanlobby.join.joinNPC.JoinNPCConfig;
import me.titan.titanlobby.staticEnums.Perms;
import org.mineacademy.fo.command.SimpleSubCommand;
import org.mineacademy.fo.model.HookManager;

public class CreateJoinNPCCommand extends SimpleSubCommand {
	public CreateJoinNPCCommand() {
		super("createNPC|cn");

		setMinArguments(1);
		setUsage("<name>");
		setDescription("Creates a new join npc (without spawning it), along with it's configuration file.");
		setPermission(Perms.NPC_CREATE.getPerms());
	}

	@Override
	protected void onCommand() {
		checkConsole();


		if(!HookManager.isCitizensLoaded()){

			returnTell("&cCitizens must be enabled to perform this command!");
		}

		String name = args[0];

		if(JoinNPCConfig.get(name) != null){
		returnTell("&cAn npc with this name already exist.");
		}

		JoinNPCConfig con = new JoinNPCConfig(name);
		JoinNPCConfig.npcs.put(name, con);
		tell("&aYou have successfully created a new join npc with the name: &c" + name + "&a.");
	}
}
