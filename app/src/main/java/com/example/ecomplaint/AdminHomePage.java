package com.example.ecomplaint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class AdminHomePage extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        tabLayout=findViewById(R.id.tabLayout);

        viewPager=findViewById(R.id.viewpager);
        tabLayout.setupWithViewPager(viewPager);

        VPAdapter vpAdapter= new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new adminPage(),"Complaints");
        vpAdapter.addFragment(new AboutFragment(),"about");

        viewPager.setAdapter(vpAdapter);
    }
}