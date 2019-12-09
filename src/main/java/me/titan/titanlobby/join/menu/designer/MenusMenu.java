package me.titan.titanlobby.join.menu.designer;

import me.titan.titanlobby.join.menu.JoinMenuConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.MenuPagged;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompMaterial;

public class MenusMenu extends MenuPagged<JoinMenuConfig> {
	protected MenusMenu() {
		super(JoinMenuConfig.menus.values());
	}

	@Override
	protected ItemStack convertToItemStack(JoinMenuConfig item) {
		return ItemCreator.of(CompMaterial.BOOK, "&6" + item.getName(), "", "&aButtons Amount: &r" + item.getButtons().size(), "&aSize: &r" + item.getMenuSize(), "&aTitle: &r" + item.getMenuTitle(), "&cClick to manage.").build().make();
	}

	@Override
	protected void onPageClick(Player player, JoinMenuConfig item, ClickType click) {

		new DesignMenu(item).displayTo(player);
	}

	@Override
	protected String[] getInfo() {
		return null;
	}
}
