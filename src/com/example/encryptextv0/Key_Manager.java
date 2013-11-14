package com.example.encryptextv0;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
	
	public ArrayList<String> getKeys(int id)
	{
		ArrayList<String> listRows = new ArrayList<String>();
		SQLiteDatabase db = this.getReadableDatabase();
		String[] keys = new String[]
				{
				KeyEntry.KEY_ID, KeyEntry.COLUMN_NAME_NAME, 
				KeyEntry.COLUMN_NAME_KEY }; 
		Cursor cursor = db.query(KeyEntry.TABLE_KEY, keys, KeyEntry.KEY_ID + "=?", null, null, null, null);
		if(cursor !=null)
		{
			cursor.moveToFirst();
		}
		while(cursor.isAfterLast()==false)
		{
			listRows.add(cursor.getString(0));
			listRows.add(cursor.getString(1));
			cursor.moveToNext();
		}
		if(cursor != null && !cursor.isClosed())
		{
			cursor.close();
		}
		return listRows;			
	}
	
	public void onDownGrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		onUpgrade(db, oldVersion, newVersion);
	} 
	
	public void deleteKey(String name)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(KeyEntry.TABLE_NAME, KeyEntry.COLUMN_NAME_NAME + "=" + name,null);
		db.execSQL("DROP TABLE IF EXISTS" + KeyEntry.TABLE_NAME);
	}
	
	
	
}
