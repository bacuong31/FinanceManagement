package com.example.financemanager.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.financemanager.Model.DanhMucChiTieu;
import com.example.financemanager.Model.HoatDongChiTieu;
import com.example.financemanager.Model.ThuNhap;
import com.example.financemanager.Model.TienChoMuon;
import com.example.financemanager.Model.TienNo;
import com.example.financemanager.Model.ViTien;
import com.example.financemanager.Object_Perform.ChiTieuPerform;
import com.example.financemanager.Object_Perform.ThuNhapPerform;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


public class DatabaseManager extends SQLiteOpenHelper {
    private static DatabaseManager sIntance;
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private SQLiteDatabase database;


    // Database Info
    public static final String DATABASE_NAME = "QuanLyChiTieu.db";
    public static final int DATABASE_VERSION = 7;

    // Tables
    private static final String TABLE_DANHMUCCHITIEU = "DanhMucChiTieu";
    private static final String TABLE_HOATDONGCHITIEU = "HoatDongChiTieu";
    private static final String TABLE_THUNHAP = "ThuNhap";
    private static final String TABLE_TIENCHOMUON = "TienChoMuon";
    private static final String TABLE_TIENNO = "TienNo";
    private static final String TABLE_VITIEN = "ViTien";

    // ViTien Columns
    private static final String COLUMN_VITIEN_MAVITIEN = "MaViTien";
    private static final String COLUMN_VITIEN_TENVITIEN = "TenViTien";
    private static final String COLUMN_VITIEN_GHICHU = "GhiChu";
    private static final String COLUMN_VITIEN_TIENDU = "TienDu";
    private static final String COLUMN_VITIEN_ISACTIVE = "isActive";

    // ThuNhap Columns
    private static final String COLUMN_THUNHAP_MATHUNHAP = "MaThuNhap";
    private static final String COLUMN_THUNHAP_CHUTHICH = "ChuThich";
    private static final String COLUMN_THUNHAP_NGAYTHUNHAP = "NgayThuNhap";
    private static final String COLUMN_THUNHAP_LUONGTIEN = "LuongTien";
    private static final String COLUMN_THUNHAP_TENTHUNHAP = "TenThuNhap";
    private static final String COLUMN_THUNHAP_MAVITIEN = "MaViTien";

    // TienNo Columns
    private static final String COLUMN_TIENNO_MATIENNO = "MaTienNo";
    private static final String COLUMN_TIENNO_NGAYMUONNO = "NgayMuonNo";
    private static final String COLUMN_TIENNO_NGAYHENTRA = "NgayHenTra";
    private static final String COLUMN_TIENNO_LUONGTIEN = "LuongTien";
    private static final String COLUMN_TIENNO_MAVITIEN = "MaViTien";
    private static final String COLUMN_TIENNO_THONGTINMUONNO = "ThongTinMuonNo";
    private static final String COLUMN_TIENNO_TRANGTHAI = "TrangThai";

    // TienChoMuon Columns
    private static final String COLUMN_TIENCHOMUON_MATIENCHOMUON = "MaTienChoMuon";
    private static final String COLUMN_TIENCHOMUON_NGAYCHOMUON = "NgayChoMuon";
    private static final String COLUMN_TIENCHOMUON_NGAYHENTRA = "NgayHenTra";
    private static final String COLUMN_TIENCHOMUON_LUONGTIEN = "LuongTien";
    private static final String COLUMN_TIENCHOMUON_MAVITIEN = "MaViTien";
    private static final String COLUMN_TIENCHOMUON_THONGTINCHOMUON = "ThongTinChoMuon";
    private static final String COLUMN_TIENCHOMUON_TRANGTHAI = "TrangThai";

    // HoatDongChiTieu Columns
    private static final String COLUMN_HOATDONGCHITIEU_MAHOATDONGCHITIEU = "MaHoatDongChiTieu";
    private static final String COLUMN_HOATDONGCHITIEU_TENCHITIEU = "TenChiTieu";
    private static final String COLUMN_HOATDONGCHITIEU_NGAYCHITIEU = "NgayChiTieu";
    private static final String COLUMN_HOATDONGCHITIEU_LUONGTIEN = "LuongTien";
    private static final String COLUMN_HOATDONGCHITIEU_MACHITIEU = "MaChiTieu";
    private static final String COLUMN_HOATDONGCHITIEU_MAVITIEN = "MaViTien";

    // DanhMucChiTieu Columns
    private static final String COLUMN_DANHMUCCHITIEU_MACHITIEU = "MaChiTieu";
    private static final String COLUMN_DANHMUCCHITIEU_TENCHITIEU = "TenChiTieu";

    public DatabaseManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        database = db;
        createDatabase();
        insertData(db);
    }

    private void insertData(SQLiteDatabase db) {
        db.execSQL("INSERT INTO " + TABLE_DANHMUCCHITIEU + "(TenChiTieu) VALUES ('Ăn uống')");
        db.execSQL("INSERT INTO " + TABLE_DANHMUCCHITIEU + "(TenChiTieu) VALUES ('Di chuyển')");
        db.execSQL("INSERT INTO " + TABLE_DANHMUCCHITIEU + "(TenChiTieu) VALUES ('Mua sắm')");
        db.execSQL("INSERT INTO " + TABLE_DANHMUCCHITIEU + "(TenChiTieu) VALUES ('Xăng dầu')");
        db.execSQL("INSERT INTO " + TABLE_DANHMUCCHITIEU + "(TenChiTieu) VALUES ('Du lịch')");
        db.execSQL("INSERT INTO " + TABLE_DANHMUCCHITIEU + "(TenChiTieu) VALUES ('Thể thao')");
        db.execSQL("INSERT INTO " + TABLE_DANHMUCCHITIEU + "(TenChiTieu) VALUES ('Sức khỏe')");
        db.execSQL("INSERT INTO " + TABLE_VITIEN + "(TenViTien,TienDu,GhiChu,isActive) VALUES ('Mặc định', 0.0, 'Tài khoản mặc định',1)");
    }

    private void createDatabase() {

        String CREATE_VITIEN_TABLE = "CREATE TABLE " +TABLE_VITIEN +
                "(" +
                COLUMN_VITIEN_MAVITIEN + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_VITIEN_TENVITIEN + " TEXT, " +
                COLUMN_VITIEN_TIENDU + " REAL, " +
                COLUMN_VITIEN_GHICHU + " TEXT, " +
                COLUMN_VITIEN_ISACTIVE + " INTEGER" +
                //"FOREIGN KEY(" + COLUMN_VITIEN_MAVITIEN + ") REFERENCES " + TABLE_VITIEN + "(" + COLUMN_VITIEN_MAVITIEN + ")" +
                ")";
        String CREATE_DANHMUCCHITIEU_TABLE = "CREATE TABLE " +TABLE_DANHMUCCHITIEU +
                "(" +
                COLUMN_DANHMUCCHITIEU_MACHITIEU + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_DANHMUCCHITIEU_TENCHITIEU + " TEXT" +
                ")";
        String CREATE_HOATDONGCHITIEU_TABLE = "CREATE TABLE " +TABLE_HOATDONGCHITIEU +
                "(" +
                COLUMN_HOATDONGCHITIEU_MAHOATDONGCHITIEU + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_HOATDONGCHITIEU_TENCHITIEU + " TEXT," +
                COLUMN_HOATDONGCHITIEU_NGAYCHITIEU + " TEXT," +
                COLUMN_HOATDONGCHITIEU_LUONGTIEN + " REAL, "  +
                COLUMN_HOATDONGCHITIEU_MACHITIEU + " INTEGER," +
                COLUMN_HOATDONGCHITIEU_MAVITIEN + " INTEGER," +
                "FOREIGN KEY(" + COLUMN_HOATDONGCHITIEU_MAVITIEN + ") REFERENCES " + TABLE_VITIEN + "(" + COLUMN_VITIEN_MAVITIEN + ")," +
                "FOREIGN KEY(" + COLUMN_HOATDONGCHITIEU_MACHITIEU + ") REFERENCES " + TABLE_DANHMUCCHITIEU + "(" + COLUMN_DANHMUCCHITIEU_MACHITIEU + ")" +
                ")";
        String CREATE_THUNHAP_TABLE = "CREATE TABLE " +TABLE_THUNHAP +
                "(" +
                COLUMN_THUNHAP_MATHUNHAP + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_THUNHAP_TENTHUNHAP + " TEXT," +
                COLUMN_THUNHAP_LUONGTIEN + " REAL," +
                COLUMN_THUNHAP_NGAYTHUNHAP + " TEXT," +
                //COLUMN_THUNHAP_CHUTHICH + " TEXT," +
                COLUMN_THUNHAP_MAVITIEN + " INTEGER," +
                "FOREIGN KEY(" + COLUMN_THUNHAP_MAVITIEN + ") REFERENCES " + TABLE_VITIEN + "(" + COLUMN_VITIEN_MAVITIEN + ")" +
                ")";
        String CREATE_TIENNO_TABLE = "CREATE TABLE " + TABLE_TIENNO +
                "(" +
                COLUMN_TIENNO_MATIENNO + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TIENNO_NGAYMUONNO + " TEXT," +
                COLUMN_TIENNO_NGAYHENTRA + " TEXT," +
                COLUMN_TIENNO_LUONGTIEN + " REAL," +
                COLUMN_TIENNO_MAVITIEN + " INTEGER," +
                COLUMN_TIENNO_THONGTINMUONNO + " TEXT," +
                COLUMN_TIENNO_TRANGTHAI + " TEXT," +
                "FOREIGN KEY(" + COLUMN_TIENNO_MAVITIEN + ") REFERENCES " + TABLE_VITIEN + "(" + COLUMN_VITIEN_MAVITIEN + ")" +
                ")";
        String CREATE_TIENCHOMUON_TABLE = "CREATE TABLE " + TABLE_TIENCHOMUON +
                "(" +
                COLUMN_TIENCHOMUON_MATIENCHOMUON + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TIENCHOMUON_NGAYCHOMUON + " TEXT," +
                COLUMN_TIENCHOMUON_NGAYHENTRA + " TEXT," +
                COLUMN_TIENCHOMUON_LUONGTIEN + " REAL," +
                COLUMN_TIENCHOMUON_MAVITIEN + " INTEGER," +
                COLUMN_TIENCHOMUON_THONGTINCHOMUON + " TEXT," +
                COLUMN_TIENCHOMUON_TRANGTHAI+ " TEXT," +
                "FOREIGN KEY(" + COLUMN_TIENCHOMUON_MAVITIEN + ") REFERENCES " + TABLE_VITIEN + "(" + COLUMN_VITIEN_MAVITIEN + ")" +
                ")";
        database.execSQL(CREATE_VITIEN_TABLE);
        database.execSQL(CREATE_DANHMUCCHITIEU_TABLE);
        database.execSQL(CREATE_THUNHAP_TABLE);
        database.execSQL(CREATE_TIENNO_TABLE);
        database.execSQL(CREATE_TIENCHOMUON_TABLE);
        database.execSQL(CREATE_HOATDONGCHITIEU_TABLE);
    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIENCHOMUON);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIENNO);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_THUNHAP);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOATDONGCHITIEU);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_DANHMUCCHITIEU);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_VITIEN);
            onCreate(db);
        }
    }

    public static synchronized DatabaseManager getInstance(Context context){
        if(sIntance == null)
            sIntance = new DatabaseManager(context.getApplicationContext());
        return sIntance;
    }

    //DanhMucChiTieu
    public void insertDanhMucChiTieu(DanhMucChiTieu danhMucChiTieu){
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try{
            ContentValues values = new ContentValues();
            //values.put(COLUMN_DANHMUCCHITIEU_MACHITIEU,danhMucChiTieu.getMaChiTieu());
            values.put(COLUMN_DANHMUCCHITIEU_TENCHITIEU,danhMucChiTieu.getTenChiTieu());

            db.insert(TABLE_DANHMUCCHITIEU,null,values);
            db.setTransactionSuccessful();
        }
        catch (Exception e){
            Log.d("TAG","Error insert");
        }
        finally {
            db.endTransaction();
        }
    }
    public int updateDanhMucChiTieu(DanhMucChiTieu danhMucChiTieu){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DANHMUCCHITIEU_TENCHITIEU,danhMucChiTieu.getTenChiTieu());
        return db.update(TABLE_DANHMUCCHITIEU,values,COLUMN_DANHMUCCHITIEU_MACHITIEU + " = ?",new String[]{String.valueOf(danhMucChiTieu.getMaChiTieu())} );
    }

    public int deleteDanhMucChiTieu(DanhMucChiTieu danhMucChiTieu){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_DANHMUCCHITIEU,COLUMN_DANHMUCCHITIEU_MACHITIEU + " = ?" , new String[]{String.valueOf(danhMucChiTieu.getMaChiTieu())});
    }

    public ArrayList<DanhMucChiTieu> getAllDanhMucChiTieu(){
        ArrayList<DanhMucChiTieu> arr = new ArrayList<DanhMucChiTieu>();
        String sqlQuery = "SELECT * FROM " + TABLE_DANHMUCCHITIEU;
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery(sqlQuery,null);
        if (res.moveToFirst()) {
            do {
                DanhMucChiTieu temp = new DanhMucChiTieu();
                temp.setMaChiTieu(res.getString(res.getColumnIndex(COLUMN_DANHMUCCHITIEU_MACHITIEU)));
                temp.setTenChiTieu(res.getString(res.getColumnIndex(COLUMN_DANHMUCCHITIEU_TENCHITIEU)));
                arr.add(temp);
            } while (res.moveToNext());
        }
        return arr;
    }
    public DanhMucChiTieu selectDanhMucChiTieu(String maChiTieu){
        String sqlQuery = "SELECT * FROM " + TABLE_DANHMUCCHITIEU + " WHERE " + COLUMN_DANHMUCCHITIEU_MACHITIEU + " = " + maChiTieu;
        DanhMucChiTieu temp = new DanhMucChiTieu();
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery(sqlQuery,null);
        res.moveToFirst();
        temp.setMaChiTieu(res.getString(res.getColumnIndex(COLUMN_DANHMUCCHITIEU_MACHITIEU)));
        temp.setTenChiTieu(res.getString(res.getColumnIndex(COLUMN_DANHMUCCHITIEU_TENCHITIEU)));
        return temp;
    }


    //HoatDongChiTieu
    public boolean insertHoatDongChiTieu(HoatDongChiTieu hoatDongChiTieu){
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try{
            ContentValues values = new ContentValues();
            values.put(COLUMN_HOATDONGCHITIEU_MACHITIEU,hoatDongChiTieu.getMaChiTieu());
            values.put(COLUMN_HOATDONGCHITIEU_LUONGTIEN,hoatDongChiTieu.getLuongTien());
            values.put(COLUMN_HOATDONGCHITIEU_TENCHITIEU,hoatDongChiTieu.getTenChiTieu());
            values.put(COLUMN_HOATDONGCHITIEU_MAVITIEN,hoatDongChiTieu.getMaViTien());
            values.put(COLUMN_HOATDONGCHITIEU_NGAYCHITIEU,dateFormat.format(hoatDongChiTieu.getNgayChiTieu()));
            long result = db.insert(TABLE_HOATDONGCHITIEU,null,values);
            db.setTransactionSuccessful();
            return result != -1;
        }
        catch (Exception e){
            Log.d("TAG","Error insert");
        }
        finally {
            db.endTransaction();
        }
        return false;
    }
    public boolean updateHoatDongChiTieu(HoatDongChiTieu hoatDongChiTieu){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_HOATDONGCHITIEU_NGAYCHITIEU,dateFormat.format(hoatDongChiTieu.getNgayChiTieu()));
        values.put(COLUMN_HOATDONGCHITIEU_MAVITIEN,hoatDongChiTieu.getMaViTien());
        values.put(COLUMN_HOATDONGCHITIEU_TENCHITIEU,hoatDongChiTieu.getTenChiTieu());
        values.put(COLUMN_HOATDONGCHITIEU_LUONGTIEN,hoatDongChiTieu.getLuongTien());
        values.put(COLUMN_HOATDONGCHITIEU_MACHITIEU,hoatDongChiTieu.getMaChiTieu());
        long result = -1;
        result = db.update(TABLE_HOATDONGCHITIEU,values,COLUMN_HOATDONGCHITIEU_MAHOATDONGCHITIEU + " = ?",new String[]{String.valueOf(hoatDongChiTieu.getMaHoatDongChiTieu())} );
        return result != -1;
    }

    public boolean deleteHoatDongChiTieu(String maHoatDongChiTieu){
        SQLiteDatabase db = getWritableDatabase();
        long result = -1;
        result = db.delete(TABLE_HOATDONGCHITIEU,COLUMN_HOATDONGCHITIEU_MAHOATDONGCHITIEU + " = ?" , new String[]{maHoatDongChiTieu});
        return result != -1;
    }

    public ArrayList<HoatDongChiTieu> getAllHoatDongChiTieu() throws ParseException {
        ArrayList<HoatDongChiTieu> arr = new ArrayList<HoatDongChiTieu>();
        String sqlQuery = "SELECT * FROM " + TABLE_HOATDONGCHITIEU;
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery(sqlQuery,null);
        if (res.moveToFirst()) {
            do {
                HoatDongChiTieu temp = new HoatDongChiTieu();
                temp.setMaHoatDongChiTieu(res.getInt(res.getColumnIndex(COLUMN_HOATDONGCHITIEU_MAHOATDONGCHITIEU)));
                temp.setLuongTien(res.getDouble(res.getColumnIndex(COLUMN_HOATDONGCHITIEU_LUONGTIEN)));
                temp.setTenChiTieu(res.getString(res.getColumnIndex(COLUMN_HOATDONGCHITIEU_TENCHITIEU)));
                temp.setMaChiTieu(res.getInt(res.getColumnIndex(COLUMN_HOATDONGCHITIEU_MACHITIEU)));
                temp.setNgayChiTieu(dateFormat.parse(res.getString(res.getColumnIndex(COLUMN_HOATDONGCHITIEU_NGAYCHITIEU))));
                temp.setMaViTien(res.getInt(res.getColumnIndex(COLUMN_HOATDONGCHITIEU_MAVITIEN)));
                arr.add(temp);
            } while (res.moveToNext());
        }
        return arr;
    }
    public HoatDongChiTieu selectHoatDongChiTieu(int maHoatDongChiTieu) throws ParseException {
        String sqlQuery = "SELECT * FROM " + TABLE_HOATDONGCHITIEU + " WHERE " + COLUMN_HOATDONGCHITIEU_MAHOATDONGCHITIEU + " = " + maHoatDongChiTieu;
        HoatDongChiTieu temp = new HoatDongChiTieu();
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery(sqlQuery,null);
        res.moveToFirst();
        temp.setMaHoatDongChiTieu(res.getInt(res.getColumnIndex(COLUMN_HOATDONGCHITIEU_MAHOATDONGCHITIEU)));
        temp.setLuongTien(res.getDouble(res.getColumnIndex(COLUMN_HOATDONGCHITIEU_LUONGTIEN)));
        temp.setMaChiTieu(res.getInt(res.getColumnIndex(COLUMN_HOATDONGCHITIEU_MACHITIEU)));
        temp.setTenChiTieu(res.getString(res.getColumnIndex(COLUMN_HOATDONGCHITIEU_TENCHITIEU)));
        temp.setNgayChiTieu(dateFormat.parse(res.getString(res.getColumnIndex(COLUMN_HOATDONGCHITIEU_NGAYCHITIEU))));
        temp.setMaViTien(res.getInt(res.getColumnIndex(COLUMN_HOATDONGCHITIEU_MAVITIEN)));
        return temp;
    }
    public ArrayList<ChiTieuPerform> getHoatDongChiTieuTheoThang(int thang,int nam) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ChiTieuPerform> arrayList = new ArrayList<ChiTieuPerform>();
        String dieukien="";
        if(thang<10)
            dieukien="'%/0"+thang+'/'+nam+"'";
        else
            dieukien="'%/"+thang+'/'+nam+"'";
        //Cursor res =  db.rawQuery( "select TenChiTieu as 'Tên chi tiêu', LuongTien as 'Số tiền', NgayChiTieu as 'Ngày chi' from HoatDongChiTieu, DanhMucChiTieu where NgayChiTieu like '"+DieuKien+"' and HoatDongChiTieu.MaChiTieu = DanhMucChiTieu.MaChiTieu", null );
        Cursor res =  db.rawQuery( "select MaHoatDongChiTieu as _id, HoatDongChiTieu.TenChiTieu as 'Tên chi tiêu', LuongTien as 'Số tiền', NgayChiTieu as 'Ngày chi' from HoatDongChiTieu, DanhMucChiTieu where NgayChiTieu like "+dieukien+" and HoatDongChiTieu.MaChiTieu = DanhMucChiTieu.MaChiTieu", null );
        //Cursor res =  db.rawQuery( "select MaHoatDongChiTieu as _id, HoatDongChiTieu.TenChiTieu as 'Tên chi tiêu', LuongTien as 'Số tiền', NgayChiTieu as 'Ngày chi' from HoatDongChiTieu, DanhMucChiTieu where NgayChiTieu ='31/01/2021' and HoatDongChiTieu.MaChiTieu = DanhMucChiTieu.MaChiTieu", null );
        //System.out.println(dieukien);
        res.moveToFirst();
        while(res.isAfterLast() == false){
            ChiTieuPerform temp = new ChiTieuPerform();
            temp.setLuongTien(res.getDouble(2));
            try {
                temp.setNgayChiTieu(dateFormat.parse(res.getString(3)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            temp.setTenHoatDongChiTieu(res.getString(1));
            arrayList.add(temp);
            res.moveToNext();
        }
        return arrayList;
    }
    public Cursor NhacDoiNo(String date)
    {
        SQLiteDatabase db = getReadableDatabase();
        String sql="Select * from TienChoMuon where NgayHenTra = "+date;
        Cursor DoiNo = db.rawQuery(sql,null);
        return DoiNo;
    }
    public Cursor NhacTraNo(String date)
    {
        SQLiteDatabase db = getReadableDatabase();
        String sql="Select * from TienNo where NgayHenTra = "+date;
        Cursor TraNo = db.rawQuery(sql,null);
        return TraNo;
    }
    public int updateTrangThaiTienNo(String TrangThai, String ID)
    {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TIENNO_TRANGTHAI,TrangThai);
        return db.update(TABLE_TIENNO,contentValues,COLUMN_TIENNO_MATIENNO+" = ?", new String[]{String.valueOf(ID)});
    }
    public int updateTrangThaiTienChoMuon(String TrangThai, String ID)
    {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TIENNO_TRANGTHAI,TrangThai);
        return db.update(TABLE_TIENCHOMUON,contentValues,COLUMN_TIENCHOMUON_MATIENCHOMUON+" = ?", new String[]{String.valueOf(ID)});
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public double TongTien()
    {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("Select sum(tiendu) as TongTien from vitien where isActive = 1",null);
        double s = 0.0;
        if(cursor.moveToFirst()){
            s = cursor.getDouble(cursor.getColumnIndex("TongTien"));
        };

        //System.err.println(cursor.getCount());
        return s;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public double TongTienChi()
    {
        SQLiteDatabase database = getReadableDatabase();
        LocalDate date = LocalDate.now();
        int thang = date.getMonthValue();
        int nam =date.getYear();
        String dieukien="";
        if(thang<10)
            dieukien="'%/0"+thang+'/'+nam+"'";
        else
            dieukien="'%/"+thang+'/'+nam+"'";
        Cursor cursor = database.rawQuery("Select sum(luongtien) as TongTien from hoatdongchitieu where ngaychitieu Like "+ dieukien,null);
        double s = 0;
        if(cursor.moveToFirst()){
            s = cursor.getDouble(cursor.getColumnIndex("TongTien"));
        };
       // System.err.println(cursor.getCount());
        return s;

    }
    //ThuNhap
    public boolean insertThuNhap(ThuNhap thuNhap){
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try{
            ContentValues values = new ContentValues();
            values.put(COLUMN_THUNHAP_TENTHUNHAP,thuNhap.getTenThuNhap());
            values.put(COLUMN_THUNHAP_LUONGTIEN,thuNhap.getLuongTien());
            //values.put(COLUMN_THUNHAP_MATHUNHAP,thuNhap.getMaThuNhap());
            //values.put(COLUMN_THUNHAP_CHUTHICH,thuNhap.getGhiChu());
            values.put(COLUMN_THUNHAP_MAVITIEN,thuNhap.getMaViTien());
            values.put(COLUMN_THUNHAP_NGAYTHUNHAP,dateFormat.format(thuNhap.getNgayThuNhap()));
            long result = -1;
            result = db.insert(TABLE_THUNHAP,null,values);
            db.setTransactionSuccessful();
            return result != -1;
        }
        catch (Exception e){
            Log.d("TAG","Error insert");
        }
        finally {
            db.endTransaction();
        }
        return false;
    }
    public boolean updateThuNhap(ThuNhap thuNhap){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_THUNHAP_NGAYTHUNHAP,dateFormat.format(thuNhap.getNgayThuNhap()));
        values.put(COLUMN_THUNHAP_MAVITIEN,thuNhap.getMaViTien());
        values.put(COLUMN_THUNHAP_LUONGTIEN,thuNhap.getLuongTien());
        values.put(COLUMN_THUNHAP_TENTHUNHAP,thuNhap.getTenThuNhap());
        //values.put(COLUMN_THUNHAP_CHUTHICH,thuNhap.getGhiChu());
        long result = -1;
        result = db.update(TABLE_THUNHAP,values,COLUMN_THUNHAP_MATHUNHAP + " = ?",new String[]{String.valueOf(thuNhap.getMaThuNhap())} );
        return result != -1;
    }

    public boolean deleteThuNhap(String maThuNhap){
        SQLiteDatabase db = getWritableDatabase();
        long result = -1;
        result = db.delete(TABLE_THUNHAP,COLUMN_THUNHAP_MATHUNHAP + " = ?" , new String[]{maThuNhap});
        return result != -1;
    }
    public ArrayList<ThuNhapPerform> getThuNhapTheoThang(int thang, int nam) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ThuNhapPerform> arrayList = new ArrayList<ThuNhapPerform>();
        String dieukien="";
        if(thang<10)
            dieukien="'%/0"+thang+'/'+nam+"'";
        else
            dieukien="'%/"+thang+'/'+nam+"'";
        Cursor res =  db.rawQuery( "select MaThuNhap as _id, TenThuNhap as 'Tên thu nhập', NgayThuNhap as 'Ngày thu nhập', LuongTien as 'Số tiền' from ThuNhap where NgayThuNhap like "+dieukien+"", null );
        res.moveToFirst();
        while(res.isAfterLast()==false)
        {
            ThuNhapPerform ob = new ThuNhapPerform();
            ob.setLuongTien(res.getDouble(3));
            try {
                ob.setNgayThuNhap(dateFormat.parse(res.getString(2)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ob.setTenThuNhap(res.getString(1));
            arrayList.add(ob);
            res.moveToNext();
        }
        return arrayList;
    }
    public ArrayList<ThuNhap> getAllThuNhap() throws ParseException {
        ArrayList<ThuNhap> arr = new ArrayList<ThuNhap>();
        String sqlQuery = "SELECT * FROM " + TABLE_THUNHAP + ", " + TABLE_VITIEN + " WHERE ThuNhap.MaViTien = ViTien.MaViTien" ;
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery(sqlQuery,null);
        if (res.moveToFirst()) {
            do {
                ThuNhap temp = new ThuNhap();
                temp.setMaThuNhap(res.getInt(res.getColumnIndex(COLUMN_THUNHAP_MATHUNHAP)));
                temp.setTenThuNhap(res.getString(res.getColumnIndex(COLUMN_THUNHAP_TENTHUNHAP)));
                temp.setLuongTien(res.getDouble(res.getColumnIndex(COLUMN_THUNHAP_LUONGTIEN)));
                temp.setTenViTien(res.getString(res.getColumnIndex(COLUMN_VITIEN_TENVITIEN)));
                //temp.setGhiChu(res.getString(res.getColumnIndex(COLUMN_THUNHAP_CHUTHICH)));
                temp.setMaViTien(res.getInt(res.getColumnIndex(COLUMN_THUNHAP_MAVITIEN)));
                temp.setNgayThuNhap(dateFormat.parse(res.getString(res.getColumnIndex(COLUMN_THUNHAP_NGAYTHUNHAP))));
                arr.add(temp);
            } while (res.moveToNext());
        }
        return arr;
    }
    public ThuNhap selectThuNhap(int maThuNhap) throws ParseException {
        String sqlQuery = "SELECT * FROM " + TABLE_THUNHAP + " WHERE " + COLUMN_THUNHAP_MATHUNHAP + " = " + maThuNhap;
        ThuNhap temp = new ThuNhap();
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery(sqlQuery,null);
        res.moveToFirst();
        temp.setMaThuNhap(res.getInt(res.getColumnIndex(COLUMN_THUNHAP_MATHUNHAP)));
        temp.setTenThuNhap(res.getString(res.getColumnIndex(COLUMN_THUNHAP_TENTHUNHAP)));
        temp.setLuongTien(res.getDouble(res.getColumnIndex(COLUMN_THUNHAP_LUONGTIEN)));
        //temp.setGhiChu(res.getString(res.getColumnIndex(COLUMN_THUNHAP_CHUTHICH)));
        temp.setMaViTien(res.getInt(res.getColumnIndex(COLUMN_THUNHAP_MAVITIEN)));
        temp.setNgayThuNhap(dateFormat.parse(res.getString(res.getColumnIndex(COLUMN_THUNHAP_NGAYTHUNHAP))));
        return temp;
    }

    //ViTien
    public boolean insertViTien(ViTien viTien){
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try{
            ContentValues values = new ContentValues();
            //values.put(COLUMN_VITIEN_MAVITIEN,viTien.getMaViTien());
            values.put(COLUMN_VITIEN_GHICHU,viTien.getGhiChu());
            values.put(COLUMN_VITIEN_TENVITIEN,viTien.getTenViTien());
            values.put(COLUMN_VITIEN_TIENDU,viTien.getTienDu());
            values.put(COLUMN_VITIEN_ISACTIVE,1);
            long result = db.insert(TABLE_VITIEN,null,values);
            db.setTransactionSuccessful();
            return result != -1;
        }
        catch (Exception e){
            Log.d("TAG","Error insert");
        }
        finally {
            db.endTransaction();
        }
        return false;
    }
    public boolean updateViTien(ViTien viTien){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_VITIEN_GHICHU, viTien.getGhiChu());
        values.put(COLUMN_VITIEN_TENVITIEN,viTien.getTenViTien());
        values.put(COLUMN_VITIEN_TIENDU,viTien.getTienDu());
        long result = -1;
        result = db.update(TABLE_VITIEN,values,COLUMN_VITIEN_MAVITIEN + " = ?",new String[]{String.valueOf(viTien.getMaViTien())} );

        return result != -1;
    }

    public boolean softDeleteViTien(String maViTien){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_VITIEN_ISACTIVE,0);
        long result = -1;
        result = db.update(TABLE_VITIEN,values,COLUMN_VITIEN_MAVITIEN + " = ?",new String[]{maViTien} );

        return result != -1;
    }

    public ArrayList<ViTien> getAllViTien() throws ParseException {
        ArrayList<ViTien> arr = new ArrayList<ViTien>();
        String sqlQuery = "SELECT * FROM " + TABLE_VITIEN + " WHERE isActive = 1";
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery(sqlQuery,null);
        if (res.moveToFirst()) {
            do {
                ViTien temp = new ViTien();
                temp.setMaViTien(res.getInt(res.getColumnIndex(COLUMN_VITIEN_MAVITIEN)));
                temp.setGhiChu(res.getString(res.getColumnIndex(COLUMN_VITIEN_GHICHU)));
                temp.setTenViTien(res.getString(res.getColumnIndex(COLUMN_VITIEN_TENVITIEN)));
                temp.setTienDu(res.getDouble(res.getColumnIndex(COLUMN_VITIEN_TIENDU)));

                arr.add(temp);
            } while (res.moveToNext());
        }
        return arr;
    }
    public ViTien selectViTien(int maViTien) throws ParseException {
        String sqlQuery = "SELECT * FROM " + TABLE_VITIEN + " WHERE " + COLUMN_VITIEN_MAVITIEN + " = " + maViTien + " AND isActive = 1";
        ViTien temp = new ViTien();
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery(sqlQuery,null);
        if(res.moveToFirst()) {
            temp.setMaViTien(res.getInt(res.getColumnIndex(COLUMN_VITIEN_MAVITIEN)));
            temp.setGhiChu(res.getString(res.getColumnIndex(COLUMN_VITIEN_GHICHU)));
            temp.setTenViTien(res.getString(res.getColumnIndex(COLUMN_VITIEN_TENVITIEN)));
            temp.setTienDu(res.getDouble(res.getColumnIndex(COLUMN_VITIEN_TIENDU)));
        }
        return temp;
    }

    //TienChoMuon
    public boolean insertTienChoMuon(TienChoMuon tienChoMuon){
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try{
            ContentValues values = new ContentValues();
            //values.put(COLUMN_TIENCHOMUON_MATIENCHOMUON,tienChoMuon.getMaTienChoMuon());
            values.put(COLUMN_TIENCHOMUON_LUONGTIEN,tienChoMuon.getLuongTien());
            values.put(COLUMN_TIENCHOMUON_MAVITIEN,tienChoMuon.getMaViTien());
            values.put(COLUMN_TIENCHOMUON_NGAYCHOMUON,dateFormat.format(tienChoMuon.getNgayChoMuon()));
            values.put(COLUMN_TIENCHOMUON_NGAYHENTRA,dateFormat.format(tienChoMuon.getNgayHenTra()));
            values.put(COLUMN_TIENCHOMUON_THONGTINCHOMUON,tienChoMuon.getThongTinChoMuon());
            values.put(COLUMN_TIENCHOMUON_TRANGTHAI,tienChoMuon.getTrangThai());
            long result = db.insert(TABLE_TIENCHOMUON,null,values);

            db.setTransactionSuccessful();
            return result != -1;
        }
        catch (Exception e){
            Log.d("TAG","Error insert");
        }
        finally {
            db.endTransaction();
        }
        return false;
    }
    public boolean updateTienChoMuon(TienChoMuon tienChoMuon){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TIENCHOMUON_LUONGTIEN,tienChoMuon.getLuongTien());
        values.put(COLUMN_TIENCHOMUON_MAVITIEN,tienChoMuon.getMaViTien());
        values.put(COLUMN_TIENCHOMUON_NGAYCHOMUON,dateFormat.format(tienChoMuon.getNgayChoMuon()));
        values.put(COLUMN_TIENCHOMUON_NGAYHENTRA,dateFormat.format(tienChoMuon.getNgayHenTra()));
        values.put(COLUMN_TIENCHOMUON_THONGTINCHOMUON,tienChoMuon.getThongTinChoMuon());
        values.put(COLUMN_TIENCHOMUON_TRANGTHAI,tienChoMuon.getTrangThai());
        long result = -1;
        result = db.update(TABLE_TIENCHOMUON,values,COLUMN_TIENCHOMUON_MATIENCHOMUON + " = ?",new String[]{String.valueOf(tienChoMuon.getMaTienChoMuon())} );
        return result != -1;
    }

    public boolean deleteTienChoMuon(int maTienChoMuon){
        SQLiteDatabase db = getWritableDatabase();
        long result = -1;
        result = db.delete(TABLE_TIENCHOMUON,COLUMN_TIENCHOMUON_MATIENCHOMUON + " = ?" , new String[]{String.valueOf(maTienChoMuon)} );
        return result != -1;
    }

    public ArrayList<TienChoMuon> getAllTienChoMuon() throws ParseException {
        ArrayList<TienChoMuon> arr = new ArrayList<TienChoMuon>();
        String sqlQuery = "SELECT * FROM " + TABLE_TIENCHOMUON;
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery(sqlQuery,null);
        if (res.moveToFirst()) {
            do {
                TienChoMuon temp = new TienChoMuon();
                temp.setLuongTien(res.getDouble(res.getColumnIndex(COLUMN_TIENCHOMUON_LUONGTIEN)));
                temp.setMaTienChoMuon(res.getInt(res.getColumnIndex(COLUMN_TIENCHOMUON_MATIENCHOMUON)));
                temp.setMaViTien(res.getInt(res.getColumnIndex(COLUMN_TIENCHOMUON_MAVITIEN)));
                temp.setNgayChoMuon(dateFormat.parse(res.getString(res.getColumnIndex(COLUMN_TIENCHOMUON_NGAYCHOMUON))));
                temp.setNgayHenTra(dateFormat.parse(res.getString(res.getColumnIndex(COLUMN_TIENCHOMUON_NGAYHENTRA))));
                temp.setThongTinChoMuon(res.getString(res.getColumnIndex(COLUMN_TIENCHOMUON_THONGTINCHOMUON)));
                arr.add(temp);
            } while (res.moveToNext());
        }
        return arr;
    }
    public TienChoMuon selectTienChoMuon(int maTienChoMuon) throws ParseException {
        String sqlQuery = "SELECT * FROM " + TABLE_TIENCHOMUON + " WHERE " + COLUMN_TIENCHOMUON_MATIENCHOMUON + " = " + maTienChoMuon;
        TienChoMuon temp = new TienChoMuon();
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery(sqlQuery,null);
        res.moveToFirst();
        temp.setLuongTien(res.getDouble(res.getColumnIndex(COLUMN_TIENCHOMUON_LUONGTIEN)));
        temp.setMaTienChoMuon(res.getInt(res.getColumnIndex(COLUMN_TIENCHOMUON_MATIENCHOMUON)));
        temp.setMaViTien(res.getInt(res.getColumnIndex(COLUMN_TIENCHOMUON_MAVITIEN)));
        temp.setNgayChoMuon(dateFormat.parse(res.getString(res.getColumnIndex(COLUMN_TIENCHOMUON_NGAYCHOMUON))));
        temp.setNgayHenTra(dateFormat.parse(res.getString(res.getColumnIndex(COLUMN_TIENCHOMUON_NGAYHENTRA))));
        temp.setThongTinChoMuon(res.getString(res.getColumnIndex(COLUMN_TIENCHOMUON_THONGTINCHOMUON)));
        return temp;
    }
    //TienNo
    public boolean insertTienNo(TienNo tienNo){
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try{
            ContentValues values = new ContentValues();
            //values.put(COLUMN_TIENNO_MATIENNO,tienNo.getMaTienNo());
            values.put(COLUMN_TIENNO_LUONGTIEN,tienNo.getLuongTien());
            values.put(COLUMN_TIENNO_MAVITIEN,tienNo.getMaViTien());
            values.put(COLUMN_TIENNO_NGAYMUONNO,dateFormat.format(tienNo.getNgayMuonNo()));
            values.put(COLUMN_TIENNO_NGAYHENTRA,dateFormat.format(tienNo.getNgayHenTra()));
            values.put(COLUMN_TIENNO_THONGTINMUONNO,tienNo.getThongTinMuonNo());
            values.put(COLUMN_TIENNO_TRANGTHAI,tienNo.getTrangThai());
            long result = db.insert(TABLE_TIENNO,null,values);

            db.setTransactionSuccessful();
            return result != -1;
        }
        catch (Exception e){
            Log.d("TAG","Error insert");
        }
        finally {
            db.endTransaction();
        }
        return false;
    }
    public boolean updateTienNo(TienNo tienNo){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TIENNO_LUONGTIEN,tienNo.getLuongTien());
        values.put(COLUMN_TIENNO_MAVITIEN,tienNo.getMaViTien());
        values.put(COLUMN_TIENNO_NGAYMUONNO,dateFormat.format(tienNo.getNgayMuonNo()));
        values.put(COLUMN_TIENNO_NGAYHENTRA,dateFormat.format(tienNo.getNgayHenTra()));
        values.put(COLUMN_TIENNO_THONGTINMUONNO,tienNo.getThongTinMuonNo());
        values.put(COLUMN_TIENNO_TRANGTHAI,tienNo.getTrangThai());
        long result = -1;
        result = db.update(TABLE_TIENNO,values,COLUMN_TIENNO_MATIENNO + " = ?",new String[]{String.valueOf(tienNo.getMaTienNo())} );
        return result != -1;
    }

    public boolean deleteTienNo(int tienNo){
        SQLiteDatabase db = getWritableDatabase();
        long result = -1;
        result = db.delete(TABLE_TIENNO,COLUMN_TIENNO_MATIENNO + " = ?",new String[]{String.valueOf(tienNo)} );
        return result != -1;
    }

    public ArrayList<TienNo> getAllTienNo() throws ParseException {
        ArrayList<TienNo> arr = new ArrayList<TienNo>();
        String sqlQuery = "SELECT * FROM " + TABLE_TIENNO;
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery(sqlQuery,null);
        if (res.moveToFirst()) {
            do {
                TienNo temp = new TienNo();
                temp.setLuongTien(res.getDouble(res.getColumnIndex(COLUMN_TIENNO_LUONGTIEN)));
                temp.setMaTienNo(res.getInt(res.getColumnIndex(COLUMN_TIENNO_MATIENNO)));
                temp.setMaViTien(res.getInt(res.getColumnIndex(COLUMN_TIENNO_MAVITIEN)));
                temp.setNgayMuonNo(dateFormat.parse(res.getString(res.getColumnIndex(COLUMN_TIENNO_NGAYMUONNO))));
                temp.setNgayHenTra(dateFormat.parse(res.getString(res.getColumnIndex(COLUMN_TIENNO_NGAYHENTRA))));
                temp.setThongTinMuonNo(res.getString(res.getColumnIndex(COLUMN_TIENNO_THONGTINMUONNO)));
                arr.add(temp);
            } while (res.moveToNext());
        }
        return arr;
    }
    public TienNo selectTienNo(int maTienNo) throws ParseException {
        String sqlQuery = "SELECT * FROM " + TABLE_TIENNO + " WHERE " + COLUMN_TIENNO_MATIENNO + " = " + maTienNo;
        TienNo temp = new TienNo();
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery(sqlQuery,null);
        res.moveToFirst();
        temp.setLuongTien(res.getDouble(res.getColumnIndex(COLUMN_TIENNO_LUONGTIEN)));
        temp.setMaTienNo(res.getInt(res.getColumnIndex(COLUMN_TIENNO_MATIENNO)));
        temp.setMaViTien(res.getInt(res.getColumnIndex(COLUMN_TIENNO_MAVITIEN)));
        temp.setNgayMuonNo(dateFormat.parse(res.getString(res.getColumnIndex(COLUMN_TIENNO_NGAYMUONNO))));
        temp.setNgayHenTra(dateFormat.parse(res.getString(res.getColumnIndex(COLUMN_TIENNO_NGAYHENTRA))));
        temp.setThongTinMuonNo(res.getString(res.getColumnIndex(COLUMN_TIENNO_THONGTINMUONNO)));
        return temp;
    }

    public ArrayList<ChiTieuPerform> getAllChiTieuPerform() throws ParseException {
        ArrayList<ChiTieuPerform> arr = new ArrayList<ChiTieuPerform>();
        String sqlQuery = "SELECT " + COLUMN_HOATDONGCHITIEU_MAHOATDONGCHITIEU + ", " + COLUMN_VITIEN_TENVITIEN + ", " + COLUMN_HOATDONGCHITIEU_MAHOATDONGCHITIEU + ", " + COLUMN_HOATDONGCHITIEU_NGAYCHITIEU + ", DanhMucChiTieu." + COLUMN_DANHMUCCHITIEU_TENCHITIEU + ", " + COLUMN_HOATDONGCHITIEU_LUONGTIEN + " FROM " +TABLE_HOATDONGCHITIEU
                + ", " + TABLE_DANHMUCCHITIEU + ", " + TABLE_VITIEN + " WHERE HoatDongChiTieu.MaChiTieu = DanhMucChiTieu.MaChiTieu AND ViTien.MaViTien = HoatDongChiTieu.MaViTien";
        System.out.println(sqlQuery);
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery(sqlQuery,null);
        if (res.moveToFirst()) {
            do {
                ChiTieuPerform temp = new ChiTieuPerform();
                temp.setMaHoatDongChiTieu(res.getInt(res.getColumnIndex(COLUMN_HOATDONGCHITIEU_MAHOATDONGCHITIEU)));
                temp.setTenViTien(res.getString(res.getColumnIndex(COLUMN_VITIEN_TENVITIEN)));
                temp.setMaChiTieu(res.getInt(res.getColumnIndex(COLUMN_HOATDONGCHITIEU_MAHOATDONGCHITIEU)));
                temp.setLuongTien(res.getDouble(res.getColumnIndex(COLUMN_HOATDONGCHITIEU_LUONGTIEN)));
                temp.setNgayChiTieu(dateFormat.parse(res.getString(res.getColumnIndex(COLUMN_HOATDONGCHITIEU_NGAYCHITIEU))));
                temp.setTenHoatDongChiTieu(res.getString(res.getColumnIndex(COLUMN_DANHMUCCHITIEU_TENCHITIEU)));
                arr.add(temp);
            } while (res.moveToNext());
        }
        return arr;
    }

    public int getMaViTienTheoTen(String tenVi) {
        String sqlQuery = "SELECT MaViTien FROM " + TABLE_VITIEN + " WHERE " + COLUMN_VITIEN_TENVITIEN + " = '" + tenVi + "'";
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery(sqlQuery,null);
        res.moveToFirst();
        return res.getInt(res.getColumnIndex(COLUMN_VITIEN_MAVITIEN));
    }

    public int getMaChiTieuTheoTen(String tenChiTieu) {
        String sqlQuery = "SELECT MaChiTieu FROM " + TABLE_DANHMUCCHITIEU + " WHERE " + COLUMN_DANHMUCCHITIEU_TENCHITIEU + " = '" + tenChiTieu + "'";
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery(sqlQuery,null);
        res.moveToFirst();
        return res.getInt(res.getColumnIndex(COLUMN_DANHMUCCHITIEU_MACHITIEU));
    }

    public void tangTienTrongVi(double luongTien, int maViTien) {
        String sqlQuery = "UPDATE ViTien SET TienDu = TienDu + " + luongTien + " WHERE MaViTien = " + maViTien;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlQuery);
    }
}
