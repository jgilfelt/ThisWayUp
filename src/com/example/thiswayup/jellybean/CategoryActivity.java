package com.example.thiswayup.jellybean;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.thiswayup.Data;
import com.example.thiswayup.Data.Movie;
import com.readystatesoftware.example.thiswayup.R;

/**
 * Activity that accepts a single String extra in its Intent bundle that
 * represents a genre category to display a list of movies.
 * 
 * Notice we do not need to implement Up behavior in onOptionsItemSelected. 
 * This is handled for us in Jelly Bean and above when we have a 
 * parentActivityName defined in our manifest.
 *
 */
public class CategoryActivity extends Activity implements OnItemClickListener {
	
	public static final String ARG_GENRE = "com.example.thiswayup.GENRE";
	
	ListView mList;
	ArrayAdapter<Movie> mAdapter;
	
	private String genre;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample);
		
		genre = getIntent().getStringExtra(ARG_GENRE);
		
		setTitle(genre);
		TextView info = (TextView) findViewById(R.id.info);
		info.setText(R.string.info_category);
		
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		mList = (ListView) findViewById(R.id.list);
		mAdapter = new ArrayAdapter<Movie>(this, android.R.layout.simple_list_item_1, android.R.id.text1, Data.getMovies(genre));
		mList.setAdapter(mAdapter);
		mList.setOnItemClickListener(this);
	
	}

	@Override
	public void onItemClick(AdapterView<?> l, View v, int position, long id) {
		Movie movie = Data.getMovies(genre).get(position);
		Intent intent = new Intent(this, DetailActivity.class);
		intent.putExtra(DetailActivity.ARG_ID, movie.getId());
		startActivity(intent);
	}

}
