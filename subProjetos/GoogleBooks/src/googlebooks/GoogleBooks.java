/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package googlebooks;

import googlebooks.ws.BookParser;

/**
 *
 * @author ezequieloliveira
 */
public class GoogleBooks {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        BookParser.read("harry potter e a pedra filosofal".replace(" ", "+")).forEach(b -> System.out.println(b));
    }
    
}
