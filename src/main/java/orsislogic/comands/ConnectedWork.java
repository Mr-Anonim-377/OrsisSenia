package orsislogic.comands;

import java.sql.ResultSet;
import java.sql.SQLException;

import static telegramm.Bot.*;

public class ConnectedWork {
    public void connectedWork (){

        String [] arrayWordCommand = messageOutCommad.split(":");
        String works = arrayWordCommand[arrayWordCommand.length -1];
        String [] toFromWork = works.split(" к ");

        String fromWorkMessage = toFromWork[0].trim();
        String fromWork = toFromWork[0].toUpperCase().trim();
        String toWorkMessage = toFromWork[toFromWork.length - 1];
        String toWork = toFromWork[toFromWork.length - 1].toUpperCase().trim();

        try {
            ResultSet sql = statement.executeQuery("insert into works (title,status,title_no_formaliz)\n" +
                    "values('" + fromWork  + "',1,'"+fromWorkMessage+" "+"')");
        } catch (SQLException e) {

        }


        try {
            ResultSet sql = statement.executeQuery("insert into works_line  (id_from, id_to)\n" +
                    "values((select id\n" +
                    "from works\n" +
                    "where title = '"+fromWork+"'),(select id\n" +
                    "from works\n" +
                    "where title = '"+toWork+"') )");
        } catch (SQLException e) {

        }
        messageInRespons = "Я привязал : " + fromWorkMessage +" к "+ toWorkMessage;

    }
}
