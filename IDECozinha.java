import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.io.*;

import analisadorLexico.Lexer;
import analisadorLexico.Token;
import analisadorSintatico.Parser;
import analisadorSintatico.Tree;

public class IDECozinha extends JFrame {
    private JTextArea codigoArea;
    private JTextArea outputArea;
    private JButton traduzirButton;
    private JButton lexerButton;
    private JButton arvoreButton;

    public IDECozinha() {
        super("IDE da Linguagem Cozinha");

        // area de codigo
        codigoArea = new JTextArea(19, 60);
        codigoArea.setFont(new Font("Arial", Font.PLAIN, 18));
        JScrollPane scrollCodigo = new JScrollPane(codigoArea);

        // area de saida
        outputArea = new JTextArea(15, 60);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 18));
        outputArea.setEditable(false);
        JScrollPane scrollOutput = new JScrollPane(outputArea);

        // botao de traduzir
        traduzirButton = new JButton("Traduzir");
        traduzirButton.addActionListener(this::traduzirCodigo);

        // botao de printar a lista de tokens
        lexerButton = new JButton("Lista de Tokens");
        lexerButton.addActionListener(this::listarTokens);

        // botao de printar a arvore
        arvoreButton = new JButton("Arvore de Derivacao");
        arvoreButton.addActionListener(this::printarArvore);


        // painel pros botoes
        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotao.add(traduzirButton);
        painelBotao.add(lexerButton);
        painelBotao.add(arvoreButton);


        // layout principal
        JSplitPane divisaoPag = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollCodigo, scrollOutput);
        divisaoPag.setDividerLocation(300);

        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout(5, 5));
        painel.add(painelBotao, BorderLayout.NORTH); 
        painel.add(divisaoPag, BorderLayout.CENTER);

        add(painel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void listarTokens(ActionEvent e){
        String codigo = codigoArea.getText();
        try{
            Lexer lexer = new Lexer(codigo);
            List<Token> tokens = lexer.getTokens();

            StringBuilder resultado = new StringBuilder();
            resultado.append("Lista de Tokens:\n");
            for (Token t : tokens) {
                resultado.append(t).append("\n");
            }
            outputArea.setText(resultado.toString());

        } catch (Exception ex) {
            outputArea.setText(" Erro na compilação:\n" + ex.getMessage());
        }
    }

    private void traduzirCodigo(ActionEvent e) {
        try {
            String codigo = codigoArea.getText();

            // gera o lexer
            Lexer lexer = new Lexer(codigo);
            List<Token> tokens = lexer.getTokens();

            // cria o arquivo de saida
            File arquivoSaida = new File("main.rs");
            BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoSaida));

            // gera parser
            Parser parser = new Parser(tokens, writer);
            Tree sucesso = parser.main();

            writer.close();

            if (sucesso != null) {
                outputArea.setText("Traducao gerada com sucesso em: " + arquivoSaida.getAbsolutePath());
            } else {
                outputArea.setText("Erros encontrados:\n\n" + parser.getErros());
            }

        } catch (Exception ex) {
            outputArea.setText("Erro na traducao:\n" + ex.getMessage());
        }
    }

    private void printarArvore(ActionEvent e){
        String codigo = codigoArea.getText();
        try{
            Lexer lexer = new Lexer(codigo);
            List<Token> tokens = lexer.getTokens();

            Parser parser = new Parser(tokens);
            Tree tree = parser.main();

            if (tree != null) {
                outputArea.setText(tree.getRoot().getTree());
            } else {
                outputArea.setText("Erro ao gerar árvore.");
            }

        } catch (Exception ex) {
            outputArea.setText("Erro: " + ex.getMessage());
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new IDECozinha());
    }
}
