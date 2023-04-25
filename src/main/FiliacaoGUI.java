package main;

import Entidades.Filiacao;
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
import DAOs.DAOFiliacao;

/**
 *
 * @author Mateus Batichotti Silva | 19/04/2023 - 15:53:14
 */
public class FiliacaoGUI extends JDialog {

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

    Filiacao filiacao = new Filiacao();

    private CardLayout cardLayout;////////////////////////////////// - MUTÁVEL - //////////////////////////////////
//pk
    JLabel lbIdfiliacao = new JLabel("ID");
    JTextField tfIdfiliacao = new JTextField(30);
    JLabel lbFiliacao = new JLabel("Filiation");
    JTextField tfFiliacao = new JTextField(25);
    JLabel lbDesc_filiacao = new JLabel("Description");
    JTextField tfDesc_filiacao = new JTextField(25);
    DAOFiliacao daoFiliacao = new DAOFiliacao();
    String[] colunas = new String[]{"id", "Filiation", "Description"};
    String[][] dados = new String[0][colunas.length];
    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    public FiliacaoGUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Filiation");

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.setBackground(Color.GRAY);
        pnCentro.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnNorte.add(lbIdfiliacao);
        pnNorte.add(tfIdfiliacao);
        pnNorte.add(btBuscar);
        pnNorte.add(btAdicionar);
        pnNorte.add(btAlterar);
        pnNorte.add(btExcluir);
        pnNorte.add(btListar);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);

        lbIdfiliacao.setVisible(true);
        tfIdfiliacao.setVisible(true);
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
        pnCentro.add(lbFiliacao);
        pnCentro.add(tfFiliacao);
        pnCentro.add(lbDesc_filiacao);
        pnCentro.add(tfDesc_filiacao);
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
        tfIdfiliacao.setEditable(true);
        tfFiliacao.setText("");
        tfFiliacao.setEditable(false);
        tfDesc_filiacao.setText("");
        tfDesc_filiacao.setEditable(false);

//listeners
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cardLayout.show(pnSul, "avisos");
                    filiacao = daoFiliacao.obter(Integer.valueOf(tfIdfiliacao.getText()));
                    if (filiacao != null) {//achou o filiacao na lista
                        //mostrar
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        btCancelar.setVisible(true);
                        tfFiliacao.setText(filiacao.getFiliacao());
                        tfFiliacao.setEditable(false);
                        tfDesc_filiacao.setText(filiacao.getDescFiliacao());
                        tfDesc_filiacao.setEditable(false);
                    } else {//não achou na lista
                        //mostrar botão incluir

                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfIdfiliacao.setEditable(true);
                        tfFiliacao.setText("");
                        tfFiliacao.setEditable(false);
                        tfDesc_filiacao.setText("");
                        tfDesc_filiacao.setEditable(false);
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
                tfIdfiliacao.setEnabled(false);
                tfIdfiliacao.setEditable(true);
                tfFiliacao.requestFocus();
                tfFiliacao.setEditable(true);
                tfDesc_filiacao.setEditable(true);
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
                        filiacao.setIdfiliacao(Integer.valueOf(tfIdfiliacao.getText()));
                        filiacao.setFiliacao(tfFiliacao.getText());
                        filiacao.setDescFiliacao(tfDesc_filiacao.getText());
                        daoFiliacao.atualizar(filiacao);
                    } else { //acao == adicionar
                        filiacao = new Filiacao();
                        filiacao.setIdfiliacao(Integer.valueOf(tfIdfiliacao.getText()));
                        filiacao.setFiliacao(tfFiliacao.getText());
                        filiacao.setDescFiliacao(tfDesc_filiacao.getText());
                        daoFiliacao.inserir(filiacao);
                    }
                    btSalvar.setVisible(false);
                    btCancelar.setVisible(false);
                    btBuscar.setVisible(true);
                    btListar.setVisible(true);
                    tfIdfiliacao.setEnabled(true);
                    tfIdfiliacao.setEditable(true);
                    tfIdfiliacao.requestFocus();
                    tfIdfiliacao.setText("");
                    tfFiliacao.setText("");
                    tfFiliacao.setEditable(false);
                    tfDesc_filiacao.setText("");
                    tfDesc_filiacao.setEditable(false);
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
                tfIdfiliacao.setEditable(false);
                tfFiliacao.requestFocus();
                tfFiliacao.setEditable(true);
                tfDesc_filiacao.setEditable(true);
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
                tfIdfiliacao.setEditable(false);
                tfIdfiliacao.requestFocus();
                tfIdfiliacao.setText("");
                tfIdfiliacao.setEditable(true);
                tfFiliacao.setText("");
                tfFiliacao.setEditable(true);
                tfDesc_filiacao.setText("");
                tfDesc_filiacao.setEditable(true);
                btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    daoFiliacao.remover(filiacao);
                }
            }
        });
//listener listar
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Filiacao> listaFiliacao = daoFiliacao.listInOrderNome();
                String[] colunas = new String[]{"id", "Filiation", "Description"};
                String[][] dados = new String[listaFiliacao.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaFiliacao.size(); i++) {
                    aux = listaFiliacao.get(i).toString().split(";");
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
                tfIdfiliacao.setText("");
                tfIdfiliacao.requestFocus();
                tfIdfiliacao.setEnabled(true);
                tfIdfiliacao.setEditable(true);
                tfFiliacao.setText("");
                tfFiliacao.setEditable(false);
                tfDesc_filiacao.setText("");
                tfDesc_filiacao.setEditable(false);
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
