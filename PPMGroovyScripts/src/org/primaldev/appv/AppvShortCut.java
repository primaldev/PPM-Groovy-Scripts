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
	    
	    public static void setFile(String file) {
	        File = file;
	    }
	    
	    public static void setArguments(String arguments) {
	        Arguments = arguments;
	    }
	    public static void setIcon(String icon) {
	        Icon = icon;
	    }
	    public static void setShowCommand(boolean showCommand) {
	        ShowCommand = showCommand;
	    }
	    public static void setTarget(String target) {
	        Target = target;
	    }
	    public static void setWorkingDirectory(String workingDirectory) {
	        WorkingDirectory = workingDirectory;
	    }
	        
	    public static String getWorkingDirectory() {
	        return WorkingDirectory;
	    } 
	    
	    public static String getFile() {
	        return File;
	    }
	    public static String getArguments() {
	        return Arguments;
	    }
	    public static String getIcon() {
	        return Icon;
	    }
	    public static String getTarget() {
	        return Target;
	    }
	    public static boolean isShowCommand() {
	        return ShowCommand;
	    }
	    
	
	

}
