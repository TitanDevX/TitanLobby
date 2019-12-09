package me.titan.titanlobby.join.sign;

import lombok.Getter;
import lombok.Setter;
import me.titan.titanlobby.automaticTeleportMethod.AutomaticTeleportMethod;
import me.titan.titanlobby.commandsReader.CommandReader;
import me.titan.titanlobby.commandsReader.LobbyCommand;
import me.titan.titanlobby.signFill.SignFillMethod;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.YamlConfiguration;
import org.mineacademy.fo.FileUtil;
import org.mineacademy.fo.SerializeUtil;
import org.mineacademy.fo.settings.YamlConfig;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class JoinSignConfig extends YamlConfig {

	String name;

	boolean clearPrefix;

	SignFillMethod fillMethod;
	AutomaticTeleportMethod teleportMethod;
	List<LobbyCommand> commands;

	Map<Location, JoinSign> joinSigns = new HashMap<>();

	public static Map<String, JoinSignConfig> signs = new HashMap<>();

	public JoinSignConfig(String name){
		this.name = name;
		loadConfiguration("sign.yml", "signs/" + name + (name.endsWith(".yml") ? "" : ".yml"));
	}

	@Override
	protected void onLoadFinish() {

		clearPrefix = getBoolean("Clear_Name_On_Place");
		fillMethod = SignFillMethod.readFrom(getConfig().getConfigurationSection("Automatic_Fill"));
		teleportMethod = AutomaticTeleportMethod.read(getConfig().getConfigurationSection("Automatic_Teleport"));
		commands = CommandReader.readCommands(getConfig().getConfigurationSection("Commands"));

	}

	public static void loadAll(){
		signs.clear();
		for(File f : FileUtil.getFiles("signs", "yml")){
			JoinSignConfig js = new JoinSignConfig(f.getName().replace(".yml", ""));

			File fi= FileUtil.getFile("signs/" + js.getName() + ".yml");
			YamlConfiguration config = FileUtil.loadConfigurationStrict(fi);

			if(config.contains("Sign") && config.contains("Sign.Locations")){
				for(String ln : config.getStringList("Sign.Locations")) {
					Location loc = SerializeUtil.deserializeLocation(ln);
					if (!(loc.getBlock().getState() instanceof Sign)) {
						continue;
					}

					Sign s = (Sign) loc.getBlock().getState();


					js.getJoinSigns().put(s.getLocation(), new JoinSign(s,js));
				}

			}


			signs.put(js.getName(), js);
		}
	}




}
