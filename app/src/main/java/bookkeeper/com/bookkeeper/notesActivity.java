package bookkeeper.com.bookkeeper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bookkeeper.com.bookkeeper.BookNotesDatabase.bookQuotes;
import bookkeeper.com.bookkeeper.BookNotesDatabase.bookQuotesTable;
import me.anwarshahriar.calligrapher.Calligrapher;

public class notesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent_that_woke_me = getIntent();


        final String book_name;
        book_name = intent_that_woke_me.getStringExtra("Book_name");

        final bookQuotes bookQuotes = bookkeeper.com.bookkeeper.BookNotesDatabase.bookQuotes.getInstance(this);
        final ArrayList<bookQuotesTable> book_data = (ArrayList<bookQuotesTable>) bookQuotes.getDAO().getQuotesForThisBook(book_name);
        setContentView(R.layout.activity_notes2);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "fonts/Product Sans Bold.ttf", true);

        RecyclerView recyclerView = findViewById(R.id.notesRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        final notesRecyclerView obj = new notesRecyclerView(book_data);
        recyclerView.setAdapter(obj);

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.notesactivitybottom);

        Button notesadd_button = bottomSheetDialog.findViewById(R.id.button_noteadder);
        Button showDialog = findViewById(R.id.notesadd_button);

        notesadd_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText notetaker = bottomSheetDialog.findViewById(R.id.note_edit_text);
                bookQuotesTable bookQuotesTable = new bookQuotesTable();
                bookQuotesTable.quoted_book = book_name;
                bookQuotesTable.quote = notetaker.getText().toString();
                bookQuotes.getDAO().insert(bookQuotesTable);
                book_data.add(bookQuotesTable);
                obj.notifyDataSetChanged();
                bottomSheetDialog.dismiss();
            }
        });
        showDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.show();

            }
        });


    }
}
