package adressTest;

import java.awt.EventQueue;
import java.awt.ScrollPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class AdressInputTest {

	private JFrame frmAdressInputTest;
	private JTextField inputField;
	private JScrollPane scrollPane;
	private JTable adressTable;
	private JButton inputButtom;
	private DefaultTableModel tableModel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdressInputTest window = new AdressInputTest();
					window.frmAdressInputTest.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// Window Builder Stuff
	
	public AdressInputTest() {
		initialize();
	}
	
	private void initialize() {
		frmAdressInputTest = new JFrame();
		frmAdressInputTest.setTitle("Adress Input Test");
		frmAdressInputTest.setBounds(100, 100, 600, 400);
		frmAdressInputTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdressInputTest.getContentPane().setLayout(null);
		
		inputField = new JTextField();
		inputField.setBounds(10, 296, 214, 20);
		frmAdressInputTest.getContentPane().add(inputField);
		inputField.setColumns(10);
		
		inputButtom = new JButton("Add");
		inputButtom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputToTable();
			}
		});
		inputButtom.setBounds(10, 327, 214, 23);
		frmAdressInputTest.getContentPane().add(inputButtom);		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(150, 11, 424, 261);
		frmAdressInputTest.getContentPane().add(scrollPane);
		
		tableModel = new DefaultTableModel();
        tableModel.addColumn("Address");
        tableModel.addColumn("Numbers");
		
		adressTable = new JTable(tableModel);
		scrollPane.setViewportView(adressTable);
	}
	
	//
	
	private void inputToTable() {
		
		String input = inputField.getText();
		if(!inputField.getText().isEmpty()) {
			
			tableModel.addRow(separateAdressData(input));
			
			inputField.setText("");
		}
	}
	
	// Main adress regex funcion
	
	public static Object[] separateAdressData(String input) {

		String streetFound = new String();
		String numberFound = new String();

		// Regex for adress:(Verify boundary or ",")((Ignore "No" and "b")(Look for N
		// size word | N size word after space after another word | Look for digits
		// preceding "No"))(Verify boundary or
		// ",")

		Pattern adressPattern = Pattern
				.compile("(?<=\\b|,)((?!No|b)[\\p{L}{2,}]+|(?<=\\p{L})\\s[\\p{L}{2,}]+|\\s\\d+(?=\\s+No))(?:\\b|,)");

		// Regex for numb er:(Ignore numbers before "No")(Verify boundary or ",")(Look
		// for: number before space before word | "No" | Number+letter sequence )(Verify
		// boundary or ",");

		Pattern numberPattern = Pattern.compile("(?!(?:.*\\bNo\\b.*\\d))(?:\\b|,)(\\d+\\s\\w|No\\s|no\\\\s|\\d+\\w*)(?:\\b|,)");

		Matcher streetMatcher = adressPattern.matcher(input);
		while (streetMatcher.find()) {
			streetFound += streetMatcher.group();
		}

		Matcher numberMatcher = numberPattern.matcher(input);
		while (numberMatcher.find()) {
			numberFound += numberMatcher.group();
		}
		
		return new Object[] {streetFound, numberFound};

	}
}
