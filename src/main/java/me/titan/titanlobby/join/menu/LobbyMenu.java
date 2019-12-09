package me.titan.titanlobby.join.menu;

import me.titan.titanlobby.join.menu.menuReader.MenuButton;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompMaterial;

import java.util.HashMap;
import java.util.Map;

public class LobbyMenu extends Menu {

	Map<String, MenuButton> buttons = new HashMap<>();

	JoinMenuConfig conf;

	public LobbyMenu(JoinMenuConfig conf) {
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
		if(slot == 0){
			return ItemCreator.of(CompMaterial.EGG).build().make();
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
		cmb.onClick(player);
	}

	@Override
	protected String[] getInfo() {
		return null;
	}
}
