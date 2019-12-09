package me.titan.titanlobby.core;

import me.titan.lib.TitanLib;
import me.titan.titanlobby.commands.PartyCommandsGroup;
import me.titan.titanlobby.commands.TitanLobbyCommands;
import me.titan.titanlobby.config.Settings;
import me.titan.titanlobby.join.item.JoinItem;
import me.titan.titanlobby.join.joinNPC.JoinNPCConfig;
import me.titan.titanlobby.join.menu.JoinMenuConfig;
import me.titan.titanlobby.join.sign.JoinSign;
import me.titan.titanlobby.join.sign.JoinSignConfig;
import me.titan.titanlobby.listeners.NPCListener;
import me.titan.titanlobby.listeners.PlayerListener;
import me.titan.titanlobby.listeners.SignListener;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.model.HookManager;
import org.mineacademy.fo.plugin.SimplePlugin;
import org.mineacademy.fo.settings.YamlStaticConfig;

import java.util.Arrays;
import java.util.List;

public class TitanLobbyPlugin extends SimplePlugin {
	private SimpleCommandGroup mainCmd = new TitanLobbyCommands();


	@Override
	protected void onPluginStart() {

		TitanLib.setPlugin(this);

		loadCitizensStuff();
		JoinMenuConfig.loadAll();
	    JoinItem.loadAll();
		JoinSignConfig.loadAll();


	registerEvents(new PlayerListener());
	registerEvents(new SignListener());
		Common.runLater(20 * 5, new Runnable() {
			@Override
			public void run() {
				registerCommands("party|p|par",new PartyCommandsGroup());

			}
		});
	}

	public void loadCitizensStuff(){
		if(!HookManager.isCitizensLoaded()) return;

		registerEvents(new NPCListener());

		JoinNPCConfig.loadAll();
	}

	@Override
	protected void onPluginStop() {
		for(JoinSign sign : JoinSign.signs.values()){
			System.out.println("Saving signs...");
			sign.save();
		}
	}

	@Override
	public SimpleCommandGroup getMainCommand() {
		return mainCmd;
	}

	public static void reloadAll(){
		if(HookManager.isCitizensLoaded()){
			JoinMenuConfig.loadAll();
		}
		JoinMenuConfig.loadAll();
		JoinItem.loadAll();
		JoinSignConfig.loadAll();
	}

	@Override
	public List<Class<? extends YamlStaticConfig>> getSettings() {
		return Arrays.asList(Settings.class);
	}
}
