package com.mediquei.design;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mediquei.R;

public class ToastCustom extends Toast {

    public static int SUCCESS=1;
    public static int ERROR=3;
    public static int INFO=4;

    public ToastCustom(Context context) {
        super(context);
    }

    public static Toast makeText(Context context,String message,int duration,int type){
        Toast toast = new Toast(context);
        toast.setDuration(duration);

        View layout = LayoutInflater.from(context).inflate(R.layout.toast_custom_layout, null, false);
        TextView l1 = (TextView) layout.findViewById(R.id.toast_text);
        LinearLayout linearLayout=(LinearLayout) layout.findViewById(R.id.toast_type);
        ImageView img=(ImageView) layout.findViewById(R.id.toast_icon);
        l1.setText(message);
        if(type==1)
        { linearLayout.setBackgroundResource(R.drawable.shape_toast_sucess);
            img.setImageResource(R.drawable.shape_icon_sucess);
        }
        else if (type==3)
        { linearLayout.setBackgroundResource(R.drawable.shape_toast_error);
            img.setImageResource(R.drawable.shape_icon_error);
        }
        else if (type==4)
        { linearLayout.setBackgroundResource(R.drawable.shape_toast_info);
            img.setImageResource(R.drawable.shape_icon_info);
        }
        toast.setView(layout);
        return toast;
    }

}