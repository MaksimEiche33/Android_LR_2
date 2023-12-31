package mai.team2.android_lr_2;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    private List<Crime> mCrimes;
    private static CrimeLab sCrimeLab;
    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }
    private CrimeLab(Context context) { //Создание списка List объектов Crime
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase(); // подключение к базе данных и работа с ней (связь данного класса с базой данных)
    }
    public List<Crime> getCrimes() {
        return mCrimes;
    }
    public Crime getCrime(UUID id) {
        for (Crime crime : mCrimes) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }
}
