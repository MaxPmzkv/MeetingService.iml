# Redis queue 
##### Nosql training    
This program emulates a meeting service web site with 20 registered users; They are shown in a raw on the "main page" (in the console);  
Queue order depends on registration time by default;
User is sent to the end of the queue after being displayed;
User can "donate" to become first in the queue;

 - program starts an endless loop, where
 1) The number of the user, who has to be displayed on the main page is shawn
2) Random user "donates" in one out of ten cases. His number displays in the console
3) The program sleeps for a sec and the loop starts over

**The queue is held in Redis database**