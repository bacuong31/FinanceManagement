package com.example.financemanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;


import com.example.financemanager.Database.DatabaseManager;
import com.example.financemanager.Model.TienChoMuon;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Calendar;

import static com.example.financemanager.Database.DatabaseManager.dateFormat;


public class ChinhSuaKhoanVay extends AppCompatActivity implements View.OnClickListener {
    Button btnChonNgayVay,btnChonNgayTraVay,btnXoaVay,btnLuuVay,btnThemVay;
    EditText etSoTienVay, etGhiChuVay;
    TextView tvNgayVay,tvNgayTra;
    int MaTienChoMuon;
    double TienVayBefore, TienVayAfter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua_khoan_vay);
        btnChonNgayVay = (Button)findViewById(R.id.btnChonNgayVay);
        btnChonNgayTraVay = (Button)findViewById(R.id.btnChonNgayTraVay);
        btnXoaVay = (Button)findViewById(R.id.btnXoaVay);
        btnLuuVay = (Button)findViewById(R.id.btnLuuVay);
        etSoTienVay = (EditText)findViewById(R.id.etSoTienVay);
        etGhiChuVay = (EditText)findViewById(R.id.etGhiChuVay);
        tvNgayTra = (TextView)findViewById(R.id.tvNgayTra);
        tvNgayVay = (TextView)findViewById(R.id.tvNgayVay);
        btnThemVay = (Button)findViewById(R.id.btnThemVay);
        btnThemVay.setOnClickListener(this);
        btnChonNgayTraVay.setOnClickListener(this);
        btnChonNgayVay.setOnClickListener(this);
        btnXoaVay.setOnClickListener(this);
        btnLuuVay.setOnClickListener(this);
        btnThemVay.setEnabled(true);
        btnLuuVay.setEnabled(false);
        btnXoaVay.setEnabled(false);

        if (getIntent().getStringExtra("MaTienChoMuon") != null){
            MaTienChoMuon = Integer.valueOf(getIntent().getStringExtra("MaTienChoMuon"));
            TienVayBefore = Double.valueOf(getIntent().getStringExtra("SoTien"));

            loadData();
        }
    }

    private void loadData() {
        etSoTienVay.setText(getIntent().getStringExtra("SoTien"));
        tvNgayVay.setText(getIntent().getStringExtra("NgayVay"));
        tvNgayTra.setText(getIntent().getStringExtra("NgayTra"));
        etGhiChuVay.setText(getIntent().getStringExtra("GhiChu"));
        btnThemVay.setEnabled(false);
        btnLuuVay.setEnabled(true);
        btnXoaVay.setEnabled(true);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnChonNgayVay){
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog =  new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    tvNgayVay.setText(dayOfMonth +  "/" + (month+1) + "/" + year);
                }
            },year,month,dayOfMonth);
            datePickerDialog.show();
        } else if (v.getId() == R.id.btnChonNgayTraVay){
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog =  new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    tvNgayTra.setText(dayOfMonth +  "/" + (month+1) + "/" + year);
                }
            },year,month,dayOfMonth);
            datePickerDialog.show();
        } else if (v.getId() == R.id.btnThemVay){
            TienChoMuon t = null;
            try {
                t = new TienChoMuon(dateFormat.parse(tvNgayVay.getText().toString()),dateFormat.parse(tvNgayTra.getText().toString()),Double.valueOf(etSoTienVay.getText().toString()),DatabaseManager.getInstance(this).getMaViTienTheoTen("Mặc định"),etGhiChuVay.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (DatabaseManager.getInstance(this).insertTienChoMuon(t)) {
                DatabaseManager.getInstance(this).tangTienTrongVi(-t.getLuongTien(),t.getMaViTien());
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }


            finish();
            Intent intent = new Intent(this, ChiTietVay.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnLuuVay){
            TienChoMuon t = null;
            try {
                t = new TienChoMuon(MaTienChoMuon,dateFormat.parse(tvNgayVay.getText().toString()),dateFormat.parse(tvNgayTra.getText().toString()),Double.valueOf(etSoTienVay.getText().toString()),DatabaseManager.getInstance(this).getMaViTienTheoTen("Mặc định"),etGhiChuVay.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            TienVayAfter = Double.valueOf(etSoTienVay.getText().toString());
            if (DatabaseManager.getInstance(this).updateTienChoMuon(t)) {
                DatabaseManager.getInstance(this).tangTienTrongVi(TienVayBefore-TienVayAfter,t.getMaViTien());
                Toast.makeText(this, "Chỉnh sửa thành công", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, "Chỉnh sửa thất bại", Toast.LENGTH_SHORT).show();
            }
            finish();
            Intent intent = new Intent(this, ChiTietVay.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnXoaVay){
            if (DatabaseManager.getInstance(this).deleteTienChoMuon(MaTienChoMuon)){
                DatabaseManager.getInstance(this).tangTienTrongVi(TienVayBefore,1);
                Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
            }
            finish();
            Intent intent = new Intent(this, ChiTietVay.class);
            startActivity(intent);
        }
    }
}
