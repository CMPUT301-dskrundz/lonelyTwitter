package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by dskrundz on 9/29/15.
 */
public class TweetList {
	private List<Tweet> list = new ArrayList<Tweet>();

	public void addTweet(Tweet tweet) throws IllegalArgumentException {
		for (Tweet t : list) {
			if (t.equals(tweet)) {
				throw new IllegalArgumentException();
			}
		}
		list.add(tweet);
	}

	public List<Tweet> getTweets() {
		Collections.sort(list, new Comparator<Tweet>() {
			public int compare(Tweet lhs, Tweet rhs) {
				return lhs.date.compareTo(rhs.date);
			}
		});
		return list;
	}

	public boolean hasTweet(Tweet tweet) {
		for (Tweet t : list) {
			if (t.equals(tweet)) {
				return true;
			}
		}
		return false;
	}

	public void removeTweet(Tweet tweet) {
		list.remove(tweet);
	}

	public int getCount() {
		return list.size();
	}
}
