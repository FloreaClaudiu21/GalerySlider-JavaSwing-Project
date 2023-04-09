package me.window.login;

import me.window.Window;
import me.window.panel.GaleryPanel;
import me.window.users.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;


public class Login extends JPanel {
    public Window WINDOW;
    public GaleryPanel GP;
    public JLabel IMAGE_LABEL;
    public JButton LOGIN_BUTTON;
    public JTextField USERNAME_FIELD;
    public JLabel TITLE_LABEL, DESC_LABEL;
    public JPanel TOP_PANEL, BOTTOM_PANEL;
    public JPasswordField PASSWORD_FIELD;
    public JLabel CHANGE_SCENE;

    public Login(Window WINDOW, GaleryPanel GP) throws Exception {
        this.GP = GP;
        this.WINDOW = WINDOW;
        GridLayout gl = new GridLayout(2, 1);
        InputStream in = getClass().getClassLoader().getResourceAsStream("res/logo-RAU-ro-1.png");
        ///////////////////////////////////////////////////////////////////////////////////////////////
        gl.setVgap(10);
        this.setLayout(gl);
        this.setBackground(Color.black);
        this.setBorder(new EmptyBorder(20, 20, 20, 20));
        BufferedImage image = ImageIO.read(in);
        ///////////////////////////////////////
        this.TOP_PANEL = new JPanel();
        this.BOTTOM_PANEL = new JPanel();
        this.BOTTOM_PANEL.setOpaque(false);
        this.BOTTOM_PANEL.setPreferredSize(new Dimension(400, 300));
        this.TOP_PANEL.setOpaque(false);
        this.TOP_PANEL.setLayout(new BoxLayout(this.TOP_PANEL, BoxLayout.Y_AXIS));
        this.TOP_PANEL.setPreferredSize(new Dimension(400, 100));
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
        this.LOGIN_BUTTON = new JButton("LOGIN");
        this.LOGIN_BUTTON.setFocusPainted(false);
        this.LOGIN_BUTTON.setBorderPainted(false);
        this.LOGIN_BUTTON.setBackground(Color.orange);
        this.LOGIN_BUTTON.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
        this.LOGIN_BUTTON.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                executeLogin();
            }
        });
        this.CHANGE_SCENE = new JLabel();
        this.CHANGE_SCENE.setForeground(Color.lightGray);
        this.CHANGE_SCENE.setFont(new Font(Font.SERIF, 0, 22));
        this.CHANGE_SCENE.setBorder(new EmptyBorder(20, 0,0,0));
        this.CHANGE_SCENE.setText("You don't have an account? Register here");
        this.CHANGE_SCENE.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                CHANGE_SCENE.setFont(new Font(Font.SERIF, 1, 22));
                CHANGE_SCENE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseEntered(e);
                CHANGE_SCENE.setFont(new Font(Font.SERIF, 0, 22));
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
        this.BOTTOM_PANEL.add(createLabelDesc("Email:"));
        this.BOTTOM_PANEL.add(this.USERNAME_FIELD);
        this.BOTTOM_PANEL.add(createLabelDesc("Password:"));
        this.BOTTOM_PANEL.add(this.PASSWORD_FIELD);
        this.BOTTOM_PANEL.add(this.LOGIN_BUTTON);
        this.BOTTOM_PANEL.add(this.CHANGE_SCENE);
        /////////////////////////////////////////
        this.add(this.TOP_PANEL);
        this.add(this.BOTTOM_PANEL);
        this.WINDOW.MAIN_CONTAINER.add(this);
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
        this.WINDOW.MAIN_CONTAINER.remove(this);
        this.WINDOW.MAIN_CONTAINER.add(this.WINDOW.REGISTER_PANEL);
        this.WINDOW.repaint();
        this.WINDOW.validate();
        this.WINDOW.requestFocusInWindow();
    }
    private void executeLogin() {
        String email = USERNAME_FIELD.getText();
        String password = String.valueOf(PASSWORD_FIELD.getPassword());
        ///////////////////////////////////////////////////////////////
        if  (this.WINDOW.LOADING) {
            JOptionPane.showMessageDialog(null, "Wait for the images to finish loading...");
            return;
        }
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
        User USER = new User(email, password);
        if (!this.WINDOW.USERS.exists(USER)) {
            JOptionPane.showMessageDialog(null, "User with the email address '" + email + "' does not exist.");
            return;
        }
        if (!this.WINDOW.USERS.canLogin(USER)) {
            JOptionPane.showMessageDialog(null, "Password is not valid, please try again!");
            return;
        }
        this.WINDOW.setResizable(true);
        this.WINDOW.MAIN_CONTAINER.add(this.WINDOW.PHOTOS_PANEL.MAIN_CONTAINER, BorderLayout.WEST);
        this.WINDOW.MAIN_CONTAINER.add(this.GP, BorderLayout.CENTER);
        this.WINDOW.MAIN_CONTAINER.remove(this);
        this.WINDOW.setSize(new Dimension(864, 640));
        JOptionPane.showMessageDialog(null, "Successufully logged in as the user '" + email + "', enjoy the application! :)");
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
