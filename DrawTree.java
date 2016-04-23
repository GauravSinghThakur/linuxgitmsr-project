package linuxgitmsr;
import javax.swing.JComponent;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class DrawTree extends JComponent{
	
	private CommitTree ct = null;
	private ArrayList<CommitInfo> al = null;
	private String[] count = new String[2];
	private String[] comdates = null;
	private String[] repos = null;
	private Point2D.Double p1 = null;
	private Point2D.Double p2 = null;
	private int[][] comdate_xy = null;
	private int[][] repo_xy = null;
	
	
	
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.red);
		g2d.setFont(new Font("monospace",Font.BOLD,11));
		
		/*
		Color blue = new Color(0,0,255);
		g2d.setColor(blue);
		
		Rectangle rect = new Rectangle(5,10,300,100);
		//g2d.draw(rect);
		g2d.fill(rect);
		
		Ellipse2D.Double elp = new Ellipse2D.Double(100,100,100,100);
		g2d.fill(elp);
		
		Line2D.Double line = new Line2D.Double(550,150,100,50);
		g2d.draw(line);		
		
		Point2D.Double p1 = new Point2D.Double(400,200);
		Point2D.Double p2 = new Point2D.Double(200,500);
		
		Line2D.Double l12 = new Line2D.Double(p1,p2);
		g2d.draw(l12);
		
		g2d.setFont(new Font("arial",Font.BOLD,12));
		g2d.drawString("Repository", 500, 400);
		*/
		
		//Get repos and comdates and commitTree
		ct = new CommitTree();
		count = ct.getRepoComdateCount("5ede3ceb7b2c2843e153a1803edbdc8c56655950");
		comdates = ct.getComdates("5ede3ceb7b2c2843e153a1803edbdc8c56655950");
		repos = ct.getRepos("5ede3ceb7b2c2843e153a1803edbdc8c56655950");
		al = ct.getCommitTree("5ede3ceb7b2c2843e153a1803edbdc8c56655950");
		
		int comdate_count = Integer.parseInt(count[0]);
		int repo_count = Integer.parseInt(count[1]);
		
		comdate_xy = new int[comdate_count][2];
		repo_xy = new int[repo_count][2];
		
		p1 = new Point2D.Double(400,200);
		p2 = new Point2D.Double(200,500);
		
		int x = 2000/comdate_count;
		int y = 1200;
		
		for(int i=0; i<comdate_count; i++){
			comdate_xy[i][0] = 2150 - (i+1)*x;
			comdate_xy[i][1] = y;
			g2d.drawString(comdates[i].substring(2,10), comdate_xy[i][0], comdate_xy[i][1]);			
		}
		
		for(int i=0; i<comdate_count; i++){
			System.out.println(+(i+1)+" "+comdates[i]+" comdate x:"+comdate_xy[i][0]+", comdate y: "+comdate_xy[i][1]);
		}
		
		y = 1000;
		y=y/repo_count;
		for(int i=0; i<repo_count; i++){
			repo_xy[i][0] = 5;
			repo_xy[i][1] = 0 + (i+1)*y;
			g2d.drawString(repos[i], repo_xy[i][0], repo_xy[i][1]);			
		}
	}
}
