package me.titan.titanlobby.join.menu.designer;

import me.titan.titanlobby.join.menu.JoinMenuConfig;
import me.titan.titanlobby.join.menu.designer.conv.*;
import me.titan.titanlobby.join.menu.menuReader.MenuButton;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.button.Button;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.menu.model.MenuClickLocation;
import org.mineacademy.fo.remain.CompMaterial;

import java.io.IOException;

public class MenuManageMenu extends Menu {

	Button dbBtn;
	Button slotBtn;
	Button desBtn;
	Button itemBtn;
	Button removeBtn;
	Button addItems;
	Button addLore;

	JoinMenuConfig jmc;
	MenuButton btn;

	boolean settingItem;
	boolean creatingItem;

	public MenuManageMenu(JoinMenuConfig jmc, MenuButton btn){
	this.jmc = jmc;
	this.btn = btn;

	dbBtn = new Button() {
		@Override
		public void onClickedInMenu(Player player, Menu menu, ClickType click) {
			player.closeInventory();
			new SetDisplayNameConv(btn, jmc).start(player);
		}

		@Override
		public ItemStack getItem() {
			return ItemCreator.of(CompMaterial.PAPER, "&cChange Display Name").build().make();

		}
	};
	slotBtn = new Button() {
		@Override
		public void onClickedInMenu(Player player, Menu menu, ClickType click) {

			new SetSlotConv(btn, jmc).start(player);

		}

		@Override
		public ItemStack getItem() {
			return ItemCreator.of(CompMaterial.ARROW, "&cChange slot").build().make();
		}
	};
	desBtn =  new Button() {
		@Override
		public void onClickedInMenu(Player player, Menu menu, ClickType click) {

			new SetLoreConv(btn, jmc).start(player);
		}

		@Override
		public ItemStack getItem() {
			return ItemCreator.of(CompMaterial.PAPER, "&cChange lore").build().make();
		}
	};
	itemBtn = new Button() {
		@Override
		public void onClickedInMenu(Player player, Menu menu, ClickType click) {
			settingItem = true;
			menu.restartMenu();
		}

		@Override
		public ItemStack getItem() {
			return ItemCreator.of(CompMaterial.ITEM_FRAME, "&cChange Item", "", "You can change the item by", "clicking on this button, then", "clicking on the item from your inventory.",
					"this will only change the item", "this will not effect the lore and display name.").glow(settingItem).build().make();
		}
	};
	removeBtn = new Button() {
		@Override
		public void onClickedInMenu(Player player, Menu menu, ClickType click) {
			try {
				 btn.remove(jmc);
			}catch (IOException ie){
				ie.printStackTrace();
				player.closeInventory();
				Common.tell(player, "&cUnable to remove this button, check console for more information.");
			}
		}

		@Override
		public ItemStack getItem() {
			return ItemCreator.of(CompMaterial.BARRIER, "&cRemove Item").build().make();
		}
	};

	addItems = new Button() {
		@Override
		public void onClickedInMenu(Player player, Menu menu, ClickType click) {


			creatingItem = true;

		}

		@Override
		public ItemStack getItem() {
			return ItemCreator.of(CompMaterial.ARROW, "&bAdd Items", "Add items to the menu.", "Click on this item, then", "Choose item from your inventory.").glow(creatingItem).build().make();
		}
	};

	addLore = new Button() {
		@Override
		public void onClickedInMenu(Player player, Menu menu, ClickType click) {
			new AddLoreConv(btn, jmc).start(player);
		}

		@Override
		public ItemStack getItem() {
			return ItemCreator.of(CompMaterial.PAPER, "&cAdd lore line(s)").build().make();
		}
	};

	}

	@Override
	protected boolean isActionAllowed(MenuClickLocation location, int slot, ItemStack clicked, ItemStack cursor) {

		if(location == MenuClickLocation.PLAYER_INVENTORY){
			if(settingItem){
				creatingItem = false;
			btn.setMaterial(CompMaterial.fromMaterial(clicked.getType()));
				Common.tell(getViewer(), "&aYou have changed the item to &c" + clicked.getType().toString().toLowerCase() + "&a.");
				settingItem = false;
			}
			if(creatingItem){
					settingItem = false;
				creatingItem =false;
					new ItemCreateConv(jmc, CompMaterial.fromMaterial(clicked.getType())).start(getViewer());

					System.out.println(CompMaterial.fromMaterial(clicked.getType()));
			}

		}

		return false;
	}

	@Override
	public ItemStack getItemAt(int slot) {
		if (slot == 2) {
			return dbBtn.getItem();
		}
		if(slot == 6){
			return slotBtn.getItem();
		}
		if(slot == 13){
			return itemBtn.getItem();
		}
		if(slot == 20){
			return desBtn.getItem();
		}

		if(slot == 24){
			return addLore.getItem();
		}
		if(slot == 9){
			return removeBtn.getItem();
		}
		if(slot == 26){
			return addItems.getItem();
		}
		return null;
	}

	@Override
	protected String[] getInfo() {
		return null;
	}
}
