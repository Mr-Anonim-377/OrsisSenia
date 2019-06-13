package orsislogic.controller;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import orsislogic.comands.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static telegramm.Bot.*;


public class Comand  {
     private Time timeComand = new Time();
     private Hello helloComand = new Hello();
     private AddWorks addWorksComand = new AddWorks();
     private ShowWork showWorkComand = new ShowWork();
     private ConnectedWork connectedWork = new ConnectedWork();
     private RememberChain rememberChain = new RememberChain();
    public void comandSearch() throws SQLException, TelegramApiException {
        String command = messageOutCommad.toLowerCase();
        connection = DriverManager.getConnection(url_BD, user_BD, pass_BD);
        statement = connection.createStatement();

        if (command.contains("все")||command.contains("что связано с ")||command.contains("всё")||command.contains("связи")||command.contains("вспомни все что связанно с")) {

            rememberChain.rememberChain();
        }
        if(command.contains("привет")|| command.contains("hi")|| command.contains("ку")|| command.contains("приветик")){
            helloComand.hello();
        }
        if(command.contains("время") || command.contains("времени")|| command.contains("time")|| command.contains("тайма")){
            timeComand.time();
        }
        if (command.contains("добавь")||command.contains("запиши")||command.contains("запомни")||command.contains("следи")) {

            addWorksComand.addWorks();
        }
        if (command.contains("покажи")||command.contains("подскажи")||command.contains("вспомни")||command.contains("помнишь")) {

            showWorkComand.showWork();
        }
        if (command.contains("привяжи")||command.contains("приспособь")||command.contains("соедени")||command.contains("запиши к")) {

            connectedWork.connectedWork();
        }

        statement.close();
        connection.close();
    }

    }


