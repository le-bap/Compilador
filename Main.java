

import java.util.ArrayList;
import java.util.List;
import analisadorLexico.Token;
import analisadorSintatico.Parser;

public class Main {
  public static void main(String[] args) {
    List<Token> tokens = new ArrayList<>();

    tokens.add(new Token("INICIO_ARQUIVO", "receita"));

    // sirva
    tokens.add(new Token("PRINT", "sirva"));
    tokens.add(new Token("ABRE_PARENTESES", "("));
    tokens.add(new Token("RECEITINHA", "Hello World"));
    tokens.add(new Token("FECHA_PARENTESES", ")"));
    tokens.add(new Token("PONTO_VIRGULA", ";"));

    // deguste (x <= max) { sirva("max é menor ou igual a 5"); }
    tokens.add(new Token("IF", "deguste"));
    tokens.add(new Token("ABRE_PARENTESES", "("));
    tokens.add(new Token("IDENTIFICADOR", "x"));
    tokens.add(new Token("OP_COMPARACAO", "<="));
    tokens.add(new Token("INGREDIENTE", "10"));
    tokens.add(new Token("FECHA_PARENTESES", ")"));
    tokens.add(new Token("ABRE_CHAVES", "{"));
      tokens.add(new Token("PRINT", "sirva"));
      tokens.add(new Token("ABRE_PARENTESES", "("));
      tokens.add(new Token("RECEITINHA", "Hello World"));
      tokens.add(new Token("FECHA_PARENTESES", ")"));
      tokens.add(new Token("PONTO_VIRGULA", ";"));
    tokens.add(new Token("FECHA_CHAVES", "}"));
    // tempere 
    tokens.add(new Token("ELSE", "tempere"));
    tokens.add(new Token("ABRE_CHAVES", "{"));
    tokens.add(new Token("FECHA_CHAVES", "}"));
    
    // bata
    tokens.add(new Token("FOR", "bata"));
    tokens.add(new Token("ABRE_PARENTESES", "("));
    tokens.add(new Token("IDENTIFICADOR", "l"));
    tokens.add(new Token("OP_COMPARACAO", "=="));
    tokens.add(new Token("INGREDIENTE", "3"));
    tokens.add(new Token("FECHA_PARENTESES", ")"));
    tokens.add(new Token("ABRE_CHAVES", "{"));
    tokens.add(new Token("FECHA_CHAVES", "}"));

     // cozinhe enquanto
    tokens.add(new Token("WHILE", "cozinhe_enquanto"));
    tokens.add(new Token("ABRE_PARENTESES", "("));
    tokens.add(new Token("IDENTIFICADOR", "lis"));
    tokens.add(new Token("OP_COMPARACAO", "=="));
    tokens.add(new Token("INGREDIENTE", "6"));
    tokens.add(new Token("FECHA_PARENTESES", ")"));
    tokens.add(new Token("ABRE_CHAVES", "{"));
      tokens.add(new Token("PRINT", "sirva"));
      tokens.add(new Token("ABRE_PARENTESES", "("));
      tokens.add(new Token("RECEITINHA", "eh q eu nao to falando tudo"));
      tokens.add(new Token("FECHA_PARENTESES", ")"));
      tokens.add(new Token("PONTO_VIRGULA", ";"));
    tokens.add(new Token("FECHA_CHAVES", "}"));

    // comentario
    tokens.add(new Token("COMMENT", "@testando@"));

    // ferva bata
    tokens.add(new Token("DO_WHILE", "ferva"));
    tokens.add(new Token("ABRE_CHAVES", "{"));
      // prove
      tokens.add(new Token("INPUT", "prove"));
      tokens.add(new Token("ABRE_PARENTESES", "("));
      tokens.add(new Token("TIPO_PROVE", "\"%i\"")); 
      tokens.add(new Token("IDENTIFICADOR", "num_maior"));
      tokens.add(new Token("FECHA_PARENTESES", ")"));
      tokens.add(new Token("PONTO_VIRGULA", ";"));
    tokens.add(new Token("FECHA_CHAVES", "}"));
    tokens.add(new Token("WHILE", "cozinhe_enquanto"));
    tokens.add(new Token("ABRE_PARENTESES", "("));
    tokens.add(new Token("IDENTIFICADOR", "qtd"));
    tokens.add(new Token("OP_COMPARACAO", "<="));
    tokens.add(new Token("TEMPERO", "8.9"));
    tokens.add(new Token("FECHA_PARENTESES", ")"));
    
    // declaracao
    tokens.add(new Token("RESERVADA_INGREDIENTE", "ingrediente"));
    tokens.add(new Token("IDENTIFICADOR", "tomate"));
    tokens.add(new Token("PONTO_VIRGULA", ";"));

    tokens.add(new Token("RESERVADA_TEMPERO", "tempero"));
    tokens.add(new Token("IDENTIFICADOR", "oregano"));
    tokens.add(new Token("PONTO_VIRGULA", ";"));

    tokens.add(new Token("RESERVADA_RECEITINHA", "receitinha"));
    tokens.add(new Token("IDENTIFICADOR", "bolo"));
    tokens.add(new Token("PONTO_VIRGULA", ";"));

    // declaracao com atribuicao
    tokens.add(new Token("RESERVADA_TEMPERO", "tempero"));
    tokens.add(new Token("IDENTIFICADOR", "vambora"));
    tokens.add(new Token("ATRIBUICAO", "="));
    tokens.add(new Token("TEMPERO", "10.3"));
    tokens.add(new Token("PONTO_VIRGULA", ";"));

    // atribuicao direta 
    tokens.add(new Token("IDENTIFICADOR", "teste"));
    tokens.add(new Token("ATRIBUICAO", "="));
    tokens.add(new Token("INGREDIENTE", "3"));
    tokens.add(new Token("OP_ARITMETICO", "+"));
    tokens.add(new Token("TEMPERO", "10.3"));
    tokens.add(new Token("OP_ARITMETICO", "*"));
    tokens.add(new Token("TEMPERO", "10.3"));
    tokens.add(new Token("PONTO_VIRGULA", ";"));

    // fim da receita
    tokens.add(new Token("FIM_ARQUIVO", "pratopronto"));
    tokens.add(new Token("EOF", "$"));
    
    Parser parser = new Parser(tokens);
    parser.main();
  }
}

