
import java.text.CharacterIterator;

public class Comentario extends AFD {
    
    @Override
      public Token evaluate(CharacterIterator code) {

        if (code.current() == '@') {
            code.next(); 
            StringBuilder comentario = new StringBuilder();
            comentario.append("@");
            while (code.current() != CharacterIterator.DONE && code.current() != '@') {
                comentario.append(code.current());
                code.next();
            }

            if (code.current() == '@') {
                code.next(); 
                comentario.append("@");
                return new Token("COMMENT", comentario.toString());
            }

        }

        return null;
    }
}
