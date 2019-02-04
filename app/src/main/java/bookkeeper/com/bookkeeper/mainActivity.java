package bookkeeper.com.bookkeeper;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import bookkeeper.com.bookkeeper.BooksDatabase.Book;
import bookkeeper.com.bookkeeper.BooksDatabase.BooksDataBase;
import me.anwarshahriar.calligrapher.Calligrapher;

import java.util.ArrayList;

public class mainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<Book> books = (ArrayList<Book>) BooksDataBase.getInstance(this).getDAO().getBooks();
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
                   books.addAll( (ArrayList<Book>) BooksDataBase.getInstance(mainActivity.this).getDAO().getBooks());
                    obj.notifyDataSetChanged();
                }
            }




            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        FloatingActionButton fab = findViewById(R.id.add_book);

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
                obj.notifyDataSetChanged();
                Toast.makeText(mainActivity.this , "Added" ,Toast.LENGTH_LONG).show();
                bottomSheetDialog.dismiss();
            }
        });




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.show();
            }
        });




    }
}
