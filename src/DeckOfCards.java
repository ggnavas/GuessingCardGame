import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DeckOfCards {
	public static Card[] deck;
	// private int currentCard;

	public DeckOfCards() throws IOException {

		deck = new Card[52];

		int deckNum = 0;
		int deckImage = 1;

		for (int i = 0; i < 4; i++) {

			for (int j = 1; j < 14; j++) {

				deck[deckNum] = new Card(j, i, ImageIO.read(new File("images/" + deckImage + ".gif")));
				deckNum++;
				deckImage++;
			}
		}
	}

	public void shuffle() {

		int deckLength = deck.length - 1;

		for (int i = 0; i < deckLength; i++) {
			int r = i + (int) (Math.random() * (deckLength - i));
			Card temp = deck[r];
			deck[r] = deck[i];
			deck[i] = temp;
		}
	}

	public int dealTopCard() {
		int topCard = -1;

		if (deck.length > 0) {
			topCard++;
			return deck[topCard].getRank();

		} else {
			return 0;
		}
	}
}
