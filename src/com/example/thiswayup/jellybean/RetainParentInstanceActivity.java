package com.example.thiswayup.jellybean;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.readystatesoftware.example.thiswayup.R;

public class RetainParentInstanceActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_retain_parent_instance);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	public Intent getParentActivityIntent() {
		Intent parent = super.getParentActivityIntent();
		parent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		return parent;
	}
	
}
