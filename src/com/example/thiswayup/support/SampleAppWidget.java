package com.example.thiswayup.support;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.widget.RemoteViews;

import com.example.thiswayup.Data;
import com.example.thiswayup.Data.Movie;
import com.readystatesoftware.example.thiswayup.R;

/**
 * App Widget demonstrating the proper pattern for launching an Activity 
 * deep within an app hierarchy.
 * 
 * https://developer.android.com/design/patterns/navigation.html#into-your-app
 */
public class SampleAppWidget extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// There may be multiple widgets active, so update all of them
		final int N = appWidgetIds.length;
		for (int i = 0; i < N; i++) {
			updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
		}
	}

	@Override
	public void onEnabled(Context context) {
		// Enter relevant functionality for when the first widget is created
	}

	@Override
	public void onDisabled(Context context) {
		// Enter relevant functionality for when the last widget is disabled
	}


	static void updateAppWidget(Context context,
			AppWidgetManager appWidgetManager, int appWidgetId) {

		// An arbitrary movie to display
		Movie movie = Data.getMovie(4);

		// Construct the RemoteViews object
		RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.sample_app_widget);
		views.setTextViewText(R.id.appwidget_text, movie.toString());
		
		// To make navigation more predictable we must create a parent task stack
		// using TaskStackBuilder that includes a complete upward navigation path to
		// our app's topmost screen.

		TaskStackBuilder tsb = TaskStackBuilder.create(context)
			// Add all parent activities for the DetaiActivity we will launch 
			// from the app widget
			.addParentStack(DetailActivity.class);
		// Edit any Intents in the newly constructed task stack
		// to supply the extras we need
		tsb.editIntentAt(tsb.getIntentCount()-1)
			.putExtra(CategoryActivity.ARG_GENRE, movie.getGenre());
		// Add the DetailActivity with the appropriate extras to display
		// the relevant movie
		tsb.addNextIntent(new Intent(context, DetailActivity.class)
			.putExtra(DetailActivity.ARG_ID, movie.getId())
			.putExtra(DetailActivity.ARG_INFO, context.getString(R.string.info_detail_from_widget)));

		// Supply a pending intent to launch the task defined by the constructed stack
		views.setOnClickPendingIntent(R.id.appwidget_text, tsb.getPendingIntent(1, PendingIntent.FLAG_CANCEL_CURRENT));   

		// Instruct the widget manager to update the widget
		appWidgetManager.updateAppWidget(appWidgetId, views);
	}
	
}
