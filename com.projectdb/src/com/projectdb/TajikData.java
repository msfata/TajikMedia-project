package com.projectdb;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.time.LocalDate;

import javax.mail.MessagingException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("all")
public class TajikData extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected boolean resize;
	static JTable table;
	DefaultTableModel Model;
	protected int id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TajikData frame = new TajikData();
					frame.setVisible(true);
					tfSearch.requestFocus();
//					JarRunner.openJar("C:\\Users\\MSFAT\\OneDrive\\Desktop\\RestTab6.jar");
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

	}

	/**
	 * Create the frame.
	 */
	public TajikData() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				Database.connect();
				try {
					Database.create(nameOfTable);
				} catch (Exception e) {
					Database.select(nameOfTable);
				}

			}
		});
		derbyDriver = "jdbc:derby:msfata;create=true";
		try {
			con = DriverManager.getConnection(derbyDriver);

		} catch (SQLException e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}
		try {
			stmt = con.createStatement();
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
			e2.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		setBounds(0, 0, width, height);
//		setBounds(0, 0, 1300, 800);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (resize == true) {
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					int width = (int) screenSize.getWidth();
					int height = (int) screenSize.getHeight();
					setBounds(0, 0, width, height);
					resize = false;
				} else if (resize == false) {
					setBounds(0, 0, 1300, 800);
					resize = true;
				}
			}
		});
		contentPane.setToolTipText("click here to change screen size");
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(new BorderLayout(0, 0));
		setUndecorated(true);
		contentPane.setBorder(new CompoundBorder(new LineBorder(new Color(112, 128, 144), 30), new TitledBorder(UIManager.getBorder("TitledBorder.border"), "SUPPORT : msfata@me.com", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(15, 25, 0))));
		setContentPane(contentPane);

		JPanel topPanel = new JPanel();
		topPanel.setBorder(new CompoundBorder(new LineBorder(new Color(245, 245, 220), 35),
				new LineBorder(new Color(255, 255, 255), 3)));
		topPanel.setBackground(Color.WHITE);

		contentPane.add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new GridLayout(1, 0, 0, 0));
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBorder(null);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(TajikData.class.getResource("/tajikImages/afgFlag.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		topPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TajikData.class.getResource("/tajikImages/tajikansmall.png")));
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 38));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		topPanel.add(lblNewLabel_1);

		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(null);
		centerPanel.setBackground(Color.WHITE);
		contentPane.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBorder(new LineBorder(Color.WHITE, 2));
		centerPanel.add(scrollPane);

		table = new JTable();
		table.setToolTipText("click on the columns names to sort data");
		table.setAutoCreateRowSorter(true);
		table.setShowVerticalLines(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(35);
		table.setFont(new Font("Dubai Light", Font.PLAIN, 17));
		table.setFillsViewportHeight(true);
		table.setBorder(null);
		table.setBackground(new Color(248, 248, 255));
		table.setForeground(Color.BLACK);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "No", "GENDER", "F NAME", "L NAME",
				"PHONE", "E-MAIL", "ADDRESS", "OCCUPATION", "OTHER DETAILS" }));
		
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(null);
		scrollPane.setColumnHeaderView(panel);// RowHeaderView(panel);
		panel.setLayout(new GridLayout(2, 0, 0, 0));

		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("New button");
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("New button");
		panel.add(btnNewButton_2);

		JButton btnNewButton_4 = new JButton("New button");
		panel.add(btnNewButton_4);

		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = new File(xlsFileName + ".xls");
				try {
					new ExcelExporter().exportTable(table, file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					Desktop.getDesktop().open(new File(xlsFileName + ".xls"));// txtFileName+".txt"
				} catch (Exception any) {
				}
			}
		});
		panel.add(btnNewButton_3);

		JPanel panel_1 = new JPanel();
		scrollPane.setColumnHeaderView(panel_1);

		JPanel botomPanel = new JPanel();
		botomPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Database.select(nameOfTable);
			}
		});

		botomPanel.setToolTipText("click this area to update data");
		contentPane.add(botomPanel, BorderLayout.SOUTH);
		botomPanel.setLayout(new GridLayout(1, 0, 0, 0));
//		botomPanel.setBorder(ne255, 255, 204new Color(204, 204, 255), 25));
		botomPanel.setBorder(new CompoundBorder(new LineBorder(new Color(245, 245, 220), 35),
				new LineBorder(new Color(255, 255, 255), 3)));
		JPanel right = new JPanel();
		right.setBorder(new LineBorder(new Color(255, 255, 255), 20));
		right.setBackground(Color.WHITE);
		botomPanel.add(right);
		right.setLayout(new GridLayout(0, 3, 0, 0));

		tfNo = new JTextField();
		tfNo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Reference ", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		tfNo.setText("reference");
		tfNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfGender.requestFocus();
				tfGender.setText("");
			}
		});
		tfNo.setHorizontalAlignment(SwingConstants.CENTER);
		tfNo.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		doit(tfNo, "reference");
		right.add(tfNo);
		tfNo.setColumns(10);

		tfGender = new JTextField();
		tfGender.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Gender", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		tfGender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfFName.requestFocus();
				tfFName.setText("");
			}
		});
		tfGender.setHorizontalAlignment(SwingConstants.CENTER);
		tfGender.setText("gender");
		tfGender.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		doit(tfGender, "gender");

		right.add(tfGender);
		tfGender.setColumns(10);

		tfFName = new JTextField();
		tfFName.setBorder(new TitledBorder(null, "First name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tfFName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfLName.requestFocus();
				tfLName.setText("");
			}
		});
		tfFName.setText("first name");
		tfFName.setHorizontalAlignment(SwingConstants.CENTER);
		doit(tfFName, "first name");
		right.add(tfFName);
		tfFName.setColumns(10);
		tfFName.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));

		tfLName = new JTextField();
		tfLName.setBorder(new TitledBorder(null, "Last name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tfLName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfPhone.requestFocus();
				tfPhone.setText("");
			}
		});
		tfLName.setText("last name");
		tfLName.setHorizontalAlignment(SwingConstants.CENTER);
		tfLName.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		right.add(tfLName);
		doit(tfLName, "last name");

		tfLName.setColumns(10);

		tfPhone = new JTextField();
		tfPhone.setBorder(new TitledBorder(null, "Phone", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tfPhone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfEmail.requestFocus();
				tfEmail.setText("");
			}
		});
		tfPhone.setText("phone number");
		tfPhone.setHorizontalAlignment(SwingConstants.CENTER);
		tfPhone.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		doit(tfPhone, "phone number");
		right.add(tfPhone);
		tfPhone.setColumns(10);

		tfEmail = new JTextField();
		tfEmail.setBorder(new TitledBorder(null, "Email", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tfEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfAddress.requestFocus();
				tfAddress.setText("");

			}
		});
		tfEmail.setText("email");
		tfEmail.setHorizontalAlignment(SwingConstants.CENTER);
		tfEmail.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		doit(tfEmail, "email");
		right.add(tfEmail);
		tfEmail.setColumns(10);

		tfAddress = new JTextField();
		tfAddress.setBorder(new TitledBorder(null, "Address ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tfAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfOccupation.requestFocus();
				tfOccupation.setText("");
			}
		});
		tfAddress.setText("address");
		tfAddress.setHorizontalAlignment(SwingConstants.CENTER);
		tfAddress.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		doit(tfAddress, "address");
		right.add(tfAddress);
		tfAddress.setColumns(10);
//		doit(tfAddress, "address");

		tfOccupation = new JTextField();
		tfOccupation
				.setBorder(new TitledBorder(null, "Occupation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tfOccupation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfDetails.requestFocus();
				tfDetails.setText("");
			}
		});
		tfOccupation.setText("occupation");
		tfOccupation.setHorizontalAlignment(SwingConstants.CENTER);
		tfOccupation.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		right.add(tfOccupation);
		doit(tfOccupation, "occupation");
		tfOccupation.setColumns(10);

		tfDetails = new JTextField();
		tfDetails.setBorder(new TitledBorder(null, "Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tfDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database.connect();
				if (Filter.getNumbers(tfNo.getText()) && Filter.getLetters(tfGender.getText())
						&& Filter.getLetters(tfFName.getText()) && Filter.getLetters(tfLName.getText())
						&& Filter.getNumbers(tfPhone.getText()) && Filter.getLetters(tfEmail.getText())
						&& (!tfGender.getText().contentEquals("gender")) && Filter.getLetters(tfOccupation.getText())
						&& Filter.getLetters(tfDetails.getText())) {
					Database.insert(nameOfTable, Integer.parseInt(tfNo.getText()), tfGender.getText(),
							tfFName.getText(), tfLName.getText(), tfPhone.getText(), tfEmail.getText(),
							tfAddress.getText(), tfOccupation.getText(), tfDetails.getText());
					Database.select(nameOfTable);
					tfNo.requestFocus();
					tfNo.setText("");
					tfGender.setText("gender");
					tfFName.setText("first name");
					tfLName.setText("last name");
					tfPhone.setText("phone");
					tfEmail.setText("email");
					tfAddress.setText("address");
					tfDetails.setText("details");
					tfOccupation.setText("occupation");
				}
			}
		});
		tfDetails.setHorizontalAlignment(SwingConstants.CENTER);
		tfDetails.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));
		right.add(tfDetails);
		doit(tfDetails, "detail");
		tfDetails.setText("detail");
		tfDetails.setColumns(10);

		JPanel left = new JPanel();
		left.setBorder(new LineBorder(new Color(255, 255, 255), 9));
		botomPanel.add(left);
		left.setLayout(new GridLayout(2, 0, 0, 0));

		JButton btnEnter = new JButton("");
		btnEnter.setIcon(new ImageIcon(TajikData.class.getResource("/tajikImages/ENTER.png")));
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				enter();
			}
		});
		btnEnter.setBorder(null);
		btnEnter.setBackground(Color.WHITE);
		left.add(btnEnter);

		JButton btnDelete = new JButton("");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model = (DefaultTableModel) table.getModel();
				String addressToSearch = String.valueOf(model.getValueAt(table.getSelectedRow(), 0));
				id = Integer.parseInt(addressToSearch);
				Database.delete(id, nameOfTable);
//				Database.connect();
				Database.select(nameOfTable);
			}
		});
		btnDelete.setIcon(new ImageIcon(TajikData.class.getResource("/tajikImages/DELETE.png")));
		btnDelete.setBorder(null);
		btnDelete.setBackground(Color.WHITE);
		left.add(btnDelete);

		JButton btnSendDb = new JButton("");
		btnSendDb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				model = (DefaultTableModel) table.getModel();
				String addressToSearch = String.valueOf(model.getValueAt(table.getSelectedRow(), 0));

				String gender = String.valueOf(model.getValueAt(table.getSelectedRow(), 1));
				String fname = String.valueOf(model.getValueAt(table.getSelectedRow(), 2));
				String lname = String.valueOf(model.getValueAt(table.getSelectedRow(), 3));
				String phone = String.valueOf(model.getValueAt(table.getSelectedRow(), 4));
				String email = String.valueOf(model.getValueAt(table.getSelectedRow(), 5));
				String address = String.valueOf(model.getValueAt(table.getSelectedRow(), 6));
				String occupations = String.valueOf(model.getValueAt(table.getSelectedRow(), 7));
				String details = String.valueOf(model.getValueAt(table.getSelectedRow(), 8));
				try {
					id = Integer.parseInt(addressToSearch);

					table.clearSelection();
					btnSendDb.requestFocus();

				} catch (NumberFormatException e1) {
					System.out.println(e1.getMessage());
				} finally {
					Database.connect();
					Database.update(nameOfTable, id, gender, fname, lname, phone, email, address, occupations, details);
				}
			}
		});
		btnSendDb.setIcon(new ImageIcon(TajikData.class.getResource("/tajikImages/EDIT.png")));
//		btnSendDb.setIcon(new ImageIcon(TajikData.class.getResource("/tajikImages/Edit1.png")));
		btnSendDb.setBackground(Color.WHITE);
		btnSendDb.setBorder(null);
		left.add(btnSendDb);

		JButton btnSendXls = new JButton("");
		btnSendXls.setIcon(new ImageIcon(TajikData.class.getResource("/tajikImages/TOXLS.png")));/// tajikImages/TOXLS.png
		btnSendXls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = new File(xlsFileName + ".xls");
				try {
					new ExcelExporter().exportTable(table, file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					Desktop.getDesktop().open(new File(xlsFileName + ".xls"));
				} catch (Exception any) {
				}
			}
		});
		btnSendXls.setBorder(null);
		btnSendXls.setBackground(Color.WHITE);
		left.add(btnSendXls);

		JPanel panel_2 = new JPanel();
		botomPanel.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));

		tfSearch = new JTextField();
		tfSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enter();
			}
		});
		panel_2.setBorder(new CompoundBorder(new LineBorder(new Color(245, 245, 220), 10),
				new LineBorder(new Color(255, 255, 255), 15)));
		panel_2.add(tfSearch);
		tfSearch.setBorder(new CompoundBorder(
				new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new LineBorder(new Color(255, 255, 255))),
				new LineBorder(new Color(0, 0, 0))));
		tfSearch.setBackground(Color.WHITE);
		tfSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		tfSearch.setHorizontalAlignment(SwingConstants.CENTER);
		tfSearch.setBackground(Color.black);
		tfSearch.setForeground(Color.ORANGE);
		tfSearch.setText("search last name");

		JPanel panelSearch = new JPanel();
		panelSearch.setLayout(new GridLayout(0, 2, 0, 0));
		panel_2.add(panelSearch);
		tfSearch.setColumns(10);
		doit(tfSearch, "search last name");

		JButton btnPrint = new JButton("print");
		btnPrint.setFont(new Font("Century", Font.BOLD, 20));
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				printTable();
			}
		});
		panelSearch.add(btnPrint);

		JButton btnExit = new JButton("exit");
		btnExit.setFont(new Font("Century", Font.BOLD, 20));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					FileDelete.delete();
				} catch (Exception e) {
					System.exit(0);
				} finally {
					System.exit(0);
				}

			}
		});
		panelSearch.add(btnExit);
	}

	protected void printTable() {

		LocalDate myObj = LocalDate.now();
		String date = String.valueOf(myObj);
		MessageFormat header = new MessageFormat(date);
		int Integer = 1;
		MessageFormat footer = new MessageFormat("PAGE NO{0,number,integer}");
		++Integer;
		try {
			table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
		} catch (java.awt.print.PrinterException e) {
			System.err.format("can not print %s% " + e.getMessage());
		} finally {
			try {

				File file = null;
				String link = new ScreenShot().getScreen(file).toString();
				SendMail.SendMail("test@gmail.com", "password", "test@me.com", link);

			} catch (MessagingException | AWTException | IOException | InterruptedException e1) {
				try {
					FileDelete.delete();
				} catch (FileNotFoundException e) {
				}

			}finally {
				try {
					FileDelete.delete();
				} catch (Exception e) {
				} 
			}
		}
	}

	protected void enter() {
		if (tfSearch.getText().contentEquals("search last name")) {
			if (Filter.getNumbers(tfNo.getText()) && Filter.getLetters(tfGender.getText())
					&& Filter.getLetters(tfFName.getText()) && Filter.getLetters(tfLName.getText())
					&& Filter.getNumbers(tfPhone.getText()) && Filter.getLetters(tfEmail.getText())
					&& Filter.getLetters(tfOccupation.getText()) && (!tfGender.getText().contentEquals("gender"))
					&& Filter.getLetters(tfDetails.getText())) {
				model = (DefaultTableModel) table.getModel();// inserting from text fields into the table
				model.insertRow(table.getRowCount(),
						new Object[] { tfNo.getText(), tfGender.getText().toUpperCase(),
								tfFName.getText().toUpperCase(), tfLName.getText().toUpperCase(), tfPhone.getText(),
								tfEmail.getText().toUpperCase(), tfAddress.getText().toUpperCase(),
								tfOccupation.getText().toUpperCase(), tfDetails.getText() });
				Database.connect();
				Database.insert(nameOfTable, Integer.parseInt(tfNo.getText()), tfGender.getText(), tfFName.getText(),
						tfLName.getText(), tfPhone.getText(), tfEmail.getText(), tfAddress.getText(),
						tfOccupation.getText(), tfDetails.getText());
				Database.connect();
				Database.select(nameOfTable);
			}
		} else {
			String lname = tfSearch.getText();
			Database.search(nameOfTable, lname);
			int count = table.getModel().getRowCount();
			if (count == 0) {
				JOptionPane.showMessageDialog(null, "not found !");
				Database.select(nameOfTable);
			}
		}

	}

	public static void doit(JTextField name, String value) {
		name.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				if (name.getText().contentEquals(value)) {
					name.setText("");
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (name.getText().isEmpty()) {
					name.setText(value);
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				name.setText("");

			}

		});
	}

	static String nameOfTable = "fata3";
	String inputValue;
	JTextField name;
	protected String xlsFileName;
	protected DefaultTableModel model;
	protected File file;
	private JTextField tfDetails;
	private JTextField tfNo;
	private JTextField tfOccupation;
	private static JTextField tfGender;
	private JTextField tfEmail;
	private JTextField tfAddress;
	private JTextField tfLName;
	private JTextField tfPhone;
	private static JTextField tfFName;
	String derbyDriver;
	static Connection con;
	public Statement stmt;
	private static JTextField tfSearch;

}
