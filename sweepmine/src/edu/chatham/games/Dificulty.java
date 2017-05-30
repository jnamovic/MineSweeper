package edu.chatham.games;

public enum Dificulty 
{BEGINNER,INTERMEDIATE,EXPERT;
	public String toString(){
		switch(name()){
		case"BEGINNER": return "beginner";
		case"INTERMEDIATE": return "intermediate";
		case"EXPERT": return "expert";
		
		}
		return "";
	}

}
