import java.text.CharacterIterator;

public class PontoEVirgula extends AFD{
    @Override
    public Token evaluate(CharacterIterator code) {
        if (code.current() == ';'){
            code.next();
            return new Token("PONTO-VIRGULA", ";");
        }
        return null;
    }
}
