package fortechno.com.magnit_junior;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Дмитрий on 24.07.2016.
 */
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
public class DBHelper extends SQLiteOpenHelper implements BaseColumns {
    public static final String DB_TABLE = "changesOfRowIdAndGreen";
    public static final String ROW_ID_COLUMN = "row";
    public static final String ROW_GREEN_COLUMN = "green";
    public static final String ROW_RED_COLUMN = "red";
    //    private  static  final String CREATE_TABLE = "CREATE TABLE myTable(...)";
    private static final String DB_NAME = "myDB.db";
    private static final String DB_CREATE_SCRIPT =
//            "CREATE TABLE"
//            + DB_TABLE + " ("
//            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//            + ROW_ID_COLUMN + " integer, "
//            + ROW_GREEN_COLUMN + " integer )";
            "create table " + DB_TABLE
                    + " (" + BaseColumns._ID + " integer primary key autoincrement, "
                    + ROW_ID_COLUMN + " integer, "
                    + ROW_GREEN_COLUMN + " integer,"
                    + ROW_RED_COLUMN + " integer);";
    public static String DB_PATH = ""; //= conte getDatabasePath(DBNAME).getAbsolutePath();
    public static int DB_VERSION = 1;
    Context context;
    ContentValues contentValues = new ContentValues();
    int lol;

    DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
        DB_PATH = this.context.getDatabasePath(DB_NAME).getAbsolutePath();


    }
private void setDBVersion (int version){
    DB_VERSION=version;

}
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("ИЛИ БАЗА СОЗДАЁТСЯ ДАЙНГРЕЙД ??????");
        newVersion = oldVersion + 1;
        DB_VERSION = newVersion;
        onUpgrade(db, oldVersion, newVersion);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE_SCRIPT);
        System.out.println(" БАЗА СОЗДАЁТСЯ ??????");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        newVersion = oldVersion +1;
        DB_VERSION = newVersion;
        System.out.println(" стараяверсия :" + oldVersion + "   новая версия :" + newVersion);
//        String[] projetion = {_ID, ROW_ID_COLUMN, ROW_GREEN_COLUMN};
//
//        Cursor cursor = db.query(DB_TABLE, projetion, null, null, null, null, null);
//        int position = cursor.getPosition();
//        System.out.println(position);
//        int value = cursor.getInt(cursor.getColumnIndex(ROW_ID_COLUMN));
//        System.out.println("cursor value : " + value);
//        Log.w("SQLite", "Обновляемся с версии " + oldVersion + " на версию " + newVersion);
//        try {
//            copyDataBase();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
//
//        cursor.close();
//        onCreate(db);

    }

    private void copyDataBase() throws IOException {


        InputStream myInput = null;
        try {
            myInput = context.getAssets().open(DB_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String outFileName = String.valueOf(context.getDatabasePath(DB_NAME).getAbsolutePath());

        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];

        int length = 0;

        assert myInput != null;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

}


