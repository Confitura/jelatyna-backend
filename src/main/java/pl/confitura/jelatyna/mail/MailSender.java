package pl.confitura.jelatyna.mail;

import com.microtripit.mandrillapp.lutung.model.MandrillApiError;

import java.io.IOException;

public interface MailSender {
    void send(String template, MessageInfo messageInfo) throws IOException, MandrillApiError;
}
