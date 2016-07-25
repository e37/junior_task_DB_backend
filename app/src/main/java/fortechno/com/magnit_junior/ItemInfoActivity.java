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
        setContentView(R.layout.list_item);
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        int row = intent.getExtras().getInt("rowId");
        int green = intent.getExtras().getInt("green");
        TextView tv = (TextView) findViewById(R.id.textViewItemInfo);
        tv.setText(row);
        Button button = (Button) findViewById(R.id.buttonItemInfo);
        button.setText(green);
    }
}
