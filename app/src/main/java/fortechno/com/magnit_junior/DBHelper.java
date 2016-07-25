package fortechno.com.magnit_junior;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by Дмитрий on 24.07.2016.
 */
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
public class DBHelper extends SQLiteOpenHelper implements BaseColumns{
Context context ;
//    private  static  final String CREATE_TABLE = "CREATE TABLE myTable(...)";
    private  static final String DB_NAME = "myDB.db";
    public static final String DB_TABLE = "changesOfRowIdAndGreen";
    public static final String ROW_ID_COLUMN = "row";
    public static final String ROW_GREEN_COLUMN = "green";
    public static final String ROW_RED_COLUMN = "red";
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
        +ROW_RED_COLUMN + " integer);";

    public static  int DB_VERSION=1;

    ContentValues contentValues= new ContentValues();

     DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
    }
    DBHelper (Context context){
        super(context,DB_NAME,null,DB_VERSION);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("ИЛИ БАЗА СОЗДАЁТСЯ ДАЙНГРЕЙД ??????");
        newVersion = oldVersion +1;
        onUpgrade(db, oldVersion, newVersion);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE_SCRIPT);
        System.out.println(" БАЗА СОЗДАЁТСЯ ??????");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w ("SQLite", "Обновляемся с версии " + oldVersion + " на версию " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(db);
    }
}
