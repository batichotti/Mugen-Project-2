package main;

import Entidades.Grade;
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
import DAOs.DAOGrade;

/**
 *
 * @author Mateus Batichotti Silva | 19/04/2023 - 15:53:55
 */
public class GradeGUI extends JDialog {

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

    Grade grade = new Grade();

    private CardLayout cardLayout;////////////////////////////////// - MUTÁVEL - //////////////////////////////////
//pk
    JLabel lbIdgrade = new JLabel("ID");
    JTextField tfIdgrade = new JTextField(30);
    JLabel lbNome_grade = new JLabel("Name");
    JTextField tfNome_grade = new JTextField(25);
    JLabel lbDescricao = new JLabel("Description");
    JTextField tfDescricao = new JTextField(25);
    DAOGrade daoGrade = new DAOGrade();
    String[] colunas = new String[]{"id", "Name", "Description"};
    String[][] dados = new String[0][colunas.length];
    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    public GradeGUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Grade");

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.setBackground(Color.GRAY);
        pnCentro.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnNorte.add(lbIdgrade);
        pnNorte.add(tfIdgrade);
        pnNorte.add(btBuscar);
        pnNorte.add(btAdicionar);
        pnNorte.add(btAlterar);
        pnNorte.add(btExcluir);
        pnNorte.add(btListar);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);

        lbIdgrade.setVisible(true);
        tfIdgrade.setVisible(true);
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
        pnCentro.add(lbNome_grade);
        pnCentro.add(tfNome_grade);
        pnCentro.add(lbDescricao);
        pnCentro.add(tfDescricao);
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
        tfIdgrade.setEditable(true);
        tfNome_grade.setText("");
        tfNome_grade.setEditable(false);
        tfDescricao.setText("");
        tfDescricao.setEditable(false);

//listeners
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cardLayout.show(pnSul, "avisos");
                    grade = daoGrade.obter(Integer.valueOf(tfIdgrade.getText()));
                    if (grade != null) {//achou o grade na lista
                        //mostrar
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        btCancelar.setVisible(true);
                        tfNome_grade.setText(grade.getNomeGrade());
                        tfNome_grade.setEditable(false);
                        tfDescricao.setText(grade.getDescricao());
                        tfDescricao.setEditable(false);
                    } else {//não achou na lista
                        //mostrar botão incluir

                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfIdgrade.setEditable(true);
                        tfNome_grade.setText("");
                        tfNome_grade.setEditable(false);
                        tfDescricao.setText("");
                        tfDescricao.setEditable(false);
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
                tfIdgrade.setEnabled(false);
                tfIdgrade.setEditable(true);
                tfNome_grade.requestFocus();
                tfNome_grade.setEditable(true);
                tfDescricao.setEditable(true);
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
                        grade.setIdgrade(Integer.valueOf(tfIdgrade.getText()));
                        grade.setNomeGrade(tfNome_grade.getText());
                        grade.setDescricao(tfDescricao.getText());
                        daoGrade.atualizar(grade);
                    } else { //acao == adicionar
                        grade = new Grade();
                        grade.setIdgrade(Integer.valueOf(tfIdgrade.getText()));
                        grade.setNomeGrade(tfNome_grade.getText());
                        grade.setDescricao(tfDescricao.getText());
                        daoGrade.inserir(grade);
                    }
                    btSalvar.setVisible(false);
                    btCancelar.setVisible(false);
                    btBuscar.setVisible(true);
                    btListar.setVisible(true);
                    tfIdgrade.setEnabled(true);
                    tfIdgrade.setEditable(true);
                    tfIdgrade.requestFocus();
                    tfIdgrade.setText("");
                    tfNome_grade.setText("");
                    tfNome_grade.setEditable(false);
                    tfDescricao.setText("");
                    tfDescricao.setEditable(false);
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
                tfIdgrade.setEditable(false);
                tfNome_grade.requestFocus();
                tfNome_grade.setEditable(true);
                tfDescricao.setEditable(true);
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
                tfIdgrade.setEditable(false);
                tfIdgrade.requestFocus();
                tfIdgrade.setText("");
                tfIdgrade.setEditable(true);
                tfNome_grade.setText("");
                tfNome_grade.setEditable(true);
                tfDescricao.setText("");
                tfDescricao.setEditable(true);
                btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    daoGrade.remover(grade);
                }
            }
        });
//listener listar
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Grade> listaGrade = daoGrade.listInOrderNome();
                String[] colunas = new String[]{"id", "Name", "Description"};
                String[][] dados = new String[listaGrade.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaGrade.size(); i++) {
                    aux = listaGrade.get(i).toString().split(";");
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
                tfIdgrade.setText("");
                tfIdgrade.requestFocus();
                tfIdgrade.setEnabled(true);
                tfIdgrade.setEditable(true);
                tfNome_grade.setText("");
                tfNome_grade.setEditable(false);
                tfDescricao.setText("");
                tfDescricao.setEditable(false);
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
