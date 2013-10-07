package com.example.thiswayup.drawer;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.thiswayup.Data;
import com.example.thiswayup.Data.Movie;
import com.readystatesoftware.example.thiswayup.R;

/**
 * This example illustrates usage of the DrawerLayout widget in an application root activity.
 * 
 */
public class RootDrawerActivity extends BaseDrawerActivity {

	public static final String ARG_SORT = "com.example.thiswayup.SORT";
	
	public static final int SORT_TITLE = 0;
	public static final int SORT_YEAR = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_root_drawer);
		setupNavDrawer(true);
		if (savedInstanceState == null) {
			int sort = getIntent().getIntExtra(ARG_SORT, SORT_TITLE);
			onDrawerItemSelected(sort);
	    }
	}

	@Override
	protected void onDrawerItemSelected(int position) {
		
		// As this is our root Activity, draw item selection should initiate
		// a 'view switch' action by replacing the Activity's current fragment
		// content. 
		
		// A view switch follows the same basic policies as list or tab navigation 
		// in that a view switch does not create navigation history. This pattern
		// should only be used at the root activity of a task, leaving some form
		// of Up navigation active for activities further down the navigation
		// hierarchy.
		
		// Update the main content by replacing fragments
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putInt(RootDrawerActivity.ARG_SORT, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // Update selected item and title, then close the drawer
        setDrawerItemChecked(position, true);
        setTitle(getResources().getStringArray(R.array.drawer_array)[position]);
        closeDrawer();
		
	}
	
	/**
     * Fragment that appears in the "content_frame"
     */
    public static class CategoryFragment extends ListFragment {

        private ArrayList<Movie> mData;
        
        public CategoryFragment() {
            // Empty constructor required for fragment subclasses
        }
        
        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            int sort = getArguments().getInt(ARG_SORT);
            mData = sort == SORT_TITLE ? Data.getMoviesByTitle() : Data.getMoviesByYear();
            setListAdapter(new ArrayAdapter<Movie>(getActivity(), 
            		android.R.layout.simple_list_item_1, android.R.id.text1, 
            		mData));
        }

		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {
			Movie movie = mData.get(position);
			Intent intent = new Intent(getActivity(), DetailDrawerActivity.class);
			intent.putExtra(DetailDrawerActivity.ARG_ID, movie.getId());
			intent.putExtra(DetailDrawerActivity.ARG_INFO, getString(R.string.info_detail_from_nav_drawer));
			startActivity(intent);
		}
        
    }

}