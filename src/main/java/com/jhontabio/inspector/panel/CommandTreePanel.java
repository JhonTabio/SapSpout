package com.jhontabio.inspector.panel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class CommandTreePanel extends JPanel
{
	public CommandTreePanel()
	{
		super(new BorderLayout());

		Vertex v = new Vertex("Test");
		v.setBackground(Color.DARK_GRAY);
		add(v);
	}
}
