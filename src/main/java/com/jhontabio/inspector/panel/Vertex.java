package com.jhontabio.inspector.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Vertex extends JPanel
{
	private Color outline = Color.BLACK;

	private String cmd;
	private JLabel label;

	public Vertex(String cmd)
	{
		this.cmd = cmd;

		setOpaque(false);
		setLayout(new GridBagLayout());
		setName(cmd);

		label = new JLabel(cmd);
		label.setName(cmd);
		add(label, new GridBagConstraints());
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
