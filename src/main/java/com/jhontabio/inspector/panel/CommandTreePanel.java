package com.jhontabio.inspector.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class CommandTreePanel extends JPanel
{
	private final int fps = 60;
	long lastTime, nowTime;

	private Point initialMouseLocation;

	public CommandTreePanel()
	{
		super(null);
		setPreferredSize(new Dimension(4000, 4000));

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

		lastTime = System.nanoTime();
		new Timer(0, e -> {
			nowTime = System.nanoTime();
			double dt = (nowTime - lastTime) / 1e9;

			lastTime = nowTime;
			repaint();
		}).start();

		Node v = new Node("Test");
		v.setBackground(Color.DARK_GRAY);
		v.setPreferredSize(new Dimension(150, 150));
		v.setBounds((getPreferredSize().width - v.getPreferredSize().width) / 2, (getPreferredSize().height - v.getPreferredSize().height) / 2, 150, 150);
		add(v);

		Node v2 = new Node("Another Test");
		v2.setBackground(Color.DARK_GRAY);
		v2.setPreferredSize(new Dimension(50, 50));
		v2.setBounds((getPreferredSize().width - v2.getPreferredSize().width) / 2 + 170, (getPreferredSize().height - v2.getPreferredSize().height) / 2 + 170, 50, 50);
		add(v2);
	}
}
