package com.orderedsoft.loangate.models;

import java.math.BigDecimal;
import java.util.Date;


public class Loan 
{

	private String _title;
	private String _amountUnit;
	private BigDecimal _value; 
    private Date _expiration;
    private long _id;
	
	
	public Loan() 
	{
		// TODO Auto-generated constructor stub
	}

	public void setValue(BigDecimal value) 
	{
		_value = value;
	}

	public void setTitle(String title) 
	{
		_title = title;
	}
	
	@Override
	public String toString() 
	{
		return _title;
	}

	public CharSequence getTitle() 
	{
		return _title;
	}

	/**
	 * @return the _value
	 */
	public BigDecimal getValue() {
		return _value;
	}

	/**
	 * @return the _expiration
	 */
	public Date getEpiration() {
		return _expiration;
	}

	/**
	 * @param expiration the expiration to set
	 */
	public void setExpiration(Date expiration) {
		this._expiration = expiration;
	}

	public String getLoanAmountUnit() {
		return _amountUnit;
	}

	/**
	 * @param _amountUnit the _amountUnit to set
	 */
	public void setAmountUnit(String _amountUnit) {
		this._amountUnit = _amountUnit;
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
