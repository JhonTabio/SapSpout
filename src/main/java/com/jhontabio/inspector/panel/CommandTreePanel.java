package com.jhontabio.inspector.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

public class CommandTreePanel extends JPanel
{
	private Point initialMouseLocation;

	public CommandTreePanel()
	{
		super();

		addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				initialMouseLocation = e.getPoint();
				System.out.println("Mouse Pressed! " + initialMouseLocation);
			}
		});

		addMouseMotionListener(new MouseAdapter()
		{
			public void mouseDragged(MouseEvent e)
			{
				if(initialMouseLocation == null) return;

				JViewport viewport = (JViewport) SwingUtilities.getAncestorOfClass(JViewport.class, CommandTreePanel.this);

				if(viewport == null) return;

				Point viewPos = viewport.getViewPosition();

				int dx = e.getX() - initialMouseLocation.x;
				int dy = e.getY() - initialMouseLocation.y;

				// Inverse for drag effect
				viewPos.translate(-dx, -dy);

				// Clamp to stay within bounds
				int maxX = getWidth() - viewport.getWidth();
				int maxY = getHeight() - viewport.getHeight();

				if(maxX < 0) maxX = 0;
				if(maxY < 0) maxY = 0;
				if(viewPos.x < 0) viewPos.x = 0;
				if(viewPos.y < 0) viewPos.y = 0;
				if(viewPos.x > maxX) viewPos.x = maxX;
				if(viewPos.y > maxY) viewPos.y = maxY;

				viewport.setViewPosition(viewPos);

				Point finalMouseLocation = e.getLocationOnScreen();
				System.out.println("Mouse dragged! " + finalMouseLocation);
			}
		});

		Vertex v = new Vertex("Test");
		v.setBackground(Color.DARK_GRAY);
		v.setPreferredSize(new Dimension(150, 150));
		add(v);

		Vertex v2 = new Vertex("Another Test");
		v2.setBackground(Color.DARK_GRAY);
		v2.setPreferredSize(new Dimension(50, 50));
		add(v2);
	}
}
