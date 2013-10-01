/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
import com.example.thiswayup.R;

/**
 * This example illustrates a common usage of the DrawerLayout widget
 * in the Android support library.
 * <p/>
 * <p>When a navigation (left) drawer is present, the host activity should detect presses of
 * the action bar's Up affordance as a signal to open and close the navigation drawer. The
 * ActionBarDrawerToggle facilitates this behavior.
 * Items within the drawer should fall into one of two categories:</p>
 * <p/>
 * <ul>
 * <li><strong>View switches</strong>. A view switch follows the same basic policies as
 * list or tab navigation in that a view switch does not create navigation history.
 * This pattern should only be used at the root activity of a task, leaving some form
 * of Up navigation active for activities further down the navigation hierarchy.</li>
 * <li><strong>Selective Up</strong>. The drawer allows the user to choose an alternate
 * parent for Up navigation. This allows a user to jump across an app's navigation
 * hierarchy at will. The application should treat this as it treats Up navigation from
 * a different task, replacing the current task stack using TaskStackBuilder or similar.
 * This is the only form of navigation drawer that should be used outside of the root
 * activity of a task.</li>
 * </ul>
 * <p/>
 * <p>Right side drawers should be used for actions, not navigation. This follows the pattern
 * established by the Action Bar that navigation should be to the left and actions to the right.
 * An action should be an operation performed on the current contents of the window,
 * for example enabling or disabling a data overlay on top of the current content.</p>
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
		
		// update the main content by replacing fragments
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putInt(RootDrawerActivity.ARG_SORT, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
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