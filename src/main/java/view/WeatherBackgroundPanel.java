package view;

import javax.swing.*;
import java.awt.*;

public class WeatherBackgroundPanel extends JPanel {
    private final Image background;

    public WeatherBackgroundPanel(Image background)
    {
        this.background = background;
        setLayout( new BorderLayout() );
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
