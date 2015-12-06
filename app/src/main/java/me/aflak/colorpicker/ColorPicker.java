package me.aflak.colorpicker;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;

/**
 * Created by Omar on 06/12/2015.
 */
public class ColorPicker extends LinearLayout implements SeekBar.OnSeekBarChangeListener{
    private SeekBar red, green, blue;
    private SurfaceView preview;

    public ColorPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ColorPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ColorPicker(Context context) {
        super(context);
        init();
    }

    private void init(){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.color_picker, null, false);

        red = (SeekBar) layout.findViewById(R.id.colorPicker_red);
        green = (SeekBar) layout.findViewById(R.id.colorPicker_green);
        blue = (SeekBar) layout.findViewById(R.id.colorPicker_blue);
        preview = (SurfaceView) layout.findViewById(R.id.colorPicker_preview);

        red.setOnSeekBarChangeListener(this);
        green.setOnSeekBarChangeListener(this);
        blue.setOnSeekBarChangeListener(this);

        red.setProgress(127);
        green.setProgress(127);
        blue.setProgress(127);
        refreshPreview();

        addView(layout, new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    private RGB refreshPreview(){
        RGB color = getRGBColor();
        preview.setBackground(new ColorDrawable(Color.rgb(color.R, color.G, color.B)));
        return color;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        refreshPreview();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        return;
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        return;
    }

    public class RGB{
        public int R;
        public int G;
        public int B;

        public RGB(int R, int G, int B){
            this.R=R;
            this.G=G;
            this.B=B;
        }
    }

    public RGB getRGBColor(){
        RGB color = new RGB(red.getProgress(), green.getProgress(), blue.getProgress());
        return color;
    }

    public void setRGBColor(RGB rgb){
        red.setProgress(rgb.R);
        green.setProgress(rgb.G);
        blue.setProgress(rgb.B);
        refreshPreview();
    }

    public String getHexColor(){
        RGB rgb = getRGBColor();
        String hexR = Integer.toHexString(rgb.R);
        String hexG = Integer.toHexString(rgb.G);
        String hexB = Integer.toHexString(rgb.B);

        hexR=rgb.R<10?"0"+hexR:hexR;
        hexG=rgb.G<10?"0"+hexG:hexG;
        hexB=rgb.B<10?"0"+hexB:hexB;

        return hexR+hexG+hexB;
    }

    public void setHexColor(String hexa){
        int r = Integer.parseInt(hexa.substring(0,2).trim(), 16);
        int g = Integer.parseInt(hexa.substring(2,4).trim(), 16);
        int b = Integer.parseInt(hexa.substring(4,6).trim(), 16);
        setRGBColor(new RGB(r,g,b));
    }
}
