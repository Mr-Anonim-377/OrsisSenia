package orsislogic.comands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static telegramm.Bot.*;

public class ShowWork {
    ArrayList<String> helloResponse = new ArrayList<String>();

    public void showWork() throws SQLException {
         String bdOut = "";

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
        work = work.toUpperCase().trim();
            ResultSet resultSet = statement.executeQuery("select *\n" +
                    "from works \n" +
                    "where title like"+ "'%"+work + "%'");

        while (resultSet.next()) {
            helloResponse.add(resultSet.getString(4));
        }

        for(String i:helloResponse){
            bdOut=bdOut +i + "\n";
        }
        messageInRespons ="Все что смог вспомнить: "+ bdOut ;




    }


}
