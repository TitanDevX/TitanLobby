package me.titan.titanlobby.staticEnums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.titan.lib.Common;
import me.titan.titanlobby.api.TitanPlayer;
import org.bukkit.entity.Player;


@RequiredArgsConstructor
@Getter
public enum Perms {

	SIGN("titanlobby.sign"),ITEM_CREATE("titanlobby.itemcreate"),RELOAD("titanlobby.reload"),DESIGN("titanlobby.design"), NPC_CREATE("titanlobby.npccreate"), NPC_SPAWN("titanlobby.npcspawn"), MENU_CREATE("titanlobby.menucreate"), MENU_OPEN("titnalobby.menuopen");


final String perms;

public boolean has(Player p){
	return Common.checkPerm(p, getPerms());
}
public boolean has(TitanPlayer pl){
	return has(pl.getPlayer());
}

}
