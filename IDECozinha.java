import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import analisadorLexico.Lexer;
import analisadorLexico.Token;
import analisadorSintatico.Parser;

public class IDECozinha extends JFrame {
    private JTextArea codigoArea;
    private JTextArea outputArea;
    private JButton compilarButton;
    private JButton lexerButton;

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

        // botao de compilar
        compilarButton = new JButton("Compilar");
        compilarButton.addActionListener(this::compilarCodigo);

        // botao de printar a lista de tokens
        lexerButton = new JButton("Lista de Tokens");
        lexerButton.addActionListener(this::listarTokens);

        // painel pros botoes
        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotao.add(compilarButton);
        painelBotao.add(lexerButton);

        // layout principal
        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout(5, 5));
        painel.add(painelBotao, BorderLayout.NORTH); 
        painel.add(scrollCodigo, BorderLayout.CENTER);
        painel.add(scrollOutput, BorderLayout.SOUTH);

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

    private void compilarCodigo(ActionEvent e) {
        try {
            String codigo = codigoArea.getText();

            // gera o lexer
            Lexer lexer = new Lexer(codigo);
            List<Token> tokens = lexer.getTokens();

            StringBuilder resultado = new StringBuilder();
            resultado.append("Lista de Tokens:\n");
            for (Token t : tokens) {
                resultado.append(t).append("\n");
            }

            resultado.append("\nAnalise Sintatica:\n");

            // parser
            Parser parser = new Parser(tokens);
            if (parser.main()){
                resultado.append("Sintaticamente Correto!");
            }else {
                resultado.append("Sintaticamente Incorreto");
            } 

            outputArea.setText(resultado.toString());

        } catch (Exception ex) {
            outputArea.setText(" Erro na compilação:\n" + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new IDECozinha());
    }
}
