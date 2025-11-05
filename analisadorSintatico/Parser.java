package analisadorSintatico;

import analisadorLexico.Token;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class Parser {

    List<Token> tokens;
    Token token;
    private BufferedWriter writer;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Parser(List<Token> tokens, BufferedWriter writer) {
        this.tokens = tokens;
        this.writer = writer;
    }

    public Tree main() {
        Node main = new Node("main");
        token = getNextToken();
        Tree tree = new Tree(main);
        header();
        if (receita(main) && matchT("EOF")) {
            footer();
            return tree;
        } else {
            erro("main");
            return null;
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
    public boolean receita(Node node) {
        Node receita = node.addNode("receita");
        if (matchL("receita", receita) && codigo(receita) && matchL("pratopronto", receita)) {
            return true;
        }
        erro("receita");
        return false;
    }
    
    private boolean codigo(Node node) {
        Node codigo = node.addNode("codigo");
        if (token.lexema.equals("}") || token.lexema.equals("pratopronto")) return true;

        if (sentenca(codigo))
            return codigo(codigo);

        erro("codigo");
        return false;
    }

    // SENTENÇAS
    private boolean sentenca(Node node) {
        Node sentenca = node.addNode("sentenca");
        if (sirva(sentenca)) return true;
        if (ifelse(sentenca)) return true;
        if (prove(sentenca)) return true;
        if (bata(sentenca)) return true;
        if (ferva_cozinhe_enquanto(sentenca)) return true;
        if (cozinhe_enquanto(sentenca)) return true;
        if (declarar(sentenca)) return true;
        if (atribuir(sentenca)) return true;
        if (comentario(sentenca)) return true;
        if (parar(sentenca)) return true;
        return false;
    }

    // print
    private boolean sirva(Node node) {
        Node sirva = node.addNode("sirva");
        if (matchL("sirva", "println!", sirva))
        {
            if (matchL("(", "(\"{}\",")) 
            {
                if ( string(sirva) && matchL(")", ")", sirva) &&
                 matchL(";", ";\n", sirva)) return true;
                if ( matchL("{", sirva) && id(sirva) && matchL("}", sirva) && 
                matchL(")", ")", sirva) && matchL(";", ";\n", sirva)) return true;
                erro("sirva");
                return false;
            }
            erro("sirva");
            return false;
        }
        return false;
    }

    // input
    private boolean prove(Node node) {
        Node prove = node.addNode("prove");
        if (matchL("prove", prove))
        {
            if (matchL("(", prove))
            {
                if (matchT("TIPO_PROVE", prove)) 
                {
                    traduz("let mut ");
                    String id = token.lexema;
                    if (id(prove))
                    {
                        traduz(" = String::new();\n");
                        traduz("io::stdin().read_line(&mut " + id + ").expect(\"Falha ao ler a entrada\");\n");
                        if (matchL(")", prove) && matchL(";", prove)) return true;
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

    private boolean comentario(Node node){
        Node comentarioNode  = node.addNode("comentario");
        String comentario = token.lexema;
        if (matchT("COMMENT", comentarioNode)){
            traduz("//" +  comentario + "\n");
            return true;
        } 
        return false;
    }

    private boolean parar(Node node){
        Node parar = node.addNode("´parar");
        if (matchL("parar", "break", parar) && matchL(";", parar)){
            return true;
        } 
        return false;
    }

    private boolean ifelse(Node node) {
        Node ifelse = node.addNode("ifelse");
        if (matchL("deguste", "if ", ifelse))
        {
            if (condicao(ifelse) && matchL("{", " {\n", ifelse) && codigo(ifelse) && 
                matchL("}", "}", ifelse))
            {
                if (matchL("tempere", "else", ifelse))
                {
                    if (!(matchL("{", " {", ifelse) && codigo(ifelse) && 
                    matchL("}", "}", ifelse))){
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
    private boolean bata(Node node) {
        Node bata = node.addNode("bata");
        if (matchL("bata", "for ", bata))
        {
            if (condicaoBata(bata) && matchL("{", " {\n", bata) && codigo(bata) &&
             matchL("}", "}\n", bata)) return true;
            erro("bata");
            return false;
        }
        return false;
    }

    // while
    private boolean cozinhe_enquanto(Node node) {
        Node cozinhe_enq = node.addNode("cozinhe_enquanto");
        if (matchL("cozinhe_enquanto", "while ", cozinhe_enq))
        {
            if (condicao(cozinhe_enq) && matchL("{", "{\n", cozinhe_enq)){
                if (codigo(cozinhe_enq) && matchL("}", "}\n", cozinhe_enq)) return true;
            } 
            erro("cozinhe_enquanto");
            return false;
        }
        return false;    
    }

    // do while
    private boolean ferva_cozinhe_enquanto(Node node) {
        Node ferva = node.addNode("ferva");
        if (matchL("ferva", "loop", ferva))
        {
            if (matchL("{", "{",ferva) && codigo(ferva) && matchL("}",ferva)) {
                if (matchL("cozinhe_enquanto", "if ", ferva) && condicao(ferva)){
                    traduz("{\nbreak\n}\n}");
                    return true;
                }
            }
            erro("ferva cozinhe_enquanto");
            return false;
        }
        return false;
    }

    // tipo id; ou tipo id = exp ;
    private boolean declarar(Node node) {
        Node declarar = node.addNode("declaracao");
        if (tipos(declarar) && id(declarar)){
            if (matchL(";", ";\n", declarar)) return true;
            
            if (matchL("=", "=", declarar))
            {
                if (exp(declarar) && matchL(";", ";\n", declarar)) return true;
                erro("declarar");
                return false;
            }
            erro("declarar");
            return false;
        }
        return false; 
    }

    // tipo id = exp;
    private boolean atribuir(Node node) {
        Node atribuir = node.addNode("atribuicao");
        if (id(atribuir)) 
        {
            if (matchL("=", "=", atribuir) && exp(atribuir) &&
             matchL(";", ";\n", atribuir)) return true;
            erro("atribuir");
            return false;
        }
        return false;
    }

    // FUNÇÕES AUXILIARES
    private boolean exp(Node node) {
        Node exp = node.addNode("exp");
        if (!termo(exp)) return false;

        while (operadorArit(exp)) {
            if (!termo(exp)) return false;
        }
        return true;
    }

    private boolean termo(Node node) {
        Node termo = node.addNode("termo");
        if (num(termo) || id(termo) || string(termo)) {
            return true;
        }
        if (matchL("(", termo)) {
            if (exp(termo) && matchL(")", termo)) return true;
            return false; 
        }
        return false;
    }

    private boolean condicao(Node node) {
        Node condicao = node.addNode("condicao");
        if (matchL("(", condicao))
        {
            if (id(condicao) && operador(condicao) && num(condicao) && matchL(")", condicao)) return true;
            if (num(condicao) && operador(condicao) && id(condicao) && matchL(")", condicao)) return true;
            if (id(condicao) && matchL(")", condicao)) return true;
            erro("condicao");
            return false;
        }
        return false;
    }

    private boolean condicaoBata(Node node) {
        Node condicao = node.addNode("condicao_bata");
        if (matchL("(", condicao))
        {
            if (id(condicao) && matchL("=", " in ", condicao)){
                if (num(condicao) && matchL(";", condicao))
                {
                    if (idBata(condicao) && operadorBata(condicao) && num(condicao) && matchL(")", condicao)) return true;
                    if (num(condicao) && operadorBata(condicao) && idBata(condicao) && matchL(")", condicao)) return true;
                    erro("condicao");
                    return false; 
                }
            }   
            erro("condicao");
            return false;
        }
        return false;
    }

    private boolean operador(Node node) {
        Node operador = node.addNode("operador_comp");
        return matchT("OP_COMPARACAO", token.lexema, operador);
    }

    private boolean operadorBata(Node node) {
        Node operador = node.addNode("operador_comp");
        String op = token.lexema; 
        if (matchT("OP_COMPARACAO", operador)){
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

    private boolean operadorArit(Node node) {
        Node operadorArit = node.addNode("operador_arit");
        return matchT("OP_ARITMETICO", token.lexema, operadorArit);
    }

    private boolean tipos(Node node){
        Node tipo = node.addNode("tipo_msg");
        return matchT("RESERVADA_INGREDIENTE", "let mut ", tipo) || 
        matchT("RESERVADA_TEMPERO", "let mut ", tipo) ||
         matchT("RESERVADA_RECEITINHA", "let mut ", tipo);
    }

    private boolean id(Node node) {
        Node id = node.addNode("id");
        if (matchT("IDENTIFICADOR", token.lexema, id)) return true;

        return false;
    }

    private boolean idBata(Node node) {
        Node id = node.addNode("id");
        if (matchT("IDENTIFICADOR", id)) return true;

        return false;
    }

    private boolean string(Node node) {
        Node string = node.addNode("string");
        return matchT("RECEITINHA", token.lexema, string);
    }

    private boolean num(Node node) {
        Node num = node.addNode("num");
        return (matchT("TEMPERO", token.lexema, num) || matchT("INGREDIENTE", token.lexema, num));
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

    private boolean matchT(String palavra, Node node){
        if (token.tipo.equals(palavra)){
            node.addNode(token.lexema);
            token = getNextToken();
            return true;
        }
        return false;
    }

    private boolean matchT(String palavra, String newcode, Node node){
        if (token.tipo.equals(palavra)){
        traduz(newcode);
            node.addNode(token.lexema);
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

    private boolean matchL(String palavra, Node node){
        if (token.lexema.equals(palavra)){
            node.addNode(token.lexema);
            token = getNextToken();
            return true;
        }
        return false;
    }

    private boolean matchL(String palavra, String newcode, Node node){
        if (token.lexema.equals(palavra)){
            traduz(newcode);
            node.addNode(token.lexema);
            token = getNextToken();
            return true;
        }
        return false;
    }

    public void traduz(String code){
        try {
            if (writer != null)
                writer.write(code);
            else
                System.out.print(code);
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    public void header(){
        if (writer != null){
            try{
                writer.write("use std::io;\n");
                writer.write("fn main() {\n");
            }catch (IOException e) {
                    System.err.println("Erro: " + e.getMessage());
            }
        }
        else{
            System.out.println("use std::io;");
            System.out.println("fn main() {\\n");
        }
        
    }
  
    public void footer(){
        System.out.println("}");
        if (writer != null) {
            try {
                writer.write("}");
                writer.close();
            } catch (IOException e) {
                System.err.println("Erro:" + e.getMessage());
            }
        }
    }
}
