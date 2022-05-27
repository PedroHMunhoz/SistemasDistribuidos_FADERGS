// Classe que encapsula os métodos e objetos para permitir o gerenciamento de cores no programa. No programa,
// ele está sendo usado para definir cores de fundo dos paineis e bordas de alguns elementos.
import java.awt.Color;

// Expõe os métodos para capturar eventos de ação disparados por elementos. No programa,
// é usado para sabermos qual ação foi disparada pelo programa e direcionar a ação conforme ela.
import java.awt.event.ActionEvent;

// Expõe metodos para ouvir ouvir os eventos disparados por algum elemento. No programa, é implementado
// pela classe para permitir o uso do método para adicionar quais os eventos queremos ouvir ao serem disparados.
import java.awt.event.ActionListener;

// Expõe métodos e objetos para tratamento de eventos disparados por cliques em teclas. No programa, ele é usado
// para checar se foi pressionada a tecla Enter para enviar a mensagem do cliente.
import java.awt.event.KeyEvent;

// Expõe os métodos da interface para registrar os eventos de pressão de tecla que queremos ouvir. No programa é implementado
// pela classe para permitir o uso do método para adicionar quais os eventos queremos ouvir quando uma tecla for pressionada.
import java.awt.event.KeyListener;

// Faz a leitura dos dados oriundos de um local e faz um buffer de caracteres pra ser usado posteriormente.
// No programa, ele faz a leitura dos dados enviados pelo servidor para exibir no cliente.
import java.io.BufferedReader;

// Faz a escrita de dados em um local e mantém armazenado em um buffer para facilitar o uso posterior.
// No programa, ele faz a escrita dos dados que deverão ser enviados pro servidor.
import java.io.BufferedWriter;

// Habilita o uso e captura das exceções de IO (IOException) no tratamento de exceções do programa. No programa,
// está sendo usado para a captura de erros de IO (I/O = In/Out = Entrada/Saída), ou seja, quaisquer erros na leitura/escrita
// de dados.
import java.io.IOException;

// É uma superclasse do Java que pode ser usada para representar/instanciar objetos que trabalhem com a entrada de dados através
// de trens de bytes. Essas classes servem para receber os dados de algum local. No programa, é ele quem recebe os dados vindos
// de alguma conexão e repassa para ser armazenado num buffer pra uso posterior.
import java.io.InputStream;

// Classe que permite instanciar objetos do tipo InputStreamReader, que fazem a transcrição das streams de bytes para streams
// de caracteres. No programa, ele cria as variáveis para receber a entrada de dados que vem em array de bytes pelo socket
// e salva no buffer de leitura.
import java.io.InputStreamReader;

// É uma superclasse do Java que pode ser usada para representar/instanciar objetos que trabalhem com a escrita de dados através
// de trens de bytes. Essas classes servem para então enviar os dados para algum local. No programa, é ele quem cria os objetos para 
// escrita dos dados e será passado para o objeto OutputStreamWriter para então ser escrito na stream de bytes.
import java.io.OutputStream;

// Classe que permite instanciar objetos do tipo OutputStreamWriter, que fazem a transcrição das streams de caracteres para streams
// de bytes, ou seja, o contrário do InputStreamReader. No programa, ele cria as variáveis que irão receber a saída necessária,
// transcrever o texto para array de bytes e fazer o armazenamento no buffer de escrita que será enviado.
import java.io.OutputStreamWriter;

// Permite o uso de objetos para escrever dados em uma stream de caracteres, sejam eles quais forem. No programa,
// está sendo utilizado para instaciar o objeto que será passado como o parâmetro necessário para o BufferedWriter
import java.io.Writer;

// Permite uso do objeto para criar clientes socket, que se conectam a outro socket (podendo ser outro cliente ou um servidor)
// para o envio e/ou recebimento de mensagens. No programa, ele é responsável por instanciar o socket que receberá os dados de 
// outro socket.
import java.net.Socket;

// Carrega todos as classes e objetos do javax.swing, que disponibilizam funcionalidades
// para interface gráfica. No cliente, ele é usado para instanciar os objetos de botões,
// labels, campos de texto.
import javax.swing.*;

public class Cliente extends JFrame implements ActionListener, KeyListener {

    // Essa varíavel não está em uso no programa
    private static final long serialVersionUID = 1L;

    // Variável para instanciar os campos de área de texto da interface
    private JTextArea texto;

    // Variáveç para instanciar os campos de texto da Mensagem interface
    private JTextField txtMsg;

    // Variável para instanciar o botão de Enviar mensagens da interface
    private JButton btnSend;

    // Variável para instanciar o botão Sair na interface
    private JButton btnSair;

    // Variável para instanciar o texto "Histórico" que aparecerá na interface
    private JLabel lblHistorico;

    // Variável para instanciar o texto "Mensagem" que aparecerá na interface
    private JLabel lblMsg;

    // Variável para instanciar um painel que vai conter os demais elementos gráficos
    private JPanel pnlContent;

    // Variável para instanciar o objeto de socket utilizado pelo cliente para conectar no servidor
    private Socket socket;

    // Variável para instanciar o objeto do fluxo de escrita de dados
    private OutputStream ou;

    // Variável para instanciar o objeto Writer
    private Writer ouw;

    // Variável para instanciar o objeto BufferedWriter, para escrever os dados a serem enviados
    private BufferedWriter bfw;

    // Variável para instanciar os campos de texto pra digitar o IP do servidor
    private JTextField txtIP;

    // Variável para instanciar os campos de texto pra digitar a porta do servidor
    private JTextField txtPorta;

    // Variável para instanciar os campos de texto pra digitar o nome do usuário
    private JTextField txtNome;

    /**
     * Método construtor da classe Cliente, que não recebe parâmetros e
     * instancia as variáveis necessárias para desenhar os elementos de tela,
     * bem como configurar os eventos que serão ouvidos e as ações a serem
     * disparadas pelos elementos. Nele também definidas algumas configurações
     * padrão da tela que será montada.
     */
    public Cliente() throws IOException {
        // Cria uma label de texto com o texto "Verificar"
        JLabel lblMessage = new JLabel("Verificar!");

        // Instancia um campo de texto com o valor padrão 127.0.0.1 já preenchido
        txtIP = new JTextField("127.0.0.1");

        // Instancia um campo de texto com o valor padrão 12345 já preenchido
        txtPorta = new JTextField("12345");

        // Instancia um campo de texto com o valor padrão Cliente já preenchido
        txtNome = new JTextField("Cliente");

        // Cria um objeto genérico com os objetos acima instanciados
        Object[] texts = {lblMessage, txtIP, txtPorta, txtNome};

        // Mostra a caixa de díálogo e mostra pro usuário. O primeiro parâmetro é passado
        // como null pois o mesmo não possui um pai definido, somente os objetos criados
        // anteriormente no método.
        JOptionPane.showMessageDialog(null, texts);
        
        // Cria um objeto do tipo Painel, que irá servir de elemento pai para os demais
        pnlContent = new JPanel();
        
        // Cria uma área de texto com tamanho de 10 linhas de altura por 20 colunas de largura
        texto = new JTextArea(10, 20);
        
        // Marca a área de texto criada como não editável, ficando somente leitura
        texto.setEditable(false);
        
        // Configura a cor de fundo da caixa de texto
        texto.setBackground(new Color(240, 240, 240));
        
        // Cria o campo de texto para a mensagem, com largura de 20 colunas
        txtMsg = new JTextField(20);
        
        // Cria uma label com o valor "Histórico" dentro
        lblHistorico = new JLabel("Histórico");
        
        // Cria uma label com o valor "Mensagem" dentro
        lblMsg = new JLabel("Mensagem");
        
        // Cria um botão com o valor "Enviar" dentro
        btnSend = new JButton("Enviar");
        
        // Cria uma tooltip, que é uma mensagem de auxílio que aparece ao posicionar
        // o mouse em cima do botão, com o texto "Enviar Mensagem"
        btnSend.setToolTipText("Enviar Mensagem");
        
        // Cria um botão com o valor "Sair" dentro
        btnSair = new JButton("Sair");
        
        // Cria uma tooltip, que é uma mensagem de auxílio que aparece ao posicionar
        // o mouse em cima do botão, com o texto "Sair do Chat"
        btnSair.setToolTipText("Sair do Chat");
        
        // Adiciona uma ação para "escutar" as ações executadas pelo botão Enviar
        btnSend.addActionListener(this);
        
        // Adiciona uma ação para "escutar" as ações executadas pelo botão Sair
        btnSair.addActionListener(this);
        
        // Adiciona uma ação para "escutar" as ações de teclado do botão Enviar
        btnSend.addKeyListener(this);
        
        // Adiciona uma ação para "escutar" as ações de teclado quando o campo de Mensagem estiver em foco
        txtMsg.addKeyListener(this);
        
        // Cria um elemento JScrollPane, que é uma janela com barra de rolagem, caso os elementos precisem rolar
        JScrollPane scroll = new JScrollPane(texto);
        
        // Configura a área de texto para quebrar o texto caso o mesmo ultrapasse a largura
        texto.setLineWrap(true);
        
        // Adiciona a label Histórico dentro do painel de conteúdo criado anteriormente
        pnlContent.add(lblHistorico);
        
        // Adiciona o painel com scroll dentro do painel de conteúdo criado anteriormente
        pnlContent.add(scroll);
        
        // Adiciona a label Mensagem dentro do painel de conteúdo criado anteriormente
        pnlContent.add(lblMsg);
        
        // Adiciona o campo de texto da mensagem dentro do painel de conteúdo criado anteriormente
        pnlContent.add(txtMsg);
        
        // Adiciona botão Sair dentro do painel de conteúdo criado anteriormente
        pnlContent.add(btnSair);
        
        // Adiciona botão Enviar dentro do painel de conteúdo criado anteriormente
        pnlContent.add(btnSend);
        
        // Configura a cor de fundo do painel de conteúdo para cinza claro
        pnlContent.setBackground(Color.LIGHT_GRAY);
        
        // Configura a borda da área de texto para azul
        texto.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.BLUE));
        
        // Configura a borda do campo de mensagem para azul
        txtMsg.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.BLUE));
        
        // Configura o título da janela para o nome do usuário digitado pelo cliente
        setTitle(txtNome.getText());
        
        // Configura o painel de conteúdo como sendo o pnlContent criado anteriormente,
        // juntamente com todos os seus elementos adicionados
        setContentPane(pnlContent);
        
        // Remove qualquer relacionamento da posição da janela para ser exibida 
        setLocationRelativeTo(null);
        
        // Configura o painel como não redimensionável, para ele ter um tamanho fixo
        setResizable(false);
        
        // Configura o tamanho da janela para 250px de largura por 300px de largura
        setSize(250, 300);
        
        // Configura o painel como visível
        setVisible(true);
        
        // Configura a ação padrão a ser executada quando fechar
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Faz a conexão do socket do cliente com o servidor, usando os parâmetros
     * preenchidos pelo cliente. Além disso, instancia e inicializa os objetos
     * para receber as mensagens do servidor e demais clientes. Em caso de
     * erros, irá disparar uma exceção do tipo IOException.
     */
    public void conectar() throws IOException {

        // Instancia um novo socket com os parâmetros informados pelo cliente
        socket = new Socket(txtIP.getText(), Integer.parseInt(txtPorta.getText()));

        // Instancia o objeto para pegar a saída do fluxo de escrita do sockect
        ou = socket.getOutputStream();

        // Instancia o objeto pra fazer a escrita de dados no fluxo
        ouw = new OutputStreamWriter(ou);

        // Instancia o objeto pra fazer a leitura dos dados para  obuffer de leitura
        bfw = new BufferedWriter(ouw);

        // Escreve (envia) o nome do cliente para o servidor
        bfw.write(txtNome.getText() + "\r\n");

        // Limpa o fluxo de dados para liberar possíveis lixos de memória
        bfw.flush();
    }

    /**
     * *
     * Método que faz o envio das mensagens para o servidor, que então irá
     * disparar a todos os clientes conectados, menos o remetente. Ele faz uma
     * validação para que caso o cliente digite a palavra "Sair", desconecta do
     * servidor. Caso digite qualquer outro texto, ele envia a mensagem. Em caso
     * da algum erro, irá levantar uma exceção do tipo IOException, que deverá
     * ser tratada por quem chamou o método.
     */
    public void enviarMensagem(String msg) throws IOException {

        // Checa se a mensagem é "Sair", se for, desconecta
        if (msg.equals("Sair")) {
            bfw.write("Desconectado \r\n");
            texto.append("Desconectado \r\n");
        } else { // Se não for, envia a mensagem
            bfw.write(msg + "\r\n");
            texto.append(txtNome.getText() + " diz -> " + txtMsg.getText() + "\r\n");
        }

        // Limpa o fluxo de dados para liberar possíveis lixos de memória
        bfw.flush();

        // Limpa o campo do texto, para digitar uma nova mensagem
        txtMsg.setText("");
    }

    /**
     * Este método faz o tratamento das mensagens recebidas do servidor pelo
     * cliente. Em caso de algum erro, ele dispara uma exceção IOException, que
     * deverá ser tratada pelo método que chamou este, para verificar o
     * problema.
     */
    public void escutar() throws IOException {

        // Pega o valor do fluxo de leitura
        InputStream in = socket.getInputStream();

        // Instancia um objeto de leitura do fluxo
        InputStreamReader inr = new InputStreamReader(in);

        // Instancia um objeto para o buffer de leitura recebido
        BufferedReader bfr = new BufferedReader(inr);

        // Instancia uma string local da mensagem, com valor em branco
        String msg = "";

        // Faz o loop enquanto a mensagem recebida for diferente de "Sair"
        while (!"Sair".equalsIgnoreCase(msg)) {
            // Se o buffer estiver pronto
            if (bfr.ready()) {

                // Lê a mensagem digitada no buffer
                msg = bfr.readLine();

                // Se for sair, escreve a mensagem que o servidor caiu na área de texto
                if (msg.equals("Sair")) {
                    texto.append("Servidor caiu! \r\n");
                } else { // Caso contrário, coloca o texto digitado
                    texto.append(msg + "\r\n");
                }
            }
        }
    }

    /**
     * Método que trata a ação disparada quando o usuário clicar em Sair. Em
     * caso de algum erro, ele dispara uma exceção IOException, que deverá ser
     * tratada pelo método que chamou este, para verificar o problema.
     */
    public void sair() throws IOException {

        // Chama o método enviarMensagem enviando "Sair", fazendo assim o reaproveitamento da regra
        // que já está implementada lá
        enviarMensagem("Sair");

        // Fecha o buffer de escrita
        bfw.close();

        // Fecha o buffer de escrita da saída
        ouw.close();

        // Fecha o objeto do fluxo de saída
        ou.close();

        //Fecha o socket
        socket.close();
    }

    // Override do método que vai ouvir a ação disparada por algum elemento,
    // vai checar qual foi a ação e chamar a rotina necessária
    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            // Se o comando for do botão Enviar, chama o método enviarMensagem com o texto digitado
            if (e.getActionCommand().equals(btnSend.getActionCommand())) {
                enviarMensagem(txtMsg.getText());
            } else if (e.getActionCommand().equals(btnSair.getActionCommand())) {
                // Se o comando for do botão Sair, chama o método Sair
                sair();
            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    /**
     * Override do método que vai tratar os eventos de pressionar teclas no
     * teclado e chamar a rotina conforme as regras.
     */
    @Override
    public void keyPressed(KeyEvent e) {

        // Se a tecla pressionada for Enter
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                // Envia a mensagem que estiver escrita no campo
                enviarMensagem(txtMsg.getText());
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
    }

    /**
     * O método main é o método principal de uma classe, que sempre será
     * executado em primeiro lugar. Normalmente, é onde se inicializam algumas
     * coisas, é feita a criação de objetos de interface gráfica, validação de
     * algumas configurações inicias, etc. Os métodos main são estáticos,
     * portantos podem ser chamados sem a necessidade de instanciar a classe. No
     * caso aqui do Cliente, esse método instancia um novo objeto do Cliente
     * (que vai montar e exibir a tela), e chama os métodos conectar() e
     * escutar(), que irão abrir os sockets e ficar ouvindo as mensagens
     * enviadas do servidor, bem como enviar as mensagens do cliente
     * instanciado.
     */
    public static void main(String[] args) throws IOException {

        Cliente app = new Cliente();
        app.conectar();
        app.escutar();
    }
}
