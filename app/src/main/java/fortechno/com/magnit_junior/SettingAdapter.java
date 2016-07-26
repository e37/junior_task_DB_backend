package fortechno.com.magnit_junior;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Дмитрий on 20.07.2016.
 */
public class SettingAdapter extends android.widget.BaseAdapter {
    TreeMap<Integer, Integer> treeMap;
    private Context context;
    private int green;
    private int position;
    private Map<Integer, Integer> map;
    Cursor cursor;
    SQLiteDatabase sqLiteDatabase;
    DBHelper dbHelper ;

    public SettingAdapter(TreeMap<Integer, Integer> treeMap, Context context) {
        this.treeMap = treeMap;
        this.context = context;
    }

    @Override
    public int getCount() {
        return treeMap.size();
//            return items.size();
    }

    @Override
    public Object getItem(int position) {
        Object[] keys = treeMap.keySet().toArray();
        int item =  treeMap.get( keys[position]);
        return item;
//        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        Object[] keys = treeMap.keySet().toArray();
        System.out.println( treeMap.keySet().toArray());
        int key = (int) keys[position];
        return key;
    }

//        if (treeMap != null) {
//            for (Integer  keyI : treeMap.keySet().toArray()) {
//                if (treeMap.get(position).equals(position)) {
////                   key = keyI; //return the first found
////                    key = keyI+1;
//                    return keyI;
//                }
//                key = keyI;
//
//            }
//
//        }

    //            int key = map.keySet(map.get(position));
//            return treeMap.keySet(String.valueOf(position));


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
        }
//       dbHelper = new DBHelper(context);
//         sqLiteDatabase = dbHelper.getReadableDatabase();



        fillView(listItemView, position);

        return listItemView;
    }

    private void fillView(View convertView, int position) {

        TextView tv = (TextView) convertView.findViewById(R.id.textViewOfItem);
        Button button = (Button) convertView.findViewById(R.id.buttonOfItem);
        ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);
//        DBHelper dbHelper = new DBHelper(context);
//        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
//
//        String[] projection = {DBHelper._ID, DBHelper.ROW_ID_COLUMN, DBHelper.ROW_GREEN_COLUMN};
//
//
//        Cursor cursor = sqLiteDatabase.query(DBHelper.DB_TABLE, projection,
//                null, null, null, null, null);

//        if ((cursor != null) && (cursor.moveToFirst())) {
//        String[] projection = {DBHelper._ID, DBHelper.ROW_ID_COLUMN, DBHelper.ROW_GREEN_COLUMN};
//
//
//        Cursor cursor = sqLiteDatabase.query(DBHelper.DB_TABLE, projection,
//                null, null, null, null, null);
//        cursor.moveToFirst();

//            int rowId = cursor.getInt(cursor.getColumnIndex(DBHelper.ROW_ID_COLUMN));
//            int green = cursor.getInt(cursor.getColumnIndex(DBHelper.ROW_GREEN_COLUMN));
//            treeMap.put(rowId, green);
//            System.out.println(rowId + " : " + green + "+++++FILL VIEW++++++=");
//            cursor.close();
//        }


//        dbHelper.close();
//        sqLiteDatabase.close();

        tv.setText(String.valueOf(getItemId(position)));
        button.setText(String.valueOf(getItem(position)));
        progressBar.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
progressBar.setProgress((Integer) getItem(position));
//        if (progressBar.isEnabled()) {
//            System.out.println("PROGRESS BAR IS NULL+++++++++++++");
////            System.out.println(Integer.valueOf(String.valueOf(treeMap.get(position))));
//        } else
//            progressBar.setProgress(Integer.valueOf(String.valueOf(treeMap.get(position))));

    }

}
