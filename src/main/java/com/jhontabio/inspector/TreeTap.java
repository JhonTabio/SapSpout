package com.jhontabio.inspector;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class TreeTap extends JFrame
{
	public TreeTap()
	{
		super("TreeTap - Brigadier Inspector");

		// Configuration
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(900, 800);
		setLocationByPlatform(true);
		setLayout(new BorderLayout());

		// UI Layout
		add(new JLabel("Hello Swing!", SwingConstants.CENTER), BorderLayout.CENTER);
	}

	public static void main(String[] args)
	{
		System.out.println("Hello World!");

		SwingUtilities.invokeLater(() -> {
			TreeTap tt = new TreeTap();
			tt.setVisible(true);
		});
	}
}
