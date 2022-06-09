package mrs.infrastructure.datasource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Message {
    public final MessageSource messagesource;

    public Message(MessageSource messagesource) {
        this.messagesource = messagesource;
    }

    public String getMessageByKey(String messageKey) {
        return messagesource.getMessage(messageKey, new String[]{}, Locale.JAPAN);
    }
}
