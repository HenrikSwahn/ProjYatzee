import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class YatzyGUI extends JFrame
{
	private Container contentPane;
	private JTextField[] nameFields;
	private JCheckBox[] holdBoxes;
	private JLabel[] imgDices;
	private JList<String>[] protocolLists;
	private JCheckBox skipBox;
	private JLabel skipLabel;
	private JButton saveNameButton;
	private JButton throwButton;
	private JButton unlockButton;
	private JButton startGameButton;
	private Yatzy yatzy;
	private String[] imagePaths = { "nullDice.png",
									"Dice1.png",
									"Dice2.png",
									"Dice3.png",
									"Dice4.png",
									"Dice5.png",
									"Dice6.png" };
	private ImageIcon[] diceFaces; 
	
	// H:\\Skola\\Java\\Project IV Yatzy\\src\\images\\nullDice.png
	public YatzyGUI()
	{
		initiateInstanceVariables();
		addComponentsOnLeftSide();
		setUpMainWindow();
		addMenu();
	}

	private void initiateInstanceVariables()
	{
		this.contentPane = this.getContentPane();
		this.contentPane.setLayout( null );

		initiateNameFields();
		
		this.saveNameButton = new JButton( "Save" );
		this.saveNameButton.setBounds(20, 220, 150, 40);
		this.saveNameButton.addActionListener( new buttonListener() );
		
		initiateHoldBoxes();
		initiateDices();
		
		this.throwButton = new JButton( "Throw" );
		this.throwButton.setBounds( 175, 330, 75, 15 );
		this.throwButton.addActionListener( new buttonListener() );
		this.throwButton.setVisible( false );
		
		this.unlockButton = new JButton( "Unlock" );
		this.unlockButton.setBounds( 95, 330, 75, 15);
		this.unlockButton.addActionListener( new buttonListener() );
		this.unlockButton.setVisible( false );
		
		this.startGameButton = new JButton( "Start" );
		this.startGameButton.setBounds( 15,330,75,15 );
		this.startGameButton.addActionListener( new buttonListener() );
		this.startGameButton.setVisible( false );
		
		this.skipBox = new JCheckBox();
		this.skipBox.setBounds(230, 5, 20, 20);
		this.skipBox.setActionCommand( "skipBox" );
		this.skipBox.addActionListener( new lockListener() );
		this.skipBox.setVisible( false );
		
		this.skipLabel = new JLabel("Skip");
		this.skipLabel.setBounds(203, 8, 45, 15);
		this.skipLabel.setVisible( false );
		
		this.yatzy = new Yatzy();
	}
	private void initiateNameFields()
	{
		//Initiates the array of JTextFields that will be used to enter players names 
		this.nameFields = new JTextField[4];
		int yPos = 20;
		for( int i = 0; i < nameFields.length; i++ )
		{
			this.nameFields[i] = new JTextField();
			this.nameFields[i].setBorder(BorderFactory.createTitledBorder( "Player " + (i+1) + ":" ) );
			this.nameFields[i].setBounds( 20, yPos, 150, 40 );
			yPos += 45;
		}
	}
	private void initiateHoldBoxes()
	{
		//Initiates the array of holdeBoxes that will be used to lock the dices
		this.holdBoxes = new JCheckBox[5];
		int xPos = 20;
		int yPos = 10;
		for( int i = 0; i < holdBoxes.length; i++ )
		{
			this.holdBoxes[i] = new JCheckBox();
			this.holdBoxes[i].setBounds(xPos, yPos, 20, 20);
			this.holdBoxes[i].setVisible( false );
			yPos += 115;
			if( i == 2 )
			{
				yPos = this.holdBoxes[i-2].getY();
				yPos += 70;
				xPos += 120;
			}
			this.holdBoxes[i].setActionCommand( "dice" + i + "holdbox");
			this.holdBoxes[i].addActionListener( new lockListener() );
		}
	}
	private void initiateDices()
	{
		//Initiate the array of labels that currently representing the dicesValue produced in yatzy
		this.imgDices = new JLabel[5];
		int xPos = 45;
		int yPos = 10;
		this.diceFaces = new ImageIcon[7];
		for( int i = 0; i < 7; i++ )
		{
			diceFaces[i] = new ImageIcon(this.imagePaths[i]);
		}
		for( int i = 0; i < imgDices.length; i++ )
		{
			this.imgDices[i] = new JLabel( this.diceFaces[0] );
			this.imgDices[i].setBounds( xPos, yPos, 75,75 );
			this.imgDices[i].setVisible( false );
			yPos += 115;
			if( i == 2 )
			{
				yPos = this.imgDices[i-2].getY();
				yPos += 70;
				xPos += 120;
			}
		}
	}
	private void addComponentsOnLeftSide()
	{
		/*Adds components on the left side of the gamefield,this side "
		  will hold the yatzy dices and holdboxes but initiatly it holds
		  the name fields before the windows expands 
		 */
		JPanel leftPanel = new JPanel();
		leftPanel.setBounds( 0,0, 250,380 );
		leftPanel.setBackground( Color.white );
		leftPanel.setLayout( null );
		
		//These will be used initiatly when the game wants to players names
		for( int i = 0; i < this.nameFields.length; i++ )
		{
			leftPanel.add( this.nameFields[i] );
		}
		leftPanel.add( this.saveNameButton );
		
		//And these will be use during the game, but are initiatly invisible
		for( int i = 0; i < this.holdBoxes.length; i++ )
		{
			leftPanel.add( this.holdBoxes[i] );
		}
		for( int i = 0; i < this.imgDices.length; i++ )
		{
			leftPanel.add( imgDices[i] );
		}
		leftPanel.add( this.throwButton );
		leftPanel.add( this.unlockButton );
		leftPanel.add( this.startGameButton );
		leftPanel.add( this.skipBox );
		leftPanel.add( this.skipLabel );
		this.contentPane.add( leftPanel );
	}
	private void setUpMainWindow() 
	{
		this.setSize( 200, 330 );
		this.setResizable( false );
		this.setLocationRelativeTo( null );
		this.setTitle( "Yatzy" );
		this.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);
	}
	private void addMenu() 
	{
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu( "Menu" );
		bar.add( menu );
		
		JMenuItem item = new JMenuItem( "HighScores" );
		item.addActionListener( new menuItemListener() );
		menu.add( item );
		
		this.setJMenuBar( bar );
	}
	@SuppressWarnings("unchecked")
	private void addComponentsOnRightSide()
	{
		//The right side will hold the player lists during the game
		//but all this is done after the number of player s has been chosen 
		//so the game knows how many lists that will be needed
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout( new GridLayout( 1, this.yatzy.deliverNrOfPlayers() ) );
		
		this.protocolLists = new JList[this.yatzy.deliverNrOfPlayers()];
		for( int i = 0; i < this.protocolLists.length; i++ )
		{
			this.protocolLists[i] = new JList<String>();
			this.protocolLists[i].setBorder( BorderFactory.createTitledBorder( this.yatzy.deliverPlayerNameOnPos( i ) ) );
			this.protocolLists[i].setListData( this.yatzy.deliverProtocolForAPlayer( i ) );
			this.protocolLists[i].addListSelectionListener( new listListener() );
			this.protocolLists[i].setEnabled( false );
			rightPanel.add( this.protocolLists[i] );
		}
		//This will expand the rightpanel depending on how many players 2,3 or 4.
		if( this.getWidth() == 506 )
		{
			rightPanel.setBounds(250, 0, 250, 380 );
		}
		else if( this.getWidth() == 580 )
		{
			rightPanel.setBounds(250, 0, 325, 380 );
		}
		else if( this.getWidth() == 629 )
		{
			rightPanel.setBounds(250, 0, 375, 380 );
		}
		this.contentPane.add( rightPanel );	
		//this will be executed after saveButton is clicked
	}
	private void editWindowsAndFields()
	{
		//Sets the namefields and saveButton visibility to false
		//and holdBoxes,dices,thorw,unlock to visible
		for( int i = 0; i < this.nameFields.length; i++ )
		{
			this.nameFields[i].setText( "" );
			this.nameFields[i].setVisible( false );
		}
		this.saveNameButton.setVisible( false );
		for( int i = 0; i < this.holdBoxes.length; i++ )
		{
			this.holdBoxes[i].setVisible( true );
			this.holdBoxes[i].setEnabled( false );
		}
		for( int i = 0; i < this.imgDices.length; i++ )
		{
			this.imgDices[i].setVisible( true );
			this.imgDices[i].setEnabled( false);
		}
		this.throwButton.setVisible( true );
		this.throwButton.setEnabled( false );
		this.unlockButton.setVisible ( true );
		this.unlockButton.setEnabled( false );
		this.skipBox.setVisible( true );
		this.skipBox.setEnabled( false );
		this.skipLabel.setVisible( true );
		this.startGameButton.setVisible( true );
		//this will expand the mainwindow depending on how many player there will be
		if( this.yatzy.deliverNrOfPlayers() == 2 )
		{
			this.setSize( 506,400 );
		}
		else if( this.yatzy.deliverNrOfPlayers() == 3 )
		{
			this.setSize( 580, 400 );
		}
		else if( this.yatzy.deliverNrOfPlayers() ==  4 )
		{
			this.setSize( 629, 400 );
		}
		//All of this will be executed after saveButton is clicked
	}
	private void takeNamesAndCreatePlayers() 
	{
		for( int i = 0; i < this.nameFields.length; i++ )
		{
			if( !this.nameFields[i].getText().equals( "" ) )
			{
				//Create the players if the fields are not empty
				this.yatzy.addPlayer( this.nameFields[i].getText() );
			}
		}
		editWindowsAndFields();
		addComponentsOnRightSide();
	}
	private void checkNameFields()
	{
		boolean passed = false;
		if( ( !this.nameFields[0].getText().equals( "" ) )  && ( !this.nameFields[1].getText().equals( "" ) ) )
		{
			//If field for p1 and p2 are not empty
			if( this.nameFields[2].getText().equals( "" ) && this.nameFields[3].getText().equals( "" ) ) 
			{
				//But the fields for p3 and p4 are, this call will create two players
				takeNamesAndCreatePlayers();
				passed = true;
			}
		}
		if( ( !this.nameFields[0].getText().equals( "" ) )  && ( !this.nameFields[1].getText().equals( "" ) ) )
		{
			//If field for p1 and p2 are not empty
			if( ( !this.nameFields[2].getText().equals( "" ) ) && this.nameFields[3].getText().equals( "" ) ) 
			{
				//And the field for p3 not empty and field for p4 are, the call will create three players
				takeNamesAndCreatePlayers();
				passed = true;
			}
		}
		if( ( !this.nameFields[0].getText().equals( "" ) )  && ( !this.nameFields[1].getText().equals( "" ) ) )
		{
			//If field for p1 and p2 are not empty
			if( ( !this.nameFields[2].getText().equals( "" ) ) && ( !this.nameFields[3].getText().equals( "" ) ) ) 
			{
				//And non of p3 or p4 fields are empty, this will generate 4 players
				takeNamesAndCreatePlayers();
				passed = true;
			}
		}
		if( !passed )
		{
			//This will be called if you try to just enter one name or enter names in for exampel p1 field
			//and p3 field and leave p2 field empty, this is not accepted.
			JOptionPane.showMessageDialog(this, "Error, make sure you enter names in order!");
		}
	}
	private void tellYatzyToThrowDices() 
	{
		this.yatzy.throwDices();
		updateDices();
		if( this.yatzy.deliverNrOfThrowsLeftForCurrentPlayer() == 0 )
		{
			this.throwButton.setEnabled( false );
		}
		this.protocolLists[this.yatzy.deliverCurrentPlayer()].clearSelection();
	}
	private void updateDices() 
	{
		int[] diceVals = this.yatzy.deliverDiceVals();
		int value = 0;
		for( int i = 0; i < diceVals.length; i++ )
		{
			value = diceVals[i];
			this.imgDices[i].setIcon( this.diceFaces[value]);
		}	
	}
	private void tellYatzyToLockDice( int diceNr ) 
	{
		this.yatzy.lockDices( diceNr);	
	}
	private void tellYatzyToUnlockAllDices()
	{
		this.yatzy.unLockAllDices();
		for( int i = 0; i < this.holdBoxes.length; i++ )
		{
			this.holdBoxes[i].setSelected( false );
		}
	}
	private void tellYatzyToGoToNextPlayer() 
	{
		//Sets current players list enable = false;
		int currentPlayer = this.yatzy.deliverCurrentPlayer();
		this.protocolLists[currentPlayer].setEnabled( false );
		
		//Get next player
		this.yatzy.nextPlayer();
		currentPlayer = this.yatzy.deliverCurrentPlayer();
		this.protocolLists[currentPlayer].setEnabled( true );	
		
		if( this.yatzy.deliverNrOfThrowsLeftForCurrentPlayer() == 3 )
		{
			this.throwButton.setEnabled( true );
		}
		this.yatzy.resetDices();
		this.tellYatzyToUnlockAllDices();
		for( int i = 0; i < imgDices.length; i++ )//Set the default dice face representing 0
		{
			this.imgDices[i].setIcon( this.diceFaces[0] );
		}
		if( this.skipBox.isSelected() )//If last player skipped
		{
			this.skipBox.doClick();
		}
		if( this.yatzy.gameIsDone() )
		{
			this.protocolLists[currentPlayer].setEnabled( false );
			this.throwButton.setEnabled( false );
			this.skipBox.setEnabled( false );
			this.unlockButton.setEnabled( false );
			presentResults();
		}
	}
	private void takeDiceValsAndPlaceInProtocol() 
	{	
		if( this.yatzy.deliverNrOfThrowsLeftForCurrentPlayer() < 3 )
		{
			if( this.protocolLists[this.yatzy.deliverCurrentPlayer()].getSelectedIndex() != 6 )
			{
				if( this.protocolLists[this.yatzy.deliverCurrentPlayer()].getSelectedIndex() != 7 )
				{
					if( this.protocolLists[this.yatzy.deliverCurrentPlayer()].getSelectedIndex() != 17 )
					{
						if( this.protocolLists[this.yatzy.deliverCurrentPlayer()].getSelectedIndex() != -1 )
						{
							if( this.skipBox.isSelected() )
							{
								//If this is executed -1 will be placed in the selectedIndex, representing that the player is skipping this index
								int currentPlayer = this.yatzy.deliverCurrentPlayer();
								int protocolSelection = this.protocolLists[currentPlayer].getSelectedIndex();
								if( this.yatzy.skip(protocolSelection) )
								{
									updateScoreboard();
									tellYatzyToGoToNextPlayer();
								}
							}
							else
							{
								//Els the point will be placed in the index
								int currentPlayer = this.yatzy.deliverCurrentPlayer();
								int protocolSelection = this.protocolLists[currentPlayer].getSelectedIndex();
								if( this.yatzy.givePointsToPlayer(protocolSelection) )
								{
									updateScoreboard();
									tellYatzyToGoToNextPlayer();
								}
							}
						}
					}
				}
			}
		}
	}
	private void updateScoreboard()
	{
		//Updates currentsplayers playerlist/scoreboard
		int currentPlayer = this.yatzy.deliverCurrentPlayer();
		this.protocolLists[currentPlayer].setListData( this.yatzy.deliverProtocolForAPlayer( currentPlayer ) );
	}
	private void disableAllDices() 
	{
		if( this.skipBox.isSelected() )
		{
			for( int i = 0; i < this.holdBoxes.length; i++ )
			{
				this.holdBoxes[i].setEnabled( false );
				this.imgDices[i].setEnabled( false );
			}
		}
		else
		{
			for( int i = 0; i < this.holdBoxes.length; i++ )
			{
				this.holdBoxes[i].setEnabled( true );
				this.imgDices[i].setEnabled( true );
			}
		}
	}
	private void presentResults()
	{
		//Opens a new window where results will be presented
		String[] presentArr = this.yatzy.getResults();
		String[] protocols = new String[this.yatzy.deliverNrOfPlayers()];
		for( int i = 0; i < this.yatzy.deliverNrOfPlayers(); i++ )
		{
			String[] temp = this.yatzy.deliverProtocolForAPlayer( i );
			protocols[i] = this.yatzy.deliverPlayerNameOnPos( i ) + System.lineSeparator();
			for( int j = 0; j < temp.length; j++ )
			{
				protocols[i] += temp[j] + System.lineSeparator();
			}
		}
		PresentResultWin resultWin = new PresentResultWin( presentArr, protocols );
		resultWin.setLocationRelativeTo( this );
		resultWin.setVisible( true );
	}
	private void startGame()
	{
		this.yatzy.startGame();
		for( int i = 0; i < this.holdBoxes.length; i++ )
		{
			this.holdBoxes[i].setEnabled( true );
			this.imgDices[i].setEnabled( true );
		}
		this.throwButton.setEnabled( true );
		this.unlockButton.setEnabled( true );
		this.skipBox.setEnabled( true );
		//Set first players list available
		this.protocolLists[this.yatzy.deliverCurrentPlayer()].setEnabled( true );
		this.startGameButton.setEnabled( false );
	}
	private void openHighScoreWindow()
	{
		HighScoresWindow hiScWin = new HighScoresWindow( this.yatzy.delivereHighscores() );
		hiScWin.setLocationRelativeTo( this );
		hiScWin.setVisible( true );
	}
	private class buttonListener implements ActionListener
	{
		@Override
		public void actionPerformed( ActionEvent e )
		{
			String cases = e.getActionCommand();
			switch( cases )
			{
			case "Save":
				checkNameFields();
				break;
			case "Throw":
				tellYatzyToThrowDices();
				break;
			case "Unlock":
				tellYatzyToUnlockAllDices();
				break;
			case "Start":
				startGame();
				break;
			}
		}
	}
	private class lockListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if( e.getActionCommand().equals( "dice0holdbox" ) )
			{
				tellYatzyToLockDice( 0 );
			}
			else if( e.getActionCommand().equals( "dice1holdbox" ) )
			{
				tellYatzyToLockDice( 1 );
			}
			else if( e.getActionCommand().equals( "dice2holdbox" ) )
			{
				tellYatzyToLockDice( 2 );
			}
			else if( e.getActionCommand().equals( "dice3holdbox" ) )
			{
				tellYatzyToLockDice( 3 );
			}
			else if( e.getActionCommand().equals( "dice4holdbox" ) )
			{
				tellYatzyToLockDice( 4 );
			}
			else if( e.getActionCommand().equals( "skipBox" ) )
			{
				disableAllDices();
			}
		}
	}
	private class listListener implements ListSelectionListener
	{
		@Override
		public void valueChanged(ListSelectionEvent e) 
		{
			if( !e.getValueIsAdjusting() )
			{
				takeDiceValsAndPlaceInProtocol();
			}
		}	
	}
	private class menuItemListener implements ActionListener
	{
		@Override
		public void actionPerformed( ActionEvent e ) 
		{
			String event = e.getActionCommand();
			
			switch( event )
			{
			case "HighScores":
				openHighScoreWindow();
				break;
			}
		}	
	}
}
