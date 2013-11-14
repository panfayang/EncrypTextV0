package com.example.encryptextv0;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class DecryptedActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_decrypted);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.decrypted, menu);
		return true;
	}
	
	/*
	 * Copy the message in the textview to the clipboard
	 */
	public void copy(View view) {
		TextView decrypted = (TextView) findViewById(R.id.textViewDecrypted);
		String message = decrypted.getText().toString();
		
		if(!message.equals("")) {
			ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
			ClipData clip = ClipData.newPlainText("Decrypted message", message);
			clipboard.setPrimaryClip(clip);
		}
	}

}
