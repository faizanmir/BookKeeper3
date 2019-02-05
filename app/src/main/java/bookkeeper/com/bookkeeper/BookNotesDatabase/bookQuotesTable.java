package bookkeeper.com.bookkeeper.BookNotesDatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bookQuoteTable")
public class bookQuotesTable {

    @NonNull
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "id")
    public int quote_id;

    @ColumnInfo(name = "book")
    public String quoted_book;

    @ColumnInfo(name = "quote")
    public String quote;
}
