package me.window.panel;

import me.window.Window;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PhotosPanel extends JPanel {
    public Window WINDOW;
    private final int WIDTH = 250;
    public JScrollPane MAIN_CONTAINER;
    public PhotosPanel(Window W) {
        this.WINDOW = W;
        this.MAIN_CONTAINER = new JScrollPane(this);
        BoxLayout LAYOUT = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.MAIN_CONTAINER.setPreferredSize(new Dimension(WIDTH, Integer.MAX_VALUE));
        //////////////////////////////////////////////////////////////////////////////
        this.setLayout(LAYOUT);
        this.setBackground(Color.BLACK);
        this.setSize(WIDTH, Integer.MAX_VALUE);
        this.MAIN_CONTAINER.setBackground(Color.black);
        this.setMaximumSize(new Dimension(WIDTH, Integer.MAX_VALUE));
        this.setBorder(BorderFactory.createEmptyBorder(30,5,10,5));
    }

    public void init() {
        for (int I = 0 ; I < this.WINDOW.IMAGES.size(); I++) {
            SidePhotoPanel SP = new SidePhotoPanel(I, this.WINDOW.IMAGES.get(I));
            this.add(SP);
            this.add(Box.createVerticalStrut(30));
        }
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        GaleryPanel.gradientBG(g, this);
    }

    private class SidePhotoPanel extends JPanel {
        private int I;
        private Image IMG;
        private final int WIDTH = 200;
        private final int HEIGHT = 200;
        private final JLabel label = new JLabel();
        public SidePhotoPanel(int INDEX, Image IMAGE) {
            this.I = INDEX;
            this.IMG = IMAGE;
            this.setOpaque(false);
            this.setLayout(new BorderLayout());
            this.setSize(this.WIDTH, this.HEIGHT);
            this.setMaximumSize(new Dimension(this.WIDTH, this.HEIGHT));
            this.IMG = this.IMG.getScaledInstance(this.WIDTH, this.HEIGHT, Image.SCALE_SMOOTH);
            this.label.setIcon(new ImageIcon(this.IMG));
            this.label.setToolTipText("Click to choose this image");
            this.label.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    setCursor(Cursor.getDefaultCursor());
                }
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    WINDOW.GP.photoP.buttonsP.choosePhoto(I);
                }
            });
            this.add(label, BorderLayout.CENTER);
        }
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            boolean ACTIVE = PhotosPanel.this.WINDOW.GP.photoP.start_photo == this.I;
            g.setColor(new Color(0,0,0, 60));
            g.fillRect(0, 0, this.label.getBounds().width, this.label.getBounds().height);
            g.setColor(Color.white);
            g.setFont(new Font(Font.SERIF, Font.BOLD, 18));
            g.drawString((I + 1) + "", 15, 40);
            if (ACTIVE) {
                this.label.setBorder(new MatteBorder(2,2,2,2, Color.WHITE));
            } else {
                this.label.setBorder(null);
            }
        }
    }
}
