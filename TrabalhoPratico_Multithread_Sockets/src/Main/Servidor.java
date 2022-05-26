// Faz a leitura dos dados oriundos de um local e faz um buffer de caracteres pra ser usado posteriormente.
// No programa, é ele quem lê e armazena a mensagem enviada pelos clientes para enviar para os demais.
import java.io.BufferedReader;

// Faz a escrita de dados em um local e mantém armazenado em um buffer para facilitar o uso posterior.
// No programa, ele recebe o objeto do tipo Writer e é adicionado no ArrayList dos clientes conectados e
// escreve os dados na saída padrão, enviando aos demais.
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
// transcrever o texto para array de bytes e fazer o armazenamento no buffer de escrita que será enviado aos clientes.
import java.io.OutputStreamWriter;

// Permite o uso de objetos para escrever dados em uma stream de caracteres, sejam eles quais forem. No programa,
// está sendo utilizado para instaciar o objeto que será passado como o parâmetro necessário para o BufferedWriter
import java.io.Writer;

// Permite o uso do objeto para criar um servidor de sockets, para ficar recebendo e respondendo a requisições.
// No programa, ele é responsável por inicializar o nosso servidor, para receber as mensagens de um cliente e 
// encaminhar para todos os demais que estiverem conectados.
import java.net.ServerSocket;

// Permite uso do objeto para criar clientes socket, que se conectam a outro socket (podendo ser outro cliente ou um servidor)
// para o envio e/ou recebimento de mensagens. No programa, ele é responsável por instanciar o socket que receberá os dados de 
// outro socket e responderá para os demais clientes ou para quem solicitar.
import java.net.Socket;

// Permite o uso dos objetos do tipo ArrayList, que são utilizados para montar lista de objetos quaisquer. No programa
// esta sendo usado para a declaração e uso de uma lista de BufferedWriter que contém a lista dos clientes conectados ao servidor.
import java.util.ArrayList;

// Habilita o uso do componente JLabel, que serve para colocar labels (textos) em interfaces gráficas.
// No programa, é usado para montar o texto do campo "Porta do Servidor"
import javax.swing.JLabel;

// Habilita o uso do componente JOptionPane, que serve para criar caixas de díalogo do estilo de alerta na interface gráfica
// ou para solicitar alguma entrada de dados pelo usuário. No programa, é usado para mostrar a tela de configuração da porta do servidor
// e também o alerta de qual porta o servidor está ativo.
import javax.swing.JOptionPane;

// Habilita o uso do componente JTextField, que serve para criar campos de texto para que o usuário possa digitar alguma informação para o programa.
// É usado para mostrar o campo "Porta" do servidor na interface
import javax.swing.JTextField;

// Definição da classe Servidor, que extende por herança a classe Thread, permitindo acesso aos seus métodos e atributos
public class Servidor extends Thread {

    // Variável estática que irá conter a lista de todos os clientes conectados ao servidor
    private static ArrayList<BufferedWriter> clientes;

    // Instancia um novo socket para o servidor, que ficará ouvindo e encaminhando as requisições
    // recebidas para seus respectivos clientes
    private static ServerSocket server;

    // Variável para armazenar o nome do cliente e escrever ao lado de cada mensagem enviada
    private String nome;

    // Variável para instanciar o objeto de socket utilizado pelo servidor
    private Socket con;

    // Variável para criar o objeto que irá receber os dados vindos dos sockets
    private InputStream in;

    // Variável pra instanciar o objeto que vai ler os dados recebidos pelo InputStream
    // e passar como parâmetro pro BufferedReader pra bufferizar os dados
    private InputStreamReader inr;

    // Variável que irá instanciar o BufferedReader que é usada para toda a leitura do
    // que for armazenado nos buffers que chegam ao servidor
    private BufferedReader bfr;

    /* Método construtor da classe Servidor, que recebe como parâmetro um objeto
    do tipo Socket e instancia as variáveis in, inr e bfr, bem como seta o valor
    recebido como parâmetro na variável local "con"
     */
    public Servidor(Socket con) {

        // Atribui o objeto de socket recebido pelo construtor à varíavel local de socket da classe 
        this.con = con;

        // Faz a inicialização das variáveis com os objetos para leitura dos dados oriundos dos sockets
        try {
            in = con.getInputStream();
            inr = new InputStreamReader(in);
            bfr = new BufferedReader(inr);
        } catch (IOException e) { // Captura as exceções de Leitura/Escrita
            // Imprime todo o StackTrace da exceção, contendo dados para debug e verificação
            // do que causou o erro
            e.printStackTrace();
        }
    }

    // Esse método é executado ao iniciar uma thread no Java. Sempre que uma thread inicia e vai rodar,
    // todo o conteúdo deste método será executado.
    public void run() {

        try {
            // Variável local para guardar o valor recebido e escrever a mensagem 
            String msg;

            // Cria um objeto para o stream de saída do socket aberto
            OutputStream ou = this.con.getOutputStream();
            Writer ouw = new OutputStreamWriter(ou);
            BufferedWriter bfw = new BufferedWriter(ouw);

            // Adiciona o objeto BufferedWriter na lista que contém os clientes conectado
            clientes.add(bfw);

            // Lê do buffer a mensagem digitada
            nome = msg = bfr.readLine();

            // Vai executar enquanto não for digitado "Sair" na mensagem e o objeto
            // msg não for nulo
            while (!"Sair".equalsIgnoreCase(msg) && msg != null) {

                // Lê a mensagem digitada do buffer
                msg = bfr.readLine();

                // Chama o método que fará o envio da mensagem para todos os conectados
                sendToAll(bfw, msg);

                // Printa a mensagem no console
                System.out.println(msg);
            }
        } catch (Exception e) { // Captura todas as exceções que ocorreram na função
            // Imprime todo o StackTrace da exceção, contendo dados para debug e verificação
            // do que causou o erro
            e.printStackTrace();
        }
    }

    /**
     * *
     * Método usado para enviar mensagem para todos os clients
     *
     * @param bwSaida do tipo BufferedWriter
     * @param msg do tipo String
     * @throws IOException
     */
    public void sendToAll(BufferedWriter bwSaida, String msg) throws IOException {
        BufferedWriter bwS;

        for (BufferedWriter bw : clientes) {
            bwS = (BufferedWriter) bw;
            if (!(bwSaida == bwS)) {
                bw.write(nome + " -> " + msg + "\r\n");
                bw.flush();
            }
        }
    }

    /**
     * *
     * Método main
     *
     * @param args
     */
    public static void main(String[] args) {

        try {
            // Instancia uma nova label de texto para ser exibida na tela
            JLabel lblMessage = new JLabel("Porta do Servidor:");

            // Instancia uma nova label de texto para ser exibida na tela
            JTextField txtPorta = new JTextField("12345");
            Object[] texts = {lblMessage, txtPorta};
            JOptionPane.showMessageDialog(null, texts);
            server = new ServerSocket(Integer.parseInt(txtPorta.getText()));
            clientes = new ArrayList<BufferedWriter>();
            JOptionPane.showMessageDialog(null, "Servidor ativo na porta: "
                    + txtPorta.getText());

            while (true) {
                System.out.println("Aguardando conexão...");
                Socket con = server.accept();
                System.out.println("Cliente conectado...");
                Thread t = new Servidor(con);
                t.start();
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }// Fim do método main
} // Fim da classe
