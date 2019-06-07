package orsislogic.comands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static telegramm.Bot.*;

public class RememberChain {
    private ArrayList<String> worksCollection = new ArrayList<String>();
    ResultSet sqlRes;
    public void rememberChain() throws SQLException {

        String [] arrayWordCommand = messageOutCommad.split(":");
        String workMessage = arrayWordCommand[arrayWordCommand.length -1].trim();
        String work = arrayWordCommand[arrayWordCommand.length -1].toUpperCase().trim();

        try {
            sqlRes = statement.executeQuery("select w.id,wl.id_to,title, title_no_formaliz, status\n" +
                    "from works w \n" +
                    "join (select *\n" +
                    "from works_line\n" +
                    "where id_to = (select id\n" +
                    "from works\n" +
                    "where title = '"+work+"'\n" +
                    ")) wl on wl.id_from = w.id");

            while (sqlRes.next()) {
                worksCollection.add(sqlRes.getString(4));
            }

        } catch (SQLException e) {

        }



        if(worksCollection.size()>0){
            messageInRespons = "Вот все, что связанно с задачей "+ workMessage+": \n";
            for(String i : worksCollection){
                messageInRespons = messageInRespons + i + "\n";
            }
        }else{
            messageInRespons = "Ничего не связано";
        }

    }
}
