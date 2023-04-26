package main;

import Entidades.Jujutsu;
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
import DAOs.DAOJujutsu;

/**
 *
 * @author Mateus Batichotti Silva | 19/04/2023 - 15:52:24
 */
public class JujutsuGUI extends JDialog {

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

    Jujutsu jujutsu = new Jujutsu();

    private CardLayout cardLayout;////////////////////////////////// - MUTÁVEL - //////////////////////////////////
//pk
    JLabel lbIdjujutsu = new JLabel("ID");
    JTextField tfIdjujutsu = new JTextField(30);
    JLabel lbJujutsu_name = new JLabel("Jujutsu Name");
    JTextField tfJujutsu_name = new JTextField(25);
    JLabel lbJujutsu_desc = new JLabel("Description");
    JTextField tfJujutsu_desc = new JTextField(25);
    JLabel lbDano_base = new JLabel("Base Damage");
    JTextField tfDano_base = new JTextField(25);
    DAOJujutsu daoJujutsu = new DAOJujutsu();
    String[] colunas = new String[]{"id", "Name", "Description", "Base Damage"};
    String[][] dados = new String[0][colunas.length];
    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    public JujutsuGUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Jujutsu");

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.setBackground(Color.GRAY);
        pnCentro.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnNorte.add(lbIdjujutsu);
        pnNorte.add(tfIdjujutsu);
        pnNorte.add(btBuscar);
        pnNorte.add(btAdicionar);
        pnNorte.add(btAlterar);
        pnNorte.add(btExcluir);
        pnNorte.add(btListar);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);

        lbIdjujutsu.setVisible(true);
        tfIdjujutsu.setVisible(true);
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
        pnCentro.add(lbJujutsu_name);
        pnCentro.add(tfJujutsu_name);
        pnCentro.add(lbJujutsu_desc);
        pnCentro.add(tfJujutsu_desc);
        pnCentro.add(lbDano_base);
        pnCentro.add(tfDano_base);
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
        tfIdjujutsu.setEditable(true);
        tfJujutsu_name.setText("");
        tfJujutsu_name.setEditable(false);
        tfJujutsu_desc.setText("");
        tfJujutsu_desc.setEditable(false);
        tfDano_base.setText("");
        tfDano_base.setEditable(false);

//listeners
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cardLayout.show(pnSul, "avisos");
                    jujutsu = daoJujutsu.obter(Integer.valueOf(tfIdjujutsu.getText()));
                    if (jujutsu != null) {//achou o jujutsu na lista
                        //mostrar
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        btCancelar.setVisible(true);
                        tfJujutsu_name.setText(jujutsu.getJujutsuName());
                        tfJujutsu_name.setEditable(false);
                        tfJujutsu_desc.setText(jujutsu.getJujutsuDesc());
                        tfJujutsu_desc.setEditable(false);
                        tfDano_base.setText(String.valueOf(jujutsu.getDanoBase()));
                        tfDano_base.setEditable(false);
                    } else {//não achou na lista
                        //mostrar botão incluir

                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfIdjujutsu.setEditable(true);
                        tfJujutsu_name.setText("");
                        tfJujutsu_name.setEditable(false);
                        tfJujutsu_desc.setText("");
                        tfJujutsu_desc.setEditable(false);
                        tfDano_base.setText("");
                        tfDano_base.setEditable(false);
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
                tfIdjujutsu.setEnabled(false);
                tfIdjujutsu.setEditable(true);
                tfJujutsu_name.requestFocus();
                tfJujutsu_name.setEditable(true);
                tfJujutsu_desc.setEditable(true);
                tfDano_base.setEditable(true);
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
                        jujutsu.setIdjujutsu(Integer.valueOf(tfIdjujutsu.getText()));
                        jujutsu.setJujutsuName(tfJujutsu_name.getText());
                        jujutsu.setJujutsuDesc(tfJujutsu_desc.getText());
                        jujutsu.setDanoBase(Integer.valueOf(tfDano_base.getText()));
                        daoJujutsu.atualizar(jujutsu);
                    } else { //acao == adicionar
                        jujutsu = new Jujutsu();
                        jujutsu.setIdjujutsu(Integer.valueOf(tfIdjujutsu.getText()));
                        jujutsu.setJujutsuName(tfJujutsu_name.getText());
                        jujutsu.setJujutsuDesc(tfJujutsu_desc.getText());
                        jujutsu.setDanoBase(Integer.valueOf(tfDano_base.getText()));
                        daoJujutsu.inserir(jujutsu);
                    }
                    btSalvar.setVisible(false);
                    btCancelar.setVisible(false);
                    btBuscar.setVisible(true);
                    btListar.setVisible(true);
                    tfIdjujutsu.setEnabled(true);
                    tfIdjujutsu.setEditable(true);
                    tfIdjujutsu.requestFocus();
                    tfIdjujutsu.setText("");
                    tfJujutsu_name.setText("");
                    tfJujutsu_name.setEditable(false);
                    tfJujutsu_desc.setText("");
                    tfJujutsu_desc.setEditable(false);
                    tfDano_base.setText("");
                    tfDano_base.setEditable(false);
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
                tfIdjujutsu.setEditable(false);
                tfJujutsu_name.requestFocus();
                tfJujutsu_name.setEditable(true);
                tfJujutsu_desc.setEditable(true);
                tfDano_base.setEditable(true);
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
                tfIdjujutsu.setEditable(false);
                tfIdjujutsu.requestFocus();
                tfIdjujutsu.setText("");
                tfIdjujutsu.setEditable(true);
                tfJujutsu_name.setText("");
                tfJujutsu_name.setEditable(true);
                tfJujutsu_desc.setText("");
                tfJujutsu_desc.setEditable(true);
                tfDano_base.setText("");
                tfDano_base.setEditable(true);
                btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    daoJujutsu.remover(jujutsu);
                }
            }
        });
//listener listar
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Jujutsu> listaJujutsu = daoJujutsu.listInOrderNome();
                String[] colunas = new String[]{"id", "Name", "Description", "Base Damage"};
                String[][] dados = new String[listaJujutsu.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaJujutsu.size(); i++) {
                    aux = listaJujutsu.get(i).toString().split(";");
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
                tfIdjujutsu.setText("");
                tfIdjujutsu.requestFocus();
                tfIdjujutsu.setEnabled(true);
                tfIdjujutsu.setEditable(true);
                tfJujutsu_name.setText("");
                tfJujutsu_name.setEditable(false);
                tfJujutsu_desc.setText("");
                tfJujutsu_desc.setEditable(false);
                tfDano_base.setText("");
                tfDano_base.setEditable(false);
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
