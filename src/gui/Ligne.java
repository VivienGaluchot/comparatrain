package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import comparaison.Offre;
import comparaison.OffreSegment;
import elements.Gare;
import elements.GareHoraire;
import elements.Horaire;
import elements.Ville;

public class Ligne extends JPanel{
	
	private PanneauGare Départ;
	private PanneauGare Arrivée;
	
	
	Color[] couleur = {new Color(7019114),new Color(7771648),new Color(16747652),new Color(14366112),new Color(9174374)};
	Offre offre;
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		Stroke s = new BasicStroke(3.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER);
		g2.setStroke(s);
		
		Double height =  this.getHeight()/2.;
		Double longeur = (double) this.getWidth();
		LocalDateTime Td = offre.getDepart().horaire.getTime();
		LocalDateTime Ta = offre.getArrivee().horaire.getTime();
		Double echelle = longeur/(Td.until(Ta,ChronoUnit.SECONDS));
		

		g2.drawString(offre.getDepart().gare.getNom(), 4,this.getHeight()/2 - 4);
		Font font = getFont();
		FontMetrics fm = getFontMetrics(font);
		int sizeChaine = fm.stringWidth(offre.getArrivee().gare.getNom());
		g2.drawString(offre.getArrivee().gare.getNom(), this.getWidth()-sizeChaine-4 ,this.getHeight()/2-4);
		for(OffreSegment o : offre.getOffres()){
			Point2D deb = new Point2D.Double(echelle*(Td.until(o.getDepart().horaire.getTime(),ChronoUnit.SECONDS)),height);
			Point2D fin = new Point2D.Double(echelle*(Td.until(o.getArrivee().horaire.getTime(),ChronoUnit.SECONDS)),height);
			Line2D l = new Line2D.Double(deb, fin);
			Rectangle2D rect1 = new Rectangle2D.Double(deb.getX(), deb.getY()-3, 6, 6);
			Rectangle2D rect2 = new Rectangle2D.Double(fin.getX()-6, fin.getY()-3, 6, 6);
			g2.setColor(couleur[o.getTrain().getId()%5]);
			g2.fill(rect1);
			g2.fill(rect2);
			g2.draw(l);
			
		}
	}
	
	public Ligne (Offre o){
		setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
		Départ = new PanneauGare(o.getDepart().gare,o.getDepart().gare.getVille(),o.getDepart().horaire);
		Arrivée = new PanneauGare(o.getArrivee().gare,o.getArrivee().gare.getVille(),o.getArrivee().horaire);
		offre = o;
		this.setPreferredSize(new Dimension(500,50));
	}
	
	
	
	private class PanneauGare extends GroupPanel{
		private JLabel lblGare = new JLabel();
		private JLabel lblHoraires = new JLabel();
		
		public PanneauGare(Gare g, Ville v, Horaire h){
			super(v.getNom());
			lblGare.setText(g.getNom());
			lblGare.setFont(new javax.swing.plaf.FontUIResource("Arial",Font.PLAIN,12));
			lblHoraires.setText(h.toString());
			lblHoraires.setFont(new javax.swing.plaf.FontUIResource("Arial",Font.PLAIN,12));
			add(lblGare);
			add(lblHoraires);
			
		}
	}

}
