package me.titan.titanlobby.join.menu;

import lombok.Getter;
import me.titan.titanlobby.join.menu.menuReader.MenuButton;
import org.mineacademy.fo.FileUtil;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.settings.YamlConfig;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
@Getter
public class JoinMenuConfig extends YamlConfig {

	String name;

	String menuTitle;
	int menuSize;

	Menu menu;

	Map<String, MenuButton> buttons = new HashMap<>();

	public static Map<String, JoinMenuConfig> menus = new HashMap<>();

	public JoinMenuConfig(String name){
		this.name = name;
		loadConfiguration("join-menu-name.yml", "menus/" + name + (name.endsWith(".yml") ? "" : ".yml"));

		this.menu = new LobbyMenu(this);
	}

	@Override
	protected void onLoadFinish() {
		this.menuTitle = getString("Menu_Title");
		this.menuSize = getInteger("Menu_Size");


		this.buttons = MenuButton.readButtons(getConfig().getConfigurationSection("Buttons"));
	}
	public static void loadAll(){
		menus.clear();
		for(File f : FileUtil.getFiles("menus", "yml")){
			JoinMenuConfig jmc = new JoinMenuConfig(f.getName().replace(".yml", ""));

			menus.put(f.getName().replace(".yml", ""), jmc);

		}
	}
}
