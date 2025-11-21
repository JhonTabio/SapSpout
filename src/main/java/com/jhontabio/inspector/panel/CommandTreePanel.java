package com.jhontabio.inspector.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

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
				initialMouseLocation = e.getLocationOnScreen();
				System.out.println("Mouse Pressed! " + initialMouseLocation);
			}
		});

		addMouseMotionListener(new MouseAdapter()
		{
			public void mouseDragged(MouseEvent e)
			{
				if(initialMouseLocation == null) return;

				Point finalMouseLocation = e.getLocationOnScreen();
				System.out.println("Mouse dragged! " + finalMouseLocation);
			}
		});

		Vertex v = new Vertex("Test");
		v.setBackground(Color.DARK_GRAY);
		add(v);
	}
}
