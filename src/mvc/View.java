package mvc;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.beans.*;

public class View extends JComponent implements PropertyChangeListener{
	
	protected Model model;
	public View(Model model) 
	{
		super();
		this.model = model;
		model.addPropertyChangeListener(this);
		//optional border around the view component
		setBorder(LineBorder.createBlackLineBorder());
	}
	
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) 
	{
		if(this.model!= null) this.model.removePropertyChangeListener(this);
		this.model = model;
		if(model != null)
		{
			this.model.addPropertyChangeListener(this);
			repaint();
		}
	}
	
	@Override
	
    public void propertyChange(PropertyChangeEvent arg0)
    {
		repaint();
    }
}