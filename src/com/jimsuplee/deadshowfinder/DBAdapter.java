/**Copyright (C) 2013 Thomas Maher
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.jimsuplee.deadshowfinder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

public class DBAdapter {
	
	static final String TAG = "DEAD";
	
	static final String DATABASE_NAME = "deadsets";
	static final String DATABASE_TABLE = "deadsets";
	static final int DATABASE_VERSION = 1;
	
	static final String location = "location";
	static final String date = "date";
	static final String setone = "setone";
	static final String settwo = "settwo";
	static final String setthree = "setthree";
	
	static final String DATABASE_CREATE = "CREATE TABLE deadsets (location text default null, date text default null, setone text default null, settwo text default null, setthree text default null);";
	//CREATE TABLE deadsets (location text default null, date text default null, setone text default null, settwo text default null, setthree text default null);

	final Context context;

	DatabaseHelper DBHelper;
	SQLiteDatabase db;

	public DBAdapter(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
				db.execSQL(DATABASE_CREATE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS deadsets");
			onCreate(db);
		}
	}

	public DBAdapter open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		DBHelper.close();
	}

	public Cursor getByLocation(String locationParam) throws SQLException {
		String[] columns = new String[] {location,date,setone,settwo,setthree};				
		String selection;
		//selection = "Country=? ORDER BY Startyear LIMIT 100";
		selection = "location=?";
		String[] selectionArgs = new String[] { locationParam };
		//String orderBy = Startyear;
		////Log.w(TAG, "In DBAdapter.getByLocation(), About to make Cursor, c, with db.query()");
		//WORKS: unsorted:
		//Cursor mCursor = db.query(DATABASE_TABLE, columns, selection, selectionArgs, null, null, null);
		//NOT WORK: fails because this doesn't work in SQLite:
		// sqlite> SELECT Startyear from emdata WHERE Country="Afghanistan" LIMIT 5 ORDER BY Startyear;
		// Error: near "ORDER": syntax error
		//Cursor mCursor = db.query(DATABASE_TABLE, columns, selection, selectionArgs, null, null, orderBy);
		//The LIMIT in db.query is the last (and apparently optional) argument.  Its a String:
		//String limit = "100";
		// ...but we will have a problem because we will have to manage the paging in the LIMIT argument. 
		// We will have to set it next to """ String limit = "200,100" """...and so on. 
		// Managing the LIMIT string won't be too difficult because we are already managing it by tracking 
		// the pagerCounterTotal.  But that is an integer and easy to increment.  Even so, the way to 
		// do paging is by managing the limit String in the db.query().
		//Cursor mCursor = db.query(DATABASE_TABLE, columns, selection, selectionArgs, null, null, orderBy, limit);
		//Cursor mCursor = db.query(DATABASE_TABLE, columns, selection, selectionArgs, null, null, orderBy);
		Cursor mCursor = db.query(DATABASE_TABLE, columns, selection, selectionArgs, null, null, null);
		////Log.w(TAG, "In DBAdapter.getByLocation(String location), About to check if Cursor c is null");
		if (mCursor != null) {
			////Log.w(TAG,"In DBAdapter.getByLocation(String location), c is NOT null, about to c.moveToFirst()");
			mCursor.moveToFirst();
		}
		////Log.w(TAG, "In DBAdapter.getByLocation(String location), about to return cursor, c");
		return mCursor;
	}	
	//public Cursor getBySongs(String songoneParam, String songtwoParam, String songthreeParam) throws SQLException {
	public Cursor getBySongs(String songsParam) throws SQLException {
		String[] columns = new String[] {location,date,setone,settwo,setthree};				
		String selection;
		//selection = "Country=? ORDER BY Startyear LIMIT 100";
		//selection = "location=?";
		//selection = "setone LIKE ? OR settwo LIKE ?";
		selection = "setone LIKE ? OR settwo LIKE ?";
	    //String selectionConcatenation = songoneParam+"_"+songtwoParam+"_"+songthreeParam;
		//String[] selectionArgs = new String[] { selectionConcatenation, selectionConcatenation };
		String[] selectionArgs = new String[] { songsParam, songsParam };
		//Log.w(TAG, "In DBAdapter.getBySongs, about to dbquery("+selection+" "+selectionArgs[0]);
        Cursor mCursor = db.query(DATABASE_TABLE, columns, selection, selectionArgs, null, null, null);
		////Log.w(TAG, "In DBAdapter.getByLocation(String location), About to check if Cursor c is null");
		if (mCursor != null) {
			////Log.w(TAG,"In DBAdapter.getByLocation(String location), c is NOT null, about to c.moveToFirst()");
			mCursor.moveToFirst();
		}
		////Log.w(TAG, "In DBAdapter.getByLocation(String location), about to return cursor, c");
		return mCursor;
	}
	public Cursor getByDate(String dateParam) throws SQLException {
		String[] columns = new String[] {location,date,setone,settwo,setthree};				
		String selection;
		//selection = "Country=? ORDER BY Startyear LIMIT 100";
		//selection = "location=?";
		//selection = "setone LIKE ? OR settwo LIKE ?";
		//selection = "setone LIKE ? OR settwo LIKE ?";
		String dateParamSubstring = dateParam.substring(2);
		String[] dataParamSplit = dateParamSubstring.split("_");
		String selectionParam = dataParamSplit[1]+"%"+dataParamSplit[0]+"%";
		String[] selectionArgs = new String[] { selectionParam };
		selection = "date LIKE ?";
	    //String selectionConcatenation = songoneParam+"_"+songtwoParam+"_"+songthreeParam;
		//String[] selectionArgs = new String[] { selectionConcatenation, selectionConcatenation };
		//String[] selectionArgs = new String[] { dateParam };
		//Log.w(TAG, "In DBAdapter.getByDate, about to dbquery("+selection+" "+selectionArgs[0]);
        Cursor mCursor = db.query(DATABASE_TABLE, columns, selection, selectionArgs, null, null, null);
		////Log.w(TAG, "In DBAdapter.getByLocation(String location), About to check if Cursor c is null");
		if (mCursor != null) {
			////Log.w(TAG,"In DBAdapter.getByLocation(String location), c is NOT null, about to c.moveToFirst()");
			mCursor.moveToFirst();
		}
		////Log.w(TAG, "In DBAdapter.getByLocation(String location), about to return cursor, c");
		return mCursor;
	}
}
