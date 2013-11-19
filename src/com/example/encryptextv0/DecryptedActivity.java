/**
 * @author Kevin Davison, Sam Evans-Golden, Fayang Pan
 * 
 * This class handles decryption activity. It searches for 
 * the key with the input key name and tries to decrypt the 
 * input text with the key.
 * 
 * After decryption, the user can choose to copy the decrypted 
 * text.
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

import com.example.algo.Decrypt;
import com.example.algo.Key;
import com.example.algo.KeyParser;
import com.example.encryptextv0.Key_Contract.KeyEntry;

public class DecryptedActivity extends Activity {
	
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_decrypted);
		displayDecrypt(null);
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
	public void copyText(View view) {
		
		TextView decrypted = (TextView) findViewById(R.id.textViewDecrypted);
		String message = decrypted.getText().toString();
		
		if(!message.equals("")) {
			ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
			ClipData clip = ClipData.newPlainText("Decrypted message", message);
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
					return keyKey;
				}
			}while(cursor.moveToNext());
			Toast.makeText(getApplicationContext(), getIntent().getStringExtra("key")+" was not found :(" , Toast.LENGTH_SHORT).show();
			return "";
		}

//		catch (SQLiteException se)
		catch(Exception e)
		{
//			Log.e("could not create key", null);	
			Toast.makeText(getApplicationContext(), "Database empty", Toast.LENGTH_SHORT).show();
			return "";
		}
	}
	
	public ArrayList<Key> getKey(){
		KeyParser kp = new KeyParser();
		return kp.parseToKey(getKeyFromDB());
	}
	
	public void displayDecrypt(View view){
		Decrypt decrypt = new Decrypt();
		String decrypted = decrypt.decrypt(getIntent().getStringExtra("text"), getKey());
		((TextView) findViewById(R.id.textViewDecrypted)).setText(decrypted);
	}


}
