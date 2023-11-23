package com.example.tqlphone;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

public class Navigationdrawer extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigationdrawer);

        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar);
        nav = findViewById(R.id.nav);

        setSupportActionBar(toolbar);// gán toolbar
        // set drawer toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        nav.setItemIconTintList(null);
        // xử lý khi click chọn item trên nav
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.danhsach){
                    frgdanhsach frgdanhsach = new frgdanhsach();// tạo đối tượng
                    replaceFrg(frgdanhsach);
                }else if (item.getItemId()==R.id.quanlyhang){
                    frgloaihang frgloaihang = new frgloaihang();// tạo đối tượng
                    replaceFrg(frgloaihang);
                } else if (item.getItemId()==R.id.quanlykhachhang){
                    frgkhachhang frgkhachhang = new frgkhachhang();// tạo đối tượng
                    replaceFrg(frgkhachhang);
                }else if (item.getItemId()==R.id.giohang){
                    frggiohang frggiohang = new frggiohang();// tạo đối tượng
                    replaceFrg(frggiohang);
                }else if (item.getItemId()==R.id.hoadon){
                    frghoadon frghoadon = new frghoadon();// tạo đối tượng
                    replaceFrg(frghoadon);
                }else if (item.getItemId()==R.id.doanhthu){
                    frgdoanhthu frgdoanhthu = new frgdoanhthu();// tạo đối tượng
                }else if (item.getItemId()==R.id.dangxuat) {
                    finish();
                }
                return true;
            }
        });
    }
    // phương thức gọi lại Fragment
    public void replaceFrg(Fragment frg){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frmnav, frg).commit();
    }
}