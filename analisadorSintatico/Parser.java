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
        System.out.println("token inválido: " + token);
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
        if ((sentenca() && codigo()) || epslon()) {
            return true;
        }
        erro("codigo");
        return false;
    }

    // SENTENÇAS
    private boolean sentenca() {
        if (declarar_int() || declarar_float() || declarar_string() ||atribuir() ||
        sirva() || prove() || ifelse() || cozinhe_enquanto() || bata() || ferva_bata()) return true;
        return false;
    }

    private boolean declarar_int() {
        return matchL("ingrediente") && id() && matchL("=") && matchT("ingrediente") && matchL(";");
    }

    private boolean declarar_float() {
        return matchL("tempero") && id() && matchL("=") && matchT("tempero") && matchL(";");
    }

    private boolean declarar_string() {
        return matchL("receitinha") && id() && matchL("=") && string() && matchL(";");
    }

    private boolean atribuir() {
        return id() && matchL("=") && exp() && matchL(";");
    }

    private boolean sirva() {
        return matchL("sirva") && matchL("(") && string() && matchL(")") && matchL(";");
    }

    private boolean prove() {
        if (matchL("prove") && matchL("(")) {
            if (matchL("\"%i\"") || matchL("\"%t\"") || matchL("\"%r\"")) {
                if (id() && matchL(")") && matchL(";")) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean ifelse() {
        if ((matchL("deguste") && condicao() && matchL("{") && codigo() && matchL("}")) ||
            (matchL("deguste") && condicao() && matchL("{") && codigo() && matchL("}") &&
             matchL("tempere") && matchL("{") && codigo() && matchL("}"))) {
            return true;
        }
        return false;
    }

    private boolean cozinhe_enquanto() {
        return matchL("cozinhe_enquanto") && condicao() && matchL("{") && codigo() && matchL("}");
    }

    private boolean bata() {
        return matchL("bata") && condicao() && matchL("{") && codigo() && matchL("}");
    }

    private boolean ferva_bata() {
        return matchL("ferva") && condicao() && matchL("{") && codigo() && matchL("}") &&
               matchL("bata") && condicao();
    }

    // FUNÇÕES AUXILIARES
    private boolean epslon() {
        return true;
    }

    private boolean exp() {
        if (num() || id() || string() || (matchL("(") && exp() && matchL(")"))) {
            while (operadorArit() && (num() || id() || string() || (matchL("(") && exp() && matchL(")")))) {
                // repete operadores aritméticos
            }
            return true;
        }
        return false;
    }

    private boolean condicao() {
        return matchL("(") && id() && operador() && num() && matchL(")");
    }

    private boolean operador() {
        return matchL(">") || matchL("<") || matchL("==") || matchL(">=") || matchL("<=");
    }

    private boolean operadorArit() {
        return matchL("+") || matchL("-") || matchL("*") || matchL("/");
    }

    private boolean id() {
        return matchT("id");
    }

    private boolean string() {
        return matchT("receitinha");
    }

    private boolean num() {
        return matchT("tempero") || matchT("ingrediente");
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
