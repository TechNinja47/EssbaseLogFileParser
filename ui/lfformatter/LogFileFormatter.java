package ui.lfformatter;
/* This utility will take a an Essbase log file and parse it into columnar format */
import javax.swing.*;
import java.awt.event.*;
import ui.logparser.*;


class LogFileFormatterWindow extends JFrame implements ActionListener
{
   JLabel headerLabel;
   JLabel sourceFilepathLabel;
   JLabel targetFilepathLabel;
   
   JTextField sourceFilepath;
   JTextField targetFilepath;
	
   JButton fileBrowser;
   JButton runParser;
   
   HyperionLogReader hlr;
   
   String sourceFileName;
   String targetFileName;
   
	LogFileFormatterWindow()
	{
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Essbase Application Logfile Formatter");
		
		headerLabel=new JLabel("Essbase Application Logfile Formatter",JLabel.CENTER);
		headerLabel.setBounds(0,0,600,30);
		add(headerLabel);
		
        sourceFilepathLabel=new JLabel("Essbase log file:",JLabel.CENTER);
        sourceFilepathLabel.setBounds(30,50,150,30);
        add(sourceFilepathLabel);
		
        targetFilepathLabel=new JLabel("Formatted log file:",JLabel.CENTER);
        targetFilepathLabel.setBounds(30,100,150,30);
        add(targetFilepathLabel);
		
        sourceFilepath=new JTextField();
        sourceFilepath.setBounds(200,50,300,30);
        add(sourceFilepath);
        
        targetFilepath=new JTextField();
        targetFilepath.setBounds(200,100,300,30);
        add(targetFilepath);
        targetFilepath.setEditable(false);
        
        
        fileBrowser=new JButton("Browse");
        fileBrowser.setBounds(510,50,100,30);
        add(fileBrowser);
        fileBrowser.addActionListener(this);
        
        runParser=new JButton("Format");
        runParser.setBounds(30,150,100,30);
        add(runParser);
        runParser.addActionListener(this);
        
        
      //  hlr=new HyperionLogReader("C:\\Users\\Sibin\\Downloads\\RPPMGMT.LOG");
      //  hlr.parseOutputToFile("C:\\Users\\Sibin\\Downloads\\RPPMGMT_PARSED_COLUMNAR.LOG");
        
		setBounds(100,100,650,400);
		setVisible(true);
		
		
		
	}//end of the constructor

	public void actionPerformed(ActionEvent ae)
	{
		String actionCommand=ae.getActionCommand();
		System.out.println(actionCommand);
		
		if(actionCommand.toUpperCase().equals("BROWSE"))
		{
			chooseEssbaseLogFile();
		}
		else if(actionCommand.toUpperCase().equals("FORMAT"))
		{
			runParser.setEnabled(false);
			parseOutputToFile();
			runParser.setEnabled(true);

		}
	}//end of actionPerformed function
	
	
	private void parseOutputToFile()
	{
		hlr=new HyperionLogReader(sourceFileName);
		hlr.parseOutputToFile(targetFileName);
	}
	
	private void chooseEssbaseLogFile()
	{
		JFileChooser jfc=new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal=jfc.showOpenDialog(this);

		if(returnVal==JFileChooser.APPROVE_OPTION)
		{
			System.out.println(jfc.getSelectedFile().getPath());
			sourceFileName=jfc.getSelectedFile().getPath();
			sourceFilepath.setText(sourceFileName);
			
			targetFileName=sourceFileName.substring(0,sourceFileName.indexOf(".")).concat("_PARSED.LOG");
			
			targetFilepath.setText(targetFileName);
		}		
	}//end of the chooseEssbaseLogFile function
	


}//end of the LogFileFormatterWindow class
	
	
public class LogFileFormatter {

	public static void main(String[] args) 
	{
      System.out.println("Essbase application Log file formatter is now online");
      new LogFileFormatterWindow();
	}//end of the main class

}//end of the LogFileFormatter class
