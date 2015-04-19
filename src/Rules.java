
public class Rules
{
	public boolean routeIncomingVals( int[] diceVals, int protocolSelection )
	{
		boolean returnBool = false;
		
		switch( protocolSelection )
		{
		case 0:
			returnBool = this.ones(diceVals);
			break;
		case 1:
			returnBool = this.twos(diceVals);
			break;
		case 2:
			returnBool = this.threes(diceVals);
			break;
		case 3:
			returnBool = this.fours(diceVals);
			break;
		case 4:
			returnBool = this.fives(diceVals);
			break;
		case 5:
			returnBool = this.sixths(diceVals);
			break;
		case 8:
			returnBool = this.pairs(diceVals);
			break;
		case 9:
			returnBool = this.twoPairs(diceVals);
			break;
		case 10:
			returnBool = this.threeOfAKind(diceVals);
			break;
		case 11:
			returnBool = this.fourOfAKind(diceVals);
			break;
		case 12:
			returnBool = this.fullHouse(diceVals);
			break;
		case 13:
			returnBool = this.smallStraight(diceVals);
			break;
		case 14:
			returnBool = this.largeStraight(diceVals);
			break;
		case 15:
			returnBool = this.chance();
			break;
		case 16:
			returnBool = this.yahtzee(diceVals);
			break;
		}
		return returnBool;
	}
	private boolean ones( int [] diceVals )
	{
		boolean boolToReturn = true;
		for( int i = 0; i < diceVals.length && boolToReturn == true; i++ )
		{
			if( diceVals[i] != 1 )
			{
				boolToReturn = false;
			}
		}
		return boolToReturn;
	}
	private boolean twos( int [] diceVals )
	{
		boolean boolToReturn = true;
		for( int i = 0; i < diceVals.length && boolToReturn == true; i++ )
		{
			if( diceVals[i] != 2 )
			{
				boolToReturn = false;
			}
		}
		return boolToReturn;
	}
	private boolean threes( int [] diceVals )
	{
		boolean boolToReturn = true;
		for( int i = 0; i < diceVals.length && boolToReturn == true; i++ )
		{
			if( diceVals[i] != 3 )
			{
				boolToReturn = false;
			}
		}
		return boolToReturn;
	}
	private boolean fours( int [] diceVals )
	{
		boolean boolToReturn = true;
		for( int i = 0; i < diceVals.length && boolToReturn == true; i++ )
		{
			if( diceVals[i] != 4 )
			{
				boolToReturn = false;
			}
		}
		return boolToReturn;
	}
	private boolean fives( int [] diceVals )
	{
		boolean boolToReturn = true;
		for( int i = 0; i < diceVals.length && boolToReturn == true; i++ )
		{
			if( diceVals[i] != 5 )
			{
				boolToReturn = false;
			}
		}
		return boolToReturn;
	}
	private boolean sixths( int [] diceVals )
	{
		boolean boolToReturn = true;
		for( int i = 0; i < diceVals.length && boolToReturn == true; i++ )
		{
			if( diceVals[i] != 6 )
			{
				boolToReturn = false;
			}
		}
		return boolToReturn;
	}
	private boolean pairs( int [] diceVals )
	{
		boolean boolToReturn = false;
		if( diceVals.length == 2 )
		{
			if( diceVals[0] == diceVals[1] )
			{
				boolToReturn = true;
			}
		}
		return boolToReturn;
	}
	private boolean twoPairs( int [] diceVals )
	{
		boolean boolToReturn = false;
		
		if( diceVals.length == 4 )
		{
			this.insertionSort( diceVals );
			
			if( diceVals[0] == diceVals[1] )
			{
				if( diceVals[1] != diceVals[2] )
				{
					if( diceVals[2] == diceVals[3] )
					{
						boolToReturn = true;
					}
				}
			}
		}
		return boolToReturn;
	}
	private boolean threeOfAKind( int [] diceVals )
	{
		boolean boolToReturn = false;
		if( diceVals.length == 3 )
		{
			if( diceVals[0] == diceVals[1] && diceVals[1] == diceVals[2] )
			{
				boolToReturn = true;
			}
		}
		return boolToReturn;	
	}
	private boolean fourOfAKind( int [] diceVals )
	{
		boolean boolToReturn = false;
		if( diceVals.length == 4 )
		{
			if( diceVals[0] == diceVals[1] )
			{
				if( diceVals[1] == diceVals[2] )
				{
					if( diceVals[2] == diceVals[3] )
					{
						boolToReturn = true;
					}
				}
			}
		}
		return boolToReturn;
	}
	private boolean fullHouse( int [] diceVals )
	{
		boolean boolToReturn = false;
		if( diceVals.length == 5 )
		{
			this.insertionSort(diceVals);
			
			if( diceVals[0] == diceVals[1] && diceVals[1] != diceVals[2] )
			{
				if( diceVals[1] != diceVals[2] )
				{
					if( diceVals[2] == diceVals[3] && diceVals[3] == diceVals[4] )
					{
						boolToReturn = true;
					}
				}
			}
			else if( diceVals[0] == diceVals[1] && diceVals[1] == diceVals[2] )
			{
				if( diceVals[2] != diceVals[3] )
				{
					if( diceVals[3] == diceVals[4] )
					{
						boolToReturn = true;
					}
				}
			}
		}
		return boolToReturn;
	}
	private boolean smallStraight( int [] diceVals )
	{	boolean boolToReturn = false;
	
		if( diceVals.length == 5 )
		{
			this.insertionSort(diceVals);
			if( diceVals[0] == 1 )
				if( diceVals[1] == 2 )
					if( diceVals[2] == 3 )
						if( diceVals[3] == 4 )
							if( diceVals[4] == 5 )
							{
								boolToReturn = true;
							}
		}
		return boolToReturn;
		
	}
	private boolean largeStraight( int [] diceVals )
	{
		boolean boolToReturn = false;
		if( diceVals.length == 5 )
		{
			this.insertionSort(diceVals);
			if( diceVals[0] == 2 )
				if( diceVals[1] == 3 )
					if( diceVals[2] == 4 )
						if( diceVals[3] == 5 )
							if( diceVals[4] == 6 )
							{
								boolToReturn = true;
							}
		}
		return boolToReturn;
	}
	private boolean chance()
	{
		boolean boolToReturn = true;
		return boolToReturn;
	}
	private boolean yahtzee( int [] diceVals )
	{
		boolean boolToReturn = true;
		if( diceVals.length == 5 )
		{
			for( int i = 0; i < diceVals.length; i++ )
			{
				if( diceVals[i] != diceVals[0] )
				{
					boolToReturn = false;
				}
			}
		}
		return boolToReturn;
	}
	private void insertionSort( int[] diceVals )
	{
		for( int i = 1,j; i < diceVals.length; i++ )
		{
			int temp = diceVals[i];
			for( j = i; j > 0 && temp < diceVals[j-1]; j-- )
			{
				diceVals[j] = diceVals[j-1];
			}
			diceVals[j] = temp;
		}
	}
}
