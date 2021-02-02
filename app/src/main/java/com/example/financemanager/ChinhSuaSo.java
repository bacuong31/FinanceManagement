package com.example.financemanager;

import android.content.Intent;
import android.os.Bundle;

import com.example.financemanager.Database.DatabaseManager;
import com.example.financemanager.Model.ViTien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;


public class ChinhSuaSo extends AppCompatActivity implements View.OnClickListener{
    EditText etTenSo, etSoTien, etGhiChu;
    Button btnThemTien, btnXoaSo, btnLuuSo;
    int MaViTien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua_so);
        etTenSo = (EditText)findViewById(R.id.etTenSo);
        etSoTien = (EditText)findViewById(R.id.etSoTien);
        etGhiChu = (EditText)findViewById(R.id.etGhiChu);
        btnThemTien = (Button)findViewById(R.id.btnThemTien);
        btnXoaSo = (Button)findViewById(R.id.btnXoaSo);
        btnLuuSo = (Button)findViewById(R.id.btnLuuSo);
        btnThemTien.setOnClickListener(this);
        btnXoaSo.setOnClickListener(this);
        btnLuuSo.setOnClickListener(this);
        btnXoaSo.setEnabled(false);
        btnLuuSo.setEnabled(false);
        btnThemTien.setEnabled(true);
        if (getIntent().getStringExtra("MaViTien")!=null){
            MaViTien = Integer.valueOf(getIntent().getStringExtra("MaViTien"));
            loadData();
        }
    }

    private void loadData() {
        etTenSo.setText(getIntent().getStringExtra("TenViTien"));
        etSoTien.setText(getIntent().getStringExtra("TienDu"));
        etGhiChu.setText(getIntent().getStringExtra("GhiChu"));
        btnThemTien.setEnabled(false);
        btnXoaSo.setEnabled(true);
        btnLuuSo.setEnabled(true);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnThemTien){
            //System.out.println("btnThemTien");
            ViTien viTien = new ViTien(etTenSo.getText().toString(), Double.valueOf(etSoTien.getText().toString()), etGhiChu.getText().toString());

            if(DatabaseManager.getInstance(this).insertViTien(viTien)){
                Toast.makeText(this,"Thêm thành công",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"Thêm thất bại",Toast.LENGTH_SHORT).show();
            }

            finish();
            Intent intent = new Intent(this,QuanLySo.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnLuuSo){
            ViTien viTien = new ViTien(MaViTien,etTenSo.getText().toString(), Double.valueOf(etSoTien.getText().toString()), etGhiChu.getText().toString());

            if(DatabaseManager.getInstance(this).updateViTien(viTien)){
                Toast.makeText(this,"Chỉnh sửa thành công",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"Chỉnh sửa thất bại",Toast.LENGTH_SHORT).show();
            }

            finish();
            Intent intent = new Intent(this,QuanLySo.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnXoaSo){
            if (MaViTien == 1){
                Toast.makeText(this,"Không thể xóa ví mặc định",Toast.LENGTH_SHORT).show();
            } else {
                ViTien vt = null;
                try {
                    vt = DatabaseManager.getInstance(this).selectViTien(MaViTien);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (DatabaseManager.getInstance(this).softDeleteViTien(String.valueOf(MaViTien))) {
                    DatabaseManager.getInstance(this).tangTienTrongVi(vt.getTienDu(), 1);
                    Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
            finish();
            Intent intent = new Intent(this,QuanLySo.class);
            startActivity(intent);
        }
    }
}
