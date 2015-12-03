/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author metterlin
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.*;
import static javax.swing.JFrame.*;

/**
 *
 * @author metterlin
 */
public class GameOptionsView {

    JFrame jframeOptions;
    
    JPanel jPanelOptions;
    JPanel jPanelGameMode;
    JPanel jPanelField;
    JPanel jPanelSubmit;
    JLabel jLabelGameMode;
    JLabel jLabelFieldSize;
    JLabel jLabelx;
    JLabel jLabely;
    ButtonGroup bgrpRadioButtons;
    JRadioButton jRadioSingle;
    JRadioButton jRadioMulti;
    JSlider jSliderX;
    JSlider jSliderY;
    JButton jBtnSubmit; 
      
    private static final Dimension size= new Dimension(400,200);
    
    
    public GameOptionsView(){
        jframeOptions=new JFrame("Options Dots and Boxes");
        jPanelOptions= new JPanel();
        jLabelGameMode = new JLabel("Game Mode");
        jLabelFieldSize= new JLabel("Field Size");
        bgrpRadioButtons = new ButtonGroup();
        jRadioSingle = new JRadioButton("SinglePlayer");
        jRadioMulti= new JRadioButton("MultiPlayer");
        jPanelGameMode = new JPanel();
        jPanelField = new JPanel();
        jSliderX = new JSlider(0, 20, 10);
        jSliderY = new JSlider(0,20,10);
        jBtnSubmit = new  JButton("Submit");
        jPanelSubmit = new JPanel();
        
                
        setup();
    }
    public void setup(){
        jframeOptions.setSize(size);
        jframeOptions.setPreferredSize(size);
        jPanelOptions = new JPanel();
        jframeOptions.add(jPanelOptions);
        jframeOptions.setDefaultCloseOperation(EXIT_ON_CLOSE);   
        
        //jPanel Options
        jPanelOptions.setLayout(new BorderLayout());
        jPanelOptions.add(jPanelField, BorderLayout.EAST);
        jPanelOptions.add(jPanelGameMode, BorderLayout.CENTER);
        jPanelOptions.add(jPanelSubmit, BorderLayout.SOUTH);
        
        //Setup JPanel Submit
        jPanelSubmit.add(jBtnSubmit);
               
        //Radio Buttons
        bgrpRadioButtons.add(jRadioSingle);
        bgrpRadioButtons.add(jRadioMulti);
        
        //Setup JPanel GameMode
        jPanelGameMode.setLayout(new GridLayout(3,0));
        jPanelGameMode.add(jLabelGameMode);
        jPanelGameMode.add(jRadioSingle);
        jPanelGameMode.add(jRadioMulti);
         
        //Setup JPanel Field
        jPanelField.setLayout(new GridLayout(3,0));
        jPanelField.add(jLabelFieldSize);
        jPanelField.add(jSliderX);
        jPanelField.add(jSliderY);
        
       
        jframeOptions.setVisible(true);
        
        
    }
    public static void main(String[] args){
        GameOptionsView optionView1 = new GameOptionsView();
    }
}
