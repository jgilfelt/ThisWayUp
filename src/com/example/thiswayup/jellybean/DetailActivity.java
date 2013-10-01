package com.example.thiswayup.jellybean;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
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
			// launched externally
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

	
//	@SuppressLint("NewApi")
//	@Override
//	public void onPrepareNavigateUpTaskStack(TaskStackBuilder builder) {
//		super.onPrepareNavigateUpTaskStack(builder);
//		Log.d("Up", "onPrepareNavigateUpTaskStack");
//		Intent parent = builder.editIntentAt(builder.getIntentCount()-1);
//		parent.putExtra(CategoryActivity.ARG_GENRE, genre);
//		//super.onPrepareNavigateUpTaskStack(builder);
//	}



	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	public Intent getParentActivityIntent() {
		Intent parent = super.getParentActivityIntent();
		parent.putExtra(CategoryActivity.ARG_GENRE, mMovie.getGenre());
		return parent;
	}
	
	@Override
	public void onItemClick(AdapterView<?> l, View v, int position, long id) {
		Movie movie = Data.getSimilarMovies(mMovie).get(position);
		Intent intent = new Intent(this, DetailActivity.class);
		intent.putExtra(DetailActivity.ARG_ID, movie.getId());
		startActivity(intent);
	}

}
