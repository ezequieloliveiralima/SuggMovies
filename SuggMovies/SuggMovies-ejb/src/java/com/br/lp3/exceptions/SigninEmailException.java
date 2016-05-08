package com.br.lp3.exceptions;

/**
 *
 * @author ezequieloliveira
 */
public class SigninEmailException extends Exception {

    public SigninEmailException() {
        super("Erro no login, e-mail inválido.");
    }
    
}
