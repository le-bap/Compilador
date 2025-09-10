import java.text.CharacterIterator;

public abstract class AFD{

    public abstract Token evaluate(CharacterIterator code); // a classe q implementar essa, terá q ter essa classe

    public boolean isTokenSeparator(CharacterIterator code){
        char current = code.current();

        if (current == ' ' || current == '+' || current == '-' || current == '*' ||
        current == '/' || current == '(' || current == ')' || current == '@' ||
        current == '\n' || current == CharacterIterator.DONE){
            return true;
        }
        
        if (current == 'i' && proximoChar(code) == 'n' && proximoChar(code) == 'g'
        && proximoChar(code) == 'r' && proximoChar(code) == 'e' && proximoChar(code) == 'd'
        && proximoChar(code) == 'i' && proximoChar(code) == 'e' && proximoChar(code) == 'n'
        && proximoChar(code) == 't' && proximoChar(code) == 'e'){
            return true;
        } 
        
            return false;
    }
    
    private char proximoChar(CharacterIterator code) {
        char next = code.next();
        code.previous(); // volta para onde estava
        return next;
    }
}