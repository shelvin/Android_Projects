package com.chess;

import javax.swing.*;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Chess extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int START_X = 25;
	public static final int START_Y = 25;
	public static final int SIZE = 55; //Chess size
	public static final int GAP = 27; // Chess gap
	public static final int UNIT = 57; // Chess board unit size
	public enum Suits {Red, Black};
	private int id = 0;
	private String name = null;
	private String rank = null;
	private Suits suit = null;
	private int index_x = 0;
	private int index_y = 0;
	private Point location = null;
	private int pre_x = 0;
	private int pre_y = 0;
	private String url = null;
	private boolean isChosen = false;
	private boolean isAlive = true;
	//public boolean isMove = false;
	
	Chess(){
		super();
	}
	Chess(String str, int x, int y){
		this.name = new String(str);
		//System.out.println(str);
		String seprator = new String("-");
		String[] sub = str.split(seprator);
		if(sub.length != 2){
			System.out.println("input strname has problem!");
			System.out.println(str);
			System.out.println(sub[0]);
			System.out.println(sub[1]);
			System.exit(-1);
		}
		//this.figure = Integer.parseInt(sub[1]);
		switch(name.charAt(0)){
		case 'b':
			this.suit = Suits.Black;
			break;
		case 'r':
			this.suit = Suits.Red;
			break;
		}
		this.index_x = x;
		this.index_y = y;
		//Point p = new Point(x,y);
		this.location = new Point();
		//this.preLocation = new Point();
		calPosition(this.index_x, this.index_y);
		this.pre_x = this.index_x;
		this.pre_y = this.index_y;
		this.url = new String("images/" + name);
		int id = name.indexOf('-');
		this.rank = new String(name.substring(id+1,name.length()-4));
		System.out.println("url is:" + url);
		System.out.println("name is:" + name);
		this.setIcon(new ImageIcon(url));
        this.setSize(55, 55);
        this.setLocation(location);
        this.setVisible(true);
        this.addMouseListener(new Moniter());
        System.out.println("chess has been initilized");
	}
	
	// Convert the matrix index to the real pixels
	public void calPosition(int index_x, int index_y){
		double tmp_x = index_x*UNIT + START_X;
		double tmp_y = index_y*UNIT + START_Y;
		this.location.setLocation(tmp_x, tmp_y);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Suits getSuit() {
		return suit;
	}
	public void setSuit(Suits suit) {
		this.suit = suit;
	}
	
	public String getRank(){
		return rank;
	}
	public int getIndX(){
		return index_x;
	}
	public int getIndY(){
		return index_y;
	}
	public void setIndX(int index_x){
		this.index_x = index_x;
	}
	public void setIndY(int index_y){
		this.index_y = index_y;
	}
	
	public int getPre_x() {
		return pre_x;
	}
	public void setPre_x(int pre_x) {
		this.pre_x = pre_x;
	}
	public int getPre_y() {
		return pre_y;
	}
	public void setPre_y(int pre_y) {
		this.pre_y = pre_y;
	}
	public void draw(){
		setIcon(new ImageIcon(url));
        setSize(55, 55);
        setLocation(location);
        setVisible(true);
	}
	
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}

	public boolean isChosen() {
		return isChosen;
	}
	public void setChosen(boolean isChosen) {
		this.isChosen = isChosen;
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
/*	public boolean isMove() {
		return isMove;
	}

	public void setMove(boolean isMove) {
		this.isMove = isMove;
	}*/
	
	
	public String toString() {
			
			//write your code here!
			String tmp = null;
			tmp = (suit == Suits.Red)? new String("red")
				: (suit == Suits.Black)? new String("black")
				: null;
			
			double tmp_x = this.location.getX();
			double tmp_y = this.location.getY();
			
			return Double.valueOf(tmp_x).toString() + ":" + Double.valueOf(tmp_y) + ":" + tmp;
		}
	
	public void dead(){
		this.setChosen(false);
		this.setAlive(false);
		this.setVisible(false);
	}
	/**
	 * Inner class. Used to respond mouse click
	 * @author Louis
	 *
	 */
	class Moniter extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			Chess c = (Chess)e.getSource();
			c.isChosen = true;
			new Blink(c);
			System.out.println(c.getId());
			System.out.println(e.getX());
			System.out.println(e.getY());
		}

		
	}
	
	class Blink implements Runnable{
		Chess c = null;
		public Blink(Chess c){
			this.c = c;
			(new Thread(this)).start();
		}
		@Override
		public void run() {
			while (c.isChosen){
				//begin to blink when first click
				System.out.println(Thread.currentThread().getName());
				if (c.isChosen){
					c.setVisible(false);

					//time control
					try{
						Thread.sleep(200);
					}
					catch(Exception e){
						e.printStackTrace();
					}
					
					c.setVisible(true);
					
					try{
						Thread.sleep(350);
					}
					catch(Exception e){
						e.printStackTrace();
					}
				}
				
			}
			
		}
		
	}
}
