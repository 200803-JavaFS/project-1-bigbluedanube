package com.revature.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="ers_reimbursement")
public class Reimbursement implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="reimb_id")
	private int reimbId;
	
	@Column(name="reimb_amount", nullable=false)
	private double reimbAmount;

	@Column(name="reimb_submitted", nullable=false)
	private Timestamp reimbSubmitted;
	
	@Column(name="reimb_resolved")
	private Timestamp reimbResolved;
	
	@Column(name="reimb_description")
	private String reimbDescription;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="reimb_author_fk", referencedColumnName="ers_users_id", nullable=false)
	private User reimbAuthor;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="reimb_resolver_fk", referencedColumnName="ers_users_id")
	private User reimbResolver;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="reimb_status_id_fk", referencedColumnName="reimb_status_id", nullable=false)
	private ReimbursementStatus reimbStatusFk;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="reimb_type_id_fk", referencedColumnName="reimb_type_id", nullable=false)
	private ReimbursementType reimbTypeFk;
	
	
	public Reimbursement(int reimbId, double reimbAmount, Timestamp reimbSubmitted, Timestamp reimbResolved, String reimbDescription, User reimbAuthor, User reimbResolver, ReimbursementStatus reimbStatusFk, ReimbursementType reimbTypeFk) {
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.reimbDescription = reimbDescription;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
		this.reimbStatusFk = reimbStatusFk;
		this.reimbTypeFk = reimbTypeFk;		
	}
	

	public Reimbursement() {
		super();
	}
	
	
	public Reimbursement(double reimbAmount, Timestamp reimbSubmitted, Timestamp reimbResolved, String reimbDescription,
			User reimbAuthor, User reimbResolver, ReimbursementStatus reimbStatusFk, ReimbursementType reimbTypeFk) {
		super();
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.reimbDescription = reimbDescription;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
		this.reimbStatusFk = reimbStatusFk;
		this.reimbTypeFk = reimbTypeFk;
	}
	

	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", reimbAmount=" + reimbAmount + ", reimbSubmitted="
				+ reimbSubmitted + ", reimbResolved=" + reimbResolved + ", reimbDescription=" + reimbDescription 
				+ ", reimbAuthor=" + reimbAuthor + ", reimbResolver=" + reimbResolver
				+ ", reimbStatusFk=" + reimbStatusFk + ", reimbTypeFk=" + reimbTypeFk + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(reimbAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((reimbAuthor == null) ? 0 : reimbAuthor.hashCode());
		result = prime * result + ((reimbDescription == null) ? 0 : reimbDescription.hashCode());
		result = prime * result + reimbId;
		result = prime * result + ((reimbResolved == null) ? 0 : reimbResolved.hashCode());
		result = prime * result + ((reimbResolver == null) ? 0 : reimbResolver.hashCode());
		result = prime * result + ((reimbStatusFk == null) ? 0 : reimbStatusFk.hashCode());
		result = prime * result + ((reimbSubmitted == null) ? 0 : reimbSubmitted.hashCode());
		result = prime * result + ((reimbTypeFk == null) ? 0 : reimbTypeFk.hashCode());
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
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(reimbAmount) != Double.doubleToLongBits(other.reimbAmount))
			return false;
		if (reimbAuthor == null) {
			if (other.reimbAuthor != null)
				return false;
		} else if (!reimbAuthor.equals(other.reimbAuthor))
			return false;
		if (reimbDescription == null) {
			if (other.reimbDescription != null)
				return false;
		} else if (!reimbDescription.equals(other.reimbDescription))
			return false;
		if (reimbId != other.reimbId)
			return false;
		if (reimbResolved == null) {
			if (other.reimbResolved != null)
				return false;
		} else if (!reimbResolved.equals(other.reimbResolved))
			return false;
		if (reimbResolver == null) {
			if (other.reimbResolver != null)
				return false;
		} else if (!reimbResolver.equals(other.reimbResolver))
			return false;
		if (reimbStatusFk == null) {
			if (other.reimbStatusFk != null)
				return false;
		} else if (!reimbStatusFk.equals(other.reimbStatusFk))
			return false;
		if (reimbSubmitted == null) {
			if (other.reimbSubmitted != null)
				return false;
		} else if (!reimbSubmitted.equals(other.reimbSubmitted))
			return false;
		if (reimbTypeFk == null) {
			if (other.reimbTypeFk != null)
				return false;
		} else if (!reimbTypeFk.equals(other.reimbTypeFk))
			return false;
		return true;
	}


	public int getReimbId() {
		return reimbId;
	}


	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}


	public double getReimbAmount() {
		return reimbAmount;
	}


	public void setReimbAmount(double reimbAmount) {
		this.reimbAmount = reimbAmount;
	}


	public Timestamp getReimbSubmitted() {
		return reimbSubmitted;
	}


	public void setReimbSubmitted(Timestamp reimbSubmitted) {
		this.reimbSubmitted = reimbSubmitted;
	}


	public Timestamp getReimbResolved() {
		return reimbResolved;
	}


	public void setReimbResolved(Timestamp reimbResolved) {
		this.reimbResolved = reimbResolved;
	}


	public String getReimbDescription() {
		return reimbDescription;
	}


	public void setReimbDescription(String reimbDescription) {
		this.reimbDescription = reimbDescription;
	}


	public User getReimbAuthor() {
		return reimbAuthor;
	}


	public void setReimbAuthor(User reimbAuthor) {
		this.reimbAuthor = reimbAuthor;
	}


	public User getReimbResolver() {
		return reimbResolver;
	}


	public void setReimbResolver(User reimbResolver) {
		this.reimbResolver = reimbResolver;
	}


	public ReimbursementStatus getReimbStatusFk() {
		return reimbStatusFk;
	}


	public void setReimbStatusFk(ReimbursementStatus reimbStatusFk) {
		this.reimbStatusFk = reimbStatusFk;
	}


	public ReimbursementType getReimbTypeFk() {
		return reimbTypeFk;
	}


	public void setReimbTypeFk(ReimbursementType reimbTypeFk) {
		this.reimbTypeFk = reimbTypeFk;
	}
	

}
