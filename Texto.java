import java.text.CharacterIterator;

public class Texto extends AFD {
    
    @Override
    public Token evaluate(CharacterIterator code) {
        
         if (code.current() == '\"') {
            code.next(); 
            StringBuilder comentario = new StringBuilder();
            comentario.append('\"');
            while (code.current() != CharacterIterator.DONE && code.current() != '\"') {
                comentario.append(code.current());
                code.next();
            }

            if (code.current() == '\"') {
                code.next(); 
                comentario.append('\"');
                return new Token("TEXT", comentario.toString());
            }

        }
        return null;
    }
}
