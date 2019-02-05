package bookkeeper.com.bookkeeper.BookNotesDatabase;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface bookQuotesDAO {

    @Insert
    public void insert(bookQuotesTable obj);

    @Update
    public void update(bookQuotesTable obj);

    @Delete
    public void delete(bookQuotesTable obj);

    @Query("SELECT * FROM bookQuoteTable WHERE book =(:book_name)")
    public List<bookQuotesTable> getQuotesForThisBook(String book_name);
}
