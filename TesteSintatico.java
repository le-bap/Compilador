

import java.util.ArrayList;
import java.util.List;
import analisadorLexico.Token;
import analisadorSintatico.Parser;

public class TesteSintatico {
  public static void main(String[] args) {
    List<Token> tokens = new ArrayList<>();

    tokens.add(new Token("INICIO_ARQUIVO", "receita"));

    // ingrediente max = 10 + 2;
    tokens.add(new Token("RESERVADA_INGREDIENTE", "ingrediente"));
    tokens.add(new Token("IDENTIFICADOR", "max"));
    tokens.add(new Token("ATRIBUICAO", "="));
    tokens.add(new Token("INGREDIENTE", "10"));
    tokens.add(new Token("OP_ARITMETICO", "+"));
    tokens.add(new Token("INGREDIENTE", "2"));
    tokens.add(new Token("PONTO_VIRGULA", ";"));

    //sirva("digite um numero");
    tokens.add(new Token("PRINT", "sirva"));
    tokens.add(new Token("ABRE_PARENTESES", "("));
    tokens.add(new Token("RECEITINHA", "\"digite um numero\""));
    tokens.add(new Token("FECHA_PARENTESES", ")"));
    tokens.add(new Token("PONTO_VIRGULA", ";"));

    // ingrediente x;
    tokens.add(new Token("RESERVADA_INGREDIENTE", "ingrediente"));
    tokens.add(new Token("IDENTIFICADOR", "x"));
    tokens.add(new Token("PONTO_VIRGULA", ";"));

    // prove("%i" x);
    tokens.add(new Token("INPUT", "prove"));
    tokens.add(new Token("ABRE_PARENTESES", "("));
    tokens.add(new Token("TIPO_PROVE", "\"%i\""));
    tokens.add(new Token("IDENTIFICADOR", "x"));
    tokens.add(new Token("FECHA_PARENTESES", ")"));
    tokens.add(new Token("PONTO_VIRGULA", ";"));

    // deguste (x <= max) { sirva ("max eh menor ou igual a 5"); }
    tokens.add(new Token("IF", "deguste"));
    tokens.add(new Token("ABRE_PARENTESES", "("));
    tokens.add(new Token("IDENTIFICADOR", "x"));
    tokens.add(new Token("OP_COMPARACAO", "<="));
    tokens.add(new Token("IDENTIFICADOR", "max"));
    tokens.add(new Token("FECHA_PARENTESES", ")"));
    tokens.add(new Token("ABRE_CHAVES", "{"));
      tokens.add(new Token("PRINT", "sirva"));
      tokens.add(new Token("ABRE_PARENTESES", "("));
      tokens.add(new Token("RECEITINHA", "\"max eh menor ou igual a 5\""));
      tokens.add(new Token("FECHA_PARENTESES", ")"));
      tokens.add(new Token("PONTO_VIRGULA", ";"));
    tokens.add(new Token("FECHA_CHAVES", "}"));
    // tempere {
    tokens.add(new Token("ELSE", "tempere"));
    tokens.add(new Token("ABRE_CHAVES", "{"));

    // ingrediente num_maior;
    tokens.add(new Token("RESERVADA_INGREDIENTE", "ingrediente"));
    tokens.add(new Token("IDENTIFICADOR", "num_maior"));
    tokens.add(new Token("PONTO_VIRGULA", ";"));

    // sirva("digite um numero maior que 5");
    tokens.add(new Token("PRINT", "sirva"));
    tokens.add(new Token("ABRE_PARENTESES", "("));
    tokens.add(new Token("RECEITINHA", "\"digite um numero maior que 5\""));
    tokens.add(new Token("FECHA_PARENTESES", ")"));
    tokens.add(new Token("PONTO_VIRGULA", ";"));

    // prove("%i" num_maior);
    tokens.add(new Token("INPUT", "prove"));
    tokens.add(new Token("ABRE_PARENTESES", "("));
    tokens.add(new Token("TIPO_PROVE", "\"%i\""));
    tokens.add(new Token("IDENTIFICADOR", "num_maior"));
    tokens.add(new Token("FECHA_PARENTESES", ")"));
    tokens.add(new Token("PONTO_VIRGULA", ";"));

    tokens.add(new Token("FECHA_CHAVES", "}")); // fim do tempere

    // fim da receita
    tokens.add(new Token("FIM_ARQUIVO", "pratopronto"));
    tokens.add(new Token("EOF", "$"));

    
    Parser parser = new Parser(tokens);
    parser.main();
  }
}

