package com.example.matthewgardner.weatherapp;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.matthewgardner.weatherapp.Models.Forecast;
import java.util.ArrayList;

/**
 * Created by matthewgardner on 1/5/18.
 */

public class ForecastListAdapter extends BaseAdapter {

    /*********** Declare Used Variables *********/
    public Activity activity;
    public ArrayList<Forecast> data;
    private static LayoutInflater inflater=null;
    public Resources res;
    Forecast tempValues=null;

    /*************  CustomAdapter Constructor *****************/
    public ForecastListAdapter(Activity a, ArrayList<Forecast> d, Resources resLocal) {

        /********** Take passed values **********/
        activity = a;
        data=d;
        res = resLocal;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = ( LayoutInflater )activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /******** What is the size of Passed Arraylist Size ************/
    public int getCount() {
        if(data.size()<=0)
            return 0;
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{
        TextView date;
        TextView condition;
        TextView temp;
    }

    /****** Depends upon data size called for each row , Create each ListView row *****/
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ForecastListAdapter.ViewHolder holder;

        if(convertView==null){

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.row_forecast, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ForecastListAdapter.ViewHolder();
            holder.date = (TextView) vi.findViewById(R.id.date);
            holder.temp = (TextView) vi.findViewById(R.id.temp);
            holder.condition = (TextView) vi.findViewById(R.id.condition);

            /************  Set holder with LayoutInflater ************/
            vi.setTag( holder );
        }
        else
            holder=(ForecastListAdapter.ViewHolder)vi.getTag();

        if(!(data.size()<=0)) {

            /***** Get each Model object from Arraylist ********/
            tempValues=null;
            tempValues = (Forecast) data.get( position );

            /************  Set Model values in Holder elements ***********/

            holder.date.setText(tempValues.getDay() + ", " + tempValues.getDate());
            holder.condition.setText(tempValues.getText());
            holder.temp.setText(tempValues.getLow() + " | " + tempValues.getHigh());
        }

        return vi;
    }

}

