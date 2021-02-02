package com.example.financemanager.Adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.financemanager.Object_Perform.SoThuChiPerform;
import com.example.financemanager.R;

import java.util.ArrayList;

public class ViTienAdapter extends BaseAdapter {
    Context context;
    ArrayList<SoThuChiPerform> arr;

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
            convertView = inflater.inflate(R.layout.vi_tien_layout,null);
            TextView tvTenViTien = (TextView)convertView.findViewById(R.id.tvTenViTien);
            TextView tvLuongTien = (TextView)convertView.findViewById(R.id.tvLuongTien);
            SoThuChiPerform s = arr.get(position);
            tvTenViTien.setText(s.getTenViTien());
            tvLuongTien.setText(String.valueOf(s.getTienDu()));

        return convertView;
    }

    public ViTienAdapter() {
    }

    public ViTienAdapter(Context context, ArrayList<SoThuChiPerform> arr) {
        this.context = context;
        this.arr = arr;
    }
}
