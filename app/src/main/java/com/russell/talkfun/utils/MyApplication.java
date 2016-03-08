package com.russell.talkfun.utils;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;

/**
 * Created by wq on 15/10/22.
 */
public class MyApplication extends Application {
    private ArrayList<Activity> activities=new ArrayList<Activity>();
    private static MyApplication instance;

    public MyApplication()
    {
    }
    //单例模式中获取唯一的MyApplication实例
    public static MyApplication getInstance()
    {
        if(null == instance)
        {
            instance = new MyApplication();
        }
        return instance;
    }
    //添加Activity到容器中
    public void addActivity(Activity activity)
    {
        activities.add(activity);
    }
    public void deleteActivity(Activity activity){
        activities.remove(activity);
    }
    //finish
    public void exit()
    {
        for(Activity activity:activities){
            activity.finish();
        }
        activities.clear();

    }
}
