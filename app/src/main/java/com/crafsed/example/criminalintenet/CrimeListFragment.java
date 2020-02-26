package com.crafsed.example.criminalintenet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;


public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private UUID currentUUID;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        mCrimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI(currentUUID);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI(currentUUID);
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        protected TextView mTittleTextView;
        protected TextView mDateTextView;
        protected ImageView mSolvedImageView;
        private Crime mCrime;



        public CrimeHolder(LayoutInflater inflater,ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));
            mTittleTextView = itemView.findViewById(R.id.crime_tittle);
            mDateTextView = itemView.findViewById(R.id.crime_date);
            mSolvedImageView = itemView.findViewById(R.id.crime_solved);
            itemView.setOnClickListener(this);

        }

        public void bind(Crime crime){
            mCrime = crime;
            SimpleDateFormat date = new SimpleDateFormat("EEEE, d MMMM yyyy", getResources().getConfiguration().locale);
            mDateTextView.setText(date.format(mCrime.getDate()));
            mTittleTextView.setText(mCrime.getTitle());
            mSolvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.INVISIBLE);
        }

        @Override
        public void onClick(View v) {
            Intent intent = CrimePagerActivity.newIntent(getActivity(),mCrime.getId());
            startActivity(intent);
            currentUUID = mCrime.getId();
        }
    }


    private class PoliceCrimeHolder extends CrimeHolder{

        public PoliceCrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater, parent);
            mTittleTextView.setAllCaps(true);
            itemView.setBackgroundColor(getResources().getColor(R.color.colorPolice));
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> mCrimes;

        CrimeAdapter(List<Crime> crimes){
            mCrimes = crimes;
        }

        @Override
        public int getItemViewType(int position) {
            return mCrimes.get(position).isRequiresPolice() ? 1 : 0;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            switch (viewType) {
                case 1:   return new PoliceCrimeHolder(layoutInflater, parent);
                default: return new CrimeHolder(layoutInflater, parent);
            }

        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {

            Crime crime = mCrimes.get(position);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }
    private void updateUI(UUID currentUUID){
        CrimeFactory cf = CrimeFactory.get(getActivity());
        List<Crime> crimes = cf.getCrimes();

        if (mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        } else {

              mAdapter.notifyDataSetChanged();
//            mAdapter.notifyItemChanged(CrimeFactory.get(getActivity()).getCrimeNumber(currentUUID));
        }
    }
}
