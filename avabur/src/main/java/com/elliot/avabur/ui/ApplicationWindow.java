package com.elliot.avabur.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import org.apache.log4j.Logger;

import com.elliot.avabur.appender.Log4jAppender;
import com.elliot.avabur.application.Application;


public class ApplicationWindow {

    private JFrame frame;
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private JComboBox harvestingComboBox;
    private JRadioButton combatRadio;
    private JComboBox browserComboBox;
    private JComboBox questComboBox;
    private JComboBox monsterComboBox;
    
    private Thread gameThread;
    private Application application;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    ApplicationWindow window = new ApplicationWindow();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public ApplicationWindow() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame("Avabur bot");
        frame.setBounds(100, 100, 531, 359);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        usernameTextField = new JTextField();
        usernameTextField.setText("tapoo");
        usernameTextField.setToolTipText("");
        usernameTextField.setBounds(73, 11, 125, 20);
        frame.getContentPane().add(usernameTextField);
        usernameTextField.setColumns(10);
        
        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 14, 42, 14);
        frame.getContentPane().add(userLabel);
        
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 45, 61, 14);
        frame.getContentPane().add(passwordLabel);
        
        passwordTextField = new JPasswordField();
        passwordTextField.setText("plokij123");
        passwordTextField.setBounds(73, 42, 125, 20);
        frame.getContentPane().add(passwordTextField);
      
        monsterComboBox = new JComboBox();
        monsterComboBox.setBounds(73, 104, 125, 20);
        frame.getContentPane().add(monsterComboBox);
        monsterComboBox.addItem("Giant Ant");
        monsterComboBox.addItem("Giant Wasp");
        monsterComboBox.addItem("Scurrying Rat");
        monsterComboBox.addItem("Forest Spider");
        monsterComboBox.addItem("Amarok");
        monsterComboBox.addItem("Gigantic Beetle");
        monsterComboBox.addItem("Towering Treant");
        monsterComboBox.addItem("Leech");
        monsterComboBox.addItem("Forest Gnome");
        monsterComboBox.addItem("Forest Troll");
        monsterComboBox.addItem("Pinebrush");
        monsterComboBox.addItem("Timid Snail");
        monsterComboBox.addItem("Razorbeast");
        monsterComboBox.addItem("Centaur");
        monsterComboBox.addItem("Ibex");
        monsterComboBox.addItem("Green Glob");
        monsterComboBox.addItem("Locust Swarm");
        monsterComboBox.addItem("Kehra");
        monsterComboBox.addItem("Plague Brush");
        monsterComboBox.addItem("Vexling");
        monsterComboBox.addItem("Stonesinger");
        monsterComboBox.addItem("Pink Glob");
        monsterComboBox.addItem("Tangleclaw");
        monsterComboBox.addItem("Dracotaur");
        monsterComboBox.addItem("Wood Woad");
        monsterComboBox.addItem("Chelicerata");
        monsterComboBox.addItem("Red-Shelled Snail");
        monsterComboBox.addItem("Mighty Dryad");
        monsterComboBox.addItem("Pseudodragon");
        monsterComboBox.addItem("Tree Guardian");
        monsterComboBox.addItem("Fog Serpent");
        monsterComboBox.addItem("Ethereal Toad");
        monsterComboBox.addItem("Flying Mist");
        monsterComboBox.addItem("Giant Gar Pike");
        monsterComboBox.addItem("Giant Crocodile");
        monsterComboBox.addItem("Harpy Archer");
        monsterComboBox.addItem("Manticore");
        monsterComboBox.addItem("Mirescreen");
        monsterComboBox.addItem("Four-Headed Hydra");
        monsterComboBox.addItem("Swamp Troll");
        monsterComboBox.addItem("Gray Ooze");
        monsterComboBox.addItem("Green Hag");
        monsterComboBox.addItem("Smogbody");
        monsterComboBox.addItem("Muck Lizard");
        monsterComboBox.addItem("Marsh Giant");
        monsterComboBox.addItem("Black Ooze");
        monsterComboBox.addItem("Yellow Marsh Dragon");
        monsterComboBox.addItem("Mistscreamer");
        monsterComboBox.addItem("Black Viper");
        monsterComboBox.addItem("Gythrinal");
        monsterComboBox.addItem("Smog Golem");
        monsterComboBox.addItem("Black Marsh Dragon");
        monsterComboBox.addItem("Medusa");
        monsterComboBox.addItem("Swamp Thing");
        monsterComboBox.addItem("Smoke Mirage");
        monsterComboBox.addItem("Underwater Mold");
        monsterComboBox.addItem("Dawnseeker");
        monsterComboBox.addItem("Vapormorph");
        monsterComboBox.addItem("Mud Dragon");
        monsterComboBox.addItem("Tides of Marsh");

        
        JLabel monsterLabel = new JLabel("Monster");
        monsterLabel.setBounds(10, 107, 61, 14);
        frame.getContentPane().add(monsterLabel);
       
        questComboBox = new JComboBox();
        questComboBox.setBounds(73, 135, 125, 20);
        frame.getContentPane().add(questComboBox);
        questComboBox.addItem("Giant Ant");
        questComboBox.addItem("Giant Wasp");
        questComboBox.addItem("Scurrying Rat");
        questComboBox.addItem("Forest Spider");
        questComboBox.addItem("Amarok");
        questComboBox.addItem("Gigantic Beetle");
        questComboBox.addItem("Towering Treant");
        questComboBox.addItem("Leech");
        questComboBox.addItem("Forest Gnome");
        questComboBox.addItem("Forest Troll");
        questComboBox.addItem("Pinebrush");
        questComboBox.addItem("Timid Snail");
        questComboBox.addItem("Razorbeast");
        questComboBox.addItem("Centaur");
        questComboBox.addItem("Ibex");
        questComboBox.addItem("Green Glob");
        questComboBox.addItem("Locust Swarm");
        questComboBox.addItem("Kehra");
        questComboBox.addItem("Plague Brush");
        questComboBox.addItem("Vexling");
        questComboBox.addItem("Stonesinger");
        questComboBox.addItem("Pink Glob");
        questComboBox.addItem("Tangleclaw");
        questComboBox.addItem("Dracotaur");
        questComboBox.addItem("Wood Woad");
        questComboBox.addItem("Chelicerata");
        questComboBox.addItem("Red-Shelled Snail");
        questComboBox.addItem("Mighty Dryad");
        questComboBox.addItem("Pseudodragon");
        questComboBox.addItem("Tree Guardian");
        questComboBox.addItem("Fog Serpent");
        questComboBox.addItem("Ethereal Toad");
        questComboBox.addItem("Flying Mist");
        questComboBox.addItem("Giant Gar Pike");
        questComboBox.addItem("Giant Crocodile");
        questComboBox.addItem("Harpy Archer");
        questComboBox.addItem("Manticore");
        questComboBox.addItem("Mirescreen");
        questComboBox.addItem("Four-Headed Hydra");
        questComboBox.addItem("Swamp Troll");
        questComboBox.addItem("Gray Ooze");
        questComboBox.addItem("Green Hag");
        questComboBox.addItem("Smogbody");
        questComboBox.addItem("Muck Lizard");
        questComboBox.addItem("Marsh Giant");
        questComboBox.addItem("Black Ooze");
        questComboBox.addItem("Yellow Marsh Dragon");
        questComboBox.addItem("Mistscreamer");
        questComboBox.addItem("Black Viper");
        questComboBox.addItem("Gythrinal");
        questComboBox.addItem("Smog Golem");
        questComboBox.addItem("Black Marsh Dragon");
        questComboBox.addItem("Medusa");
        questComboBox.addItem("Swamp Thing");
        questComboBox.addItem("Smoke Mirage");
        questComboBox.addItem("Underwater Mold");
        questComboBox.addItem("Dawnseeker");
        questComboBox.addItem("Vapormorph");
        questComboBox.addItem("Mud Dragon");
        questComboBox.addItem("Tides of Marsh");

       
        browserComboBox= new JComboBox();
        browserComboBox.setBounds(275, 11, 125, 20);
        frame.getContentPane().add(browserComboBox);
        browserComboBox.addItem("Chrome");
        browserComboBox.addItem("Internet Explorer");
        
        
        JLabel questLabel = new JLabel("Quest");
        questLabel.setBounds(10, 138, 61, 14);
        frame.getContentPane().add(questLabel);
        

        
        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if(gameThread != null && application != null) {
                    
                    try {
                        application.stop();
                        gameThread.join();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        stopButton.setBounds(358, 130, 125, 23);
        frame.getContentPane().add(stopButton);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 198, 495, 115);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        frame.getContentPane().add(scrollPane);
        
        JTextArea loggerTextArea = new JTextArea();
        scrollPane.setViewportView(loggerTextArea);
        Logger.getRootLogger().addAppender(new Log4jAppender(loggerTextArea));
        
        
        JButton resetButton = new JButton("Reset Combat");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(application != null) {
                    application.requestReset();
                }
            }
        });
        resetButton.setBounds(230, 164, 116, 23);
        frame.getContentPane().add(resetButton);
 
        JLabel browserLabel = new JLabel("Browser");
        browserLabel.setBounds(220, 14, 61, 14);
        frame.getContentPane().add(browserLabel);
        

        ButtonGroup jobGroup = new ButtonGroup();
        combatRadio = new JRadioButton("Combat");
        combatRadio.setSelected(true);
        combatRadio.setBounds(6, 74, 81, 23);
        frame.getContentPane().add(combatRadio);
        jobGroup.add(combatRadio);
        
        JRadioButton harvestingRadio = new JRadioButton("Harvesting");
        harvestingRadio.setBounds(89, 74, 94, 23);
        frame.getContentPane().add(harvestingRadio);
        jobGroup.add(harvestingRadio);
        
        harvestingComboBox = new JComboBox();
        harvestingComboBox.setBounds(73, 167, 125, 20);
        frame.getContentPane().add(harvestingComboBox);
        harvestingComboBox.addItem("Wood");
        harvestingComboBox.addItem("Fish");
        harvestingComboBox.addItem("Iron");
        harvestingComboBox.addItem("Stone");
        harvestingComboBox.setEnabled(false);
        
        JLabel harvestingLabel = new JLabel("Harvesting");
        harvestingLabel.setBounds(10, 170, 61, 14);
        frame.getContentPane().add(harvestingLabel);

        
        harvestingRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JRadioButton button = (JRadioButton) e.getSource();

                if (button.isSelected()){
                    monsterComboBox.setEnabled(false);
                    questComboBox.setEnabled(false);
                    harvestingComboBox.setEnabled(true);
                }
            }
        });
        
        combatRadio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JRadioButton button = (JRadioButton) e.getSource();

                if (button.isSelected()){
                    monsterComboBox.setEnabled(true);
                    questComboBox.setEnabled(true);
                    harvestingComboBox.setEnabled(false);
                }
            }
        });
        
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if(application == null) {
                    application = new Application();
                    
                }
                
                setApplicationValues();
                
                if(gameThread == null) {
                    gameThread = new Thread(application);
                }
                gameThread.start();
            }
        });
        
        startButton.setBounds(230, 130, 116, 23);
        frame.getContentPane().add(startButton);
        
        JButton changeObjectiveButton = new JButton("Change objective");
        changeObjectiveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setApplicationValues();
            }
        });
        

        changeObjectiveButton.setBounds(358, 164, 125, 23);
        frame.getContentPane().add(changeObjectiveButton);
        
    }
    
    private void setApplicationValues() {
        if(application != null) {
            application.setUsername(usernameTextField.getText());
            application.setPassword(new String(passwordTextField.getPassword()));
            application.setQuest(questComboBox.getSelectedItem().toString());
            application.setMonster(monsterComboBox.getSelectedItem().toString());
            application.setBrowser(browserComboBox.getSelectedItem().toString());
            application.setHarvesting(harvestingComboBox.getSelectedItem().toString());
            application.setCombat(combatRadio.isSelected()); 
        }

    }
   
}
