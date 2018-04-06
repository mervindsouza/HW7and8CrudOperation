package mdsouza5.hw7and8crudoperation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

/**
 * Created by mdsouza on 4/6/18.
 */

public class SqlHelper extends SQLiteOpenHelper {

    //Assign Database Name
    private static final String DATABASE_NAME = "BOOKDB";

    //Assign Table Name
    private static final String TABLE_NAME = "books";

    //Assign Columns for Table - books
    private static final String COL_BOOK_ID = "id";
    private static final String COL_BOOK_TITLE = "title";
    private static final String COL_BOOK_AUTHOR = "author";

    //DML for onUpgrade()
    private static final String DROP_TABLE = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);

    //Default Constructor
    public SqlHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //Implemented Methods

    //Configure and enable write ahead logging for atomicity and durability
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
        db.enableWriteAheadLogging();
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_BOOKS_TABLE = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT)",
                TABLE_NAME, COL_BOOK_ID, COL_BOOK_TITLE, COL_BOOK_AUTHOR);
        sqLiteDatabase.execSQL(CREATE_BOOKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int previousVersion, int newVersion) {
        if (previousVersion != newVersion) {
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
        }
    }
}
