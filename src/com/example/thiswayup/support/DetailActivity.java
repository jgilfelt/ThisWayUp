package com.example.thiswayup.support;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.thiswayup.Data;
import com.example.thiswayup.Data.Movie;
import com.example.thiswayup.R;

/**
 * Activity that accepts a integer extra in its Intent bundle or data
 * supplied from a http deep link that represents an id for which 
 * to display details for a particular movie.
 * 
 * Because we can navigate to this Activity from an outside task, we must
 * query NavUtils.shouldUpRecreateTask when handling Up navigation. See
 * the implementation in onOptionsItemSelected.
 *
 */
public class DetailActivity extends Activity implements OnItemClickListener {

	public static final String ARG_ID = "com.example.thiswayup.ID";
	public static final String ARG_INFO = "com.example.thiswayup.INFO";
	
	ListView mList;
	ArrayAdapter<Movie> mAdapter;
	
	private int mId;
	private Movie mMovie;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		Intent launchIntent = getIntent();
		Uri data = launchIntent.getData();
		String infoText = null;
		
		if (data != null) {
			// Activity was launched externally from a deep link
			String id = data.getLastPathSegment();
			mId = Integer.parseInt(id);
			infoText = getString(R.string.info_detail_from_outside_task);
		} else {
			mId = launchIntent.getIntExtra(ARG_ID, 0);
			infoText = launchIntent.getStringExtra(ARG_INFO);
		}
		
		mMovie = Data.getMovie(mId);
		setTitle(mMovie.toString());
		
		ImageView image = (ImageView) findViewById(R.id.image);
		image.setImageResource(mMovie.getImage());
		TextView info = (TextView) findViewById(R.id.info);
		if (infoText != null) {
			info.setText(infoText);
		} else {
			info.setText(R.string.info_detail);
		}
		
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		mList = (ListView) findViewById(R.id.list);
		mAdapter = new ArrayAdapter<Movie>(this, android.R.layout.simple_list_item_1, android.R.id.text1, Data.getSimilarMovies(mMovie));
		mList.setAdapter(mAdapter);
		mList.setOnItemClickListener(this);
		
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// Respond to the action bar's Up/Home button
		case android.R.id.home:
			// Retrieve this Activiy's parent Up intent
            Intent upIntent = NavUtils.getParentActivityIntent(this);
            if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
            	// This activity is NOT part of this app's task, so create a new task
                // when navigating up, with a synthesized back stack.
            	TaskStackBuilder tsb = TaskStackBuilder.create(this)
                        .addParentStack(this);
            	// Add the required Intent extras as appropriate
                tsb.editIntentAt(tsb.getIntentCount() - 1)
                	.putExtra(CategoryActivity.ARG_GENRE, mMovie.getGenre());
                // Navigate up to the closest parent
            	tsb.startActivities();
                finish();
            } else {
            	// This activity is part of this app's task, so simply
                // navigate up to the logical parent activity.
            	// Add the required Intent extras as appropriate
            	upIntent.putExtra(CategoryActivity.ARG_GENRE, mMovie.getGenre());
                NavUtils.navigateUpTo(this, upIntent);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
	
	@Override
	public void onItemClick(AdapterView<?> l, View v, int position, long id) {
		// Here we can navigate to peer activities
		Movie movie = Data.getSimilarMovies(mMovie).get(position);
		Intent intent = new Intent(this, DetailActivity.class);
		intent.putExtra(DetailActivity.ARG_ID, movie.getId());
		startActivity(intent);
	}

}
