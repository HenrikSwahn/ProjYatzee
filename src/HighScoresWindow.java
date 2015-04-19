import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.*;


public class HighScoresWindow extends JFrame
{
	private Container contentPane;
	
	public HighScoresWindow( String[] highScores )
	{
		initiateInstanceVaribles();
		addComponents( highScores );
		setUpWindow();
	}
	private void initiateInstanceVaribles()
	{
		this.contentPane = this.getContentPane();
		this.contentPane.setLayout( new GridLayout( 1,1 ) );
	}
	private void addComponents( String[] highScores )
	{
		JList<String> highScoreList = new JList<String>();
		highScoreList.setBorder( BorderFactory.createTitledBorder( "HighScores" ) );
		highScoreList.setListData( highScores );
		
		this.contentPane.add( highScoreList );
	}
	private void setUpWindow()
	{
		this.setSize( 200,230 );
		this.setTitle( "Scores");
		this.setResizable( false );
		this.setDefaultCloseOperation(  WindowConstants.DISPOSE_ON_CLOSE  );
	}
}
