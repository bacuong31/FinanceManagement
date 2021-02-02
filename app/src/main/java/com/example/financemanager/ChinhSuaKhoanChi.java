package com.example.financemanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;


import com.example.financemanager.Database.DatabaseManager;
import com.example.financemanager.Model.DanhMucChiTieu;
import com.example.financemanager.Model.HoatDongChiTieu;
import com.example.financemanager.Model.ViTien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.financemanager.Database.DatabaseManager.dateFormat;


public class ChinhSuaKhoanChi extends AppCompatActivity implements View.OnClickListener {
    EditText etTenChi, etSoTienChi;
    TextView tvNgayChi;
    Spinner spNguonTien, spChonNhom;
    Button btnXoaChi, btnLuuChi, btnChonNgayChi,btnThemChiTieu;
    double tienChiBefore;
    int MaHoatDongChiTieu;
    int MaViTienBefore;
    ArrayList<ViTien> arrViTien;
    ArrayList<String> tenViTien;
    ArrayList<DanhMucChiTieu> arrDanhMucChiTieu;
    ArrayList<String> tenDanhMucChi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua_khoan_chi);
        try {
            arrViTien = DatabaseManager.getInstance(this).getAllViTien();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tenViTien = new ArrayList<>();
        for (ViTien v : arrViTien){
            String tenVi = v.getTenViTien();
            tenViTien.add(tenVi);
        }
        arrDanhMucChiTieu = DatabaseManager.getInstance(this).getAllDanhMucChiTieu();
        tenDanhMucChi = new ArrayList<>();
        for (DanhMucChiTieu d : arrDanhMucChiTieu){
            String tenDanhMuc = d.getTenChiTieu();
            tenDanhMucChi.add(tenDanhMuc);
        }
        etTenChi = (EditText) findViewById(R.id.etTenChi);
        etSoTienChi = (EditText) findViewById(R.id.etSoTienChi);
        tvNgayChi = (TextView) findViewById(R.id.tvNgayChi);

        spNguonTien = (Spinner) findViewById(R.id.spNguonTien);
        spChonNhom = (Spinner) findViewById(R.id.spChonNhom);
        btnChonNgayChi = (Button)findViewById(R.id.btnChonNgayChi);
        btnXoaChi = (Button) findViewById(R.id.btnXoaChi);
        btnLuuChi = (Button) findViewById(R.id.btnLuuChi);
        btnThemChiTieu = (Button)findViewById(R.id.btnThemChiTieu);
        btnThemChiTieu.setOnClickListener(this);
        btnXoaChi.setOnClickListener(this);
        btnLuuChi.setOnClickListener(this);
        btnChonNgayChi.setOnClickListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,tenViTien);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spNguonTien.setAdapter(arrayAdapter);
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,tenDanhMucChi);
        arrayAdapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spChonNhom.setAdapter(arrayAdapter1);
        btnLuuChi.setEnabled(false);
        btnXoaChi.setEnabled(false);
        btnThemChiTieu.setEnabled(true);
        if (getIntent().getStringExtra("MaHoatDongChiTieu")!=null){
            MaHoatDongChiTieu = Integer.valueOf(getIntent().getStringExtra("MaHoatDongChiTieu"));
            tienChiBefore = Double.valueOf(getIntent().getStringExtra("SoTienChi"));
            MaViTienBefore = DatabaseManager.getInstance(this).getMaViTienTheoTen(getIntent().getStringExtra("NguonTien"));
            loadData();
        }
    }

    private void loadData() {
        etTenChi.setText(getIntent().getStringExtra("TenKhoanChi"));
        etSoTienChi.setText(getIntent().getStringExtra("SoTienChi"));
        tvNgayChi.setText(getIntent().getStringExtra("NgayChi"));
        spChonNhom.setSelection(tenDanhMucChi.indexOf(getIntent().getStringExtra("Nhom")));
        spNguonTien.setSelection(tenViTien.indexOf(getIntent().getStringExtra("NguonTien")));
        btnThemChiTieu.setEnabled(false);
        btnLuuChi.setEnabled(true);
        btnXoaChi.setEnabled(true);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnThemChiTieu) {
            //System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabtnLuuChi");
            //Toast.makeText(this, "Btnluuchi onclick", Toast.LENGTH_SHORT).show();
            HoatDongChiTieu hd = null;
            try {
                hd = new HoatDongChiTieu(dateFormat.parse(tvNgayChi.getText().toString()), Double.valueOf(etSoTienChi.getText().toString()),DatabaseManager.getInstance(this).getMaChiTieuTheoTen(spChonNhom.getSelectedItem().toString()), DatabaseManager.getInstance(this).getMaViTienTheoTen(spNguonTien.getSelectedItem().toString()), etTenChi.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
//            System.out.println(hd.getNgayChiTieu());
//            System.out.println(hd.getLuongTien());
//            System.out.println(hd.getMaChiTieu());
//            System.out.println(hd.getMaHoatDongChiTieu());
//            System.out.println(hd.getTenChiTieu());
            if (DatabaseManager.getInstance(this).insertHoatDongChiTieu(hd)) {
                DatabaseManager.getInstance(this).tangTienTrongVi(-hd.getLuongTien(),hd.getMaViTien());
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }

            finish();
            Intent intent = new Intent(this, ChiTietKhoanChi.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnChonNgayChi){
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog =  new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    tvNgayChi.setText(dayOfMonth +  "/" + (month+1) + "/" + year);
                }
            },year,month,dayOfMonth);
            datePickerDialog.show();
        } else if (v.getId() == R.id.btnLuuChi){
            HoatDongChiTieu hd = null;
            try {
                hd = new HoatDongChiTieu(MaHoatDongChiTieu,dateFormat.parse(tvNgayChi.getText().toString()), Double.valueOf(etSoTienChi.getText().toString()),DatabaseManager.getInstance(this).getMaChiTieuTheoTen(spChonNhom.getSelectedItem().toString()), DatabaseManager.getInstance(this).getMaViTienTheoTen(spNguonTien.getSelectedItem().toString()), etTenChi.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
//            System.out.println(hd.getNgayChiTieu());
//            System.out.println(hd.getLuongTien());
//            System.out.println(hd.getMaChiTieu());
//            System.out.println(hd.getMaHoatDongChiTieu());
//            System.out.println(hd.getTenChiTieu());
            double tienChiAfter = hd.getLuongTien();

            if (DatabaseManager.getInstance(this).updateHoatDongChiTieu(hd)){
                DatabaseManager.getInstance(this).tangTienTrongVi(-(tienChiAfter-tienChiBefore),hd.getMaViTien());
                Toast.makeText(this, "Chỉnh sửa thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Chỉnh sửa thất bại", Toast.LENGTH_SHORT).show();
            }
            finish();
            Intent intent = new Intent(this, ChiTietKhoanChi.class);
            startActivity(intent);

        } else if (v.getId() == R.id.btnXoaChi){
            if(DatabaseManager.getInstance(this).deleteHoatDongChiTieu(String.valueOf(MaHoatDongChiTieu))) {

                DatabaseManager.getInstance(this).tangTienTrongVi(tienChiBefore, MaViTienBefore);
                Toast.makeText(this,"Xóa thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,"Xóa thất bại", Toast.LENGTH_SHORT).show();
            }
            finish();
            Intent intent = new Intent(this, ChiTietKhoanChi.class);
            startActivity(intent);
        }
    }
}
