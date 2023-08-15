package uz.pdp.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.MyBot;
import uz.pdp.model.*;
import uz.pdp.model.enums.State;
import uz.pdp.utils.DataBase;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    public void studentMenu(Update update, Student currentUser){

        if (update.hasMessage()){
            process(currentUser, update);
        } else if (update.hasCallbackQuery()){
            solveTestProcess(currentUser, update.getCallbackQuery());
        }

    }

    private void process(Student currentUser,Update update) {
        MyBot myBot = new MyBot();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(currentUser.getChatId());
        String msg = "";

        if (update.hasMessage() && update.getMessage().hasText()){
            msg = update.getMessage().getText();
        }

        if (msg.equals("/start")){
            sendMessage.setText("Assalomu Alaykum, Botga hush kelibsiz");
            currentUser.setState(State.MAIN_MENU);
            sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));

            try {
                myBot.execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            return;
        }

        switch (currentUser.getState()) {
            case MAIN_MENU:
                mainMenuProcess(currentUser, update.getMessage());
                break;
            case SOLVE_TESTS: {

                if (msg.equals("Back")) {
                    currentUser.setState(State.SOLVE_TESTS);
                    sendMessage.setText("Choose one Subject to solve: ");
                    sendMessage.setReplyMarkup(getInlineMarkup(currentUser));

                    try {
                        myBot.execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                } else if (msg.equals("Start Test")) {
                    currentUser.setState(State.TEST_STARTED);
                    Test test = currentUser.getSelectedSubject().getTestList().get(currentUser.getCurrentTestNumber());

                    sendMessage.setText((currentUser.getCurrentTestNumber()+1) + ". " + test.getQuestion());
                    sendMessage.setReplyMarkup(getInlineMarkup(currentUser));

                    try {
                        currentUser.setCurrentMessageId(myBot.execute(sendMessage).getMessageId());
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }

            }break;
        }
    }

    private void solveTestProcess(Student currentUser, CallbackQuery callbackQuery) {
        MyBot myBot = new MyBot();
        switch (currentUser.getState()){
            case SOLVE_TESTS: {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(currentUser.getChatId());
                String data = callbackQuery.getData();

                Subject subject = DataBase.subjectList.get(Integer.parseInt(data));
                currentUser.setSelectedSubject(subject);

                sendMessage.setText("Selected Subject: " + subject.getName() + "\n" +
                        "Number of tests: " + subject.getTestList().size() + "\n" +
                        "Maximum score in percentage: 100%" + "\n" +
                        "Given minutes to solve: " + (subject.getTestList().size()) + " minutes\n");
                sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));

                try {
                    myBot.execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }break;

            case TEST_STARTED: {
                EditMessageText editMessageText = new EditMessageText();
                editMessageText.setChatId(currentUser.getChatId());
                editMessageText.setMessageId(currentUser.getCurrentMessageId());

                String data = callbackQuery.getData();
                Test previousTest = currentUser.getSelectedSubject().getTestList().get(currentUser.getCurrentTestNumber());

                if (previousTest.getTrueAnswer().equals(data)){
                    currentUser.setCorrectAnswers(currentUser.getCorrectAnswers() + 1);
                }

                if ((currentUser.getCurrentTestNumber() + 1) == currentUser.getSelectedSubject().getTestList().size()){
                    userFinishedTest(currentUser);
                    return;
                }

                currentUser.setCurrentTestNumber(currentUser.getCurrentTestNumber() + 1);
                Test currentTest = currentUser.getSelectedSubject().getTestList().get(currentUser.getCurrentTestNumber());

                editMessageText.setText((currentUser.getCurrentTestNumber()+1) + ". " + currentTest.getQuestion());
                editMessageText.setReplyMarkup(getInlineMarkup(currentUser));

                try {
                    myBot.execute(editMessageText);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }break;

        }





    }

    private void userFinishedTest(Student currentUser) {
        MyBot myBot = new MyBot();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(currentUser.getChatId());

        float result = (float) currentUser.getCorrectAnswers() / currentUser.getSelectedSubject().getTestList().size() * 100;

        if (result > 70){
            sendMessage.setText("Congratulations!" + "\n" +
                    "Your selected subject: " + currentUser.getSelectedSubject().getName() + "\n"  +
                    "Your result: " + result + "%\n"  +
                    "True answers: " + currentUser.getCorrectAnswers() + " out of " + currentUser.getSelectedSubject().getTestList().size());

            try {
                myBot.execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        } else {

            sendMessage.setText("You should try more!" + "\n" +
                    "Your selected subject: " + currentUser.getSelectedSubject().getName() + "\n"  +
                    "Your result: " + result + "%\n"  +
                    "True answers: " + currentUser.getCorrectAnswers() + " out of " + currentUser.getSelectedSubject().getTestList().size());

            try {
                myBot.execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }

        currentUser.setCurrentTestNumber(0);
        currentUser.setCorrectAnswers(0);
        currentUser.setSelectedSubject(null);
        currentUser.setState(State.MAIN_MENU);
        sendMessage.setText("Choose one option: ");
        sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));

        try {
            myBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    private void mainMenuProcess(Student currentUser, Message message) {
        MyBot myBot = new MyBot();
        String msg = message.getText();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(currentUser.getChatId());

        if (msg.equals("Solve Tests")){
            currentUser.setState(State.SOLVE_TESTS);
            sendMessage.setText("Choose one Subject to solve");
            sendMessage.setReplyMarkup(getInlineMarkup(currentUser));

            try {
                myBot.execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        } else {
            sendMessage.setText("Wrong command!");


            try {
                myBot.execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

    public InlineKeyboardMarkup getInlineMarkup(Student currentUser){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

//        List<InlineKeyboardButton> row1 = new ArrayList<>();
//        List<InlineKeyboardButton> row2 = new ArrayList<>();
//        List<InlineKeyboardButton> row3 = new ArrayList<>();
//
//        InlineKeyboardButton button1 = new InlineKeyboardButton();
//        InlineKeyboardButton button2 = new InlineKeyboardButton();
//        InlineKeyboardButton button3 = new InlineKeyboardButton();

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        switch (currentUser.getState()){
            case MAIN_MENU:break;
            case SOLVE_TESTS: {
                for (int i = 0; i < DataBase.subjectList.size(); i++) {
                    Subject subject = DataBase.subjectList.get(i);

                    InlineKeyboardButton button = new InlineKeyboardButton();

                    button.setText(subject.getName());
                    button.setCallbackData(String.valueOf(i));

                    List<InlineKeyboardButton> row = new ArrayList<>();
                    row.add(button);
                    rowList.add(row);
                }
            } break;

            case TEST_STARTED:{
                Test test = currentUser.getSelectedSubject().getTestList().get(currentUser.getCurrentTestNumber());

                for (int i = 0; i < test.getAnswerList().size(); i++) {
                    Answer answer = test.getAnswerList().get(i);

                    InlineKeyboardButton button = new InlineKeyboardButton();

                    button.setText(answer.getBody());
                    button.setCallbackData(String.valueOf(i));

                    List<InlineKeyboardButton> row = new ArrayList<>();
                    row.add(button);
                    rowList.add(row);
                }

            } break;
        }




        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

    private ReplyKeyboard getReplyKeyBoard(Student currentUser) {

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> rowList = new ArrayList<>();
        keyboardMarkup.setKeyboard(rowList);
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow rowN = new KeyboardRow();

        State state = currentUser.getState();

        switch (state) {
            case MAIN_MENU:
                row1.add("Solve Tests");
                rowList.add(row1);
                break;
            case SOLVE_TESTS:
                row1.add("Back");
                row1.add("Start Test");
                rowList.add(row1);
                break;
        }

        return keyboardMarkup;
    }

}
