package bookkeeper.com.bookkeeper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import bookkeeper.com.bookkeeper.BookNotesDatabase.bookQuotes;
import bookkeeper.com.bookkeeper.BookNotesDatabase.bookQuotesTable;

public class notesRecyclerView extends RecyclerView.Adapter<notesRecyclerView.viewHolder> {
    ArrayList<bookQuotesTable> abc;
    public Context context;
    public notesRecyclerView(ArrayList<bookQuotesTable> abc) {
        this.abc = abc;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv2, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.textView.setText(abc.get(position).quote);
    }

    @Override
    public int getItemCount() {

        return abc.size();
    }

    class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView textView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recycler_view);
            context = itemView.getContext();
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

        @Override
        public boolean onLongClick(View v) {

            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
            bottomSheetDialog.setContentView(R.layout.delete);
            bottomSheetDialog.show();

            Button delete_button = bottomSheetDialog.findViewById(R.id.delete_book_button);
            delete_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bookQuotes.getInstance(context).getDAO().delete(abc.get(getAdapterPosition()));
                    abc.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    bottomSheetDialog.dismiss();

                }
            });


            return true;
        }
    }


}
