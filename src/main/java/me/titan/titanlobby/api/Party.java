package me.titan.titanlobby.api;

import me.titan.titanlobby.player.ITitanPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a TitanParty party.
 *
 */
public interface Party {

	/**
	 *
	 * Get's the unique Id of this party.
	 *
	 * @return
	 */
	public int getId();


	public ITitanPlayer getCreator();

	/**
	 *
	 * Makes a player join this party. forcefully.
	 * This will handle messages.
	 *
	 * @param p
	 */
	public void addPlayer(Player p);

	/**
	 * Warps party players to a certain match.
	 * This method will only work if {@link TitanMatch#allowJoin(Party)}
	 *
	 */
	public void warpPlayers(TitanMatch match);

	/**
	 * Get's the current match which this party in, or null if the party is not in a match yet..
	 * @return
	 *
	 */
	public @Nullable TitanMatch getCurrentMatch();

	public int getMemberCount();

	/**
	 * called to kick a player from a party by it's party leader.<br>
	 * This also calls {@link #removePlayer(TitanPlayer)}.
	 * <br><b>This does not check for permissions.</b>
	 * @param player
	 */
	public void kickPlayer(TitanPlayer player, TitanPlayer kicker);


	/**
	 *
	 * Make a player leaves a party, forcefully.
	 * @param player
	 */
	public void leavePlayer(TitanPlayer player);
	/**
	 *
	 * Removes a player from a party, regardless if he kicked or he left.
	 *
	 * @param player
	 */
	public void removePlayer(TitanPlayer player);

}
