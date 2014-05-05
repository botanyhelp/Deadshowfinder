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

import android.app.ListActivity;
import android.app.Activity;
import android.content.Intent;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.net.Uri;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View;
import android.util.Log;

public class Date extends ListActivity {
	static final String TAG = "DEAD";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    displayListView();
	}


	private void displayListView() {
		List<String> dateList = new ArrayList<String>();
        dateList.add("1972_1");
        dateList.add("1972  _3");
        dateList.add("1972 _3");
        dateList.add("1972_3");
        dateList.add("1972_4");
        dateList.add("1972_5");
        dateList.add("1972_6");
        dateList.add("1972 _7");
        dateList.add("1972_7");
        dateList.add("1972_8");
        dateList.add("1972 _9");
        dateList.add("1972_9");
        dateList.add("1972_Date:10");
        dateList.add("1972_10");
        dateList.add("1972 _10");
        dateList.add("1972 _11");
        dateList.add("1972_11");
        dateList.add("1972:  _11");
        dateList.add("1972_12");
        dateList.add("1973_2");
        dateList.add("1973_3");
        dateList.add("1973_4");
        dateList.add("1973_5");
        dateList.add("1973_6");
        dateList.add("1973_7");
        dateList.add("1973_8");
        dateList.add("1973_9");
        dateList.add("1973_10");
        dateList.add("1973_11");
        dateList.add("1973_12");
        dateList.add("1974_2");
        dateList.add("1974_3");
        dateList.add("1974_5");
        dateList.add("1974_6");
        dateList.add("1974_7");
        dateList.add("1974_8");
        dateList.add("1974_9");
        dateList.add("1974_10");
        dateList.add("1975_3");
        dateList.add("1975_6");
        dateList.add("1975_8");
        dateList.add("1975_9");
        dateList.add("1976_6");
        dateList.add("1976_7");
        dateList.add("1976_8");
        dateList.add("1976_9");
        dateList.add("1976_10");
        dateList.add("1976_12");
        dateList.add("1977_2");
        dateList.add("1977_3");
        dateList.add("1977_4");
        dateList.add("1977_5");
        dateList.add("1977_6");
        dateList.add("1977_9");
        dateList.add("1978_1");
        dateList.add("1977_10");
        dateList.add("1977_11");
        dateList.add("1977_12");
        dateList.add("1978_2");
        dateList.add("1978_4");
        dateList.add("1978_5");
        dateList.add("1978_6");
        dateList.add("1978_7");
        dateList.add("1978_8");
        dateList.add("1978_9");
        dateList.add("1978_10");
        dateList.add("1978_11");
        dateList.add("1978_12");
        dateList.add("1979_1");
        dateList.add("1979_2");
        dateList.add("1979_4");
        dateList.add("1979_5");
        dateList.add("1979_6");
        dateList.add("1979_7");
        dateList.add("1979_8");
        dateList.add("1979_9");
        dateList.add("1979_10");
        dateList.add("1979_11");
        dateList.add("1979_12");
        dateList.add("1980_1");
        dateList.add("1980_3");
        dateList.add("1980_4");
        dateList.add("1980_5");
        dateList.add("1980_6");
        dateList.add("1980_7");
        dateList.add("1980_8");
        dateList.add("1980_9");
        dateList.add("1980_10");
        dateList.add("1980_11");
        dateList.add("1980_12");
        dateList.add("1981_2");
        dateList.add("1981_3");
        dateList.add("1981_4");
        dateList.add("1981_5");
        dateList.add("1981_7");
        dateList.add("1981_8");
        dateList.add("1981_9");
        dateList.add("1981_10");
        dateList.add("1981_11");
        dateList.add("1981_12");
        dateList.add("1982_2");
        dateList.add("1982_3");
        dateList.add("1982_4");
        dateList.add("1982_5");
        dateList.add("1982_7");
        dateList.add("1982_8");
        dateList.add("1982_9");
        dateList.add("1982_10");
        dateList.add("1982_11");
        dateList.add("1982_12");
        dateList.add("1983_3");
        dateList.add("1983_4");
        dateList.add("1983_5");
        dateList.add("1983_6");
        dateList.add("1983_7");
        dateList.add("1983_8");
        dateList.add("1983_9");
        dateList.add("1983_10");
        dateList.add("1983_12");
        dateList.add("1984_3");
        dateList.add("1984_4");
        dateList.add("1984_5");
        dateList.add("1984_6");
        dateList.add("1984_7");
        dateList.add("1984_10");
        dateList.add("1984_11");
        dateList.add("1984_12");
        dateList.add("1985_2");
        dateList.add("1985_3");
        dateList.add("1985_4");
        dateList.add("1985_6");
        dateList.add("1985_7");
        dateList.add("1985_8");
        dateList.add("1985_9");
        dateList.add("1985_10");
        dateList.add("1985_11");
        dateList.add("1985_12");
        dateList.add("1986_2");
        dateList.add("1986_3");
        dateList.add("1986_4");
        dateList.add("1986_5");
        dateList.add("1986_6");
        dateList.add("1986_7");
        dateList.add("1986_12");
        dateList.add("1987_1");
        dateList.add("1987_3");
        dateList.add("1987_4");
        dateList.add("1987_5");
        dateList.add("1987_6");
        dateList.add("1987_7");
        dateList.add("1987_8");
        dateList.add("1987_9");
        dateList.add("1987_10");
        dateList.add("1987_11");
        dateList.add("1987_12");
        dateList.add("1988_2");
        dateList.add("1988_3");
        dateList.add("1988_4");
        dateList.add("1988_5");
        dateList.add("1988_6");
        dateList.add("1988_7");
        dateList.add("1988_8");
        dateList.add("1988_9");
        dateList.add("1988_10");
        dateList.add("1988_12");
        dateList.add("1989_2");
        dateList.add("1989_3");
        dateList.add("1989_4");
        dateList.add("1989_5");
        dateList.add("1989_6");
        dateList.add("1989_7");
        dateList.add("1989_8");
        dateList.add("1989_9");
        dateList.add("1989_10");
        dateList.add("1989_12");
        dateList.add("1990_2");
        dateList.add("1990_3");
        dateList.add("1990_4");
        dateList.add("1990_5");
        dateList.add("1990_6");
        dateList.add("1990_7");
        dateList.add("1990_9");
        dateList.add("1990_10");
        dateList.add("1990_11");
        dateList.add("1990_12");
        dateList.add("1991_2");
        dateList.add("1991_3");
        dateList.add("1991_4");
        dateList.add("1991_5");
        dateList.add("1991_6");
        dateList.add("1991_8");
        dateList.add("1991_9");
        dateList.add("1991_10");
        dateList.add("1991_11");
        dateList.add("1991_12");
        dateList.add("1992_2");
        dateList.add("1992 _3");
        dateList.add("1992_3");
        dateList.add("1992_5");
        dateList.add("1992  _6");
        dateList.add("1992_6");
        dateList.add("1992_7");
        dateList.add("1992_12");

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.locationtextview, dateList);
		setListAdapter(dataAdapter);
		ListView listView = getListView();
		// filtering contents of ListView
		listView.setTextFilterEnabled(true);
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent i = new Intent("");
				String yearChoice = ((TextView) view).getText().toString();
				i.setData(Uri.parse(yearChoice));
				setResult(RESULT_OK, i);
				finish();
			}
		});
	}
}
