package com.jhontabio.inspector.panel;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class CommandTreePanel extends JPanel
{
	public CommandTreePanel()
	{
		super(null);

		addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				System.out.println("Mouse Pressed!");
			}
		});

		Vertex v = new Vertex("Test");
		v.setBackground(Color.DARK_GRAY);
		add(v);
	}
}
