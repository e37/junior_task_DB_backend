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

    public SettingAdapter(TreeMap<Integer, Integer> treeMap, Context context) {
        this.treeMap = treeMap;
        this.green = green;
        this.context = context;
    }

    @Override
    public int getCount() {
        return treeMap.size();
//            return items.size();
    }

    @Override
    public Object getItem(int position) {
        return treeMap.get(position);
//        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        int key = 0;
        if (treeMap != null) {
            for (Integer keyI : treeMap.keySet()) {
                if (treeMap.get(keyI).equals(position)) {
                    key = keyI; //return the first found
                    return key;

                }
            }
        }
        return key;
    }
    //            int key = map.keySet(map.get(position));
//            return treeMap.keySet(String.valueOf(position));


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
        }
        fillView(listItemView, position);

        return listItemView;
    }

    private void fillView(View convertView, int position) {

        TextView tv = (TextView) convertView.findViewById(R.id.textViewOfItem);
        Button button = (Button) convertView.findViewById(R.id.buttonOfItem);
        ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        String[] projection = {DBHelper._ID, DBHelper.ROW_ID_COLUMN, DBHelper.ROW_GREEN_COLUMN};


        Cursor cursor = sqLiteDatabase.query(DBHelper.DB_TABLE, projection,
                null, null, null, null, null);

        if ((cursor !=null)&&(cursor.moveToFirst())) {
            cursor.moveToFirst();
            cursor.close();

        int rowId = cursor.getInt(cursor.getColumnIndex(DBHelper.ROW_ID_COLUMN));
        int green = cursor.getInt(cursor.getColumnIndex(DBHelper.ROW_GREEN_COLUMN));
        treeMap.put(rowId, green);
        System.out.println(rowId + " : " + green + "++++++++++++++++++=");
        System.out.println(" or NULL ??????++++++++++++++++++=");
        }


        dbHelper.close();
        sqLiteDatabase.close();

        tv.setText(String.valueOf(position));
        button.setText(String.valueOf(treeMap.get(position)));
//        progressBar.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);

        if (progressBar.isEnabled()) {
            System.out.println("PROGRESS BAR IS NULL+++++++++++++");
//            System.out.println(Integer.valueOf(String.valueOf(treeMap.get(position))));
        } else
            progressBar.setProgress(Integer.valueOf(String.valueOf(treeMap.get(position))));

    }

}
