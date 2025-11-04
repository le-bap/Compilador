package analisadorSintatico;

import analisadorLexico.Token;
import java.util.List;

public class Parser {

    List<Token> tokens;
    Token token;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public boolean main() {
        token = getNextToken();
        header();
        if (receita() && matchT("EOF")) {
            footer();
            return true;
        } else {
            erro("main");
            return false;
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
        if (ferva_cozinhe_enquanto()) return true;
        if (cozinhe_enquanto()) return true;
        if (declarar()) return true;
        if (atribuir()) return true;
        if (comentario()) return true;
        return false;
    }

    // print
    private boolean sirva() {
        if (matchL("sirva", "println!"))
        {
            if (matchL("(", "(\"{}\",")) 
            {
                if ( string() && matchL(")", ")") && matchL(";", ";\n")) return true;
                if ( matchL("{") && id() && matchL("}") && 
                matchL(")", ")") && matchL(";", ";\n")) return true;
                erro("sirva");
                return false;
            }
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
                if (matchT("TIPO_PROVE")) 
                {
                    traduz("let mut ");
                    String id = token.lexema;
                    if (id())
                    {
                        traduz(" = String::new();\n");
                        traduz("io::stdin().read_line(&mut " + id + ").expect(\"Falha ao ler a entrada\");\n");
                        if (matchL(")") && matchL(";")) return true;
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

    private boolean comentario(){
        traduz("// ");
        if (matchT("COMMENT", token.lexema)){
            traduz("\n");
            return true;
        } 
        return false;
    }

    private boolean ifelse() {
        if (matchL("deguste", "if "))
        {
            if (condicao() && matchL("{", " {\n") && codigo() && matchL("}", "}"))
            {
                if (matchL("tempere", "else"))
                {
                    if (!(matchL("{", " {") && codigo() && matchL("}", "}"))){
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
        if (matchL("bata", "for "))
        {
            if (condicaoBata() && matchL("{", " {\n") && codigo() && matchL("}", "}\n")) return true;
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
    private boolean ferva_cozinhe_enquanto() {
        if (matchL("ferva"))
        {
            if (matchL("{") && codigo() && matchL("}") &&
               matchL("cozinhe_enquanto") && condicao()) return true;
            erro("ferva cozinhe_enquanto");
            return false;
        }
        return false;
    }

    // tipo id; ou tipo id = exp ;
    private boolean declarar() {
        if (tipos() && id()){
            if (matchL(";", ";\n")) return true;
            
            if (matchL("=", "="))
            {
                if (exp() && matchL(";", ";\n")) return true;
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
            if (matchL("=", "=") && exp() && matchL(";", ";\n")) return true;
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
        if (matchL("("))
        {
            if (id() && operador() && num() && matchL(")")) return true;
            if (num() && operador() && id() && matchL(")")) return true;
            if (id() && matchL(")")) return true;
            erro("condicao");
            return false;
        }
        return false;
    }

    private boolean condicaoBata() {
        if (matchL("("))
        {
            if (id() && matchL("=", " in ")){
                if (num() && matchL(";"))
                {
                    if (idBata() && operadorBata() && num() && matchL(")")) return true;
                    if (num() && operadorBata() && idBata() && matchL(")")) return true;
                    erro("condicao");
                    return false; 
                }
                  
                  
            }   
            erro("condicao");
            return false;
        }
        return false;
    }

    private boolean operador() {
        return matchT("OP_COMPARACAO", token.lexema);
    }

    private boolean operadorBata() {
        String op = token.lexema; 
        if (matchT("OP_COMPARACAO")){
            if (op == "<" || op == ">"){
                traduz("..");
            }
            else {
                traduz("..=");
            }
            return true;
        }
        return false;
    }

    private boolean operadorArit() {
        return matchT("OP_ARITMETICO", token.lexema);
    }

    private boolean tipos(){
        return matchT("RESERVADA_INGREDIENTE", "let ") || matchT("RESERVADA_TEMPERO", "let ") ||
         matchT("RESERVADA_RECEITINHA", "let ");
    }

    private boolean id() {
        if (matchT("IDENTIFICADOR", token.lexema)) return true;

        return false;
    }

    private boolean idBata() {
        if (matchT("IDENTIFICADOR")) return true;

        return false;
    }

    private boolean string() {
        return matchT("RECEITINHA", token.lexema);
    }

    private boolean num() {
        return (matchT("TEMPERO", token.lexema) || matchT("INGREDIENTE", token.lexema));
    }

    // confere o tipo
    private boolean matchT(String tipo) {
        if (token.tipo.equals(tipo)) {
            token = getNextToken();
            return true;
        }
        return false;
    }

    private boolean matchT(String palavra, String newcode){
        if (token.tipo.equals(palavra)){
        traduz(newcode);
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

    private boolean matchL(String palavra, String newcode){
        if (token.lexema.equals(palavra)){
        traduz(newcode);
        token = getNextToken();
        return true;
        }
        return false;
    }

    public void traduz(String code){
        System.out.print(code);
    }

    public void header(){
    System.out.println("use std::io;");
    System.out.println("fn main() {");
    }
  
    public void footer(){
        System.out.println("}");
    }
}
