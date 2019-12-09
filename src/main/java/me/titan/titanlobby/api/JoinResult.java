package me.titan.titanlobby.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class JoinResult{

	/**
	 * The message which we will tell the creator of the party IF and only IF {@link #allowed} is false.
	 */
	final String message;
	final boolean allowed;


}
