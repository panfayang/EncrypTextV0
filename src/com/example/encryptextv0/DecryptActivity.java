/**
 * @author Kevin Davison, Sam Evans-Golden, Fayang Pan
 * 
 * This class handles incoming text. User can input or paste 
 * text into the EditText, and by pressing Decrypt button, 
 * an AlertDialog pops up for name of the key the user would 
 * like to use to decrypt the text.
 * 
 */

package com.example.encryptextv0;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DecryptActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_decrypt);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.decrypt, menu);
		return true;
	}
	
	public void decrypt(View view) {
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
	
	/*
	 * Paste item from the clipboard to the edittext.
	 * Taken mostly exactly from the Android Developer website.
	 */
	public void paste(View view) {
		String pasteData = "";
		ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
		
		if(!clipboard.hasPrimaryClip()) {
			Toast toast = Toast.makeText(this, "Nothing copied", Toast.LENGTH_SHORT);
			toast.show();
		}
		else if(!clipboard.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
			Toast toast = Toast.makeText(this, "Copied item isn't text", Toast.LENGTH_SHORT);
			toast.show();
		}
		else {
			ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
			pasteData = item.getText().toString();
			
			if(pasteData != null) {
				EditText edit = (EditText) findViewById(R.id.editTextDecrypted);
				edit.setText(pasteData);
			}
			else{
				Toast toast = Toast.makeText(this, "Copied item format is unknown", Toast.LENGTH_SHORT);
				toast.show();
			}
		}
	}
}
