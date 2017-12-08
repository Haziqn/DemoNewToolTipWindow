package com.example.haziq.demonewtooltipwindow;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * Created by Haziq on 12/8/2017.
 */

public class ToolTipWindow {
    public static final String TAG = "ToolTipWindow";
    private static final int MSG_DISMISS_TOOLTIP = 100;
    private Context context;
    private PopupWindow popupWindow;
    private View view;
    private LayoutInflater layoutInflater;

    public ToolTipWindow(Context context) {
        Log.d(TAG, "ToolTipWindow: new ToolTipWindow");
        this.context = context;
        popupWindow = new PopupWindow(context);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.tooltip, null);
    }

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message m) {
            Log.d(TAG, "handleMessage: ");
            switch (m.what) {
                case MSG_DISMISS_TOOLTIP:
                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                        break;
                    }
            }
        }
    };

    boolean isToolTipShown() {
        if(popupWindow != null && popupWindow.isShowing()) {
            return true;
        }
        return false;
    }

    void dismissToolTip() {
        Log.d(TAG, "dismissToolTip: ");
        if(popupWindow !=null && popupWindow.isShowing()) {
            Log.d(TAG, "dismissToolTip: dismissed");
            popupWindow.dismiss();
        } else {
            Log.d(TAG, "dismissToolTip: not dismissed");
        }
    }

    void showToolTip(View contentView, String toolTipText) {
        Log.d(TAG, "showToolTip: ");
        popupWindow.setHeight(ActionBar.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ActionBar.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setContentView(view);
        TextView textView = (TextView)view.findViewById(R.id.text);
        textView.setText(toolTipText);

        int screen_pos[] = new int[2];
        Log.d(TAG, "showToolTip: " + String.valueOf(screen_pos[0]) + String.valueOf(screen_pos[1]));
        contentView.getLocationOnScreen(screen_pos);
        Rect _rect = new Rect(screen_pos[0], screen_pos[1], screen_pos[0] + contentView.getWidth(), screen_pos[1] + contentView.getHeight());
        view.measure(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

        int viewWidth = view.getMeasuredWidth();
        int pos_x = _rect.centerX() - (viewWidth / 2);
        int pos_y = _rect.bottom - (_rect.height() / 2);

        popupWindow.showAtLocation(contentView, Gravity.NO_GRAVITY, pos_x, pos_y);

        handler.sendEmptyMessageDelayed(MSG_DISMISS_TOOLTIP, 4000);

    }

}
