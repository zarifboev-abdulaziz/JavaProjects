package uz.pdp;

//Yunus Boliyev 14.12.2021, 16:41

import lombok.SneakyThrows;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import uz.pdp.enums.BotState;
import uz.pdp.model.User;

import static uz.pdp.Service.BotService.saveAndGetUser;
import static uz.pdp.Service.BotService.updateUser;

public class Main extends TelegramLongPollingBot {
    @SneakyThrows
    public static void main(String[] args) {
        TelegramBotsApi telegramBotsApi=new TelegramBotsApi(DefaultBotSession.class);

        try {
            telegramBotsApi.registerBot(new Main());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getBotUsername() {
        return "passportfinder_bot";
    }

    @Override
    public String getBotToken() {
        return "5017756589:AAFF1gj63P600yVFDRT14sKKOU3hrFKTDj0";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            String text=update.getMessage().getText();
            User user=saveAndGetUser(update);
            SendMessage sendMessage=new SendMessage();
            if(user.getState()!=null && user.getState().equals(BotState.CONFIRM_FIRST_NAME)){
                user.setUsername(text);
                updateUser(user);
                user.setState(BotState.ASK_SERIAL_NUMMBER);
                sendMessage.setText(user.getUsername());
                sendMessage.setChatId(update.getMessage().getChatId().toString());

            }
            if(text.equals("/start")){
                sendMessage.setText("Xush kelibsiz Passport_Finder_Bot ga!!!");
                user.setState(BotState.ASK_FISRT_NAME);
                updateUser(user);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if(user.getState()!=null && user.getState().equals(BotState.ASK_FISRT_NAME)){
                sendMessage.setText("Passport egasini ismini kiriting: ");
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                user.setState(BotState.CONFIRM_FIRST_NAME);
                updateUser(user);
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }
    }
}
