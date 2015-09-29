package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

/**
 * Created by dskrundz on 9/29/15.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 {
	public TweetListTest() {
		super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
	}

	public void testHoldStuf() {
		TweetList list = new TweetList();
		Tweet tweet = new NormalTweet("test");
		list.addTweet(tweet);
		assertSame(list.getCount(), 1);
		list.addTweet(tweet);assertSame(list.getCount(), 2);
	}
}