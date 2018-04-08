package mdsouza5.hw7and8crudoperation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mdsouza on 4/6/18.
 */

public class SqlHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = "CRUD BOOK";

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

    public boolean InsertBooks(Book bookObj) {
        boolean insertStatus = false;

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_BOOK_TITLE, bookObj.getBookName());
        contentValues.put(COL_BOOK_AUTHOR, bookObj.getAuthorName());

        SQLiteDatabase db = this.getWritableDatabase();
        insertStatus = db.insert(TABLE_NAME, null, contentValues) > 0;
        db.close();

        if (insertStatus == true) {
            Log.d(LOG_TAG, "Insert Successful");
        } else {
            Log.d(LOG_TAG, "Insert Unsuccessful");
        }

        return insertStatus;
    }

    public List<Book> GetAllBooks() {
        List<Book> bookList = new ArrayList<>();
        String GET_ALL_BOOKS_QUERY = String.format("Select * from %s", TABLE_NAME);
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(GET_ALL_BOOKS_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Book bookObj = new Book();
                    bookObj.bookName = cursor.getString(cursor.getColumnIndex(COL_BOOK_TITLE));
                    bookObj.authorName = cursor.getString(cursor.getColumnIndex(COL_BOOK_AUTHOR));
                    bookList.add(bookObj);

                } while (cursor.moveToNext());
            }
        } catch (Exception cursorEx) {
            Log.d("GET ALL BOOKS ERROR", cursorEx.toString());
        } finally {
            if (!cursor.isClosed() || cursor != null) {
                cursor.close();
            }
        }
        return bookList;
    }

    public Integer UpdateBook(Book book) {
        int rowNumToReturn = 0;
        String GET_ROW_ID_QUERY = String.format("Select %s from %s where %s=? limit 1", COL_BOOK_ID, TABLE_NAME, COL_BOOK_AUTHOR);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(GET_ROW_ID_QUERY, new String[]{book.authorName});
        try {
            if(cursor.moveToFirst()){
                do {
                    rowNumToReturn = cursor.getInt(cursor.getColumnIndex(COL_BOOK_ID));
                }while(cursor.moveToNext());
            }

        } catch (Exception cursorEx) {
            Log.d("UPDATE BOOKS ERROR", cursorEx.toString());
        } finally {

        }
        return rowNumToReturn;
    }
}
