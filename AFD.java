import java.text.CharacterIterator;

public abstract class AFD{

    public abstract Token evaluate(CharacterIterator code); // a classe q implementar essa, terá q ter essa classe

    public boolean isTokenSeparator(CharacterIterator code){
        return code.current() == ' ' ||
            code.current() == '+' ||
            code.current() == '-' ||
            code.current() == '*' ||
            code.current() == '/' ||
            code.current() == '(' ||
            code.current() == ')' ||
            code.current() == '\n' ||
            code.current() == CharacterIterator.DONE;
    }
}