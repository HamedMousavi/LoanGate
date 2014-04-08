package com.orderedsoft.loangate.models;

import java.util.Date;

public class LoanCategory 
{
    private String _name;
    

    private String _description;


    private Date _lastModified;


    private int _loanerCount;


    private long _id;


	public String getName() {
		return _name;
	}


	public void setName(String _name) {
		this._name = _name;
	}


	/**
	 * @return the _description
	 */
	public String getDescription() {
		return _description;
	}


	/**
	 * @param _description the _description to set
	 */
	public void setDescription(String _description) {
		this._description = _description;
	}


	/**
	 * @return the _lastModified
	 */
	public Date getLastModified() {
		return _lastModified;
	}


	/**
	 * @param _lastModified the _lastModified to set
	 */
	public void setLastModified(Date _lastModified) {
		this._lastModified = _lastModified;
	}


	/**
	 * @return the _loanerCount
	 */
	public int getLoanerCount() {
		return _loanerCount;
	}


	/**
	 * @param _loanerCount the _loanerCount to set
	 */
	public void setLoanerCount(int _loanerCount) {
		this._loanerCount = _loanerCount;
	}


	/**
	 * @return the _id
	 */
	public long getId() {
		return _id;
	}


	/**
	 * @param _id the _id to set
	 */
	public void setId(long _id) {
		this._id = _id;
	}

}
