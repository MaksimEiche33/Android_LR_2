package mai.team2.android_lr_2.database;

import static mai.team2.android_lr_2.database.CrimeDbSchema.CrimeTable.NAME;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.UUID;

import mai.team2.android_lr_2.database.CrimeDbSchema.CrimeTable;

public class CrimeBaseHelper extends SQLiteOpenHelper {           // описание автоматического помощника SQL при открытии базы данных

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "crimeBase.db";
    public  CrimeBaseHelper (Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ NAME+
                "( " + " _id integer primary key autoincrement, " +
                CrimeTable.Cols.UUID + ", " +
                CrimeTable.Cols.TITLE + ", " +
                CrimeTable.Cols.DATE + ", " +
                CrimeTable.Cols.SOLVED + ") "
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
