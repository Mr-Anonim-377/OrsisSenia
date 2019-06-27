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
            ResultSet resultComparison = statement.executeQuery("select id\n" +
                    "from works \n" +
                    "where title ="+work);
            if(resultComparison != null){
                try {
                    ResultSet resultSet = statement.executeQuery("insert into works (title,status,title_no_formaliz)\n" +
                            "values('" + work.trim()  + "',1,'"+workMessage+" "+"')");
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                messageInRespons = "Я запомнил: " + workMessage;
            }
            else{
                messageInRespons = "Такое дело я уже помню)";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        
    }
}
