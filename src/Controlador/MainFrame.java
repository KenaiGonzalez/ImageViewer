package Controlador;

import Persistencia.FileImageLoader;
import UI.ImageDisplay;
import UI.SwingImageDisplay;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
    private ImageDisplay imageDisplay;
    private final FileImageLoader fil;

    public MainFrame(FileImageLoader fil){
        super("ImageViewer");
        this.fil = fil;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(800,500);
        this.setLocationRelativeTo(null);
        this.getContentPane().add(toolBar(), BorderLayout.SOUTH);
        this.getContentPane().add(imageDisplay(), BorderLayout.CENTER);
        
        this.setVisible(true);
    }
    
    
    private JPanel toolBar(){
        JPanel toolBar = new JPanel();
        toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        toolBar.add(prevButton());
        toolBar.add(nextButton());
        return toolBar;
    }
    
    private Button nextButton(){
        Button next = new Button(">");
        next.setPreferredSize(new Dimension(100, 50));
        next.setFont(new Font("Arial Black",Font.BOLD,30));
        next.setForeground(Color.green);
        next.addActionListener((ActionEvent e) -> {
            imageDisplay.show(fil.next());
        });
        return next;
    }
    
    private Button prevButton(){
        Button prev = new Button("<");
        prev.setPreferredSize(new Dimension(100, 50));
        prev.setFont(new Font("Arial Black",Font.BOLD,30));
        prev.setForeground(Color.red);
        prev.addActionListener((ActionEvent e) -> {
            imageDisplay.show(fil.prev());
        });
        return prev;
    }
    
    private JPanel imageDisplay(){
        SwingImageDisplay sid = new SwingImageDisplay();
        sid.setSize(500, 300);
        this.imageDisplay = sid;
        return sid;
    }

    public ImageDisplay getImageDisplay() {
        return imageDisplay;
    }
}
