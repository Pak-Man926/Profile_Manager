package app.profile.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper
{
    //DB name and Table
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserManager.db";
    private static final String TABLE_USERS = "USERS";

    //DB columns
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FIRST_NAME ="first_name";
    private static final String COLUMN_SECOND_NAME = "second_name";
    private static final String COLUMN_USERNAME ="username";
    private static final String COLUMN_PASSWORD = "password";

    public DatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String Query = "CREATE TABLE " + TABLE_USERS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_FIRST_NAME + " TEXT, "
                + COLUMN_SECOND_NAME + " TEXT, "
                + COLUMN_USERNAME + " TEXT UNIQUE, "
                + COLUMN_PASSWORD + " TEXT)";
        db.execSQL(Query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    //Inserts Data into the DB
    public boolean registerUsers(String firstName, String secondName, String userName, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_FIRST_NAME, firstName);
        contentValues.put(COLUMN_SECOND_NAME, secondName);
        contentValues.put(COLUMN_USERNAME, userName);
        contentValues.put(COLUMN_PASSWORD, password);

        long Results = db.insert(TABLE_USERS, null, contentValues);
        return Results != -1;
    }

    //Checks if User exits
    public boolean checkUsers(String userName, String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String query ="SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{userName, password});

        boolean isValid = cursor.getCount() > 0;

        cursor.close();

        return isValid;
    }

}
