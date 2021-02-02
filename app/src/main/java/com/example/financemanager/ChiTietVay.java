package com.example.financemanager;

import android.content.Intent;
import android.os.Bundle;


import com.example.financemanager.Adapter.VayAdapter;
import com.example.financemanager.Database.DatabaseManager;
import com.example.financemanager.Model.TienChoMuon;
import com.example.financemanager.Object_Perform.VayPerform;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ChiTietVay extends AppCompatActivity {
    ListView lvChoVay;
    ArrayList<VayPerform> arr;
    VayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_vay);
        arr = new ArrayList<>();
        lvChoVay = (ListView)findViewById(R.id.lvChoVay);
        try {
            viewData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        lvChoVay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =  new Intent(getApplication(),ChinhSuaKhoanVay.class);
                VayPerform v_perform = arr.get(position);
                TienChoMuon t = new TienChoMuon();
                try {
                    t = DatabaseManager.getInstance(getApplication()).selectTienChoMuon(v_perform.getMaVayMuon());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar calendar_ngayMuon = Calendar.getInstance();
                calendar_ngayMuon.setTime(t.getNgayChoMuon());
                Calendar calendar_ngayTra = Calendar.getInstance();
                calendar_ngayTra.setTime(t.getNgayHenTra());
                SimpleDateFormat temp = new SimpleDateFormat("dd/MM/yyyy");
                intent.putExtra("SoTien",String.valueOf(t.getLuongTien()));

                intent.putExtra("NgayVay",temp.format(calendar_ngayMuon.getTime()));
                intent.putExtra("NgayTra",temp.format(calendar_ngayTra.getTime()));
                intent.putExtra("GhiChu",t.getThongTinChoMuon());
                intent.putExtra("MaTienChoMuon",String.valueOf(t.getMaTienChoMuon()));

                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent(this, MainActivity.class);
        startActivity(returnIntent);
    }
    private void viewData() throws ParseException {
        ArrayList<TienChoMuon> arrTienChoMuon = DatabaseManager.getInstance(this).getAllTienChoMuon();
        for (TienChoMuon t : arrTienChoMuon){
            VayPerform v = new VayPerform();
            v.setMaVayMuon(t.getMaTienChoMuon());
            v.setNgayTra(t.getNgayHenTra());
            v.setNoiDung(t.getThongTinChoMuon());
            v.setSoTien(t.getLuongTien());
            //System.out.println(s.getTenViTien());
            arr.add(v);
        }
        adapter = new VayAdapter(this,arr);
        lvChoVay.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void btnThemKhoanVay_onClick(View view) {
        Intent intent =  new Intent(this,ChinhSuaKhoanVay.class);
        startActivity(intent);
        finish();
    }
}
