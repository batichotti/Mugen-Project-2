package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import tools.Musicas;

/**
 *
 * @author Mateus CohuzEr
 */
class CRUD_GUI extends JFrame {

    Container cp;
    Container gp;
    JPanel pnCentro = new JPanel();
    JPanel pnOeste = new JPanel();

    JLabel lbOeste = new JLabel();
    JLabel lbCentro = new JLabel();

    JButton btArma = new JButton("Arma");
    JButton btCla = new JButton("Clã");
    JButton btCorCabelo = new JButton("Cor do Cabelo");
    JButton btCorOlhos = new JButton("Cor dos olhos");
    JButton btFiliacao = new JButton("Filiação");
    JButton btGrade = new JButton("Grade");
    JButton btJujutsu = new JButton("Jujutsu");
    JButton btPais = new JButton("País");
    JButton btPlayer = new JButton("Player");
    JButton btCidade = new JButton("Cidade");
    JButton btRaca = new JButton("Raça");
    JButton btExit = new JButton("Exit");

    Musicas musicas = new Musicas();

    public CRUD_GUI() throws UnsupportedAudioFileException, IOException, LineUnavailableException  {

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        //setDefaultCloseOperation(EXIT_ON_CLOSE);

        cp.add(pnOeste, BorderLayout.WEST);
        cp.add(pnCentro, BorderLayout.CENTER);
        pnCentro.add(lbCentro);

        try {
            ImageIcon icone = new ImageIcon(getClass().getResource("../res/imgs/StartScreen_Yuta_MugenProject.jpg"));
            Image imagemAux;
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(1100, 950, Image.SCALE_FAST));

            lbCentro.setIcon(icone);
        } catch (Exception e) {
            System.out.println("Erro ao carregar a imagem");
        }

        btArma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArmaGUI armaGUI = new ArmaGUI();
            }
        });

        btCla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClaGUI claGUI = new ClaGUI();
            }
        });

        btCorCabelo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cor_CabeloGUI ccGUI = new Cor_CabeloGUI();
            }
        });

        btCorOlhos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cor_OlhosGUI coGUI = new Cor_OlhosGUI();
            }
        });

        btFiliacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiliacaoGUI filiacaoGUI = new FiliacaoGUI();
            }
        });

        btGrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GradeGUI gradeGUI = new GradeGUI();
            }
        });

        btJujutsu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JujutsuGUI jjtGUI = new JujutsuGUI();
            }
        });

        btPais.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaisGUI paisGUI = new PaisGUI();
            }
        });

        btRaca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RacaGUI racaGUI = new RacaGUI();
            }
        });
        
        btCidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlaceGUI cidadeGUI = new PlaceGUI();
            }
        });

        //Exit Button
        btExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Do you want to exit the program?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        pnOeste.setLayout(new GridLayout(4, 3));
        pnOeste.add(btArma);
        pnOeste.add(btCla);
        pnOeste.add(btCorCabelo);
        pnOeste.add(btCorOlhos);
        pnOeste.add(btFiliacao);
        pnOeste.add(btFiliacao);
        pnOeste.add(btGrade);
        pnOeste.add(btJujutsu);
        pnOeste.add(btPais);
        pnOeste.add(btRaca);
        pnOeste.add(btPlayer);
        pnOeste.add(btCidade);
        pnOeste.add(btExit);

        btArma.setBackground(Color.WHITE);
        btCla.setBackground(Color.WHITE);
        btCorCabelo.setBackground(Color.WHITE);
        btCorOlhos.setBackground(Color.WHITE);
        btExit.setBackground(Color.WHITE);
        btFiliacao.setBackground(Color.WHITE);
        btGrade.setBackground(Color.WHITE);
        btJujutsu.setBackground(Color.WHITE);
        btPais.setBackground(Color.WHITE);
        btPlayer.setBackground(Color.WHITE);
        btCidade.setBackground(Color.WHITE);
        btRaca.setBackground(Color.WHITE);

        pnCentro.setBackground(Color.WHITE);

        musicas.playBGM2();

        setTitle("Mugen Project");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../res/imgs/icon.png")));
        setSize(1480, 920);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
