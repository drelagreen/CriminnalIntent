package com.crafsed.example.criminalintenet;

import java.util.Date;
import java.util.UUID;

public class Crime {
    private UUID mId;
    private String mTitle;
    private Date  mDate;
    private boolean mSolved;
    private boolean mRequiresPolice;
    Crime(){
        mId = UUID.randomUUID();
        mDate = new Date();
    }
    public UUID getId() {
        return mId;
    }

    public Date getDate() {
        return mDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public Crime setTitle(String title) {
        mTitle = title;
        return this;
    }

    public Crime setSolved(boolean solved) {
        mSolved = solved;
        return this;
    }

    public Crime setDate(Date date) {
        mDate = date;
        return this;
    }

    public void setRequiresPolice(boolean requiresPolice) {
        mRequiresPolice = requiresPolice;
    }

    public boolean isRequiresPolice() {
        return mRequiresPolice;
    }
}
