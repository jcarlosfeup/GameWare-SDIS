package gameware.info;

public class Review 
{	
	private String deck;
	private String description;
	private String date;
	private int score;
	private String review;
	
	public Review(String deck, String desc, String d, int s, String r)
	{
		this.setDeck(deck);
		setDescription(desc);
		setDate(d);
		setScore(s);
		setReview(r);
	}

	public String getDeck() {
		return deck;
	}

	public void setDeck(String deck) {
		this.deck = deck;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
	
}
