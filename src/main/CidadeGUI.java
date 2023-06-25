package main;

import Entidades.Cidade;
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
import DAOs.DAOCidade;
import DAOs.DAOPais;
import Entidades.Pais;
import javax.swing.JComboBox;

/**
 *
 * @author Mateus Batichotti Silva | 19/04/2023 - 15:55:43
 */
public class CidadeGUI extends JDialog {

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

    Cidade cidade = new Cidade();

    private CardLayout cardLayout;////////////////////////////////// - MUTÁVEL - //////////////////////////////////
//pk
    JLabel lbIdcidade = new JLabel("ID");
    JTextField tfIdcidade = new JTextField(30);
    
    JLabel lbPais = new JLabel("País");
    DAOPais daoPais = new DAOPais();
    List<Pais> paises = daoPais.listInOrderNome();
    Pais pais = new Pais();
    JComboBox cbPais = new JComboBox();
    
    JLabel lbNome_cidade = new JLabel("Name");
    JTextField tfNome_cidade = new JTextField(25);
    DAOCidade daoCidade = new DAOCidade();
    String[] colunas = new String[]{"id", "Name", "País"};
    String[][] dados = new String[0][colunas.length];
    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    public CidadeGUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Country");

        //Add combo box
        for (Pais pais_es : paises) {
            String ditto = pais_es.toString().replace(";", "-");
            cbPais.addItem(ditto);
        }
        
        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.setBackground(Color.GRAY);
        pnCentro.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnNorte.add(lbIdcidade);
        pnNorte.add(tfIdcidade);
        pnNorte.add(lbPais);
        pnNorte.add(cbPais);
        pnNorte.add(btBuscar);
        pnNorte.add(btAdicionar);
        pnNorte.add(btAlterar);
        pnNorte.add(btExcluir);
        pnNorte.add(btListar);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);

        lbIdcidade.setVisible(true);
        tfIdcidade.setVisible(true);
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
        pnCentro.add(lbNome_cidade);
        pnCentro.add(tfNome_cidade);
        pnCentro.add(lbPais);
        pnCentro.add(cbPais);
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
        tfIdcidade.setEditable(true);
        tfNome_cidade.setText("");
        tfNome_cidade.setEditable(false);
        cbPais.setEnabled(false);

//listeners
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cardLayout.show(pnSul, "avisos");
                    cidade = daoCidade.obter(Integer.valueOf(tfIdcidade.getText()));
                    if (cidade != null) {//achou o cidade na lista
                        //mostrar
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        btCancelar.setVisible(true);
                        tfNome_cidade.setText(cidade.getNomeCidade());
                        tfNome_cidade.setEditable(false);
                        cbPais.setSelectedItem(cidade.getPaisIdpais().toString().replace(";","-"));
                        cbPais.setEnabled(false);
                    } else {//não achou na lista
                        //mostrar botão incluir

                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfIdcidade.setEditable(true);
                        tfNome_cidade.setText("");
                        tfNome_cidade.setEditable(false);
                        cbPais.setEnabled(false);
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
                tfIdcidade.setEnabled(false);
                tfIdcidade.setEditable(true);
                tfNome_cidade.requestFocus();
                tfNome_cidade.setEditable(true);
                cbPais.setEnabled(true);
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
                        cidade.setIdcidade(Integer.valueOf(tfIdcidade.getText()));
                        cidade.setNomeCidade(tfNome_cidade.getText());
                        
                        Pais selecionado = daoPais.obter(Integer.valueOf(String.valueOf(cbPais.getSelectedItem()).split("-")[0]));
                        cidade.setPaisIdpais(selecionado);
                        
                        daoCidade.atualizar(cidade);
                    } else { //acao == adicionar
                        cidade = new Cidade();
                        cidade.setIdcidade(Integer.valueOf(tfIdcidade.getText()));
                        cidade.setNomeCidade(tfNome_cidade.getText());
                        
                        Pais selecionado = daoPais.obter(Integer.valueOf(String.valueOf(cbPais.getSelectedItem()).split("-")[0]));
                        cidade.setPaisIdpais(selecionado);
                        
                        daoCidade.inserir(cidade);
                    }
                    btSalvar.setVisible(false);
                    btCancelar.setVisible(false);
                    btBuscar.setVisible(true);
                    btListar.setVisible(true);
                    tfIdcidade.setEnabled(true);
                    tfIdcidade.setEditable(true);
                    tfIdcidade.requestFocus();
                    tfIdcidade.setText("");
                    tfNome_cidade.setText("");
                    tfNome_cidade.setEditable(false);
                    cbPais.setEnabled(false);
                } catch (Exception macau1) {
                    JOptionPane.showMessageDialog(null, "Algo deu errado - btSalvar: " + macau1);
                }
            }
        });

//listener Alterar
        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btBuscar.setVisible(false);
                btAlterar.setVisible(false);
                tfIdcidade.setEditable(false);
                tfNome_cidade.requestFocus();
                tfNome_cidade.setEditable(true);
                cbPais.setEnabled(true);
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
                tfIdcidade.setEditable(false);
                tfIdcidade.requestFocus();
                tfIdcidade.setText("");
                tfIdcidade.setEditable(true);
                tfNome_cidade.setText("");
                tfNome_cidade.setEditable(true);
                cbPais.setEnabled(true);
                btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    daoCidade.remover(cidade);
                }
            }
        });
        
//listener listar
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Cidade> listaCidade = daoCidade.listInOrderNome();
                String[] colunas = new String[]{"id", "Name", "País"};
                String[][] dados = new String[listaCidade.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaCidade.size(); i++) {
                    aux = listaCidade.get(i).toString().split(";");
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
                tfIdcidade.setText("");
                tfIdcidade.requestFocus();
                tfIdcidade.setEnabled(true);
                tfIdcidade.setEditable(true);
                tfNome_cidade.setText("");
                tfNome_cidade.setEditable(false);
                cbPais.setEnabled(false);
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
