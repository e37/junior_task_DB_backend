package fortechno.com.magnit_junior;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Дмитрий on 18.07.2016.
 */
public class ItemInfoActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.item_info);
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
System.out.println(intent.getExtras().getInt("rowId"));
        int row = intent.getExtras().getInt("rowId");
        int green = intent.getExtras().getInt("green");

        TextView tv = (TextView) findViewById(R.id.textViewItemInfo);
        tv.setText(String.valueOf(row));
        Button button = (Button) findViewById(R.id.buttonItemInfo);
        button.setText(String.valueOf(green));
    }
}
