import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class Simulation extends JPanel implements ActionListener {

    private final static int maxX = 768;
    private final static int maxY = 768;
    private List<Celestial> list;
    private double scale;
    private Graphics g;

    public Simulation(List<Celestial> l, double s) {
        list = l;
        scale = s;
    }

    public void updatePositions() {
    	try {
    		int i;
    		if (list.size() > 1) {
	    		for (i = 0; i < list.size()-1; i++) {
		    		list.get(i).addForce(list.get(i+1), scale);
		    		list.get(i).updatePosition();
		    		list.get(i).resetForce();
		    	}
		    	list.get(i).addForce(list.get(i-1), scale);
	    		list.get(i).updatePosition();
	    		list.get(i).resetForce();
    		} else if (list.size() == 1) {
    			list.get(0).updatePosition();
    		}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Timer tm = new Timer(50, this);
        Random random = new Random();

        try {
            for (int i = 0; i < list.size(); i++){
            	g.setColor(list.get(i).getColor());
                g.fillOval(list.get(i).getX(), list.get(i).getY(), list.get(i).getSize(), list.get(i).getSize());
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

		tm.start();
    }

    public void actionPerformed(ActionEvent e) {
        updatePositions();
        repaint();    
    }

    public static void main(String[] args) throws Exception {
    	List<Celestial> temp = null;
    	double tempScale = 0;
        File data = new File(args[0]);

        try {
        	Scanner reader = new Scanner(data).useDelimiter(",");
			String list_type = reader.nextLine();
        	double scale = Double.parseDouble(reader.nextLine());

	        if (list_type.equalsIgnoreCase("arraylist")) { // check if user wants arraylist or linkedlist
	            temp = new ArrayList<Celestial>();
	        } else if (list_type.equalsIgnoreCase("linkedlist")) {
	            temp = new LinkedList<Celestial>();
	        } else {
	            throw new Exception("Invalid List Type"); // throw exception if otherwise
	        }

        	int i = 0;
        	while (reader.hasNext()) { //instantiate list of bodies
	            temp.add(new Celestial(reader.next(), reader.next(), reader.next(),
	                reader.next(), reader.next(), reader.next(), reader.nextLine()));
        	}
        }
        catch (FileNotFoundException e) {
        	e.printStackTrace();
        }

        Simulation sim = new Simulation(temp, 1000000.0);
        JFrame jf = new JFrame();
        jf.setTitle("N_Bodies");
        jf.setSize(sim.maxX, sim.maxY); // Window size defined in the class
        jf.add(sim); // This appears below "setVisible" in the video
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}


