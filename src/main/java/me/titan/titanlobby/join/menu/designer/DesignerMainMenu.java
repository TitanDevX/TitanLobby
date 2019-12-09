package me.titan.titanlobby.join.menu.designer;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.button.Button;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompMaterial;


public class DesignerMainMenu extends Menu {

	Button manageBtn;
	Button createBtn;

	public DesignerMainMenu(){
	setTitle("Titan Menu Designer");

	manageBtn = new Button() {
		@Override
		public void onClickedInMenu(Player player, Menu menu, ClickType click) {

			new MenusMenu().displayTo(player);
		}

		@Override
		public ItemStack getItem() {
			return ItemCreator.of(CompMaterial.DIAMOND_AXE, "&bManage Current Menus", "Opens a menu contains all menus created.").build().make();
		}
	};

	createBtn = new Button() {
		@Override
		public void onClickedInMenu(Player player, Menu menu, ClickType click) {


		}

		@Override
		public ItemStack getItem() {
			return ItemCreator.of(CompMaterial.PAPER, "&6Create New Menu", "You can do that by performing", " command /tl createMenu <name>").build().make();
		}
	};
	}

	@Override
	public ItemStack getItemAt(int slot) {
		if(slot == 11){
			return manageBtn.getItem();
		}else if (slot == 15){
			return createBtn.getItem();
		}
		return null;
	}

	@Override
	protected String[] getInfo() {
		return null;
	}
}
