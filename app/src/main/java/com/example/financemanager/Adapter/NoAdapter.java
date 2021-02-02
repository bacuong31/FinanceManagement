package com.example.financemanager.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.financemanager.Object_Perform.NoPerform;
import com.example.financemanager.Object_Perform.VayPerform;
import com.example.financemanager.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class NoAdapter extends BaseAdapter {
    Context context;
    ArrayList<NoPerform> arr;

    public NoAdapter(Context context, ArrayList<NoPerform> arr) {
        this.context = context;
        this.arr = arr;
    }
    public NoAdapter(){}
    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return arr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.vay_layout,null);
        TextView tvNgayTraNo = (TextView)convertView.findViewById(R.id.tvNgayTraNo);
        TextView tvNoiDungVay = (TextView)convertView.findViewById(R.id.tvNoiDungVay);
        TextView tvTienVay = (TextView)convertView.findViewById(R.id.tvTienVay);
        NoPerform c = arr.get(position);
        //String input_date="01/08/2012";
        //System.out.println(c.getNgayChiTieu().toString());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(c.getNgayTra());
        SimpleDateFormat temp = new SimpleDateFormat("d/M/yyyy");
        tvNgayTraNo.setText(temp.format(calendar.getTime()));
        tvNoiDungVay.setText(c.getNoiDung());
        tvTienVay.setText(String.valueOf(c.getSoTien()));
        return convertView;
    }
}
