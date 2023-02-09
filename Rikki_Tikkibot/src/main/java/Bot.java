import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

//https://core.telegram.org/bots/tutorial
//https://github.com/rubenlagus/TelegramBots/blob/master/README.md
public class Bot  extends TelegramLongPollingBot{
    public static void main(String[] args) throws TelegramApiException {
        //Registering the Bot
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(new Bot());
    }
    final static String NameOfBot = "Rikki_TikkiBot";

    @Override
    public String getBotUsername() {
        return NameOfBot;
    }

    @Override
    public String getBotToken() {
        return "6205520866:AAExQuZaT2kCJXqPkdITlBnfIA73cUWNewU";
    }

    @Override
    public void onUpdateReceived(Update update) {
        var msg = update.getMessage(); //What was sent.
        var user = msg.getFrom(); //The user - Who sent the message.
        var id = user.getId();
        System.out.println(user.getFirstName() + " wrote " + msg.getText());

        if(msg.isCommand()){

            return;                                     //We don't want to echo commands, so we exit
        }

        sendText(id, msg.getText());
    }

    public void sendText(Long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }
}
