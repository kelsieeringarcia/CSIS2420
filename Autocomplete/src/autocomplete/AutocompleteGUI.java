package autocomplete;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class AutocompleteGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPanel suggestionPanel;
	private StringBuilder word = new StringBuilder();

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
		AutoComplete ac = new AutoComplete();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(SystemColor.textHighlight);
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		
		JLabel titleLabel = new JLabel("Autocomplete Search");
		titleLabel.setForeground(new Color(255, 127, 80));
		titleLabel.setFont(new Font("Symbol", Font.BOLD, 20));
		titleLabel.setBounds(93, 24, 228, 33);
		contentPane.add(titleLabel);
		
		suggestionPanel = new JPanel();
		suggestionPanel.setBounds(35, 92, 290, 175);
		contentPane.add(suggestionPanel);
		suggestionPanel.setLayout(null);
		suggestionPanel.setVisible(false);
		
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void keyPressed(KeyEvent e) {
				String search = e.toString();
				if(!search.equals("") ) {
					if(e.getKeyText(e.getKeyCode()).equals("⌫")
							|| e.getKeyText(e.getKeyCode()).equals("⇧")
							|| e.getKeyText(e.getKeyCode()).equals("␣")
							|| e.getKeyText(e.getKeyCode()).equals("←")
							|| e.getKeyText(e.getKeyCode()).equals("→")
							|| e.getKeyText(e.getKeyCode()).equals("↓")
							|| e.getKeyText(e.getKeyCode()).equals("↑")
							|| e.getKeyText(e.getKeyCode()).equals("⌘")) {
						if(word.length() <= 1) {
							word.setLength(0);
							suggestionPanel.setVisible(false);
						}else {
							word.deleteCharAt(word.length() - 1);
							showSearchOptions(ac);
						}

					}else {
						word.append(e.getKeyText(e.getKeyCode()));
						showSearchOptions(ac);

					}
					
				}
				
				
			}

			private void showSearchOptions(AutoComplete ac) {
				List<String> words = ac.autoCompleteFromWord(word.toString(), 10);

				@SuppressWarnings({ "unchecked", "rawtypes" })
				JList list = new JList(words.toArray());
				suggestionPanel.add(list);
				list.setBounds(6, 6, 278, 200);
				list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				list.setVisibleRowCount(10);
				suggestionPanel.setVisible(true);
				list.setVisible(true);
			}
		});
		textField.setBounds(25, 70, 300, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton searchButton = new JButton("Search");
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int results = JOptionPane.YES_NO_OPTION;
				if(JOptionPane.showConfirmDialog(null, "Do you wish to add this word?","Word not found!", results) == JOptionPane.YES_OPTION) {
					suggestionPanel.setVisible(false);
					ac.insertIntoWordList(word.toString());
					word.setLength(0);
					textField.setText("");
					ac.refreshWordFileOnDestroy();
				}else {
					textField.setText("");
					word.setLength(0);
					suggestionPanel.setVisible(false);
				}
				
				

			}
		});
		searchButton.setBackground(Color.BLUE);
		searchButton.setBounds(327, 70, 117, 29);
		contentPane.add(searchButton);
	

	}

}
