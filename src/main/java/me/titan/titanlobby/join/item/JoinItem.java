package me.titan.titanlobby.join.item;

import lombok.Getter;
import me.titan.titanlobby.commandsReader.CommandReader;
import me.titan.titanlobby.commandsReader.LobbyCommand;
import me.titan.titanlobby.itemCreator.TitanItemCreator;
import me.titan.titanlobby.join.menu.JoinMenuConfig;
import me.titan.titanlobby.join.menu.LobbyMenu;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.FileUtil;
import org.mineacademy.fo.ItemUtil;
import org.mineacademy.fo.remain.CompColor;
import org.mineacademy.fo.remain.CompMaterial;
import org.mineacademy.fo.settings.YamlConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class JoinItem extends YamlConfig {

	String name;

	CompMaterial item;
	int data;
	CompColor color;
	int amount;
	boolean glow;
	int slot;
	String displayName;
	List<String> lores = new ArrayList<>();
	boolean giveOnJoin;
	String perms;

	String menu;

	List<LobbyCommand> commands = new ArrayList<>();

	public static Map<String, JoinItem> items = new HashMap<>();

	public JoinItem(String name){
		this.name = name;
		loadConfiguration("item.yml", "items/" + name + (name.endsWith(".yml") ? "" : ".yml"));
	}

	@Override
	protected void onLoadFinish() {

		this.item = getMaterial("Item");
		this.slot = getInteger("Slot");
		this.data = getInteger("Data");
		this.color = CompColor.fromName(getString("Color"));
		this.amount = getInteger("Amount");
		this.glow = getBoolean("Glow");
		this.giveOnJoin = getBoolean("Give_On_Join");
		this.perms = getString("Give_Perms");
		this.menu = getString("Menu_To_Open");
		this.displayName = getString("Display_Name");

		this.lores = getListSafe("Lores", String.class);

		this.commands = CommandReader.readCommands(getConfig().getConfigurationSection("Commands"));

	}

	public ItemStack getItemStack(){
		return new TitanItemCreator(item, displayName).data(data).color(color).amount(amount).glow(glow).lores(lores).make();
	}

	public  void giveOnJoin(Player p){
		if(giveOnJoin){
			give(p);
		}
	}

	public static void giveAllOnJoin(Player p){
		if(items.isEmpty()) return;
		items.values().forEach((i) -> {
			if(i.giveOnJoin){
				i.giveOnJoin(p);
			}
		});
	}

	public void give(Player p){
		p.getInventory().setItem(slot, getItemStack());
	}

	public void onClick(Player p){

		if(!menu.isEmpty() && !menu.equalsIgnoreCase("")){
			if(!JoinMenuConfig.menus.containsKey(menu)) return;

			JoinMenuConfig jmc = JoinMenuConfig.menus.get(menu);
			new LobbyMenu(jmc).displayTo(p);
		}
		LobbyCommand.perform(commands, p, null);

	}

	public static void loadAll(){
		items.clear();
		for(File f : FileUtil.getFiles("items", ".yml")){
			JoinItem ji = new JoinItem(f.getName().replace(".yml", ""));

			items.put(ji.getName(), ji);
		}
	}

	public boolean is(ItemStack item){
		return ItemUtil.isSimilar(item, getItemStack());
	}


}
