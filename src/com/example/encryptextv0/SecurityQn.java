/**
 * @author Kevin Davison, Sam Evans-Golden, Fayang Pan
 * 
 * This class sets up a dialog for the user to answer 
 * the security question he/she set up.
 */

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
import android.widget.TextView;
import android.widget.Toast;

public class SecurityQn extends DialogFragment {

	SharedPreferences ePrefs;

	@Override
	public Dialog onCreateDialog(Bundle saveInstanceState){
		ePrefs = getActivity().getSharedPreferences("com.example.encryptextv0", Context.MODE_PRIVATE);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		// Get the layout inflater 
		LayoutInflater inflater = getActivity().getLayoutInflater();
		final View view = inflater.inflate(R.layout.activity_security_qn, null);
		builder.setView(view);
		TextView tv = (TextView)view.findViewById(R.id.securityQnCheck);
		tv.setText("Your security qn: \n" + ePrefs.getString("securityQn", "error: no question specified"));
		//		final View view = inflater.inflate(R.layout.activity_security_qn, null);



		// Inflate and set the layout for the dialog
		// Pass null as the parent view because its going in the dialog layout

		builder.setView(view)
		// Add action buttons

		.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int id){
				ePrefs = getActivity().getSharedPreferences("com.example.encryptextv0", Context.MODE_PRIVATE);
				String ans;
				try{
					ans = ((EditText) view.findViewById(R.id.securityAnsCheck)).getText().toString();
				}
				catch(Exception e){
					ans = null;	
				}
				String rightAns = ePrefs.getString("securityAns", "");

				if (ans.equals(rightAns)){
					Toast.makeText( getActivity(), "answer is right, your password was: " + ePrefs.getString("password", ""), Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(getActivity(), KeyApp.class);
					startActivity(intent);
				}
				else{
					Toast.makeText( getActivity(), "Wrong answer, please try again", Toast.LENGTH_SHORT).show();
				}


			}
		})
		.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id){
				SecurityQn.this.getDialog().cancel();
			}

		});

		return builder.create();
	}


}
