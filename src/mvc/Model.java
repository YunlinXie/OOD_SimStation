package mvc;

public abstract class Model extends Bean {
	private String fName;  // name of the file where model is saved
	private Boolean unSavedChanges;  // true if there are unsaved changes
	
	//public Model(String fName) 
	//{
      //this.fName  = fName;
      //this.unSavedChanges = true;
    //}
	
	public String getFileName() 
	{
		return fName;
	}
    
	public void  setFileName(String name)
	{
		this.fName = name;
	}
	
	public Boolean hasUnsavedChanges()
	{
		return unSavedChanges;
	}
	
	public void setUnsavedChanges(boolean status)
	{
		unSavedChanges = status;
	}
	
	public void changed() {
		unSavedChanges = true;
		firePropertyChange (null, null, null);
	}
}