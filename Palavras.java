import java.text.CharacterIterator;
import java.util.Arrays;
import java.util.List;

public class Palavras extends AFD {

    // Lista de palavras reservadas da classe
    private static final List<String> reservadas = Arrays.asList(
        "ingrediente", "tempero", "receitinha", "prove", "pratopronto", "receita"
    );

    @Override
    public Token evaluate(CharacterIterator code) {
        // Só entra se começar com letra ou "_"
        if (Character.isLetter(code.current()) || code.current() == '_') {
            StringBuilder sb = new StringBuilder();

            // Continua lendo enquanto for letra, número ou "_"
            while (Character.isLetterOrDigit(code.current()) || code.current() == '_') {
                sb.append(code.current());
                code.next();
            }

            String lexema = sb.toString();

            // Verifica se é palavra reservada
            if (reservadas.contains(lexema)) {
                return new Token("RESERVADA_" + lexema.toUpperCase(), lexema);
            } else {
                // Caso não seja reservada -> identificador
                return new Token("IDENTIFICADOR", lexema);
            }
        }

        return null;
    }
}
