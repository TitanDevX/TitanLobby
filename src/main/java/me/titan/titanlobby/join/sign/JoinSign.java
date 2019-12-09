package me.titan.titanlobby.join.sign;

import lombok.Getter;
import lombok.Setter;
import me.titan.lib.MetaManager;
import me.titan.titanlobby.commandsReader.LobbyCommand;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.mineacademy.fo.FileUtil;
import org.mineacademy.fo.SerializeUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class JoinSign {

	public static String meta = "TITAN_SIGN";

	public static String prefix = "[TLSIGN]";

	final Sign sign;
	final JoinSignConfig config;


	public static Map<Location, JoinSign> signs = new HashMap<>();

	public JoinSign(Sign sign,JoinSignConfig config){
		this.sign = sign;
		this.config = config;

		signs.put(sign.getLocation(), this);

		MetaManager.setMetaData(sign, meta, this);
	}

	public void save(){
		File f= FileUtil.getFile("signs/" + config.getName() + ".yml");
		YamlConfiguration conf = FileUtil.loadConfigurationStrict(f);

		List<String> oldList = new ArrayList<>();
		if(conf.contains("Sign.Locations")){
			oldList = conf.getStringList("Sign.Locations");
		}
		oldList.add(SerializeUtil.serializeLoc(this.getSign().getLocation()));
		conf.set("Sign.Locations", oldList);

		try {
			conf.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void onPlace(Player p){
		if(config.getFillMethod() != null && config.fillMethod.isEnabled()){
			config.getFillMethod().applyTo(this.getSign());
		}


	}

	public void onClick(Player p){
		LobbyCommand.perform(config.getCommands(),p,config.getTeleportMethod());
	}


}
