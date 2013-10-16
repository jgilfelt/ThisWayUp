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
import com.readystatesoftware.example.thiswayup.R;

/**
 * Activity that accepts a integer extra in its Intent bundle or data supplied
 * from a http deep link that represents an id for which to display details for
 * a particular movie.
 * 
 * Notice we do not need to implement Up behavior in onOptionsItemSelected. This
 * is handled for us in Jelly Bean and above when we have a parentActivityName
 * defined in our manifest.
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
		mAdapter = new ArrayAdapter<Movie>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				Data.getSimilarMovies(mMovie));
		mList.setAdapter(mAdapter);
		mList.setOnItemClickListener(this);

	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	public Intent getParentActivityIntent() {

		// Our parent activity (CategoryActivity) requires Intent
		// extras in order to display the appropriate genre.
		//
		// Here we override getParentActivityIntent, call the super
		// implementation and supply the required extra to the
		// returned Intent.
		//
		// We could also override onPrepareNavigateUpTaskStack if we
		// needed to manipulate Intents at deeper levels.

		Intent parent = super.getParentActivityIntent();
		parent.putExtra(CategoryActivity.ARG_GENRE, mMovie.getGenre());
		return parent;
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
