package com.revature.models;

public class ReimbursementDTO {

	public ReimbursementDTO() {
		super();
	}	
	
	
	public int rId;
	public int rAuthorId;
	public int resolverId;
	public double rAmt;
	public String rDescription;
	public String rStatus;
	public String rType;
	
	
	public ReimbursementDTO(int rId, int rAuthorId, int resolverId, double rAmt, String rDescription, String rStatus, String rType) {
		super();
		this.rId = rId;
		this.rAuthorId = rAuthorId;
		this.resolverId = resolverId;
		this.rAmt = rAmt;
		this.rDescription = rDescription;
		this.rStatus = rStatus;
		this.rType = rType;
	}


	@Override
	public String toString() {
		return "ReimbursementDTO [rId=" + rId + ", rAuthorId=" + rAuthorId + ", resolverId=" + resolverId + ", rAmt="
				+ rAmt + ", rDescription=" + rDescription + ", rStatus=" + rStatus + ", rType=" + rType + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(rAmt);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + rAuthorId;
		result = prime * result + ((rDescription == null) ? 0 : rDescription.hashCode());
		result = prime * result + rId;
		result = prime * result + ((rStatus == null) ? 0 : rStatus.hashCode());
		result = prime * result + ((rType == null) ? 0 : rType.hashCode());
		result = prime * result + resolverId;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimbursementDTO other = (ReimbursementDTO) obj;
		if (Double.doubleToLongBits(rAmt) != Double.doubleToLongBits(other.rAmt))
			return false;
		if (rAuthorId != other.rAuthorId)
			return false;
		if (rDescription == null) {
			if (other.rDescription != null)
				return false;
		} else if (!rDescription.equals(other.rDescription))
			return false;
		if (rId != other.rId)
			return false;
		if (rStatus == null) {
			if (other.rStatus != null)
				return false;
		} else if (!rStatus.equals(other.rStatus))
			return false;
		if (rType == null) {
			if (other.rType != null)
				return false;
		} else if (!rType.equals(other.rType))
			return false;
		if (resolverId != other.resolverId)
			return false;
		return true;
	}

	

	public int getrId() {
		return rId;
	}


	public void setrId(int rId) {
		this.rId = rId;
	}


	public int getrAuthorId() {
		return rAuthorId;
	}


	public void setrAuthorId(int rAuthorId) {
		this.rAuthorId = rAuthorId;
	}


	public int getResolverId() {
		return resolverId;
	}


	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}


	public double getrAmt() {
		return rAmt;
	}


	public void setrAmt(double rAmt) {
		this.rAmt = rAmt;
	}


	public String getrDescription() {
		return rDescription;
	}


	public void setrDescription(String rDescription) {
		this.rDescription = rDescription;
	}


	public String getrStatus() {
		return rStatus;
	}


	public void setrStatus(String rStatus) {
		this.rStatus = rStatus;
	}


	public String getrType() {
		return rType;
	}


	public void setrType(String rType) {
		this.rType = rType;
	}

}

// Nancy had one of these, and I think it's a way to send Reimbursement data through the servlet w/o instantiating an entire Reimbursement Object.