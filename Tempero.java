import java.text.CharacterIterator;

public class Tempero extends AFD{
    @Override
    public Token evaluate(CharacterIterator code) {
        if (AFD.verificarPalavra(code, "tempero")) {
            return new Token("reservada_TEMPERO", "tempero");
        }
        return null;
    }  
}
