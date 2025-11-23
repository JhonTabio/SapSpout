package com.jhontabio.inspector;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.jhontabio.inspector.panel.CommandTreePanel;

public class TreeTap extends JFrame
{
	private final CommandTreePanel ctp;
	private final JScrollPane scrollPane;

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
		scrollPane = new JScrollPane(ctp);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

		// Command input
		JPanel testPanel = new JPanel();
		testPanel.add(new JLabel("Hello Swing!", SwingConstants.CENTER), BorderLayout.CENTER);

		// Divide panels
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, testPanel);
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
			tt.setLocationRelativeTo(null);

			SwingUtilities.invokeLater(() -> {
				JViewport viewport = tt.scrollPane.getViewport();

				Dimension viewSize = tt.ctp.getPreferredSize();
				Dimension extentSize = viewport.getExtentSize();

				int x = (viewSize.width - extentSize.width) / 2;
				int y = (viewSize.height - extentSize.height) / 2;

				if(x < 0) x = 0;
				if(y < 0) y = 0;

				viewport.setViewPosition(new Point(x, y));
			});
		});
	}
}
