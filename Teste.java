
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class Teste {

    public static void main(String[] args) {
        try {
            System.out.println("Lista de tokens");
            // Lê todo o conteúdo do arquivo em uma String
            Path path = Path.of("testando-scripts.txt");
            String conteudo = Files.readString(path, StandardCharsets.UTF_8);

            Lexer lexer = new Lexer(conteudo);
            List<Token> tokens = lexer.getTokens();

            for (Token t : tokens) {
                System.out.println(t);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
