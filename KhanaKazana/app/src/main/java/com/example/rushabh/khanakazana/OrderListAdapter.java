package com.example.rushabh.khanakazana;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderListAdapter extends ArrayAdapter<MenuBean> {

    private Context mContext;
    private int mResource;

    public OrderListAdapter(Context context, int resource, ArrayList<MenuBean> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String dish_name=getItem(position).getDish_name();
        String dish_price=Integer.toString(getItem(position).getDish_price());
        String quantity=Integer.toString(getItem(position).getQuantity());
        MenuBean menuBean=new MenuBean(dish_name, Integer.parseInt(dish_price), Integer.parseInt(quantity));

        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        convertView=layoutInflater.inflate(mResource,parent,false);

        TextView c_dish_name=convertView.findViewById(R.id.c_dish_name);
        TextView c_dish_price=convertView.findViewById(R.id.c_dish_price);

        c_dish_name.setText(dish_name);
        c_dish_price.setText(quantity+"x"+dish_price);

        return convertView;
    }
}
