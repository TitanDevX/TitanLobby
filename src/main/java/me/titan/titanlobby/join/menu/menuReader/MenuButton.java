package me.titan.titanlobby.join.menu.menuReader;

import lombok.Getter;
import lombok.Setter;
import me.titan.titanlobby.automaticTeleportMethod.AutomaticTeleportMethod;
import me.titan.titanlobby.commandsReader.CommandReader;
import me.titan.titanlobby.commandsReader.LobbyCommand;
import me.titan.titanlobby.itemCreator.TitanItemCreator;
import me.titan.titanlobby.join.menu.JoinMenuConfig;
import me.titan.titanlobby.player.ITitanPlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.FileUtil;
import org.mineacademy.fo.remain.CompColor;
import org.mineacademy.fo.remain.CompMaterial;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class MenuButton {

	 final String name;
	CompMaterial material;
	int slot;
	 String displayName;

	List<String> lores = new ArrayList<>();
	boolean glow;
	int amount;
	CompColor color;
	int data;
	boolean party;
	AutomaticTeleportMethod tpMethod;

	List<LobbyCommand> commands = new ArrayList<>();

	public static Map<String, MenuButton> buttons = new HashMap<>();

	public MenuButton(String name, CompMaterial material, int slot, String displayName) {
		this.name = name;
		this.material = material;
		this.slot = slot;
		this.displayName = displayName;
	}

	public void onClick(Player clicker){
		ITitanPlayer itp = ITitanPlayer.getPlayer(clicker);

		LobbyCommand.perform(commands, clicker, tpMethod);
	}
	public void setOn( JoinMenuConfig conf) throws IOException {
		File f = FileUtil.getFile("menus/" + conf.getName() + ".yml");
		YamlConfiguration config = FileUtil.loadConfigurationStrict(f);

		ConfigurationSection section = config.createSection("Buttons." + name);
		section.set("Item", this.getMaterial().toString());
		section.set("Glow", false);
		section.set("Display_Name", this.getDisplayName());
		section.set("Lores", this.getLores());
		section.set("Data", 0);
		section.set("Amount", 1);
		section.set("Color", "");
		section.set("Slot", this.getSlot());
		ConfigurationSection s2 = section.createSection("Automatic_Teleport");
		s2.set("Enabled", false);
		s2.set("Location", "world, 100, 100, 100");
		s2.set("Server", "");
		s2.set("Message_On_Teleport", "");
		s2 = section.createSection("Commands");
		s2.set("Run_As_Player_CMDs", new ArrayList<>());
		s2.set("Run_As_Console_CMDs", new ArrayList<>());
		config.save(f);

	}

	public void remove(JoinMenuConfig conf) throws IOException {
		File f= FileUtil.getFile("menus/" + conf.getName() + ".yml");
		YamlConfiguration config = FileUtil.loadConfigurationStrict(f);

		config.set("Buttons." + name, null);
		config.save(f);

		conf.getButtons().remove(this.getName());

	}

	public ItemStack getItemStack(){
		return new TitanItemCreator(material, displayName).lores(lores).amount(amount).data(data).color(color).glow(glow).make();
	}
	public static Map<String, MenuButton> readButtons(ConfigurationSection section){
		Map<String, MenuButton> result = new HashMap<>();
		String path = "";
		for(String name : section.getKeys(false)){
			path =   name + ".";
		CompMaterial mat = CompMaterial.fromString(section.getString(path + "Item"));
			boolean glow = section.getBoolean(path + "Glow");
			String dn = section.getString(path + "Display_Name");
			List<String> lores = section.getStringList(path + "Lores");
			int data = section.getInt(path + "Data");
			int amount = section.getInt(path + "Amount");
			CompColor color = null;
			if(!section.getString(path + "Color").equalsIgnoreCase("none") && !section.getString(path + "Color").isEmpty())
			 color = CompColor.fromName(section.getString(path + "Color"));
			int slot = section.getInt(path + "Slot");
			boolean party = section.getBoolean(path + "Check_Party_On_Click");

			AutomaticTeleportMethod au = AutomaticTeleportMethod.read(section.getConfigurationSection(path + "Automatic_Teleport"));
			List<LobbyCommand> cmds= CommandReader.readCommands(section.getConfigurationSection(path + "Commands"));

			MenuButton mb = new MenuButton(name, mat, slot, dn);
			mb.setLores(lores);
			mb.setData(data);
			mb.setAmount(amount);
			mb.setColor(color);
			mb.setGlow(glow);
			mb.setParty(party);

			if(au.isEnabled()){
				mb.setTpMethod(au);
			}
			mb.setCommands(cmds);

			result.put(name, mb);
		}
		return result;
	}

}
