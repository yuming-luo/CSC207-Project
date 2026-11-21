package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class WeatherBackgroundPanel extends JPanel {
    private Image background;

    public WeatherBackgroundPanel(String imageName) {
        try {
            this.background = ImageIO.read(new File("images/" + imageName));
            setLayout( new BorderLayout() );
        } catch (IOException e) {
            System.out.println("Image not found");
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.drawImage(background, 0, 0, null);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(background.getWidth(this), background.getHeight(this));
    }
}
