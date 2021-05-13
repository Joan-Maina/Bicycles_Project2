package com.example.myapplicationbicycles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class dashboard extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        drawerLayout = findViewById(R.id.drawer_layout);

    }

    public void ClickMenu(View view) {
        homepage.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view) {
        //close drawer
        homepage.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view) {
        //redirect to home
        homepage.redirectActivity(this, MainActivity.class);
    }

    public void ClickDashBoard(View view) {
        //recreate activity
        recreate();
    }

    public void ClickAboutus(View view) {
        //redirect to about us
        homepage.redirectActivity(this, aboutus.class);
    }

    public void ClickLogout(View view) {
        homepage.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //close drawer
        homepage.closeDrawer(drawerLayout);
    }
}