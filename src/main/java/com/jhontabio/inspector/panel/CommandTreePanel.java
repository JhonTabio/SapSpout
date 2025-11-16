package com.jhontabio.inspector.panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class CommandTreePanel extends JPanel
{
	private final JTree tree;

	public CommandTreePanel()
	{
		super(new BorderLayout());

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Hello Tree!");

		tree = new JTree(root);

		add(new JScrollPane(tree));
	}
}
