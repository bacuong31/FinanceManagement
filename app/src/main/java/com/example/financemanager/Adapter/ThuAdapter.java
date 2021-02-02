package com.example.financemanager.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.financemanager.Object_Perform.ChiTieuPerform;
import com.example.financemanager.Object_Perform.ThuNhapPerform;
import com.example.financemanager.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ThuAdapter extends BaseAdapter {
    Context context;
    ArrayList<ThuNhapPerform> arr;

    public ThuAdapter(Context context, ArrayList<ThuNhapPerform> arr) {
        this.context = context;
        this.arr = arr;
    }
    public ThuAdapter(){}
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
        convertView = inflater.inflate(R.layout.thu_nhap_layout,null);
        TextView tvNgayThuNhap = (TextView)convertView.findViewById(R.id.tvNgayThuNhap);
        TextView tvTenThuNhap = (TextView)convertView.findViewById(R.id.tvTenThuNhap);
        TextView tvLuongTien = (TextView)convertView.findViewById(R.id.tvLuongTien);
        ThuNhapPerform c = arr.get(position);
        //String input_date="01/08/2012";
        //System.out.println(c.getNgayChiTieu().toString());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(c.getNgayThuNhap());
        SimpleDateFormat temp = new SimpleDateFormat("d/M/yyyy");
        tvNgayThuNhap.setText(temp.format(calendar.getTime()));
        tvTenThuNhap.setText(c.getTenThuNhap());
        tvLuongTien.setText(String.valueOf(c.getLuongTien()));
        return convertView;
    }
}
