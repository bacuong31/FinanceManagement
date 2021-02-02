package com.example.financemanager;

import android.content.Intent;
import android.os.Bundle;




import com.example.financemanager.Adapter.NoAdapter;
import com.example.financemanager.Database.DatabaseManager;
import com.example.financemanager.Model.TienNo;
import com.example.financemanager.Object_Perform.NoPerform;
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

public class ChiTietNo extends AppCompatActivity {
      ListView lvMuonNo;
      ArrayList<NoPerform> arr;
      NoAdapter adapter;
      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_chi_tiet_no);
            arr = new ArrayList<>();
            lvMuonNo = (ListView)findViewById(R.id.lvMuonNo);
            try {
                  viewData();
            } catch (ParseException e) {
                  e.printStackTrace();
            }
            lvMuonNo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                  @Override
                  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent =  new Intent(getApplication(),ChinhSuaKhoanNo.class);
                        NoPerform t_perform = arr.get(position);
                        TienNo t = new TienNo();
                        try {
                              t = DatabaseManager.getInstance(getApplication()).selectTienNo(t_perform.getMaTienNo());
                        } catch (ParseException e) {
                              e.printStackTrace();
                        }
                        Calendar calendar_ngayMuon = Calendar.getInstance();
                        calendar_ngayMuon.setTime(t.getNgayMuonNo());
                        Calendar calendar_ngayTra = Calendar.getInstance();
                        calendar_ngayTra.setTime(t.getNgayHenTra());
                        SimpleDateFormat temp = new SimpleDateFormat("dd/MM/yyyy");
                        intent.putExtra("SoTien",String.valueOf(t.getLuongTien()));
                        //System.out.println(t.getLuongTien());
                        intent.putExtra("NgayMuon",temp.format(calendar_ngayMuon.getTime()));
                        intent.putExtra("NgayTra",temp.format(calendar_ngayTra.getTime()));
                        intent.putExtra("GhiChu",t.getThongTinMuonNo());
                        intent.putExtra("MaTienNo",String.valueOf(t.getMaTienNo()));

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
            ArrayList<TienNo> arrTienNo = DatabaseManager.getInstance(this).getAllTienNo();
            for (TienNo t : arrTienNo){
                  NoPerform v = new NoPerform();
                  v.setMaTienNo(t.getMaTienNo());
                  v.setNgayTra(t.getNgayHenTra());
                  v.setNoiDung(t.getThongTinMuonNo());
                  v.setSoTien(t.getLuongTien());
                  //System.out.println(s.getTenViTien());
                  arr.add(v);
            }
            adapter = new NoAdapter(this,arr);
            lvMuonNo.setAdapter(adapter);
            adapter.notifyDataSetChanged();
      }


      public void btnThemKhoanNo_onClick(View view) {
            Intent intent =  new Intent(this,ChinhSuaKhoanNo.class);
            startActivity(intent);
            finish();
      }
}
