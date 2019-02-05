package bookkeeper.com.bookkeeper;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import bookkeeper.com.bookkeeper.BooksDatabase.Book;
import bookkeeper.com.bookkeeper.BooksDatabase.BooksDataBase;
import me.anwarshahriar.calligrapher.Calligrapher;

public class mainActivity extends AppCompatActivity {
    TextView textView_quotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int array_quotes[] = {R.string.quote1, R.string.quote2, R.string.quote3, R.string.quote4, R.string.quote5, R.string.quote6, R.string.quote7};
        Random random = new Random();
        int z = random.nextInt(array_quotes.length - 1);
        textView_quotes = findViewById(R.id.quotes);
        textView_quotes.setText(array_quotes[z]);
        final ArrayList<Book> books = (ArrayList<Book>) BooksDataBase.getInstance(this).getDAO().getBooks();
        Collections.reverse(books);
        final ArrayList<Book>filter_books = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.recycle);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "fonts/Product Sans Bold.ttf", true);

        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        final myRecyclerView obj = new myRecyclerView(books);
        recyclerView.setAdapter(obj);

        final BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.input);

        EditText search = findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                filter_books.clear();
                if (s.length() > 0) {


                    for (int i = 0; i < books.size(); i++) {
                        if(books.get(i).book.toLowerCase().contains(s.toString().toLowerCase()))
                        filter_books.add(books.get(i));
                    }
                    books.clear();
                    books.addAll(filter_books);
                    obj.notifyDataSetChanged();

                }
                else
                {
                    books.clear();
                   books.addAll( (ArrayList<Book>) BooksDataBase.getInstance(mainActivity.this).getDAO().getBooks());
                    Collections.reverse(books);
                    obj.notifyDataSetChanged();
                }
            }




            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Button fab = findViewById(R.id.add_book);
        Button book_Save = bottomSheetDialog.findViewById(R.id.SaveButton);
        book_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText book_name = bottomSheetDialog.findViewById(R.id.book_name);
                EditText author_name = bottomSheetDialog.findViewById(R.id.author);
                Book book =new Book();
                book.book = book_name.getText().toString();
                book.author = author_name.getText().toString();
                books.add(book);
                BooksDataBase.getInstance(mainActivity.this).getDAO().insert(book);
                Collections.reverse(books);
                obj.notifyDataSetChanged();
                bottomSheetDialog.dismiss();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText book = bottomSheetDialog.findViewById(R.id.book_name);
                EditText author = bottomSheetDialog.findViewById(R.id.author);
                book.setText("");
                author.setText("");
                bottomSheetDialog.show();

            }
        });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
