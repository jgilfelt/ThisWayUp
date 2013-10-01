package com.example.thiswayup.jellybean;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
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


	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	private void postNotification() {
		
		Movie movie = Data.getMovie(1);
		
		TaskStackBuilder ts = TaskStackBuilder.create(this)
			.addParentStack(DetailActivity.class);
		ts.editIntentAt(ts.getIntentCount()-1)
			.putExtra(CategoryActivity.ARG_GENRE, movie.getGenre());
        ts.addNextIntent(new Intent(this, DetailActivity.class)
        	.putExtra(DetailActivity.ARG_ID, movie.getId())
        	.putExtra(DetailActivity.ARG_INFO, getString(R.string.info_detail_from_notification)));
        
		Notification.Builder builder = new Notification.Builder(this)
        	.setTicker("Notification Demo")
        	.setSmallIcon(R.drawable.ic_stat_notification)
        	.setLargeIcon(BitmapFactory.decodeResource(getResources(), movie.getImage()))
        	.setContentTitle(movie.getTitle())
        	.setContentText("This will open the detail Activity")
        	.setAutoCancel(true)
        	.setContentIntent(ts.getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT));
		NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		nm.notify("direct_tag", R.id.post_notification, builder.build());
		
	}



}
