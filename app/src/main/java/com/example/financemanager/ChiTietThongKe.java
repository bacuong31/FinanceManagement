package com.example.financemanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.financemanager.Adapter.ChiAdapter;
import com.example.financemanager.Adapter.ThuAdapter;
import com.example.financemanager.Database.DatabaseManager;
import com.example.financemanager.Object_Perform.ChiTieuPerform;
import com.example.financemanager.Object_Perform.ThuNhapPerform;

import java.time.LocalDate;
import java.util.ArrayList;


public class ChiTietThongKe extends AppCompatActivity {
    DatabaseManager DM;
    ListView ThongKeListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_thong_ke);
        DM = new DatabaseManager(this);
        final Button btnTru=(Button)findViewById(R.id.btnTru);
        Button btnCong=(Button)findViewById(R.id.btnCong);
        ThongKeListView = (ListView) findViewById(R.id.lvThongKe);
        Bundle LoaiThongKeBundle = getIntent().getExtras();
        final String LoaiThongKe = LoaiThongKeBundle.getString("LoaiThongKe");
        LocalDate Date       = LocalDate.now();
        final TextView tv =(TextView)findViewById(R.id.tvNamThang);
        final ArrayList<ChiTieuPerform>[] ThongKeChiCursor = new ArrayList[0];
        final ArrayList<ThuNhapPerform>[] ThongKeThuCursor = new ArrayList[0];
        final ArrayAdapter[] cursorAdapter = new ArrayAdapter[1];
        final int[] Thang = {Date.getMonthValue()};
        final int[] Nam = {Date.getYear()};
        final ChiAdapter[] adapter = new ChiAdapter[1];
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(this.LAYOUT_INFLATER_SERVICE);

        //Add Header View
        View headerViewchi = inflater.inflate(R.layout.thongkeheader_view, null, false);
        View headerViewthu = inflater.inflate(R.layout.thongkethuheader, null, false);

        tv.setText("Tháng "+Thang[0]+"/"+Nam[0]);
        final ArrayList<ChiTieuPerform>[] a = new ArrayList[1];
        final ArrayList<ThuNhapPerform>[] Thu = new ArrayList[1];
        final ThuAdapter[] adapterThu = new ThuAdapter[1];
        if(LoaiThongKe.contentEquals("Thu"))
        {
            ThongKeListView.addHeaderView(headerViewthu);//Add view to list view as header view
            Thu[0] = DM.getThuNhapTheoThang(Thang[0],Nam[0]);
            adapterThu[0] = new ThuAdapter(this, Thu[0]);
            ThongKeListView.setAdapter(adapterThu[0]);
            btnCong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Thu cong");
                    Thang[0]++;
                    if (Thang[0]>12) {
                        Thang[0] = 1;
                        Nam[0]++;
                    }
                    tv.setText("Tháng "+Thang[0]+"/"+Nam[0]);
                    Thu[0] = DM.getThuNhapTheoThang(Thang[0],Nam[0]);
                    adapterThu[0] = new ThuAdapter(getApplicationContext(), Thu[0]);
                    ThongKeListView.setAdapter(adapterThu[0]);
                }});
            btnTru.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Thu Tru");
                    Thang[0]--;
                    if (Thang[0]<1) {
                        Thang[0] = 12;
                        Nam[0]--;
                    }
                    tv.setText("Tháng "+Thang[0]+"/"+Nam[0]);
                    Thu[0] = DM.getThuNhapTheoThang(Thang[0],Nam[0]);
                    adapterThu[0] = new ThuAdapter(getApplicationContext(), Thu[0]);
                    ThongKeListView.setAdapter(adapterThu[0]);
                }
            });

        }
        else
        {
            ThongKeListView.addHeaderView(headerViewchi);//Add view to list view as header view
            a[0] = DM.getHoatDongChiTieuTheoThang(Thang[0],Nam[0]);
            adapter[0] = new ChiAdapter(getApplicationContext(),a[0]);
            ThongKeListView.setAdapter(adapter[0]);
            btnCong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Thang[0]++;
                    if (Thang[0]>12) {
                        Thang[0] = 1;
                        Nam[0]++;
                    }
                    tv.setText("Tháng "+Thang[0]+"/"+Nam[0]);
                    a[0] = DM.getHoatDongChiTieuTheoThang(Thang[0],Nam[0]);
                    adapter[0] = new ChiAdapter(getApplicationContext(),a[0]);
                    ThongKeListView.setAdapter(adapter[0]);
                }});
            btnTru.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Chi Tru");
                    Thang[0]--;
                    if (Thang[0]<1) {
                        Thang[0] = 12;
                        Nam[0]--;
                    }
                    tv.setText("Tháng "+Thang[0]+"/"+Nam[0]);
                    a[0] = DM.getHoatDongChiTieuTheoThang(Thang[0],Nam[0]);
                    adapter[0] = new ChiAdapter(getApplicationContext(),a[0]);
                    ThongKeListView.setAdapter(adapter[0]);
                }
            });
        }

    }

}
