package bookkeeper.com.bookkeeper.BooksDatabase;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface BooksDAO {

    @Insert
    void insert(Book obj);

    @Delete
    void delete(Book obj);

    @Update
    void update(Book obj);

    @Query("SELECT * FROM books")
    List<Book> getBooks();
}