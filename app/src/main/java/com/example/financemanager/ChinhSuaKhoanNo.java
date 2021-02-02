package com.example.financemanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.financemanager.Database.DatabaseManager;
import com.example.financemanager.Model.TienNo;
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


public class ChinhSuaKhoanNo extends AppCompatActivity implements View.OnClickListener{
    EditText etSoTienMuon,etGhiChuNo;
    Button btnXoaNo,btnLuuNo,btnChonNgayMuon,btnChonNgayTraNo,btnThemTienNo;
    TextView tvNgayMuon,tvNgayTraNo;
    int MaTienNo;
    double TienMuonBefore, TienMuonAfter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua_khoan_no);
        etSoTienMuon = (EditText)findViewById(R.id.etSoTienMuon);
        etGhiChuNo = (EditText)findViewById(R.id.etGhiChuNo);
        btnXoaNo = (Button)findViewById(R.id.btnXoaNo);
        btnLuuNo = (Button)findViewById(R.id.btnLuuNo);
        btnChonNgayMuon = (Button)findViewById(R.id.btnChonNgayMuon);
        btnChonNgayTraNo = (Button)findViewById(R.id.btnChonNgayTraNo);
        tvNgayMuon = (TextView)findViewById(R.id.tvNgayMuon);
        tvNgayTraNo = (TextView)findViewById(R.id.tvNgayTraNo);
        btnThemTienNo = (Button)findViewById(R.id.btnThemTienNo);
        btnThemTienNo.setOnClickListener(this);
        btnXoaNo.setOnClickListener(this);
        btnLuuNo.setOnClickListener(this);
        btnChonNgayTraNo.setOnClickListener(this);
        btnChonNgayMuon.setOnClickListener(this);
        btnThemTienNo.setEnabled(true);
        btnXoaNo.setEnabled(false);
        btnLuuNo.setEnabled(false);
        if(getIntent().getStringExtra("MaTienNo")!= null){
            MaTienNo = Integer.valueOf(getIntent().getStringExtra("MaTienNo"));
            TienMuonBefore = Double.valueOf(getIntent().getStringExtra("SoTien"));
            loadData();
        }
    }

    private void loadData() {
        etSoTienMuon.setText(getIntent().getStringExtra("SoTien"));
        tvNgayMuon.setText(getIntent().getStringExtra("NgayMuon"));
        tvNgayTraNo.setText(getIntent().getStringExtra("NgayTra"));
        etGhiChuNo.setText(getIntent().getStringExtra("GhiChu"));
        btnThemTienNo.setEnabled(false);
        btnXoaNo.setEnabled(true);
        btnLuuNo.setEnabled(true);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnChonNgayMuon){
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog =  new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    tvNgayMuon.setText(dayOfMonth +  "/" + (month+1) + "/" + year);
                }
            },year,month,dayOfMonth);
            datePickerDialog.show();
        } else if (v.getId() == R.id.btnChonNgayTraNo){
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog =  new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    tvNgayTraNo.setText(dayOfMonth +  "/" + (month+1) + "/" + year);
                }
            },year,month,dayOfMonth);
            datePickerDialog.show();
        } else if (v.getId() == R.id.btnThemTienNo){
            TienNo t = null;
            try {
                t = new TienNo(dateFormat.parse(tvNgayMuon.getText().toString()),dateFormat.parse(tvNgayTraNo.getText().toString()),Double.valueOf(etSoTienMuon.getText().toString()), 1,etGhiChuNo.getText().toString());

            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (DatabaseManager.getInstance(this).insertTienNo(t)) {
                DatabaseManager.getInstance(this).tangTienTrongVi(t.getLuongTien(),t.getMaViTien());
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }

            finish();
            Intent intent = new Intent(this, ChiTietNo.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnLuuNo){
            TienNo t = null;
            try {
                t = new TienNo(MaTienNo,dateFormat.parse(tvNgayMuon.getText().toString()),dateFormat.parse(tvNgayTraNo.getText().toString()),Double.valueOf(etSoTienMuon.getText().toString()), 1,etGhiChuNo.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            TienMuonAfter = Double.valueOf(etSoTienMuon.getText().toString());

            if (DatabaseManager.getInstance(this).updateTienNo(t)) {
                DatabaseManager.getInstance(this).tangTienTrongVi(TienMuonAfter-TienMuonBefore,t.getMaViTien());
                Toast.makeText(this, "Chỉnh sửa thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Chỉnh sửa thất bại", Toast.LENGTH_SHORT).show();
            }

            finish();
            Intent intent = new Intent(this, ChiTietNo.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnXoaNo){
            if (DatabaseManager.getInstance(this).deleteTienNo(MaTienNo)){
                DatabaseManager.getInstance(this).tangTienTrongVi(-TienMuonBefore,1);
                Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
            }
            finish();
            Intent intent = new Intent(this, ChiTietNo.class);
            startActivity(intent);
        }
    }
}
