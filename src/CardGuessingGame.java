import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CardGuessingGame implements ActionListener, ItemListener {

	public DeckOfCards deck;

	Card backCard = new Card(100, 100, ImageIO.read(new File("images/b.gif")));
	JFrame window = new JFrame("Card Game!");
	JButton button = new JButton("Deal");
	JPanel contentPane = new JPanel(new BorderLayout());
	JPanel suitPanel = new JPanel(new BorderLayout());
	JPanel valuePanel = new JPanel(new BorderLayout());
	JLabel backImage = new JLabel(new ImageIcon(backCard.getCardImage()));
	JLabel cardImage;
	JLabel gameStats;
	ImageIcon imgIcon;
	JComboBox suitSelection;
	JComboBox valueSelection;

	int suit = 0;
	int value = 1;
	int nextCard = 0;
	int userCardRank = 1;
	int win = 0;
	int loss = 0;
	int rmngCards = 52;

	public CardGuessingGame() throws IOException {

		deck = new DeckOfCards();

		// shuffle deck
		deck.shuffle();

		// printing out rank and card name in the console.. for cheating (:
		System.out.println(DeckOfCards.deck[nextCard].getRank());
		System.out.println(DeckOfCards.deck[nextCard].toString());

		window.setSize(1250, 1000);
		((JFrame) window).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		button.addActionListener(this);
		button.setSize(300, 400);

		// creating panel in window
		contentPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		contentPane.setLayout(new GridLayout(2, 3, 20, 20));
		suitPanel.setLayout(new GridLayout(0, 1));
		valuePanel.setLayout(new GridLayout(0, 1));

		// creating card image
		imgIcon = new ImageIcon();
		cardImage = new JLabel(imgIcon);

		gameStats = new JLabel("<html>Welcome!<br>" + "Wins: " + win + "<br> Loss: " + loss + "<br>Cards Left: "
				+ rmngCards + "</html>");

		JLabel suitLabel = new JLabel("Pick Your Suit");
		String[] suits = { "Spades", "Hearts", "Diamonds", "Clubs" };
		suitSelection = new JComboBox(suits);

		JLabel valueLabel = new JLabel("Pick Your Value");
		String[] value = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
		valueSelection = new JComboBox(value);

		suitSelection.addItemListener(this);
		suitPanel.add(suitLabel);
		suitPanel.add(suitSelection);

		valueSelection.addItemListener(this);
		valuePanel.add(valueLabel);
		valuePanel.add(valueSelection);

		// Adding labels to panel
		contentPane.add(backImage);
		contentPane.add(gameStats);
		contentPane.add(cardImage);
		contentPane.add(valuePanel);
		contentPane.add(suitPanel);
		contentPane.add(button);
		window.add(contentPane, BorderLayout.CENTER);
		window.pack();
		window.setVisible(true);

	}

	public static void main(String[] args) throws IOException {

		new CardGuessingGame();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		imgIcon.setImage(DeckOfCards.deck[nextCard].getCardImage());
		cardImage.repaint();

		if (DeckOfCards.deck[nextCard].getRank() == userCardRank) {

			System.out.println("you got it");
			rmngCards--;
			win++;
		}

		else {
			System.out.println("wrong");
			rmngCards--;
			loss++;
		}

		gameStats.setText("<html>Welcome!<br>" + "Wins: " + win + "<br> Loss: " + loss + "<br>Cards Left: " + rmngCards
				+ "</html>");
		nextCard++;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {

		suit = suitSelection.getSelectedIndex();
		value = valueSelection.getSelectedIndex() + 1;

		userCardRank = (suit * 13) + value;
	}

}
