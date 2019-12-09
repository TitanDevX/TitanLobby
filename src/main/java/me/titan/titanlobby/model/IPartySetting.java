package me.titan.titanlobby.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter

public enum IPartySetting {

	ALL_INVITE("All_invite", "Allow your party players to invite their friends.");

	final String key;
	final String description;

	public void add(IParty party){
		party.getSettings().add(this);
	}
	public boolean has(IParty party){
		return party.getSettings().contains(this);
	}

	public void remove(IParty party){
		 party.getSettings().remove(this);
	}


}
