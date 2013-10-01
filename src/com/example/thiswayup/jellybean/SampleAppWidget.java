package com.example.thiswayup.jellybean;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;

import com.example.thiswayup.Data;
import com.example.thiswayup.Data.Movie;
import com.example.thiswayup.R;

/**
 * Implementation of App Widget functionality.
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


	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	static void updateAppWidget(Context context,
			AppWidgetManager appWidgetManager, int appWidgetId) {

		Movie movie = Data.getMovie(2);

		// Construct the RemoteViews object
		RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.sample_app_widget);
		views.setTextViewText(R.id.appwidget_text, movie.toString());

		TaskStackBuilder ts = TaskStackBuilder.create(context)
			.addParentStack(DetailActivity.class);
		ts.editIntentAt(ts.getIntentCount()-1)
			.putExtra(CategoryActivity.ARG_GENRE, movie.getGenre());
		ts.addNextIntent(new Intent(context, DetailActivity.class)
			.putExtra(DetailActivity.ARG_ID, movie.getId())
			.putExtra(DetailActivity.ARG_INFO, context.getString(R.string.info_detail_from_widget)));

		views.setOnClickPendingIntent(R.id.appwidget_text, ts.getPendingIntent(1, PendingIntent.FLAG_CANCEL_CURRENT));   

		// Instruct the widget manager to update the widget
		appWidgetManager.updateAppWidget(appWidgetId, views);
	}
	
}
