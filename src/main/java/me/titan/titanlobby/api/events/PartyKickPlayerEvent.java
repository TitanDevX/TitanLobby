package me.titan.titanlobby.api.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.titan.titanlobby.api.Party;
import me.titan.titanlobby.api.TitanPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.mineacademy.fo.event.SimpleEvent;


@RequiredArgsConstructor
@Getter
@Setter
public class PartyKickPlayerEvent extends SimpleEvent implements Cancellable {

	final TitanPlayer player;
	final TitanPlayer kicker;

	final Party party;

	boolean cancelled;

	public static HandlerList getHandlerList(){

		return new HandlerList();
	}


	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public @NotNull HandlerList getHandlers() {
		return getHandlerList();
	}
}
