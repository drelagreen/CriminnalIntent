package com.crafsed.example.criminalintenet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        mCrimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }
    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView mTittleTextView;
        private TextView mDateTextView;

        public CrimeHolder(LayoutInflater inflater,ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));
            mTittleTextView = itemView.findViewById(R.id.crime_tittle);
            mDateTextView = itemView.findViewById(R.id.crime_date);
            itemView.setOnClickListener(this);
        }

        private Crime mCrime;

        public void bind(Crime crime){
            mCrime = crime;
            mDateTextView.setText(mCrime.getDate().toString());
            mTittleTextView.setText(mCrime.getTitle());
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getContext(),mCrime.getTitle()+" clicked", Toast.LENGTH_SHORT)
                    .show();
//            mCrimeRecyclerView.getAdapter().notifyItemRemoved(0);
//            mCrimeRecyclerView.getAdapter().notifyItemMoved(0,1);
        }


    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> mCrimes;

        CrimeAdapter(List<Crime> crimes){
            mCrimes = crimes;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater, parent);
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
    private void updateUI(){
        CrimeFactory cf = CrimeFactory.get(getActivity());
        List<Crime> crimes = cf.getCrimes();
        mAdapter = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);
    }
}
