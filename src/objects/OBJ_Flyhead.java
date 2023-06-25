package objects;

import javax.imageio.ImageIO;

/**
 *
 * @author Mateus CohuzEr
 */
public class OBJ_Flyhead extends SuperObject {

    public OBJ_Flyhead() {
        name = "Fly Head";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("../res/imgs/objects/flyhead.png"));
        } catch (Exception e) {
            System.out.println("Flyhead obj msg:" + e);
        }
        collision = true;
    }
}
