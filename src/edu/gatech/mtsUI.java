package edu.gatech;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class mtsUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtStopId;
	private JTextField txtStopName;
	private JTextField txtRiderNumbers;
	private JTextField txtBusRouteID;
	private JTextField txtBusRouteName;
	private JTextField txtBusStopNumber;
	private JButton btnAddBusRoute;
	private JTextField txtTrainID;
	private JTextField txtTrainRouteId;
	private JTextField txtTrainLocation;
	private JTextField txtPassengersNo_Train;
	private JTextField txtTrainCapacity;
	private JTextField txtTrainSpeed;
	private JButton btnAddTrain;
	private JTextField txtBusID;
	private JTextField txtBusRouteID_1;
	private JTextField txtBusLocation;
	private JTextField txtPassengersNo_Bus;
	private JTextField txtBusCapacity;
	private JTextField txtBusSpeed;
	private JButton btnAddBus;
	private JTextField txtNextStopID_Train;
	private JButton btnExtendTrainRoute;
	private JTextField txtRouteID_Bus;
	private JTextField txtNextStopID_Bus;
	private JButton btnExtendBusRoute;
	private JTextField txtEventRank;
	private JTextField txtEventID;
	private JButton btnAddEvent;
	private JTextField txtStepNumber;
	private JTextField txtFrequencyOfCompleted;
	private JTextField txtSecondsOfPause;
	private JButton btnStepMulti;
	private JTextField txtFrequencyOfModel;
	private JTabbedPane tabbedPane;
	private JButton btnSystemReport;
	private JButton btnModelDisplay;
	private JButton btnSystemStop;
	private JComboBox<Object> comboBoxEventType;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private JTextField txtTrainStopNumbers;
	private JTextField txtTrainRouteName;
	private JTextField txtRouteID_Train;
	private JButton button;
	
	SimDriver commandInterpreter;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mtsUI frame = new mtsUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mtsUI() {
		this.getContentPane().setBackground(new Color(211, 211, 211));
		this.setBounds(0, 0, 1600, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		
		JComponent panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(173, 216, 230), 8));
		panel_2.setBounds(62, 504, 699, 253);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnClear = new JButton("clear");
		btnClear.setFont(new Font("Dialog", Font.PLAIN, 11));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea_1.selectAll();
                textArea_1.replaceSelection("");
			}
		});
		btnClear.setBounds(601, 11, 70, 20);
		panel_2.add(btnClear);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(12, 12, 675, 265);
		panel_2.add(tabbedPane_1);
		
		textArea_1 = new ConsoleText();
		
		textArea_1.setBounds(12, 12, 675, 229);
		JScrollPane commandList = new JScrollPane(textArea_1,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		tabbedPane_1.addTab("Commands", null, commandList, null);
		//panel_2.setLayout(null);
		
								
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(173, 216, 230), 8));
		panel.setBounds(62, 106, 988, 386);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		txtStopId = new JTextField();
		txtStopId.setBounds(25, 31, 114, 25);
		txtStopId.setToolTipText("Stop ID");
		panel.add(txtStopId);
		txtStopId.setColumns(10);
		
		txtTrainRouteName = new JTextField();
		txtTrainRouteName.setToolTipText("Train Route Name");
		txtTrainRouteName.setColumns(10);
		txtTrainRouteName.setBounds(151, 68, 114, 25);
		panel.add(txtTrainRouteName);
		
		txtStopName = new JTextField();
		txtStopName.setToolTipText("Stop Name");
		txtStopName.setColumns(10);
		txtStopName.setBounds(151, 31, 114, 25);
		panel.add(txtStopName);
		
		JButton btnAddStop = new JButton("Add stop");
		btnAddStop.setToolTipText("Need Stop ID, Stop name, and Rider Numbers");
		btnAddStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 String text1 = txtStopId.getText();
				 String text2 = txtStopName.getText();
				 String text3 = txtRiderNumbers.getText();
				 textArea_1.append("add_stop"+","+text1+","+text2+","+text3+"\n");
				 //textField.selectAll();
				 txtStopId.setText("");
				 txtStopName.setText("");
				 txtRiderNumbers.setText("");
			}
		});
		btnAddStop.setBounds(818, 31, 147, 25);
		panel.add(btnAddStop);
		
		txtRiderNumbers = new JTextField();
		txtRiderNumbers.setToolTipText("Rider Numbers");
		txtRiderNumbers.setColumns(10);
		txtRiderNumbers.setBounds(277, 31, 114, 25);
		panel.add(txtRiderNumbers);
		
	    txtTrainStopNumbers = new JTextField();
		txtTrainStopNumbers.setToolTipText("Stop Numbers");
		txtTrainStopNumbers.setColumns(10);
		txtTrainStopNumbers.setBounds(277, 68, 114, 25);
		panel.add(txtTrainStopNumbers);
		
		txtTrainRouteId = new JTextField();
		txtTrainRouteId.setToolTipText("Train Route ID");
		txtTrainRouteId.setColumns(10);
		txtTrainRouteId.setBounds(25, 68, 114, 25);
		panel.add(txtTrainRouteId);
				
		JButton btnAddTrainRoute = new JButton("Add Train Route");
		btnAddTrainRoute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 String text1 = txtTrainRouteId.getText();
				 String text2 = txtTrainRouteName.getText();
				 String text3 = txtTrainStopNumbers.getText();
				 textArea_1.append("add_train_route"+","+text1+","+text2+","+text3+"\n");
				 txtTrainRouteId.setText("");
				 txtTrainRouteName.setText("");
				 txtTrainStopNumbers.setText("");
			}
		});
		btnAddTrainRoute.setToolTipText("Need Train Route ID, Train Route Name, and  Stop Numbers");
		btnAddTrainRoute.setBounds(818, 68, 147, 25);
		panel.add(btnAddTrainRoute);
		
		txtBusRouteID = new JTextField();
		txtBusRouteID.setToolTipText("Bus Route ID");
		txtBusRouteID.setColumns(10);
		txtBusRouteID.setBounds(25, 105, 114, 25);
		panel.add(txtBusRouteID);
		
		txtBusRouteName = new JTextField();
		txtBusRouteName.setToolTipText("Bus Route Name");
		txtBusRouteName.setColumns(10);
		txtBusRouteName.setBounds(151, 105, 114, 25);
		panel.add(txtBusRouteName);
		
		txtBusStopNumber = new JTextField();
		txtBusStopNumber.setToolTipText("Stop Numbers");
		txtBusStopNumber.setColumns(10);
		txtBusStopNumber.setBounds(277, 105, 114, 25);
		panel.add(txtBusStopNumber);
		
		btnAddBusRoute = new JButton("Add Bus Route");
		btnAddBusRoute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String text1 = txtBusRouteID.getText();
				 String text2 = txtBusRouteName.getText();
				 String text3 = txtBusStopNumber.getText();
				 textArea_1.append("add_bus_route"+","+text1+","+text2+","+text3+"\n");
				 txtBusRouteID.setText("");
				 txtBusRouteName.setText("");
				 txtBusStopNumber.setText("");		
			}
		});
		btnAddBusRoute.setToolTipText("Need Bus Route ID, Bus Route Name, and Stop Numbers");
		btnAddBusRoute.setBounds(818, 105, 147, 25);
		panel.add(btnAddBusRoute);
		
		txtTrainID = new JTextField();
		txtTrainID.setToolTipText("Train ID");
		txtTrainID.setColumns(10);
		txtTrainID.setBounds(25, 142, 114, 25);
		panel.add(txtTrainID);
		
		JTextField txtTrainRouteID_1 = new JTextField();
		txtTrainRouteID_1.setToolTipText("Train Route ID");
		txtTrainRouteID_1.setColumns(10);
		txtTrainRouteID_1.setBounds(151, 142, 114, 25);
		panel.add(txtTrainRouteID_1);
	
		txtTrainLocation = new JTextField();
		txtTrainLocation.setToolTipText("Location");
		txtTrainLocation.setColumns(10);
		txtTrainLocation.setBounds(277, 142, 114, 25);
		panel.add(txtTrainLocation);
		
		txtPassengersNo_Train = new JTextField();
		txtPassengersNo_Train.setToolTipText("Passenger Numbers");
		txtPassengersNo_Train.setColumns(10);
		txtPassengersNo_Train.setBounds(403, 142, 114, 25);
		panel.add(txtPassengersNo_Train);
		
		txtTrainCapacity = new JTextField();
		txtTrainCapacity.setToolTipText("Train Capacity");
		txtTrainCapacity.setColumns(10);
		txtTrainCapacity.setBounds(529, 142, 114, 25);
		panel.add(txtTrainCapacity);
		
		txtTrainSpeed = new JTextField();
		txtTrainSpeed.setToolTipText("Train Speed");
		txtTrainSpeed.setColumns(10);
		txtTrainSpeed.setBounds(655, 142, 114, 25);
		panel.add(txtTrainSpeed);
		
		btnAddTrain = new JButton("Add Train");
		btnAddTrain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 String text1 = txtTrainID.getText();
				 String text2 = txtTrainRouteID_1.getText();
				 String text3 = txtTrainLocation.getText();
				 String text4 = txtPassengersNo_Train.getText();
				 String text5 = txtTrainCapacity.getText();
				 String text6 = txtTrainSpeed.getText();
				 textArea_1.append("add_train"+","+text1+","+text2+","+text3+","+text4+","+text5+","+text6+"\n");
				 txtTrainID.setText("");
				 txtTrainLocation.setText("");
				 txtPassengersNo_Train.setText("");	
				 txtTrainCapacity.setText("");
				 txtTrainRouteID_1.setText("");
				 txtTrainSpeed.setText("");
			}
			
		});
		btnAddTrain.setToolTipText("Need Train ID, Train Route ID, Location, Passenger Numbers, Train Capacity, and Train Speed");
		btnAddTrain.setBounds(818, 142, 147, 25);
		panel.add(btnAddTrain);
		
		txtBusID = new JTextField();
		txtBusID.setToolTipText("Bus ID");
		txtBusID.setColumns(10);
		txtBusID.setBounds(25, 179, 114, 25);
		panel.add(txtBusID);
		
		txtBusRouteID_1 = new JTextField();
		txtBusRouteID_1.setToolTipText("Bus Route ID");
		txtBusRouteID_1.setColumns(10);
		txtBusRouteID_1.setBounds(151, 179, 114, 25);
		panel.add(txtBusRouteID_1);
		
		txtBusLocation = new JTextField();
		txtBusLocation.setToolTipText("Location");
		txtBusLocation.setColumns(10);
		txtBusLocation.setBounds(277, 179, 114, 25);
		panel.add(txtBusLocation);
		
		txtPassengersNo_Bus = new JTextField();
		txtPassengersNo_Bus.setToolTipText("Passenger Numbers");
		txtPassengersNo_Bus.setColumns(10);
		txtPassengersNo_Bus.setBounds(403, 179, 114, 25);
		panel.add(txtPassengersNo_Bus);
		
		txtBusCapacity = new JTextField();
		txtBusCapacity.setToolTipText("Bus Capacity");
		txtBusCapacity.setColumns(10);
		txtBusCapacity.setBounds(529, 179, 114, 25);
		panel.add(txtBusCapacity);
		
		txtBusSpeed = new JTextField();
		txtBusSpeed.setToolTipText("Bus Speed");
		txtBusSpeed.setColumns(10);
		txtBusSpeed.setBounds(655, 179, 114, 25);
		panel.add(txtBusSpeed);
		
		btnAddBus = new JButton("Add Bus");
		btnAddBus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 String text1 = txtBusID.getText();
				 String text2 = txtBusRouteID_1.getText();
				 String text3 = txtBusLocation.getText();
				 String text4 = txtPassengersNo_Bus.getText();
				 String text5 = txtBusCapacity.getText();
				 String text6 = txtBusSpeed.getText();
				 textArea_1.append("add_bus"+","+text1+","+text2+","+text3+","+text4+","+text5+","+text6+"\n");
				 txtBusID.setText("");
				 txtBusRouteID_1.setText("");
				 txtBusLocation.setText("");	
				 txtPassengersNo_Bus.setText("");
				 txtBusCapacity.setText("");
				 txtBusSpeed.setText("");
			}
		});
		btnAddBus.setToolTipText("Need Bus ID, Bus Route ID, Location, Passenger Number, Bus Capacity, and Bus Speed");
		btnAddBus.setBounds(818, 179, 147, 25);
		panel.add(btnAddBus);
				
		txtNextStopID_Train = new JTextField();
		txtNextStopID_Train.setToolTipText("Next Stop ID");
		txtNextStopID_Train.setColumns(10);
		txtNextStopID_Train.setBounds(151, 216, 114, 25);
		panel.add(txtNextStopID_Train);
		
		btnExtendTrainRoute = new JButton("Extend Train Route");
		btnExtendTrainRoute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String text1 = txtRouteID_Train.getText();
				 String text2 = txtNextStopID_Train.getText();
				 textArea_1.append("extend_train_route"+","+text1+","+text2+"\n");
				 txtRouteID_Train.setText("");
				 txtNextStopID_Train.setText("");
			}
		});
		btnExtendTrainRoute.setToolTipText("Need Tain Route ID, Next Stop ID");
		btnExtendTrainRoute.setBounds(797, 216, 168, 25);
		panel.add(btnExtendTrainRoute);
		
		txtRouteID_Bus = new JTextField();
		txtRouteID_Bus.setToolTipText("Bus Route ID");
		txtRouteID_Bus.setColumns(10);
		txtRouteID_Bus.setBounds(25, 253, 114, 25);
		panel.add(txtRouteID_Bus);
		
		txtNextStopID_Bus = new JTextField();
		txtNextStopID_Bus.setToolTipText("Next Stop ID");
		txtNextStopID_Bus.setColumns(10);
		txtNextStopID_Bus.setBounds(151, 253, 114, 25);
		panel.add(txtNextStopID_Bus);
		
		btnExtendBusRoute = new JButton("Extend Bus Route");
		btnExtendBusRoute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String text1 = txtRouteID_Bus.getText();
				 String text2 = txtNextStopID_Bus.getText();
				 textArea_1.append("extend_bus_route"+","+text1+","+text2+"\n");
				 txtRouteID_Bus.setText("");
				 txtNextStopID_Bus.setText("");
			}
		});
		btnExtendBusRoute.setToolTipText("Need Bus Route ID, and Next Stop ID");
		btnExtendBusRoute.setBounds(797, 253, 168, 25);
		panel.add(btnExtendBusRoute);
		
		txtEventRank = new JTextField();
		txtEventRank.setToolTipText("Event Rank");
		txtEventRank.setColumns(10);
		txtEventRank.setBounds(25, 290, 114, 25);
		panel.add(txtEventRank);
		
		txtEventID = new JTextField();
		txtEventID.setToolTipText("Event ID");
		txtEventID.setColumns(10);
		txtEventID.setBounds(277, 290, 114, 25);
		panel.add(txtEventID);
		
		btnAddEvent = new JButton("Add Event");
		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String text1 = txtEventRank.getText();
				 String text2 = comboBoxEventType.getSelectedItem().toString();;
				 String text3 = txtEventID.getText();
				 textArea_1.append("add_event"+","+text1+","+text2+","+text3+"\n");
				 txtEventRank.setText("");
				 txtEventID.setText("");
			}
		});
		btnAddEvent.setToolTipText("Need Event Rank, Event Type, and Event ID");
		btnAddEvent.setBounds(818, 290, 147, 25);
		panel.add(btnAddEvent);
		
		txtStepNumber = new JTextField();
		txtStepNumber.setToolTipText("Step Numbers");
		txtStepNumber.setColumns(10);
		txtStepNumber.setBounds(25, 327, 114, 25);
		panel.add(txtStepNumber);
		
		txtFrequencyOfCompleted = new JTextField();
		txtFrequencyOfCompleted.setToolTipText("Display Frequency of Completes");
		txtFrequencyOfCompleted.setColumns(10);
		txtFrequencyOfCompleted.setBounds(151, 327, 114, 25);
		panel.add(txtFrequencyOfCompleted);
		
		txtSecondsOfPause = new JTextField();
		txtSecondsOfPause.setToolTipText("Seconds of pause");
		txtSecondsOfPause.setColumns(10);
		txtSecondsOfPause.setBounds(277, 327, 114, 25);
		panel.add(txtSecondsOfPause);
		
		btnStepMulti = new JButton("Step Multi");
		btnStepMulti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String text1 = txtStepNumber.getText();
				 String text2 = txtFrequencyOfCompleted.getText();
				 String text3 = txtSecondsOfPause.getText();
				 String text4 = txtFrequencyOfModel.getText();
				 textArea_1.append("step_multi"+","+text1+","+text2+","+text3+","+text4+"\n");
				 txtStepNumber.setText("");
				 txtFrequencyOfCompleted.setText("");
				 txtSecondsOfPause.setText("");	
				 txtFrequencyOfModel.setText("");
			}
		});
		btnStepMulti.setToolTipText("Set up numbers of steps to run and frequency of report, need Step Numbers, Display Frequency of Completes, Seconds of pause, and Display Frequency of Model");
		btnStepMulti.setBounds(818, 327, 147, 25);
		panel.add(btnStepMulti);
		
		txtFrequencyOfModel = new JTextField();
		txtFrequencyOfModel.setToolTipText("Display Frequency of Model");
		txtFrequencyOfModel.setColumns(10);
		txtFrequencyOfModel.setBounds(403, 327, 114, 25);
		panel.add(txtFrequencyOfModel);
		
		comboBoxEventType = new JComboBox<Object>();
		comboBoxEventType.setModel(new DefaultComboBoxModel<Object>(new String[] {"move_bus", "move_train"}));
		comboBoxEventType.setToolTipText("Event Type");
		comboBoxEventType.setBounds(151, 290, 114, 24);
		panel.add(comboBoxEventType);
		
		txtRouteID_Train = new JTextField();
		txtRouteID_Train.setToolTipText("Train Route ID");
		txtRouteID_Train.setColumns(10);
		txtRouteID_Train.setBounds(25, 216, 114, 25);
		panel.add(txtRouteID_Train);
		
				
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(173, 216, 230), 8));
		panel_3.setBounds(773, 504, 277, 253);
		this.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnSystemStart = new JButton("System Start");
		btnSystemStart.addActionListener(new ActionListener() {
			//@SuppressWarnings("resource")
			public void actionPerformed(ActionEvent arg0) {
							
				new Thread(new Runnable() {
					@Override
					public void run() {
						System.out.println("Mass Transit Simulation System Starting...");
						String text = textArea_1.getText();
						commandInterpreter = new SimDriver();  		
				        commandInterpreter.runInterpreter(text, textArea);
					}
				}).start();
		        
			}
		});
		btnSystemStart.setBounds(63, 21, 151, 25);
		panel_3.add(btnSystemStart);
		
		btnSystemReport = new JButton("System Report");
		btnSystemReport.setBounds(63, 67, 151, 25);
		panel_3.add(btnSystemReport);
		
		btnModelDisplay = new JButton("Model Display");
		btnModelDisplay.setBounds(63, 113, 151, 25);
		panel_3.add(btnModelDisplay);
		
		btnSystemStop = new JButton("System Stop");
		btnSystemStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				commandInterpreter.runInterpreter("quit", textArea);
			}
		});
		btnSystemStop.setBounds(63, 159, 151, 25);
		panel_3.add(btnSystemStop);
		
		JButton btnUploadData = new JButton("Upload Data");
		btnUploadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnUploadData.setBounds(63, 205, 151, 25);
		panel_3.add(btnUploadData);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(173, 216, 230), 8));
		panel_4.setBounds(1062, 106, 515, 651);
		this.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		button = new JButton("clear");
		button.setFont(new Font("Dialog", Font.PLAIN, 11));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.selectAll();
                textArea.replaceSelection("");
			}
		});
		button.setBounds(433, 11, 70, 20);
		panel_4.add(button);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 12, 491, 627);
		panel_4.add(tabbedPane);
		
			
		textArea = new ConsoleText();
		textArea.setBounds(6, 30, 474, 540);
		JScrollPane Report = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		tabbedPane.addTab("Console Report", null, Report, null);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("New tab", null, scrollPane, null);
		//Report.setLayout(null);
				
			
		
		JLabel lblM = new JLabel("Mass Transit Simulation System");
		lblM.setForeground(Color.DARK_GRAY);
		lblM.setFont(new Font("Dialog", Font.BOLD, 40));
		lblM.setBounds(448, 12, 734, 112);
		this.getContentPane().add(lblM);
	}
}
