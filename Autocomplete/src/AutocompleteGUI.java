
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.UIManager;

public class AutocompleteGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AutocompleteGUI frame = new AutocompleteGUI();
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
	public AutocompleteGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(SystemColor.textHighlight);
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList autocompleteList = new JList();
		autocompleteList.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent event) {
			}
			public void inputMethodTextChanged(InputMethodEvent event) {
			}
		});
		autocompleteList.setBounds(35, 94, 288, 136);
		contentPane.add(autocompleteList);
		autocompleteList.setVisible(false);
		
		textField = new JTextField();
		textField.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent event) {
			}
			public void inputMethodTextChanged(InputMethodEvent event) {
			}
		});
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textField != null) autocompleteList.setVisible(true);
			}
		});
		textField.setBounds(25, 70, 300, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton searchButton = new JButton("Search");
		searchButton.setBackground(Color.BLUE);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		searchButton.setBounds(327, 70, 117, 29);
		contentPane.add(searchButton);
		
		JLabel titleLabel = new JLabel("Autocomplete Search");
		titleLabel.setForeground(new Color(255, 127, 80));
		titleLabel.setFont(new Font("Symbol", Font.BOLD, 20));
		titleLabel.setBounds(93, 24, 228, 33);
		contentPane.add(titleLabel);
	}
}
