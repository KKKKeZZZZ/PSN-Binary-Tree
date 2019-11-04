package Database;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Class to represent a PlayStation game.
 * Created for Data Structures, SP2 2017
 * @author James Baumeister
 * @version 1.0
 */
public class Game {
	private String name;
	private Calendar released;
	private int totalTrophies;
	private Game next;
    public Game() {}

    public Game(String name, Calendar released, int totalTrophies) {
    	this.name = name;
    	this.released = released;
    	this.totalTrophies = totalTrophies;
    }

    public String toString() {
    	// use format to get the "MMM dd, yyyy"format
    	SimpleDateFormat date = new SimpleDateFormat("MMM dd, yyyy",  Locale.ENGLISH); 
    	Date dt = released.getTime();
    	String day = date.format(dt); //trans the date to the format
		return '"' + name + '"' + ", released on: " + day;
    }
    
    public boolean equals(Game o) {
    	if (this.getName() == o.getName()){
    		
    		if(this.getReleased() == o.getReleased()){
    			
    			if(this.getTotalTrophies() == o.getTotalTrophies()){
    				
    				return true;
    			}
    		}
    	}
		return false;
    }

	public Calendar getReleased() {
		// TODO Auto-generated method stub
		return this.released ;
	}

	public int getTotalTrophies() {
		// TODO Auto-generated method stub
		return this.totalTrophies ;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	public void setNext(Game g2) {
		this.next = g2;
	}
	public Game getNext() {
		// TODO Auto-generated method stub
		return this.next;
	}
}
