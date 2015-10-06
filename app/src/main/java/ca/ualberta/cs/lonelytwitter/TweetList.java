package ca.ualberta.cs.lonelytwitter;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by joshua2 on 9/29/15.
 */
public class TweetList implements MyObservable, MyObserver {
    private Tweet mostRecentTweet;
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();

    public void add(Tweet tweet) {
        mostRecentTweet = tweet;
	    tweet.addObserver(this);
        tweets.add(tweet);
	    this.notifyAllObservers();
    }

    public Tweet getMostRecentTweet() {
        return mostRecentTweet;
    }

    public int count() {
        return tweets.size();
    }

    private volatile ArrayList<MyObserver> observers = new ArrayList<MyObserver>();

    public void addObserver(MyObserver observer) {
		this.observers.add(observer);
    }

    private void notifyAllObservers() {
        for (MyObserver observer : this.observers) {
            observer.myNotify(this);
        }
    }

	public void myNotify(MyObservable observable) {
		this.notifyAllObservers();
	}
}
