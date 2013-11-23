package com.example.encryptextv0;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewPassword extends DialogFragment {
	
	SharedPreferences ePrefs;
	
	@Override
	public Dialog onCreateDialog(Bundle saveInstanceState){
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		// Get the layout inflater 
		LayoutInflater inflater = getActivity().getLayoutInflater();
		final View view = inflater.inflate(R.layout.activity_new_password, null);
		
		// Inflate and set the layout for the dialog
		// Pass null as the parent view because its going in the dialog layout

		builder.setView(view)
		// Add action buttons
		
			.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int id){
					String pw = ((EditText) view.findViewById(R.id.newpw)).getText().toString();
					String pwAgain = ((EditText) view.findViewById(R.id.newpwAgain)).getText().toString();
					String qn = ((EditText) view.findViewById(R.id.securityQn)).getText().toString();
					String ans = ((EditText) view.findViewById(R.id.securityAns)).getText().toString();

					if (pw.equals(pwAgain)){
						ePrefs = getActivity().getSharedPreferences("com.example.encryptextv0", Context.MODE_PRIVATE);
						ePrefs.edit().putString("password", pw).commit();
						ePrefs.edit().putString("securityQn", qn).commit();
						ePrefs.edit().putString("securityAns", ans).commit();

						Toast.makeText( getActivity(), "password successfully set", Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(getActivity(), KeyApp.class);
				    	startActivity(intent);
					}
					else{
						Toast.makeText( getActivity(), "Passwords did not match, please try again", Toast.LENGTH_SHORT).show();
					}
				}
			})
			.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id){
					NewPassword.this.getDialog().cancel();
				}
					
			});
		
		return builder.create();
	}
	
	
}
