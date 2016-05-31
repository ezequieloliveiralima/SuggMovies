package com.br.lp3.logger.consumer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author 41488350
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/myQueue"), // recurso
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") // tipo
})
public class LoggerConsumer implements MessageListener {

    @Override
    public void onMessage(Message message) {
        TextMessage txtMessage = (TextMessage) message;
        try {
            File file = new File("log.txt");
            if (!file.exists()) file.createNewFile();
            
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            
            String txt = new String(carregar(file));
            txt += "\n" + format.format(new Date()) + " - " + txtMessage.getText();
            salvar(file, txt);
        } catch (IOException ex) {
            Logger.getLogger(LoggerConsumer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LoggerConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public byte[] carregar(File arquivo)
            throws Exception {
        FileInputStream dispositivoDeEntrada = new FileInputStream(arquivo);
        byte[] conteudo = new byte[dispositivoDeEntrada.available()];
        dispositivoDeEntrada.read(conteudo);
        return conteudo;
    }

    public void salvar(File arquivo, String conteudo)
            throws IOException, Exception {
        try (FileOutputStream streamDeSaida = new FileOutputStream(arquivo)) {
            streamDeSaida.write(conteudo.getBytes());
        }
    }
    
}
