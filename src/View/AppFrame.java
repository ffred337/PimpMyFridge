package View;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AppFrame extends JFrame implements ActionListener {
    private final ControlPanel controlPanel = new ControlPanel();
    private final Button mainButton = new Button("WELCOME",Color.decode("#515099"),Color.white, 75, 200);
    private final Button pmfButton = new Button("MY FRIDGE",Color.decode("#515099"),Color.white, 75, 300);
    private final Button crdButton = new Button("CREDITS",Color.decode("#515099"),Color.white, 75, 400);
    private final MainPanel mainPanel = new MainPanel();
    private final CrdPanel crdPanel = new CrdPanel();
    private final AppPanel appPanel = new AppPanel();
    private final DataPanel dataPanel = new DataPanel();
    private final Button nextButton = new Button("NEXT",Color.decode("#515099"),Color.white, 755, 625, 100,50);
    private final Button prevButton = new Button("PREV",Color.darkGray,Color.white, 25, 625, 100,50);
    private final Button refreshButton = new Button("REFRESH",Color.decode("#7b4397"),Color.white, 1055, 625, 100,50);


    private ArrayList<Button> buttons = new ArrayList<Button>(){{add(mainButton); add(pmfButton); add(crdButton);add(prevButton);add(nextButton);add(refreshButton);}};
    public AppFrame(){
        this.setTitle("Pimp My Fridge");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBounds(0,0,1280,720);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        controlPanel.add(mainButton);
        controlPanel.add(pmfButton);
        controlPanel.add(crdButton);

        buttons.add(appPanel.getAddButton());
        buttons.add(appPanel.getRemButton());

        appPanel.add(nextButton);
        dataPanel.add(prevButton);
        dataPanel.add(refreshButton);

        this.add(mainPanel);
        this.add(crdPanel);
        this.add(appPanel);
        this.add(controlPanel);
        this.add(dataPanel);
        for(Button bt :buttons){
            bt.addActionListener(this);
        }
        appBuildFrame("main");
        this.setVisible(true);
        this.appPanel.startGameThread();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == mainButton){
            appBuildFrame("main");
        } else if (e.getSource() == pmfButton) {
            appBuildFrame("pmf");
        } else if (e.getSource() == crdButton) {
            appBuildFrame("crd");
        } else if (e.getSource() == nextButton) {
            appBuildFrame("data");
        } else if (e.getSource() == prevButton) {
            appBuildFrame("pmf");
        }
        else if (e.getSource() == appPanel.getSwitchButton()) {
            System.out.println("OKOK");
        }
        else if (e.getSource() == refreshButton) {
            this.dataPanel.refresh();
        }
    }

    private void appBuildFrame(String state){
        if(state == "main"){
            mainPanel.setVisible(true);
            controlPanel.setVisible(true);
            crdPanel.setVisible(false);
            appPanel.setVisible(false);
            dataPanel.setVisible(false);
            mainButton.OnCliked();
            pmfButton.OnRealeased();
            crdButton.OnRealeased();
        } else if (state == "pmf") {
            appPanel.setVisible(true);
            controlPanel.setVisible(true);
            mainPanel.setVisible(false);
            crdPanel.setVisible(false);
            dataPanel.setVisible(false);
            mainButton.OnRealeased();
            pmfButton.OnCliked();
            crdButton.OnRealeased();

        } else if (state == "crd") {
            crdPanel.setVisible(true);
            controlPanel.setVisible(true);
            mainPanel.setVisible(false);
            appPanel.setVisible(false);
            dataPanel.setVisible(false);
            mainButton.OnRealeased();
            pmfButton.OnRealeased();
            crdButton.OnCliked();
        } else if (state == "data") {
            dataPanel.setVisible(true);
            this.dataPanel.refresh();
            controlPanel.setVisible(false);
            mainPanel.setVisible(false);
            appPanel.setVisible(false);
            crdPanel.setVisible(false);
            mainButton.OnRealeased();
            pmfButton.OnCliked();
            crdButton.OnRealeased();
        }
    }
}
