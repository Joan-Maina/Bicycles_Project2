package com.example.myapplicationbicycles;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class stations extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stations);

        //assign variables
        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.view_pager);

        //initialize array list
        ArrayList<String> arrayList= new ArrayList<>();

        //add title in arraylist; on tabs
        arrayList.add("Home");
        arrayList.add("favourite");
        arrayList.add("recent");

        //set up tablayout
        tabLayout.setupWithViewPager(viewPager);

        //prepare viewpager
        prepareViewPager(viewPager,arrayList);




    }

    private void prepareViewPager(ViewPager viewPager, ArrayList<String> arrayList) {
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());

        //initialize main fragment
     MainFragment mainFragment=new MainFragment();

    //use for loop
        for (int i=0;i<arrayList.size();i++){
        //initialize bundle
        Bundle bundle = new Bundle();

        //put title
        bundle.putString("title", arrayList.get(i));
        //set argument
        mainFragment.setArguments(bundle);
        //add fragment
        adapter.addFragment(mainFragment,arrayList.get(i));
        mainFragment=new MainFragment();
    }
    //set adapter
        viewPager.setAdapter(adapter);
}

    private class MainAdapter extends FragmentPagerAdapter {
//initialize arraylist

        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();
        int[] imageList = {R.drawable.ic_baseline_home_24, R.drawable.ic_baseline_favorite_24,R.drawable.ic_baseline_recent_actors_24};

        //create constructor
        public void  addFragment(Fragment fragment, String s){
            //add fragment
            fragmentArrayList.add(fragment);
            //add title
            stringArrayList.add(s);
    }public MainAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);

        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            //return fragment position
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            //return fragment array list size
            return fragmentArrayList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            //initialize drawable
            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),imageList[position]);

            //set bound
            drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
            //initialize spannable strng
            SpannableString spannableString=new SpannableString(stringArrayList.get(position));
            ///initialize image span
            ImageSpan imageSpan=new ImageSpan(drawable,ImageSpan.ALIGN_BOTTOM);
            //SET SPAN
            spannableString.setSpan(imageSpan,0,1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            //return spannable string
            return spannableString;
        }
    }
}
