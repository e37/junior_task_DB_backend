package fortechno.com.magnit_junior;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by Дмитрий on 18.07.2016.
 */
public class SettingsActivity extends Activity {
    TreeMap<Integer, Integer> treeMap = new TreeMap<>();
    SettingAdapter baseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_setting);
        DBHelper mDBHelper = new DBHelper(this);
        SQLiteDatabase mSqLiteDB = mDBHelper.getReadableDatabase();

        Cursor cursor = mSqLiteDB.query("changesOfRowIdAndGreen",
                new String[]{DBHelper.ROW_ID_COLUMN, DBHelper.ROW_GREEN_COLUMN},
                null, null, null, null, null);

        while (cursor.moveToNext()) {
            int rowId = cursor.getInt(cursor.getColumnIndex(DBHelper.ROW_ID_COLUMN));
            int green = cursor.getInt(cursor.getColumnIndex(DBHelper.ROW_GREEN_COLUMN));
            treeMap.put(rowId, green);
            System.out.println(rowId + " : " + green + "+++++ACTIVITY SETTING+++++=");
        }
        mDBHelper.close();
        mSqLiteDB.close();
        cursor.close();

        baseAdapter = new SettingAdapter(treeMap, this);


        ListView lvChangedItems = (ListView) findViewById(R.id.listViewSetting);

        lvChangedItems.setAdapter(baseAdapter);

        baseAdapter.notifyDataSetChanged();

        super.onCreate(savedInstanceState);

    }

    public void AddItem(View view) {

        EditText edRowId = (EditText) findViewById(R.id.editTextRowId);
        EditText edGreen = (EditText) findViewById(R.id.editTextGreen);
        Integer rowId = Integer.valueOf(String.valueOf(edRowId.getText()));
        Integer green = Integer.valueOf(String.valueOf(edGreen.getText()));

        treeMap.put(rowId, green);
        baseAdapter.notifyDataSetChanged();

        ContentValues contentValues = new ContentValues();
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        contentValues.put(DBHelper.ROW_ID_COLUMN, rowId);
        contentValues.put(DBHelper.ROW_GREEN_COLUMN, green);
        db.setVersion(DBHelper.DB_VERSION++);
        db.insert(DBHelper.DB_TABLE, DBHelper.ROW_ID_COLUMN, contentValues);

        dbHelper.close();
        db.close();
    }

}
