package com.crafsed.example.criminalintenet;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CrimeFactory {
    private static CrimeFactory instance;
    private CrimeFactory(Context context){
        mCrimes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Crime c = new Crime();
            c.setTitle("Crime #"+i);
            c.setSolved(true);
            mCrimes.add(i,c);
        }
    }

    private List<Crime> mCrimes;

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        for (Crime c :
                mCrimes) {
            if (c.getId()==id){
                return c;
            }
        }
        return null;
    }

    public static CrimeFactory get(Context c) {
        if (instance==null){
            instance = new CrimeFactory(c);
        }
        return instance;
    }
}
