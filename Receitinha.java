import java.text.CharacterIterator;

public class Receitinha extends AFD{
    @Override
    public Token evaluate(CharacterIterator code) {
        if (AFD.verificarPalavra(code, "receitinha")) {
            return new Token("reservada_RECEITINHA", "receitinha");
        }
        return null;
    }  
}
