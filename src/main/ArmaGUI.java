package main;

import Entidades.Arma;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import java.awt.Container;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import tools.ConverteDatas;
import javax.swing.JCheckBox;
import DAOs.DAOArma;

/**
 *
 * @author Mateus Batichotti Silva | 19/04/2023 - 15:49:35
 */
public class ArmaGUI extends JDialog {

    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();
    ConverteDatas converteDatas = new ConverteDatas();
    JButton btBuscar = new JButton("Buscar");
    JButton btAdicionar = new JButton("Adicionar");
    JButton btSalvar = new JButton("Salvar");
    JButton btAlterar = new JButton("Alterar");
    JButton btExcluir = new JButton("Excluir");
    JButton btListar = new JButton("Listar");
    JButton btCancelar = new JButton("Cancelar");
    String acao = "";
    private JScrollPane scrollTabela = new JScrollPane();

    private JPanel pnAvisos = new JPanel(new GridLayout(1, 1));
    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));
    private JPanel pnVazio = new JPanel(new GridLayout(6, 1));

    Arma arma = new Arma();

    private CardLayout cardLayout;////////////////////////////////// - MUTÁVEL - //////////////////////////////////
//pk
    JLabel lbIdraca = new JLabel("ID");
    JTextField tfIdraca = new JTextField(30);
    JLabel lbNome_arma = new JLabel("Name");
    JTextField tfNome_arma = new JTextField(25);
    JLabel lbDesc_arma = new JLabel("Description");
    JTextField tfDesc_arma = new JTextField(25);
    JLabel lbDano = new JLabel("Damage");
    JTextField tfDano = new JTextField(25);
    DAOArma daoArma = new DAOArma();
    String[] colunas = new String[]{"id", "Name", "Description", "Damage"};
    String[][] dados = new String[0][colunas.length];
    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    public ArmaGUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Weapon");

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.setBackground(Color.GRAY);
        pnCentro.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnNorte.add(lbIdraca);
        pnNorte.add(tfIdraca);
        pnNorte.add(btBuscar);
        pnNorte.add(btAdicionar);
        pnNorte.add(btAlterar);
        pnNorte.add(btExcluir);
        pnNorte.add(btListar);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);

        lbIdraca.setVisible(true);
        tfIdraca.setVisible(true);
        btSalvar.setVisible(false);
        btAdicionar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btCancelar.setVisible(false);
        btSalvar.setVisible(false);
        btSalvar.setBackground(Color.WHITE);
        btBuscar.setBackground(Color.WHITE);
        btListar.setBackground(Color.WHITE);
        btAdicionar.setBackground(Color.WHITE);
        btAlterar.setBackground(Color.WHITE);
        btExcluir.setBackground(Color.WHITE);
        btCancelar.setBackground(Color.WHITE);
        pnCentro.setLayout(new GridLayout(colunas.length - 1, 2));
        pnCentro.add(lbNome_arma);
        pnCentro.add(tfNome_arma);
        pnCentro.add(lbDesc_arma);
        pnCentro.add(tfDesc_arma);
        pnCentro.add(lbDano);
        pnCentro.add(tfDano);
        cardLayout = new CardLayout();
        pnSul.setLayout(cardLayout);
        for (int i = 0; i < 5; i++) {
            pnVazio.add(new JLabel(" "));
        }
        pnSul.add(pnVazio, "vazio");
        pnSul.add(pnAvisos, "avisos");
        pnSul.add(pnListagem, "listagem");
        tabela.setEnabled(false);

        pnAvisos.add(new JLabel("Avisos"));
        tfIdraca.setEditable(true);
        tfNome_arma.setText("");
        tfNome_arma.setEditable(false);
        tfDesc_arma.setText("");
        tfDesc_arma.setEditable(false);
        tfDano.setText("");
        tfDano.setEditable(false);

//listeners
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cardLayout.show(pnSul, "avisos");
                    arma = daoArma.obter(Integer.valueOf(tfIdraca.getText()));
                    if (arma != null) {//achou o arma na lista
                        //mostrar
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        btCancelar.setVisible(true);
                        tfNome_arma.setText(arma.getNomeArma());
                        tfNome_arma.setEditable(false);
                        tfDesc_arma.setText(arma.getDescArma());
                        tfDesc_arma.setEditable(false);
                        tfDano.setText(String.valueOf(arma.getDano()));
                        tfDano.setEditable(false);
                    } else {//não achou na lista
                        //mostrar botão incluir

                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfIdraca.setEditable(true);
                        tfNome_arma.setText("");
                        tfNome_arma.setEditable(false);
                        tfDesc_arma.setText("");
                        tfDesc_arma.setEditable(false);
                        tfDano.setText("");
                        tfDano.setEditable(false);
                    }
                } catch (Exception extru0) {
                    JOptionPane.showMessageDialog(null, "Algo deu errado");
                }
            }
        });

//listener adicionar
        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfIdraca.setEnabled(false);
                tfIdraca.setEditable(true);
                tfNome_arma.requestFocus();
                tfNome_arma.setEditable(true);
                tfDesc_arma.setEditable(true);
                tfDano.setEditable(true);
                btAdicionar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                acao = "adicionar";
            }
        });

//listener Salvar
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (acao.equals("alterar")) {
                        arma.setIdarma(Integer.valueOf(tfIdraca.getText()));
                        arma.setNomeArma(tfNome_arma.getText());
                        arma.setDescArma(tfDesc_arma.getText());
                        arma.setDano(Integer.valueOf(tfDano.getText()));
                        daoArma.atualizar(arma);
                    } else { //acao == adicionar
                        arma = new Arma();
                        arma.setIdarma(Integer.valueOf(tfIdraca.getText()));
                        arma.setNomeArma(tfNome_arma.getText());
                        arma.setDescArma(tfDesc_arma.getText());
                        arma.setDano(Integer.valueOf(tfDano.getText()));
                        daoArma.inserir(arma);
                    }
                    btSalvar.setVisible(false);
                    btCancelar.setVisible(false);
                    btBuscar.setVisible(true);
                    btListar.setVisible(true);
                    tfIdraca.setEnabled(true);
                    tfIdraca.setEditable(true);
                    tfIdraca.requestFocus();
                    tfIdraca.setText("");
                    tfNome_arma.setText("");
                    tfNome_arma.setEditable(false);
                    tfDesc_arma.setText("");
                    tfDesc_arma.setEditable(false);
                    tfDano.setText("");
                    tfDano.setEditable(false);
                } catch (Exception macau1) {
                    JOptionPane.showMessageDialog(null, "Algo deu errado");
                }
            }
        });

//listener Alterar
        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btBuscar.setVisible(false);
                btAlterar.setVisible(false);
                tfIdraca.setEditable(false);
                tfNome_arma.requestFocus();
                tfNome_arma.setEditable(true);
                tfDesc_arma.setEditable(true);
                tfDano.setEditable(true);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btListar.setVisible(false);
                btExcluir.setVisible(false);
                acao = "alterar";

            }
        });
//listener excluir
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int response = JOptionPane.showConfirmDialog(cp, "Confirme a exclusão?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                btExcluir.setVisible(false);
                tfIdraca.setEditable(false);
                tfIdraca.requestFocus();
                tfIdraca.setText("");
                tfIdraca.setEditable(true);
                tfNome_arma.setText("");
                tfNome_arma.setEditable(true);
                tfDesc_arma.setText("");
                tfDesc_arma.setEditable(true);
                tfDano.setText("");
                tfDano.setEditable(true);
                btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    daoArma.remover(arma);
                }
            }
        });
//listener listar
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Arma> listaArma = daoArma.listInOrderNome();
                String[] colunas = new String[]{"id", "Name", "Description", "Damage"};
                String[][] dados = new String[listaArma.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaArma.size(); i++) {
                    aux = listaArma.get(i).toString().split(";");
                    for (int j = 0; j < colunas.length; j++) {
                        dados[i][j] = aux[j];
                    }
                }
                cardLayout.show(pnSul, "listagem");
                scrollTabela.setPreferredSize(tabela.getPreferredSize());
                pnListagem.add(scrollTabela);
                scrollTabela.setViewportView(tabela);
                model.setDataVector(dados, colunas);

                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                btAdicionar.setVisible(false);

            }
        });
//listener Cancelar
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btCancelar.setVisible(false);
                tfIdraca.setText("");
                tfIdraca.requestFocus();
                tfIdraca.setEnabled(true);
                tfIdraca.setEditable(true);
                tfNome_arma.setText("");
                tfNome_arma.setEditable(false);
                tfDesc_arma.setText("");
                tfDesc_arma.setEditable(false);
                tfDano.setText("");
                tfDano.setEditable(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);

            }
        });
//windão FECHAR O PROGRAMA
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //antes de sair, salvar a lista em armazenamento permanente
                // Sai da classe
                dispose();
            }
        });

        setModal(true);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);//centraliza na tela
        setVisible(true);
    }//fim do construtor de GUI
}//fim da classe
