package orsislogic.comands;







import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import static telegramm.Bot.messageInRespons;
import static telegramm.Bot.statement;

public class Hello {
     private ArrayList<String> helloResponse = new ArrayList<String>();


    public void hello() throws SQLException {


        ResultSet resultSet = statement.executeQuery("Select * From hello");

        while (resultSet.next()) {
            helloResponse.add(resultSet.getString(2));
        }

        int arrayList_BD = helloResponse.size();
        Random random = new Random();
        int index_random = random.nextInt(arrayList_BD);

        resultSet.close();


        messageInRespons = helloResponse.get(index_random);

        }
}
