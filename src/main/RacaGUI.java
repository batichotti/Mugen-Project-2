package main;

import Entidades.Raca;
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
import DAOs.DAORaca;

/**
 *
 * @author Mateus Batichotti Silva | 19/04/2023 - 15:48:00
 */
public class RacaGUI extends JDialog {

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

    Raca raca = new Raca();

    private CardLayout cardLayout;////////////////////////////////// - MUTÁVEL - //////////////////////////////////
//pk
    JLabel lbIdraca = new JLabel("ID");
    JTextField tfIdraca = new JTextField(30);
    JLabel lbNome_raca = new JLabel("Name");
    JTextField tfNome_raca = new JTextField(25);
    JLabel lbDesc_raca = new JLabel("Description");
    JTextField tfDesc_raca = new JTextField(25);
    DAORaca daoRaca = new DAORaca();
    String[] colunas = new String[]{"id", "Name", "Description"};
    String[][] dados = new String[0][colunas.length];
    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    public RacaGUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Race");

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
        pnCentro.add(lbNome_raca);
        pnCentro.add(tfNome_raca);
        pnCentro.add(lbDesc_raca);
        pnCentro.add(tfDesc_raca);
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
        tfNome_raca.setText("");
        tfNome_raca.setEditable(false);
        tfDesc_raca.setText("");
        tfDesc_raca.setEditable(false);

//listeners
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cardLayout.show(pnSul, "avisos");
                    raca = daoRaca.obter(Integer.valueOf(tfIdraca.getText()));
                    if (raca != null) {//achou o raca na lista
                        //mostrar
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        btCancelar.setVisible(true);
                        tfNome_raca.setText(raca.getNomeRaca());
                        tfNome_raca.setEditable(false);
                        tfDesc_raca.setText(raca.getDescricao());
                        tfDesc_raca.setEditable(false);
                    } else {//não achou na lista
                        //mostrar botão incluir

                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfIdraca.setEditable(true);
                        tfNome_raca.setText("");
                        tfNome_raca.setEditable(false);
                        tfDesc_raca.setText("");
                        tfDesc_raca.setEditable(false);
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
                tfNome_raca.requestFocus();
                tfNome_raca.setEditable(true);
                tfDesc_raca.setEditable(true);
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
                        raca.setIdraca(Integer.valueOf(tfIdraca.getText()));
                        raca.setNomeRaca(tfNome_raca.getText());
                        raca.setDescricao(tfDesc_raca.getText());
                        daoRaca.atualizar(raca);
                    } else { //acao == adicionar
                        raca = new Raca();
                        raca.setIdraca(Integer.valueOf(tfIdraca.getText()));
                        raca.setNomeRaca(tfNome_raca.getText());
                        raca.setDescricao(tfDesc_raca.getText());
                        daoRaca.inserir(raca);
                    }
                    btSalvar.setVisible(false);
                    btCancelar.setVisible(false);
                    btBuscar.setVisible(true);
                    btListar.setVisible(true);
                    tfIdraca.setEnabled(true);
                    tfIdraca.setEditable(true);
                    tfIdraca.requestFocus();
                    tfIdraca.setText("");
                    tfNome_raca.setText("");
                    tfNome_raca.setEditable(false);
                    tfDesc_raca.setText("");
                    tfDesc_raca.setEditable(false);
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
                tfNome_raca.requestFocus();
                tfNome_raca.setEditable(true);
                tfDesc_raca.setEditable(true);
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
                tfNome_raca.setText("");
                tfNome_raca.setEditable(true);
                tfDesc_raca.setText("");
                tfDesc_raca.setEditable(true);
                btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    daoRaca.remover(raca);
                }
            }
        });
//listener listar
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Raca> listaRaca = daoRaca.listInOrderNome();
                String[] colunas = new String[]{"id", "Name", "Description"};
                String[][] dados = new String[listaRaca.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaRaca.size(); i++) {
                    aux = listaRaca.get(i).toString().split(";");
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
                tfNome_raca.setText("");
                tfNome_raca.setEditable(false);
                tfDesc_raca.setText("");
                tfDesc_raca.setEditable(false);
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
