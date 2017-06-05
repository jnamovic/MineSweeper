package edu.chatham.games;

public enum Difficulty_ {BEGINNER,INTERMEDIATE,EXPERT;
	/**
	 * Returns the name of the difficulty in a string
	 * @return name of the difficulty 
	 */
	public String toString(){
		switch(name()){
		case"BEGINNER": return "beginner";
		case"INTERMEDIATE": return "intermediate";
		case"EXPERT": return "expert";
		
		}
		return "";
	}
	}