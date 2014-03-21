package com.orderedsoft.loangate;

import android.widget.ImageView;
import android.widget.TextView;

public class CategoryItemViewControls 
{
	private ImageView _ivwCategoryIcon;
	private TextView _tbxCategoryTitle;
	private TextView _tbxCategoryCount;
	private TextView _tbxCategoryModified;
	private TextView _tbxCategoryDescription;
	/**
	 * @return the _tbxCategoryDescription
	 */
	public TextView getCategoryDescription() {
		return _tbxCategoryDescription;
	}
	/**
	 * @param _tbxCategoryDescription the _tbxCategoryDescription to set
	 */
	public void setCategoryDescription(TextView _tbxCategoryDescription) {
		this._tbxCategoryDescription = _tbxCategoryDescription;
	}
	/**
	 * @return the _tbxCategoryModified
	 */
	public TextView getCategoryModified() {
		return _tbxCategoryModified;
	}
	/**
	 * @param _tbxCategoryModified the _tbxCategoryModified to set
	 */
	public void setCategoryModified(TextView _tbxCategoryModified) {
		this._tbxCategoryModified = _tbxCategoryModified;
	}
	/**
	 * @return the _tbxCategoryCount
	 */
	public TextView getCategoryCount() {
		return _tbxCategoryCount;
	}
	/**
	 * @param _tbxCategoryCount the _tbxCategoryCount to set
	 */
	public void setCategoryCount(TextView _tbxCategoryCount) {
		this._tbxCategoryCount = _tbxCategoryCount;
	}
	/**
	 * @return the _tbxCategoryTitle
	 */
	public TextView getCategoryTitle() {
		return _tbxCategoryTitle;
	}
	/**
	 * @param _tbxCategoryTitle the _tbxCategoryTitle to set
	 */
	public void setCategoryTitle(TextView _tbxCategoryTitle) {
		this._tbxCategoryTitle = _tbxCategoryTitle;
	}
	/**
	 * @return the _ivwCategoryIcon
	 */
	public ImageView getCategoryIcon() {
		return _ivwCategoryIcon;
	}
	/**
	 * @param _ivwCategoryIcon the _ivwCategoryIcon to set
	 */
	public void setCategoryIcon(ImageView _ivwCategoryIcon) {
		this._ivwCategoryIcon = _ivwCategoryIcon;
	}
}
