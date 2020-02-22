package com.crafsed.example.criminalintenet;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CrimeFactory {

    private HashMap<UUID, Integer> mCrimesMap = new HashMap<>();

    private static CrimeFactory instance;
    private CrimeFactory(Context context){
        mCrimes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Crime c = new Crime();
            c.setTitle("Crime #"+i);
            c.setSolved(i%2 == 0);
            mCrimes.add(i,c);
            mCrimesMap.put(c.getId(),i);
        }
        mCrimes.get(5).setRequiresPolice(true);
        mCrimes.get(6).setRequiresPolice(true);
        mCrimes.get(10).setRequiresPolice(true);
        mCrimes.get(0).setRequiresPolice(true);
    }

    private List<Crime> mCrimes;

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        try {
            return mCrimes.get(mCrimesMap.get(id));
        }catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    public int getCrimeNumber(UUID uuid){

        try{
            return mCrimesMap.get(uuid);
        } catch (NullPointerException e){
            e.printStackTrace();
            return -1;
        }
    }

    public static CrimeFactory get(Context c) {
        if (instance==null){
            instance = new CrimeFactory(c);
        }
        return instance;
    }
}
