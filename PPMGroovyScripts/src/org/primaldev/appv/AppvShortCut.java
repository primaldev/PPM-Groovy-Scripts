package org.primaldev.appv;

public class AppvShortCut {
	
	 static String File;  //the actual lnk file
	    static String Target;
	    static String Icon;
	    static String Arguments;
	    static String WorkingDirectory;
	    static boolean ShowCommand;
	    
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
