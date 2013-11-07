package com.example.encryptextv0;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

public class SenderNameFragment extends DialogFragment {
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		
		builder.setTitle(R.string.enterSender)
			   .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// maybe start the decrypted activity here?
					}
			   })
			   .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
				   public void onClick(DialogInterface dialog, int id) {
					   // close the dialog maybe?
				   }
			   })
			   .setView(inflater.inflate(R.layout.dialog_enter_name, null));
		
		return builder.create();
	}
}
