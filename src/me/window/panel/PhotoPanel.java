package me.window.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

public class PhotoPanel extends JPanel {
    public GaleryPanel gp;
    public JLabel picture_holder;
    public ButtonsPanel buttonsP;
    public int start_photo = 0;
    public int max_photo = 0;
    public PhotoPanel(GaleryPanel gp) {
        this.gp = gp;
        this.setBorder(null);
        this.setLayout(new BorderLayout());
        this.picture_holder = new JLabel();
        this.picture_holder.setOpaque(false);
        this.buttonsP = new ButtonsPanel(this);
        this.picture_holder.setBackground(Color.BLACK);
        this.add(this.picture_holder, BorderLayout.CENTER);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                picture_holder.setIcon(scaleImage(gp.window.IMAGES.get(start_photo)));
            }
        });
        gp.add(this, BorderLayout.CENTER);
        this.max_photo = gp.window.IMAGES.size();
    }
    public ImageIcon scaleImage(BufferedImage originalImage) {
        Image scaledImage = originalImage.getScaledInstance(this.picture_holder.getWidth(), this.picture_holder.getHeight(), Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
