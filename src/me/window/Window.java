package me.window;

import me.window.login.Login;
import me.window.panel.GaleryPanel;
import me.window.register.Register;
import me.window.users.Users;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Window extends JFrame {
    public JFrame WINDOW;
    public Users USERS;
    public Login LOGIN_PANEL;
    public Register REGISTER_PANEL;
    public GaleryPanel GP;
    public JPanel MAIN_CONTAINER;
    public boolean LOADING = true;
    public List<BufferedImage> IMAGES;

    public Window() throws Exception {
        this.WINDOW = this;
        this.USERS = new Users();
        this.IMAGES = new ArrayList<>();
        this.MAIN_CONTAINER = new JPanel();
        this.GP = new GaleryPanel(this);
        this.MAIN_CONTAINER.setLayout(new GridLayout(1, 1));
        /////////////////////////////////////////////////////////////
        this.setResizable(false);
        this.setAutoRequestFocus(true);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Galery Slider - Java Project");
        this.setSize(new Dimension(480, 640));
        this.setMinimumSize(new Dimension(400, 360));
        this.LOGIN_PANEL = new Login(this, GP);
        this.REGISTER_PANEL = new Register(this, GP);
        this.getContentPane().add(this.MAIN_CONTAINER);
    }

    public void start() {
        this.setVisible(true);
        this.IMAGES = Window.getImagesFromDirectory();
        if (this.IMAGES.size() <= 0) {
            JOptionPane.showMessageDialog(null, "No images have been found in the directory 'images', closing the program...", "Error when starting the program!", JOptionPane.WARNING_MESSAGE);
            return;
        }
        this.GP.photoP.max_photo = this.IMAGES.size();
        JOptionPane.showMessageDialog(null, "Done! Images have been loaded");
        this.LOADING = false;
    }

    public static List<BufferedImage> getImagesFromDirectory() {
        Thread th = null;
        List<BufferedImage> images = new ArrayList<>();
        try {
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            File folder = new File(s + File.separator + "images");
            th = new Thread(() -> {
                JOptionPane.showMessageDialog(null, "Loading images, please wait..", "Program is starting up!", JOptionPane.INFORMATION_MESSAGE);
            });
            th.start();
            for (final File fileEntry : folder.listFiles()) {
                if (!fileEntry.isDirectory()) {
                    BufferedImage img = ImageIO.read(fileEntry);
                    if (img != null) {
                        images.add(img);
                    }
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            th.stop();
        }
        return images;
    }
}
