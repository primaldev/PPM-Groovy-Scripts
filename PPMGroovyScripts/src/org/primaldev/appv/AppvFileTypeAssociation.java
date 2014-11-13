package org.primaldev.appv;

import java.util.Collection;

public class AppvFileTypeAssociation {
	
	private String name;
	private boolean mimeAssociation;
	private String ProgId;	
	private Collection<AppvShellCommand> appvShellCommands ;
	private boolean shellCommds;
	private String progIdName;
	private String progIdDescription;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isMimeAssociation() {
		return mimeAssociation;
	}
	public void setMimeAssociation(boolean mimeAssociation) {
		this.mimeAssociation = mimeAssociation;
	}
	public String getProgId() {
		return ProgId;
	}
	public void setProgId(String progId) {
		ProgId = progId;
	}
	public Collection<AppvShellCommand> getAppvShellCommands() {
		return appvShellCommands;
	}
	public void setAppvShellCommands(Collection<AppvShellCommand> appvShellCommands) {
		this.appvShellCommands = appvShellCommands;
	}
	
	public boolean isShellCommds() {
		return shellCommds;
	}
	
	public void setShellCommds(boolean shellCommds) {
		this.shellCommds = shellCommds;
	}
	public String getProgIdName() {
		return progIdName;
	}
	public void setProgIdName(String progIdName) {
		this.progIdName = progIdName;
	}
	public String getProgIdDescription() {
		return progIdDescription;
	}
	public void setProgIdDescription(String progIdDescription) {
		this.progIdDescription = progIdDescription;
	}
	
	
	
}
