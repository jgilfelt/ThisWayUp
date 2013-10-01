package com.example.thiswayup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.ShareCompat;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ExternalActivitiesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_external);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Button b = (Button) findViewById(R.id.launch_pick);
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Create an intent that asks the user to pick a photo, but using
		        // FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET, ensures that relaunching
		        // the application from the device home screen does not return
		        // to the external activity.
		        Intent externalActivityIntent = new Intent(Intent.ACTION_PICK);
		        externalActivityIntent.setType("image/*");
		        externalActivityIntent.addFlags(
		                Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
		        startActivity(externalActivityIntent);
			}
		});
		
		b = (Button) findViewById(R.id.launch_share);
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ShareCompat.IntentBuilder.from(ExternalActivitiesActivity.this)
	        		.setType("text/plain")
	        		.setText("I'm sharing!")
	        		.startChooser();
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
