package analisadorSintatico;

import analisadorLexico.Token;
import java.util.List;

public class Parser {

    List<Token> tokens;
    Token token;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public void main() {
        token = getNextToken();
        if (receita() && matchT("EOF")) {
            System.out.println("Sintaticamente correto");
        } else {
            erro("main");
        }
    }

    public Token getNextToken() {
        if (tokens.size() > 0)
            return tokens.remove(0);
        return null;
    }

    private void erro(String regra) {
        System.out.println("-------------- Regra: " + regra);
        System.out.println("token invalido: " + token);
        System.out.println("------------------------------\n");
    }

    // FUNÇÃO MAIN
    public boolean receita() {
        if (matchL("receita") && codigo() && matchL("pratopronto")) {
            return true;
        }
        erro("receita");
        return false;
    }
    
    private boolean codigo() {
        if (token.lexema.equals("}") || token.lexema.equals("pratopronto")) return true;

        if (sentenca())
            return codigo();

        erro("codigo");
        return false;
    }

    // SENTENÇAS
    private boolean sentenca() {
        if (sirva()) return true;
        if (ifelse()) return true;
        if (prove()) return true;
        if (bata()) return true;
        if (ferva_bata()) return true;
        if (cozinhe_enquanto()) return true;
        if (declarar()) return true;
        if (atribuir()) return true;
        return false;
    }

    // print
    private boolean sirva() {
        if (matchL("sirva"))
        {
            if (matchL("(") && string() && matchL(")") && matchL(";")) return true;
            erro("sirva");
            return false;
        }
        return false;
    }

    // input
    private boolean prove() {
        if (matchL("prove"))
        {
            if (matchL("("))
            {
                if (matchL("\"%i\"") || matchL("\"%t\"") || matchL("\"%r\"")) {
                    if (id() && matchL(")") && matchL(";")) {
                        return true;
                    }
                    erro("prove");
                    return false;
                }
                erro("prove");
                return false; 
            }
            erro("prove");
            return false;
        }
        return false;
    }

    private boolean ifelse() {
        if (matchL("deguste"))
        {
            if (condicao() && matchL("{") && codigo() && matchL("}"))
            {
                if (matchL("tempere"))
                {
                    if (!(matchL("{") && codigo() && matchL("}"))){
                        erro("if else");
                        return false;
                    }
                    return true;
                }
                return true;
            }
            erro("ifelse");
            return false;
        }
        return false;
    }

    // for
    private boolean bata() {
        if (matchL("bata"))
        {
            if (condicao() && matchL("{") && codigo() && matchL("}")) return true;
            erro("bata");
            return false;
        }
        return false;
    }

    // while
    private boolean cozinhe_enquanto() {
        if (matchL("cozinhe_enquanto"))
        {
            if (condicao() && matchL("{") && codigo() && matchL("}")) return true;
            erro("cozinhe_enquanto");
            return false;
        }
        return false;    
    }

    // do while
    private boolean ferva_bata() {
        if (matchL("ferva"))
        {
            if (matchL("{") && codigo() && matchL("}") &&
               matchL("bata") && condicao()) return true;
            erro("ferva bata");
            return false;
        }
        return false;
    }

    // tipo id; ou tipo id = exp ;
    private boolean declarar() {
        if (tipos() && id()){
            if (matchL(";")) return true;
            
            if (matchL("="))
            {
                if (exp() && matchL(";")) return true;
                erro("declarar");
                return false;
            }
            erro("declarar");
            return false;
        }
        return false; 
    }

    // tipo id = exp;
    private boolean atribuir() {
        if (id()) 
        {
            if (matchL("=") && exp() && matchL(";")) return true;
            erro("atribuir");
            return false;
        }
        return false;
    }

    // FUNÇÕES AUXILIARES
    private boolean exp() {
        if (!termo()) return false;

        while (operadorArit()) {
            if (!termo()) return false;
        }
        return true;
    }

    private boolean termo() {
        if (num() || id()) {
            return true;
        }
        if (matchL("(")) {
            if (exp() && matchL(")")) return true;
            return false; 
        }
        return false;
    }


    private boolean condicao() {
        if (matchL("(") && id() && operador() && num() && matchL(")")) return true;
        erro("condicao");
        return false;
    }

    private boolean operador() {
        if (matchL(">") || matchL("<") || matchL("==") || 
        matchL(">=") || matchL("<=")) return true;
        
        erro("operador comparativo");
        return false;
    }

    private boolean operadorArit() {
        return matchT("op_aritmetico");
    }

    private boolean tipos(){
        return matchL("ingrediente") || matchL("tempero") || matchL("receitinha");
    }

    private boolean id() {
        if (matchT("id")) return true;

        return false;
    }

    private boolean string() {
        return matchT("receitinha");
    }

    private boolean num() {
        return (matchT("tempero") || matchT("ingrediente"));
    }

    // confere o tipo
    private boolean matchT(String tipo) {
        if (token.tipo.equals(tipo)) {
            token = getNextToken();
            return true;
        }
        return false;
    }

    // confere o lexema
    private boolean matchL(String lexema) {
        if (token.lexema.equals(lexema)) {
            token = getNextToken();
            return true;
        }
        return false;
    }
}
