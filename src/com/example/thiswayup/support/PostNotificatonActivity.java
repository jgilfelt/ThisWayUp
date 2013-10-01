package com.example.thiswayup.support;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.thiswayup.Data;
import com.example.thiswayup.Data.Movie;
import com.example.thiswayup.R;

public class PostNotificatonActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notificaton);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Button b = (Button) findViewById(R.id.post_notification);
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				postNotification();
			}
		});
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
	
	private void postNotification() {
		
		Movie movie = Data.getMovie(6);
		
		TaskStackBuilder tsb = TaskStackBuilder.create(this)
			.addParentStack(DetailActivity.class);
		tsb.editIntentAt(tsb.getIntentCount()-1)
			.putExtra(CategoryActivity.ARG_GENRE, movie.getGenre());
        tsb.addNextIntent(new Intent(this, DetailActivity.class)
        	.putExtra(DetailActivity.ARG_ID, movie.getId())
        	.putExtra(DetailActivity.ARG_INFO, getString(R.string.info_detail_from_notification)));
        
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
        	.setTicker("Notification Demo")
        	.setSmallIcon(R.drawable.ic_stat_notification)
        	.setLargeIcon(BitmapFactory.decodeResource(getResources(), movie.getImage()))
        	.setContentTitle(movie.getTitle())
        	.setContentText("This will open the detail Activity")
        	.setAutoCancel(true)
        	.setContentIntent(tsb.getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT));
		NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		nm.notify("direct_tag", R.id.post_notification, builder.build());
		
	}



}
