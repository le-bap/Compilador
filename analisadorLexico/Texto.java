package analisadorLexico;

import java.text.CharacterIterator;

public class Texto extends AFD {
    
    @Override
    public Token evaluate(CharacterIterator code) {
        
         if (code.current() == '"') {
            code.next(); 
            StringBuilder valor = new StringBuilder();
            valor.append('\"');
            while (code.current() != CharacterIterator.DONE && code.current() != '\"') {
                valor.append(code.current());
                code.next();
            }

            if (code.current() == '\"') {
                code.next(); 
                valor.append('\"');
                String lexema = valor.toString();

                // confere se eh do tipo prove
                if (lexema.equals("\"%i\"") || lexema.equals("\"%t\"") || lexema.equals("\"%r\"")) {
                    return new Token("TIPO_PROVE", lexema); 
                }

                // se nao for eh text normal
                return new Token("RECEITINHA", lexema);
            }

        }
        return null;
    }
}
