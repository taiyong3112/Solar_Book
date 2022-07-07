package com.example.solar_book;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.solar_book.fragment.CategoryFragment;
import com.example.solar_book.fragment.HomeFragment;
import com.example.solar_book.fragment.NewsFragment;
import com.google.android.material.navigation.NavigationView;

//implements sự kiện click vào từng item trong navigation drawer
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_CATEGORY = 1;
    private static final int FRAGMENT_NEWS = 2;

    private int mCurrentFragment = FRAGMENT_HOME;

    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set toolbar
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        //tạo nút để mở navigation drawer trên toolbar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.open_nav_drawer, R.string.close_nav_drawer);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //thiết lập sự kiện khi click vào item trên navigation drawer
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        //vào app lần đầu thì vào HomeFragment
        replaceFragment(new HomeFragment());
        //hiển thị chọn Trang chủ trong Navigation Drawer
        navigationView.getMenu().findItem(R.id.menuHome).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //get Id của từng item trong navigation drawer
        int id = item.getItemId();
        switch (id){
            case R.id.menuHome:
                if(mCurrentFragment != FRAGMENT_HOME){
                    replaceFragment(new HomeFragment());
                    mCurrentFragment = FRAGMENT_HOME;
                }
                break;
            case R.id.menuCategory:
                if(mCurrentFragment != FRAGMENT_CATEGORY){
                    replaceFragment(new CategoryFragment());
                    mCurrentFragment = FRAGMENT_CATEGORY;
                }
                break;
            case R.id.menuNews:
                if(mCurrentFragment != FRAGMENT_NEWS){
                    replaceFragment(new NewsFragment());
                    mCurrentFragment = FRAGMENT_NEWS;
                }
                break;
        }

        //khi click vào item trong navigation drawer thì đồng thời tắt navigation drawer
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //khi ấn nút back sẽ đóng navigation drawer
    @Override
    public void onBackPressed() {
        //nếu navigation drawer đang mở
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    //phương thức thay đổi các fragment
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentFrame, fragment); //tham số thứ 1 là FrameLayout trong file activity_main, tham số thứ 2 là fragment ta muốn thay đổi
        transaction.commit();
    }
}