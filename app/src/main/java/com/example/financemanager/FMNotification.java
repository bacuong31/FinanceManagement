package com.example.financemanager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.financemanager.Database.DatabaseManager;

import java.time.LocalDate;


public class FMNotification extends BroadcastReceiver {
    Context MainContext;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        MainContext=context;
        LocalDate ldDate = LocalDate.now();
        int Ngay = ldDate.getDayOfMonth();
        int Thang = ldDate.getMonthValue();
        int Nam = ldDate.getYear();
        LocalDate nhacnho = ldDate.minusDays(5);
        int NgayNhacNho = nhacnho.getDayOfMonth();
        int ThangNhacNho = nhacnho.getMonthValue();
        int NamNhacNho = nhacnho.getYear();
        String sdate =Ngay+"/"+ Thang+"/"+  Nam;
        String datesoon=NgayNhacNho+"/"+ ThangNhacNho+"/"+ NamNhacNho;
        taoChannel();
        Test();
        NhacDoiNo(sdate);
        NhacTraNo(sdate);
        NhacDoiNoTruoc5Ngay(datesoon);
        NhacTraNoTruoc5Ngay(datesoon);
    }
    public void Test()
    {
        NotificationCompat.Builder ncBuilder = new NotificationCompat.Builder(MainContext,  "CN1")
                .setSmallIcon(R.drawable.im)
                .setContentTitle("Nhắc trả nợ")
                .setContentText("contentNhacTraNo")
                .setAutoCancel(true);
        NotificationManagerCompat manager = NotificationManagerCompat.from(MainContext);
        manager.notify(2,ncBuilder.build());
        System.out.println("YES");
    }
    private  void taoChannel()
    {
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            CharSequence name =   MainContext.getString(R.string.channel_name);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel =new NotificationChannel("CN1",name,importance);
            channel.setDescription("Khong co");
            NotificationManager NM  =  MainContext.getSystemService(NotificationManager.class);
            NM.createNotificationChannel(channel);
        }
    }
    public void NhacTraNo(String date)
    {
        DatabaseManager DM = new DatabaseManager(MainContext);
        int notification_id =1;
        Cursor NhacTraNo = DM.NhacTraNo(date);
        if (NhacTraNo!=null)
            while (!NhacTraNo.isAfterLast())
            {
                String TrangThai=NhacTraNo.getString(NhacTraNo.getColumnIndex("TrangThai"));
                if (TrangThai.contentEquals("1"))
                {
                    float LuongTien=NhacTraNo.getFloat(NhacTraNo.getColumnIndex("LuongTien"));
                    String MaTienNo=NhacTraNo.getString(NhacTraNo.getColumnIndex("MaTienNo"));
                    String MaViTien=NhacTraNo.getString(NhacTraNo.getColumnIndex("MaViTien"));
                    String NgayHenTra=NhacTraNo.getString(NhacTraNo.getColumnIndex("NgayHenTra"));
                    String NgayMuonNo=NhacTraNo.getString(NhacTraNo.getColumnIndex("NgayMuonNo"));
                    String ThongTinMuonNo=NhacTraNo.getString(NhacTraNo.getColumnIndex("ThongTinMuonNo"));
                    Intent intent = new Intent(MainContext, ChinhSuaKhoanNo.class);
                    intent.putExtra("MaTienNo",MaTienNo);
                    intent.putExtra("LuongTien",LuongTien);
                    intent.putExtra("MaViTien",MaViTien);
                    intent.putExtra("NgayHenTra",NgayHenTra);
                    intent.putExtra("NgayMuonNo",NgayMuonNo);
                    intent.putExtra("ThongTinMuonNo",ThongTinMuonNo);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    PendingIntent pendingIntent = PendingIntent.getActivity(MainContext,0,intent,0);
                    String contentNhacTraNo="Bạn mượn "+ThongTinMuonNo+" "+LuongTien+" vào ngày "+NgayMuonNo+" và hẹn trả vào hôm nay. Nhớ trả tiền nhé";
                    NotificationCompat.Builder ncBuilder = new NotificationCompat.Builder(MainContext,  "CN1")
                            .setSmallIcon(R.drawable.im)
                            .setContentTitle("Nhắc trả nợ")
                            .setContentText(contentNhacTraNo)
                            .setOnlyAlertOnce(true)
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true);
                    NotificationManagerCompat manager = NotificationManagerCompat.from(MainContext);
                    manager.notify(notification_id,ncBuilder.build());
                    DM.updateTrangThaiTienNo("2",MaTienNo);
                }
                NhacTraNo.moveToNext();
            }

    }
    public void NhacDoiNo(String date) {
        int notification_id = 2;
        DatabaseManager DM = new DatabaseManager(MainContext);
        Cursor NhacDoiNo = DM.NhacDoiNo(date);
        if (NhacDoiNo!=null)
        {
            while (!NhacDoiNo.isAfterLast()) {
                String TrangThai=NhacDoiNo.getString(NhacDoiNo.getColumnIndex("TrangThai"));
                if (TrangThai.contentEquals("1"))
                {
                    float LuongTien = NhacDoiNo.getFloat(NhacDoiNo.getColumnIndex("LuongTien"));
                    String MaTienChoMuon = NhacDoiNo.getString(NhacDoiNo.getColumnIndex("MaTienChoMuon"));
                    String MaViTien = NhacDoiNo.getString(NhacDoiNo.getColumnIndex("MaViTien"));
                    String NgayHenTra = NhacDoiNo.getString(NhacDoiNo.getColumnIndex("NgayHenTra"));
                    String NgayMuonNo = NhacDoiNo.getString(NhacDoiNo.getColumnIndex("NgayMuonNo"));
                    String ThongTinChoMuon = NhacDoiNo.getString(NhacDoiNo.getColumnIndex("ThongTinChoMuon"));
                    String contentDoiNo=ThongTinChoMuon+" nợ bạn "+LuongTien+" vào ngày "+NgayMuonNo+" và hẹn trả vào hôm nay. Nhớ nhắc nhở họ trả tiền nhé";
                    Intent intent = new Intent(MainContext, ChinhSuaKhoanVay.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    PendingIntent pendingIntent = PendingIntent.getActivity(MainContext, 0, intent, 0);
                    NotificationCompat.Builder notify = new NotificationCompat.Builder(MainContext, "CN1")
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setOnlyAlertOnce(true)
                            .setContentTitle("Nhắc đòi nợ")
                            .setContentText(contentDoiNo)
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true);
                    NotificationManagerCompat manager = NotificationManagerCompat.from(MainContext);
                    manager.notify(notification_id, notify.build());
                    DM.updateTrangThaiTienChoMuon("2",MaTienChoMuon);
                }
                NhacDoiNo.moveToNext();
            }
            ;

        }
        ;
    }
    public void NhacTraNoTruoc5Ngay(String date)
    {
        DatabaseManager DM = new DatabaseManager(MainContext);
        int notification_id =1;
        Cursor NhacTraNo = DM.NhacTraNo(date);
        if (NhacTraNo!=null)
            while (!NhacTraNo.isAfterLast())
            {
                String TrangThai=NhacTraNo.getString(NhacTraNo.getColumnIndex("TrangThai"));
                if ( TrangThai.contentEquals("0"))
                {
                    float LuongTien=NhacTraNo.getFloat(NhacTraNo.getColumnIndex("LuongTien"));
                    String MaTienNo=NhacTraNo.getString(NhacTraNo.getColumnIndex("MaTienNo"));
                    String MaViTien=NhacTraNo.getString(NhacTraNo.getColumnIndex("MaViTien"));
                    String NgayHenTra=NhacTraNo.getString(NhacTraNo.getColumnIndex("NgayHenTra"));
                    String NgayMuonNo=NhacTraNo.getString(NhacTraNo.getColumnIndex("NgayMuonNo"));
                    String ThongTinMuonNo=NhacTraNo.getString(NhacTraNo.getColumnIndex("ThongTinMuonNo"));
                    String contentNhacTraNoSom="Bạn mượn "+ThongTinMuonNo+" "+LuongTien+" vào ngày "+NgayMuonNo+" và hẹn trả vào "+NgayHenTra+". Nhớ trả tiền đúng hạn nhé";
                    Intent intent = new Intent(MainContext, ChinhSuaKhoanNo.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    PendingIntent pendingIntent = PendingIntent.getActivity(MainContext,0,intent,0);
                    NotificationCompat.Builder ncBuilder = new NotificationCompat.Builder(MainContext,  "CN1")
                            .setSmallIcon(R.drawable.im)
                            .setContentTitle("Nhắc trả nợ sớm")
                            .setOnlyAlertOnce(true)
                            .setContentText(contentNhacTraNoSom)
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                            .setContentIntent(pendingIntent)
                            .setAutoCancel(true);
                    NotificationManagerCompat manager = NotificationManagerCompat.from(MainContext);
                    manager.notify(notification_id,ncBuilder.build());
                    DM.updateTrangThaiTienNo("1",MaTienNo);
                }
                NhacTraNo.moveToNext();
            }

    }
    public void NhacDoiNoTruoc5Ngay(String date)
    {
        int notification_id =2;
            DatabaseManager DM = new DatabaseManager(MainContext);
            Cursor NhacDoiNo=DM.NhacDoiNo(date);
            if (NhacDoiNo!=null)
            {
                while (!NhacDoiNo.isAfterLast())
                {
                    String TrangThai=NhacDoiNo.getString(NhacDoiNo.getColumnIndex("TrangThai"));
                    if (TrangThai.contentEquals("0")) {
                        float LuongTien=NhacDoiNo.getFloat(NhacDoiNo.getColumnIndex("LuongTien"));
                        String MaTienChoMuon=NhacDoiNo.getString(NhacDoiNo.getColumnIndex("MaTienChoMuon"));
                        String MaViTien=NhacDoiNo.getString(NhacDoiNo.getColumnIndex("MaViTien"));
                        String NgayHenTra=NhacDoiNo.getString(NhacDoiNo.getColumnIndex("NgayHenTra"));
                        String NgayMuonNo=NhacDoiNo.getString(NhacDoiNo.getColumnIndex("NgayMuonNo"));
                        String ThongTinChoMuon=NhacDoiNo.getString(NhacDoiNo.getColumnIndex("ThongTinChoMuon"));
                        String contentDoiNoSom=ThongTinChoMuon+" nợ bạn "+LuongTien+" vào ngày "+NgayMuonNo+" và hẹn trả vào "+NgayHenTra+". Nhớ nhắc nhở họ trả tiền nhé";
                        Intent intent = new Intent(MainContext, ChinhSuaKhoanVay.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        PendingIntent pendingIntent = PendingIntent.getActivity(MainContext,0,intent,0);
                        NotificationCompat.Builder notify = new NotificationCompat.Builder(MainContext,"CN1")
                                .setSmallIcon(R.drawable.ic_launcher_background)
                                .setContentTitle("Nhắc đòi nợ sớm")
                                .setOnlyAlertOnce(true)
                                .setContentText("contentDoiNoSom")
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                .setContentIntent(pendingIntent)
                                .setAutoCancel(true);
                        NotificationManagerCompat manager = NotificationManagerCompat.from(MainContext);
                        manager.notify(notification_id,notify.build());
                        DM.updateTrangThaiTienNo("1",MaTienChoMuon);
                    }
                    NhacDoiNo.moveToNext();
                }

            }
    }
}
