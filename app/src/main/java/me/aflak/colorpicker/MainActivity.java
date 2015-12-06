package me.aflak.colorpicker;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
    private ColorPicker cp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cp = (ColorPicker) findViewById(R.id.main_colorPicker);

        findViewById(R.id.main_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorPicker.RGB rgb = cp.getRGBColor();
                String text = cp.getHexColor()+" : "+rgb.R + " " + rgb.G + " " + rgb.B;
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}