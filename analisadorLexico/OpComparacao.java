package analisadorLexico;
import java.text.CharacterIterator;

public class OpComparacao extends AFD {

    @Override
    public Token evaluate(CharacterIterator code) {
        char atual = code.current();

        switch (atual) {
            case '>':
                code.next();
                if (code.current() == '=') { 
                    code.next();
                    return new Token("OP_COMPARACAO", ">=");
                }
                return new Token("OP_COMPARACAO", ">");
            
            case '<':
                code.next();
                if (code.current() == '=') {
                    code.next();
                    return new Token("OP_COMPARACAO", "<=");
                }
                return new Token("OP_COMPARACAO", "<");

            case '=':
                code.next();
                if (code.current() == '=') {
                    code.next();
                    return new Token("OP_COMPARACAO", "==");
                }
                return new Token("ATRIBUICAO", "=");

            default:
                return null;
        }
    }
}
