package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import junit.framework.TestCase;

import java.util.List;

/**
 * Created by wz on 14/09/15.
 */
public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2 {

	private EditText bodyText;
	private Button saveButton;
	private Button saveEditButton;
	private EditText editTweetText;

	public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    public void testEditTweet() {
	    // start lonelyTwiter
	    LonelyTwitterActivity activity = (LonelyTwitterActivity)this.getActivity();

	    activity.getTweets().clear();

	    bodyText = activity.getBodyText();

	    activity.runOnUiThread(new Runnable() {
		    public void run() {
			    bodyText.setText("1234567890");
		    }
	    });
	    getInstrumentation().waitForIdleSync();

	    saveButton = activity.getSaveButton();
	    activity.runOnUiThread(new Runnable() {
		    public void run() {
			    saveButton.performClick();
		    }
	    });
	    getInstrumentation().waitForIdleSync();

	    final ListView oldTweetsList = activity.getOldTweetsList();
	    Tweet tweet = (Tweet)oldTweetsList.getItemAtPosition(0);
	    assertEquals(tweet.getText(), "1234567890");

	    Instrumentation.ActivityMonitor receiverActivityMonitor = getInstrumentation().addMonitor(EditTweetActivity.class.getName(), null, false);
	    activity.runOnUiThread(new Runnable() {
		    public void run() {
			    View v = oldTweetsList.getChildAt(0);
			    oldTweetsList.performItemClick(v, 0, v.getId());
		    }
	    });
	    getInstrumentation().waitForIdleSync();


	    EditTweetActivity receiverActivity = (EditTweetActivity) receiverActivityMonitor.waitForActivityWithTimeout(1000);
 	    assertNotNull(receiverActivity);
	    assertEquals(1, receiverActivityMonitor.getHits());
	    assertEquals(EditTweetActivity.class, receiverActivity.getClass());
	    getInstrumentation().removeMonitor(receiverActivityMonitor);

	    editTweetText = receiverActivity.getEditTweetText();
	    assertEquals(editTweetText.getText().toString(), "1234567890");

	    saveEditButton = receiverActivity.getSaveButton();
	    receiverActivity.runOnUiThread(new Runnable() {
		    public void run() {
			    editTweetText.setText("0987654321");
			    saveEditButton.performClick();
		    }
	    });
	    getInstrumentation().waitForIdleSync();

	    final ListView oldTweetsList2 = activity.getOldTweetsList();
	    Tweet tweet2 = (Tweet)oldTweetsList2.getItemAtPosition(0);
	    assertEquals(tweet2.getText(), "0987654321");

	    activity.getTweets().clear();
    }
}