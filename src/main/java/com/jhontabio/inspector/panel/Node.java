package com.jhontabio.inspector.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Node extends JPanel
{
	private Color outline = Color.BLACK;

	private String label_string;
	private JLabel label;

	private double friction = 0.9;
	protected double vx, vy;
	private boolean isDragged = false;
	private double dragOffsetX, dragOffsetY; // Click offset
	private long lastDragTime = -1;
	private double lastDragX, lastDragY;

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
				isDragged = true;

				dragOffsetX = e.getX();
				dragOffsetY = e.getY();

				vx = 0;
				vy = 0;

				lastDragX = getX() + e.getX();
				lastDragY = getY() + e.getY();
				lastDragTime = System.nanoTime();

				System.out.println("Node (" + label_string + ") Pressed @ (" + dragOffsetX + "x" + dragOffsetY + ")");
				System.out.println("Node (" + label_string + ") lastDrag @ (" + lastDragX + "x" + lastDragY + ")");
			}

			public void mouseReleased(MouseEvent e)
			{
				isDragged = false;
				lastDragTime = -1;
				System.out.println("Node (" + label_string + ") Released @ (" + lastDragX + "x" + lastDragY + ")");
			}
		});

		addMouseMotionListener(new MouseAdapter()
		{
			public void mouseDragged(MouseEvent e)
			{
				if(!isDragged) return;

				double panelX = getX() + e.getX();
				double panelY = getY() + e.getY();

				double x = panelX - dragOffsetX;
				double y = panelY - dragOffsetY;


				if(x > getParent().getWidth()) x = getParent().getWidth();
				else if(x <= 0) x = 0;
				if(y > getParent().getHeight()) y = getParent().getHeight();
				else if(y <= 0) y = 0;
				
				setLocation((int) x, (int) y);

				System.out.println("Node (" + label_string + ") Dragged @ (" + x + "x" + y + ")");

				long now = System.nanoTime();

				if(lastDragTime > 0)
				{
					double dt = (now - lastDragTime) / 1e9;
					if(dt > 0)
					{
						vx = (panelX - lastDragX) / dt;
						vy = (panelY - lastDragY) / dt;
					}
				}
				lastDragTime = now;
				lastDragX = panelX;
				lastDragY = panelY;
			}
		});
	}

	public void tick(double dt)
	{
		if(isDragged) return;

		vx *= friction;
		vy *= friction;

		double x = getX() + (vx * dt);
		double y = getY() + (vy * dt);

		if(x > getParent().getWidth() || x <= 0)
		{
			vx *= -1;
			return;
		}

		if(y > getParent().getHeight() || y <= 0)
		{
			vy *= -1;
			return;
		}

		setLocation((int) x, (int) y);
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
