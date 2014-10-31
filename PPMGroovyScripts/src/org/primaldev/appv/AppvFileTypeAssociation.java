package org.primaldev.appv;

import java.util.Collection;

public class AppvFileTypeAssociation {
	
	private String name;
	private boolean mimeAssociation;
	private String ProgId;	
	private Collection<AppvShellCommand> appvShellCommands ;
	
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
	
	
	
	
}
