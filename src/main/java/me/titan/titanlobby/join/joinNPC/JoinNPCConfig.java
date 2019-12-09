package me.titan.titanlobby.join.joinNPC;

import lombok.Getter;
import me.titan.titanlobby.commandsReader.CommandReader;
import me.titan.titanlobby.commandsReader.LobbyCommand;
import org.bukkit.entity.EntityType;
import org.mineacademy.fo.FileUtil;
import org.mineacademy.fo.settings.YamlConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class JoinNPCConfig extends YamlConfig {

	String name;

	String npcName;
	EntityType npcType;

	List<LobbyCommand> commands = new ArrayList<>();

	public static Map<String , JoinNPCConfig> npcs = new HashMap<>();

	JoinNPC npc;

	public JoinNPCConfig(String name){

		this.name = name;

		loadConfiguration("join-npc-name.yml", "npcs/join-npc-" + name + (name.endsWith(".yml") ? "" : ".yml"));

		npc = new JoinNPC(this);


	}

	public static JoinNPCConfig get(String name){
		return npcs.get(name);
	}

	@Override
	protected void onLoadFinish() {
		this.npcName = getString("NPC_Display_Name");
		this.npcType = get("NPC_Type", EntityType.class);

		this.commands = CommandReader.readCommands(getConfig().getConfigurationSection("Commands"));
	}
	public static void loadAll(){
		for(File f : FileUtil.getFiles("npcs", "yml")){
			JoinNPCConfig npc = new JoinNPCConfig(getNameFromFile(f));

			npcs.put(npc.getName(), npc);
		}
	}

	public static String getNameFromFile(File f){
		return f.getName().replace("join-npc-", "").replace(".yml", "");
	}

}
