import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class Yatzy implements Game
{
	private ArrayList<Player> players;
	private ArrayList<Dice> dices;
	private Rules yatzyRules;
	private int currentPlayer;
	private int throwsForCurrentPlayer;
	private int round;
	private String[] highScoreNames;
	private int[] highScorePoints; 

	public Yatzy()
	{
		this.players = new ArrayList<Player>();
		this.dices = new ArrayList<Dice>();
		this.yatzyRules = new Rules();
		this.highScoreNames = new String[10];
		this.highScorePoints = new int[10];
		getHighScore();
	
		for( int i = 0; i < 5; i++ )//Creates five dices
		{
			dices.add( new Dice() );
		}
	}
	public void throwDices()
	{
		//Throw the dices if they aren't locked
		for( int i = 0; i < dices.size(); i++ )
		{
			if( !this.dices.get(i).getLock() )
			{
				this.dices.get(i).throwDice();
			}
		}
		//Count downs nr of throws left for the player
		this.throwsForCurrentPlayer--;
	}
	public void lockDices( int diceNrToLock )
	{
		//lockes the chosen dice
		this.dices.get( diceNrToLock ).setLock();
	}
	public void unlockSpecficDice( int diceToUnlock )
	{
		//Unlocks a specific dice
		this.dices.get( diceToUnlock).setLock();
	}
	public void unLockAllDices()
	{
		//Unocks all dices
		for( int i = 0; i < this.dices.size(); i++ )
		{
			this.dices.get(i).unlockDice();
		}
	}
	public int deliverCurrentPlayer()
	{
		//Deliver a int representing current player
		return this.currentPlayer;
	}
	public int deliverNrOfPlayers()
	{
		//Delivers a int for the nrOfPlayers
		return this.players.size();
	}
	public String deliverPlayerNameOnPos( int posOfPlayer )
	{
		//Delivers a String representation of the players name found in players a posOfPlayer
		return this.players.get( posOfPlayer).getName();
	}
	public String[] deliverProtocolForAPlayer( int posOfPlayer )
	{
		//Creates a protocol
		String[] protocol = { "Ettor: ",
							  "Tv�or: ",
							  "Treor: ",
							  "Fyror: ",
							  "Femor: ",
							  "Sexor: ",
							  "Sum: ",
							  "Bonus: ",
							  "Ett par: ",
							  "Tv� par: ",
							  "Triss: ",
							  "Fyrtal: ",
							  "K�k: ",
							  "Liten stege: ",
							  "Stor stege: ",
							  "Chans: ",
							  "Yatzy: ",
							  "Total: " };
		String[] playerProtocol = new String[protocol.length];
		//Merges it with the players points for each part of the protocol
		for( int i = 0; i < protocol.length; i++ )
		{
			if( this.players.get( posOfPlayer ).getPoints( i ) != -1 )
			{
				playerProtocol[i] = protocol[i] + players.get( posOfPlayer ).getPoints( i );
			}
			else if( this.players.get( posOfPlayer ).getPoints( i ) == -1 )
			{
				playerProtocol[i] = protocol[i] + "X";
			}
		}
		return playerProtocol;			
	}
	public int[] deliverDiceVals()
	{
		//Delivers an in array with int representing the dice vals
		int[] diceVals = new int[this.dices.size()];
		
		for( int i = 0; i < diceVals.length; i++ )
		{
			diceVals[i] = this.dices.get(i).getCurrentVal();
		}
		return diceVals;
	}
	public int deliverNrOfThrowsLeftForCurrentPlayer()
	{
		//Delivers how many thros left the players has
		return this.throwsForCurrentPlayer;
	}
	public boolean givePointsToPlayer( int protocolSelection )
	{
		boolean pointGiven = false;
		if( this.players.get(this.currentPlayer).getPoints( protocolSelection ) == 0 )
		{
			int counter = 0;
			int points = 0;
			for( int i = 0; i < this.dices.size(); i++ )
			{
				if(  this.dices.get(i).getLock() )
				{
					//System.out.println( i );
					counter++;
				}
			}
			int[] diceVals = new int[counter];
			counter = 0;
			for( int i = 0; i < this.dices.size(); i++)
			{
				if( this.dices.get(i).getLock() )
				{
					diceVals[counter++] = this.dices.get(i).getCurrentVal();
					points += this.dices.get(i).getCurrentVal();
				}
			}
			if( this.yatzyRules.routeIncomingVals(diceVals, protocolSelection) )
			{
				this.players.get( this.currentPlayer).setPoints( protocolSelection, points );
				System.out.println( protocolSelection + " " + points);
				pointGiven = true;
			}
		}
		return pointGiven;
	}
	public boolean skip( int protocolSelection )
	{
		boolean skipped = false;
		if( this.players.get( this.currentPlayer).getPoints( protocolSelection )== 0 )
		{
			this.players.get( this.currentPlayer ).setPoints( protocolSelection, -1);
			skipped = true;
		}
		return skipped;
	}
	public void resetDices()
	{
		for( int i = 0; i < this.dices.size(); i++ )
		{
			this.dices.get(i).reset();
		}
	}

	@Override
	public void nextPlayer()
	{
		this.currentPlayer = ( this.currentPlayer +1 )%this.players.size();
		this.throwsForCurrentPlayer = 3;
		if( this.currentPlayer == 0 )
		{
			this.round++;
		}
	}
	@Override
	public boolean gameIsDone()
	{
		boolean returnBool = false;
		if( this.round >= 15 )
		{
			printHighScore();
			returnBool = true;
		}
		return returnBool;
	}
	@Override
	public void addPlayer( String name )
	{
		//Adds player
		this.players.add( new Player( name ) );
	}
	@Override
	public String[] getResults()
	{
		String[] playerNames = new String[this.players.size()];
		int[] playerPoints = new int[this.players.size()];
		
		
		for( int i = 0; i < this.players.size(); i++ )
		{
			playerNames[i] = this.players.get(i).getName();
			playerPoints[i] = this.players.get(i).getTotalPoints();
		}
		
		this.sortPointsAndNames(playerNames, playerPoints);
		String[] results = new String[playerPoints.length];
		for( int i = 0; i < playerPoints.length; i++ )
		{
			results[i] = (i+1) + "." + playerNames[i] + " with " + playerPoints[i] + " pts";
		}
		return results;
	}
	@Override
	public void startGame() 
	{
		this.currentPlayer = 0;
		this.throwsForCurrentPlayer = 3;
		this.round = 13;
	}
	
	private void sortPointsAndNames(String[] playerNames, int[] playerPoints )
	{
		for( int i = 0; i < playerPoints.length; i++ )
		{
			int maxValue = playerPoints[i];
			int maxIndex = i;
			{
				for( int j = i+1; j < playerPoints.length; j++ )
				{
					if (playerPoints[j] > maxValue )
					{
						maxValue = playerPoints[j];
						maxIndex = j;
					}
				}
				if( maxIndex != i)
				{
					int intTemp = playerPoints[i];
					String strTemp = playerNames[i];
					playerPoints[i] = playerPoints[maxIndex];
					playerNames[i] = playerNames[maxIndex];
					playerPoints[maxIndex] = intTemp;
					playerNames[maxIndex] = strTemp;
				}
			}
		}	
	}
	public String[] delivereHighscores()
	{
		String[] deliverArr = new String[this.highScoreNames.length];
		
		for( int i = 0; i < deliverArr.length; i++ )
		{
			deliverArr[i] = (i+1) + ": " + this.highScoreNames[i] + " " + this.highScorePoints[i] + " pts";
		}
		return deliverArr;
	}
	private void checkIfPointsBelongOnScoreBoard()
	{
		for( int i = 0; i < this.players.size(); i++ )
		{
			boolean loopBreaker = false;
			for( int j = 0; j < this.highScorePoints.length && loopBreaker == false; j++ )
			{
				if( this.players.get( i ).getTotalPoints() >= this.highScorePoints[j] )
				{
					insertScoreToHighScores( i,j );
					loopBreaker = true;
				}
			}
		}
	}
	private void insertScoreToHighScores( int player, int pos )
	{
		String thePlayer = this.players.get( player).getName();
		int thePlayerPoints = this.players.get( player).getTotalPoints();
		if( pos == this.highScorePoints.length-1 )
		{
			this.highScoreNames[pos] = thePlayer;
			this.highScorePoints[pos] = thePlayerPoints;
		}
		else
		{
			for( int i = this.highScorePoints.length-1; i > pos ; i-- )
			{
				if( pos > 0 )
				{
					this.highScoreNames[i] = this.highScoreNames[i-1];
					this.highScorePoints[i] = this.highScorePoints[i-1];
				}
			}
			this.highScoreNames[pos] = thePlayer;
			this.highScorePoints[pos] = thePlayerPoints;
		}
	}
	@SuppressWarnings("resource")
	private void getHighScore()
	{
		try
		{
			Scanner in = new Scanner( new File( "HighScores.txt" ) );
			int index = 0;
			String temp;
			while( in.hasNext() && index < 10 )
			{
				this.highScoreNames[index] = in.next();
				temp = in.next();
				this.highScorePoints[index++] = Integer.parseInt( temp );
			}
			for( int i = 0; i < 10; i++ )
			{
				System.out.println( this.highScoreNames[i] + this.highScorePoints[i] );
			}
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println( "Highscore file not found" );
		}
	}
	private void printHighScore()
	{
		checkIfPointsBelongOnScoreBoard();
		try
		{
			PrintWriter out = new PrintWriter( "HighScores.txt" );
			for( int i = 0; i < this.highScoreNames.length; i++ )
			{
				out.println( this.highScoreNames[i] + " " + this.highScorePoints[i] + "\n" );
			}
			out.close();
		} 
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
