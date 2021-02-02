package com.example.financemanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AlignmentSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.financemanager.Database.DatabaseManager;

import java.util.Calendar;

//import Database.DatabaseManager;

public class MainActivity extends AppCompatActivity {
    DatabaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context = this;
        final Intent intent = new Intent(this, ChiTietThongKe.class);
        final Bundle bundle = new Bundle();
        Button btnThongKe = (Button)findViewById(R.id.btnThongKe) ;
        final Boolean[] wait = {false};
        final String[] ThongKe = {""};
        TextView tvTongTien = (TextView)findViewById(R.id.tvTongTien);
        TextView tvChiTieu = (TextView)findViewById(R.id.tvDaChi);
        db = new DatabaseManager(this);
        double TienCon = db.TongTien();
        String tienCon = "Tổng tiền: " + String.valueOf(TienCon);
        System.out.println(tienCon);

        SpannableString spannableString =new SpannableString("Tổng tiền "+String.valueOf(TienCon));
        //spannableString.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvTongTien.setText(tienCon);
        spannableString =new SpannableString("Đã chi: "+String.valueOf(db.TongTienChi()));
        tvChiTieu.setText(spannableString);
        GuiNhacNho();
        db = new DatabaseManager(this);
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which==DialogInterface.BUTTON_POSITIVE)
                        {
                            ThongKe[0] ="Thu";
                            bundle.putString("LoaiThongKe", ThongKe[0]);
                            intent.putExtra("LoaiThongKe", ThongKe[0]);
                                    startActivity(intent);
                        }
                        else
                        {
                            ThongKe[0] ="Chi";
                            bundle.putString("LoaiThongKe", ThongKe[0]);
                            intent.putExtra("LoaiThongKe", ThongKe[0]);
                            startActivity(intent);

                        }

                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Chọn loại thống kê").setPositiveButton("Thống kê thu", dialogClickListener)
                        .setNegativeButton("Thống kê chi", dialogClickListener).show();

                }});


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void GuiNhacNho()
    {
        Calendar calendar = Calendar.getInstance();
        //if (calendar.getTime().compareTo(new Date()) < 0) calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,18);
        calendar.set(Calendar.SECOND,0);
        Intent intent = new Intent(getApplicationContext(),FMNotification.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,10,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void btnQuanLySoOnClick(View view) {
        Intent intent =  new Intent(MainActivity.this,QuanLySo.class);
        startActivity(intent);
    }

    public void btnChoVayOnClick(View view) {
        Intent intent =  new Intent(MainActivity.this,ChiTietVay.class);
        startActivity(intent);
    }

    public void btnMuonNoOnClick(View view) {
        Intent intent =  new Intent(MainActivity.this,ChiTietNo.class);
        startActivity(intent);
    }

    public void btnThuNhapOnClick(View view) {
        Intent intent =  new Intent(MainActivity.this,ChiTietThu.class);
        startActivity(intent);
    }

    public void btnChiTieuOnClick(View view) {
        Intent intent =  new Intent(MainActivity.this,ChiTietKhoanChi.class);
        startActivity(intent);
    }
}
