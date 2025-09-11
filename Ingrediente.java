import java.text.CharacterIterator;

public class Ingrediente extends AFD {
    @Override
    public Token evaluate(CharacterIterator code) {
        if (AFD.verificarPalavra(code, "ingrediente")) {
            return new Token("reservada_INGREDIENTE", "ingrediente");
        }
        return null;
    }    
}
