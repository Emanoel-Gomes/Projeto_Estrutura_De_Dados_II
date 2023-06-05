import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class OpcoesPanel extends JPanel implements ActionListener {
    private JButton lerBtn;
    private JButton listarBtn;
    private JButton frequentesBtn;
    private JButton adicionarBtn;
    private JButton sairBtn;

    public OpcoesPanel() {
        // Configura o layout do painel
        setLayout(new GridLayout(5, 1));

        // Cria os botões
        lerBtn = new JButton("Ler");
        listarBtn = new JButton("Listar");
        frequentesBtn = new JButton("Frequentes");
        adicionarBtn = new JButton("Adicionar");
        sairBtn = new JButton("Sair");

        // Adiciona os botões ao painel
        add(lerBtn);
        add(listarBtn);
        add(frequentesBtn);
        add(adicionarBtn);
        add(sairBtn);

        // Configura os listeners de ação para cada botão
        lerBtn.addActionListener(this);
        listarBtn.addActionListener(this);
        frequentesBtn.addActionListener(this);
        adicionarBtn.addActionListener(this);
        sairBtn.addActionListener(this);
    }
    String fileName = FileChooser.chooseFile();
    // Listener de ação para os botões
    public void actionPerformed(ActionEvent e) {

        JButton button = (JButton) e.getSource();
        String buttonText = button.getText();
        
        switch (buttonText) {
            
            case "Ler":
                System.out.println("Você selecionou a opção Ler.");
                try {
                    ES_Arquivo.ler(FileChooser.getSelectedFilePath());
                    TextoPanel.imprimirTexto(ES_Arquivo.getTexto());
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                break;
            case "Listar":
                System.out.println("Você selecionou a opção Listar.");
                Paragraph.parag(FileChooser.getSelectedFilePath());
                break;
            case "Frequentes":
                System.out.println("Você selecionou a opção Frequentes.");
                
                System.out.println(FileChooser.getSelectedFilePath());
                
                break;
            case "Adicionar":
                System.out.println("Você selecionou a opção Adicionar.");
                break;
            case "Sair":
                System.out.println("Você selecionou a opção Sair.");
                System.exit(0);
                break;
        }
    }
}

