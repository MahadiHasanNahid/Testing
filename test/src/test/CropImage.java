/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class CropImage extends JFrame implements MouseListener, MouseMotionListener {

    int drag_status = 0, c1, c2, c3, c4;
    int w,h;
    String filepath = "C:\\Users\\MdMahadiHasan\\Pictures\\Screenshots\\Screenshot (1).png";

    public static void main(String args[]) throws IOException {
        new CropImage().start();
    }

    public void start() throws IOException {
        Image image = ImageIO.read(new File(filepath));       
        Image scaledImage = image.getScaledInstance(1000,700,Image.SCALE_SMOOTH);
        ImagePanel im = new ImagePanel(scaledImage);
        add(im);
        setSize(1000, 700);
        setVisible(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void draggedScreen() throws Exception {
        w = c1 - c3;
        h = c2 - c4;
        w = w * -1;
        h = h * -1;
        
        createCropImage(c1,c2, w, h);
        
    }
    
    public void createCropImage(int c1,int c2,int w,int h) throws AWTException, IOException{
        Robot robot = new Robot();
        BufferedImage img = robot.createScreenCapture(new Rectangle(c1, c2, w, h));
        File save_path = new File("screen2.jpg");
        ImageIO.write(img, "JPG", save_path);
        System.out.println("Cropped image saved successfully.");
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        repaint();
        c1 = arg0.getX();
        c2 = arg0.getY();
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        repaint();
        if (drag_status == 1) {
            c3 = arg0.getX();
            c4 = arg0.getY();
            try {
                draggedScreen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
        repaint();
        drag_status = 1;
        c3 = arg0.getX();
        c4 = arg0.getY();
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {

    }

    public void paint(Graphics g) {
        super.paint(g);
        int w = c1 - c3;
        int h = c2 - c4;
        w = w * -1;
        h = h * -1;
        if (w < 0) {
            w = w * -1;
        }
        g.drawRect(c1, c2, w, h);
    }
}
