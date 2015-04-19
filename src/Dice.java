
public class Dice 
{
	private final static int NROFFACES = 6;
	private final static int MIN = 1;
	private int currentVal;
	private boolean lock;
	
	public Dice()
	{
		this.currentVal = 0;
		this.lock = false;
	}
	public void throwDice()
	{
		this.currentVal = (int)( Math.random()* NROFFACES + MIN );
	}
	public int getCurrentVal()
	{
		return currentVal;
	}
	public void setLock()
	{
		if( this.lock == false )
		{
			this.lock = true;
		}
		else
		{
			this.lock = false;
		}
	}
	public void unlockDice()//Used when the game unlocks all dices
	{
		this.lock = false;
	}
	public boolean getLock()
	{
		return this.lock;
	}
	public void reset()
	{
		this.currentVal = 0;
	}
}
