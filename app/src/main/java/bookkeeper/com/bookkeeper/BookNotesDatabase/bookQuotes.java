package bookkeeper.com.bookkeeper.BookNotesDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {bookQuotesTable.class}, version = 1, exportSchema = false)
public abstract class bookQuotes extends RoomDatabase {
    private static final String database_name = "bookQuoteTable";
    private static final Object lock = new Object();
    private static bookQuotes db;

    public static bookQuotes getInstance(Context abc) {

        if (db == null) {
            synchronized (lock) {
                db = Room.databaseBuilder(abc.getApplicationContext(), bookQuotes.class, database_name)
                        .allowMainThreadQueries().build();
            }
        }
        return db;
    }

    public abstract bookQuotesDAO getDAO();


}
