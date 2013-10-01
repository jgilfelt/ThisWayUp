package com.example.thiswayup.drawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
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
 * This example illustrates usage of the DrawerLayout widget for an activity deep within an
 * application navigation hierarchy.
 * 
 */
public class DetailDrawerActivity extends BaseDrawerActivity implements OnItemClickListener {

	public static final String ARG_ID = "com.example.thiswayup.ID";
	public static final String ARG_INFO = "com.example.thiswayup.INFO";
	
	ListView mList;
	ArrayAdapter<Movie> mAdapter;
	
	private int mId;
	private Movie mMovie;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_drawer);
		
		Intent launchIntent = getIntent();
		mId = launchIntent.getIntExtra(ARG_ID, 0);
		String infoText = launchIntent.getStringExtra(ARG_INFO);
		
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
	
		setupNavDrawer(false);
		
		mList = (ListView) findViewById(R.id.list);
		mAdapter = new ArrayAdapter<Movie>(this, android.R.layout.simple_list_item_1, android.R.id.text1, Data.getSimilarMovies(mMovie));
		mList.setAdapter(mAdapter);
		mList.setOnItemClickListener(this);
		
	}
	
	@Override
	public void onItemClick(AdapterView<?> l, View v, int position, long id) {
		Movie movie = Data.getSimilarMovies(mMovie).get(position);
		Intent intent = new Intent(this, DetailDrawerActivity.class);
		intent.putExtra(DetailDrawerActivity.ARG_ID, movie.getId());
		startActivity(intent);
	}


	@Override
	protected void onDrawerItemSelected(int position) {
		
		// As we are outside the root Activity, we should perform
		// 'Selective Up' navigation, recreating our task stack in
		// the process.
		
		// Selective Up allows a user to jump across an app's navigation 
		// hierarchy at will. The application should treat this as it treats
		// Up navigation from a different task, replacing the current task
		// stack using TaskStackBuilder or similar. This is the only form of
		// navigation drawer that should be used outside of the root activity
		// of a task.
		
		TaskStackBuilder ts = TaskStackBuilder.create(this)
        	.addParentStack(this);
        ts.editIntentAt(ts.getIntentCount()-1)
			.putExtra(RootDrawerActivity.ARG_SORT, position);
		ts.startActivities();
		finish();
		
	}

}
