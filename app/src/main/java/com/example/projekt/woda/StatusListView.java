package com.example.projekt.woda;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class StatusListView extends ArrayAdapter<String>{

    private Activity context;
    private ArrayList<String> drinks;
    private ArrayList<String> desc;
    private ArrayList<Integer> img;

    public StatusListView(Activity context, ArrayList<String> drinks, ArrayList<String> desc, ArrayList<Integer> img) {
        super(context, R.layout.daily_data_layoyt, drinks);
        this.drinks = drinks;
        this.desc = desc;
        this.img = img;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        ViewHolder viewHolder = null;
        if(r==null){

            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.daily_data_layoyt,null,true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) r.getTag();
        }

        viewHolder.drink.setText(drinks.get(position));
        viewHolder.status1.setText(desc.get(position));
        viewHolder.imageView.setImageResource(img.get(position));
        return r;
    }

    private class ViewHolder {
        TextView drink;
        TextView status1;
        ImageView imageView;
        ViewHolder(View v){
            drink = v.findViewById(R.id.drink);
            status1 = v.findViewById(R.id.status1);
            imageView = v.findViewById(R.id.imageView2);
        }
    }

}
