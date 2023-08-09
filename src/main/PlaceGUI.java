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
import DAOs.DAOCidade;
import DAOs.DAOPlace;
import Entidades.Cidade;
import Entidades.Place;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

/**
 *
 * @author Mateus Batichotti Silva | 19/04/2023 - 15:55:43
 */
public class PlaceGUI extends JDialog {

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

    Place place = new Place();

    private CardLayout cardLayout;////////////////////////////////// - MUTÁVEL - //////////////////////////////////
//pk
    JLabel lbIdplace = new JLabel("ID");
    JTextField tfIdplace = new JTextField(30);

    JLabel lbDesc = new JLabel("Descrição");
    JTextField tfDesc = new JTextField(30);

    JLabel lbCidade = new JLabel("Cidade");
    DAOCidade daoCidade = new DAOCidade();
    List<Cidade> cidades = daoCidade.listInOrderNome();
    Cidade cidade = new Cidade();
    JComboBox cbCidade = new JComboBox();

    JLabel lbDedo = new JLabel("Tem dedo?");
    JCheckBox cbDedo = new JCheckBox();

    JLabel lbNome_place = new JLabel("Name");
    JTextField tfNome_place = new JTextField(25);
    DAOPlace daoPlace = new DAOPlace();
    String[] colunas = new String[]{"id", "Name", "Descrição", "Tem Dedo?", "País"};
    String[][] dados = new String[0][colunas.length];
    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    public PlaceGUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Place");

        //Add combo box
        for (Cidade cidade_es : cidades) {
            String ditto = cidade_es.toString().replace(";", "-");
            cbCidade.addItem(ditto);
        }

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.setBackground(Color.GRAY);
        pnCentro.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnNorte.add(lbIdplace);
        pnNorte.add(tfIdplace);
        pnNorte.add(lbNome_place);
        pnNorte.add(tfNome_place);
        pnNorte.add(btBuscar);
        pnNorte.add(btAdicionar);
        pnNorte.add(btAlterar);
        pnNorte.add(btExcluir);
        pnNorte.add(btListar);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);

        lbIdplace.setVisible(true);
        tfIdplace.setVisible(true);
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
        pnCentro.add(lbNome_place);
        pnCentro.add(tfNome_place);
        pnCentro.add(lbDesc);
        pnCentro.add(tfDesc);
        pnCentro.add(lbDedo);
        pnCentro.add(cbDedo);
        pnCentro.add(lbCidade);
        pnCentro.add(cbCidade);
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
        tfIdplace.setEditable(true);
        tfNome_place.setText("");
        tfNome_place.setEditable(false);
        tfDesc.setEditable(false);
        cbDedo.setEnabled(false);
        cbCidade.setEnabled(false);

//listeners
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cardLayout.show(pnSul, "avisos");
                    place = daoPlace.obter(Integer.valueOf(tfIdplace.getText()));
                    if (place != null) {//achou o place na lista
                        //mostrar
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        btCancelar.setVisible(true);
                        tfNome_place.setText(place.getNomePlace());
                        tfNome_place.setEditable(false);

                        tfDesc.setText(place.getDescPlace());
                        tfDesc.setEditable(false);

                        cbDedo.setSelected(place.getTemDedo() == 1 ? true : false);
                        cbDedo.setEnabled(false);

                        cbCidade.setSelectedItem(place.getCidadeIdcidade().toString().replace(";", "-"));
                        cbCidade.setEnabled(false);
                    } else {//não achou na lista
                        //mostrar botão incluir

                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfIdplace.setEditable(true);
                        tfNome_place.setText("");

                        tfDesc.setEditable(false);

                        cbDedo.setEnabled(false);

                        tfNome_place.setEditable(false);
                        cbCidade.setEnabled(false);
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
                tfIdplace.setEnabled(false);
                tfIdplace.setEditable(true);
                tfNome_place.requestFocus();
                tfNome_place.setEditable(true);
                cbDedo.setEnabled(true);
                tfDesc.setEditable(true);
                cbCidade.setEnabled(true);
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
                    Short tem_dedo_escopo;
                    if (cbDedo.isSelected()) {
                        tem_dedo_escopo = (short) 1;
                    } else {
                        tem_dedo_escopo = (short) 0;
                    }
                    if (acao.equals("alterar")) {
                        place.setIdplace(Integer.valueOf(tfIdplace.getText()));
                        place.setNomePlace(tfNome_place.getText());
                        place.setDescPlace(tfDesc.getText());
                        place.setTemDedo(tem_dedo_escopo);

                        Cidade selecionado = daoCidade.obter(Integer.valueOf(String.valueOf(cbCidade.getSelectedItem()).split("-")[0]));
                        place.setCidadeIdcidade(selecionado);

                        daoPlace.atualizar(place);
                    } else { //acao == adicionar
                        place = new Place();
                        place.setIdplace(Integer.valueOf(tfIdplace.getText()));
                        place.setNomePlace(tfNome_place.getText());
                        place.setDescPlace(tfDesc.getText());
                        place.setTemDedo(tem_dedo_escopo);

                        Cidade selecionado = daoCidade.obter(Integer.valueOf(String.valueOf(cbCidade.getSelectedItem()).split("-")[0]));
                        place.setCidadeIdcidade(selecionado);

                        daoPlace.inserir(place);
                    }
                    btSalvar.setVisible(false);
                    btCancelar.setVisible(false);
                    btBuscar.setVisible(true);
                    btListar.setVisible(true);
                    tfIdplace.setEnabled(true);
                    tfIdplace.setEditable(true);
                    tfIdplace.requestFocus();
                    tfIdplace.setText("");
                    tfNome_place.setText("");
                    tfNome_place.setEditable(false);
                    cbCidade.setEnabled(false);
                    tfDesc.setEditable(false);
                    tfDesc.setText("");
                    cbDedo.setEnabled(false);
                    cbDedo.setSelected(false);
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
                tfIdplace.setEditable(false);
                tfNome_place.requestFocus();
                tfNome_place.setEditable(true);
                cbDedo.setEnabled(true);
                tfDesc.setEditable(true);
                cbCidade.setEnabled(true);
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
                tfIdplace.setEditable(false);
                tfIdplace.requestFocus();
                tfIdplace.setText("");
                tfIdplace.setEditable(true);
                tfNome_place.setText("");
                tfNome_place.setEditable(false);
                cbDedo.setEnabled(false);
                tfDesc.setEditable(false);
                cbCidade.setEnabled(false);
                btAlterar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    daoPlace.remover(place);
                }
            }
        });

//listener listar
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Place> listaPlace = daoPlace.listInOrderNome();
                String[] colunas = new String[]{"id", "Nome", "Descrição", "Tem Dedo?", "Cidade"};
                String[][] dados = new String[listaPlace.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaPlace.size(); i++) {
                    aux = listaPlace.get(i).toString().split(";");
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
                tfIdplace.setText("");
                tfIdplace.requestFocus();
                tfIdplace.setEnabled(true);
                tfIdplace.setEditable(true);
                tfNome_place.setText("");
                tfIdplace.setText("");
                tfNome_place.setText("");
                tfNome_place.setEditable(false);
                cbCidade.setEnabled(false);
                tfDesc.setEditable(false);
                tfDesc.setText("");
                cbDedo.setEnabled(false);
                cbDedo.setSelected(false);
                tfNome_place.setEditable(false);
                cbDedo.setEnabled(false);
                tfDesc.setEditable(false);
                cbCidade.setEnabled(false);
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
