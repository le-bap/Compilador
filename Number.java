import java.text.CharacterIterator;

public class Number extends AFD{
    
    @Override
    public Token evaluate(CharacterIterator code) {

        if (Character.isDigit(code.current())){
            String number = readNumber(code);

            if (code.current() == '.'){
                number += '.';
                code.next();
                number+=readNumber(code);

                if (isTokenSeparator(code)){
                    return new Token("tempero", number); // fracionario
                }
            }
              if (isTokenSeparator(code)) {
                return new Token("ingrediente", number); // inteiro
            }
        }

        return null;
    }

    private String readNumber(CharacterIterator code){
        StringBuilder number = new StringBuilder();
        while (Character.isDigit(code.current())){
            number.append(code.current());
            code.next();
        }
        return number.toString();
    }

}