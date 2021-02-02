package com.example.financemanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.financemanager.Database.DatabaseManager;
import com.example.financemanager.Model.ThuNhap;
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

public class ChinhSuaKhoanThu extends AppCompatActivity implements View.OnClickListener{

    EditText etTenThu, etSoTienThu, etGhiChuThu;
    Button btnXoaThu, btnLuuThu, btnChonNgay, btnThemThu;
    Spinner spVitien2;
    TextView tvNgayThu_Ngay;
    ArrayList<ViTien> arrViTien;
    ArrayList<String> tenViTien;
    int MaThuNhap;
    int MaViTienBefore;
    double tienThuBefore;
    double tienThuAfter;
    public ChinhSuaKhoanThu() throws ParseException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua_khoan_thu);
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
        etTenThu = (EditText)findViewById(R.id.etTenThu);
        etSoTienThu = (EditText)findViewById(R.id.etSoTienThu);
        tvNgayThu_Ngay = (TextView)findViewById(R.id.tvNgayThu_Ngay);
        btnChonNgay = (Button)findViewById(R.id.btnChonNgay);
        //dNgayThu = (EditText)findViewById(R.id.dNgayThu);
        spVitien2 = (Spinner)findViewById(R.id.spVitien2);
        etGhiChuThu = (EditText)findViewById(R.id.etGhiChuThu);
        btnXoaThu = (Button)findViewById(R.id.btnXoaThu);
        btnLuuThu = (Button)findViewById(R.id.btnLuuThu);
        btnThemThu = (Button)findViewById(R.id.btnThemThu);
        btnThemThu.setOnClickListener(this);
        btnXoaThu.setOnClickListener(this);
        btnLuuThu.setOnClickListener(this);
        btnChonNgay.setOnClickListener(this);
        btnXoaThu.setEnabled(false);
        btnLuuThu.setEnabled(false);
        btnThemThu.setEnabled(true);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,tenViTien);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spVitien2.setAdapter(arrayAdapter);
        if (getIntent().getStringExtra("MaThuNhap")!=null) {
            MaThuNhap = Integer.valueOf(getIntent().getStringExtra("MaThuNhap"));
            tienThuBefore = Double.valueOf(getIntent().getStringExtra("SoTienThu"));
            MaViTienBefore = DatabaseManager.getInstance(this).getMaViTienTheoTen(getIntent().getStringExtra("ViTien"));
            loadData();

        }
    }

    private void loadData() {
        btnThemThu.setEnabled(false);
        btnXoaThu.setEnabled(true);
        btnLuuThu.setEnabled(true);
        etTenThu.setText(getIntent().getStringExtra("TenKhoanThu"));
        etSoTienThu.setText(getIntent().getStringExtra("SoTienThu"));
        tvNgayThu_Ngay.setText(getIntent().getStringExtra("NgayThu"));
        spVitien2.setSelection(tenViTien.indexOf(getIntent().getStringExtra("ViTien")));
//        intent.putExtra("TenKhoanThu", t.getTenThuNhap());
        //   intent.putExtra("SoTienThu", String.valueOf(t.getLuongTien()));
        // intent.putExtra("NgayThu", temp.format(calendar.getTime()));
        //intent.putExtra("ViTien", t_perform.getTenViTien());
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnXoaThu){
            if (DatabaseManager.getInstance(this).deleteThuNhap(String.valueOf(MaThuNhap))){
                DatabaseManager.getInstance(this).tangTienTrongVi(-tienThuBefore,MaViTienBefore);
                Toast.makeText(this,"Xóa thành công",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,"Xóa thất bại",Toast.LENGTH_SHORT).show();
            }
            finish();
            Intent intent = new Intent(this, ChiTietThu.class);
            startActivity(intent);

        } else if (v.getId() == R.id.btnThemThu){
            ThuNhap t = null;
            try {
                t = new ThuNhap(etTenThu.getText().toString(),Double.valueOf(etSoTienThu.getText().toString()),dateFormat.parse(tvNgayThu_Ngay.getText().toString()),DatabaseManager.getInstance(this).getMaViTienTheoTen(spVitien2.getSelectedItem().toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (DatabaseManager.getInstance(this).insertThuNhap(t)) {
                DatabaseManager.getInstance(this).tangTienTrongVi(t.getLuongTien(),t.getMaViTien());
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }


            finish();
            Intent intent = new Intent(this, ChiTietThu.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnChonNgay){
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog =  new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    tvNgayThu_Ngay.setText(dayOfMonth +  "/" + (month+1) + "/" + year);
                }
            },year,month,dayOfMonth);
            datePickerDialog.show();
        } else if (v.getId() == R.id.btnLuuThu){
            ThuNhap t = null;
            try {
                t = new ThuNhap(MaThuNhap,etTenThu.getText().toString(),Double.valueOf(etSoTienThu.getText().toString()),dateFormat.parse(tvNgayThu_Ngay.getText().toString()),DatabaseManager.getInstance(this).getMaViTienTheoTen(spVitien2.getSelectedItem().toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            tienThuAfter = Double.valueOf(etSoTienThu.getText().toString());
            if (DatabaseManager.getInstance(this).updateThuNhap(t)){
                DatabaseManager.getInstance(this).tangTienTrongVi(tienThuAfter-tienThuBefore,t.getMaViTien());
                Toast.makeText(this,"Chỉnh sửa thành công",Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this,"Chỉnh sửa thất bại",Toast.LENGTH_SHORT).show();
            }
            finish();
            Intent intent = new Intent(this, ChiTietThu.class);
            startActivity(intent);
        }
    }
}
