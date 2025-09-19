package analisadorLexico;

import java.util.ArrayList;
import java.text.StringCharacterIterator;
import java.text.CharacterIterator;
import java.util.List;

public class Lexer{

    private List<Token> tokens;
    private List<AFD> afds;
    private CharacterIterator code;
    private int linha = 1;

    public Lexer(String code){
        tokens = new ArrayList<>();
        afds = new ArrayList<>();
        this.code = new StringCharacterIterator(code);
        afds.add(new MathOperator());
        afds.add(new Number());
        afds.add(new Comentario());
        afds.add(new Texto());
        afds.add(new Palavras());
        afds.add(new Caracteres());
    }

    public void skipWhiteSpace(){
        while(code.current() == ' ' || code.current() == '\n' || code.current() == '\r'){
            if (code.current() == '\n') {
                linha++; 
            }
            code.next();
        }
    }

    public void error(){
        // implementar a linha q deu erro
        int idx = code.getIndex();
        System.out.println("Character not recognized in index: " + idx);
        System.out.println("Line: " + linha);
        throw new RuntimeException("Error: Token not recognized: "+ code.current());
    }

    private Token searchNextToken(){
        int pos = code.getIndex();
        for (AFD afd: afds){ // pra cada automato dentro da list de automatos
            Token t = afd.evaluate(code); // ve se algum deles consegue reconhecer o token
            if (t != null) return t;
            code.setIndex(pos);
        }
        return null;
    }

    public List<Token> getTokens(){
        Token t;
        do {
            skipWhiteSpace();
            t = searchNextToken();
            if (t == null) error();

            tokens.add(t);

        }while (!t.tipo.equals("EOF"));

        return tokens;
    }
}