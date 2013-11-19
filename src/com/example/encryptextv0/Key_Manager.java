/**
 * @author Kevin Davison, Sam Evans-Golden, Fayang Pan
 * 
 * This is the database manager. This class handles adding, 
 * viewing and deleting keys.
 */

package com.example.encryptextv0;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.encryptextv0.Key_Contract.KeyEntry;


public class Key_Manager extends SQLiteOpenHelper

{
	private static final int DATABASE_VERSION = 1;
	private static final String CREATE_KEYS_TABLE = "CREATE TABLE " + KeyEntry.TABLE_NAME + 
			" (" +
			KeyEntry.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
			KeyEntry.COLUMN_NAME_NAME + " TEXT," + 
			KeyEntry.COLUMN_NAME_KEY + " TEXT" + ")";
	public Key_Manager(Context context)
	{
		super(context,KeyEntry.DB_NAME, null, DATABASE_VERSION );
	}
	@Override
	public void onCreate(SQLiteDatabase db) 
	{		
		db.execSQL(CREATE_KEYS_TABLE);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		db.execSQL("DROP TABLE IF EXISTS" + KeyEntry.TABLE_NAME);
		onCreate(db);
		
	}
	
	
	public void addKey(String name, String key)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KeyEntry.COLUMN_NAME_NAME, name);
		values.put(KeyEntry.COLUMN_NAME_KEY, key);
		try{db.insertOrThrow(KeyEntry.TABLE_NAME, null, values);}
		catch(Exception e)
		{
			Log.e("Error in inserting rows", e.toString());
			e.printStackTrace();
		}
		db.close();
	}
	
	public void onDownGrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		onUpgrade(db, oldVersion, newVersion);
	} 
	
	
}
