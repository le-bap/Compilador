import java.util.List;

public class Teste{

    public static void main(String[] args){
        // Token num = new Token("NUM", "2");
        // Token op = new Token("op_mat", "+");
        // Token num2 = new Token("NUM", "5");

        // System.out.println(num);
        // System.out.println(op);
        // System.out.println(num2);

        String code = "5.6+ 6- 7.6  * -2  @ fazendo um comentario     @";
        Lexer lexer = new Lexer(code);
        List<Token> tokens = lexer.getTokens();
        for (Token t: tokens) {
            System.out.println(t);
        }
    }
}