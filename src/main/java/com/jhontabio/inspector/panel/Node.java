package com.jhontabio.inspector.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Node extends JPanel
{
	private Color outline = Color.BLACK;

	private String label_string;
	private JLabel label;

	private Point initialMouseLocation;
	private Point mouseLocation;

	public Node(String label_string)
	{
		this.label_string = label_string;

		setOpaque(false);
		setLayout(new GridBagLayout());
		setName(label_string);

		label = new JLabel(label_string);
		label.setName(label_string);
		add(label, new GridBagConstraints());

		addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				initialMouseLocation = e.getPoint();
				mouseLocation = getLocation();
				System.out.println("Node (" + label_string + ") Clicked @ (" + initialMouseLocation.x + "x" + initialMouseLocation.y + ")");
				System.out.println("Node (" + label_string + ") Position @ (" + mouseLocation.x + "x" + mouseLocation.y + ")");
			}
		});

		addMouseMotionListener(new MouseAdapter()
		{
			public void mouseDragged(MouseEvent e)
			{
				if(initialMouseLocation == null || mouseLocation == null) return;

				int dx = e.getX() - initialMouseLocation.x;
				int dy = e.getY() - initialMouseLocation.y;

				mouseLocation.translate(dx, dy);
				System.out.println("Node (" + label_string + ") Dragged @ (" + mouseLocation.x + "x" + mouseLocation.y + ")");
				setLocation(mouseLocation);
				//getParent().repaint();
			}
		});
	}

	public void fill(Graphics2D graphic, Color color)
	{
		int s = Math.min(getWidth(), getHeight());
        	int x = (getWidth() - s) / 2;
        	int y = (getHeight() - s) / 2;

        	graphic.setColor(getBackground());
        	graphic.fillOval(x, y, s - 1, s - 1);
	}

	public void draw(Graphics2D graphic)
	{
		int s = Math.min(getWidth(), getHeight());
        	int x = (getWidth() - s) / 2;
        	int y = (getHeight() - s) / 2;

        	graphic.setColor(this.outline);
        	graphic.drawOval(x, y, s - 1, s - 1);
	}

	public String getlabel_string()
	{
		return label_string;
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
        	Graphics2D g2 = (Graphics2D) g.create();
        	try
		{
        	    fill(g2, getBackground());
        	    draw(g2);
        	}
		finally { g2.dispose(); }
	}
}
