package gui;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GroupPanel extends JPanel{
	public GroupPanel(String titre){
		this.setBorder(BorderFactory.createTitledBorder(titre));
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
	}
}
