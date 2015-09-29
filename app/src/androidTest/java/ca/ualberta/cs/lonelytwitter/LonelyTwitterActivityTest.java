package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

import java.util.List;

/**
 * Created by wz on 14/09/15.
 */
public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2 {

    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testAdd() {
        TweetList list = new TweetList();
        Tweet t = new NormalTweet("123345454235432");
        list.addTweet(t);
        try {
            list.addTweet(t);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    public void testGet() {
        TweetList list = new TweetList();
        list.addTweet(new NormalTweet("123"));
        list.addTweet(new NormalTweet("1234"));
        List<Tweet> l = list.getTweets();
        assertEquals(l.size(), 2);
    }

    public void testHasTweet() {
        TweetList list = new TweetList();
        Tweet t = new NormalTweet("as");
        list.addTweet(t);
        assertTrue(list.hasTweet(t));
    }

    public void testRemoveTweet() {
        TweetList list = new TweetList();
        Tweet t1 = new NormalTweet("1rf");
        list.addTweet(new NormalTweet("fdsgsdfsdfsdf"));
        list.addTweet(t1);
        list.removeTweet(t1);
        assertEquals(list.getCount(), 1);
    }

    public void testGetCount() {
        TweetList list = new TweetList();
        list.addTweet(new NormalTweet("5"));
        list.addTweet(new NormalTweet("4"));
        list.addTweet(new NormalTweet("3"));
        list.addTweet(new NormalTweet("2"));
        list.addTweet(new NormalTweet("1"));
        assertEquals(list.getCount(), 5);
    }
}