package analisadorLexico;

import java.text.CharacterIterator;
import java.util.Arrays;
import java.util.List;

public class Palavras extends AFD {

    // Lista de palavras reservadas da classe
    private static final List<String> reservadas = Arrays.asList(
    "receita", "pratopronto", "ingrediente", "tempero", "receitinha", "prove",
    "sirva", "deguste", "tempere", "cozinhe_enquanto", "bata", "ferva"
    );

    @Override
    public Token evaluate(CharacterIterator code) {
        // se começar com letra ou "_"
        if (Character.isLetter(code.current()) || code.current() == '_') {
            StringBuilder sb = new StringBuilder();

            // Continua lendo enquanto for letra, numero ou _
            while (Character.isLetterOrDigit(code.current()) || code.current() == '_') {
                sb.append(code.current());
                code.next();
            }

            String lexema = sb.toString();


            // Verifica se eh palavra e define qual o token
            if (reservadas.contains(lexema)) {
                if (lexema.equals("tempero") || lexema.equals("ingrediente") || lexema.equals("receitinha")){
                    return new Token("RESERVADA_" + lexema.toUpperCase(), lexema);
                }
                else if (lexema.equals("pratopronto")){
                    return new Token("FIM_ARQUIVO", lexema);
                }
                else if (lexema.equals("receita")){
                    return new Token("INICIO_ARQUIVO", lexema);
                }
                else if (lexema.equals("prove")){
                    return new Token("INPUT", lexema);
                }
                else if (lexema.equals("sirva")){
                    return new Token("PRINT", lexema);
                }
                else if (lexema.equals("deguste")){
                    return new Token("IF", lexema);
                }
                else if (lexema.equals("tempere")){
                    return new Token("ELSE", lexema);
                }
                else if (lexema.equals("cozinhe_enquanto")){
                    return new Token("WHILE", lexema);
                }
                else if (lexema.equals("bata")){
                    return new Token("FOR", lexema);
                }
                else if (lexema.equals("bata")){
                    return new Token("FOR", lexema);
                }
                else if (lexema.equals("ferva")){
                    return new Token("DO_WHILE", lexema);
                }

            } else {
                // se nao for reservada, eh um identificador
                return new Token("IDENTIFICADOR", lexema);
            }
        }

        return null;
    }
}
