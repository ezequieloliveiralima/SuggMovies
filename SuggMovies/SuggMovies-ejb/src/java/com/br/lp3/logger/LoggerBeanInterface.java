package com.br.lp3.logger;

import javax.ejb.Local;

/**
 *
 * @author ezequieloliveira
 */
@Local
public interface LoggerBeanInterface {
    public void sendMessage(String msg);
}
