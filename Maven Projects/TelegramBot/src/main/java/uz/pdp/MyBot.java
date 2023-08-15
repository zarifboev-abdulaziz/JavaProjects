package uz.pdp;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.model.CustomMessage;
import uz.pdp.model.User;
import uz.pdp.model.enums.State;
import uz.pdp.model.jsonPlaceHolder.Journalist;
import uz.pdp.model.jsonPlaceHolder.Post;
import uz.pdp.utils.DataBase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static uz.pdp.utils.Constants.botToken;
import static uz.pdp.utils.Constants.botUsername;


public class MyBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        User currentUser = DataBase.getUserFromList(update);
        if (update.hasMessage()){
            process(currentUser, update);
        } else if (update.hasCallbackQuery()){
            processShowNews(currentUser, update.getCallbackQuery());
        }
    }

    private void process(User currentUser,Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(currentUser.getChatId());
        String msg = "";

        if (update.hasMessage() && update.getMessage().hasText()){
             msg = update.getMessage().getText();
        }

        if (msg.equals("/start")){

            SendDocument sendDocument = new SendDocument();
            sendDocument.setChatId(currentUser.getChatId());
            sendDocument.setDocument(new InputFile(new File("src/main/resources/001 - O'TKAN KUNLAR.mp3")));
            sendDocument.setCaption("kitobni eshit");

            try {
                execute(sendDocument);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            sendMessage.setText("Assalomu Alaykum, Botga hush kelibsiz");
            currentUser.setState(State.MAIN_MENU);
            currentUser.setNewsPage(0);
            sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        switch (currentUser.getState()) {
            case MAIN_MENU:
                mainMenuProcess(currentUser, update.getMessage());
                break;
            case CHAT_WITH_ADMIN:
                processChatWithAdmin(currentUser, update.getMessage());
                break;
            case SHOW_NEWS:
                if (update.hasCallbackQuery()){
                    processShowNews(currentUser, update.getCallbackQuery());
                }
                break;
        }
    }

    private void processChatWithAdmin(User currentUser, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(currentUser.getChatId());

        String text = message.getText();

        if (text.equals("Bosh Sahifa") || text.equals("Orqaga")){
            sendMessage.setText("Tanlang");
            currentUser.setState(State.MAIN_MENU);
            sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }else {

        currentUser.getMyMessageList().add(new CustomMessage(text));

        }

    }

    private void processShowNews(User currentUser, CallbackQuery callbackQuery) {
        String data = callbackQuery.getData();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(currentUser.getChatId());

        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(currentUser.getChatId());
        editMessageText.setMessageId(callbackQuery.getMessage().getMessageId());

        if (data.equals("Main Menu")){
            sendMessage.setText("Tanlang: ");
            currentUser.setState(State.MAIN_MENU);
            sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));
            currentUser.setCurrentMessageId(null);

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }
        else if (data.equals("Previous")){
            currentUser.setNewsPage(currentUser.getNewsPage() - 10);

            String newsText = "";
            for (int i = currentUser.getNewsPage(); i < currentUser.getNewsPage() + 10; i++) {
                Post post = DataBase.postList.get(i);
                newsText += "" + (i+1) + ". " + post.getTitle() + "\n";
            }

            editMessageText.setText(newsText);
            currentUser.setState(State.SHOW_NEWS);
            editMessageText.setReplyMarkup(getInlineMarkup(currentUser));

            try {
                execute(editMessageText);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }else if (data.equals("Next")){
            currentUser.setNewsPage(currentUser.getNewsPage() + 10);

            String newsText = "";
            for (int i = currentUser.getNewsPage(); i < currentUser.getNewsPage() + 10; i++) {
                Post post = DataBase.postList.get(i);
                newsText += "" + (i+1) + ". " + post.getTitle() + "\n";
            }

            editMessageText.setText(newsText);
            currentUser.setState(State.SHOW_NEWS);
            editMessageText.setReplyMarkup(getInlineMarkup(currentUser));

            try {
                execute(editMessageText);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }else {

            Post selectedPost = null;
            Journalist selectedJournalist = null;

            for (Post post : DataBase.postList) {
                if (post.getId() == Integer.parseInt(data)){
                    selectedPost = post;
                    for (Journalist journalist1 : DataBase.journalistList) {
                        if (journalist1.getId() == post.getUserId()){
                            selectedJournalist = journalist1;
                            break;
                        }
                    }
                    break;
                }
            }


            if (currentUser.getCurrentMessageId() == null){
                sendMessage.setText("Title: " + selectedPost.getTitle() + "\n" +
                        "Body: " + selectedPost.getBody() + "\n" +
                        "Journalist Name: "  + selectedJournalist.getName() + "\n" +
                        "Journalist Email: " + selectedJournalist.getEmail() + "\n");

                try {
                    //execute(sendMessage);
                    currentUser.setCurrentMessageId(execute(sendMessage).getMessageId());
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }else {
                EditMessageText editMessageText1 = new EditMessageText();
                editMessageText1.setChatId(currentUser.getChatId());
                editMessageText1.setMessageId(currentUser.getCurrentMessageId());

                editMessageText1.setText("Title: " + selectedPost.getTitle() + "\n" +
                        "Body: " + selectedPost.getBody() + "\n" +
                        "Journalist Name: "  + selectedJournalist.getName() + "\n" +
                        "Journalist Email: " + selectedJournalist.getEmail() + "\n");

                try {
                    execute(editMessageText1);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }

        }



    }


    private void mainMenuProcess(User currentUser, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(currentUser.getChatId());
        String msg = message.getText();

        if (msg.equals("Adminga Yozish")){
            sendMessage.setText("Xabaringizni yozing: ");
            currentUser.setState(State.CHAT_WITH_ADMIN);
            sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        } else if (msg.equals("Yangiliklarni ko'rish")){
            String newsText = "";
            for (int i = currentUser.getNewsPage(); i < currentUser.getNewsPage() + 10; i++) {
                Post post = DataBase.postList.get(i);
                newsText += "" + (i+1) + ". " + post.getTitle() + "\n";
            }

            sendMessage.setText(newsText);
            currentUser.setNewsPage(0);
            currentUser.setState(State.SHOW_NEWS);
            sendMessage.setReplyMarkup(getInlineMarkup(currentUser));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public InlineKeyboardMarkup getInlineMarkup(User currentUser){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        List<InlineKeyboardButton> row3 = new ArrayList<>();

        for (int i = currentUser.getNewsPage(); i < currentUser.getNewsPage() + 5; i++) {
            Post post = DataBase.postList.get(i);
            InlineKeyboardButton button = new InlineKeyboardButton();

            button.setText(String.valueOf(post.getId()));
            button.setCallbackData(String.valueOf(post.getId()));
            row1.add(button);
        }

        for (int i = currentUser.getNewsPage() + 5; i < currentUser.getNewsPage() + 10; i++) {
            Post post = DataBase.postList.get(i);
            InlineKeyboardButton button = new InlineKeyboardButton();

            button.setText(String.valueOf(post.getId()));
            button.setCallbackData(String.valueOf(post.getId()));
            row2.add(button);
        }

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        InlineKeyboardButton button3 = new InlineKeyboardButton();

        button1.setText("Previous");
        button1.setCallbackData("Previous");

        button2.setText("Main Menu");
        button2.setCallbackData("Main Menu");

        button3.setText("Next");
        button3.setCallbackData("Next");


        if (currentUser.getNewsPage() == 0){
            row3.add(button2);
            row3.add(button3);
        }else if (currentUser.getNewsPage() == (DataBase.postList.size() - 10)){
            row3.add(button1);
            row3.add(button2);
        } else {
            row3.add(button1);
            row3.add(button2);
            row3.add(button3);
        }

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(row1);
        rowList.add(row2);
        rowList.add(row3);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }

    private ReplyKeyboard getReplyKeyBoard(User currentUser) {

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
                row1.add("Adminga Yozish");
                row1.add("Yangiliklarni ko'rish");
                rowList.add(row1);
                break;

            case CHAT_WITH_ADMIN:
                row1.add("Orqaga");
                row1.add("Bosh Sahifa");
                rowList.add(row1);
                break;
        }

        return keyboardMarkup;
    }

    public void messageToUserService(){
        System.out.println("1.Send message to users");
        int option = new Scanner(System.in).nextInt();

        switch (option) {
            case 1:
                for (User user : DataBase.userList) {
                    System.out.println("Name: " + user.getFirstName() + ", chatId: " + user.getChatId());
                }

                System.out.println("Enter chatId to select ");
                String inputId = new Scanner(System.in).nextLine();

                User selectedUser = null;
                for (User user : DataBase.userList) {
                    if (user.getChatId().equals(inputId)){
                        selectedUser = user;
                    }
                }

                selectedUser.getMyMessageList().forEach(customMessage -> System.out.println(customMessage.getBody()));

                while (true){
                    System.out.println("Type (0=> Back): ");
                    String message = new Scanner(System.in).nextLine();
                    if (message.equals("0")){
                        break;
                    }

                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(selectedUser.getChatId());
                    sendMessage.setText(message);

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    selectedUser.getMyMessageList().add(new CustomMessage("Me: " + message));

                }
                break;
            default:
                System.out.println("Wrong option!");
        }
        messageToUserService();
    }

}
