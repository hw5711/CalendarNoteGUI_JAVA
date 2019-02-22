package CalendarNoteGUI;


import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.io.*;
import java.util.GregorianCalendar;


public class MyFrame extends CalendarDate {

	Calendar calendar;
	int week;
	JFrame mainFrame;
	JLabel title;
	JButton todayBut;
	JLabel todayLab;
	JPanel leftNorthPanel;
	JButton lChangeMonth;
	JButton rChangeMonth;
	JButton lChangeYear;
	JButton rChangeYear;
	JLabel selectedMY;
	JPanel leftMidPanel;
	JLabel weekDayName[];
	JPanel leftSouthPanel;
	JButton[][] dateButs = new JButton[CALENDAR_HEIGHT][CALENDAR_WIDTH];
	String[] weekDay = new String[] { " Sun", " Mon", " Tus", " Wed", " Thu"," Fri", " Sat" };

	public JPanel rightNorthPanel;
	public JTextArea myMemo;
	public JPanel rightSouthPanel;
	public JButton okBut;
	public JButton cancelBut;

	public MyFrame() {
		setCurrentDate();
		init();
	}

	public void init() {
		JFrame mainFrame = new JFrame("my frame"); 
		mainFrame.setSize(700, 400); 
		mainFrame.setLocation(100, 100); 
		mainFrame.setLayout(null);


		JLabel title = new JLabel("MY CALENDAR"); 
		title.setBounds(300, 20, 200, 15);
		title.setFont(new Font("Arial", Font.BOLD, 16)); 
		title.setForeground(Color.darkGray); 
		mainFrame.add(title);


		JPanel rightNorthPanel = new JPanel(); 
		rightNorthPanel.setBounds(360, 50, 300, 266); 
		rightNorthPanel.setBackground(Color.white); 
		mainFrame.add(rightNorthPanel);// text memo


		final JTextArea myMemo = new JTextArea(16, 24); 
		myMemo.setBackground(Color.white); 
		myMemo.setFont(new Font("Arial", Font.PLAIN, 14)); 
		myMemo.setBounds(0, 0, 100, 100); 
		rightNorthPanel.add(myMemo);


		final JLabel selectedMY = new JLabel((month + 1 < 10 ? "&nbsp;" : "") + (month + 1) +" / " + year);
		selectedMY.setBounds(60, 0, 230, 25);
		JPanel leftSouthPanel = new JPanel(); 
		leftSouthPanel.setBounds(5, 110, 350, 240); 
		leftSouthPanel.setBackground(Color.white); 
		GridLayout calBoardLayout = new GridLayout(6, 7); 
		leftSouthPanel.setLayout(calBoardLayout);


		for (int i = 0; i < CALENDAR_HEIGHT; i++) { 
			for (int j = 0; j < CALENDAR_WIDTH; j++) {
				dateButs[i][j] = new JButton();
				dateButs[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) { 
						int k = 0;
						int l = 0;
						for (int i = 0; i < CALENDAR_HEIGHT; i++) {
							for (int j = 0; j < CALENDAR_WIDTH; j++) {
								if (e.getSource() == dateButs[i][j]) {
									k = i;
									l = j; 
								}
							}
						}

						if ((k != 0) || (l != 0)) {
							MyFrame.this.day = MyFrame.this.calDates[k][l];
						}
						MyFrame.this.cal = new GregorianCalendar(MyFrame.this.year, MyFrame.this.day); 
						selectedMY.setText((MyFrame.this.month +"/"+ MyFrame.this.day + "/" + MyFrame.this.year);
						String fileName = (MyFrame.this.month + MyFrame.this.day + "-" + ".txt");
						try {
							File file = new File(fileName); 
							BufferedReader buf = new BufferedReader(new FileReader(file)); 
							if (file.exists()) {
								myMemo.setText("");
								String line = null; 
								line = buf.readLine(); 
								while (line != null) {
									myMemo.append(line + "\r\n");
									line = buf.readLine();
								}
							} else { file.createNewFile();
							}
							buf.close();
						} catch (IOException eee) {
						} 
					}
				});

			dateButs[i][j].setFont(new Font("Arial", Font.PLAIN, 12));
			dateButs[i][j].setForeground(Color.gray); 
			dateButs[i][j].setVisible(true); 
			leftSouthPanel.add(dateButs[i][j]);
			} 
		}


		final JLabel todayLab = new JLabel((this.today.get(2) + 1) + "/"+ this.today.get(5) + "/" + this.today.get(1)); 
		todayLab.setBounds(65, 355, 100, 20); 
		todayLab.setFont(new Font("Arial", Font.PLAIN, 14));


		JButton todayBut = new JButton("today"); 
		todayBut.setBounds(5, 355, 50, 20); 
		todayBut.setFont(new Font("Arial", Font.PLAIN, 14));


		mainFrame.add(todayBut);
		mainFrame.add(todayLab);


		JPanel leftNorthPanel = new JPanel(); 
		leftNorthPanel.setBounds(5, 50, 350, 30); 
		leftNorthPanel.setLayout(null); 
		leftNorthPanel.setBackground(Color.white); 
		mainFrame.add(leftNorthPanel);// calendar select


		JButton lChangeMonth = new JButton("<"); 
		lChangeMonth.setBounds(30, 0, 25, 25); 
		lChangeMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2) { 
				for (int i = 0; i < CALENDAR_HEIGHT; i++) {
					for (int j = 0; j < CALENDAR_WIDTH; j++) { 
						dateButs[i][j].setText("");
					} 
				}
				MyFrame.this.moveMonth(-1);
				selectedMY.setText(" " + (MyFrame.this.month + 1)+ MyFrame.this.year + " "); 
				MyFrame.this.showCal();
			}
		});


		JButton rChangeMonth = new JButton(">"); 
		rChangeMonth.setBounds(295, 0, 25, 25); 
		rChangeMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e3) { 
				for (int i = 0; i < CALENDAR_HEIGHT; i++) {
					for (int j = 0; j < CALENDAR_WIDTH; j++) { 
						dateButs[i][j].setText("");
					}
				} 
			}
		});

			MyFrame.this.moveMonth(1);
			selectedMY.setText(" " + (MyFrame.this.month + 1) + MyFrame.this.year + " "); 
			MyFrame.this.showCal();

			JButton lChangeYear = new JButton("<<"); 
			lChangeYear.setBounds(0, 0, 25, 25); 
			lChangeYear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e4) { 
					for (int i = 0; i < CALENDAR_HEIGHT; i++) {

						for (int j = 0; j < CALENDAR_WIDTH; j++) { 
							dateButs[i][j].setText("");

						} 
					}
					MyFrame.this.moveMonth(-12); 
					selectedMY.setText(" " + (MyFrame.this.month + 1)+ MyFrame.this.year + " "); 
					MyFrame.this.showCal();
				}
			});

			JButton rChangeYear = new JButton(">>"); 
			rChangeYear.setBounds(325, 0, 25, 25); 
			rChangeYear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e4) { 
					for (int i = 0; i < CALENDAR_HEIGHT; i++) {

						for (int j = 0; j < CALENDAR_WIDTH; j++) { 
							dateButs[i][j].setText("");

						} 
					}
					MyFrame.this.moveMonth(12);
					selectedMY.setText(" " + (MyFrame.this.month + 1)+"/" +MyFrame.this.year + " "); 
					MyFrame.this.showCal();
				}
			});



			leftNorthPanel.add(lChangeYear);
			leftNorthPanel.add(lChangeMonth);
			leftNorthPanel.add(selectedMY);
			leftNorthPanel.add(rChangeMonth);
			leftNorthPanel.add(rChangeYear);


			JPanel leftMidPanel = new JPanel(); 
			leftMidPanel.setBounds(5, 80, 350, 30); 
			leftMidPanel.setBackground(Color.white); 
			GridLayout weekDayLayout = new GridLayout(1, 7);
			weekDayLayout.setHgap(0); 
			leftMidPanel.setLayout(weekDayLayout);


			JLabel weekDayName[]; 
			weekDayName = new JLabel[7]; 
			for (int j = 0; j < 7; j++) {
				weekDayName[j] = new JLabel();
				weekDayName[j].setFont(new Font("Arial", Font.PLAIN,14));
				weekDayName[j].setText(weekDay[j]);
				weekDayName[j].setBorder(BorderFactory.createRaisedBevelBorder());

				leftMidPanel.add(weekDayName[j]);
			}

			JPanel rightSouthPanel = new JPanel();


			rightSouthPanel.setBounds(360, 315, 300, 35); 
			rightSouthPanel.setBackground(Color.white); 
			mainFrame.add(rightSouthPanel);// two buttons
			JButton okBut = new JButton("OK"); 
			okBut.setFont(new Font("Arial", Font.PLAIN, 12)); 
			okBut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e5) { 
					String memoContent = myMemo.getText();

					String storeFileName = (MyFrame.this.month + 1) +"-"+MyFrame.this.year + ".txt"+ MyFrame.this.day + "-" + FileWriter stroeFile = null;
					try {
						stroeFile = new FileWriter(storeFileName);
					} catch (IOException e6) {
					}
					BufferedWriter bufStore = new BufferedWriter(stroeFile); 


					JButton cancelBut = new JButton("Cancel"); 
					cancelBut.setFont(new Font("Arial", Font.PLAIN, 12)); 
					cancelBut.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e8) {
							String storeFileName = (MyFrame.this.month + 1) +"-"+ MyFrame.this.day + "-" + MyFrame.this.year + ".txt";
							FileReader readFile = null; 
							try {
								readFile = new FileReader(storeFileName);
							} catch (FileNotFoundException e9) {
							}
							BufferedReader bufStore = new
									BufferedReader(readFile);
							myMemo.setText("");
							try {
								String line = null;
								line = bufStore.readLine(); while (line != null) {
									myMemo.append(line + "\r\n");
									line = bufStore.readLine();
								}
							} catch (IOException e10) {
							} }
					});
					
					rightSouthPanel.add(okBut);
					rightSouthPanel.add(cancelBut);
					
					mainFrame.add(leftMidPanel);// weed day
					mainFrame.add(leftSouthPanel);// calendar
					
					mainFrame.addWindowListener(new WindowAdapter() { 
						public void windowClosing(WindowEvent e) {
							System.exit(0); 
							}
					});
					
					for (int i = 0; i < CALENDAR_HEIGHT; i++) { 
						for (int j = 0; j < CALENDAR_WIDTH; j++) {
							if (calDates[i][j] == 0) { }
							else {
								String temp = "" + calDates[i][j];
								dateButs[i][j].setText(temp);
							} }
					}
					mainFrame.setVisible(true);
				}


				public void showCal() {
					for (int i = 0; i < CALENDAR_HEIGHT; i++) {
						for (int j = 0; j < CALENDAR_WIDTH; j++) { 
							if (this.calDates[i][j] == 0) {
							} else {
								String temp = "" + this.calDates[i][j];
								dateButs[i][j].setText(temp);
							}
						} }
				}
				public static int format(int val) {
					if (val < 10) {
						String temp = "0" + val; 
						return Integer.parseInt(temp);
					} else
						return val;
				}

				public void focusToday() {
					if (MyFrame.this.today.get(7) == 1) {
						dateButs[this.today.get(4)][(this.today.get(7) - 1)].requestFocusInWindow();
					} else {
						dateButs[(this.today.get(4) - 1)][(this.today.get(7)- 1)].requestFocusInWindow();
					} 
				}



				public static void main(String[] args) { 
					new MyFrame();
				} 
				
		}
