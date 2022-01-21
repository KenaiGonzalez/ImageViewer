package Controlador;

import UI.ImageDisplay;
import UI.SwingImageDisplay;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
    private ImageDisplay imageDisplay;

    public MainFrame(){
        super("ImageViewer");
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
        next.setSize(50, 20);
        next.addActionListener((ActionEvent e) -> {
            imageDisplay.show(imageDisplay.current().next());
        });
        return next;
    }
    
    private Button prevButton(){
        Button prev = new Button("<");
        prev.setSize(50, 20);
        prev.addActionListener((ActionEvent e) -> {
            imageDisplay.show(imageDisplay.current().prev());
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
