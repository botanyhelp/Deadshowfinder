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

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.net.Uri;
import android.widget.TextView;
import android.widget.Button;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.widget.Toast;
import android.content.Intent;
import android.database.Cursor;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.DatePicker;
import android.widget.Toast;
import java.util.Date;
import java.text.SimpleDateFormat;
import android.widget.ImageView;
import java.util.HashMap;
import java.util.Map;
import android.widget.AutoCompleteTextView;
import android.widget.ArrayAdapter;

public class Deadshowfinder extends Activity {
	DBAdapter db;
	static final String TAG = "DEAD";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deadshowfinder);
		try {
			String destPath = "/data/data/" + getPackageName() + "/databases";
			File f = new File(destPath);
			if (!f.exists()) {
				f.mkdirs();
				f.createNewFile();
				Toast.makeText(this, "Please Wait...Database Being Created",
						Toast.LENGTH_LONG).show();
				CopyDB(getBaseContext().getAssets().open("deadsets"),
						new FileOutputStream(destPath + "/deadsets"));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Now that we (hopefully) have an SQLiteDatabase available, lets make
		// an adapter and put it
		// into our global variable.
		db = new DBAdapter(this);
	    String[] allSongs = {"","Addams Family","Aint Superstitious","Alabama Getaway","All Along the Watchtower","All Along The Watchtower","Althea","A Mind to Give Up Livin","Are You Lonely For Me","Around and Around","Around Jam","Attics of My Life","Baba ORiley","Baby Blue","Baby What You Want Me to Do","Bad Moon Rising","Ballad of a Thin Man","Banana Boat Song","Banks of Ohio","Barbry Allen","Beat it on Down the Line","Been All Around This World","Believe it or Not","Bertha","B.E,Women","Big Boss Man","Big Boy Pete","Big Railroad Blues","Big River","Big River ","Big RIver","Bird Song","Blackbird","Black Muddy River","Black Peter","Black Queen","Black Throated Wind","Black-Throated Wind","Blow Away","Blues For Allah","Blues For Allah Jam","Bobby McGee","Born on the Bayou","Box of Rain","Box Of Rain","Broadcast","Brokedown Palace","Broken Arrow","Brown Eyed Women","Built to Last","BW, JG, MH, PL","Bye Bye Love","California Earthquake","Candyman","Casey Jones","Casey Jones ","Cassidy","Cassidy ","Caution","Caution Jam","C.C. Rider","Children of the Eighties","Chimes of Freedom","China Cat Sunflower","China Cat Sunflower ","China Doll","Chinatown Shuffle","Chinese Bones","Close Encounters","Cold Rain and Snow","Comes a Time","Comes A Time","Corina","Cosmic Charlie","Crazy Fingers","Cryptical Envelopment","Cumberland Blues","Dancin in the Streets","Dark Hollow","Dark Star","Dark Star Reprise","Day Job","Day Tripper","Dead Man, Dead Man","Deal","Dear Mr. Fantasy","Death Dont Have No Mercy","Deep Elem Blues","Desolation Row","Devil With a Blue Dress","Dire Wolf","Dont Ease","Dont Ease Me In","Dont Need Love","Dont Think Twice Its Alright","Down in the Bottom","drums","Drums","Drums ","Duprees Diamond Blues","Easy Answers","Easy to Love You","El Paso","El Paso ","Estimated Prophet","Eternity","Every Time You Go","Eyes of the World","Far From Me","Feel Like a Stranger","Fever","Fire on the Mountain","Fire On the Mountain","Foolish Heart","Forever Young","Frankie Lee and Judas Priest","Franklins Tower","Frere Jacques","Friend of the Devil",">From the Heart of Me","Frozen //Logger","Funiculi Funicula","GDTFB","Gentlemen Start Your Engines","Get Back","Gimme Some Lovin","Gloria","Gloria Jam","Goin Down the Road Feelin Bad","Goin Down the Road Feeling Bad","Good Golly Miss Molly","Good Lovin","Good Morning Little School Girl","Good Mornin Little School Girl","Goodnight Irene","Good Time Blues","Gotta Serve Somebody","Greatest Story Ever Told","Green River","Hamza El-Din, Matt Kelly","Happy Birthday","Happy Birthday Mickey","Hard to Handle","Heart of Mine","Heaven Help Jam","Heaven Help the Fool","Hell in a Bucket","Hell In a Bucket","Help on the Way","Here Comes Sunshine","Here Come Sunshine","Hes Gone","Hey Bo Diddiey","Hey Bo Diddley","Hey Jude Reprise","Hey Pocky Way","Hideaway","High Time","Highway 61","How Long Blues","How Sweet It Is","Hully Gully","Hurts Me Too","If I Had the World to Give","I Fought the Law","I Just Wanna Make Love to You","I Know You Rider","Iko Iko","I Kow You Rider","Ill Be Your Baby Tonight","Im a Man","Im A Man","I Need a Miracle","It Must Have Been the Roses","Its All Over Now","Its All Over Now, Baby Blue","Its a Sin","It Take A Lot to Laugh. It Take A Train to Cry","It Takes a Train to Cry","I Want You","I Will Take You Home","Jack-a-Roe","Jack-A-Roe","Jack Stnaw","Jack Straw","jam","Jam","JG, BW","JG, BW, BK, MH, JKahn","JG, BW, MH, BK, BM, JKahn","Joey","John Brown","Johnny B. Goode","Johnny B. Goode ","JohnnyB.Goode","Just a Little Light","Kansas City","Keep on Growing","King Bee","Knockin on Heavens Door","La Bamba","Lady Di","Lazy Lightnin","Lazy River Road","Let it Grow","Let It Grow","Let it Rock","Let Me Sing Your Blues Away","Let the Good Times Roll","Liberty","Linda Lou","Little Red Rooster","Little Sadie","Long Way Home","Long Way to Go Home","Looks Like Rain","Loose Lucy","Loos Lucy","Loser","Lost Sailor","Louie Louie","Love the One Youre With","Lucifers Eyes","Lucy in the Sky With Diamonds","Mack the Knife","Maggies Farm","Mama Tried","Man of Peace","Man Smart/Woman Smarter","Masterpiece","Maybe You Know How I Feel","Me and Bobby McGee","Me and My Uncle","Me and My UNcle","Me & M Uncle","Me & My Uncle","Mexicali Blues","Mexicall","Midnight Hour","Might as Well","Might As Well","Mind Left Body Jam","Mission in the Rain","Mississippi Half-Step","Mojo","Mojo Jam","Momin Dew","Mona","Money Money","Monkey and the Engineer","Morning Dew","Mountain Jam","Mr Charlie","Mr. Charlie","Mr. Tambourine Man","Music Never Stopped","My Baby Left Me","My Brother Esau","Neighborhood Girls","New Minglewood Blues","New Orleans","New Speedway Boogie","Next Time You See Me","Nobodys Fault But Mine","Nobodys Jam","Not Fade Away","Oh Babe it Aint No Lie","Oh Boy","Ollin Arrageed","One More Saturday Night","One More Saturday Night  ","One More Saturday Night Reprise","Only a Fool","On the Road Again","Other One Jam","Passenger","Pay-Per-View","Peggy-O","Picasso Moon","Playing in the Band","Playin in the Band","Playin Reprise","Promised Land","Proud Mary","Qave That Flag","Queen Jane Approximately","Quinn the Eskimo","Rain","Rainy Day Woman","Ramble  On","Ramble on Rose","Ramble On Rose","Revolution","Revolutionary Hamstrung Blues","Ripple","Roadrunner","Rockin Pneumonia","Rockin Pneumonia","Rosalie McFall","Row Jimmy","Rubin and Cherise","Sage and Spirit","Saint of Circumstance","Same Thing","Samson and Delilah","Satisfaction","Saturday Nigh","Saturday Night","Scarlet Begonias","Shakedown Street","She Belongs to Me","Shelter From the Storm","Ship of Fools","Ship Of Fools","Sidewalks of New York","Simple Twist of Fate","Sing Me Back Hom","Sing Me Back Home","Slipknot!","Slow Train Comin","Smokestack Jam","Smokestack Lightnin","Smokestack Lightning","So Many Roads","space","Space","Spanish Jam","Spoonful","Stagger Lee","Stander on the Mountain","Standing on the Moon","Stella Blue","Step Back","Stir it Up","Stir It Up","Stronger Than Dirt","St. Stephen","Stuck Inside of Mobile","Suagaree","Sugaree","SUgaree","Sugar Magnolia","Sugar Magnolia ","Sugar Magnolla","Sugar Magnolla ","Sugar Shack","Sunrise","Sunshine Daydream","Supplication","Supplication Jam","Take it Off","Tangled Up in Blue","Tell Mama","Tenessee Jed","Tennessee Jed","Tennesse Jed","Terrapin Station","Thats Alright Mama","That Would Be Something","The Alhambra","The Boxer","The Days Between","The Eleven","The Eleven Jam","The Frozen //Logger","The Last Time","The Other One","The Race is On","The Race Is On","The Same Thing","The Weight","The Wheel","They Love Each Other","This Could Be the Last Time","This Time Forever","Throwin Stones","Throwin Stones Reprise","Times They Are A-Changin","To Lay Me Down","Tom Dooley","Tomorrow is a Long Time","Tomorrow is Forever","Tomorrow Is Forever","Tomorrow Never Knows","Tom Thumbs Blues","Tons of Steel","Top Of The World","Tore Up Over You","Touch of Grey","Touch Of Grey","Truckin","TrucKin","Turn on Your Love Light","Turn On Your Lovelight","Turn On Your Lovelight ","Turn On Your Love Light","Two Souls In Communion","Uncle Johns Band","Uncle Johns Band ","Uncle Johns Bands Jam","U.S. Blues","Valley Road","Victim or the Crime","Visions of Johanna","Walkin Blues","Walkin the Dog","Wang Dang Doodle","Warriors of the Sun","Watching the River Flow","Wave That Flag","Wave to the Wind","We Bid You Goodnight","We Can Run But We Cant Hide","Werewolves of London","West L.A. Fadeaway","Wharf Rat","Whar Rat","Whats Going On","When I Paint My Masterpiece","When Push Comes to Shove","Where Have the Heroes Gone","Who Do You Love","Why Dont We Do it in the Road","Wicked Messenger","Willie and the Hand Jive","Winin Boy Blues","WRS Part 1","WRS Part l","WRS Prelude","You Aint Woman Enough","Your Love At Home","You Win Again","You Wont Find Me"};
		//String[] allSongs = {"Aiko Aiko",  "Ain\'t It Crazy (The Rub)",  "Ain\'t Superstitious / Meet Me on the Bottom",  "Alabama Getaway",  "Alligator",  "All Over Now",  "Althea",  "And We Bid You Goodnight",  "Around and Around",  "Attics of My Life",  "Babe, It Ain\'t No Lie",  "Baby, What Do You Want Me To Do",  "Ballad of Casey Jones",  "Bear Tracks",  "Beat it on Down The Line",  "Bertha",  "Big Boss Man",  "Big Boy Pete",  "Big Railroad Blues",  "Big River",  "Bird Song",  "Black Muddy River",  "Black Peter",  "Black Throated Wind",  "Blues For Allah",  "Bo Diddley",  "Bondi Pier",  "Born Cross-Eyed",  "Box of Rain",  "Boys in the Barroom",  "Bring Me My Shotgun",  "Brokedown Palace",  "Brother Esau",  "Brown-Eyed Women",  "Built to Last",  "Built To Last (cont.)",  "Candyman",  "Can\'t Come Down",  "Cassidy",  "Catfish John",  "Cats Under the Stars",  "Caution",  "C. C. Rider",  "China Cat Sunflower",  "China Doll",  "Chinatown Shuffle",  "Cocaine",  "Cocaine Habit Blues",  "Cold Jordan",  "Cold Rain and Snow",  "Comes A Time",  "Confusion Prince",  "Cosmic Charlie",  "Crazy Fingers",  "Cream Puff War",  "Cumberland Blues",  "Dancing in the Streets",  "Dark Hollow",  "Day Job",  "Day Tripper",  "Deal",  "Dear Mr. Fantasy",  "Dear Prudence",  "Death Don\'t Have No Mercy",  "Deep Elem Blues",  "Desolation Row",  "Dire Wolf",  "Doin\' That Rag",  "Don\'t Ease Me In",  "Don\'t Need Love",  "Don\'t Think Twice, It\'s All Right",  "Drink Up and Go Home",  "Dupree\'s Diamond Blues",  "Early Morning Rain",  "Easy To Love You",  "Easy Wind",  "The Eleven",  "El Paso",  "El Paso...",  "Empty Pages",  "Estimated Prophet",  "Eyes Of The World",  "Far From Me",  "Feel Like A Stranger",  "Fire On The Mountain",  "Forever Young",  "France",  "Franklin\'s Tower",  "Friend of the Devil",  "From the Heart of Me",  "Get Back",  "Gimme Some Lovin\'",  "Gloria",  "Goin\' Down The Road Feelin\' Bad",  "The Golden Road (to unlimited devotion)",  "Gomorrah",  "Good Lovin\'",  "Good Morning Little Schoolgirl",  "Goodnight Irene",  "Greatest Story Ever Told",  "Green, Green Grass of Home",  "The Harder They Come",  "Hard to Handle",  "Heaven Help The Fool",  "Hell in a Bucket",  "Help on the Way",  "Henry",  "Here Comes Sunshine",  "He\'s Gone",  "He Was A Friend of Mine",  "Hey Little One",  "High Heeled Sneakers",  "High Time",  "Hitch Hiking Woman",  "How Long Blues",  "How Sweet It Is",  "Hully Gully",  "Hurts Me Too",  "I Believe",  "If I Had The World To Give",  "I Hear A Voice Calling",  "I Just Wanna Make Love To You",  "I Know You Rider",  "I\'ll Take A Melody",  "I\'m A Hog For You",  "I Need A Miracle",  "I Saw Her Standing There",  "It Must Have Been The Roses",  "It\'s All Over Now Baby Blue",  "It\'s A Man\'s World",  "It Takes A Lot To Laugh, It Takes A Train To Cry",  "I\'ve Been All Around This World",  "I Wash My Hands",  "Jack-A-Roe",  "Jack Straw",  "We can share the women, we can share the wine.",  "Johnny B. Goode",  "Kansas City",  "Katie Mae",  "K. C. Road",  "Keep On Growing",  "King Bee",  "Knockin\' On Heaven\'s Door",  "Last Train to Jacksonville",  "Lazy Lightnin\'",  "Let It Rock",  "Let Me In 83968",  "Let Me Sing Your Blues Away",  "Lindy",  "Little Red Rooster",  "Little Sadie",  "Little Star ?",  "Logger Song",  "Long Black Limousine",  "Looks Like Rain",  "Loose Lucy",  "Loser",  "Lost Sailor",  "Louie, Louie",  "Love in the Afternoon",  "Mama Tried",  "Man Smart, Women Smarter",  "Mason\'s Children",  "Maybe You Know",  "Me and Bobby McGee",  "Me and My Uncle",  "Mercy of a Fool",  "Mexicali Blues",  "Midnight Hour",  "Midnight Moonlight",  "Might As Well",  "Mission in the Rain",  "Mississippi Halfstep Uptown Toodeloo",  "Mister Charlie",  "Money, Money",  "Monkey and the Engineer",  "Morning Dew",  "Mountains of the Moon",  "The Music Never Stopped",  "My Baby",  "Mystery Train",  "Never Trust A Woman",  "New Minglewood Blues",  "All New Minglewood Blues",  "New Orleans",  "New Potato Caboose",  "New Speedway Boogie",  "Next Time You See Me",  "The Night They Drove Ole Dixie Down",  "Nobody\"s Fault But Mine",  "Not Fade Away",  "No Tomorrow",  "Oh Boy",  "Oakie From Muskogee",  "One More Saturday Night",  "One Thing To Try",  "On The Road Again",  "Operator",  "Palm Sunday",  "Passenger",  "Playin\' In The Band",  "Positively 4th Street",  "Pretty Peggy-O",  "Pride of Cucamonga",  "Promised Land",  "Promontory Rider",  "Queen Of Santa Fe I",  "Queen Of Santa Fe II (Poor Michael Went Down)",  "Quin The Eskimo",  "The Race Is On",  "Rain",  "Ramble On Rose",  "Revolution",  "Revolutionary Hamstrung Blues",  "Rhapsody In Red",  "Ripple",  "Road Runner",  "Rockin\' Pneumonia",  "Rosalie McFall",  "Rosemary",  "Row Jimmy",  "Rubin and Cherise",  "Run for the Roses",  "Run, Rudolph Run",  "Saint of Circumstance",  "Saint Stephen",  "The Same Thing",  "Samson and Delilah",  "Satisfaction",  "Save the Whales",  "Sawmill",  "Scarlet Begonias",  "Searchin\'",  "I Second That Emotion",  "Shakedown Street",  "She Belongs To Me",  "She\'s Mine",  "Ship of Fools",  "Silver Threads and Golden Needles",  "Simple Twist Of Fate",  "Sing Me Back Home",  "Sitting In Limbo",  "Sitting on Top of the World",  "Smokestack Lightnin\'",  "So Far From Me",  "Stagger Lee",  "Stealin\'",  "Stella Blue",  "Sugaree",  "Sugar Magnolia",  "Sunrise",  "Supplication",  "Swing Low Sweet Chariot",  "Tangled Up In Blue",  "Tell Mama",  "Tennessee Jed",  "Terrapin Station - Lady With a Fan",  "That\'s All Right, Mama",  "That\'s It For The Other One",  "They Love Each Other",  "This Time Forever",  "Throwing Stones",  "Till The Morning Comes",  "To Lay Me Down",  "Tom Dooley",  "Tomorrow Is Forever",  "Tom Thumb Blues",  "100,000 Tons of Steel",  "Tore Up Over You",  "Touch of Grey",  "Truckin\'",  "Turn On Your Love Light",  "Two Souls in Communion",  "Two Women",  "Unbroken Chain",  "Uncle John\'s Band",  "U.S. Blues",  "Valerie",  "Viola Lee Blues",  "Visions of Johanna",  "Wake Up Little Susie",  "Walking Blues",  "Walking the Dog",  "Walk in the Sun",  "Walk in the Sunshine",  "Wang Dang Doodle",  "Wave That Flag",  "Weather Report Suite (Part I)",  "Weather Report Suite (Part II) - Let It Grow",  "Werewolves of London",  "West L.A. Fadeaway",  "Wharf Rat",  "What\'s Become of the Baby",  "What Will You Raise",  "The Wheel",  "When I Paint My Masterpiece",  "When Push Comes to Shove",  "Who Do You Love",  "Why Don\'t We Do It In The Road",  "Willie and the Hand Jive",  "Wining Boy Blues",  "Yellow Moon",  "You Ain\'t Woman Enough To Take My Man",  "You Don\'t Have To Ask",  "Youngblood",  "You Win Again",  "====",  "SO MANY ROADS",  "	 Broken Arrow by Robbie Robertson",  "Corina (as performed on the 24th of Feb 1992)",  "Cruel White Water --Robert Hunter",  "DAYS BETWEEN",  "Don\'t Let Go (Jesse Stone), first performed by Roy Hamilton",  "ETERNITY",  "Gentlemen, Start Your Engines",  "\"I Fought The Law\" was ",  " Lazy River Road",  " Liberty by Robert Hunter",  "LIKE A ROAD",  "Little Sadie",  "It\'s A Long Long Way (as performed on 24th of Feb 1992)",  "MAGGIE\'S FARM",  "The Maker -- Daniel Lanois",  "Man Smart, Women Smarter",  "Mississippi Moon",  "Shining Star words and music by Leo Graham and Paul Richmond",  "Unbroken Chain"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, allSongs);
        AutoCompleteTextView textViewone = (AutoCompleteTextView) findViewById(R.id.txtallsongsone);
        AutoCompleteTextView textViewtwo = (AutoCompleteTextView) findViewById(R.id.txtallsongstwo);
        AutoCompleteTextView textViewthree = (AutoCompleteTextView) findViewById(R.id.txtallsongsthree);
        textViewone.setThreshold(3);
        textViewone.setAdapter(adapter);
        textViewtwo.setThreshold(3);
        textViewtwo.setAdapter(adapter);
        textViewthree.setThreshold(3);
        textViewthree.setAdapter(adapter);
	}
	
	public void CopyDB(InputStream inputStream, OutputStream outputStream)
			throws IOException {
		// copy 1024 bytes at a time
		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) > 0) {
			outputStream.write(buffer, 0, length);
		}
		inputStream.close();
		outputStream.close();
	}
	
	public void onClickLocation(View view) {
		////Log.w(TAG, "In Deadshowfinder.onClickLocation()");
		Intent iLocation = new Intent("com.jimsuplee.deadshowfinder.Location");
		startActivityForResult(iLocation, 0);
	}
	
	public void onClickDate(View view) {
		////Log.w(TAG, "In Deadshowfinder.onClickDate()");
		Intent iDate = new Intent("com.jimsuplee.deadshowfinder.Date");
		startActivityForResult(iDate, 1);
	}

    public void onClickfindshow(View view) {
	    ////Log.w(TAG, "In Deadshowfinder.onClickLocation()");
		db.open();
		
        //Intent i = new Intent("");
        AutoCompleteTextView textViewone = (AutoCompleteTextView) findViewById(R.id.txtallsongsone);
        AutoCompleteTextView textViewtwo = (AutoCompleteTextView) findViewById(R.id.txtallsongstwo);
        AutoCompleteTextView textViewthree = (AutoCompleteTextView) findViewById(R.id.txtallsongsthree);
        String resultChoiceone = textViewone.getText().toString();
        String resultChoicetwo = textViewtwo.getText().toString();
        String resultChoicethree = textViewthree.getText().toString();
        //i.setData(Uri.parse(resultChoiceone+resultChoicetwo+resultChoicethree));
        //i.setData(Uri.parse(resultChoicetwo));
        //i.setData(Uri.parse(resultChoicethree));
        //Log.w(TAG, "In Deadshowfinder.onClickfindshow(), about to send db.getBySongs: "+resultChoiceone+"_"+resultChoicetwo+"_"+resultChoicethree);
        Cursor c = db.getBySongs("%"+resultChoiceone+"_"+resultChoicetwo+"_"+resultChoicethree+"%");
        //setResult(RESULT_OK, i);
        //finish();
		//Intent iLocation = new Intent("com.jimsuplee.deadshowfinder.Location");
		//startActivityForResult(iLocation, 0);
        String results = "";
		if (c.moveToFirst()) {
			////Log.w(TAG, "In Deadshowfinder.onClickfindshow(), cursor.moveToFirst() is true, about to loop through results");
			do {
				//results += "\nStartyear: " + c.getString(9) +"\nType: " + c.getString(11) + "\nSub_Type: " + c.getString(10) + "\nCountry: " + c.getString(2) +"\nLocation: " + c.getString(6) +"\nKilled: " + c.getString(5) +"\nAffected: " + c.getString(0) + "\nCost: " + c.getString(1) +"\nStart: " + c.getString(9) + c.getString(8) + "\nEnd: " + c.getString(3) + "\nName: " + c.getString(7) +"\nId: " + c.getString(4) + "___";
				//results += "\nLocation: " + c.getString(0) +"\nDate: " + c.getString(1) + "\nSet One: " + c.getString(2) + "\nSet Two: " + c.getString(3) +"\nSet Three: " + c.getString(4) + "___";
				String[] setonesongs;
				String[] settwosongs;
				String setonestring="";
				String settwostring="";
				if (c.getString(2).contains("_")) {
				    setonesongs = c.getString(2).split("_");
				    for(int i=0; i<setonesongs.length; i++){
				    	setonestring+="\n\t"+setonesongs[i];
				    
				    }
				}
				if (c.getString(3).contains("_")) {
				    settwosongs = c.getString(3).split("_");
				    for(int i=0; i<settwosongs.length; i++){
				    	settwostring+="\n\t"+settwosongs[i];			    
				    }
			    }
				//results += "\nLocation: " + c.getString(0) +"\nDate: " + c.getString(1) + "\nSet One: " + c.getString(2) + "\nSet Two: " + c.getString(3) +"\nSet Three: " + c.getString(4) + "___";
				results += "\nLocation: " + c.getString(0) +"\n\nDate: " + c.getString(1) + "\n\nSet One: " + setonestring + "\n\nSet Two: " + settwostring +"\n\nSet Three: " + c.getString(4) + "___";
			} while (c.moveToNext());
		} else {
			////Log.w(TAG, "In Deadshowfinder.onClickfindshow(), cursor.moveToFirst() is NOT TRUE!");
		}
		////Log.w(TAG, "In Deadshowfinder.onClickfindshow(), done looping, about to make Results intent, iResults");
		Intent iResults = new Intent("com.jimsuplee.deadshowfinder.Results");
		iResults.putExtra("results", results);
		////Log.w(TAG, "In Deadshowfinder.onClickfindshow(), about to startActivityForResult(iResults, 3)");
		startActivityForResult(iResults, 3);
		////Log.w(TAG, "In Deadshowfinder.onClickfindshow(), iResults started, about to db.close()");
		db.close();
	}
	
	public void onClickHelp(View view) {
		//Log.w(TAG, "In Deadshowfinder.onClickHelp()");
		String deadHelp = "FIND THE SHOW ON YOUR GRATEFUL DEAD TAPE\n\nThe most important capability of this application is its ability to identify which show performance your Dead tape was recorded at.  If you have an unlabeled tape, or a long mp3 file of a show, or otherwise need to discover which show the music was played at, this application can probably help to establish the truth.  It works like this:\n\n1. You input two songs that were played consecutively at the show.  \n2. The application displays the complete set lists for shows where the song sequence you submitted were played in the same order at the show.  \n3. You browse the list of candidate shows to discover which show your tape was made from.  When you see a set list with the songs on your tape, in order, you have found the correct show.  At that point, you can label your tape with the date, location and set list for the show.  \n\nOTHER USES:\n\nYou can search shows by date and by location.  \n\nLIMITATIONS:\n\nThis application works because Jerry Stratton kindly compiled and shared his database of Dead shows (still available at gdead.berkeley.edu).  Unfortunately, the database has some modest inconsistency that makes this application less useful.  For example, when selecting a song and typing Fire, and waiting a moment, it retrieves two listings for Fire on the mountain.  This is because the source database has a case-sensitive difference between two listings.  One record says Fire on the Mountain and the other says Fire On the Mountain.  This is real problem and will cause some show searches to fail.  You will need to search all of the possible combinations to overcome this shortcoming.  Finding and correcting these anomalies would be difficult and might introduce other problems.  For now, when the song listing choice list shows more than one listing for one song, you will need to check them both.  \n\nThe third set is usually only one song.  For this reason, \n\nYou can search for shows by only entering one song.  This is likely to return too many shows to be useful.  Sometimes even two consecutive songs will reveal a show list that is very long.  For example, the sequence of these two songs in this order:\n\nChina cat sunflower\nI know you rider\n\n...shows 376 shows!  That is a very long list.  Such sequences are uncommon.  Usually two consecutive songs will reveal a few number of shows. \n\nCREDIT BELONGS TO:\nJerry Stratton and others that compiled and shared the dead show data.  Thanks!";
		TextView tv = (TextView) findViewById(R.id.txt_deadshowfinder);
		tv.setVisibility(View.VISIBLE);
		tv.setText(deadHelp);
	}
	
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// ---check if the request code is 0 1 2 3 4 5---
		////Log.w(TAG, "In Deadshowfinder.onActivityResult(), checking reqCod");
		// onClickLocation startActivityForResult section
		if (requestCode == 0) {
			////Log.w(TAG, "In Deadshowfinder.onActivityResult(int-reqCode=0,int-resCode,Intent-data)");
			if (resultCode == RESULT_OK) {
				db.open();
				String location = data.getData().toString();
    			Cursor c = db.getByLocation(location);
				String results = "";
				if (c != null) {
					//Log.w(TAG, "In Deadshowfinder.onActivityResult(), cursor is not null");
				}
				
				if (c.moveToFirst()) {
					//Log.w(TAG, "In Deadshowfinder.onActivityResult(), cursor.moveToFirst() is true, about to loop through results");
					do {
						//results += "\nStartyear: " + c.getString(9) +"\nType: " + c.getString(11) + "\nSub_Type: " + c.getString(10) + "\nCountry: " + c.getString(2) +"\nLocation: " + c.getString(6) +"\nKilled: " + c.getString(5) +"\nAffected: " + c.getString(0) + "\nCost: " + c.getString(1) +"\nStart: " + c.getString(9) + c.getString(8) + "\nEnd: " + c.getString(3) + "\nName: " + c.getString(7) +"\nId: " + c.getString(4) + "___";
						//results += "\nLocation: " + c.getString(0) +"\nDate: " + c.getString(1) + "\nSet One: " + c.getString(2) + "\nSet Two: " + c.getString(3) +"\nSet Three: " + c.getString(4) + "___";
						String[] setonesongs;
						String[] settwosongs;
						String setonestring="";
						String settwostring="";
						if (c.getString(2).contains("_")) {
						    setonesongs = c.getString(2).split("_");
						    for(int i=0; i<setonesongs.length; i++){
						    	setonestring+="\n\t"+setonesongs[i];
						    
						    }
						}
						if (c.getString(3).contains("_")) {
						    settwosongs = c.getString(3).split("_");
						    for(int i=0; i<settwosongs.length; i++){
						    	settwostring+="\n\t"+settwosongs[i];			    
						    }
					    }
						//results += "\nLocation: " + c.getString(0) +"\nDate: " + c.getString(1) + "\nSet One: " + c.getString(2) + "\nSet Two: " + c.getString(3) +"\nSet Three: " + c.getString(4) + "___";
						results += "\nLocation: " + c.getString(0) +"\n\nDate: " + c.getString(1) + "\n\nSet One: " + setonestring + "\n\nSet Two: " + settwostring +"\n\nSet Three: " + c.getString(4) + "___";
					} while (c.moveToNext());
				} else {
					//Log.w(TAG, "In Deadshowfinder.onActivityResult(), cursor.moveToFirst() is NOT TRUE!");
				}
			    //Log.w(TAG, "In Deadshowfinder.onActivityResult(), done looping, about to make Results intent, iResults");
				Intent iResults = new Intent("com.jimsuplee.deadshowfinder.Results");
				iResults.putExtra("results", results);
				////Log.w(TAG, "In Deadshowfinder.onActivityResult(), about to startActivityForResult(iResults, 3)");
				startActivityForResult(iResults, 3);
				////Log.w(TAG, "In Deadshowfinder.onActivityResult(), iResults started, about to db.close()");
				db.close();
			}
		}
		if (requestCode == 1) {
			////Log.w(TAG, "In Deadshowfinder.onActivityResult(int-reqCode=1,int-resCode,Intent-data)");
			if (resultCode == RESULT_OK) {
				db.open();
				String date = data.getData().toString();
    			Cursor c = db.getByDate(date);
				String results = "";
				if (c != null) {
					//Log.w(TAG, "In Deadshowfinder.onActivityResult(), cursor is not null");
				}
				
				if (c.moveToFirst()) {
					//Log.w(TAG, "In Deadshowfinder.onActivityResult(), cursor.moveToFirst() is true, about to loop through results");
					do {
						//results += "\nStartyear: " + c.getString(9) +"\nType: " + c.getString(11) + "\nSub_Type: " + c.getString(10) + "\nCountry: " + c.getString(2) +"\nLocation: " + c.getString(6) +"\nKilled: " + c.getString(5) +"\nAffected: " + c.getString(0) + "\nCost: " + c.getString(1) +"\nStart: " + c.getString(9) + c.getString(8) + "\nEnd: " + c.getString(3) + "\nName: " + c.getString(7) +"\nId: " + c.getString(4) + "___";
						//results += "\nLocation: " + c.getString(0) +"\nDate: " + c.getString(1) + "\nSet One: " + c.getString(2) + "\nSet Two: " + c.getString(3) +"\nSet Three: " + c.getString(4) + "___";
						String[] setonesongs;
						String[] settwosongs;
						String setonestring="";
						String settwostring="";
						if (c.getString(2).contains("_")) {
						    setonesongs = c.getString(2).split("_");
						    for(int i=0; i<setonesongs.length; i++){
						    	setonestring+="\n\t"+setonesongs[i];
						    
						    }
						}
						if (c.getString(3).contains("_")) {
						    settwosongs = c.getString(3).split("_");
						    for(int i=0; i<settwosongs.length; i++){
						    	settwostring+="\n\t"+settwosongs[i];			    
						    }
					    }
						//results += "\nLocation: " + c.getString(0) +"\nDate: " + c.getString(1) + "\nSet One: " + c.getString(2) + "\nSet Two: " + c.getString(3) +"\nSet Three: " + c.getString(4) + "___";
						results += "\nLocation: " + c.getString(0) +"\n\nDate: " + c.getString(1) + "\n\nSet One: " + setonestring + "\n\nSet Two: " + settwostring +"\n\nSet Three: " + c.getString(4) + "___";
					} while (c.moveToNext());
				} else {
					//Log.w(TAG, "In Deadshowfinder.onActivityResult(), cursor.moveToFirst() is NOT TRUE!");
				}
			    //Log.w(TAG, "In Deadshowfinder.onActivityResult(), done looping, about to make Results intent, iResults");
				Intent iResults = new Intent("com.jimsuplee.deadshowfinder.Results");
				iResults.putExtra("results", results);
				////Log.w(TAG, "In Deadshowfinder.onActivityResult(), about to startActivityForResult(iResults, 3)");
				startActivityForResult(iResults, 3);
				////Log.w(TAG, "In Deadshowfinder.onActivityResult(), iResults started, about to db.close()");
				db.close();
			}
		}
		if (requestCode == 3) {
			////Log.w(TAG, "In Deadshowfinder.onActivityResult(int-reqCode=3,int-resCode,Intent-data)");
			if (resultCode == RESULT_OK) {
				//Log.w(TAG, "In Deadshowfinder.onActivityResult(3) RESULT_OK, about to set TextView");
				String deadshowfinderData = data.getData().toString();
				TextView tv = (TextView) findViewById(R.id.txt_deadshowfinder);
				//tv.setVisibility(View.VISIBLE);
				tv.setText(deadshowfinderData);
			}
		}		
	}
		
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.deadshowfinder, menu);
		return true;
	}

}
