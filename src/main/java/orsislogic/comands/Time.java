package orsislogic.comands;



import java.text.SimpleDateFormat;
import java.util.Date;

import static telegramm.Bot.messageInRespons;

public class Time {

    public void time (){
        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("HH:mm"+" "+"E");
        //System.out.println("Ну шас " + formatForDateNow.format(date));
        messageInRespons = "Ну,щас " + formatForDateNow.format(date);
    }

}
