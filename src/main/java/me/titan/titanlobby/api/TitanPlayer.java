package me.titan.titanlobby.api;

import me.titan.titanlobby.api.events.PlayerPartyInviteReceiveEvent;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface TitanPlayer {

	public UUID getUniqueId();
	public Player getPlayer();

	/**
	 *
	 * Used to get the player name even if he is offline.
	 *
	 * @return
	 */
	public String getName();

	public Party getParty();

	/**
	 *
	 * If false, this player will no longer receive any party invites.
	 *
	 *
	 * @param to
	 */
	public void setCanReceivePartyInvites(boolean to);

	public boolean canReceivePartyInvites(Player from);


	/**
	 *
	 * Sends this player a party invite, regardless for if the receiver can receive invites from the sender.
	 * <br>Note: this method will make this player receive an invite, and not send one.
	 *
	 * @param invite
	 */
	public void receivePartyInvite(PlayerPartyInviteReceiveEvent invite);

	/**
	 * Calling this method will force this player to accept this party invite.
	 * @param invite
	 */
	public void acceptPartyInvite(PlayerPartyInviteReceiveEvent invite);


}
