package bookkeeper.com.bookkeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import bookkeeper.com.bookkeeper.BooksDatabase.Book;
import me.anwarshahriar.calligrapher.Calligrapher;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Random;

public class myRecyclerView extends RecyclerView.Adapter<myRecyclerView.viewHolder> {

    private ArrayList<Book> abc ;
    private int array_pic [] ={R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4};

    myRecyclerView(ArrayList<Book> abc) {
        this.abc = abc;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        viewHolder viewHolder =  new viewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv,viewGroup,false));
        Calligrapher calligrapher = new Calligrapher(viewHolder.itemView.getContext());
        calligrapher.setFont(viewHolder.itemView, "fonts/Product Sans Bold.ttf");
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        viewHolder.textView1.setText(abc.get(i).book);
        viewHolder.textView2.setText(abc.get(i).author);

        Random random = new Random();
        int j =random.nextInt(4);
        viewHolder.imageView.setImageResource(array_pic[j]);

    }

    @Override
    public int getItemCount() {
        return abc.size();
    }
    class viewHolder extends RecyclerView.ViewHolder{
        TextView textView1,textView2 ;
        ImageView imageView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textView1=itemView.findViewById(R.id.textbox1);
            textView2=itemView.findViewById(R.id.textbox2);
            imageView = itemView.findViewById(R.id.book_image);


        }
    }

    public void notifyDS(){
        notifyDataSetChanged();
    }
}
