/**
 * @author Kevin Davison, Sam Evans-Golden, Fayang Pan
 * 
 * This is the Main Activity class. 
 */
package com.example.encryptextv0;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	SharedPreferences ePrefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void startEncryptActivity(View view) {
		Intent intent = new Intent(this, EncryptActivity.class);
		startActivity(intent);
	}

	public void startDecryptActivity(View view) {
		Intent intent = new Intent(this, DecryptActivity.class);
		startActivity(intent);
	}

	public void startManageKeysActivity(View view) {
		ePrefs = getSharedPreferences("com.example.encryptextv0", MODE_PRIVATE);
		String pword = ePrefs.getString("password", null);
//		Toast.makeText(getApplicationContext(), pword, Toast.LENGTH_SHORT).show();
		if (pword!=null){
			enterPassword(view);
		}
		else{
			FragmentManager fm = getSupportFragmentManager();
			NewPassword np = new NewPassword();
			np.show(fm, "New password");
		}
	}
	
	public void enterPassword(final View view) {
		//do decryption		
		final EditText pw = new EditText(this);
		pw.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
		pw.setHint("Please enter your password");
		
		
		new AlertDialog.Builder(this)
			.setTitle(R.string.enterPassword)
			.setView(pw)
			.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						ePrefs = getSharedPreferences("com.example.encryptextv0", MODE_PRIVATE);
						String input = pw.getText().toString();
						String verify = ePrefs.getString("password", ""); 
						if (input.equals(verify)){
							rightPassword(view);
//							Intent intent = new Intent(getApplicationContext(), KeyApp.class);
//					    	startActivity(intent);
//							Toast.makeText(getApplicationContext(), "password successfully entered", Toast.LENGTH_SHORT).show();
						}
						else{
							wrongPassword(view);
//							Toast.makeText(getApplicationContext(), "Passwords did not match, please try again", Toast.LENGTH_SHORT).show();
//							Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//					    	startActivity(intent);
						}
					}
			   })
			.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
				   public void onClick(DialogInterface dialog, int id) {
				   }
			   })
			.show();
	}
	
	public void rightPassword(View view) {
		//do decryption		
		final TextView pw = new TextView(this);
		pw.setText("right!");
		pw.setTextSize(20);
		
		new AlertDialog.Builder(this)
			.setTitle(R.string.right)
			.setView(pw)
			.setPositiveButton(R.string.proceed, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
							Intent intent = new Intent(getApplicationContext(), KeyApp.class);
					    	startActivity(intent);
					}
			   })
			.setNegativeButton(R.string.reset, new DialogInterface.OnClickListener() {
				   public void onClick(DialogInterface dialog, int id) {
					   FragmentManager fm = getSupportFragmentManager();
						NewPassword np = new NewPassword();
						np.show(fm, "New password");
				   }
			   })
			.show();
	}
	
	public void wrongPassword(View view) {
		//do decryption		
		final TextView pw = new TextView(this);
		pw.setText("wrong!");
		pw.setTextSize(20);
		
		new AlertDialog.Builder(this)
			.setTitle(R.string.wrong)
			.setView(pw)
			.setPositiveButton(R.string.forget, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						FragmentManager fm = getSupportFragmentManager();
						SecurityQn np = new SecurityQn();
						np.show(fm, "Security Qn");
					}
			   })
			.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
				   public void onClick(DialogInterface dialog, int id) {
				   }
			   })
			.show();
	}

}
