package com.example.thiswayup.jellybean;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.readystatesoftware.example.thiswayup.R;

/**
 * Activity that demonstrates how you can retain the instance of a parent Activity 
 * (instead of recreating it) when navigating Up from the same task. The Up Intent 
 * will be delivered to the current parent instance's onNewIntent() method.
 * 
 * Notice we do not need to implement Up behavior in onOptionsItemSelected. This
 * is handled for us in Jelly Bean and above when we have a parentActivityName
 * defined in our manifest.
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

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	public Intent getParentActivityIntent() {
		
		// Here we override getParentActivityIntent, call the super
		// implementation and add the FLAG_ACTIVITY_CLEAR_TOP Intent
		// flag so that any previous instance of the parent Activity
		// in the current task will be restored in its prior state 
		// instead of being recreated (the default behavior).
		
		Intent parent = super.getParentActivityIntent();
		parent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		return parent;
	}
	
}
