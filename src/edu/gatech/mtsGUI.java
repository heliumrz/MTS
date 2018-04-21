package edu.gatech;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class mtsGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private JTextArea textArea_2;
	private JTextField txtTrainRouteNumber;
	private JTextField txtTrainRouteName;
	private JTextField txtRouteID_Train;
	
	SimDriver commandInterpreter;
	String reportText = "";
	private JTextField txtStepNumber;
	private JTextField txtDisplayFrequencyOfComplete;
	private JTextField txtSecondsOfPause;
	private JTextField txtDisplayFrequencyOfModel;
	private JTextField txtEventRank;
	private JTextField txtEventID;
	private JComboBox<Object> comboBox1;
	private JComboBox<Object> comboBox2;
	private JComboBox<Object> comboBox3;
	private JTextField txtBusRouteID_2;
	private JTextField txtRailRouteID;
	
	
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
		getContentPane().setLayout(null);
		
		
		JComponent panel_2 = new JPanel();
		panel_2.setBounds(62, 584, 743, 273);
		panel_2.setBorder(new LineBorder(new Color(173, 216, 230), 8));
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
		btnClear.setBounds(661, 10, 70, 20);
		panel_2.add(btnClear);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(12, 12, 719, 249);
		panel_2.add(tabbedPane_1);
		
		textArea_1 = new JTextArea();
		textArea_1.setText("add_stop,0,Appleville,0.0,0.08,1\n" + 
				"add_stop,1,Banana Bayou,0.04,0.1,2\n" + 
				"add_stop,2,Star City,0.08,0.16,1\n" + 
				"add_stop,3,Cherry City,0.16,0.1,1\n" + 
				"add_stop,4,West Side,0.01,0.01,2\n" + 
				"add_stop,5,South Side,0.04,0.01,1\n" + 
				"add_stop,6,East Side,0.1,0.02,1\n" + 
				"add_stop,7,Huangshan City,0.04,0.05,1\n" + 
				"add_stop,8,Jinzhai City,0.04,0.05,1\n" + 
				"add_stop,9,Hefei City,0.04,0.05,1\n" + 
				"add_stop,10,Guangzhou City,0.04,0.05,1\n" + 
				"add_stop,11,Chengdu City,0.04,0.05,1\n" + 
				"add_bus_route,0,0,Express\n" + 
				"add_bus_route,1,1,Perimeter\n" + 
				"add_rail_route,0,0,rail1one\n" + 
				"add_rail_route,1,1,rail1two\n" + 
				"extend_bus_route,0,2\n" + 
				"extend_bus_route,0,3\n" + 
				"extend_bus_route,0,4\n" + 
				"extend_bus_route,0,5\n" + 
				"extend_bus_route,1,6\n" + 
				"extend_bus_route,1,7\n" + 
				"extend_bus_route,1,8\n" + 
				"extend_bus_route,1,9\n" + 
				"extend_rail_route,0,0\n" + 
				"extend_rail_route,0,3\n" + 
				"extend_rail_route,0,7\n" + 
				"extend_rail_route,0,10\n" + 
				"extend_rail_route,1,1\n" + 
				"extend_rail_route,1,4\n" + 
				"extend_rail_route,1,8\n" + 
				"extend_rail_route,1,11\n" + 
				"add_bus,0,0,0,30,80.00\n" + 
				"add_bus,1,1,0,30,80.00\n" + 
				"add_bus,2,0,0,30,80.00\n" + 
				"add_bus,3,1,0,30,80.00\n" + 
				"add_train,0,0,0,200,100.00\n" + 
				"add_train,1,1,0,200,100.00\n" + 
				"set_road_condition,0,2,3,2.20,20.00,40.00,60.00\n" + 
				"set_road_condition,0,3,4,2.20,20.00,40.00,60.00\n" + 
				"set_road_condition,0,4,5,3.60,20.00,40.00,60.00\n" + 
				"set_road_condition,0,5,2,3.60,20.00,40.00,60.00\n" + 
				"set_road_condition,1,6,7,3.20,60.00,80.00,100.00\n" + 
				"set_road_condition,1,7,8,2.20,20.00,40.00,60.00\n" + 
				"set_road_condition,1,8,9,3.60,20.00,40.00,60.00\n" + 
				"set_road_condition,1,9,6,3.60,20.00,40.00,60.00\n" + 
				"set_rail_stop_distance,0,0,3,3.50\n" + 
				"set_rail_stop_distance,0,3,7,3.50\n" + 
				"set_rail_stop_distance,0,7,10,3.50\n" + 
				"set_rail_stop_distance,0,10,0,3.50\n" + 
				"set_rail_stop_distance,1,1,4,3.50\n" + 
				"set_rail_stop_distance,1,4,8,3.50\n" + 
				"set_rail_stop_distance,1,8,11,3.50\n" + 
				"set_rail_stop_distance,1,11,1,3.50\n" + 
				"add_event,0,move_bus,0\n" + 
				"add_event,0,move_bus,1\n" + 
				"add_event,0,move_train,0\n" + 
				"add_event,0,move_train,1\n" + 
				"add_event,5,move_bus,2\n" + 
				"add_event,5,move_bus,3\n" + 
				"step_multi,240,10,1,10\n" + 
				"system_report\n" + 
				"display_model\n" + 
				"quit");
		textArea_1.setBounds(12, 12, 675, 229);
		JScrollPane commandList = new JScrollPane(textArea_1,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		tabbedPane_1.addTab("Commands", null, commandList, null);
		//panel_2.setLayout(null);
		
								
		JPanel panel = new JPanel();
		panel.setBounds(62, 106, 1222, 466);
		panel.setBorder(new LineBorder(new Color(173, 216, 230), 8));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		txtTrainRouteName = new JTextField();
		txtTrainRouteName.setToolTipText("Train Route Name");
		txtTrainRouteName.setColumns(10);
		txtTrainRouteName.setBounds(277, 68, 114, 25);
		panel.add(txtTrainRouteName);
		
		JButton btnAddStop = new JButton("Add stop");
		btnAddStop.setToolTipText("Need Stop ID, Stop name, x-coordinate,  y-coordinate, and Rider Random Number");
		btnAddStop.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				 String text1 = comboBox1.getSelectedItem().toString();
				 
				 String text5 = txtRiderRandomNumber.getText();
				
				 try {
					
					Integer.parseInt(text5);
					textArea_1.append("add_stop"+","+text1+","+ text5 + "\n");
					    
					} catch (NumberFormatException e) {
						System.out.println("Please enter interger number as Rider Random Number.");
					}
				 
				 
 				  txtRiderRandomNumber.setText("");
			}	 
			
		});
		btnAddStop.setBounds(1004, 31, 191, 25);
		panel.add(btnAddStop);
		
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
		
		txtEventRank = new JTextField();
		txtEventRank.setToolTipText("Event Rank");
		txtEventRank.setColumns(10);
		txtEventRank.setBounds(25, 364, 114, 25);
		panel.add(txtEventRank);
		
		JComboBox<Object> comboBoxEventType = new JComboBox<Object>();
		comboBoxEventType.setModel(new DefaultComboBoxModel<Object>(new String[] {"move_bus", "move_train"}));
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
		
		JButton btnSetRoadCond = new JButton("Set Road Condition");
		btnSetRoadCond.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String text1 = txtBusRouteID_2.getText();
				 String text2 = comboBox2.getSelectedItem().toString();
				
				 try {
						Integer.parseInt(text1);
						
						textArea_1.append("set_road_condition"+","+text1+","+text2+"\n");
						    
						} catch (NumberFormatException e1) {
							System.out.println("Please enter int busRouteID.");
						}
				 txtBusRouteID_2.setText("");
		    }
		});
		btnSetRoadCond.setToolTipText("Need int busRouteID, int stopID1, int stopID2, double distance, double lowestSpeed, double averageSpeed, double maxSpeed");
		btnSetRoadCond.setBounds(1004, 290, 191, 25);
		panel.add(btnSetRoadCond);
		
		JButton btnSetRailRoad = new JButton("Set Rail Stop Distance");
		btnSetRailRoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String text1 = txtRailRouteID.getText();
				String text2 = comboBox3.getSelectedItem().toString();
								
				 try {
						Integer.parseInt(text1);
						
						textArea_1.append("set_rail_stop_distance"+","+text1+","+text2+"\n");
						
						    
						} catch (NumberFormatException e) {
							System.out.println("Please enter int railRouteID.");
						}
				 txtRailRouteID.setText("");
				
				
			}
		});
		btnSetRailRoad.setToolTipText("Need int railRouteID, int stopID1, int stopID2, Double distance");
		btnSetRailRoad.setBounds(1004, 327, 191, 25);
		panel.add(btnSetRailRoad);
		
		comboBox1 = new JComboBox<Object>();
		comboBox1.setToolTipText("Stop ID, Stop name, x-coordinate,  y-coordinate");
		comboBox1.setBounds(25, 31, 492, 24);
		panel.add(comboBox1);
		
		comboBox2 = new JComboBox<Object>();
		comboBox2.setToolTipText(" int stopID1, int stopID2, double distance, double lowestSpeed, double averageSpeed, double maxSpeed");
		comboBox2.setBounds(151, 290, 492, 24);
		panel.add(comboBox2);
		
		comboBox3 = new JComboBox<Object>();
		comboBox3.setToolTipText(" int stopID1, int stopID2, Double distance");
		comboBox3.setBounds(151, 326, 492, 24);
		panel.add(comboBox3);
		
		txtBusRouteID_2 = new JTextField();
		txtBusRouteID_2.setToolTipText("Bus Route ID");
		txtBusRouteID_2.setColumns(10);
		txtBusRouteID_2.setBounds(25, 290, 114, 25);
		panel.add(txtBusRouteID_2);
		
		txtRailRouteID = new JTextField();
		txtRailRouteID.setToolTipText("Train Route ID");
		txtRailRouteID.setColumns(10);
		txtRailRouteID.setBounds(25, 326, 114, 25);
		panel.add(txtRailRouteID);
		
				
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(821, 584, 462, 273);
		panel_3.setBorder(new LineBorder(new Color(173, 216, 230), 8));
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
				   reportText = commandInterpreter.runInterpreter("system_report");
				   textArea_2.setText(reportText);
				}
			}
		});
		btnSystemReport.setBounds(242, 94, 151, 25);
		panel_3.add(btnSystemReport);
		
				
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
		
		btnModelDisplay.setBounds(242, 151, 151, 25);
		panel_3.add(btnModelDisplay);
		
		JButton btnUploadData = new JButton("Upload Data");
		btnUploadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(panel_3); //Where frame is the parent component
				
				ArrayList<String> list1 = new ArrayList<String>();
				ArrayList<String> list2 = new ArrayList<String>();
				ArrayList<String> list3 = new ArrayList<String>();
				File file = null;
				if (returnVal == JFileChooser.APPROVE_OPTION) {
				    file = fc.getSelectedFile();
				    BufferedReader br;
					try {
						br = new BufferedReader(new FileReader(file));
						String line;
						//System.out.println("Reading file");
						while ((line=br.readLine())!=null) {
							//System.out.println(line);
						        String[] s = line.split(",");
						        String s1 = "";
						        String s2 = "";
						        String s3 = "";
						        //System.out.println(s[0]);
						        if(s.length ==4) {
						        	for(int i=0; i<4; i++) {
						        		if(s[i].length()!=0) {
						        			s1 += s[i]+",";
						        			}
						        	}
						        }
						        if(s.length == 10) {
						        	for(int i=0; i<4; i++) {
						        		if(s[i].length()!=0) {
						        			s1 += s[i]+",";
						        			}
						        	}
						        	for(int i=4; i<10; i++) {
						        		if(s[i]!=""){
						        			s2 += s[i]+",";
						        			}
						        	}
						        }
						        if(s.length == 13) {
						        	for(int i=0; i<4; i++) {
						        		if(s[i].length()!=0) {
						        			s1 += s[i]+",";
						        			}
						        	}
						        	for(int i=4; i<10; i++) {
						        		if(s[i]!=""){
						        			s2 += s[i]+",";
						        			}
						        	}
						        	for(int i=10; i<13; i++) {
						        		if(s[i]!=""){
						        			s3 += s[i]+",";
						        			}
						        	}
						        }
						       
						        if(s1.length()!=0)	
						           list1.add(s1.substring(0, s1.length()-1));
						        if(s2.length()!=0)   
						           list2.add(s2.substring(0, s2.length()-1));
						        if(s3.length()!=0)   
							           list3.add(s3.substring(0, s3.length()-1));
						        
						    }
					
				        br.close();
				        
				       
				        String[] cb1 = list1.toArray(new String[list1.size()]);
				        String[] cb2 = list2.toArray(new String[list2.size()]);
				        String[] cb3 = list3.toArray(new String[list2.size()]);
				        comboBox1.setModel(new DefaultComboBoxModel<Object>(cb1));
				        comboBox2.setModel(new DefaultComboBoxModel<Object>(cb2));
				        comboBox3.setModel(new DefaultComboBoxModel<Object>(cb3));
				        
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    
				   
				} else {
				    //User did not choose a valid file
					System.out.print("Invalid file type.");
				}
				
				
			    
			   
			    
			}
		});
		btnUploadData.setBounds(242, 210, 151, 25);
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
		
		JButton btnAutoRun = new JButton("Auto Run");
		btnAutoRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(panel_3); //Where frame is the parent component
				
				File file = null;
				if (returnVal == JFileChooser.APPROVE_OPTION) {
				    file = fc.getSelectedFile();
				    BufferedReader br;
					try {
						br = new BufferedReader(new FileReader(file));
						String text = br.lines().collect(Collectors.joining("\n"));
						new Thread(new Runnable() {
							@Override
							public void run() {
								
								commandInterpreter = new SimDriver(); 		
						        commandInterpreter.runInterpreter(text);
							}
						}).start();
						
				        br.close();
				      
				      			        
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    
				   
				} else {
				    //User did not choose a valid file
					System.out.print("Invalid file type.");
				}
				
				
			}
		});
		btnAutoRun.setToolTipText("Please select input .txt file to run the system");
		btnAutoRun.setBounds(242, 40, 151, 25);
		panel_3.add(btnAutoRun);
		//Report.setLayout(null);
				
			
		
		JLabel lblM = new JLabel("Mass Transit Simulation System");
		lblM.setBounds(448, 12, 734, 112);
		lblM.setForeground(Color.DARK_GRAY);
		lblM.setFont(new Font("Dialog", Font.BOLD, 40));
		this.getContentPane().add(lblM);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(1300, 106, 515, 751);
		panel_4.setBorder(new LineBorder(new Color(173, 216, 230), 8));
		this.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(8, 8, 499, 735);
		panel_4.add(tabbedPane);
		
			
		textArea = new ConsoleText();
		textArea.setBounds(6, 30, 474, 540);
		textArea.setEditable(false);
		JScrollPane Report = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		tabbedPane.addTab("Console Report", null, Report, null);
		
		JButton btnClear_1 = new JButton("clear");
		btnClear_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.selectAll();
                textArea.replaceSelection("");
			}
		});
		btnClear_1.setFont(new Font("Dialog", Font.PLAIN, 11));
		Report.setColumnHeaderView(btnClear_1);
		
		textArea_2 = new JTextArea();
		textArea_2.setBounds(6, 30, 474, 540);
		textArea_2.setEditable(false);
		JScrollPane tabbedPane_2 = new JScrollPane(textArea_2,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		tabbedPane.addTab("Statistic Report", null, tabbedPane_2, null);
		
	}
}
