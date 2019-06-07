package orsislogic.comands;

import java.sql.ResultSet;
import java.sql.SQLException;

import static telegramm.Bot.*;

public class AddWorks {

    public void addWorks()  {


        String [] arrayWordCommand = messageOutCommad.split(" ");
        String work = "";
        boolean dublePoint = false;

        for (String i : arrayWordCommand){
            if (dublePoint){
                work = work+" "+i;
            }
            if (i.contains(":")){
                dublePoint = true;
            }


        }

        String workMessage = work.trim();
                work = work.toUpperCase();
        try {
            ResultSet resultSet = statement.executeQuery("insert into works (title,status,title_no_formaliz)\n" +
                    "values('" + work.trim()  + "',1,'"+workMessage+" "+"')");
        } catch (SQLException e) {

        }

        messageInRespons = "Я запомнил: " + workMessage;
        
    }
}
