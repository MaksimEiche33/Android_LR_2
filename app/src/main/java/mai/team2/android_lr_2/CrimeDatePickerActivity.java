package mai.team2.android_lr_2;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.Date;
import java.util.UUID;

public class CrimeDatePickerActivity extends SingleFragmentActivity{

    public static Intent newIntent(Context packageContext, Date date) {
        Intent intent = new Intent(packageContext, CrimeDatePickerActivity.class);
        intent.putExtra(DatePickerFragment.EXTRA_DATE, date);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        Date date = (Date) getIntent().getSerializableExtra(DatePickerFragment.EXTRA_DATE);
        return DatePickerFragment.newInstance(date);
    }

}
