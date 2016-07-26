package fortechno.com.magnit_junior;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;

import java.util.Random;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
    TreeMap<Integer, Integer> treeMap = new TreeMap<>();
    MainAdapter adapter;
    ListView lvMain = null;
    SQLiteDatabase mSqLiteDB;
    DBHelper dbHelper;

    public MainActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);


        for (int i = 0; i < 100; i++) {
            treeMap.put(i, 1);
        }

        dbHelper = new DBHelper(this);
        mSqLiteDB = dbHelper.getReadableDatabase();

        Cursor cursor = mSqLiteDB.query(DBHelper.DB_TABLE,
                new String[]{DBHelper.ROW_ID_COLUMN, DBHelper.ROW_GREEN_COLUMN},
                null, null, null, null,null);
        if (cursor.getCount()!=0) {
            cursor.moveToFirst();
            while (cursor.isAfterLast()) {
                int rowId = cursor.getInt(cursor.getColumnIndex(DBHelper.ROW_ID_COLUMN));
                int green = cursor.getInt(cursor.getColumnIndex(DBHelper.ROW_GREEN_COLUMN));
                treeMap.put(rowId, green);
                System.out.println(rowId + " : " + green + "++++++++++++++++++=");
                cursor.moveToNext();
            }
        }
//        dbHelper.close();
//        mSqLiteDB.close();
        cursor.close();
        treeMap.put(1,33);

        lvMain = (ListView) findViewById(R.id.listViewMain);
        adapter = new MainAdapter(treeMap, this);
        if (lvMain != null) {
            lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(getApplicationContext(), ItemInfoActivity.class).putExtra("rowId", position).putExtra("green", treeMap.get(position));
                    startActivity(intent);
                }
            });
        }
        if (lvMain != null)
            lvMain.setAdapter(adapter);
        else
            System.out.println(" MAIN ADAPTER IS NULL ++++++++++++++");

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onRestart() {
        mSqLiteDB = dbHelper.getReadableDatabase();

        Cursor cursor = mSqLiteDB.query(DBHelper.DB_TABLE,
                new String[]{DBHelper.ROW_ID_COLUMN, DBHelper.ROW_GREEN_COLUMN},
                null, null, null, null, null);
        int count = cursor.getCount();
        if (count > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int rowId = cursor.getInt(cursor.getColumnIndex(DBHelper.ROW_ID_COLUMN));
                int green = cursor.getInt(cursor.getColumnIndex(DBHelper.ROW_GREEN_COLUMN));
                treeMap.put(rowId, green);

                System.out.println(rowId + " : " + green + "++++++++++++++++++=");
                cursor.moveToNext();
            }
        }
        else
            System.out.println("cursor.count = 0 !!!!!!!! ?????++++++++++++++++++=");
        treeMap.put(2,11);
        cursor.close();
            adapter.notifyDataSetChanged();
            System.out.println("вызывается ли onResume ?????++++++++++++++++++=");
//        mSqLiteDB.close();

            super.onRestart();

    }

    public void setChanges(Integer rowId, Integer green) {
        treeMap.put(rowId, green);
//        if (lvMain != null){
//            lvMain.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
//        else
//            System.out.println(" MAIN FROM SETTINGS !!!ADAPTER IS NULL ++++++++++++++");


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (item.getItemId() == R.id.action_settings) {
//            startActivityForResult(new Intent(this,SettingsActivity.class),1);
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


}
