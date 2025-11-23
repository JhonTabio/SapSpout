package com.jhontabio.inspector;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.jhontabio.inspector.panel.CommandTreePanel;

public class TreeTap extends JFrame
{
	private final CommandTreePanel ctp;

	public TreeTap()
	{
		super("TreeTap - Brigadier Inspector");

		// Configuration
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(900, 800);
		setLocationByPlatform(true);
		setLayout(new BorderLayout());

		// Tree visualization
		ctp = new CommandTreePanel();
		//ctp.setPreferredSize(new Dimension(ctp.getWidth() + 1000, ctp.getHeight() + 1000));
		JScrollPane scroll = new JScrollPane(ctp);
		//scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		//scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

		// Command input
		JPanel testPanel = new JPanel();
		testPanel.add(new JLabel("Hello Swing!", SwingConstants.CENTER), BorderLayout.CENTER);

		// Divide panels
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, testPanel);
		split.setDividerLocation(350);
		split.setResizeWeight(0.3);

		// UI Layout
		add(split, BorderLayout.CENTER);
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
