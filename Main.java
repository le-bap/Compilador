

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import analisadorLexico.Lexer;
import analisadorLexico.Token;
import analisadorSintatico.Parser;

public class Main {
  public static void main(String[] args) {
    try {
            // le todo o conteúdo do arquivo em uma String
            Path path = Path.of("testando-scripts.txt");
            String conteudo = Files.readString(path, StandardCharsets.UTF_8);

            // gera o lexer
            Lexer lexer = new Lexer(conteudo);
            List<Token> tokens = lexer.getTokens();

            // printa os tokens
            System.out.println("Lista de tokens");
            for (Token t : tokens) {
                System.out.println(t);
            }

            System.out.println("\n\n");
            // gera o parser 
            Parser parser = new Parser(tokens);
            parser.main();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  
}

