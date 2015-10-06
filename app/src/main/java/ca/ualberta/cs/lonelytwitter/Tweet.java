package ca.ualberta.cs.lonelytwitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by joshua2 on 9/16/15.
 */
public abstract class Tweet extends Object implements Tweetable, MyObservable {
    private String text;
    protected Date date;

    public Tweet(String tweet, Date date) throws TweetTooLongException {
        this.setText(tweet);
        this.date = date;
    }

    public Tweet(String tweet) throws TweetTooLongException {
        this.setText(tweet);
        this.date = new Date();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) throws TweetTooLongException {
        if (text.length() <= 140) {
            this.text = text;
        } else {
            throw new TweetTooLongException();
        }
        this.notifyAllObservers();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
	    this.date = date;
	    this.notifyAllObservers();
    }

    public abstract Boolean isImportant();

    @Override
    public String toString() {
        return date.toString() + " | " + text;
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
}
