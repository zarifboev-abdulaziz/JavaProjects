package uz.pdp;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.polls.Poll;
import org.telegram.telegrambots.meta.api.objects.polls.PollAnswer;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.model.Document;
import uz.pdp.model.FileFromJson;
import uz.pdp.model.User;
import uz.pdp.model.enums.DocType;
import uz.pdp.model.enums.Round;
import uz.pdp.utils.DataBase;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import static uz.pdp.utils.Constants.*;


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

        if (update.hasMessage()){
            process(update);
        }

    }



    private void process(Update update) {
        User currentUser = DataBase.getUserFromList(update);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(currentUser.getChatId());
        String msg = "";
        if (update.hasMessage() && update.getMessage().hasText()){
             msg = update.getMessage().getText();
        }

        if (msg.equals("/start")){
            sendMessage.setText("Assalomu Alaykum, Botga hush kelibsiz");
            currentUser.setRound(Round.R1);
            sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (msg.equals("Main Menu")){
            sendMessage.setText("Select one option please");
            currentUser.setRound(Round.R1);
            sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        switch (currentUser.getRound()){
            case R1:
                processR1(currentUser, update.getMessage());
                break;
            case R2:
                processR2(currentUser, update.getMessage());
                break;
            case R3:
                processR3(currentUser, update.getMessage());
                break;
            case R3r1:
                processR3r1(currentUser, update.getMessage());
                break;
            case R4:
                processR4(currentUser, update.getMessage());
                break;
            case R4r1:
                processR4r1(currentUser, update.getMessage());
                break;
            case R5:
                processR5(currentUser, update.getMessage());
                break;
            case R6:
                processR6(currentUser, update.getMessage());
                break;
            case R7:
                processR7(currentUser, update.getMessage());
                break;
            case R8:
                processR8(currentUser, update.getMessage());
                break;
            case R9:
                processR9(currentUser, update.getMessage());
                break;
            case R10: break;
        }

    }

    private void processR9(User currentUser, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(currentUser.getChatId());
        String inputDocSerialN = message.getText();

        boolean isFound = false;
        for (Document document : DataBase.documentList) {
            if (document.getSerialNum() != null){
                if (document.getSerialNum().equals(inputDocSerialN)){
                    isFound = true;
                    sendMessage.setText("Congratulations! Your document found!" + "\n" +
                    "You can contact with: " + document.getUser().getPhoneNumber() + "\n" +
                            "Reserve contact: " + document.getUser().getSecondPhoneNumber());

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    break;
                }
            }
        }

        if (!isFound){
            sendMessage.setText("Unfortunately your document not found!\n " +
                    "we will hope it will found soon\n" +
                    "Don't forget to check daily");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            sendMessage.setText("Select one option: ");
            currentUser.setRound(Round.R1);
            sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

    private void processR8(User currentUser, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(currentUser.getChatId());
        String msg = message.getText();
        Document document = DataBase.getDocumentOfUser(currentUser);

        if (msg.equals("Yes")){
            sendMessage.setText("Data Confirmed! Successfully added to the System!" + "\n" +
            "Select an option: ");

            currentUser.setRound(Round.R1);
            sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }else if (msg.equals("No")){
            sendMessage.setText("Data cancelled!" + "\n" +
                    "Select an option");

            DataBase.documentList.remove(document);
            currentUser.setRound(Round.R1);
            sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

    private void processR7(User currentUser, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(currentUser.getChatId());
        String msg = message.getText();
        Document document = DataBase.getDocumentOfUser(currentUser);


        if (msg.equals("Back")){
            sendMessage.setText("Please input Second Phone Number or email");
            currentUser.setRound(Round.R6);
            sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            return;
        }

        if (document == null){
            return;
        }
        document.setPrize(msg);

        if (document.getImgFile_id() != null){
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(currentUser.getChatId());
            sendPhoto.setPhoto(new InputFile(document.getImgFile_id()));
            sendPhoto.setCaption("Document Type: " + document.getDocumentType().getUzName() + "\n" +
                    "Serial Number: " + document.getSerialNum() + "\n" +
                    "Contact Number: " + currentUser.getPhoneNumber() + "\n" +
                    "Reserve contact: " + currentUser.getSecondPhoneNumber() + "\n" +
                    "Prize: " + document.getPrize());

            try {
                execute(sendPhoto);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        } else  {
            sendMessage.setText("Document Type: " + document.getDocumentType().getUzName() + "\n" +
                    "Serial Number: " + document.getSerialNum() + "\n" +
                    "Contact Number: " + currentUser.getPhoneNumber() + "\n" +
                    "Reserve contact: " + currentUser.getSecondPhoneNumber() + "\n" +
                    "Prize: " + document.getPrize());

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        sendMessage.setText("Will you confirm this data? ");
        currentUser.setRound(Round.R8);
        sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    private void processR6(User currentUser, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(currentUser.getChatId());
        String msg = message.getText();
        Document document = DataBase.getDocumentOfUser(currentUser);

        currentUser.setEmail(msg);
        currentUser.setSecondPhoneNumber(msg);

        if (msg.equals("Back")){
            sendMessage.setText("Share Contact please: ");
            currentUser.setRound(Round.R5);
            sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            return;
        }

        currentUser.setRound(Round.R7);
        sendMessage.setText("Enter prize for the person who found: ");

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    private void processR5(User currentUser, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(currentUser.getChatId());
        Document document = DataBase.getDocumentOfUser(currentUser);

        if (message.hasText() && message.getText().equals("Back")){
            currentUser.setRound(Round.R4);
            document.setImgFile_id(null);
            sendMessage.setText("Do you have a photo?");
            sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            return;
        }

        if (message.hasContact()){
            Contact contact = message.getContact();
            currentUser.setPhoneNumber(contact.getPhoneNumber());
            currentUser.setRound(Round.R6);
            sendMessage.setText("Please input Second Phone Number or Email");
            sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

    private void processR4r1(User currentUser, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(currentUser.getChatId());
        Document document = DataBase.getDocumentOfUser(currentUser);

        if (message.hasText() && message.getText().equals("Back")){
            currentUser.setRound(Round.R4);
            sendMessage.setText("Upload photo Please");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            return;
        }

        if (message.hasPhoto()){
            List<PhotoSize> photo = message.getPhoto();
            String fileId = photo.get(1).getFileId();

            //https://api.telegram.org/file/bot<token>/<file_path>


            Gson gson = new Gson();
            try {
                URL url = new URL("https://api.telegram.org/bot"+ botToken +"/getFile?file_id=" + fileId);
                URLConnection urlConnection = url.openConnection();


                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));

                FileFromJson fileFromJson = gson.fromJson(bufferedReader, FileFromJson.class);

                try {
                    downloadFile(fileFromJson.getResult().getFilePath(), new File("src/main/resources/"+"afasf.jpeg"));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

                document.setImgFile_id(fileFromJson.getResult().getFileId());

            } catch (IOException e) {
                e.printStackTrace();
            }


            sendMessage.setText("Share Contact please: ");
            currentUser.setRound(Round.R5);
            sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }else if (message.hasText()){
            sendMessage.setText("Share Contact please: ");
            currentUser.setRound(Round.R5);
            sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

    private void processR4(User currentUser, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(currentUser.getChatId());
        String msg = message.getText();
        Document document = DataBase.getDocumentOfUser(currentUser);

        if (msg.equals("Back")){
            currentUser.setRound(Round.R3);
            sendMessage.setText("PLease input serial Number");
            sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            return;
        }
        if (msg.equals("Yes")){
            currentUser.setRound(Round.R4r1);
            sendMessage.setText("Upload photo please!");
            sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }else if (msg.equals("No")){
            currentUser.setRound(Round.R5);
            sendMessage.setText("Share your contact please.");
            sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }


    }


    private void processR3r1(User currentUser, Message message) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(currentUser.getChatId());
        String msg = message.getText();
        Document document = DataBase.getDocumentOfUser(currentUser);

        if (msg.equals("Back")){
            sendMessage.setText("Input serial Number!");
            currentUser.setRound(Round.R3);
            sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));
            assert document != null;
            document.setSerialNum(null);

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }
    }

    //Process Round 3
    private void processR3(User currentUser, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(currentUser.getChatId());
        String msg = message.getText();
        Document document = DataBase.getDocumentOfUser(currentUser);

        if (msg.equals("Back")){
            sendMessage.setText("Choose Document Type Please!");
            document.setDocumentType(null);
            currentUser.setRound(Round.R2);
            sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            return;
        }

        boolean isFound = false;

        for (Document document1 : DataBase.documentList) {
            if (document1.getSerialNum() != null){
                if (document1.getSerialNum().equals(msg)) {
                    isFound = true;
                    break;
                }
            }
        }

        if (isFound){
            sendMessage.setText("Bunday Hujjat allaqachon tizimga joylashtirilgan");
            currentUser.setRound(Round.R3r1);
            sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));

        }else {
            assert document != null;
            document.setSerialNum(msg);
            currentUser.setRound(Round.R4);
            sendMessage.setText("Do you have a photo of this Document? ");
            sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    //Process Round 2
    private void processR2(User currentUser, Message message) {
        DocType docType = null;
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(currentUser.getChatId());

        String msg = message.getText();

        if (msg.equals("Passport")){
            docType = DocType.PASSPORT;
        }else if (msg.equals("Haydovchilik guvohnomasi")){
            docType = DocType.DRIVER_LICENCE;
        }else if (msg.equals("ID karta")){
            docType = DocType.ID_CARD;
        }else if (msg.equals("Talabalik guvohnomasi")){
            docType = DocType.STUDENT_CARD;
        }else if (msg.equals("Plastik karta")){
            docType = DocType.CREDIT_CARD;
        }else if (msg.equals("Diplom, sertifikat")){
            docType = DocType.CERTIFICATE;
        }else if (msg.equals("Boshqa")){
            docType = DocType.OTHER;
        } else if (msg.equals("Back")){
            sendMessage.setText("Select one option!");
            currentUser.setRound(Round.R1);
            sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            return;
        }

        currentUser.setRound(Round.R3);
        sendMessage.setText("Please Input Document Serial Number: ");
        sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));

        Document document = new Document(docType, null, currentUser, null, null);
        DataBase.documentList.add(document);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }


    //Process Round 1
    private void processR1(User currentUser, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(currentUser.getChatId());

        String msg = message.getText();
        if (msg.equals("Add Document")){

            currentUser.setRound(Round.R2);
            sendMessage.setText("Choose Document Type!");
            sendMessage.setReplyMarkup(getReplyKeyBoard(currentUser));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }else if (msg.equals("Search Document")){

            currentUser.setRound(Round.R9);
            sendMessage.setText("Input document serial Number: ");

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }



    private ReplyKeyboard getReplyKeyBoard(User currentUser) {
//        SendPoll sendPoll = new SendPoll();
//        sendPoll.set

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> rowList = new ArrayList<>();
        keyboardMarkup.setKeyboard(rowList);
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow rowN = new KeyboardRow();

        Round currentRound = currentUser.getRound();

        switch (currentRound) {
            case R1:
                row1.add("Add Document");
                row1.add("Search Document");
                rowList.add(row1);
                break;
            case R2:
           KeyboardRow row2 = new KeyboardRow();
           KeyboardRow row3 = new KeyboardRow();

            row1.add("Passport");
            row1.add("Haydovchilik guvohnomasi");
            row1.add("ID karta");
            row2.add("Talabalik guvohnomasi");
            row2.add("Plastik karta");
            row2.add("Diplom, sertifikat");
            row3.add("Boshqa");
            rowN.add("Back");
            rowN.add("Main Menu");

            rowList.add(row1);
            rowList.add(row2);
            rowList.add(row3);
            rowList.add(rowN);

            break;
            case R3:
                rowN.add("Back");
                rowN.add("Main Menu");
                rowList.add(rowN);
                break;
            case R3r1:
                row1.add("Back");
                row1.add("Main menu");
                rowList.add(row1);
                break;
            case R4:
                row1.add("Yes");
                row1.add("No");
                rowList.add(row1);

                rowN.add("Back");
                rowN.add("Main Menu");
                rowList.add(rowN);
                break;
            case R4r1:
                rowN.add("Back");
                rowN.add("Main Menu");
                rowList.add(rowN);
                break;
            case R5:
                KeyboardButton shareButton = new KeyboardButton();
                shareButton.setText("Share contact");
                shareButton.setRequestContact(true);
                row1.add(shareButton);
                rowList.add(row1);

                rowN.add("Back");
                rowN.add("Main Menu");
                rowList.add(rowN);
                break;

            case R6:
                rowN.add("Back");
                rowN.add("Main Menu");
                rowList.add(rowN);
                break;

            case R7:
                rowN.add("Back");
                rowN.add("Main Menu");
                rowList.add(rowN);
                break;

            case R8:
                row1.add("Yes");
                row1.add("No");
                rowList.add(row1);
                break;
        }

        return keyboardMarkup;
    }

}
