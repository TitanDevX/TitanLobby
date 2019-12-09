package me.titan.titanlobby.commands.tl;

import me.titan.titanlobby.join.joinNPC.JoinNPCConfig;
import me.titan.titanlobby.staticEnums.Perms;
import org.mineacademy.fo.command.SimpleSubCommand;
import org.mineacademy.fo.model.HookManager;

public class SpawnJoinNPCCommand extends SimpleSubCommand {
	public SpawnJoinNPCCommand() {
		super("spawnNPC|sn");

		setMinArguments(1);
		setUsage("<name>");
		setDescription("Spawns a titanLobby npc.");
		setPermission(Perms.NPC_SPAWN.getPerms());
	}

	@Override
	protected void onCommand() {
		checkConsole();

		if(!HookManager.isCitizensLoaded()){

			returnTell("&cCitizens must be enabled to perform this command!");
		}

		String name = args[0];

		if(JoinNPCConfig.get(name) == null){
		returnTell("&cUnable to find an npc with this name.");
		}

		JoinNPCConfig con = JoinNPCConfig.get(name);
		con.getNpc().spawn(getPlayer().getLocation());
		tell("&aSpawned the npc: &c" + name + "&a.");
	}
}
