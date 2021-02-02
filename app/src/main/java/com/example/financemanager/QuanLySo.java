package com.example.financemanager;

import android.content.Intent;
import android.os.Bundle;


import com.example.financemanager.Adapter.ViTienAdapter;
import com.example.financemanager.Database.DatabaseManager;
import com.example.financemanager.Model.ViTien;
import com.example.financemanager.Object_Perform.SoThuChiPerform;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.ParseException;
import java.util.ArrayList;

public class QuanLySo extends AppCompatActivity {
    ListView lvSoThuChi;
    ArrayList<SoThuChiPerform> arr;
    ViTienAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_so);
        arr = new ArrayList<>();
        lvSoThuChi = (ListView)findViewById(R.id.lvSoThuChi);

        try {
            viewData();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        lvSoThuChi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =  new Intent(getApplication(),ChinhSuaSo.class);
                SoThuChiPerform s_perform = arr.get(position);
                ViTien s = new ViTien();
                try {
                    s = DatabaseManager.getInstance(getApplicationContext()).selectViTien(s_perform.getMaViTien());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                intent.putExtra("MaViTien",String.valueOf(s.getMaViTien()));
                intent.putExtra("TienDu",String.valueOf(s.getTienDu()));
                intent.putExtra("GhiChu",s.getGhiChu());
                intent.putExtra("TenViTien",s.getTenViTien());
                startActivity(intent);
                finish();
            }
        });
    }
    private void viewData() throws ParseException {
        ArrayList<ViTien> arrViTien = DatabaseManager.getInstance(this).getAllViTien();
        for (ViTien v : arrViTien){
            SoThuChiPerform s = new SoThuChiPerform();
            s.setTenViTien(v.getTenViTien());
            s.setTienDu(v.getTienDu());
            s.setMaViTien(v.getMaViTien());
            //System.out.println(s.getTenViTien());
            arr.add(s);
        }
        adapter = new ViTienAdapter(this,arr);
        lvSoThuChi.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    public void btnThemSoChiTieuOnClick(View view) {
        Intent intent =  new Intent(this,ChinhSuaSo.class);
        startActivity(intent);
        finish();
    }
}
