package mdsouza5.hw7and8crudoperation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.nio.file.FileSystemNotFoundException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Logcat Default;
    private static final String LOG_TAG = "BOOKS CRUD";

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Insert Records
        SqlHelper db = new SqlHelper(this);
        db.InsertBooks(new Book("Professional Android 4 Application Development", "Reto Meier"));
        db.InsertBooks(new Book("Beginning Android 4 Application Development", "WeiMeng Lee"));
        db.InsertBooks(new Book("Programming Android", "Wallace Jackson"));
        db.InsertBooks(new Book("Hello, Android", "Wallace Jackson"));

        //Get All Books Records
        List<Book> bookList = db.GetAllBooks();
        for (Book bookDetails :
                bookList) {
            System.out.println(String.format("Value at %d, Record Details are : %s. %s", i, bookDetails.bookName, bookDetails.authorName));
            i++;
        }

        //Get the Matching Row Id and User Id for a passed reference
        int j = db.UpdateBook(bookList.get(0));
        Log.d("UPDATE BOOK ROW Num", String.valueOf(j));

        //Delete the Matching Book Record for a passed reference
        db.DeleteBook(bookList.get(0));

        db.GetAllBooks();
    }
}

