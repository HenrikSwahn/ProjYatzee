
public interface Game 
{
	String[] getResults();
	void startGame();
	void addPlayer( String name );
	boolean gameIsDone();
	void  nextPlayer();
	
}
