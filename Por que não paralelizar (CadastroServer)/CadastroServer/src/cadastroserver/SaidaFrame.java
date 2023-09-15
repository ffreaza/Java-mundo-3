package cadastroserver;

/**
 *
 * @author Felipe
 */

// Importações necessárias
import javax.swing.JDialog;
import javax.swing.JTextArea;

public class SaidaFrame extends JDialog {

    private JTextArea texto;

    public SaidaFrame() {
        // Inicializa a janela de saída chamando métodos auxiliares
        inicializarComponentes();
        configurarJanela();
    }

    private void inicializarComponentes() {
        // Configura as dimensões da janela
        setBounds(100, 100, 400, 300);

        // Cria um componente JTextArea para exibir o texto de saída
        texto = new JTextArea();

        // Adiciona o componente JTextArea à janela
        add(texto);
    }

    private void configurarJanela() {
        // Torna a janela visível
        setVisible(true);

        // Define a modalidade da janela como não modal (não bloqueia outras janelas)
        setModal(false);

        // Define a ação padrão ao clicar no botão "X" para fechar a janela
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Define um título para a janela
        setTitle("Saída");
    }

    public JTextArea getTexto() {
        return texto;
    }

    public void setTexto(JTextArea texto) {
        this.texto = texto;
    }
}
