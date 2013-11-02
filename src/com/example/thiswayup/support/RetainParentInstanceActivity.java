package com.example.thiswayup.support;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.readystatesoftware.example.thiswayup.R;

/**
 * Activity that demonstrates how you can retain the instance of a parent Activity 
 * (instead of recreating it) when navigating Up from the same task. The Up Intent 
 * will be delivered to the current parent instance's onNewIntent() method.
 * 
 */
public class RetainParentInstanceActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_retain_parent_instance);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			
			// Here we need to get the parent intent and add the 
			// FLAG_ACTIVITY_CLEAR_TOP flag so that any previous 
			// instance of the parent Activity in the current task 
			// will be restored in its prior state instead of being 
			// recreated (the default behavior). We then simply call
			// navigateUpTo with this intent.
			
			Intent parent = NavUtils.getParentActivityIntent(this);
			parent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			NavUtils.navigateUpTo(this, parent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
