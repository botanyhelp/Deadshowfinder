### Deadshowfinder Android App README

Code for Deadshowfinder android application.

Finds which Grateful Dead show your dead tape was created from. You enter up to three songs, in sequence, that you hear on your tape. It reports which shows have that sequence played at the show. For all shows from 1972 through 1993.

There is an sqlite database in there that has a schema like this:
```{sql}
CREATE TABLE deadsets (location text default null, date text default null, setone text default null, settwo text default null, setthree text default null);
CREATE INDEX idxdate ON deadsets(date);
CREATE INDEX idxlocation ON deadsets(location);
CREATE INDEX idxsetone ON deadsets(setone);
CREATE INDEX idxsetthree ON deadsets(setthree);
CREATE INDEX idxsettwo ON deadsets(settwo);
```
Populating this database with SQL insert statements meant collecting it first.  Get some aspirin first if you try to collect such data.  Then read some books about how to search google, how to crawl the web.  Then get the data.  This python script parses that collection and structures the data so that it can exist in the form of SQL INSERT statements that will be run against the schema shown above:

```{python}
from __future__ import print_function
#import yaml
import re
#from BeautifulSoup import BeautifulSoup
#import urllib2

i=0
docs=''
#    docs=""


f = open("deadsets")
songs=False
lastSong=0;
for line in f.readlines():
    matchOne = re.match('^Location: *(.*)', line)
    matchTwo = re.match('^Date: *(.*)', line)
    matchThree = re.match('^Songs:', line)
    matchFour = re.match('^#', line)
    matchFive = re.match('^$', line)
    matchSix = re.match('^Comments: *(.*)', line)
    if matchOne:
        print("INSERT INTO deadsets (location, date, songssetone, songssettwo, songssetthree, comments) VALUES ('"+matchOne.group(), end='')
    if matchTwo:
        print("', '"+matchTwo.group(), end='')
    if matchThree:
        songs=True;
        print("', '", end='')
    if matchSix:
        print("', '"+matchSix.group(), end='')
    if matchFour:
        songs=False
        print("');")
    if songs and matchFive:
        lastSong+=1
        if(lastSong<3):
            print("', '", end='')
        else:
            lastSong=0
            #print("',", end='')
    else:
        print(line.rstrip()+" ", end='')

DOCS2="""
    line=line.rstrip()
    location=""
    m = re.search(r'^Location: (.*)',line)
    m2 = re.search(r'^Date: (.*)',line)
    for location in m.groups():
        location = str(location)
    for date in m2.groups():
        date = str(date)

```

The artifact of that python and the datafile it uses are SQL INSERT statements like these (actual) statements:
```{sql}
INSERT INTO deadsets (location, date, setone, settwo, setthree) VALUES ('Uptown Theater, Chicago, IL', '2/28/81', 'Jack Straw_They Love Each Other_Mama Tried_Mexicali Blues_Candyman_Little Red Rooster_Big Railroad Blues_Althea_Let it Grow_Deal', 'Cold Rain and Snow_Greatest Story Ever Told_Ship of Fools_Lost Sailor_Saint of Circumstance_Terrapin Station_drums_The Other One_Stella Blue_Around and Around_Good Lovin', 'One More Saturday Night__');
INSERT INTO deadsets (location, date, setone, settwo, setthree) VALUES ('Cleveland Music Hall, Cleveland, OH', '3/2/81', 'Shakedown Street_New Minglewood Blues_Dire Wolf_Cassidy_Althea_El Paso_Jack-a-Roe_Lazy Lightnin_Supplication_Dont Ease Me In_Promised Land', 'Ramble on Rose_Playin in the Band_China Doll_drums_Playin in the Band_The Wheel_Around and Around_Johnny B. Goode', 'U.S. Blues__');
INSERT INTO deadsets (location, date, setone, settwo, setthree) VALUES ('Cleveland Music Hall, Cleveland, OH', '3/3/81', 'Feel Like a Stranger_Peggy-O_Me and My Uncle_Big River_Bird Song_Looks Like Rain_Big Railroad Blues_Little Red Rooster_Deal', 'China Cat Sunflower_I Know You Rider_Samson and Delilah_Hes Gone_drums_Lost Sailor_Saint of Circumstance_Black Peter_Good Lovin', 'Casey Jones__');
```
The rest of the application is straightforward.  It allows the user to enter some songs and find the shows that had that sequence of songs played at the show. 
