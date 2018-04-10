package mdsouza5.hw7and8crudoperation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by merv on 4/8/18.
 */

public class CustomBookListAdapter extends ArrayAdapter<Book> {
    private Context bContext;
    private List<Book> bookList = new ArrayList<>();

    public CustomBookListAdapter(@NonNull Context context, @NonNull List<Book> objectsList) {
        super(context, 0, objectsList);
        bContext = context;
        bookList = objectsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(bContext).inflate(R.layout.books_layout, parent, false);
        }

        Book presentBook = bookList.get(position);
        ImageView ratingImage = (ImageView) listItem.findViewById(R.id.booksRating);

        if (presentBook.getBookName() == "Professional Android 4 Application Development") {
            ratingImage.setImageResource(R.drawable.one_of_five);
        } else if (presentBook.getBookName() == "Beginning Android 4 Application Development") {
            ratingImage.setImageResource(R.drawable.five_of_five);
        } else if (presentBook.getBookName() == "Programming Android") {
            ratingImage.setImageResource(R.drawable.four_of_five);
        } else {
            ratingImage.setImageResource(R.drawable.four_of_five);
        }
        TextView bookName = (TextView) listItem.findViewById(R.id.bookName);
        bookName.setText(presentBook.getBookName());

        TextView authorName = (TextView) listItem.findViewById(R.id.authorName);
        authorName.setText(presentBook.authorName);

        return listItem;
    }
}
