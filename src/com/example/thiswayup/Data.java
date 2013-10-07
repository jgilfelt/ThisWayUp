package com.example.thiswayup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.util.SparseArray;
import com.readystatesoftware.example.thiswayup.R;

public class Data {

	public static final String GENRE_ACTION = "Action";
	public static final String GENRE_DRAMA = "Drama";
	
	private static ArrayList<String> sGenres;
	private static SparseArray<Movie> sMovies;
	
	static {
		
		sGenres = new ArrayList<String>();
		sGenres.add(GENRE_ACTION);
		sGenres.add(GENRE_DRAMA);
		
		sMovies = new SparseArray<Movie>();
		sMovies.put(1, new Movie(1, GENRE_ACTION, "Die Hard", 1988, R.drawable.diehard));
		sMovies.put(2, new Movie(2, GENRE_ACTION, "Lethal Weapon", 1987, R.drawable.lethalweapon));
		sMovies.put(3, new Movie(3, GENRE_ACTION, "The Terminator", 1984, R.drawable.terminator));
		sMovies.put(4, new Movie(4, GENRE_DRAMA, "A Few Good Men", 1992, R.drawable.fewgoodmen));
		sMovies.put(5, new Movie(5, GENRE_DRAMA, "JFK", 1991, R.drawable.jfk));
		sMovies.put(6, new Movie(6, GENRE_DRAMA, "Wall Street", 1987, R.drawable.wallstreet));
		
	}
	
	public static ArrayList<String> getGenres() {
		return sGenres;
	}
	
	public static Movie getMovie(int id) {
		return sMovies.get(id);
	}

	public static ArrayList<Movie> getMovies(String genre) {
		ArrayList<Movie> matches = new ArrayList<Movie>();
		for (int i = 0; i < sMovies.size(); i++) {
			final Movie movie = sMovies.valueAt(i);
			if (movie.getGenre().equals(genre)) {
				matches.add(movie);
			}
		}
		return matches;
	}
	
	public static ArrayList<Movie> getMoviesByTitle() {
		ArrayList<Movie> matches = new ArrayList<Movie>();
		for (int i = 0; i < sMovies.size(); i++) {
			final Movie movie = sMovies.valueAt(i);
			matches.add(movie);
		}
		Collections.sort(matches, new Comparator<Movie>() {
			@Override
			public int compare(Movie lhs, Movie rhs) {
				return lhs.getTitle().compareToIgnoreCase(rhs.getTitle());
			}
		});
		return matches;
	}
	
	public static ArrayList<Movie> getMoviesByYear() {
		ArrayList<Movie> matches = new ArrayList<Movie>();
		for (int i = 0; i < sMovies.size(); i++) {
			final Movie movie = sMovies.valueAt(i);
			matches.add(movie);
		}
		Collections.sort(matches, new Comparator<Movie>() {
			@Override
			public int compare(Movie lhs, Movie rhs) {
				return lhs.getYear() - rhs.getYear();
			}
		});
		return matches;
	}
	
	public static ArrayList<Movie> getSimilarMovies(Movie movie) {
		ArrayList<Movie> matches = new ArrayList<Movie>();
		for (int i = 0; i < sMovies.size(); i++) {
			final Movie m = sMovies.valueAt(i);
			if (m.getId() != movie.getId() && m.getGenre().equals(movie.getGenre())) {
				matches.add(m);
			}
		}
		return matches;
	}
	
	public static class Movie {

		private int mId;
		private String mGenre;
		private String mTitle;
		private int mYear;
		private int mImage;
		
		public Movie(int id, String genre, String title, int year, int image) {
			super();
			this.mId = id;
			this.mGenre = genre;
			this.mTitle = title;
			this.mYear = year;
			this.mImage = image;
		}
		
		public int getId() {
			return mId;
		}
		
		public String getGenre() {
			return mGenre;
		}
		
		public String getTitle() {
			return mTitle;
		}
		
		public int getImage() {
			return mImage;
		}
		
		public int getYear() {
			return mYear;
		}

		@Override
		public String toString() {
			return mTitle + " (" + mYear + ")";
		}
		
	}
	
}
