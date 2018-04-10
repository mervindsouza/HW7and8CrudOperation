package mdsouza5.hw7and8crudoperation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    //Logcat Default;
    private static final String LOG_TAG = "BOOKS CRUD";

    ListView listView;
    CustomBookListAdapter listAdapter;
    Spinner spinnerControl;

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerControl = (Spinner) findViewById(R.id.booksListSpinner);

        //Insert Records
        SqlHelper db = new SqlHelper(this);
        db.InsertBooks(new Book("Professional Android 4 Application Development", "Reto Meier", 1));
        db.InsertBooks(new Book("Beginning Android 4 Application Development", "WeiMeng Lee", 5));
        db.InsertBooks(new Book("Programming Android", "Wallace Jackson", 4));
        db.InsertBooks(new Book("Hello, Android", "Wallace Jackson", 4));

        //Get All Books Records
        final List<Book> bookList = db.GetAllBooks();
        List<String> bookNames = new ArrayList<>();
        for (Book bookDetails :
                bookList) {
            System.out.println(String.format("Value at %d, Record Details are : %s %s %d", i, bookDetails.bookName, bookDetails.authorName, bookDetails.bookRating));
            bookNames.add(bookDetails.bookName);
            i++;
        }

        //Get the Matching Row Id and User Id for a passed reference
        //int j = db.UpdateBook(bookList.get(0));
        //Log.d("UPDATE BOOK ROW Num", String.valueOf(j));

        //Delete the Matching Book Record for a passed reference
        //db.DeleteBook(bookList.get(0));

        db.GetAllBooks();

        listView = (ListView) findViewById(R.id.booksListView);
        listAdapter = new CustomBookListAdapter(this, bookList);
        listView.setAdapter(listAdapter);

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, bookNames);
        stringArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerControl.setAdapter(stringArrayAdapter);

        spinnerControl.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedBook = adapterView.getItemAtPosition(i).toString();
                if(bookList.contains(selectedBook)){
                    Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_LONG).show();
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}

