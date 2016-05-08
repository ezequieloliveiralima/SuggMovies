package com.br.lp3.exceptions;

/**
 *
 * @author ezequieloliveira
 */
public class SigninPassException extends Exception {

    public SigninPassException() {
        super("Erro no login, senha não coincidente.");
    }
    
}
