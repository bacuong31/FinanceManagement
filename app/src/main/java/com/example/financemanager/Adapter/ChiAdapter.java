package com.example.financemanager.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.financemanager.Object_Perform.ChiTieuPerform;
import com.example.financemanager.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.financemanager.Database.DatabaseManager.dateFormat;

public class ChiAdapter extends BaseAdapter {

    Context context;
    ArrayList<ChiTieuPerform> arr;

    public ChiAdapter(Context context, ArrayList<ChiTieuPerform> arr) {
        this.context = context;
        this.arr = arr;
    }
    public ChiAdapter(){}
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.chi_tieu_layout,null);
        TextView tvNgayChiTieu = (TextView)convertView.findViewById(R.id.tvNgayChiTieu);
        TextView tvTenHoatDongChiTieu = (TextView)convertView.findViewById(R.id.tvTenHoatDongChiTieu);
        TextView tvLuongTien = (TextView)convertView.findViewById(R.id.tvLuongTien);
        ChiTieuPerform c = arr.get(position);
        //String input_date="01/08/2012";
        //System.out.println(c.getNgayChiTieu().toString());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(c.getNgayChiTieu());
        SimpleDateFormat temp = new SimpleDateFormat("d/M/yyyy");
        tvNgayChiTieu.setText(temp.format(calendar.getTime()));
        tvTenHoatDongChiTieu.setText(c.getTenHoatDongChiTieu());
        tvLuongTien.setText(String.valueOf(c.getLuongTien()));
        return convertView;
    }
}
