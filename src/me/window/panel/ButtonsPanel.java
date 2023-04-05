package me.window.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonsPanel extends JPanel {
    public JButton nextBtn;
    public JButton prevBtn;
    public JLabel statusLbl;
    public PhotoPanel pp;
    public ButtonsPanel(PhotoPanel pp) {
        this.pp = pp;
        this.setOpaque(true);
        this.setLayout(null);
        this.statusLbl = new JLabel();
        this.statusLbl.setOpaque(false);
        this.nextBtn = new JButton("Next =>");
        this.prevBtn = new JButton("<= Prev");
        this.statusLbl.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        this.setBackground(new Color(0, 0, 0, 70));
        this.setPreferredSize(new Dimension(700, 60));
        pp.add(this, BorderLayout.NORTH);
        this.setupBtn(this.nextBtn);
        this.setupBtn(this.prevBtn);
        this.add(this.nextBtn);
        this.add(this.prevBtn);
        this.add(this.statusLbl);
        this.nextPhoto(this.nextBtn);
        this.prevPhoto(this.prevBtn);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                statusLbl.setBounds(20, 15, 150, 30);
                statusLbl.setText((pp.start_photo + 1) + "/" + (pp.max_photo));
                nextBtn.setBounds(getWidth() / 2 + 10, 20, 100, 30);
                prevBtn.setBounds(getWidth() / 2 - 110, 20, 100, 30);
            }
        });
    }
    private void setupBtn(JButton btn) {
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setForeground(Color.white);
        btn.setBackground(Color.decode("#ff758c"));
        btn.setFont(new Font(Font.SERIF, Font.BOLD, 17));
    }
    private void nextPhoto(JButton btn) {
        btn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                pp.start_photo += 1;
                if (pp.start_photo >= pp.max_photo) {
                    pp.start_photo = 0;
                }
                pp.picture_holder.setIcon(pp.scaleImage(pp.gp.window.IMAGES.get(pp.start_photo)));
                statusLbl.setText((pp.start_photo + 1) + "/" + (pp.max_photo));
                pp.gp.window.repaint();
            }
        });
    }
    private void prevPhoto(JButton btn) {
        btn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                pp.start_photo -= 1;
                if (pp.start_photo < 0) {
                    pp.start_photo = pp.max_photo - 1;
                }
                pp.picture_holder.setIcon(pp.scaleImage(pp.gp.window.IMAGES.get(pp.start_photo)));
                statusLbl.setText((pp.start_photo + 1) + "/" + (pp.max_photo));
                pp.gp.window.repaint();
            }
        });
    }
}
