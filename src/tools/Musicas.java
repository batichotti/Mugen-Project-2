package tools;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Musicas {

    public void playBGM() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        try {
            File file = new File("D:\\Mateus Cohuzer\\Desktop\\Geral\\Java\\Mugen_Project\\src\\res\\musics\\start.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            
            clip.start();
            
            file = new File("D:\\Mateus Cohuzer\\Desktop\\Geral\\Java\\Mugen_Project\\src\\res\\musics\\bgm.wav");
            audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.start();
            clip.loop(-1);
        } catch (Exception e) {
            System.out.println("Archive not founded (music)");
        }
    }
    
    public void playBGM2() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        try {
            File file = new File("D:\\Mateus Cohuzer\\Desktop\\Geral\\Java\\Mugen_Project\\src\\res\\musics\\crud_bgm.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            
            clip.start();
        } catch (Exception e) {
            System.out.println("Archive not founded (music)");
        }
    }
    
    public void playSound(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        try {
            File file = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.start();
        } catch (Exception e) {
            System.out.println("Archive not founded (music)");
        }
    }

    public void playBGM3() {
        try {
            File file = new File("D:\\Mateus Cohuzer\\Desktop\\Geral\\Java\\Mugen_Project\\src\\res\\musics\\stop.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            
            clip.start();
        } catch (Exception e) {
            System.out.println("Archive not founded (music)");
        }
    }
}
