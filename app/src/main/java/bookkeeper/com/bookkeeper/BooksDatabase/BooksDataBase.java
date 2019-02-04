package bookkeeper.com.bookkeeper.BooksDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Book.class}, version = 1 ,exportSchema = false)
public abstract class BooksDataBase extends RoomDatabase {

    private static final String database_name = "bookdatabase";
    private static final  Object lock =new Object() ;
    private static BooksDataBase db;

    public static BooksDataBase getInstance(Context abc){

        if (db==null) {
            synchronized (lock) {
                db = Room.databaseBuilder(abc.getApplicationContext(), BooksDataBase.class, database_name)
                        .allowMainThreadQueries().build();
            }
        }
        return db;
    }

    public abstract BooksDAO getDAO ();
}
