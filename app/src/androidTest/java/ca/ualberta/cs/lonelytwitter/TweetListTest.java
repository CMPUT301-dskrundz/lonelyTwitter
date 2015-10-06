package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

/**
 * Created by joshua2 on 9/29/15.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 implements MyObserver {
    public TweetListTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testHoldsStuff() {
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.add(tweet);
        assertSame(list.getMostRecentTweet(), tweet);
    }

    public void testHoldsManyThings() {
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.add(tweet);
        assertEquals(list.count(), 1);
        list.add(new NormalTweet("test"));
        assertEquals(list.count(), 2);
    }

    private boolean weWereNotified = false;

    public void myNotify(MyObservable observable) {
        weWereNotified = true;
    }

    public void testObservable() {
        TweetList list = new TweetList();
        list.addObserver(this);
        Tweet tweet = new NormalTweet("test");
        weWereNotified = false;
        list.add(tweet); // Should get nofified only after this.
        assertTrue(weWereNotified);
    }

	public void testModifyTweetInList() {
		TweetList list = new TweetList();
		list.addObserver(this);
		Tweet tweet = new NormalTweet("tets");
		list.add(tweet);
		weWereNotified = false;
		tweet.setText("test");
		assertTrue(weWereNotified);
	}
}