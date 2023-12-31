package uz.pdp;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.model.User;
import uz.pdp.service.AdminService;
import uz.pdp.service.authService.AuthService;
import uz.pdp.service.studentService.StudentService;
import uz.pdp.utils.DataBase;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

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

    StudentService studentService = new StudentService();
    AdminService adminService = new AdminService();
    AuthService authService = new AuthService();

    @Override
    public void onUpdateReceived(Update update) {
        User currentUser = DataBase.getUserFromList(update);

       currentUser = authService.registerProcess(currentUser, update);
       if (currentUser.getPhoneNumber() == null){
           return;
       }

        switch (currentUser.getRole()) {
            case STUDENT:
                studentService.studentMenu(update, currentUser);
                break;
            case ADMIN:
                adminService.adminMenu(update, currentUser);
                break;
        }


    }
}
