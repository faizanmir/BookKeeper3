package bookkeeper.com.bookkeeper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import bookkeeper.com.bookkeeper.BookNotesDatabase.bookQuotesTable;

public class notesRecyclerView extends RecyclerView.Adapter<notesRecyclerView.viewHolder> {
    ArrayList<bookQuotesTable> abc;

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

    class viewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recycler_view);
        }
    }


}
