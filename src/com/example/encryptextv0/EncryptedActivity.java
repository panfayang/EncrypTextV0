/**
 * @author Kevin Davison, Sam Evans-Golden, Fayang Pan
 * 
 * This class handles encryption activity. It searches for 
 * the key with the input key name and tries to encrypt the 
 * input text with the key.
 * 
 * After encryption, the user can choose to copy the encrypted 
 * text, or copy the encryption key to give it to the recipient.
 * 
 */
package com.example.encryptextv0;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.algo.Encrypt;
import com.example.algo.Key;
import com.example.algo.KeyParser;
import com.example.encryptextv0.Key_Contract.KeyEntry;

public class EncryptedActivity extends Activity {
	
	private SQLiteDatabase db;
	private String key;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_encrypted);
		displayEncrypt(null);
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
			ClipData clip = ClipData.newPlainText("Encrypted message", message);
			clipboard.setPrimaryClip(clip);
		}
	}
	
	/*
	 * Copy the key to the clipboard
	 */
	public void copyKey(View view) {
		String message = this.key;
		
		if(!message.equals("")) {
			ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
			ClipData clip = ClipData.newPlainText("Encrypted message", message);
			clipboard.setPrimaryClip(clip);
		}
	}
	
	private String getKeyFromDB()
	{
		try{
			Key_Manager key_Manager = new Key_Manager(this.getApplicationContext());
			db = key_Manager.getWritableDatabase();
			String[] keys = {
					KeyEntry.COLUMN_NAME_NAME,
					KeyEntry.COLUMN_NAME_KEY
			};
			Cursor cursor = db.query(KeyEntry.TABLE_NAME, keys, null, null, null, null, null);
			if(cursor != null)
				cursor.moveToFirst();

			do
			{
				String keyName = cursor.getString(cursor.getColumnIndex(KeyEntry.COLUMN_NAME_NAME));
				String keyKey = cursor.getString(cursor.getColumnIndex(KeyEntry.COLUMN_NAME_KEY));
				if(keyName.equals(getIntent().getStringExtra("key"))){
					Toast.makeText(getApplicationContext(), keyName+" was found!" , Toast.LENGTH_SHORT).show();
					this.key = keyKey;
					return keyKey;
				}
			}while(cursor.moveToNext());
			Toast.makeText(getApplicationContext(), getIntent().getStringExtra("key")+" was not found :(" , Toast.LENGTH_SHORT).show();
			return "";
		}

//		catch (SQLiteException se)
		catch(Exception e)
		{

			Toast.makeText(getApplicationContext(), "Database empty", Toast.LENGTH_SHORT).show();
//			Log.e("could not create key", null);	
			return "";
		}
	}
	
	public ArrayList<Key> getKey(){
		KeyParser kp = new KeyParser();
		return kp.parseToKey(getKeyFromDB());
	}
	
	public void displayEncrypt(View view){
		Encrypt encrypt = new Encrypt();
		String encrypted = encrypt.encrypt(getIntent().getStringExtra("text"), getKey());
		((TextView) findViewById(R.id.textViewEncrypted)).setText(encrypted);
	}


}
