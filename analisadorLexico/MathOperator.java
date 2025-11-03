package analisadorLexico;

import java.text.CharacterIterator;

public class MathOperator extends AFD{

    @Override
    public Token evaluate(CharacterIterator code){
        switch(code.current()){
            case '+':
                code.next();
                return new Token("OP_ARITMETICO", "+");
            case '-':
                code.next();
                return new Token("OP_ARITMETICO", "-");
            case '*':
                code.next();
                return new Token("OP_ARITMETICO", "*");
            case '/':
                code.next();
                return new Token("OP_ARITMETICO", "/");
            
            case CharacterIterator.DONE: // fim do arquivo
                return new Token("EOF", "$");
            
            default:
                return null;
        }
    }
}