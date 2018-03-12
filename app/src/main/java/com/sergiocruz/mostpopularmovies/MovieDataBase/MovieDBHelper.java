package com.sergiocruz.mostpopularmovies.MovieDataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.sergiocruz.mostpopularmovies.MovieDataBase.MovieContract.ReviewsTable;

import static com.sergiocruz.mostpopularmovies.MovieDataBase.MovieContract.MovieTable;
import static com.sergiocruz.mostpopularmovies.MovieDataBase.MovieContract.VideosTable;

/**
 * Created by Sergio on 23/02/2018.
 */

public class MovieDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "moviedb.db";
    private static final int VERSION = 1;

    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use to open or create the database
     */
    public MovieDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    /**
     * Create movies table
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_MOVIES_TABLE = "CREATE TABLE " + MovieTable.MOVIES_TABLE_NAME + " (" +
                MovieTable._ID + " INTEGER, " +
                MovieTable.VOTE_COUNT + " INTEGER, " +
                MovieTable.MOVIE_ID + " INTEGER PRIMARY KEY, " +
                MovieTable.HAS_VIDEO + " INTEGER, " +
                MovieTable.VOTE_AVERAGE + " REAL, " +
                MovieTable.TITLE + " TEXT, " +
                MovieTable.POPULARITY + " REAL, " +
                MovieTable.POSTER_PATH + " TEXT, " +
                MovieTable.ORIGINAL_LANGUAGE + " TEXT, " +
                MovieTable.ORIGINAL_TITLE + " TEXT, " +
                MovieTable.GENRE_ID + " TEXT, " + // save int array as JSON
                MovieTable.BACKDROP_PATH + " TEXT, " +
                MovieTable.IS_ADULT + " INTEGER, " + //  0 - false, 1 - true
                MovieTable.OVERVIEW + " TEXT, " +
                MovieTable.RELEASE_DATE + " TEXT, " +
                MovieTable.IS_FAVORITE + " INTEGER, " + //  0 - false, 1 - true
                MovieTable.POSTER_FILE_PATH + " TEXT, " +
                MovieTable.BACKDROP_FILE_PATH + " TEXT, " +
                " UNIQUE (" + MovieTable._ID + " , " + MovieTable.MOVIE_ID + ")" +
                ");";

        final String CREATE_VIDEOS_TABLE = "CREATE TABLE " + VideosTable.VIDEOS_TABLE_NAME + " (" +
                VideosTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                VideosTable.MOVIE_ID + " INTEGER, " +
                VideosTable.VIDEO_ID + " TEXT, " +
                VideosTable.ISO_639_1 + " TEXT, " +
                VideosTable.ISO_3166_1 + " TEXT, " +
                VideosTable.KEY + " TEXT, " +
                VideosTable.NAME + " TEXT, " +
                VideosTable.SITE + " TEXT, " +
                VideosTable.SIZE + " INTEGER, " +
                VideosTable.TYPE + " INTEGER, " +
                " FOREIGN KEY (" + VideosTable.MOVIE_ID + ") REFERENCES " +
                MovieTable.MOVIES_TABLE_NAME + " (" + MovieTable.MOVIE_ID + ")" +
                " ON DELETE CASCADE);" +
                ");";

        final String CREATE_REVIEWS_TABLE = "CREATE TABLE " + ReviewsTable.REVIEWS_TABLE_NAME + " (" +
                ReviewsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ReviewsTable.MOVIE_ID + " INTEGER, " +
                ReviewsTable.REVIEW_ID + " INTEGER, " +
                ReviewsTable.AUTHOR + " TEXT, " +
                ReviewsTable.CONTENT + " TEXT, " +
                ReviewsTable.URL + " TEXT, " +
                " FOREIGN KEY (" + ReviewsTable.MOVIE_ID + ") REFERENCES " +
                MovieTable.MOVIES_TABLE_NAME + " (" + MovieTable.MOVIE_ID + ")" +
                " ON DELETE CASCADE);" +
                ");";

        db.execSQL(CREATE_MOVIES_TABLE);
        db.execSQL(CREATE_VIDEOS_TABLE);
        db.execSQL(CREATE_REVIEWS_TABLE);
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MovieTable.MOVIES_TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        if (!db.isReadOnly() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            // Enable foreign key constraints
            // db.execSQL("PRAGMA foreign_keys=ON;");
            db.setForeignKeyConstraintsEnabled(true);
        }

    }
}
