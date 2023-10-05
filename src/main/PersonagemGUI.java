package main;

import DAOs.DAOArma;
import DAOs.DAOCla;
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
import DAOs.DAOFiliacao;
import DAOs.DAOGrade;
import DAOs.DAOJujutsu;
import DAOs.DAOPersonagem;
import DAOs.DAOPersonagemHasCla;
import DAOs.DAORaca;
import Entidades.Arma;
import Entidades.Cla;
import Entidades.Filiacao;
import Entidades.Grade;
import Entidades.Jujutsu;
import Entidades.Personagem;
import Entidades.PersonagemHasCla;
import Entidades.PersonagemHasClaPK;
import Entidades.Raca;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import javax.swing.JComboBox;
import tools.DateTextField;

/**
 *
 * @author Mateus Batichotti Silva | 19/04/2023 - 15:55:43
 */
public class PersonagemGUI extends JDialog {

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

    Personagem personagem = new Personagem();

    private CardLayout cardLayout;////////////////////////////////// - MUTÁVEL - //////////////////////////////////
//pk
    JLabel lbId = new JLabel("ID");
    JTextField tfId = new JTextField(30);

    JLabel lbNome = new JLabel("Nome");
    JTextField tfNome = new JTextField(30);

    JLabel lbAltura = new JLabel("Altura");
    JTextField tfAltura = new JTextField(30);

    JLabel lbPeso = new JLabel("Peso");
    JTextField tfPeso = new JTextField(30);

    JLabel lbNascimento = new JLabel("Nascimento");
    JTextField tfNascimento = new DateTextField();

    JLabel lbRaca = new JLabel("Raça");
    JPanel pnRaca = new JPanel();
    JButton btRaca = new JButton("+");
    DAORaca daoRaca = new DAORaca();
    List<Raca> racas = daoRaca.listInOrderNome();
    Raca raca = new Raca();
    JComboBox cbRaca = new JComboBox();

    JLabel lbFiliacao = new JLabel("Filiação");
    JPanel pnFiliacao = new JPanel();
    JButton btFiliacao = new JButton("+");
    DAOFiliacao daoFiliacao = new DAOFiliacao();
    List<Filiacao> filiacoes = daoFiliacao.listInOrderNome();
    Filiacao filiacao = new Filiacao();
    JComboBox cbFiliacao = new JComboBox();

    JLabel lbCla1 = new JLabel("Clãs");
    JPanel pnCla1 = new JPanel();
    JPanel pnCla2 = new JPanel();
    JButton btCla = new JButton("+");
    DAOCla daoCla = new DAOCla();
    List<Cla> clas = daoCla.listInOrderNome();
    Cla cla = new Cla();
    JComboBox cbCla1 = new JComboBox();
    JComboBox cbCla2 = new JComboBox();

    JLabel lbJujutsu = new JLabel("Jujutsu");
    JPanel pnJujutsu = new JPanel();
    JButton btJujutsu = new JButton("+");
    DAOJujutsu daoJujutsu = new DAOJujutsu();
    List<Jujutsu> jujutsus = daoJujutsu.listInOrderNome();
    Jujutsu jujutsu = new Jujutsu();
    JComboBox cbJujutsu = new JComboBox();

    JLabel lbGrade = new JLabel("Grade");
    JPanel pnGrade = new JPanel();
    JButton btGrade = new JButton("+");
    DAOGrade daoGrade = new DAOGrade();
    List<Grade> grades = daoGrade.listInOrderNome();
    Grade grade = new Grade();
    JComboBox cbGrade = new JComboBox();

    JLabel lbArma = new JLabel("Arma");
    JPanel pnArma = new JPanel();
    JButton btArma = new JButton("+");
    DAOArma daoArma = new DAOArma();
    List<Arma> armas = daoArma.listInOrderNome();
    Arma arma = new Arma();
    JComboBox cbArma = new JComboBox();

    DAOPersonagem daoPersonagem = new DAOPersonagem();

    String[] colunas = new String[]{"id", "Name", "idade", "altura", "peso", "nascimento"};
    String[][] dados = new String[0][colunas.length];
    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    //n:m
    DAOPersonagemHasCla daoPHC = new DAOPersonagemHasCla();
    List<PersonagemHasCla> lista_pks = daoPHC.listInOrderNome();

    public PersonagemGUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Personagem");

        //Add combo box
        for (Raca raca_es : racas) {
            String ditto = raca_es.toString().split(";")[0] + " - " + raca_es.toString().split(";")[1];
            cbRaca.addItem(ditto);
        }

        //Add combo box
        for (Cla cla_es : clas) {
            String ditto = cla_es.toString().split(";")[0] + " - " + cla_es.toString().split(";")[1];
            cbCla1.addItem(ditto);
            cbCla2.addItem(ditto);
        }

        //Add combo box
        for (Filiacao filiacao_es : filiacoes) {
            String ditto = filiacao_es.toString().split(";")[0] + " - " + filiacao_es.toString().split(";")[1];
            cbFiliacao.addItem(ditto);
        }

        //Add combo box
        for (Jujutsu jujutsu_es : jujutsus) {
            String ditto = jujutsu_es.toString().split(";")[0] + " - " + jujutsu_es.toString().split(";")[1];
            cbJujutsu.addItem(ditto);
        }

        //Add combo box
        for (Grade grade_es : grades) {
            String ditto = grade_es.toString().split(";")[0] + " - " + grade_es.toString().split(";")[1];
            cbGrade.addItem(ditto);
        }

        //Add combo box
        for (Arma arma_es : armas) {
            String ditto = arma_es.toString().split(";")[0] + " - " + arma_es.toString().split(";")[1];
            cbArma.addItem(ditto);
        }

        //buttons
        btArma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArmaGUI armaGUI = new ArmaGUI();
                dispose();
            }
        });

        btFiliacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiliacaoGUI filiacaoGUI = new FiliacaoGUI();
                dispose();
            }
        });

        btGrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GradeGUI gradeGUI = new GradeGUI();
                dispose();
            }
        });

        btCla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClaGUI claGUI = new ClaGUI();
                dispose();
            }
        });

        btJujutsu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JujutsuGUI jjtGUI = new JujutsuGUI();
                dispose();
            }
        });

        btRaca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RacaGUI racaGUI = new RacaGUI();
                dispose();
            }
        });

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.setBackground(Color.GRAY);
        pnCentro.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnNorte.add(lbId);
        pnNorte.add(tfId);

        pnNorte.add(btBuscar);
        pnNorte.add(btAdicionar);
        pnNorte.add(btAlterar);
        pnNorte.add(btExcluir);
        pnNorte.add(btListar);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);

        lbId.setVisible(true);
        tfId.setVisible(true);
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

        btArma.setBackground(Color.WHITE);
        btFiliacao.setBackground(Color.WHITE);
        btGrade.setBackground(Color.WHITE);
        btJujutsu.setBackground(Color.WHITE);
        btRaca.setBackground(Color.WHITE);
        btCla.setBackground(Color.WHITE);

        pnCentro.setLayout(new GridLayout(colunas.length - 1, 2));

        pnCentro.add(lbNome);
        pnCentro.add(tfNome);
        tfNome.setEditable(false);

        pnCentro.add(pnCla2);
        pnCla2.setLayout(new BorderLayout());
        pnCla2.add(lbCla1, BorderLayout.WEST);
        pnCla2.add(btCla, BorderLayout.EAST);
        pnCentro.add(pnCla1);
        pnCla1.add(cbCla1);
        cbCla1.setEditable(false);
        pnCla1.add(cbCla2);
        cbCla2.setEditable(false);

        pnCentro.add(lbAltura);
        pnCentro.add(tfAltura);
        tfAltura.setEditable(false);

        pnCentro.add(lbPeso);
        pnCentro.add(tfPeso);
        tfPeso.setEditable(false);

        pnCentro.add(lbNascimento);
        pnCentro.add(tfNascimento);
        tfNascimento.setEditable(false);

        //cbs
        pnRaca.setLayout(new BorderLayout());
        pnCentro.add(pnRaca);
        pnCentro.add(cbRaca);

        pnFiliacao.setLayout(new BorderLayout());
        pnCentro.add(pnFiliacao);
        pnCentro.add(cbFiliacao);

        pnJujutsu.setLayout(new BorderLayout());
        pnCentro.add(pnJujutsu);
        pnCentro.add(cbJujutsu);

        pnGrade.setLayout(new BorderLayout());
        pnCentro.add(pnGrade);
        pnCentro.add(cbGrade);

        pnArma.setLayout(new BorderLayout());
        pnCentro.add(pnArma);
        pnCentro.add(cbArma);

        //configurar paineis
        pnRaca.add(lbRaca, BorderLayout.WEST);
        pnRaca.add(btRaca, BorderLayout.EAST);

        pnFiliacao.add(lbFiliacao, BorderLayout.WEST);
        pnFiliacao.add(btFiliacao, BorderLayout.EAST);

        pnJujutsu.add(lbJujutsu, BorderLayout.WEST);
        pnJujutsu.add(btJujutsu, BorderLayout.EAST);

        pnGrade.add(lbGrade, BorderLayout.WEST);
        pnGrade.add(btGrade, BorderLayout.EAST);

        pnArma.add(lbArma, BorderLayout.WEST);
        pnArma.add(btArma, BorderLayout.EAST);

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
        tfId.setEditable(true);
        tfNome.setText("");
        tfNome.setEditable(false);

        cbRaca.setEnabled(false);
        cbFiliacao.setEnabled(false);
        cbJujutsu.setEnabled(false);
        cbGrade.setEnabled(false);
        cbArma.setEnabled(false);
        cbCla1.setEnabled(false);
        cbCla2.setEnabled(false);

//listeners
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cardLayout.show(pnSul, "avisos");
                    personagem = daoPersonagem.obter(Integer.valueOf(tfId.getText()));
                    if (personagem != null) {//achou o cidade na lista
                        //mostrar
                        btAdicionar.setVisible(false);
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        btCancelar.setVisible(true);

                        tfNome.setText(personagem.getNomePersonagem());
                        tfNome.setEditable(false);

                        int apoo = 0;
                        String[] escopo_ditto = null;
                        for (PersonagemHasCla pk : lista_pks) {
                            if (pk.getPersonagemHasClaPK().getPersonagemIdpersonagem() == Integer.parseInt(tfId.getText())) {
                                if (apoo == 0) {
                                    escopo_ditto = daoCla.obter(pk.getPersonagemHasClaPK().getClaIdcla()).toString().split(";");
                                    cbCla1.setSelectedItem(String.valueOf(escopo_ditto[0] + " - " + escopo_ditto[1]));
                                    apoo++;
                                } else if (apoo == 1) {
                                    escopo_ditto = daoCla.obter(pk.getPersonagemHasClaPK().getClaIdcla()).toString().split(";");
                                    cbCla2.setSelectedItem(String.valueOf(escopo_ditto[0] + " - " + escopo_ditto[1]));
                                    break;
                                }
                            }
                        }

                        cbCla1.setEnabled(false);
                        cbCla2.setEnabled(false);

                        tfAltura.setText(String.valueOf(personagem.getAltura()));
                        tfAltura.setEditable(false);

                        tfPeso.setText(String.valueOf(personagem.getPeso()));
                        tfPeso.setEditable(false);

                        tfNascimento.setText(converteDatas.converteDeDateParaString(personagem.getNascimento()));
                        tfNascimento.setEditable(false);

                        cbRaca.setSelectedItem(personagem.getRacaIdraca().toString().split(";")[0] + " - " + personagem.getRacaIdraca().toString().split(";")[1]);
                        cbRaca.setEnabled(false);

                        cbFiliacao.setSelectedItem(personagem.getFiliacaoIdfiliacao().toString().split(";")[0] + " - " + personagem.getFiliacaoIdfiliacao().toString().split(";")[1]);
                        cbFiliacao.setEnabled(false);

                        cbJujutsu.setSelectedItem(personagem.getJujutsuIdjujutsu().toString().split(";")[0] + " - " + personagem.getJujutsuIdjujutsu().toString().split(";")[1]);
                        cbJujutsu.setEnabled(false);

                        cbGrade.setSelectedItem(personagem.getGradeIdgrade().toString().split(";")[0] + " - " + personagem.getGradeIdgrade().toString().split(";")[1]);
                        cbGrade.setEnabled(false);

                        cbArma.setSelectedItem(personagem.getArmaIdarma().toString().split(";")[0] + " - " + personagem.getArmaIdarma().toString().split(";")[1]);
                        cbArma.setEnabled(false);
                    } else {//não achou na lista
                        //mostrar botão incluir

                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfId.setEditable(true);

                        tfNome.setText("");
                        tfNome.setEditable(false);
                        tfAltura.setText("");
                        tfAltura.setEditable(false);
                        tfPeso.setText("");
                        tfPeso.setEditable(false);
                        tfNascimento.setText("");
                        tfNascimento.setEditable(false);

                        cbRaca.setEnabled(false);
                        cbFiliacao.setEnabled(false);
                        cbJujutsu.setEnabled(false);
                        cbGrade.setEnabled(false);
                        cbArma.setEnabled(false);
                        cbCla1.setEnabled(false);
                        cbCla2.setEnabled(false);
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
                tfId.setEnabled(false);
                tfId.setEditable(true);

                tfNome.requestFocus();
                tfNome.setEditable(true);

                tfAltura.setEditable(true);
                tfPeso.setEditable(true);
                tfNascimento.setEditable(true);

                cbRaca.setEnabled(true);
                cbFiliacao.setEnabled(true);
                cbJujutsu.setEnabled(true);
                cbGrade.setEnabled(true);
                cbArma.setEnabled(true);
                cbCla1.setEnabled(true);
                cbCla2.setEnabled(true);

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
                        personagem.setIdpersonagem(Integer.valueOf(tfId.getText()));
                        personagem.setNomePersonagem(tfNome.getText());
                        personagem.setAltura(Double.valueOf(tfAltura.getText()));
                        personagem.setPeso(Integer.valueOf(tfPeso.getText()));
                        personagem.setNascimento(converteDatas.converteDeStringParaDate(tfNascimento.getText()));

                        Date dataAtual = new Date();
                        SimpleDateFormat formatoAno = new SimpleDateFormat("yyyy");
                        String anoString = formatoAno.format(dataAtual);
                        int anoAtual = Integer.parseInt(anoString);
                        personagem.setIdade(anoAtual - Integer.parseInt(tfNascimento.getText().split("/")[2]));

                        Raca selecionado = daoRaca.obter(Integer.valueOf(String.valueOf(cbRaca.getSelectedItem()).split(" - ")[0]));
                        personagem.setRacaIdraca(selecionado);

                        Filiacao selecionado1 = daoFiliacao.obter(Integer.valueOf(String.valueOf(cbFiliacao.getSelectedItem()).split(" - ")[0]));
                        personagem.setFiliacaoIdfiliacao(selecionado1);

                        Jujutsu selecionado2 = daoJujutsu.obter(Integer.valueOf(String.valueOf(cbJujutsu.getSelectedItem()).split(" - ")[0]));
                        personagem.setJujutsuIdjujutsu(selecionado2);

                        Grade selecionado5 = daoGrade.obter(Integer.valueOf(String.valueOf(cbGrade.getSelectedItem()).split(" - ")[0]));
                        personagem.setGradeIdgrade(selecionado5);

                        Arma selecionado6 = daoArma.obter(Integer.valueOf(String.valueOf(cbArma.getSelectedItem()).split(" - ")[0]));
                        personagem.setArmaIdarma(selecionado6);

                        daoPersonagem.atualizar(personagem);

                        //Cla
                        PersonagemHasCla tchau = new PersonagemHasCla();
                        int apoo = 0;
                        Cla thechosenone = null;
                        for (PersonagemHasCla pk : lista_pks) {
                            if (pk.getPersonagemHasClaPK().getPersonagemIdpersonagem() == Integer.parseInt(tfId.getText())) {
                                if (apoo == 0) {
                                    thechosenone = daoCla.obter(pk.getPersonagemHasClaPK().getClaIdcla());
                                    PersonagemHasClaPK vaiserdeletado = new PersonagemHasClaPK();
                                    vaiserdeletado.setClaIdcla(thechosenone.getIdcla());
                                    vaiserdeletado.setPersonagemIdpersonagem(Integer.parseInt(tfId.getText()));
                                    tchau = daoPHC.obter(vaiserdeletado);
                                    daoPHC.remover(tchau);
                                    apoo++;
                                } else if (apoo == 1) {
                                    thechosenone = daoCla.obter(pk.getPersonagemHasClaPK().getClaIdcla());
                                    PersonagemHasClaPK vaiserdeletado = new PersonagemHasClaPK();
                                    vaiserdeletado.setClaIdcla(thechosenone.getIdcla());
                                    vaiserdeletado.setPersonagemIdpersonagem(Integer.parseInt(tfId.getText()));
                                    tchau = daoPHC.obter(vaiserdeletado);
                                    daoPHC.remover(tchau);
                                    break;
                                }
                            }
                        }
                        Cla selecionado7 = daoCla.obter(Integer.valueOf(String.valueOf(cbCla1.getSelectedItem()).split(" - ")[0]));
                        PersonagemHasClaPK vaiseradicionado = new PersonagemHasClaPK();
                        vaiseradicionado.setClaIdcla(selecionado7.getIdcla());
                        vaiseradicionado.setPersonagemIdpersonagem(Integer.valueOf(tfId.getText()));
                        tchau = daoPHC.obter(vaiseradicionado);
                        if (tchau == null) {
                            tchau = new PersonagemHasCla();
                            tchau.setPersonagemHasClaPK(vaiseradicionado);
                            tchau.setEscopo("adicionado pelo programa");
                        }
                        daoPHC.inserir(tchau);

                        Cla selecionado8 = daoCla.obter(Integer.valueOf(String.valueOf(cbCla2.getSelectedItem()).split(" - ")[0]));
                        if (selecionado8.getIdcla() == selecionado7.getIdcla()) {
                            selecionado8 = daoCla.obter(7);
                        }
                        vaiseradicionado = new PersonagemHasClaPK();
                        vaiseradicionado.setClaIdcla(selecionado8.getIdcla());
                        vaiseradicionado.setPersonagemIdpersonagem(Integer.valueOf(tfId.getText()));
                        tchau = daoPHC.obter(vaiseradicionado);
                        if (tchau == null) {
                            tchau = new PersonagemHasCla();
                            tchau.setPersonagemHasClaPK(vaiseradicionado);
                            tchau.setEscopo("adicionado pelo programa");
                        }
                        daoPHC.inserir(tchau);

                    } else { //acao == adicionar
                        personagem = new Personagem();
                        personagem.setIdpersonagem(Integer.valueOf(tfId.getText()));
                        personagem.setNomePersonagem(tfNome.getText());
                        personagem.setAltura(Double.valueOf(tfAltura.getText()));
                        personagem.setPeso(Integer.valueOf(tfPeso.getText()));
                        personagem.setNascimento(converteDatas.converteDeStringParaDate(tfNascimento.getText()));

                        Date dataAtual = new Date();
                        SimpleDateFormat formatoAno = new SimpleDateFormat("yyyy");
                        String anoString = formatoAno.format(dataAtual);
                        int anoAtual = Integer.parseInt(anoString);
                        personagem.setIdade(anoAtual - Integer.parseInt(tfNascimento.getText().split("/")[2]));
                        
                        Raca selecionado = daoRaca.obter(Integer.valueOf(String.valueOf(cbRaca.getSelectedItem()).split(" - ")[0]));
                        personagem.setRacaIdraca(selecionado);

                        Filiacao selecionado1 = daoFiliacao.obter(Integer.valueOf(String.valueOf(cbFiliacao.getSelectedItem()).split(" - ")[0]));
                        personagem.setFiliacaoIdfiliacao(selecionado1);

                        Jujutsu selecionado2 = daoJujutsu.obter(Integer.valueOf(String.valueOf(cbJujutsu.getSelectedItem()).split(" - ")[0]));
                        personagem.setJujutsuIdjujutsu(selecionado2);

                        Grade selecionado5 = daoGrade.obter(Integer.valueOf(String.valueOf(cbGrade.getSelectedItem()).split(" - ")[0]));
                        personagem.setGradeIdgrade(selecionado5);

                        Arma selecionado6 = daoArma.obter(Integer.valueOf(String.valueOf(cbArma.getSelectedItem()).split(" - ")[0]));
                        personagem.setArmaIdarma(selecionado6);

                        daoPersonagem.inserir(personagem);

                        //Cla
                        Cla selecionado7 = daoCla.obter(Integer.valueOf(String.valueOf(cbCla1.getSelectedItem()).split(" - ")[0]));
                        PersonagemHasClaPK vaiseradicionado = new PersonagemHasClaPK();
                        vaiseradicionado.setClaIdcla(selecionado7.getIdcla());
                        vaiseradicionado.setPersonagemIdpersonagem(Integer.parseInt(tfId.getText()));
                        PersonagemHasCla tchau = daoPHC.obter(vaiseradicionado);
                        if (tchau == null) {
                            tchau = new PersonagemHasCla();
                            tchau.setPersonagemHasClaPK(vaiseradicionado);
                            tchau.setEscopo("adicionado pelo programa");
                        }
                        daoPHC.inserir(tchau);

                        Cla selecionado8 = daoCla.obter(Integer.valueOf(String.valueOf(cbCla2.getSelectedItem()).split(" - ")[0]));
                        if (selecionado8.getIdcla() == selecionado7.getIdcla()) {
                            selecionado8 = daoCla.obter(7);
                        }
                        vaiseradicionado = new PersonagemHasClaPK();
                        vaiseradicionado.setClaIdcla(selecionado8.getIdcla());
                        vaiseradicionado.setPersonagemIdpersonagem(Integer.parseInt(tfId.getText()));
                        tchau = daoPHC.obter(vaiseradicionado);
                        if (tchau == null) {
                            tchau = new PersonagemHasCla();
                            tchau.setPersonagemHasClaPK(vaiseradicionado);
                            tchau.setEscopo("adicionado pelo programa");
                        }
                        daoPHC.inserir(tchau);
                    }
                    btSalvar.setVisible(false);
                    btCancelar.setVisible(false);
                    btBuscar.setVisible(true);
                    btListar.setVisible(true);

                    tfId.setEnabled(true);
                    tfId.setEditable(true);
                    tfId.requestFocus();
                    tfId.setText("");

                    tfNome.setText("");
                    tfNome.setEditable(false);

                    tfAltura.setText("");
                    tfAltura.setEditable(false);

                    tfPeso.setText("");
                    tfPeso.setEditable(false);

                    tfNascimento.setText("");
                    tfNascimento.setEditable(false);

                    cbRaca.setEnabled(false);
                    cbFiliacao.setEnabled(false);
                    cbJujutsu.setEnabled(false);
                    cbGrade.setEnabled(false);
                    cbArma.setEnabled(false);
                    cbCla1.setEnabled(false);
                    cbCla2.setEnabled(false);
                    
                    dispose();
                } catch (Exception macau1) {
                    JOptionPane.showMessageDialog(null, "Algo deu errado ao salvar: " + macau1);
                    System.out.println(macau1);
                }
            }
        });

//listener Alterar
        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btBuscar.setVisible(false);
                btAlterar.setVisible(false);
                tfId.setEditable(false);

                tfNome.requestFocus();
                tfNome.setEditable(true);
                tfAltura.setEditable(true);
                tfPeso.setEditable(true);
                tfNascimento.setEditable(true);

                cbRaca.setEnabled(true);
                cbFiliacao.setEnabled(true);
                cbJujutsu.setEnabled(true);
                cbGrade.setEnabled(true);
                cbArma.setEnabled(true);
                cbCla1.setEnabled(true);
                cbCla2.setEnabled(true);

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
                tfId.setEditable(false);
                tfId.requestFocus();
                personagem = daoPersonagem.obter(Integer.valueOf(tfId.getText()));
                tfId.setText("");
                tfId.setEditable(true);

                tfNome.setText("");
                tfNome.setEditable(false);

                tfAltura.setText("");
                tfAltura.setEditable(false);

                tfPeso.setText("");
                tfPeso.setEditable(false);

                tfNascimento.setText("");
                tfNascimento.setEditable(false);

                cbRaca.setEnabled(false);
                cbFiliacao.setEnabled(false);
                cbJujutsu.setEnabled(false);
                cbGrade.setEnabled(false);
                cbArma.setEnabled(false);
                cbCla1.setEnabled(false);
                cbCla2.setEnabled(false);

                btAlterar.setVisible(false);
                btCancelar.setVisible(false);
                if (response == JOptionPane.YES_OPTION) {
                    PersonagemHasCla phc = new PersonagemHasCla();
                    for (PersonagemHasCla pk: lista_pks) {
                        if (pk.getPersonagemHasClaPK().getPersonagemIdpersonagem() == personagem.getIdpersonagem()) {
                            PersonagemHasClaPK alguemmemata = new PersonagemHasClaPK();
                            alguemmemata.setClaIdcla(pk.getPersonagemHasClaPK().getClaIdcla());
                            alguemmemata.setPersonagemIdpersonagem(pk.getPersonagemHasClaPK().getPersonagemIdpersonagem());
                            phc = daoPHC.obter(alguemmemata);
                            if (!(phc == null)) {
                                daoPHC.remover(phc);
                            }
                        }
                    }
                    
                    daoPersonagem.remover(personagem);
                }
            }
        });

//listener listar
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Personagem> listaCidade = daoPersonagem.listInOrderNome();
                String[] colunas = new String[]{"id", "Name", "idade", "altura", "peso", "nascimento"};
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
                tfId.setText("");
                tfId.requestFocus();
                tfId.setEnabled(true);
                tfId.setEditable(true);

                tfNome.setText("");
                tfNome.setEditable(false);

                tfAltura.setText("");
                tfAltura.setEditable(false);

                tfPeso.setText("");
                tfPeso.setEditable(false);

                tfNascimento.setText("");
                tfNascimento.setEditable(false);

                cbRaca.setEnabled(false);
                cbFiliacao.setEnabled(false);
                cbJujutsu.setEnabled(false);
                cbGrade.setEnabled(false);
                cbArma.setEnabled(false);
                cbCla1.setEnabled(false);
                cbCla2.setEnabled(false);

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
        setSize(720, 480);
        setResizable(false);
        setLocationRelativeTo(null);//centraliza na tela
        setVisible(true);
    }//fim do construtor de GUI
}//fim da classe
