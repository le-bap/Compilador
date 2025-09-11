import java.util.List;
public class Teste{

    public static void main(String[] args){

        String code = "5.6+ 6- 7.6  * -2  @ fazendo um comentario     @ \"saindo do comentario\" ingrediente tempero";
        
        Lexer lexer = new Lexer(code);
        List<Token> tokens = lexer.getTokens();
        for (Token t: tokens) {
            System.out.println(t);
        }
    }
}