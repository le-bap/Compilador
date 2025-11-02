

import java.util.ArrayList;
import java.util.List;
import analisadorLexico.Token;
import analisadorSintatico.Parser;

public class Main {
  public static void main(String[] args) {
    List<Token> tokens = new ArrayList<>();

    tokens.add(new Token("receita", "receita"));

    // sirva
    tokens.add(new Token("sirva", "sirva"));
    tokens.add(new Token("(", "("));
    tokens.add(new Token("receitinha", "Hello World"));
    tokens.add(new Token(")", ")"));
    tokens.add(new Token(";", ";"));

    // deguste (x <= max) { sirva("max é menor ou igual a 5"); }
    tokens.add(new Token("deguste", "deguste"));
    tokens.add(new Token("(", "("));
    tokens.add(new Token("id", "x"));
    tokens.add(new Token("<=", "<="));
    tokens.add(new Token("num", "10"));
    tokens.add(new Token(")", ")"));
    tokens.add(new Token("{", "{"));
      tokens.add(new Token("sirva", "sirva"));
      tokens.add(new Token("(", "("));
      tokens.add(new Token("receitinha", "Hello World"));
      tokens.add(new Token(")", ")"));
      tokens.add(new Token(";", ";"));
    tokens.add(new Token("}", "}"));
    // tempere 
    tokens.add(new Token("tempere", "tempere"));
    tokens.add(new Token("{", "{"));
    tokens.add(new Token("}", "}"));
    
    // bata
    tokens.add(new Token("bata", "bata"));
    tokens.add(new Token("(", "("));
    tokens.add(new Token("id", "l"));
    tokens.add(new Token("==", "=="));
    tokens.add(new Token("num", "3"));
    tokens.add(new Token(")", ")"));
    tokens.add(new Token("{", "{"));
    tokens.add(new Token("}", "}"));

     // cozinhe enquanto
    tokens.add(new Token("cozinhe_enquanto", "cozinhe_enquanto"));
    tokens.add(new Token("(", "("));
    tokens.add(new Token("id", "lis"));
    tokens.add(new Token("==", "=="));
    tokens.add(new Token("num", "6"));
    tokens.add(new Token(")", ")"));
    tokens.add(new Token("{", "{"));
      tokens.add(new Token("sirva", "sirva"));
      tokens.add(new Token("(", "("));
      tokens.add(new Token("receitinha", "eh q eu nao to falando tudo"));
      tokens.add(new Token(")", ")"));
      tokens.add(new Token(";", ";"));
    tokens.add(new Token("}", "}"));

    // ferva bata
    tokens.add(new Token("ferva", "ferva"));
    tokens.add(new Token("{", "{"));
      // prove
      tokens.add(new Token("prove", "prove"));
      tokens.add(new Token("(", "("));
      tokens.add(new Token("tipo_prove", "\"%i\"")); 
      tokens.add(new Token("id", "num_maior"));
      tokens.add(new Token(")", ")"));
      tokens.add(new Token(";", ";"));
    tokens.add(new Token("}", "}"));
    tokens.add(new Token("bata", "bata"));
    tokens.add(new Token("(", "("));
    tokens.add(new Token("id", "qtd"));
    tokens.add(new Token("<=", "<="));
    tokens.add(new Token("num", "8.9"));
    tokens.add(new Token(")", ")"));
    

    // fim da receita
    tokens.add(new Token("pratopronto", "pratopronto"));
    tokens.add(new Token("EOF", "$"));
    
    Parser parser = new Parser(tokens);
    parser.main();
  }
}

