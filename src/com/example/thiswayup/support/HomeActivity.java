package com.example.thiswayup.support;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.thiswayup.Data;
import com.example.thiswayup.R;

public class HomeActivity extends Activity implements OnItemClickListener {

	ListView mList;
	ArrayAdapter<String> mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample);
		
		setTitle(R.string.genres);
		TextView info = (TextView) findViewById(R.id.info);
		info.setText(R.string.info_home);
		
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		mList = (ListView) findViewById(R.id.list);
		mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, Data.getGenres());
		mList.setAdapter(mAdapter);
		mList.setOnItemClickListener(this);
		
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	@Override
	public void onItemClick(AdapterView<?> l, View v, int position, long id) {
		Intent intent = new Intent(this, CategoryActivity.class);
		intent.putExtra(CategoryActivity.ARG_GENRE, Data.getGenres().get(position));
		startActivity(intent);
	}
	
}
