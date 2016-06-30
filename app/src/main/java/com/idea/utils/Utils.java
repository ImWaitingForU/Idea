package com.idea.utils;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.idea.idea.R;

/**
 * Created by Administrator on 2016/6/30.
 */
public class Utils {

    /**
     * 从Fragment中获取到Activity的Toolbar并设置是否可见
     */
    public static void setToolBarVisibility(Fragment f,int visibility){
        AppCompatActivity appCompatActivity = (AppCompatActivity) f.getActivity ();
        Toolbar toolbar = (Toolbar) appCompatActivity.findViewById (R.id.toolbar_main);
        toolbar.setVisibility (visibility);
    }


}
