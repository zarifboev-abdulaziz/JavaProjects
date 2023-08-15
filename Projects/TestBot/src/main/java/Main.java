import bot.TestBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import service.AnswerService;
import service.QuestionService;
import service.SubjectService;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi api=new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(new TestBot());
        System.out.println("TEST_BOT STARTED");
        SubjectService.getInitialData();
        QuestionService.getInitialData();
        AnswerService.getInitialData();
    }
}
