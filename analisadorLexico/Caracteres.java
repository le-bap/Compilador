package analisadorLexico;

import java.text.CharacterIterator;

public class Caracteres extends AFD {

    @Override
    public Token evaluate(CharacterIterator code) {
        char lexema = code.current();
        
        if (lexema == ';'){
            code.next();
            return new Token("PONTO_VIRGULA", ";");
        }
        else if (lexema == ','){
            code.next();
            return new Token("VIRGULA", ",");
        }
        else if (lexema == '{'){
            code.next();
            return new Token("ABRE_CHAVES", "{");
        }
        else if (lexema == '}'){
            code.next();
            return new Token("FECHA_CHAVES", "}");
        }
        else if (lexema == '('){
            code.next();
            return new Token("ABRE_PARENTESES", "(");
        }
        else if (lexema == ')'){
            code.next();
            return new Token("FECHA_PARENTESES", ")");
        }

        return null;
    }
}