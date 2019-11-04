package Database;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Class to represent a PlayStation game trophy. A trophy comes in
 * four ranks: bronze, silver, gold and platinum. The date the trophy was
 * earned and its respective game is also stored.
 * Created for Data Structures, SP2 2017
 * @author James Baumeister
 * @version 1.0
 */
public class Trophy {
	private String name;
	private Rank rank;
	private Rarity rarity;
	private Calendar obtained;
	private Game game;
    public enum Rank {
		BRONZE, GOLD, SILVER, PLATINUM

	}

	public enum Rarity {
		COMMON, UNCOMMON, RARE, VERY_RARE, ULTRA_RARE

	}

	public Trophy() {}

    public Trophy(String name, Rank rank, Rarity rarity, Calendar obtained, Game game) {
    	this.name = name;
    	this.rank = rank;
    	this.rarity = rarity;
    	this.obtained = obtained;
    	this.game = game;
    	
    }
    public String toString() {
    	SimpleDateFormat date = new SimpleDateFormat("MMM dd, yyyy",  Locale.ENGLISH); 
    	Date dt = obtained.getTime();
    	String day = date.format(dt);
		return '"' + name + '"' + ", rank: " + rank + ", rarity: " + rarity + ", obtained on: " + day;
    }
    
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	public Rank getRank() {
		// TODO Auto-generated method stub
		return this.rank;
	}

	public Rarity getRarity() {

		// TODO Auto-generated method stub
		return this.rarity;
	}

	public Calendar getObtained() {

		// TODO Auto-generated method stub
		return this.obtained;
	}
	
	public Game getGame() {
		// TODO Auto-generated method stub
		return this.game;
	}
	public boolean equals(Trophy t){
		if (this.getName() == t.getName()){
			if(this.getRank() == t.getRank()){
				if(this.getRarity() == t.getRarity()){
					if(this.getObtained() == t.getObtained()){
						if(this.getGame() == t.getGame()){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
