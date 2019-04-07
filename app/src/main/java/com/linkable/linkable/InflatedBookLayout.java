package com.linkable.linkable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class InflatedBookLayout extends LinearLayout {
    public InflatedBookLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public InflatedBookLayout(Context context) {
        super(context);

        init(context);
    }
    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.inflated_booklayout,this,true);
    }
}
