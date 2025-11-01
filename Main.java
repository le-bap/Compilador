

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


    tokens.add(new Token("prove", "prove"));
    tokens.add(new Token("(", "("));
    tokens.add(new Token("tipo_prove", "\"%i\"")); 
    tokens.add(new Token("id", "num_maior"));
    tokens.add(new Token(")", ")"));
    tokens.add(new Token(";", ";"));


    // fim da receita
    tokens.add(new Token("pratopronto", "pratopronto"));
    tokens.add(new Token("EOF", "$"));

    // tokens.add(new Token("ingrediente", "ingrediente"));
    // tokens.add(new Token("id", "num_maior"));
    // tokens.add(new Token(";", ";"));

    // tokens.add(new Token("sirva", "sirva"));
    // tokens.add(new Token("(", "("));
    // tokens.add(new Token("receitinha", "digite um numero maior que 5"));
    // tokens.add(new Token(")", ")"));
    // tokens.add(new Token(";", ";"));

     // fim do tempere

    
    
    Parser parser = new Parser(tokens);
    parser.main();
  }
}

