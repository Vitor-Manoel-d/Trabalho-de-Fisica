package a.trabalhofisica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TrabalhoFisica implements ActionListener {
    JFrame janela;
    JButton botaoCalcular, botaoResetar;
    JTextField entradaDistancia, entradaAltura, saidaVelocidade;
    Double distancia, altura;
    String descricaoVelocidade;

    TrabalhoFisica() {
        janela = new JFrame("Calcular velocidade do barco");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(430, 400);
        janela.setLayout(null);

        JLabel textoDistancia = criarLabel("Distância até a ponte (m):", 40, 25, 160, 30);
        entradaDistancia = criarCampoTexto("", 220, 25, 70, 30, true, true);

        JLabel textoAltura = criarLabel("Altura da ponte (m):", 40, 60, 160, 30);
        entradaAltura = criarCampoTexto("", 220, 60, 70, 30, true, true);

        botaoCalcular = new JButton("Calcular");
        botaoCalcular.setBounds(310, 60, 100, 30);
        botaoCalcular.addActionListener(this);
        botaoCalcular.setFocusable(false);

        descricaoVelocidade = "Velocidade do barco (m/s): ";
        saidaVelocidade = criarCampoTexto(descricaoVelocidade, 40, 150, 350, 30, false, false);

        botaoResetar = new JButton("Resetar");
        botaoResetar.setBounds(180, 290, 80, 30);
        botaoResetar.addActionListener(this);
        botaoResetar.setFocusable(false);

        janela.add(textoDistancia);
        janela.add(textoAltura);
        janela.add(entradaDistancia);
        janela.add(entradaAltura);
        janela.add(saidaVelocidade);
        janela.add(botaoCalcular);
        janela.add(botaoResetar);
        janela.setVisible(true);
    }

    public static void main(String[] args) {
        new TrabalhoFisica();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoCalcular) {
            try {
                distancia = Double.parseDouble(entradaDistancia.getText());
                altura = Double.parseDouble(entradaAltura.getText());

                if (distancia <= 0) {
                    mostrarErro("A distância deve ser maior que zero.", "Erro de entrada");
                    return;
                }

                if (altura <= 0) {
                    mostrarErro("A altura da ponte deve ser maior que zero.", "Erro de entrada");
                    return;
                }

                
                double g = 9.8;
                double tempo = Math.sqrt((2 * altura) / g);
                double velocidade = distancia / tempo;

                String resultado = formatarSaida(descricaoVelocidade, velocidade);
                saidaVelocidade.setText(resultado);

            } catch (NumberFormatException ex) {
                mostrarErro("Por favor, insira valores numéricos válidos.", "Erro de entrada");
            }
        }

        if (e.getSource() == botaoResetar) {
            entradaDistancia.setText("");
            entradaAltura.setText("");
            saidaVelocidade.setText("");
        }
    }

    private JTextField criarCampoTexto(String texto, int x, int y, int largura, int altura, boolean editavel, boolean focavel) {
        JTextField campo = new JTextField(texto);
        campo.setBounds(x, y, largura, altura);
        campo.setEditable(editavel);
        campo.setFocusable(focavel);
        return campo;
    }

    private JLabel criarLabel(String texto, int x, int y, int largura, int altura) {
        JLabel label = new JLabel(texto);
        label.setBounds(x, y, largura, altura);
        return label;
    }

    private String formatarSaida(String descricao, Double valor) {
        return descricao + String.format("%.2f", valor);
    }

    private void mostrarErro(String mensagem, String titulo) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
    }
}
