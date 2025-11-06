

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import analisadorLexico.Lexer;
import analisadorLexico.Token;
import analisadorSintatico.Parser;

public class TesteSintatico {
  public static void main(String[] args) {
    try{
      // Lê todo o conteúdo do arquivo em uma String
      Path path = Path.of("testando-scripts.txt");
      String conteudo = Files.readString(path, StandardCharsets.UTF_8);

      Lexer lexer = new Lexer(conteudo);
      List<Token> tokens = lexer.getTokens();
      
      Parser parser = new Parser(tokens);
      parser.main();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}

