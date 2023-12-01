package mai.team2.android_lr_2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;


public class PhotoDialogFragment extends DialogFragment {
    private static final String ARG_PATH = "path";
    private Button mPositiveButton;
    private ImageView mCrimePhoto;

    public static PhotoDialogFragment newInstance(String path) {                                     // создание аргументов ФРАГМЕНТА
        Bundle args = new Bundle();
        args.putSerializable(ARG_PATH, path);
        PhotoDialogFragment fragment = new PhotoDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @SuppressLint("MissingInflatedId")
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String path = (String) getArguments().getSerializable(ARG_PATH);                                   // получение пути фото из аргумента
        Bitmap bitmap = PictureUtils.getScaledBitmap(path, getActivity());                                 // получение сжатого изображения

        View inflatedView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_photo, null);

        mCrimePhoto = inflatedView.findViewById(R.id.crime_big_photo);
        mCrimePhoto.setImageBitmap(bitmap);                                                                // отображение изображения

        mPositiveButton = (Button) inflatedView.findViewById(R.id.btn1);
        mPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return inflatedView;
    }
}
