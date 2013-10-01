package com.example.thiswayup.jellybean;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.thiswayup.R;

public class LaunchBrowserTaskActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch_browser);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Button b = (Button) findViewById(R.id.launch_browser);
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				launchBrowserTask();
			}
		});
	}

	private void launchBrowserTask() {
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://dl.dropboxusercontent.com/u/3982805/thiswayup.html"))
			.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
		startActivity(intent);
	}
	
}
