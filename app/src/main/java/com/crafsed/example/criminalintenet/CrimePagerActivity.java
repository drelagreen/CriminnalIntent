package com.crafsed.example.criminalintenet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {
    static volatile String EXTRA_CRIME_ID =
            "com.bignerdranch.android.criminalintent.crime_id";

    private List<Crime> mCrimes;
    ViewPager mViewPager;

    public static Intent newIntent(Context packageContext, UUID crimeID){
        Intent i = new Intent(packageContext,CrimePagerActivity.class);
        i.putExtra(EXTRA_CRIME_ID, crimeID);
        return i;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        mCrimes = CrimeFactory.get(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager = findViewById(R.id.crime_view_pager);



        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        mViewPager.setCurrentItem(CrimeFactory.get(this).getCrimeNumber(crimeId));


    }
    public static int convertDip2Pixels(Context context, int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
    }
}

