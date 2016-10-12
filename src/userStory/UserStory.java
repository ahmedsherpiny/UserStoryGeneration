package userStory;

import java.util.ArrayList;

public class UserStory {
	private ArrayList<String> sentences;
	public UserStory()
	{
		setSentences(new ArrayList<String>());
	}
	public ArrayList<String> getSentences() {
		return sentences;
	}
	public void setSentences(ArrayList<String> sentences) {
		this.sentences = sentences;
	}

	public void addSentence(String newSentence)
	{
		sentences.add(newSentence);
	}
}
