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

        return false;
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
        erro("prove");
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

    private boolean bata() {
        return matchL("bata") && condicao() && matchL("{") && codigo() && matchL("}");
    }

    private boolean ehTipoAtual() {
        return token.lexema.equals("ingrediente") ||
            token.lexema.equals("tempero") ||
            token.lexema.equals("receitinha");
    }


    private boolean atribuir() {
        if (id() && matchL("=") && exp() && matchL(";")) return true;
        else if (matchL("ingrediente") && id() && matchL("=") && exp() && matchL(";")) return true; 
        else if (matchL("tempero") && id() && matchL("=") && exp() && matchL(";")) return true;
        else if (matchL("receitinha") && id() && matchL("=") && exp() && matchL(";")) return true;
        return false;
    }

    private boolean declarar() {
        if (tipos()){
            if (id() && matchL(";")) return true;
        }
        return false; 
    }

    

    private boolean cozinhe_enquanto() {
        return matchL("cozinhe_enquanto") && condicao() && matchL("{") && codigo() && matchL("}");
    }

    

    private boolean ferva_bata() {
        return matchL("ferva") && condicao() && matchL("{") && codigo() && matchL("}") &&
               matchL("bata") && condicao();
    }

    // FUNÇÕES AUXILIARES
    private boolean epslon(){
        return true;
    }

    private boolean exp() {
        if (!termo()) return false;

        while (operadorArit()) {
            if (!termo()) return false;
        }

        return true;
    }

    private boolean termo() {
        if (num() || id() || string()) {
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
        return matchL("+") || matchL("-") || matchL("*") || matchL("/");
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
        if (matchT("num"))
        return true;

        erro("num");
        return false;
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
