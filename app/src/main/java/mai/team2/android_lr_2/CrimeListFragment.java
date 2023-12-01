package mai.team2.android_lr_2;

import static java.text.DateFormat.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

public class CrimeListFragment extends Fragment{
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private boolean mSubtitleVisible;
    private int mIdModifiedElement; // добавлено для упражнения из 10 главы
    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";
    private Callbacks mCallbacks;

    public interface Callbacks {
        void onCrimeSelected(Crime crime);
    }
    ItemTouchHelper.Callback itemTouchCallback = new ItemTouchHelper.Callback() {                    // колбек обработки для удалений происшествий
        @Override
        public int getMovementFlags(RecyclerView recyclerView,RecyclerView.ViewHolder viewHolder) {
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
            return makeMovementFlags(dragFlags, swipeFlags);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            mAdapter.deleteCrime(viewHolder.getAdapterPosition());
        }

    };
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (Callbacks) context;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (savedInstanceState != null) {
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mCrimeRecyclerView);

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.show_subtitle);
        if (mSubtitleVisible) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        } else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.new_crime) {
                Crime crime = new Crime();
                CrimeLab.get(getActivity()).addCrime(crime);
                Intent intent = CrimeActivity
                        .newIntent(getActivity(), crime.getId());
                startActivity(intent);
                return true;
        }
        else if (item.getItemId() == R.id.show_subtitle) {
            mSubtitleVisible = !mSubtitleVisible;
            getActivity().invalidateOptionsMenu();
            updateSubtitle();
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }
    private void updateSubtitle() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());

        int crimeSize = crimeLab.getCrimes().size();
        String subtitle = getResources().getQuantityString(R.plurals.subtitle_plural, crimeSize, crimeSize);

        if (!mSubtitleVisible) {
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }
    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        if (crimeLab.getCrimes().size() == 0){                                           // вызов диалогового окна в пуслом списке
            FragmentManager manager = getActivity().getSupportFragmentManager();
            EmptyListDialogFragment myDialogFragment = new EmptyListDialogFragment();
            myDialogFragment.show (manager, "myDialog");
        }

        if (mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);

        } else {
            mAdapter.setCrimes(crimes);
            if (crimeLab.isDelete == false){
                mAdapter.notifyItemChanged(mIdModifiedElement);                                     // переделано для упражнения из 10 главы
            } else{
                mAdapter.notifyDataSetChanged();
                crimeLab.isDelete = false;
            }
        }
        updateSubtitle();
    }

    public class CrimeHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mSolvedImageView;
        private Crime mCrime;
        private int mPosition; // добавлено для упражнения из 10 главы
        public CrimeHolder(View v) {
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
            DateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm");                          // упр 9
            mDateTextView.setText(getDateInstance().format(mCrime.getDate()) + "  " + DATE_FORMAT.format(mCrime.getDate()));
            mSolvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE :View.GONE);
        }


        @Override
        public void onClick(View view) {
            mCallbacks.onCrimeSelected(mCrime);
            mIdModifiedElement = mPosition;   // добавлено для упражнения из 10 главы
        }
    }
    public class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private final int TYPE_ITEM_NORMAL  = 0;     // типы предсталения СТРОК
        private final int TYPE_ITEM_REQUIRE_SPOLICE = 1;
        private List<Crime> mCrimes;
        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }
        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View v;

            if (viewType == TYPE_ITEM_REQUIRE_SPOLICE){
                v = layoutInflater.inflate(R.layout.list_item_crime_serious, parent, false);
            } else{
                v = layoutInflater.inflate(R.layout.list_item_crime, parent, false);     // упр 8 отображение специального отображения
            }

            return new CrimeHolder(v);
        }
        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bind(crime, position);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mIdModifiedElement = position;
                    mCallbacks.onCrimeSelected(crime);
                }
            });
        }
        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
        public void setCrimes(List<Crime> crimes) {
            mCrimes = crimes;
        }
        @Override
        public int getItemViewType(int position) {
            if (mCrimes.get(position).isSerious() == false)
                return TYPE_ITEM_NORMAL;
            return TYPE_ITEM_REQUIRE_SPOLICE;
        }

        public void deleteCrime(int position) {
            CrimeLab.get(getActivity()).deleteCrime(mCrimes.get(position));
            updateUI();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }
}
