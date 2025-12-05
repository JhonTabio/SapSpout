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

	private String cmd;
	private JLabel label;

	private Point initialMouseLocation;
	private Point mouseLocation;

	public Node(String cmd)
	{
		this.cmd = cmd;

		setOpaque(false);
		setLayout(new GridBagLayout());
		setName(cmd);

		label = new JLabel(cmd);
		label.setName(cmd);
		add(label, new GridBagConstraints());

		addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				initialMouseLocation = e.getPoint();
				mouseLocation = getLocation();
				System.out.println("Node (" + cmd + ") Clicked @ (" + initialMouseLocation.x + "x" + initialMouseLocation.y + ")");
				System.out.println("Node (" + cmd + ") Position @ (" + mouseLocation.x + "x" + mouseLocation.y + ")");
			}
		});

		addMouseMotionListener(new MouseAdapter()
		{
			public void mouseDragged(MouseEvent e)
			{
				if(initialMouseLocation == null || mouseLocation == null) return;

				int dx = e.getX() - initialMouseLocation.x;
				int dy = e.getY() - initialMouseLocation.y;

				mouseLocation = new Point(mouseLocation.x + dx, mouseLocation.y + dy);
				System.out.println("Node (" + cmd + ") Dragged @ (" + mouseLocation.x + "x" + mouseLocation.y + ")");
				setLocation(mouseLocation);
				getParent().repaint();

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

	public String getCMD()
	{
		return cmd;
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
