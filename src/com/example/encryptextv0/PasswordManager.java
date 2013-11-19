package com.example.encryptextv0;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordManager extends Activity {
	
	SharedPreferences ePrefs;
	SharedPreferences.Editor eEditor;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_password_manager);
		enterPassword(null);
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.password_manager, menu);
//		return true;
//	}
	public void enterPassword(View view) {
		//do decryption		
		final EditText pw = new EditText(this);
		pw.setInputType(InputType.TYPE_CLASS_TEXT);
		pw.setHint("Please enter your password");
		
		
		new AlertDialog.Builder(this)
			.setTitle(R.string.newPassword)
			.setView(pw)
			.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						ePrefs = getSharedPreferences("com.example.encryptextv0", MODE_PRIVATE);
						String input = pw.getText().toString();
						String verify = ePrefs.getString("password", ""); 
						if (input.equals(verify)){
							Intent intent = new Intent(getApplicationContext(), KeyApp.class);
					    	startActivity(intent);
							Toast.makeText(getApplicationContext(), "password successfully entered", Toast.LENGTH_SHORT).show();
						}
						else{
							Toast.makeText(getApplicationContext(), "Passwords did not match, please try again", Toast.LENGTH_SHORT).show();
							Intent intent = new Intent(getApplicationContext(), MainActivity.class);
					    	startActivity(intent);
						}
					}
			   })
			.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
				   public void onClick(DialogInterface dialog, int id) {
				   }
			   })
			.show();
	}
	public void decryjpt(View view) {
		//do decryption
		
		final EditText enterName = new EditText(this);
		enterName.setInputType(InputType.TYPE_CLASS_TEXT);
		enterName.setHint("Enter name");
		
		new AlertDialog.Builder(this)
			.setTitle(R.string.enter_a_key_name)
			.setView(enterName)
			.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						String name = enterName.getText().toString();
						Intent intent = new Intent(getApplicationContext(), DecryptedActivity.class);
						intent.putExtra("key", name);
						String text = ((EditText) findViewById(R.id.editTextDecrypted)).getText().toString();
						intent.putExtra("text", text);
						startActivity(intent);
					}
			   })
			.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
				   public void onClick(DialogInterface dialog, int id) {
				   }
			   })
			.show();
	}

}
