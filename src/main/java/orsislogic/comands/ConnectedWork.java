package orsislogic.comands;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegramm.Bot;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static telegramm.Bot.*;

public class ConnectedWork {
    ArrayList<String> connectedResponse = new ArrayList<String>();

    public void connectedWork () throws SQLException, TelegramApiException {

        if(messageOutCommad.contains(":")){

        String [] arrayWordCommand = messageOutCommad.split(":");

        String works = arrayWordCommand[arrayWordCommand.length -1];

        String [] toFromWork = works.split(" к ");

        String fromWorkMessage = toFromWork[0].trim();
        String fromWork = fromWorkMessage.toUpperCase();
        String toWorkMessage = toFromWork[toFromWork.length - 1];
        String toWork = toWorkMessage.toUpperCase();

            ResultSet resultSet = statement.executeQuery("select *\n" +
                    "from works \n" +
                    "where title like"+ "'%"+fromWork + "%'");


            while (resultSet.next()) {
                connectedResponse.add(resultSet.getString(2));
            }
            if(connectedResponse.size()>=2){
                String worksArray = "\n";
                for(String i:connectedResponse){
                    worksArray = worksArray + i + "\n";
                }
                Bot message = new Bot();
                message.sendMessage("Я помню несколько дел, похожих на те,которые вы хотите привязать, выберете конкретное дело пожалуйста : "+worksArray);

            }else {

                switch (connectedResponse.size()) {
                    case 0:
                        try {
                            ResultSet sql = statement.executeQuery("insert into works (title,status,title_no_formaliz)\n" +
                                    "values('" + fromWork + "',1,'" + fromWorkMessage + " " + "')");
                        } catch (SQLException e) {

                        }
                        break;
                    case 1:
                        fromWork = connectedResponse.get(0);
                }


                try {
                    ResultSet sql = statement.executeQuery("insert into works_line  (id_from, id_to)\n" +
                            "values((select id\n" +
                            "from works\n" +
                            "where title = '" + fromWork + "'),(select id\n" +
                            "from works\n" +
                            "where title = '" + toWork + "') )");
                } catch (SQLException e) {

                }
                messageInRespons = "Я привязал : " + fromWorkMessage + " к " + toWorkMessage;
            }
        }
        else{
            messageInRespons = "К сожалению я еще не на столько развит и умен,введите команду с : перед задачей, связи которой хотите посмотреть";
        }







    }


}
