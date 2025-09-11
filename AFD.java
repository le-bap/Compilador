import java.text.CharacterIterator;

public abstract class AFD{

    public abstract Token evaluate(CharacterIterator code); // a classe q implementar essa, terá q ter essa classe

    public boolean isTokenSeparator(CharacterIterator code){
        char current = code.current();

        if (current == ' ' || current == '+' || current == '-' || current == '*' ||
        current == '/' || current == '(' || current == ')' || current == '@' ||
        current == '\n' || current == '\"' || current == CharacterIterator.DONE){
            return true;
        }

        if (verificarPalavra(code, "ingrediente")){
            return true;
        } 
        if (verificarPalavra(code, "tempero")){
            return true;
        }
        if (verificarPalavra(code, "receitinha")){
            return true;
        }

        return false;
    }
    
    // private char proximoChar(CharacterIterator code) {
    //     char next = code.next();
    //     code.previous(); // volta para onde estava
    //     return next;
    // }

     public static boolean verificarPalavra(CharacterIterator code, String word) {
        int pos = code.getIndex(); // salva posição atual
        for (int i = 0; i < word.length(); i++) {
            if (code.current() != word.charAt(i)) { // verifico se o prox char eh diferente do esperado
                code.setIndex(pos);
                return false;
            }
            code.next(); // se nao for, avanço no texto
        }
        return true; // casou a palavra toda
    }
}