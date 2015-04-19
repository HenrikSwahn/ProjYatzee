
public class Player 
{
	private String name;
	private int[] pts;
	private int totalPoints;
	private int pointsOnFirstPart;
	private boolean hasBonus;
	private boolean done;
	
	public Player( String name )
	{
		super();
		this.name = name;
		this.pts = new int[18];
		for( int i = 0; i < pts.length; i++ )
		{
			pts[i] = 0;
		}
		this.totalPoints = 0;
		this.pointsOnFirstPart = 0;
		this.hasBonus = false;
	}
	public String getName() 
	{
		return name;
	}
	public void setName( String name ) 
	{
		this.name = name;
	}
	public void setPoints( int pos, int point ) 
	{
		if( point != -1 )
		{
			this.totalPoints += point;
		}
		if( pos >= 0 && pos <= 5 )
		{
			if( point != -1 )
			{
				this.pointsOnFirstPart+= point;
			}
		}
		if( this.hasBonus == false )
		{
			this.setBonus( pointsOnFirstPart);
		}
		this.pts[pos] = point;
		this.pts[6] = this.pointsOnFirstPart;
		this.pts[17] = this.totalPoints;
	}
	public int getPoints( int pos )
	{
		return this.pts[pos];
	}
	public int getPointsOnFirstPart()
	{
		return this.pointsOnFirstPart;
	}
	public int getTotalPoints() 
	{
		return totalPoints;
	}
	@Override
	public String toString()
	{
		return this.name + " : " + this.totalPoints + "\n";
	}
	public boolean getDone()
	{
		return this.done;
	}
	private void setBonus( int pointsOnFirstPart )
	{
		if( pointsOnFirstPart >= 63 )
		{
			this.pts[7] = 50;
			this.hasBonus = true;
			this.totalPoints += 50;
		}
	}
}
