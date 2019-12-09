package me.titan.titanlobby.api;

import org.bukkit.entity.Player;

/**
 * Represents a minigame match, which {@link Party} can join.
 */
public interface TitanMatch {

	/**
	 * The unique id of a game match.
	 *
	 * @return
	 */
	public String getId();

	public JoinResult allowJoin(Party party);

	public JoinResult allowJoin(TitanPlayer player);

	/**
	 *
	 * This method Should handle player joining match, we will use this to join parties to this match.
	 *
	 * @param p - the player that is joining.
	 */
	public void join(Player p);

}
