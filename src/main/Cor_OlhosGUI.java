package main;

import Entidades.Cor_olhos;
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
import DAOs.DAOCor_olhos;

/**
 *
 * @author Mateus Batichotti Silva | 19/04/2023 - 15:36:37
 */
public class Cor_OlhosGUI extends JDialog {

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

    Cor_olhos cor_Olhos = new Cor_olhos();

    private CardLayout cardLayout;////////////////////////////////// - MUTÁVEL - //////////////////////////////////
//pk
    JLabel lbIdcor_olhos = new JLabel("ID");
    JTextField tfIdcor_olhos = new JTextField(30);
    JLabel lbCor = new JLabel("Color");
    JTextField tfCor = new JTextField(25);
    JLabel lbHeterocromia = new JLabel("Heterochromia");

    JCheckBox checkHeterocromia = new JCheckBox();
    DAOCor_olhos daoCor_Olhos = new DAOCor_olhos();
    String[] colunas = new String[]{"id", "Color", "Heterochromia"};
    String[][] dados = new String[0][colunas.length];
    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    public Cor_OlhosGUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Eyes Color");

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.setBackground(Color.GRAY);
        pnCentro.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnNorte.add(lbIdcor_olhos);
        pnNorte.add(tfIdcor_olhos);
        pnNorte.add(btBuscar);
        pnNorte.add(btAdicionar);
        pnNorte.add(btAlterar);
        pnNorte.add(btExcluir);
        pnNorte.add(btListar);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);

        lbIdcor_olhos.setVisible(true);
        tfIdcor_olhos.setVisible(true);
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
        pnCentro.add(lbCor);
        pnCentro.add(tfCor);
        pnCentro.add(lbHeterocromia);
        pnCentro.add(checkHeterocromia);
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
        tfIdcor_olhos.setEditable(true);
        tfCor.setText("");
        tfCor.setEditable(false);
        checkHeterocromia.setSelected(false);

//listeners
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cardLayout.show(pnSul, "avisos");
                    cor_Olhos = daoCor_Olhos.obter(Integer.valueOf(tfIdcor_olhos.getText()));
                    if (cor_Olhos != null) {//achou o cor_Olhos na lista
                        //mostrar
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        btCancelar.setVisible(true);
                        tfCor.setText(cor_Olhos.getCor());
                        tfCor.setEditable(false);
                        checkHeterocromia.setSelected(cor_Olhos.getHeterocromia() == 1 ? true : false);
                    } else {//não achou na lista
                        //mostrar botão incluir

                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfIdcor_olhos.setEditable(true);
                        tfCor.setText("");
                        tfCor.setEditable(false);
                        checkHeterocromia.setSelected(false);
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
                tfIdcor_olhos.setEnabled(false);
                tfIdcor_olhos.setEditable(true);
                tfCor.requestFocus();
                tfCor.setEditable(true);
                checkHeterocromia.setEnabled(true);
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
                    Short heterocromia_escopo;
                    if (checkHeterocromia.isSelected()) {
                        heterocromia_escopo = (short) 1;
                    } else {
                        heterocromia_escopo = (short) 0;
                    }
                    if (acao.equals("alterar")) {
                        cor_Olhos.setIdcorOlhos(Integer.valueOf(tfIdcor_olhos.getText()));
                        cor_Olhos.setCor(tfCor.getText());
                        cor_Olhos.setHeterocromia(heterocromia_escopo);
                        daoCor_Olhos.atualizar(cor_Olhos);
                    } else { //acao == adicionar
                        cor_Olhos = new Cor_olhos();
                        cor_Olhos.setIdcorOlhos(Integer.valueOf(tfIdcor_olhos.getText()));
                        cor_Olhos.setCor(tfCor.getText());
                        cor_Olhos.setHeterocromia(heterocromia_escopo);
                        daoCor_Olhos.inserir(cor_Olhos);
                    }
                    btSalvar.setVisible(false);
                    btCancelar.setVisible(false);
                    btBuscar.setVisible(true);
                    btListar.setVisible(true);
                    tfIdcor_olhos.setEnabled(true);
                    tfIdcor_olhos.setEditable(true);
                    tfIdcor_olhos.requestFocus();
                    tfIdcor_olhos.setText("");
                    tfCor.setText("");
                    tfCor.setEditable(false);
                    checkHeterocromia.setSelected(false);
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
                tfIdcor_olhos.setEditable(false);
                tfCor.requestFocus();
                tfCor.setEditable(true);
                checkHeterocromia.setEnabled(true);
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
                tfIdcor_olhos.setEditable(false);
                tfIdcor_olhos.requestFocus();
                tfIdcor_olhos.setText("");
                tfIdcor_olhos.setEditable(true);
                tfCor.setText("");
                tfCor.setEditable(true);
                checkHeterocromia.setEnabled(true);
                btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    daoCor_Olhos.remover(cor_Olhos);
                }
            }
        });
//listener listar
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Cor_olhos> listaCor_Olhos = daoCor_Olhos.listInOrderNome();
                String[] colunas = new String[]{"id", "Color", "Heterochromia"};
                String[][] dados = new String[listaCor_Olhos.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaCor_Olhos.size(); i++) {
                    aux = listaCor_Olhos.get(i).toString().split(";");
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
                tfIdcor_olhos.setText("");
                tfIdcor_olhos.requestFocus();
                tfIdcor_olhos.setEnabled(true);
                tfIdcor_olhos.setEditable(true);
                tfCor.setText("");
                tfCor.setEditable(false);
                checkHeterocromia.setEnabled(false);
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
