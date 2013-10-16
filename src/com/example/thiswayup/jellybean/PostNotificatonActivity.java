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
import com.readystatesoftware.example.thiswayup.R;

/**
 * Activity demonstrating the proper pattern for posting notifications that
 * target an Activity deep within an app hierarchy.
 * 
 * https://developer.android.com/design/patterns/navigation.html#into-your-app
 * 
 */
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

		// An arbitrary movie to show a notification for
		Movie movie = Data.getMovie(1);

		// To make navigation more predictable we must create a parent task
		// stack using TaskStackBuilder that includes a complete upward 
		// navigation path to our app's topmost screen.

		TaskStackBuilder ts = TaskStackBuilder.create(this)
		// Add all parent activities for the DetaiActivity we will launch
		// from the notification
				.addParentStack(DetailActivity.class);
		// Edit any Intents in the newly constructed task stack
		// to supply the extras we need
		ts.editIntentAt(ts.getIntentCount() - 1)
				.putExtra(CategoryActivity.ARG_GENRE, movie.getGenre());
		// Add the DetailActivity with the appropriate extras to display
		// the relevant movie
		ts.addNextIntent(new Intent(this, DetailActivity.class)
				.putExtra(DetailActivity.ARG_ID, movie.getId())
				.putExtra(DetailActivity.ARG_INFO, getString(R.string.info_detail_from_notification)));

		// Construct a new notification
		Notification.Builder builder = new Notification.Builder(this)
				.setTicker(getString(R.string.notification_demo))
				.setSmallIcon(R.drawable.ic_stat_notification)
				.setLargeIcon(
						BitmapFactory.decodeResource(getResources(),
								movie.getImage()))
				.setContentTitle(movie.toString())
				.setContentText(getString(R.string.this_will_open_the_detail_activity))
				.setAutoCancel(true)
				// Supply a pending intent to launch the task defined by the
				// constructed stack
				.setContentIntent(ts.getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT));

		// Post the notification
		NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		nm.notify("direct_tag", R.id.post_notification, builder.build());

	}

}
