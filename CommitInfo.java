package linuxgitmsr;

import java.sql.*;

public class CommitInfo {
	//encapsulating the commit variables
	private String cid;
	private String repo;
	private Timestamp comdate;
	private String mnext;
	private String mnextmerge;
	private String mcidlinus;
	private Timestamp mwhen;
	private String mdist;

	public String getMdist() {
		return mdist;
	}
	public void setMdist(String mdist) {
		this.mdist = mdist;
	}
	//creating getters and setters
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getRepo() {
		return repo;
	}
	public void setRepo(String repo) {
		this.repo = repo;
	}
	public Timestamp getComdate() {
		return comdate;
	}
	public void setComdate(Timestamp comdate) {
		this.comdate = comdate;
	}
	public String getMnext() {
		return mnext;
	}
	public void setMnext(String mnext) {
		this.mnext = mnext;
	}
	public String getMnextmerge() {
		return mnextmerge;
	}
	public void setMnextmerge(String mnextmerge) {
		this.mnextmerge = mnextmerge;
	}
	public String getMcidlinus() {
		return mcidlinus;
	}
	public void setMcidlinus(String mcidlinus) {
		this.mcidlinus = mcidlinus;
	}
	public Timestamp getMwhen() {
		return mwhen;
	}
	public void setMwhen(Timestamp mwhen) {
		this.mwhen = mwhen;
	}
}
