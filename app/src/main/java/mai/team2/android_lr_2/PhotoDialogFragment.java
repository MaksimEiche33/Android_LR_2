package mai.team2.android_lr_2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class PhotoDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("У вас пока нет происшествий!")
                .setMessage("Надо создать происшествие")
                .setIcon(R.drawable.hungrycat)
                .setPositiveButton("Создать происшествие", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //dialog.cancel();
                        //Toast.makeText(getActivity(),"dialog.cancel();",Toast.LENGTH_LONG).show();
                        ((CrimeListActivity) getActivity()).okClicked();
                    }
                });

        builder.setCancelable(true);
        return builder.create();
    }
}
