package com.jhontabio.inspector.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class CommandTreePanel extends JPanel
{
	private final int fps = 60;
	private long lastTime, nowTime;

	private Point initialMouseLocation;

	private List<Node> nodes;

	public CommandTreePanel()
	{
		super(null);
		setPreferredSize(new Dimension(4000, 4000));

		addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				initialMouseLocation = e.getPoint();
				System.out.println("Mouse Pressed! (" + initialMouseLocation.x + "x" + initialMouseLocation.y + ")");
			}
			public void mouseReleased(MouseEvent e)
			{
				System.out.println("Mouse Released! (" + initialMouseLocation.x + "x" + initialMouseLocation.y + ")");
				initialMouseLocation = null;
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
				System.out.println("Mouse dragged! (" + finalMouseLocation.x + "x" + finalMouseLocation.y + ")");
			}
		});

		nodes = new ArrayList<Node>();

		addNode("Test");
		addNode("Another Test", getPreferredSize().width + 170, getPreferredSize().height + 170, 50, 50);

		lastTime = System.nanoTime();
		new Timer(1000 / fps, e -> {
			nowTime = System.nanoTime();
			double dt = (nowTime - lastTime) / 1e9;

			for(Node n : nodes) n.tick(dt);

			lastTime = nowTime;
			repaint();
		}).start();

	}

	public Node addNode(String label)
	{
		return addNode(label, getPreferredSize().width, getPreferredSize().height);
	}

	public Node addNode(String label, int x, int y)
	{
		return addNode(label, x, y, 150, 150);
	}

	public Node addNode(String label, int x, int y, int width, int height)
	{
		return addNode(label, x, y, width, height, Color.DARK_GRAY);
	}

	public Node addNode(String label, int x, int y, int width, int height, Color color)
	{
		Node n = new Node(label);
		n.setBackground(color);
		n.setPreferredSize(new Dimension(width, height));
		n.setBounds((x - width) / 2, (y - height) / 2, width, height);
		add(n);
		nodes.add(n);

		return n;
	}
}
