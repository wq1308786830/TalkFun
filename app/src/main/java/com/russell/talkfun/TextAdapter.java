package com.russell.talkfun;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by qi on 2015/9/19.
 */
public class TextAdapter extends BaseAdapter {

    private List<ListData> list;
    private Context context;
    private RelativeLayout layout;

    public TextAdapter(List<ListData> list, Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        if (list.get(position).getFlag() == ListData.RECEIVE)
            layout = (RelativeLayout) layoutInflater.inflate(R.layout.left_layout, null);
        if (list.get(position).getFlag() == ListData.SEND)
            layout = (RelativeLayout) layoutInflater.inflate(R.layout.right_layout, null);

        TextView textView = (TextView) layout.findViewById(R.id.tv);
        textView.setText(list.get(position).getContent());
        return layout;
    }
}
