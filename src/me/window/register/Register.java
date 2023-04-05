package me.window.register;

import me.window.Window;
import me.window.panel.GaleryPanel;
import me.window.users.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.InputStream;


public class Register extends JPanel {
    public Window WINDOW;
    public GaleryPanel GP;
    public JLabel IMAGE_LABEL;
    public JButton REGISTER_BUTTON;
    public JTextField USERNAME_FIELD;
    public JLabel TITLE_LABEL, DESC_LABEL;
    public JPanel TOP_PANEL, BOTTOM_PANEL;
    public JLabel CHANGE_SCENE;
    public JPasswordField PASSWORD_FIELD, REPASSWORD_FIELD;

    public Register(Window WINDOW, GaleryPanel GP) throws Exception {
        this.GP = GP;
        this.WINDOW = WINDOW;
        InputStream in = getClass().getClassLoader().getResourceAsStream("res/logo-RAU-ro-1.png");
        ///////////////////////////////////////////////////////////////////////////////////////////////
        this.setBackground(Color.black);
        this.setBorder(new EmptyBorder(20, 20, 20, 20));
        BufferedImage image = ImageIO.read(in);
        ///////////////////////////////////////
        this.TOP_PANEL = new JPanel();
        this.BOTTOM_PANEL = new JPanel();
        this.BOTTOM_PANEL.setOpaque(false);
        this.BOTTOM_PANEL.setPreferredSize(new Dimension(480, 400));
        this.TOP_PANEL.setOpaque(false);
        this.TOP_PANEL.setLayout(new BoxLayout(this.TOP_PANEL, BoxLayout.Y_AXIS));
        this.TOP_PANEL.setPreferredSize(new Dimension(480, 200));
        this.IMAGE_LABEL = new JLabel();
        this.IMAGE_LABEL.setIcon(new ImageIcon(image));
        this.IMAGE_LABEL.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.DESC_LABEL = new JLabel("Java VS JavaFX");
        this.DESC_LABEL.setForeground(Color.white);
        this.DESC_LABEL.setFont(new Font(Font.SERIF, 0, 23));
        this.DESC_LABEL.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.TITLE_LABEL = new JLabel("Sesiunea de comunicari Stintifice");
        this.TITLE_LABEL.setForeground(Color.white);
        this.TITLE_LABEL.setFont(new Font(Font.SERIF, 0, 23));
        this.TITLE_LABEL.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.USERNAME_FIELD = new JTextField();
        this.USERNAME_FIELD.setColumns(35);
        this.USERNAME_FIELD.setToolTipText("Enter your email address...");
        this.USERNAME_FIELD.setPreferredSize(new Dimension(400, 45));
        this.PASSWORD_FIELD = new JPasswordField();
        this.PASSWORD_FIELD.setColumns(35);
        this.PASSWORD_FIELD.setAlignmentX(0.1f);
        this.PASSWORD_FIELD.setToolTipText("Enter your password...");
        this.PASSWORD_FIELD.setPreferredSize(new Dimension(400, 45));
        this.REPASSWORD_FIELD = new JPasswordField();
        this.REPASSWORD_FIELD.setColumns(35);
        this.REPASSWORD_FIELD.setAlignmentX(0.1f);
        this.REPASSWORD_FIELD.setToolTipText("Re-enter your password...");
        this.REPASSWORD_FIELD.setPreferredSize(new Dimension(400, 45));
        this.REGISTER_BUTTON = new JButton("REGISTER");
        this.REGISTER_BUTTON.setFocusPainted(false);
        this.REGISTER_BUTTON.setBorderPainted(false);
        this.REGISTER_BUTTON.setBackground(Color.orange);
        this.REGISTER_BUTTON.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
        this.REGISTER_BUTTON.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                executeRegister();
            }
        });
        this.CHANGE_SCENE = new JLabel();
        this.CHANGE_SCENE.setForeground(Color.lightGray);
        this.CHANGE_SCENE.setFont(new Font(Font.SERIF, 0, 24));
        this.CHANGE_SCENE.setBorder(new EmptyBorder(20, 0,0,0));
        this.CHANGE_SCENE.setText("You already have an account? Login here");
        this.CHANGE_SCENE.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                CHANGE_SCENE.setFont(new Font(Font.SERIF, 1, 23));
                CHANGE_SCENE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseEntered(e);
                CHANGE_SCENE.setFont(new Font(Font.SERIF, 0, 23));
                CHANGE_SCENE.setCursor(Cursor.getDefaultCursor());
            }
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                changeScene();
            }
        });
        this.TOP_PANEL.add(this.IMAGE_LABEL);
        this.TOP_PANEL.add(this.TITLE_LABEL);
        this.TOP_PANEL.add(this.DESC_LABEL);
        this.BOTTOM_PANEL.add(createLabelDesc("Your Email:"));
        this.BOTTOM_PANEL.add(this.USERNAME_FIELD);
        this.BOTTOM_PANEL.add(createLabelDesc("Password:"));
        this.BOTTOM_PANEL.add(this.PASSWORD_FIELD);
        this.BOTTOM_PANEL.add(createLabelDesc("Re-Password:"));
        this.BOTTOM_PANEL.add(this.REPASSWORD_FIELD);
        this.BOTTOM_PANEL.add(this.REGISTER_BUTTON);
        this.BOTTOM_PANEL.add(this.CHANGE_SCENE);
        /////////////////////////////////////////
        this.add(this.TOP_PANEL);
        this.add(this.BOTTOM_PANEL);
        //this.WINDOW.MAIN_CONTAINER.add(this);
    }

    private JLabel createLabelDesc(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.white);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setFont(new Font(Font.DIALOG, 0, 20));
        return label;
    }

    private void changeScene() {
        this.USERNAME_FIELD.setText("");
        this.PASSWORD_FIELD.setText("");
        this.REPASSWORD_FIELD.setText("");
        this.WINDOW.MAIN_CONTAINER.remove(this);
        this.WINDOW.MAIN_CONTAINER.add(this.WINDOW.LOGIN_PANEL);
        this.WINDOW.repaint();
        this.WINDOW.validate();
        this.WINDOW.requestFocusInWindow();
    }

    private void executeRegister() {
        String email = USERNAME_FIELD.getText();
        String repassword = String.valueOf(REPASSWORD_FIELD.getPassword());
        String password = String.valueOf(PASSWORD_FIELD.getPassword());
        ///////////////////////////////////////////////////////////////
        if (email == null || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You must specify the email address");
            return;
        }
        if (!isValidEmailAddress(email)) {
            JOptionPane.showMessageDialog(null, "Invalid email address, provide a correct email!");
            return;
        }
        if (password == null || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You must specify your password");
            return;
        }
        if (repassword == null || repassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You must specify your password");
            return;
        }
        if (!password.equals(repassword)) {
            JOptionPane.showMessageDialog(null, "Passwords must match");
            return;
        }
        User USER = new User(email, password);
        if (this.WINDOW.USERS.exists(USER)) {
            JOptionPane.showMessageDialog(null, "User with the email address '" + email + "' already exists.");
            return;
        }
        changeScene();
        this.WINDOW.USERS.register(USER);
        JOptionPane.showMessageDialog(null, "Successufully registered account with the email '" + email + "', now you must login");
        this.WINDOW.repaint();
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Point point1 = new Point(10, 10);
        Point point2 = new Point(
                getWidth() - 10,
                getHeight() - 10);
        final GradientPaint gp = new GradientPaint(
                point2, Color.darkGray,
                point1, Color.black,
                true);
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(gp);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
