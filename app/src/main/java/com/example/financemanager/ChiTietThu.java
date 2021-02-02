package com.example.financemanager;

import android.content.Intent;
import android.os.Bundle;


import com.example.financemanager.Adapter.ThuAdapter;
import com.example.financemanager.Database.DatabaseManager;
import com.example.financemanager.Model.ThuNhap;
import com.example.financemanager.Object_Perform.ThuNhapPerform;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ChiTietThu extends AppCompatActivity {
    ListView lvThu;
    ArrayList<ThuNhapPerform> arr;
    ThuAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_thu);
        arr = new ArrayList<>();
        lvThu = (ListView)findViewById(R.id.lvThu);
        try {
            viewData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        lvThu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =  new Intent(getApplication(),ChinhSuaKhoanThu.class);
                ThuNhapPerform t_perform = arr.get(position-1);
                ThuNhap t = new ThuNhap();
                try {
                    t = DatabaseManager.getInstance(getApplication()).selectThuNhap(t_perform.getMaThuNhap());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(t.getNgayThuNhap());
                SimpleDateFormat temp = new SimpleDateFormat("dd/MM/yyyy");
                intent.putExtra("MaThuNhap",String.valueOf(t.getMaThuNhap()));
                intent.putExtra("TenKhoanThu",t.getTenThuNhap());
                intent.putExtra("SoTienThu",String.valueOf(t.getLuongTien()));
                intent.putExtra("NgayThu",temp.format(calendar.getTime()));
                intent.putExtra("ViTien",t_perform.getTenViTien());
                startActivity(intent);
                finish();

            }
        });
        LayoutInflater inflater = (LayoutInflater)getSystemService(this.LAYOUT_INFLATER_SERVICE);
        View Header = inflater.inflate(R.layout.thongkethuheader,null,false);
        lvThu.addHeaderView(Header);
    }
    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent(this, MainActivity.class);
        startActivity(returnIntent);
    }
    private void viewData() throws ParseException {
        ArrayList<ThuNhap> arrThuNhap = DatabaseManager.getInstance(this).getAllThuNhap();
        for (ThuNhap t : arrThuNhap){
            ThuNhapPerform t_perform = new ThuNhapPerform();
            t_perform.setLuongTien(t.getLuongTien());
            t_perform.setNgayThuNhap(t.getNgayThuNhap());
            t_perform.setTenThuNhap(t.getTenThuNhap());
            t_perform.setMaThuNhap(t.getMaThuNhap());
            t_perform.setTenViTien(t.getTenViTien());
            arr.add(t_perform);
        }
        adapter = new ThuAdapter(this,arr);
        lvThu.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void btnThemKhoanThu_onClick(View view) {
        Intent intent =  new Intent(this,ChinhSuaKhoanThu.class);
        startActivity(intent);
        finish();
    }
}

