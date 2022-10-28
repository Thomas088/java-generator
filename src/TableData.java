import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.ArrayList;

/**
 * TableData => The architecture defined for retrieves data from .sql file (used in Parser class)
 * @author Java Generator Team
 *
 */
public class TableData {
	
	// TO REPLACE
	private LinkedList<String> attributeList;
	private LinkedList<String> typesList;
	private LinkedList<String> foreignKeyList;
	
	private String tableName = "";
	
	// NEW DATA ARCHITECTURE
	private Map<String, ArrayList<String>> attributeDatas;
	
	private boolean isIntermediaryTable;

	public TableData() {
		
		this.tableName = " ";
		this.isIntermediaryTable = false;
		this.attributeList = new LinkedList<String>();
		this.typesList = new LinkedList<String>();
		this.foreignKeyList = new LinkedList<String>();
		
		// STRUCTURE IN MAP (HashMap) - pair KEY / VALUE 
		// Explanations : 
		// KEY = the attribute
		// VALUE = An array with all attribute datas.
		// Array attribute datas order (type, length(if varchar else 0), null / not null)
		// if attribute is foreign key (foreign_key name, table referencement)
		
		// ----------------------------------------------------------------- //
		
		// Examples : 
		// MAP => "idClient" => ["int", "auto increment"]
		// MAP => "lastname" => ["varchar", "255", "not null"]
		// MAP => "lastname" => ["varchar", "255", "not null"]
		
		this.attributeDatas = new HashMap<String, ArrayList<String>>();
	}
	
	// ------------------- GETTERS / SETTERS --------------------- //
	
	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the attributeList
	 */
	public LinkedList<String> getAttributeList() {
		return attributeList;
	}
	
	/**
	 * @param attributeList the attributeList to set
	 */
	public void pushInAttributeList(String attribute) {
		this.attributeList.add(attribute);
	}

	/**
	 * @param attributeList the attributeList to set
	 */
	public void setAttributeList(LinkedList<String> attributeList) {
		this.attributeList = attributeList;
	}

	/**
	 * @return the typesList
	 */
	public LinkedList<String> getTypesList() {
		return typesList;
	}
	
	/**
	 * @param typesList the typesList to set
	 */
	public void pushInTypesList(String type) {
		this.typesList.add(type);
	}

	/**
	 * @param typesList the typesList to set
	 */
	public void setTypesList(LinkedList<String> typesList) {
		this.typesList = typesList;
	}

	/**
	 * @return the isIntermediaryTable
	 */
	public boolean isIntermediaryTable() {
		return isIntermediaryTable;
	}

	/**
	 * @param isIntermediaryTable : the isIntermediaryTable to set
	 */
	public void setIntermediaryTable(boolean isIntermediaryTable) {
		this.isIntermediaryTable = isIntermediaryTable;
	}

	/**
	 * 
	 * @return {LinkedList<String>}
	 */
	public LinkedList<String> getForeignKeyList() {
		return foreignKeyList;
	}

	/**
	 * @param attributeList the attributeList to set
	 */
	public void pushInForeignKeyList(String attribute) {
		this.foreignKeyList.add(attribute);
	}

	/**
	 * 
	 * @param foreignKey
	 */
	public void setForeignKeyList(String foreignKey) {
		this.foreignKeyList.add(foreignKey);
	}

	/**
	 * 
	 * @return {Map<String, ArrayList<String>>}
	 */
	public Map<String, ArrayList<String>> getAttributeDatas() {
		return attributeDatas;
	}

	/**
	 * 
	 * @param attributeDatas
	 */
	public void setAttributeDatas(HashMap<String, ArrayList<String>> attributeDatas) {
		this.attributeDatas = attributeDatas;
	}


}
