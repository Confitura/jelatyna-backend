package pl.confitura.jelatyna.mail;


import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FakeMailSender implements MailSender {
    @Override
    public void send(String template, MessageInfo messageInfo) throws IOException, MandrillApiError {

    }
}
