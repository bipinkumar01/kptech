package com.bipin.kptech;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.bipin.kptech.fragment.Drawer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(getApplicationContext().getResources().getBoolean(R.bool.display_show_home_enabled));
        getSupportActionBar().setDisplayShowCustomEnabled(getApplicationContext().getResources().getBoolean(R.bool.display_show_custom_enabled));
        mToolbar.setTitle(getString(R.string.app_name));

        Drawer _Drawer = (Drawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        _Drawer.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
    }
}
