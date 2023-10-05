package main;

import entity.API_Getter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import org.json.simple.parser.ParseException;
import tools.Musicas;

/**
 *
 * @author Mateus CohuzEr
 */
public class GUI_Bored extends JDialog {

    Container cp;
    API_Getter apiget;
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();

    JLabel lbCentro1 = new JLabel();
    JLabel lbCentro2 = new JLabel();
    Musicas musicas = new Musicas();

    JButton btBored = new JButton("Are you bored?");

    public GUI_Bored() {
    }

    public void start_frame() throws ProtocolException, IOException, MalformedURLException, ParseException {

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        String[] bored = apiget.getHTTP();
        //0 - activity; 1- type; 2- participants; 3- price; 4- link; 5- key; 6- accessibility

        cp.add(pnSul, BorderLayout.SOUTH);
        cp.add(pnCentro, BorderLayout.CENTER);

        //Bored Button
        btBored.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] bored1 = new String[6];
                try {
                    bored1 = apiget.getHTTP();
                } catch (ProtocolException ex) {
                    Logger.getLogger(GUI_Bored.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GUI_Bored.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(GUI_Bored.class.getName()).log(Level.SEVERE, null, ex);
                }

                musicas.playBGM3();
                lbCentro1.setText("<html> <h1>Fly Head: Are you bored?</h1> <h2>- " + bored1[0] + "</h2> <h2>Participants: " + bored1[2] + "   </h2> <h3>Type: " + bored1[1] + "</h3> <h3>Price (0:1) >>> " + bored1[3] + "</h3> </html>");
                pack();
            }
        });

        try {
            ImageIcon icone = new ImageIcon(getClass().getResource("../res/imgs/Flyhead.jpg"));
            Image imagemAux;
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(640, 640, Image.SCALE_FAST));

            lbCentro2.setIcon(icone);
        } catch (Exception e) {
            System.out.println("Erro ao carregar a imagem");
        }
        
        lbCentro1.setText("<html> <h1>Fly Head: Are you bored?</h1> <h2>- " + bored[0] + "</h2> <h2>Participants: " + bored[2] + "</h2> <h3>Type: " + bored[1] + "</h3> <h3>Price (0:1) >>> " + bored[3] + "</h3> </html>");

        pnCentro.setLayout(new BorderLayout());
        pnCentro.add(lbCentro1, BorderLayout.EAST);
        pnCentro.add(lbCentro2, BorderLayout.WEST);

        pnSul.add(btBored);

        btBored.setBackground(Color.WHITE);
        pnCentro.setBackground(Color.WHITE);
        pnSul.setBackground(Color.WHITE);
        pnSul.setBackground(Color.WHITE);
        lbCentro1.setBackground(Color.WHITE);
        lbCentro2.setBackground(Color.WHITE);
        
        musicas.playBGM3();

        setModal(true);
        setTitle("Mugen Project - Are you Bored?");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../res/imgs/icon.png")));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
