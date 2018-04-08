package mdsouza5.hw7and8crudoperation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //Logcat Default;
    private static final String LOG_TAG = "BOOKS CRUD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SqlHelper db = new SqlHelper(this);
        db.insertBooks(new Book("Professional Android 4 Application Development", "Reto Meier"));
        db.insertBooks(new Book("Beginning Android 4 Application Development", "WeiMeng Lee"));
        db.insertBooks(new Book("Programming Android", "Wallace Jackson"));
        db.insertBooks(new Book("Hello, Android", "Wallace Jackson"));
    }
}

