package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditTweetActivity extends Activity {

	private EditText bodyText;
	private Button saveButton;
	private Integer number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_tweet);

		bodyText = (EditText) findViewById(R.id.editText);
		saveButton = (Button) findViewById(R.id.button);

		Intent intent = getIntent();
		String message = intent.getStringExtra("TWEET-TEXT");
		number = intent.getIntExtra("Edit#", 0);
		bodyText.setText(message);

		saveButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//http://stackoverflow.com/questions/14292398/how-to-pass-data-from-2nd-activity-to-1st-activity-when-pressed-back-android
				Intent intent = new Intent();
				intent.putExtra("finalValue", bodyText.getText().toString());
				intent.putExtra("edit#", number);
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_edit_tweet, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}

	public EditText getEditTweetText() {
		return this.bodyText;
	}

	public Button getSaveButton() {
		return this.saveButton;
	}
}
