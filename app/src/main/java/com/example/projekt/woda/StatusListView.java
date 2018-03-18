package com.example.projekt.woda;


import android.accessibilityservice.GestureDescription;
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

public class StatusListView extends ArrayAdapter<String>{

    private Activity context;
    private String[] drinks;
    private String[] desc;
    private Integer[] img;
    private Integer[] proc;

    public StatusListView(Activity context,String[] drinks,String[] desc,Integer[] img,Integer[] proc) {
        super(context, R.layout.items2, drinks);
        this.drinks = drinks;
        this.desc = desc;
        this.img = img;
        this.proc = proc;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        ViewHolder viewHolder = null;
        if(r==null){

            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.items2,null,true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) r.getTag();
        }

        viewHolder.drink.setText(drinks[position]);
        viewHolder.status1.setText(desc[position]);
        viewHolder.progressBar.setProgress(proc[position]);
        viewHolder.imageView.setImageResource(img[position]);
        return r;
    }

    private class ViewHolder {
        TextView drink;
        TextView status1;
        ImageView imageView;
        ProgressBar progressBar;
        ViewHolder(View v){
            drink = (TextView) v.findViewById(R.id.drink);
            status1 = (TextView) v.findViewById(R.id.status1);
            imageView = (ImageView) v.findViewById(R.id.imageView2);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        }
    }

}
