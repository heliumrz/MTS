package edu.gatech;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
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
import java.io.File;

public class mtsGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtStopId;
	private JTextField txtStopName;
	private JTextField txtXCoordinate;
	private JTextField txtYCoordinate;
	private JTextField txtRiderRandomNumber;
	private JTextField txtBusRouteID;
	private JTextField txtBusRouteName;
	private JTextField txtBusRouteNumber;
	private JButton btnAddBusRoute;
	private JTextField txtTrainID;
	private JTextField txtTrainRouteId;
	private JTextField txtTrainLocation;
	private JTextField txtTrainCapacity;
	private JTextField txtTrainSpeed;
	private JButton btnAddTrain;
	private JTextField txtBusID;
	private JTextField txtBusRouteID_1;
	private JTextField txtBusLocation;
	private JTextField txtBusCapacity;
	private JTextField txtBusSpeed;
	private JButton btnAddBus;
	private JTextField txtNextStopID_Train;
	private JButton btnExtendTrainRoute;
	private JTextField txtRouteID_Bus;
	private JTextField txtNextStopID_Bus;
	private JButton btnExtendBusRoute;
	private JTabbedPane tabbedPane;
	private JButton btnSystemReport;
	private JButton btnModelDisplay;
	private JButton btnGenerateMap;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private JTextField txtTrainRouteNumber;
	private JTextField txtTrainRouteName;
	private JTextField txtRouteID_Train;
	private JButton button;
	
	SimDriver commandInterpreter;
	private JTextField txtStepNumber;
	private JTextField txtDisplayFrequencyOfComplete;
	private JTextField txtSecondsOfPause;
	private JTextField txtDisplayFrequencyOfModel;
	private JTextField txtrailRouteID;
	private JTextField txtStopID1_1;
	private JTextField txtStopID2_1;
	private JTextField txtDistance_1;
	private JTextField txtEventRank;
	private JTextField txtEventID;
	private JTextField txtbusRouteID;
	private JTextField txtstopID1;
	private JTextField txtstopID2;
	private JTextField txtDistance;
	private JTextField txtminSpeed;
	private JTextField txtavgSpeed;
	private JTextField txtmaxSpeed;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mtsGUI frame = new mtsGUI();
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
	public mtsGUI() {
		this.getContentPane().setBackground(new Color(211, 211, 211));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    setBounds(0, 0, (int) dim.getWidth(), (int) dim.getHeight());
	    //setLocationRelativeTo(null);
		//this.setBounds(0, 0, 1600, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		
		JComponent panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(173, 216, 230), 8));
		panel_2.setBounds(62, 584, 743, 273);
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
		tabbedPane_1.setBounds(12, 12, 719, 249);
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
		panel.setBounds(62, 106, 1222, 466);
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
		txtTrainRouteName.setBounds(277, 68, 114, 25);
		panel.add(txtTrainRouteName);
		
		txtStopName = new JTextField();
		txtStopName.setToolTipText("Stop Name");
		txtStopName.setColumns(10);
		txtStopName.setBounds(151, 31, 114, 25);
		panel.add(txtStopName);
		
		JButton btnAddStop = new JButton("Add stop");
		btnAddStop.setToolTipText("Need Stop ID, Stop name, x-coordinate,  y-coordinate, and Rider Random Number");
		btnAddStop.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				 String text1 = txtStopId.getText();
				 String text2 = txtStopName.getText();
				 String text3 = txtXCoordinate.getText();
				 String text4 = txtYCoordinate.getText();
				 String text5 = txtRiderRandomNumber.getText();
				
				 try {
					Integer.parseInt(text1);
					if (text2.isEmpty()) 
					    throw new NumberFormatException();
					Double.parseDouble(text3);
					Double.parseDouble(text4);
					Integer.parseInt(text5);
					textArea_1.append("add_stop"+","+text1+","+text2+","+text3+","+ text4+","+ text5 + "\n");
					    
					} catch (NumberFormatException e) {
						System.out.println("Please enter interger number as Stop Id and Rider Random Number, characters \n " +"as Stop Name, double numbers as XCoordinate and YCoordinate.");
					}
				 
				 
				 //textField.selectAll();
				 txtStopId.setText("");
				 txtStopName.setText("");
				 txtXCoordinate.setText("");
				 txtYCoordinate.setText("");
			}	 
			
		});
		btnAddStop.setBounds(1004, 31, 191, 25);
		panel.add(btnAddStop);
		
		txtXCoordinate = new JTextField();
		txtXCoordinate.setToolTipText("x-coordinate");
		txtXCoordinate.setColumns(10);
		txtXCoordinate.setBounds(277, 31, 114, 25);
		panel.add(txtXCoordinate);
		
		txtYCoordinate = new JTextField();
		txtYCoordinate.setToolTipText("y-coordinate");
		txtYCoordinate.setColumns(10);
		txtYCoordinate.setBounds(403, 31, 114, 25);
		panel.add(txtYCoordinate);
		
		txtRiderRandomNumber = new JTextField();
		txtRiderRandomNumber.setToolTipText("riderRandomNumber");
		txtRiderRandomNumber.setColumns(10);
		txtRiderRandomNumber.setBounds(529, 31, 114, 25);
		panel.add(txtRiderRandomNumber);
		
	    txtTrainRouteNumber = new JTextField();
	    txtTrainRouteNumber.setToolTipText("Stop Numbers");
	    txtTrainRouteNumber.setColumns(10);
	    txtTrainRouteNumber.setBounds(151, 68, 114, 25);
		panel.add(txtTrainRouteNumber);
		
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
				 String text3 = txtTrainRouteNumber.getText();
				 try {
						Integer.parseInt(text1);
						if (text2.isEmpty()) 
						    throw new NumberFormatException();
						
						Integer.parseInt(text3);
						textArea_1.append("add_train_route"+","+text1+","+text2+","+text3+"\n");
						    
						} catch (NumberFormatException e) {
							System.out.println("Please enter interger number as Train Route ID and Route Number, characters as Train Route Name.");
						}
				 
				 txtTrainRouteId.setText("");
				 txtTrainRouteName.setText("");
				 txtTrainRouteNumber.setText("");
			}
		});
		btnAddTrainRoute.setToolTipText("Need Train Route ID, Train Route Name, and Route Numbers");
		btnAddTrainRoute.setBounds(1004, 68, 191, 25);
		panel.add(btnAddTrainRoute);
		
		txtBusRouteID = new JTextField();
		txtBusRouteID.setToolTipText("Bus Route ID");
		txtBusRouteID.setColumns(10);
		txtBusRouteID.setBounds(25, 105, 114, 25);
		panel.add(txtBusRouteID);
		
		txtBusRouteName = new JTextField();
		txtBusRouteName.setToolTipText("Bus Route Name");
		txtBusRouteName.setColumns(10);
		txtBusRouteName.setBounds(277, 105, 114, 25);
		panel.add(txtBusRouteName);
		
		txtBusRouteNumber = new JTextField();
		txtBusRouteNumber.setToolTipText("Bus Route Number");
		txtBusRouteNumber.setColumns(10);
		txtBusRouteNumber.setBounds(151, 105, 114, 25);
		panel.add(txtBusRouteNumber);
		
		btnAddBusRoute = new JButton("Add Bus Route");
		btnAddBusRoute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String text1 = txtBusRouteID.getText();
				 String text2 = txtBusRouteName.getText();
				 String text3 = txtBusRouteNumber.getText();
				 try {
						Integer.parseInt(text1);
						if (text2.isEmpty()) 
						    throw new NumberFormatException();
						
						Integer.parseInt(text3);
						textArea_1.append("add_bus_route"+","+text1+","+text2+","+text3+"\n");
						    
						} catch (NumberFormatException e1) {
							System.out.println("Please enter interger number as Bus Route ID and Route Number, characters as Bus Route Name.");
						}
				 
				 txtBusRouteID.setText("");
				 txtBusRouteName.setText("");
				 txtBusRouteNumber.setText("");		
			}
		});
		btnAddBusRoute.setToolTipText("Need Bus Route ID, Bus Route Name, and Stop Numbers");
		btnAddBusRoute.setBounds(1004, 105, 191, 25);
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
		
		txtTrainCapacity = new JTextField();
		txtTrainCapacity.setToolTipText("Train Capacity");
		txtTrainCapacity.setColumns(10);
		txtTrainCapacity.setBounds(403, 142, 114, 25);
		panel.add(txtTrainCapacity);
		
		txtTrainSpeed = new JTextField();
		txtTrainSpeed.setToolTipText("Train Speed");
		txtTrainSpeed.setColumns(10);
		txtTrainSpeed.setBounds(529, 142, 114, 25);
		panel.add(txtTrainSpeed);
		
		btnAddTrain = new JButton("Add Train");
		btnAddTrain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 String text1 = txtTrainID.getText();
				 String text2 = txtTrainRouteID_1.getText();
				 String text3 = txtTrainLocation.getText();
				 //String text4 = txtPassengersNo_Train.getText();
				 String text4 = txtTrainCapacity.getText();
				 String text5 = txtTrainSpeed.getText();
				 

				 try {
					Integer.parseInt(text1);
					Integer.parseInt(text2);
					Integer.parseInt(text3);
					Integer.parseInt(text4);
					Double.parseDouble(text5);
					
					textArea_1.append("add_train"+","+text1+","+text2+","+text3+","+text4+","+text5+"\n");
					    
					} catch (NumberFormatException e) {
						System.out.println("Please enter interger numbers as Train Id, Train Route Id, Location and Capacity, double number as Speed.");
					}
				 
				
				 txtTrainID.setText("");
				 txtTrainLocation.setText("");
				 //txtPassengersNo_Train.setText("");	
				 txtTrainCapacity.setText("");
				 txtTrainRouteID_1.setText("");
				 txtTrainSpeed.setText("");
			}
			
		});
		btnAddTrain.setToolTipText("Need Train ID, Train Route ID, Location, Passenger Numbers, Train Capacity, and Train Speed");
		btnAddTrain.setBounds(1004, 142, 191, 25);
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
		
		txtBusCapacity = new JTextField();
		txtBusCapacity.setToolTipText("Bus Capacity");
		txtBusCapacity.setColumns(10);
		txtBusCapacity.setBounds(403, 179, 114, 25);
		panel.add(txtBusCapacity);
		
		txtBusSpeed = new JTextField();
		txtBusSpeed.setToolTipText("Bus Speed");
		txtBusSpeed.setColumns(10);
		txtBusSpeed.setBounds(529, 179, 114, 25);
		panel.add(txtBusSpeed);
		
		btnAddBus = new JButton("Add Bus");
		btnAddBus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 String text1 = txtBusID.getText();
				 String text2 = txtBusRouteID_1.getText();
				 String text3 = txtBusLocation.getText();
				 
				 String text4 = txtBusCapacity.getText();
				 String text5 = txtBusSpeed.getText();
				 try {
						Integer.parseInt(text1);
						Integer.parseInt(text2);
						Integer.parseInt(text3);
						Integer.parseInt(text4);
						Double.parseDouble(text5);
						
						textArea_1.append("add_bus"+","+text1+","+text2+","+text3+","+text4+","+text5+"\n");
						    
						} catch (NumberFormatException e) {
							System.out.println("Please enter interger numbers as Bus Id, Bus Route Id, Location and Capacity, double number as Speed.");
						}
				
				 txtBusID.setText("");
				 txtBusRouteID_1.setText("");
				 txtBusLocation.setText("");	
				 
				 txtBusCapacity.setText("");
				 txtBusSpeed.setText("");
			}
		});
		btnAddBus.setToolTipText("Need Bus ID, Bus Route ID, Location, Passenger Number, Bus Capacity, and Bus Speed");
		btnAddBus.setBounds(1004, 179, 191, 25);
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
				 try {
						Integer.parseInt(text1);
						Integer.parseInt(text2);
						textArea_1.append("extend_train_route"+","+text1+","+text2+"\n");						    
						} catch (NumberFormatException e1) {
							System.out.println("Please enter interger numbers as Route Id and Next Stop Id.");
						}
				
				 txtRouteID_Train.setText("");
				 txtNextStopID_Train.setText("");
			}
		});
		btnExtendTrainRoute.setToolTipText("Need Tain Route ID, Next Stop ID");
		btnExtendTrainRoute.setBounds(1004, 216, 191, 25);
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
				 try {
						Integer.parseInt(text1);
						Integer.parseInt(text2);
						textArea_1.append("extend_bus_route"+","+text1+","+text2+"\n");					    
						} catch (NumberFormatException e1) {
							System.out.println("Please enter interger numbers as Route Id and Next Stop Id.");
						}
				 
				 txtRouteID_Bus.setText("");
				 txtNextStopID_Bus.setText("");
			}
		});
		btnExtendBusRoute.setToolTipText("Need Bus Route ID, and Next Stop ID");
		btnExtendBusRoute.setBounds(1004, 253, 191, 25);
		panel.add(btnExtendBusRoute);
		
		txtRouteID_Train = new JTextField();
		txtRouteID_Train.setToolTipText("Train Route ID");
		txtRouteID_Train.setColumns(10);
		txtRouteID_Train.setBounds(25, 216, 114, 25);
		panel.add(txtRouteID_Train);
		
		txtStepNumber = new JTextField();
		txtStepNumber.setToolTipText("Step Numbers");
		txtStepNumber.setColumns(10);
		txtStepNumber.setBounds(25, 401, 114, 25);
		panel.add(txtStepNumber);
		
		txtDisplayFrequencyOfComplete = new JTextField();
		txtDisplayFrequencyOfComplete.setToolTipText("Display Frequency of Completes");
		txtDisplayFrequencyOfComplete.setColumns(10);
		txtDisplayFrequencyOfComplete.setBounds(151, 401, 114, 25);
		panel.add(txtDisplayFrequencyOfComplete);
		
		txtSecondsOfPause = new JTextField();
		txtSecondsOfPause.setToolTipText("Seconds of pause");
		txtSecondsOfPause.setColumns(10);
		txtSecondsOfPause.setBounds(277, 401, 114, 25);
		panel.add(txtSecondsOfPause);
		
		txtDisplayFrequencyOfModel = new JTextField();
		txtDisplayFrequencyOfModel.setToolTipText("Display Frequency of Model");
		txtDisplayFrequencyOfModel.setColumns(10);
		txtDisplayFrequencyOfModel.setBounds(403, 401, 114, 25);
		panel.add(txtDisplayFrequencyOfModel);
		
		JButton btnStepMulti = new JButton("Step Multi");
		btnStepMulti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String text1 = txtStepNumber.getText();
				 String text2 = txtDisplayFrequencyOfComplete.getText();
				 String text3 = txtSecondsOfPause.getText();
				 String text4 = txtDisplayFrequencyOfModel.getText();
				 
				 try {
						Integer.parseInt(text1);
						Integer.parseInt(text2);
						Integer.parseInt(text3);
						Integer.parseInt(text4);
												
						textArea_1.append("step_multi"+","+text1+","+text2+","+text3+","+text4+"\n");
						    
						} catch (NumberFormatException e1) {
							System.out.println("Please enter interger numbers as Step Numbers, Display Frequency of Completes, Seconds of pause, and Display Frequency of Model.");
						}
				 txtStepNumber.setText("");
				txtDisplayFrequencyOfComplete.setText("");
				txtSecondsOfPause.setText("");
				txtDisplayFrequencyOfModel.setText("");
			}
		});
		btnStepMulti.setToolTipText("Set up numbers of steps to run and frequency of report, need Step Numbers, Display Frequency of Completes, Seconds of pause, and Display Frequency of Model");
		btnStepMulti.setBounds(1004, 401, 191, 25);
		panel.add(btnStepMulti);
		
		txtrailRouteID = new JTextField();
		txtrailRouteID.setToolTipText("railRouteID");
		txtrailRouteID.setColumns(10);
		txtrailRouteID.setBounds(25, 327, 114, 25);
		panel.add(txtrailRouteID);
		
		txtStopID1_1 = new JTextField();
		txtStopID1_1.setToolTipText("StopID1");
		txtStopID1_1.setColumns(10);
		txtStopID1_1.setBounds(151, 327, 114, 25);
		panel.add(txtStopID1_1);
		
		txtStopID2_1 = new JTextField();
		txtStopID2_1.setToolTipText("StopID2");
		txtStopID2_1.setColumns(10);
		txtStopID2_1.setBounds(277, 327, 114, 25);
		panel.add(txtStopID2_1);
		
		txtDistance_1 = new JTextField();
		txtDistance_1.setToolTipText("Distance");
		txtDistance_1.setColumns(10);
		txtDistance_1.setBounds(403, 327, 114, 25);
		panel.add(txtDistance_1);
		
		txtEventRank = new JTextField();
		txtEventRank.setToolTipText("Event Rank");
		txtEventRank.setColumns(10);
		txtEventRank.setBounds(25, 364, 114, 25);
		panel.add(txtEventRank);
		
		JComboBox<Object> comboBoxEventType = new JComboBox<Object>();
		comboBoxEventType.setModel(new DefaultComboBoxModel(new String[] {"move_bus", "move_train"}));
		comboBoxEventType.setToolTipText("Event Type");
		comboBoxEventType.setBounds(151, 364, 114, 24);
		panel.add(comboBoxEventType);
		
		txtEventID = new JTextField();
		txtEventID.setToolTipText("Event ID");
		txtEventID.setColumns(10);
		txtEventID.setBounds(277, 364, 114, 25);
		panel.add(txtEventID);
		
		JButton btnAddEvent = new JButton("Add Event");
		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 String text1 = txtEventRank.getText();
				 String text2 = comboBoxEventType.getSelectedItem().toString();
				 String text3 = txtEventID.getText();
				 try {
						Integer.parseInt(text1);
											
						Integer.parseInt(text3);
						textArea_1.append("add_event"+","+text1+","+text2+","+text3+"\n");
						    
						} catch (NumberFormatException e1) {
							System.out.println("Please enter interger number as Event Rank, Event Type, and Event ID.");
						}
				 
				 txtEventRank.setText("");
				 txtEventID.setText("");
			}
		});
		btnAddEvent.setToolTipText("Need Event Rank, Event Type, and Event ID");
		btnAddEvent.setBounds(1004, 364, 191, 25);
		panel.add(btnAddEvent);
		
		txtbusRouteID = new JTextField();
		txtbusRouteID.setToolTipText("busRouteID");
		txtbusRouteID.setColumns(10);
		txtbusRouteID.setBounds(25, 290, 114, 25);
		panel.add(txtbusRouteID);
		
		txtstopID1 = new JTextField();
		txtstopID1.setToolTipText("stopID1");
		txtstopID1.setColumns(10);
		txtstopID1.setBounds(151, 290, 114, 25);
		panel.add(txtstopID1);
		
		txtstopID2 = new JTextField();
		txtstopID2.setToolTipText("stopID2");
		txtstopID2.setColumns(10);
		txtstopID2.setBounds(277, 290, 114, 25);
		panel.add(txtstopID2);
		
		txtDistance = new JTextField();
		txtDistance.setToolTipText("distance");
		txtDistance.setColumns(10);
		txtDistance.setBounds(403, 290, 114, 25);
		panel.add(txtDistance);
		
		txtminSpeed = new JTextField();
		txtminSpeed.setToolTipText("minSpeed");
		txtminSpeed.setColumns(10);
		txtminSpeed.setBounds(529, 290, 114, 25);
		panel.add(txtminSpeed);
		
		txtavgSpeed = new JTextField();
		txtavgSpeed.setToolTipText("avg speed");
		txtavgSpeed.setColumns(10);
		txtavgSpeed.setBounds(655, 290, 114, 25);
		panel.add(txtavgSpeed);
		
		JButton btnSetRoadCond = new JButton("Set Road Condition");
		btnSetRoadCond.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text1 = txtbusRouteID.getText();
				 String text2 = txtstopID1.getText();
				 String text3 = txtstopID2.getText();
				 String text4 = txtDistance.getText();
				 String text5 = txtminSpeed.getText();
				 String text6 = txtavgSpeed.getText();
			     String text7 = txtmaxSpeed.getText();
				 try {
						Integer.parseInt(text1);
						Integer.parseInt(text2);
						Integer.parseInt(text3);
						Double.parseDouble(text4);
						Double.parseDouble(text5);
						Double.parseDouble(text6);
						Double.parseDouble(text7);
						textArea_1.append("set_road_condition"+","+text1+","+text2+","+text3+","+text4+","+text5+","+text6+","+text7+ "\n");
						    
						} catch (NumberFormatException e1) {
							System.out.println("Please enter int busRouteID, int stopID1, int stopID2, double distance, double lowestSpeed, double averageSpeed, double maxSpeed.");
						}
				 txtbusRouteID.setText("");
		         txtstopID1.setText("");
				txtstopID2.setText("");
				txtDistance.setText("");
				 txtminSpeed.setText("");
				   txtavgSpeed.setText("");
			      txtmaxSpeed.setText("");
			}
		});
		btnSetRoadCond.setToolTipText("Need int busRouteID, int stopID1, int stopID2, double distance, double lowestSpeed, double averageSpeed, double maxSpeed");
		btnSetRoadCond.setBounds(1004, 290, 191, 25);
		panel.add(btnSetRoadCond);
		
		JButton btnSetRailRoad = new JButton("Set Rail Stop Distance");
		btnSetRailRoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String text1 = txtrailRouteID.getText();
				 String text2 = txtStopID1_1.getText();
				 String text3 = txtStopID2_1.getText();
				 String text4 = txtDistance_1.getText();
				
				 try {
						Integer.parseInt(text1);
						Integer.parseInt(text2);
						Integer.parseInt(text3);
						Double.parseDouble(text4);
						
						textArea_1.append("set_rail_stop_distance"+","+text1+","+text2+","+text3+","+text4+"\n");
						    
						} catch (NumberFormatException e) {
							System.out.println("Please enter int railRouteID, int stopID1, int stopID2, Double distance.");
						}
				 txtrailRouteID.setText("");
				txtStopID1_1.setText("");
				 txtStopID2_1.setText("");
				 txtDistance_1.setText("");
			}
		});
		btnSetRailRoad.setToolTipText("Need int railRouteID, int stopID1, int stopID2, Double distance");
		btnSetRailRoad.setBounds(1004, 327, 191, 25);
		panel.add(btnSetRailRoad);
		
		txtmaxSpeed = new JTextField();
		txtmaxSpeed.setToolTipText("max speed");
		txtmaxSpeed.setColumns(10);
		txtmaxSpeed.setBounds(783, 290, 114, 25);
		panel.add(txtmaxSpeed);
		
				
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(173, 216, 230), 8));
		panel_3.setBounds(821, 584, 462, 273);
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
						System.out.println("Waiting for \"Generate Map\" command");
					}
				}).start();
		        
			}
		});
		btnSystemStart.setBounds(63, 64, 151, 25);
		panel_3.add(btnSystemStart);
		
		btnSystemReport = new JButton("System Report");
		btnSystemReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(commandInterpreter == null) {
					System.out.println("Please start the system first.");
				}
				else {
				   commandInterpreter.runInterpreter("system_report");
				}
			}
		});
		btnSystemReport.setBounds(242, 64, 151, 25);
		panel_3.add(btnSystemReport);
		
		btnModelDisplay = new JButton("Model Display");
		btnModelDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
			            if (!Desktop.isDesktopSupported()) {
			                System.err.println("Platform is not supported.");
			            }

			            if (!Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
			            	System.err.println("OPEN is not supported.");
			            }
			            String path="./mts_digraph.dot";
			            File file = new File(path);
			            Desktop.getDesktop().open(file);
		            
			        } catch (Throwable t) {
			        	System.err.println("Error using desktop open.");
			        }
				commandInterpreter.runInterpreter("display_model");
			}
		});
		btnModelDisplay.setBounds(242, 121, 151, 25);
		panel_3.add(btnModelDisplay);
		
		btnGenerateMap = new JButton("Generate Map");
		btnGenerateMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						String text = textArea_1.getText();
						commandInterpreter = new SimDriver();  		
				        commandInterpreter.runInterpreter(text);
					}
				}).start();
			}
		});
		btnGenerateMap.setBounds(63, 121, 151, 25);
		panel_3.add(btnGenerateMap);
		
		JButton btnUploadData = new JButton("Upload Data");
		btnUploadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnUploadData.setBounds(242, 180, 151, 25);
		panel_3.add(btnUploadData);
		
		JButton btnSystemStop = new JButton("Quit System");
		btnSystemStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(commandInterpreter == null) System.exit(0);
				commandInterpreter.runInterpreter("quit");
				System.out.println(" stop simulating");
				System.exit(0);
			}
		});
		btnSystemStop.setBounds(63, 180, 151, 25);
		panel_3.add(btnSystemStop);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(173, 216, 230), 8));
		panel_4.setBounds(1300, 106, 515, 751);
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
		tabbedPane.setBounds(12, 12, 491, 739);
		panel_4.add(tabbedPane);
		
			
		textArea = new ConsoleText();
		textArea.setBounds(6, 30, 474, 540);
		JScrollPane Report = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		tabbedPane.addTab("Console Report", null, Report, null);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Statistics Report", null, scrollPane, null);
		//Report.setLayout(null);
				
			
		
		JLabel lblM = new JLabel("Mass Transit Simulation System");
		lblM.setForeground(Color.DARK_GRAY);
		lblM.setFont(new Font("Dialog", Font.BOLD, 40));
		lblM.setBounds(448, 12, 734, 112);
		this.getContentPane().add(lblM);
	}
}
