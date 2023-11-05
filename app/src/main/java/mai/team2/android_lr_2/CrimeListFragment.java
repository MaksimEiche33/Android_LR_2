package mai.team2.android_lr_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CrimeListFragment extends Fragment{
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private int mIdModifiedElement; // добавлено для упражнения из 10 главы

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container,
                false);
        mCrimeRecyclerView = (RecyclerView) view
                .findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager
                (getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        if (mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyItemChanged(mIdModifiedElement);// переделано для упражнения из 10 главы
            //mAdapter.notifyDataSetChanged();
        }
    }

    private class CrimeHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mSolvedImageView;
        private Crime mCrime;
        private int mPosition; // добавлено для упражнения из 10 главы
        public CrimeHolder(View v) {
            //super(inflater.inflate(R.layout.list_item_crime, parent, false));
            super(v);                                                             // переделано для упражнения из 8 главы
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
            mSolvedImageView = (ImageView) itemView.findViewById(R.id.crime_solved);
        }
        public void bind(Crime crime, int position) {
            mPosition = position;
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate());
            mSolvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE :
                    View.GONE);
        }
        @Override
        public void onClick(View view) {
            Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getId());
            startActivity(intent);
            mIdModifiedElement = mPosition;   // добавлено для упражнения из 10 главы
        }
    }
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private final int TYPE_ITEM_NORMAL  = 0;     // типы предсталения СТРОК
        private final int TYPE_ITEM_REQUIRE_SPOLICE = 1;
        private List<Crime> mCrimes;
        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }
        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View v = layoutInflater.inflate(R.layout.list_item_crime, parent, false); // код для упражнения из 8 главы

            switch (viewType) {
                case TYPE_ITEM_NORMAL:
                    v = layoutInflater.inflate(R.layout.list_item_crime, parent, false);
                    break;
                case TYPE_ITEM_REQUIRE_SPOLICE:
                    v = layoutInflater.inflate(R.layout.list_item_crime_requires_police, parent, false);
            }

            return new CrimeHolder(v);
        }
        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bind(crime, position);
        }
        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
        @Override
        public int getItemViewType(int position) {
            if (mCrimes.get(position).isRequiresPolice() == false)
                return TYPE_ITEM_NORMAL;
            return TYPE_ITEM_REQUIRE_SPOLICE;
        }
    }
}
