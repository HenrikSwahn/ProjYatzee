import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.*;


public class PresentResultWin extends JFrame
{
	private JList<String> presentList;
	private JButton printToFileButton;
	private JButton quitButton;
	private Container contentpane;
	private String imgLocation = "Dices.png";
	private ImageIcon diceImg;
	private JLabel imgLabel;
	private String[] protocols;
	
	public PresentResultWin( String[] presentArr, String[] protocols )
	{
		initiateInstanceVaribales( protocols );
		setUpWindow();
		addComponents();
		
		this.presentList.setListData( presentArr );
	}
	private void addComponents()
	{
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout( new GridLayout( 1,2 ) );
		mainPanel.add( this.presentList);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout( null);
		buttonPanel.setBackground( Color.white );
		buttonPanel.add( this.printToFileButton );
		buttonPanel.add( this.quitButton );
		buttonPanel.add( this.imgLabel );
		mainPanel.add( buttonPanel );
		this.contentpane.add( mainPanel );	
	}
	private void setUpWindow()
	{
		this.setSize( 300,250 );
		this.setResizable(false);
		this.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);
	}
	@SuppressWarnings("unchecked")
	private void initiateInstanceVaribales( String[] protocols ) 
	{
		this.presentList = new JList<String>();
		this.presentList.setBorder( BorderFactory.createTitledBorder( "Results:" ) );
		
		this.protocols = protocols;
		
		this.printToFileButton = new JButton( "Print Protocols" );
		this.printToFileButton.setBounds(10, 20, 125, 20 );
		this.printToFileButton.addActionListener( new buttonListener() );
		
		this.quitButton = new JButton( "Quit" );
		this.quitButton.setBounds( 35, 45, 75, 20 );
		this.quitButton.addActionListener( new buttonListener() );
		
		this.contentpane = this.getContentPane();
		this.contentpane.setLayout( new GridLayout(1,1) );
		
		this.diceImg = new ImageIcon( this.imgLocation );
		this.imgLabel = new JLabel( diceImg );
		this.imgLabel.setBounds( 25,100,100,100 );
		
	}
	private void printProtocols()
	{
		try 
		{
			PrintWriter p1;
			String[] protocolNames = { "P1 protocol.txt",
									   "P2 protocol.txt",
									   "P3 protocol.txt", 
									   "P4 protocol.txt" };
			for( int i = 0; i < this.protocols.length; i++ )
			{
				p1 = new PrintWriter( protocolNames[i] );
				p1.print( this.protocols[i] );
				System.out.println( this.protocols[i] );
				p1.close();
			}
		} 
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void quit()
	{
		System.exit(1);	
	}
	private class buttonListener implements ActionListener
	{
		@Override
		public void actionPerformed( ActionEvent e ) 
		{
			String event = e.getActionCommand();
			switch( event )
			{
			case "Quit":
				quit();
				break;
			case "Print Protocols":
				printProtocols();
			}
		}
	}
}
