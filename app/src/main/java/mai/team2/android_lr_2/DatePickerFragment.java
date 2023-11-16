package mai.team2.android_lr_2;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends Fragment {

    public static final String EXTRA_DATE = "com.bignerdranch.android.criminalintent.date";
    private static final String ARG_DATE = "date";
    private DatePicker mDatePicker;
    private Button mPositiveButton;
    private Date date_default;
    public static DatePickerFragment newInstance(Date date) { //Передача данных в DatePickerFragmen
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        date_default = (Date) getArguments().getSerializable(ARG_DATE);                                     //Извлечение даты и инициализация DatePicker
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date_default);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);                                                   // Извлечение даты и инициализация DatePicker

        View inflatedView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null); // подключение диалогового окна
        mDatePicker = (DatePicker) inflatedView.findViewById(R.id.dialog_date_picker);                   //Извлечение даты и инициализация DatePicker
        mDatePicker.init(year, month, day, null);                                      // выведение в календарь корректной даты

        mPositiveButton = (Button) inflatedView.findViewById(R.id.btn1);
        mPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = mDatePicker.getYear();
                int month = mDatePicker.getMonth();
                int day = mDatePicker.getDayOfMonth();
                Date date = new GregorianCalendar(year, month, day).getTime();
                date.setHours(date_default.getHours());
                date.setMinutes(date_default.getHours());

                //Toast.makeText(getActivity(), date.toString(), Toast.LENGTH_SHORT).show();
                //sendResult(Activity.RESULT_OK, date);
                //DatePickerFragment.super.dismiss();

                Intent intent = new Intent(getActivity(), CrimeFragment.class);
                intent.putExtra(EXTRA_DATE, date);
                getActivity().setResult(Activity.RESULT_OK,intent);
                getActivity().finish();
            }
        });

        return (inflatedView);
    }

//    private void sendResult(int resultCode, Date date) {  // сохранение даты
//        if (getTargetFragment() == null) {
//            return;
//        }
//        Intent intent = new Intent(getActivity(), CrimeFragment.class);
//        intent.putExtra(EXTRA_DATE, date);
//        getActivity().setResult(Activity.RESULT_OK,intent);
//
//        //setResult(Activity.RESULT_OK,intent);
//        //finish();
//        //getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
//    }
}
