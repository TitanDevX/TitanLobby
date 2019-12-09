package me.titan.titanlobby.signFill;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.titan.lib.Common;
import org.bukkit.block.Sign;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Getter
@Setter
public class SignFillMethod  {

	final boolean enabled;
	final Map<Integer, String> lines;

	public void applyTo(Sign sign){
		if(!enabled) return;
		for(Map.Entry<Integer, String> en : lines.entrySet()){
			sign.setLine(en.getKey(), Common.colorize(en.getValue()));
		}
		sign.update(true);
	}

	public static SignFillMethod readFrom(ConfigurationSection section){
		boolean enabled = section.getBoolean("Enabled");
		final Map<Integer, String> lines = new HashMap<>();

		for(String num : section.getConfigurationSection("Lines").getKeys(false)){
			String line = section.getString("Lines." + num);
			int numi = Integer.parseInt(num);
			lines.put(numi, line);
		}

		return new SignFillMethod(enabled, lines);
	}

}
