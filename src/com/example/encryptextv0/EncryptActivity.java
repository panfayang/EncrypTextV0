package com.example.encryptextv0;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EncryptActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_encrypt);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.encrypt, menu);
		return true;
	}
	
	// need to have this string in the outer class to use it outside of the alert
	private String name = "";
	
	public void encrypt(View view) {
		final EditText enterName = new EditText(this);
		enterName.setInputType(InputType.TYPE_CLASS_TEXT);
		enterName.setHint("Enter name");
		
		new AlertDialog.Builder(this)
			.setTitle(R.string.enter_a_key_name)
			.setView(enterName)
			.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						name = enterName.getText().toString();
						// Do encryption here maybe?
					}
			   })
			.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
				   public void onClick(DialogInterface dialog, int id) {
				   }
			   })
			.show();
		
//		Intent intent = new Intent(this, EncryptedActivity.class);
//		startActivity(intent);
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
			pasteData = (String) item.getText();
			
			if(pasteData == null) {
				Uri pasteUri = item.getUri();
				if(pasteUri != null) {
					ContentResolver cr = getContentResolver();
					Cursor pasteCursor = cr.query(pasteUri, null, null, null, null);
					if(pasteCursor != null) {
						if(pasteCursor.moveToFirst())
						{
							pasteData = pasteCursor.getString(0);
						}
					}
					pasteCursor.close();
				}
				else {
					//the copied item is not text, and not a Uri. That's bad.
					Toast toast = Toast.makeText(this, "Copied item format is unknown", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
			
			if(pasteData != null) {
				EditText edit = (EditText) findViewById(R.id.editTextEncrypted);
				edit.setText(pasteData);
			}
		}
	}
}
