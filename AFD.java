
import java.text.CharacterIterator;

public abstract class AFD{

    public abstract Token evaluate(CharacterIterator code); // a classe q implementar essa, terá q ter essa classe


    public boolean isTokenSeparator(CharacterIterator code){
        char current = code.current();

        if (current == ' ' || current == '+' || current == '-' || current == '*' ||
        current == '/' || current == '(' || current == ')' || current == '@' ||
        current == '\n' || current == '\r' || current == '\"' || current == ';'|| current == CharacterIterator.DONE){
            return true;
        }

        return false;
    }
    
    // private char proximoChar(CharacterIterator code) {
    //     char next = code.next();
    //     code.previous(); // volta para onde estava
    //     return next;
    // }

     public static boolean verificarPalavra(CharacterIterator code, String word) {
        int pos = code.getIndex(); // salva posição inicial
        for (int i = 0; i < word.length(); i++) {
            if (code.current() != word.charAt(i)) {
                code.setIndex(pos); // volta se não bateu
                return false;
            }
            code.next();
        }

        // depois q a palavra casou precisa ver se tem um separador de tokem depois
        char next = code.current();
        if (next == CharacterIterator.DONE || 
            next == ' ' || next == '\n' || next == ';' ||next == '+' || next == '-' ||
            next == '*' || next == '/' || next == '(' || next == ')' || next == '@' ||
            next == '\"') {
            return true;
        }

        code.setIndex(pos);
        return false;
    }
}