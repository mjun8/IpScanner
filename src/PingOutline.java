import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;

public class PingOutline extends JFrame {

	String[] titles;
	Object[][] stats;

	public PingOutline() {
		super("Network Scanner");

		// myIP and myHostname
		String myIP;
		String myHostname;
		
		

		InetAddress ia = null;
		try {
			ia = InetAddress.getLocalHost();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		myIP = ia.getHostAddress();
		myHostname = ia.getHostName();

		String fixedIP = myIP.substring(0, myIP.lastIndexOf(".") + 1);

		System.out.println(myIP + "," + myHostname + "," + fixedIP);
		// menu begin
		JMenuBar menuBar = new JMenuBar();

		setJMenuBar(menuBar);

		JMenu scanMenu = new JMenu("Scan");
		JMenu gotoMenu = new JMenu("Goto");
		JMenu cmdMenu = new JMenu("Command");
		JMenu favoriteMenu = new JMenu("Favorite");
		JMenu toolsMenu = new JMenu("Tools");
		JMenu helpMenu = new JMenu("Help");

		menuBar.add(scanMenu);
		menuBar.add(gotoMenu);
		menuBar.add(cmdMenu);
		menuBar.add(favoriteMenu);
		menuBar.add(toolsMenu);
		menuBar.add(helpMenu);

		JMenuItem loadFromFileAction = new JMenuItem("Load From File");
		JMenuItem exportAllAction = new JMenuItem("Export All");
		JMenuItem exportSelectioAction = new JMenuItem("Export Selection");
		JMenuItem quitAction = new JMenuItem("Quit");

		scanMenu.add(loadFromFileAction);
		scanMenu.add(exportAllAction);
		scanMenu.add(exportSelectioAction);
		scanMenu.addSeparator();
		scanMenu.add(quitAction);

		JMenuItem nextAliveHostAction = new JMenuItem("next alive host");
		JMenuItem nextOpenPortAction = new JMenuItem("next open port");
		JMenuItem nextDeadHostAction = new JMenuItem("next dead host");
		JMenuItem previousAliveHostAction = new JMenuItem("previous alive host");
		JMenuItem previousOpenPortAction = new JMenuItem("previous open port");
		JMenuItem previousDeadHostAction = new JMenuItem("previous dead host");
		JMenuItem findAction = new JMenuItem("Find");

		gotoMenu.add(nextAliveHostAction);
		gotoMenu.add(nextOpenPortAction);
		gotoMenu.add(nextDeadHostAction);
		gotoMenu.addSeparator();
		gotoMenu.add(previousAliveHostAction);
		gotoMenu.add(previousOpenPortAction);
		gotoMenu.add(previousDeadHostAction);
		gotoMenu.addSeparator();
		gotoMenu.add(findAction);

		JMenuItem showDetailsAction = new JMenuItem("Show details");
		JMenuItem rescanIpAction = new JMenuItem("Rescan IP");
		JMenuItem deleteIpAction = new JMenuItem("Delete IP");
		JMenuItem copyIpAction = new JMenuItem("Copy IP");
		JMenuItem copyDetailsAction = new JMenuItem("Copy details");
		JMenuItem openAction = new JMenuItem("Open");

		cmdMenu.add(showDetailsAction);
		cmdMenu.addSeparator();
		cmdMenu.add(rescanIpAction);
		cmdMenu.add(deleteIpAction);
		cmdMenu.addSeparator();
		cmdMenu.add(copyIpAction);
		cmdMenu.add(copyDetailsAction);
		cmdMenu.addSeparator();
		cmdMenu.add(openAction);

		JMenuItem addCurrentAction = new JMenuItem("Add current");
		JMenuItem manageFavoriteAction = new JMenuItem("Manage Favorite");

		favoriteMenu.add(addCurrentAction);
		favoriteMenu.add(manageFavoriteAction);

		JMenuItem preferenceAction = new JMenuItem("Preference");
		JMenuItem fetchersAction = new JMenuItem("Fetchers");
		JMenuItem selectionAction = new JMenuItem("Selection");
		JMenuItem scanStaticsAction = new JMenuItem("Scan statics");

		toolsMenu.add(preferenceAction);
		toolsMenu.add(fetchersAction);
		toolsMenu.addSeparator();
		toolsMenu.add(selectionAction);
		toolsMenu.add(scanStaticsAction);

		JMenuItem gettingStartedAction = new JMenuItem("Getting started");
		JMenuItem officialWebsiteAction = new JMenuItem("Official Website");
		JMenuItem faqAction = new JMenuItem("FAQ");
		JMenuItem reportAnIssueAction = new JMenuItem("Report an issue");
		JMenuItem pluginsAction = new JMenuItem("plug-ins");
		JMenuItem commandLineUsageAction = new JMenuItem("command-line usage");
		JMenuItem checkfornewversionAction = new JMenuItem("Check for new version");
		JMenuItem aboutAction = new JMenuItem("About");

		helpMenu.add(gettingStartedAction);
		helpMenu.addSeparator();
		helpMenu.add(officialWebsiteAction);
		helpMenu.add(faqAction);
		helpMenu.add(reportAnIssueAction);
		helpMenu.add(pluginsAction);
		helpMenu.addSeparator();
		helpMenu.add(commandLineUsageAction);
		helpMenu.add(checkfornewversionAction);
		helpMenu.addSeparator();
		helpMenu.add(aboutAction);

		// menu end

		// Toolbar begin
		Font myFont = new Font("Serif", Font.BOLD, 16);
		JToolBar toolbar1 = new JToolBar();
		toolbar1.setLayout(new FlowLayout(FlowLayout.LEFT));
		JToolBar toolbar2 = new JToolBar();
		toolbar2.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel ipRangeLabel = new JLabel("IP Range:");
		JTextField ipStartTextField = new JTextField(10);
		JLabel toLabel = new JLabel("to ");
		JTextField ipEndTextField = new JTextField(10);
		JComboBox sourceSelectionComboBox = new JComboBox();
		sourceSelectionComboBox.addItem("IP Range");
		sourceSelectionComboBox.addItem("Random");
		sourceSelectionComboBox.addItem("File");
		
		ImageIcon icon2 = new ImageIcon("./Ficture/도구.jpg");
	    JButton button2 = new JButton(icon2);

		ipRangeLabel.setFont(myFont);
		toLabel.setFont(myFont);
		ipRangeLabel.setPreferredSize(new Dimension(75, 30));
		toLabel.setPreferredSize(new Dimension(25, 30));
		sourceSelectionComboBox.setPreferredSize(new Dimension(80, 22));

		toolbar1.add(ipRangeLabel);
		toolbar1.add(ipStartTextField);
		toolbar1.add(toLabel);
		toolbar1.add(ipEndTextField);
		toolbar1.add(sourceSelectionComboBox);
		toolbar1.add(button2);

		JLabel hostNameLabel = new JLabel("Hostname:");
		JTextField hostNameTextField = new JTextField(10);
		JButton ipButton = new JButton("↑IP");
		JComboBox netMaskComboBox = new JComboBox();
		netMaskComboBox.addItem("/24");
		netMaskComboBox.addItem("/26");
		JButton startButton = new JButton("▶Start");
		
		ImageIcon icon = new ImageIcon("./Ficture/두번째 사진.jpg");
	    JButton button = new JButton(icon);

		hostNameLabel.setFont(myFont);
		hostNameLabel.setPreferredSize(new Dimension(75, 30));
		ipButton.setPreferredSize(new Dimension(40, 22));
		netMaskComboBox.setPreferredSize(new Dimension(95, 22));
		startButton.setPreferredSize(new Dimension(85, 22));
		toolbar2.add(hostNameLabel);
		toolbar2.add(hostNameTextField);
		toolbar2.add(ipButton);
		toolbar2.add(netMaskComboBox);
		toolbar2.add(startButton);
		toolbar2.add(button);

		JPanel pane = new JPanel(new BorderLayout());
		pane.add(toolbar1, BorderLayout.NORTH);
		pane.add(toolbar2, BorderLayout.SOUTH);

		add(pane, BorderLayout.NORTH);
		// Toolbar end

		// Table begin

		titles = new String[] { "IP", "Ping", "TTL", "Hostname", "Ports[4+]" };
		stats = initializeTable();

		JTable jTable = new JTable(stats, titles);

		JScrollPane jScrollPane = new JScrollPane(jTable);
		add(jScrollPane, BorderLayout.CENTER);
		
		// Table end

		//status bar begin
				JProgressBar pr = new JProgressBar(0,0,100);
				JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
				add(statusPanel, BorderLayout.SOUTH);
				JLabel readyLabel = new JLabel("Ready");
				readyLabel.setPreferredSize(new Dimension(200,16));
				readyLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
				JLabel displayLabel = new JLabel("Display:All");
				displayLabel.setPreferredSize(new Dimension(140,16));
				displayLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
				JLabel threadLabel = new JLabel("Thread:0");
				threadLabel.setPreferredSize(new Dimension(140,16));
				threadLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
				statusPanel.add(readyLabel);
				statusPanel.add(displayLabel);
				statusPanel.add(threadLabel);
				statusPanel.add(pr);
				
				//status bar end
		quitAction.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Pinging[] pg = new Pinging[254];
				
				for(int i=0; i<254; i++) {
					pg[i] = new Pinging(fixedIP+(i+1));
					pg[i].start();
				}
				for(int i=0; i<254; i++) {
					Object[] msg = pg[i].getMsg();
					stats[i][0] = msg[0];
					if (msg[1] != null)
						stats[i][1] = msg[1];
					else
						stats[i][1] = "[n/a]";
					if (msg[2] != null)
						stats[i][2] = msg[2];
					else
						stats[i][2] = "[n/s]";
					if (msg[3] != null)
						stats[i][3] = msg[3];
					else
						stats[i][3] = "[n/s]";
					stats[i][4] = "[n/s]";
				}
				
				for (int i = 0; i < 254; i++) {
					// port scan
					if(stats[i][1] != "[n/a]" || stats[i][2] != "[n/s]" || stats[i][3] != "[n/s]") {
						// 여기쓰기
					}
				}
				jTable.repaint();

			}
		});

		ipStartTextField.setText(fixedIP + 1);
		ipEndTextField.setText(fixedIP + 254);
		hostNameTextField.setText(myHostname);
		setSize(700, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private Object[][] initializeTable() {
		Object[][] results = new Object[254][titles.length];
		return results;
	}

	private Object[][] getPingStats(String string) {
		Object[][] results = new Object[254][titles.length];
		for (int i = 0; i < 254; i++) {
			results[i][0] = string + (i + 1);
			results[i][1] = "[n/a]";
			results[i][2] = "[n/s]";
			results[i][3] = "[n/s]";
			results[i][4] = "[n/s]";
		}
		return results;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PingOutline po = new PingOutline();
	}

}
