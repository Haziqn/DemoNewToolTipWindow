package com.example.haziq.demonewtooltipwindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    ToolTipWindow toolTipWindow;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolTipWindow = new ToolTipWindow(MainActivity.this);
        button = (Button)findViewById(R.id.btnShowToolTip);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                if(!toolTipWindow.isToolTipShown()) {
                    Log.d(TAG, "onClick: tooltip is not shown" );
                    toolTipWindow.showToolTip(view, "This is a toolTip");
                } else {
                    toolTipWindow.dismissToolTip();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        if(toolTipWindow != null && toolTipWindow.isToolTipShown()){
            toolTipWindow.dismissToolTip();
        }
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}