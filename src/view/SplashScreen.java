package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class SplashScreen extends JPanel {

    private int cloud1x = -200;
    private int cloud2x = 800;
    private int angle = 45;

    public SplashScreen() {
        Timer timer = new Timer(30, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cloud1x += 2;
                if (cloud1x > getWidth())
                    cloud1x = -200;

                cloud2x -= 2;
                if (cloud2x < -200)
                    cloud2x = getWidth();

                angle += 5;

                if (angle > 180)
                    angle = 0;

                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawBackground(g2d);

        drawSchool(g2d);

        drawCloud(g2d, cloud1x, 100);
        drawCloud(g2d, cloud2x, 150);

        drawFlag(g2d);

        drawSun(g2d, angle);

        studentImage(g2d, 255, 260);
        studentImage(g2d, 505, 260);

    }

    private void drawBackground(Graphics2D g2d) {
        g2d.setColor(new Color(135, 206, 235));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // dessiner le sol
        g2d.setColor(new Color(60, 179, 113));
        g2d.fillRect(0, getHeight() - 150, getWidth(), 150);
    }

    private void drawSchool(Graphics2D g2d) {

        g2d.setColor(new Color(255, 228, 181));
        g2d.fillRect(200, 200, 400, 250);

        g2d.setColor(Color.GRAY);
        g2d.fillPolygon(new int[] { 180, 620, 400 }, new int[] { 200, 200, 100 }, 3);

        // Portes
        g2d.setColor(new Color(139, 69, 19));
        g2d.fillRect(350, 300, 100, 150);

        // fenêtres
        g2d.setColor(Color.WHITE);
        g2d.fillRect(250, 250, 60, 60);
        g2d.fillRect(500, 250, 60, 60);
    }

    private void drawCloud(Graphics2D g2d, int x, int y) {
        g2d.setColor(Color.WHITE);
        g2d.fillOval(x, y, 80, 50);
        g2d.fillOval(x + 30, y - 20, 80, 50);
        g2d.fillOval(x + 60, y, 80, 50);
    }

    private void drawFlag(Graphics2D g2d) {

        g2d.setColor(Color.BLACK);
        g2d.fillRect(200, 100, 5, 100);

        g2d.setColor(Color.RED);
        g2d.fillRect(205, 100, 100, 50);

        // Définition des points de l’étoile
        int[] xPoints = { 255, 259, 270, 261, 264, 255, 246, 249, 240, 251 };
        int[] yPoints = { 110, 119, 119, 126, 137, 129, 137, 126, 119, 119 };

        // Dessin de l'étoile
        g2d.setColor(Color.GREEN);
        g2d.fillPolygon(xPoints, yPoints, xPoints.length);

        g2d.setColor(Color.GREEN);
        g2d.drawPolygon(xPoints, yPoints, xPoints.length);

    }

    private void drawSun(Graphics2D g2d, int angle) {
        // Save the current transformation to restore it later
        AffineTransform originalTransform = g2d.getTransform();

        // Translate the origin to the center of the sun before rotating
        g2d.translate(40, 40); // Translate to the sun's center
        g2d.rotate(Math.toRadians(angle)); // Rotate by the specified angle

        // Couleur du soleil
        g2d.setColor(Color.YELLOW);

        // Centre et rayon du soleil
        int radius = 50;

        // Dessiner le cercle central (soleil)
        g2d.fillOval(-radius, -radius, 2 * radius, 2 * radius);

        // Dessiner les rayons du soleil
        g2d.setStroke(new BasicStroke(3)); // Épaisseur des rayons
        for (int i = 0; i < 12; i++) {
            double angle1 = Math.toRadians(i * 30); // Espacés de 30° (360° / 12)
            int xStart = (int) (radius * Math.cos(angle1));
            int yStart = (int) (radius * Math.sin(angle1));
            int xEnd = (int) ((radius + 20) * Math.cos(angle1));
            int yEnd = (int) ((radius + 20) * Math.sin(angle1));
            g2d.drawLine(xStart, yStart, xEnd, yEnd);
        }

        // Restore the original transformation to avoid affecting other drawings
        g2d.setTransform(originalTransform);
    }

    public void splashScreen() {
        JWindow splash = new JWindow();

        JPanel content = new SplashScreen();
        content.setSize(800, 400);

        splash.setSize(800, 600);
        splash.setContentPane(content);
        splash.setLocationRelativeTo(null);
        splash.setVisible(true);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        splash.setVisible(false);
        splash.dispose();
    }

    private void studentImage(Graphics2D g2d, int x, int y) {
        // Load the image in the constructor
        try {
            Image studentImage = ImageIO.read(new File("src/res/student.png"));
            if (studentImage != null) {
                g2d.drawImage(studentImage, x, y, 50, 50, this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
