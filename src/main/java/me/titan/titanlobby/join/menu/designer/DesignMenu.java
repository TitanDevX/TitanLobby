package me.titan.titanlobby.join.menu.designer;

import me.titan.titanlobby.join.menu.JoinMenuConfig;
import me.titan.titanlobby.join.menu.menuReader.MenuButton;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.Menu;

import java.util.HashMap;
import java.util.Map;


public class DesignMenu  extends Menu {
	Map<String, MenuButton> buttons = new HashMap<>();
	JoinMenuConfig conf;
	public DesignMenu(JoinMenuConfig conf){
		this.conf = conf;
		setTitle(conf.getMenuTitle());
		setSize(conf.getMenuSize());
		buttons = conf.getButtons();


	}

	@Override
	public ItemStack getItemAt(int slot) {

		for(MenuButton mb : buttons.values()){
			if(slot == mb.getSlot()){

				return mb.getItemStack();
			}
		}
		return null;
	}

	@Override
	protected void onMenuClick(Player player, int slot, ItemStack clicked) {
		MenuButton cmb = null;
		for(MenuButton mb : buttons.values()){
			if(mb.getSlot() == slot){
				cmb = mb;
			}
		}
		if(cmb == null) return;
		new MenuManageMenu(this.conf, cmb).displayTo(player);
	}

	@Override
	protected String[] getInfo() {
		return null;
	}
}
