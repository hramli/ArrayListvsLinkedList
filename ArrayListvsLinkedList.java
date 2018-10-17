import java.awt.*;
import java.util.*;
import javax.swing.*;

/*
 *This program compares the time taken for ArrayList and LinkedList to add, remove, get, and sort, and displays
 *the result in a bar graph.
 *Used graphics instead of JButton for the bar because I ran into some formatting issues. Changing
 *to graphics solved it.
 */

public class ArrayListVsLinkedList{

	static ArrayList<Integer> arrayList = new ArrayList<>();
    static LinkedList<Integer> linkedList = new LinkedList<>();

    static double timeStart, timeEnd;

    static double timeALadd,timeLLadd,timeALget,timeLLget,timeALremove,timeLLremove,timeALsort,timeLLsort;

  
    static void add() 
    {
        Random rand = new Random();
        for (int i = 0; i < 6000000; i++) 
        {
            int value = rand.nextInt(9999);
            timeStart = System.currentTimeMillis();
            arrayList.add(value);
            timeEnd = System.currentTimeMillis();
            timeALadd += timeEnd - timeStart;

            timeStart = System.currentTimeMillis();
            linkedList.add(value);
            timeEnd = System.currentTimeMillis();
            timeLLadd += timeEnd - timeStart;
        }
        timeALadd /= 1000;
        timeLLadd /= 1000;
        System.out.println("Time taken to add (ArrayList): " + timeALadd);
        System.out.println("Time taken to add (LinkedList): " + timeLLadd);
    }

    static void get() 
    {
        Random rand = new Random();
        int value = rand.nextInt(9999);

        for (int i = 0; i < 1000; i++)
        {
            timeStart = System.currentTimeMillis();
            arrayList.get(value);
            timeEnd = System.currentTimeMillis();
            timeALget += timeEnd - timeStart;

            timeStart = System.currentTimeMillis();
            linkedList.get(value);
            timeEnd = System.currentTimeMillis();
            timeLLget += timeEnd - timeStart;
        }
        timeALget /= 1000;
        timeLLget /= 1000;
        System.out.println("Time taken to get (ArrayList): " + timeALget);
        System.out.println("Time taken to get (LinkedList): " + timeLLget);
    }

    static void remove() 
    {
        Random rand = new Random();

        for (int i = 0; i < 1000; i++) 
        {
            int value = rand.nextInt(9999);
            timeStart = System.currentTimeMillis();
            arrayList.remove(value);
            timeEnd = System.currentTimeMillis();
            timeALremove += timeEnd - timeStart;

            timeStart = System.currentTimeMillis();
            linkedList.remove(value);
            timeEnd = System.currentTimeMillis();
            timeLLremove += timeEnd - timeStart;
        }
        
        timeALremove /= 1000;
        timeLLremove /= 1000;
        System.out.println("Time taken to delete (ArrayList): " + timeALremove);
        System.out.println("Time taken to delete (LinkedList): " + timeLLremove);
    }

    static void sort()
    {
        Random rand = new Random();
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < 6000000; i++) 
        {
            int value = rand.nextInt(9999);
            arrayList.add(value);
            linkedList.add(value);
        }

        timeStart = System.currentTimeMillis();
        Collections.sort(arrayList);
        timeEnd = System.currentTimeMillis();
        timeALsort = timeEnd - timeStart;

        timeStart = System.currentTimeMillis();
        Collections.sort(linkedList);
        timeEnd = System.currentTimeMillis();
        timeLLsort = timeEnd - timeStart;
        
        timeALsort /= 1000;
        timeLLsort /= 1000;

        System.out.println("Time taken to sort (ArrayList): " + timeALsort);
        System.out.println("Time taken to sort (LinkedList): " + timeLLsort);
    }

    public static void main(String[] args) 
    {
        add();
        get();
        remove();
        sort();
        double[] time = {timeALadd,timeLLadd,timeALget,timeLLget,timeALremove,timeLLremove,timeALsort,timeLLsort};
        new barFrame(time);
    }
    
    
}
class barFrame {

	 static final int W=1200,H=800;//width and height of JFrame
	 private JFrame f; 
	 private JPanel p;
	 private JLabel lab0;
	 private JButton barAdd;
     
	 static String[] barTitle = new String[] {"add()","get()","remove()","sort()"} ; 
	 static String mainTitle ="ArrayList vs LinkedList"; 
	 static int timeArrayList = 0;    
	 static int xaxis = 601;
 
	 public barFrame(double time[]){ // constructor
     
		 f=new JFrame("ArrayList vs LinkedList Speed");
		 f.setSize(W, H);
		 f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		 p=new JPanel(){
     
			 public void paintComponent(Graphics g){
			 // Draw what you want to appear on your JPanel here.
			 super.paintComponent(g);
			 setBackground(Color.WHITE);

			 p.setLayout(null);
			
			 lab0 = new JLabel(mainTitle);
			 lab0.setBounds(330,40,W-20,H/6);
			 lab0.setFont(new Font("Serif", Font.PLAIN, 60));
			 p.add(lab0);
	
			 Xaxis(g);
			 
			 NewTitleLabel(barTitle[0],150);
			 NewTitleLabel(barTitle[1],390);
			 NewTitleLabel(barTitle[2],650);
			 NewTitleLabel(barTitle[3],1000);
			 
			 createNewBar(g,100, time[0], Color.DARK_GRAY);
			 createNewBar(g,170, time[1], Color.PINK);
			 createNewBar(g,340, time[2], Color.DARK_GRAY);
			 createNewBar(g,410, time[3], Color.PINK);
			 createNewBar(g,650, time[4], Color.DARK_GRAY);
			 createNewBar(g,720, time[5], Color.PINK);
			 createNewBar(g,960, time[6], Color.DARK_GRAY);
			 createNewBar(g,1030,time[7], Color.PINK);
			 
			 NewBarLabel("AL",122);
			 NewBarLabel("LL",192);
			 NewBarLabel("AL",362);
			 NewBarLabel("LL",432);	
			 NewBarLabel("AL",672);
			 NewBarLabel("LL",742);		
			 NewBarLabel("AL",982);
			 NewBarLabel("LL",1052);
			 
			 NewTimeLabel(time[0],122);
			 NewTimeLabel(time[1],192);
			 NewTimeLabel(time[2],362);
			 NewTimeLabel(time[3],432);
			 NewTimeLabel(time[4],672);
			 NewTimeLabel(time[5],742);
			 NewTimeLabel(time[6],982);
			 NewTimeLabel(time[7],1052);
			}
		 };
		
     	f.add(p);
	    f.setVisible(true);
	    p.setVisible(true);
}
	// create a new bar
	public void createNewBar(Graphics g, int x, double t, Color color)
	{
		g.setColor(color);
		g.fillRect(x, (int)(xaxis-(t*50)), 70, (int)(t*50));
	}
	
	// labeling the bar below the x axis
	public void NewBarLabel(String title, int x)
	{
		lab0 = new JLabel(title);
		lab0.setBounds(x,630,30,20);
		lab0.setFont(new Font("Serif", Font.PLAIN,20));
		lab0.setMinimumSize(new Dimension(100,100));
		p.add(lab0);
	}
	
	// creating title for each pair of bars
	public void NewTitleLabel(String title, int x)
	{
		lab0 = new JLabel(title);
		lab0.setBounds(x,130,130,100);
		lab0.setFont(new Font("Serif", Font.PLAIN, 30));
		lab0.setMinimumSize(new Dimension(100,100));
		p.add(lab0);
	}
	
	// to show the time
	public void NewTimeLabel(double t, int x)
	{
		lab0 = new JLabel(t+" s");
		lab0.setBounds(x,650,30,20);
		lab0.setFont(new Font("Serif", Font.PLAIN,10));
		lab0.setMinimumSize(new Dimension(100,100));
		p.add(lab0);
	}
	
	// drawing the x axis
	public void Xaxis(Graphics g)
	{
		 g.setColor(Color.BLACK);  
		 g.drawLine(100, 600, 1100, 600);
	}
}