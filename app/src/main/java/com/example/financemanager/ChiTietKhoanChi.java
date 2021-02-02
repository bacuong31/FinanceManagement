package com.example.financemanager;

import android.content.Intent;
import android.os.Bundle;




import com.example.financemanager.Adapter.ChiAdapter;
import com.example.financemanager.Database.DatabaseManager;
import com.example.financemanager.Model.HoatDongChiTieu;
import com.example.financemanager.Object_Perform.ChiTieuPerform;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ChiTietKhoanChi extends AppCompatActivity {
    ListView lvChi;
    ArrayList<ChiTieuPerform> arr;
    ChiAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_khoan_chi);

        arr = new ArrayList<>();
        lvChi = (ListView)findViewById(R.id.lvChi);

        try {
            viewData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LayoutInflater inflater = (LayoutInflater)getSystemService(this.LAYOUT_INFLATER_SERVICE);
        View Header = inflater.inflate(R.layout.thongkeheader_view,null,false);
        lvChi.addHeaderView(Header);
        lvChi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =  new Intent(getApplication(),ChinhSuaKhoanChi.class);
                ChiTieuPerform c_perform = arr.get(position-1);
                HoatDongChiTieu ct = new HoatDongChiTieu();
                try {
                    ct = DatabaseManager.getInstance(getApplicationContext()).selectHoatDongChiTieu(c_perform.getMaChiTieu());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(ct.getNgayChiTieu());
                SimpleDateFormat temp = new SimpleDateFormat("dd/MM/yyyy");
                intent.putExtra("MaHoatDongChiTieu",String.valueOf(ct.getMaHoatDongChiTieu()));
                intent.putExtra("TenKhoanChi",ct.getTenChiTieu());
                intent.putExtra("SoTienChi",String.valueOf(ct.getLuongTien()));
                intent.putExtra("NgayChi",temp.format(calendar.getTime()));
                intent.putExtra("Nhom",c_perform.getTenHoatDongChiTieu());
                intent.putExtra("NguonTien",c_perform.getTenViTien());

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
        arr = DatabaseManager.getInstance(this).getAllChiTieuPerform();

        adapter = new ChiAdapter(this,arr);
        lvChi.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void btnThemMucChiTieu_onClick(View view) {



        Intent intent =  new Intent(this,ChinhSuaKhoanChi.class);
        startActivity(intent);
        finish();
    }
}
