package com.example.encryptextv0;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class EncryptedActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_encrypted);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.encrypted, menu);
		return true;
	}
	
	/*
	 * Copy the message in the textview to the clipboard
	 */
	public void copyText(View view) {
		TextView encrypted = (TextView) findViewById(R.id.textViewEncrypted);
		String message = encrypted.getText().toString();
		
		if(!message.equals("")) {
			ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
			ClipData clip = ClipData.newPlainText("Decrypted message", message);
			clipboard.setPrimaryClip(clip);
		}
	}
	
	/*
	 * Copy the key to the clipboard
	 */
	public void copyKey(View view) {
		String message = "";
		//Need to get the key here somehow
		
		if(!message.equals("")) {
			ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
			ClipData clip = ClipData.newPlainText("Decrypted message", message);
			clipboard.setPrimaryClip(clip);
		}
	}

}
