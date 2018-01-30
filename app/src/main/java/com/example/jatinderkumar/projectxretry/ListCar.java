package com.example.jatinderkumar.projectxretry;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jatinder Kumar on 29-01-2018.
 */

public class ListCar extends BaseAdapter {

    ArrayList<Cars> car;
    Context context;

    public ListCar(ArrayList<Cars> car, Context context) {
        this.car = car;
        this.context = context;
    }

    @Override
    public int getCount() {
        return car.size();
    }

    @Override
    public Object getItem(int position) {
        return car.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cars cars = (Cars) getItem(position);
        Activity activity =(Activity) context;
        LayoutInflater layout = activity.getLayoutInflater();
        View v = layout.inflate(R.layout.listbox,null);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageCar);
        TextView textView =(TextView) v.findViewById(R.id.textCarName);
        TextView textView1 =(TextView) v.findViewById(R.id.carDescription);
        imageView.setImageResource(cars.getCarImage());
        textView.setText(cars.getCarName());
        textView1.setText(cars.getCarDetails());

        return v;



    }
}
