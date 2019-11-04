package Database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Class to represent a PlayStation user.
 * Created for Data Structures, SP2 2017
 * @author James Baumeister
 * @version 1.0
 */
public class User {
	boolean isVisted = false;
    private String username;
	private int level;
	private double key;
	private ArrayList<Trophy> trophies;
	private GameList games;
	private Calendar dob;
	private User left;
	private User right;
	public User root;
	public int height = 0;
	
	public User(String username, Calendar dob, int level) {
		this.username = username;
		this.dob = dob;
		this.level = level;
		this.height = 0;
    }

    /**
     * DO NOT MODIFY THIS METHOD
     * This method uses the username and level to create a unique key.
     * As we don't want the username's hash to increase the level, it's first converted
     * to a floating point, then added to the level.
     * @return the unique key
     */
    public double calculateKey() {
        int hash = Math.abs(username.hashCode());
        // Calculate number of zeros we need
        int length = (int)(Math.log10(hash) + 1);
        // Make a divisor 10^x
        double divisor = Math.pow(10, length);
        // Return level.hash
        return level + hash / divisor;
    }

    public String toString() {// for div to store all the message
    	SimpleDateFormat date = new SimpleDateFormat("MMM dd, yyyy",  Locale.ENGLISH); 
    	Date dt = dob.getTime();
    	String day = date.format(dt);
    	String div1 = "User: " + username + "\n" ;
    	String div2 = "\nTrophies: \n";
    	String div3 = "\nGames: \n";
    	String div4 = "\nBirth Date: " + day;
    	for(int i=0; i<trophies.size(); i++){//get all the trophy
            div2 += trophies.get(i) + "\n";
    	}
    	Game test = games.head;
    	while (test!= null){// get all the game
    		div3 += test + "\n";
    		test = test.getNext();
    	}
		return div1 + div2 + div3 + div4;
		}

	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	public Calendar getDob() {
		// TODO Auto-generated method stub
		return this.dob ;
	}

	public int getLevel() {
		// TODO Auto-generated method stub
		return this.level ;
	}

	public double getKey() {
		key = this.calculateKey();
		// TODO Auto-generated method stub
		return this.key;
	}

	public void setGames(GameList games) {
		this.games = games;
		// TODO Auto-generated method stub
		
	}

	public void setTrophies(ArrayList<Trophy> trophies) {
		this.trophies = trophies;
		// TODO Auto-generated method stub
		
	}
	public void setLeft(User faust) {
		this.left = faust;
		// TODO Auto-generated method stub
		
	}

	public void setRight(User pippin) {
		this.right = pippin;
		// TODO Auto-generated method stub
		
	}

	public User getLeft() {
		// TODO Auto-generated method stub
		return this.left ;
	}

	public User getRight() {
		// TODO Auto-generated method stub
		return this.right;
	}

	public ArrayList<Trophy> getTrophies() {
		// TODO Auto-generated method stub
		return this.trophies ;
	}

	public GameList getGames() {
		// TODO Auto-generated method stub
		return this.games;
	}
	public boolean equals(User u){
		if (this.getUsername() ==u.getUsername()){
			if(this.getDob() == u.getDob()){
				if(this.getLevel() == u.getLevel()){
					return true;
				}
			}
		}
		return false;
	}
}
