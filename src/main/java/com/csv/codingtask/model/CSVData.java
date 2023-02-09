/**
 * 
 */
package com.csv.codingtask.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author ovies
 *
 */

@Entity
@Table(name = "CSV_DATA")
public class CSVData {

	@Id
	@Column(name = "PRIMARY_KEY")
	private String primaryKey;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "UPDATED_TIMESTAMP")
	private String updatedTimestamp;

	/**
	 * 
	 */
	public CSVData() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param primaryKey
	 * @param name
	 * @param description
	 * @param updatedTimestamp
	 */
	public CSVData(String primaryKey, String name, String description, String updatedTimestamp) {
		super();
		this.primaryKey = primaryKey;
		this.name = name;
		this.description = description;
		this.updatedTimestamp = updatedTimestamp;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String isUpdatedTimestamp() {
		return updatedTimestamp;
	}

	public void setUpdatedTimestamp(String updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}

	@Override
	public String toString() {
		return "CsvData [primaryKey=" + primaryKey + ", name=" + name + ", description=" + description
				+ ", updatedTimestamp=" + updatedTimestamp + "]";
	}	
	

}
