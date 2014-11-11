package org.primaldev.appv;

public class AppvShortCut {
	
	 private  String File;  //the actual lnk file
	 private String Target;
	 private String Icon;
	 private String Arguments;
	 private String WorkingDirectory;
	 private boolean ShowCommand;
	    
	    public AppvShortCut() {
			// TODO Auto-generated constructor stub
		}
	    
	    public void setFile(String file) {
	        File = file;
	    }
	    
	    public void setArguments(String arguments) {
	        Arguments = arguments;
	    }
	    public  void setIcon(String icon) {
	        Icon = icon;
	    }
	    public void setShowCommand(boolean showCommand) {
	        ShowCommand = showCommand;
	    }
	    public void setTarget(String target) {
	        Target = target;
	    }
	    public void setWorkingDirectory(String workingDirectory) {
	        WorkingDirectory = workingDirectory;
	    }
	        
	    public  String getWorkingDirectory() {
	        return WorkingDirectory;
	    } 
	    
	    public String getFile() {
	        return File;
	    }
	    public  String getArguments() {
	        return Arguments;
	    }
	    public String getIcon() {
	        return Icon;
	    }
	    public  String getTarget() {
	        return Target;
	    }
	    public boolean isShowCommand() {
	        return ShowCommand;
	    }
	    
	
	

}
