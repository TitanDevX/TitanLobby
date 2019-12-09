package me.titan.titanlobby.listeners;

import me.titan.lib.Common;
import me.titan.lib.MetaManager;
import me.titan.titanlobby.Util;
import me.titan.titanlobby.join.sign.JoinSign;
import me.titan.titanlobby.join.sign.JoinSignConfig;
import me.titan.titanlobby.staticEnums.Perms;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignListener implements Listener {

	@EventHandler
	public void onEdit(org.bukkit.event.block.SignChangeEvent e){
		Player p = e.getPlayer();
		Sign s = (Sign) e.getBlock().getState();
		String line1 = e.getLine(0);

		if(!Perms.SIGN.has(p))return;

		if(line1.contains(JoinSign.prefix)){
			String[] parts = line1.split(", ");
			if(parts.length <= 1) return;
			String name = parts[1].replace("]", "").replace("[", "");

			if(!JoinSignConfig.signs.containsKey(name)){
				JoinSignConfig cof = new JoinSignConfig(name);
				JoinSign js = new JoinSign(s, cof);
				cof.getJoinSigns().put(s.getLocation(), js);
				js.onPlace(p);
				Common.tell(p, "&a" + Util.chatLine(), "&aYou have successfully created a new sign with the name &c" + cof.getName() +"&a.", "&aNow you can configure it in it's own file inside the &csigns &afolder.", "&a" + Util.chatLine());

				JoinSignConfig.signs.put(name, cof);
			}else {
				JoinSignConfig cof = JoinSignConfig.signs.get(name);
				if(!cof.getJoinSigns().containsKey(s.getLocation())){
					JoinSign js = new JoinSign(s, cof);
					cof.getJoinSigns().put(s.getLocation(), js);
					js.onPlace(p);
					Common.tell(p,"&b" + Util.chatLine(), "&aYou have placed a new sign for the titan sign &c" + cof.getName() +"&a.", "&b" + Util.chatLine());
					JoinSignConfig.signs.put(name, cof);
				}

			}
		}
	}
	@EventHandler
	public void onSignClick(PlayerInteractEvent e){
		Player p = e.getPlayer();

		if(!e.getAction().toString().contains("BLOCK")){

			return;
		}

		Block b = e.getClickedBlock();

		if(!(b.getState() instanceof Sign)){

			return;
		}

		if(!b.hasMetadata(JoinSign.meta)){
			return;
		}

		JoinSign js = (JoinSign) MetaManager.getMetaObject(JoinSign.meta, b);

		js.onClick(p);
	}

}
