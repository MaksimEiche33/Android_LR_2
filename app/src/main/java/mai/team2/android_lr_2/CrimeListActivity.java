package mai.team2.android_lr_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class CrimeListActivity extends SingleFragmentActivity
        implements CrimeListFragment.Callbacks{
    public void okClicked(){
        Crime crime = new Crime();
        CrimeLab.get(this).addCrime(crime);
        Intent intent = CrimeActivity.newIntent(this, crime.getId());
        startActivity(intent);
    }
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }


    @Override
    public void onCrimeSelected(Crime crime) {
        if (findViewById(R.id.detail_fragment_container) == null) {
            Intent intent = CrimeActivity.newIntent(this, crime.getId());
            startActivity(intent);
        } else {
            Fragment newDetail = CrimeFragment.newInstance(crime.getId());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, newDetail)
                    .commit();
        }
    }
}
