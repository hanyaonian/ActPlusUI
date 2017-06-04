package com.example.dell.actplus;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class Index extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ActItem selected_item;
    private String userCookie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        //toolbar 设置空
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //set up bottom navigation
        setUpBottomNavigate();
        //set up default fragment
        setUpDefaultFragment();
    }
    public void setUpDefaultFragment() {
        FragmentManager fragmentManager = this.getFragmentManager();
        list_fragment list_fragment = new list_fragment();
        fragmentManager.beginTransaction().replace(R.id.main_fragment, list_fragment).commit();
    }
    //开源项目 https://github.com/Ashok-Varma/BottomNavigation/wiki/Usage
    public void setUpBottomNavigate() {
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar)findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.home, "主页"))
                .addItem(new BottomNavigationItem(R.drawable.group, "组队"))
                .addItem(new BottomNavigationItem(R.drawable.person, "我的"))
                .initialise();
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {
                if (position == 0) {
                    FragmentManager fragmentManager = getFragmentManager();
                    list_fragment list_fragment = new list_fragment();
                    fragmentManager.beginTransaction().replace(R.id.main_fragment, list_fragment).commit();
                }
                if (position == 1) {
                    FragmentManager fragmentManager = getFragmentManager();
                    grouplist group_list_fragment = new grouplist();
                    fragmentManager.beginTransaction().replace(R.id.main_fragment, group_list_fragment).commit();
                }
                if (position == 2) {
                    Toast.makeText(getApplicationContext(), "person", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onTabUnselected(int position) {
            }
            @Override
            public void onTabReselected(int position) {
            }
        });
    }
    public void setCookie(String cookie) {
        this.userCookie = cookie;
    }
    public String getUserCookie() {
        return this.userCookie;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.my_start) {
            // Handle the camera action
        } else if (id == R.id.my_group) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void setSelected_item(ActItem actitem){
        this.selected_item = actitem;
    }
    public ActItem getSelected_item() {
        return this.selected_item;
    }
}
