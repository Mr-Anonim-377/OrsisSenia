package telegramm;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import orsislogic.controller.Comand;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class Bot extends TelegramLongPollingBot {

    public static String url_BD = "jdbc:postgresql://localhost:5432/orsis";
    public static String user_BD = "postgres";
    public static String pass_BD = "cskl19nigypqjexm";
    public static int privat = 322259552;
    public static long userOut = 322259552;//-392736899; //-396445917;   // по умолчанию мой же id, первый тест чат, второй рабочий чат(тестинг скидекс)
    public static String messageInRespons ;
    public static String messageOutCommad  ;
    public static Connection connection;
    public static Statement statement;



    public static void main(String[] args) {
        System.getProperties().put( "proxySet", "true" );
        System.getProperties().put( "socksProxyHost", "127.0.0.1" );
        System.getProperties().put( "socksProxyPort", "9150" );
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {

            if (update.hasMessage() && update.getMessage().hasText()) {
                System.out.println(update.getMessage().getChat());
                if(update.getMessage().getChatId() == privat){


                    //Извлекаем объект входящего сообщения
                    Message inMessage = update.getMessage();
                    messageOutCommad = inMessage.getText();
                    System.out.println(inMessage.getChatId() +";"+ inMessage.getText());

                    //Создаем исходящее сообщение
                    SendMessage outMessage = new SendMessage();
                    Comand comand = new Comand();
                    comand.comandSearch();




                    outMessage.setChatId(userOut);
                    //Указываем текст сообщения
                    outMessage.setText(messageInRespons);
                    //Отправляем сообщение
                    execute(outMessage);
                    messageInRespons = "";
                }
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }




    @Override
    public String getBotUsername() {
        return "Deploy_AND_testing_Bot";
    }


    @Override
    public String getBotToken() {
        return "843871869:AAEyMA6Nl7waKkTZmwQS9jYuiqN2zI6c2qA";
    }
}