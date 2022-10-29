import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

/**
 * TableData => The architecture defined for retrieves data from .sql file (used in Parser class)
 * @author Java Generator Team
 *
 */
public class TableData {
	
	// TO REPLACE
	private ArrayList<String> attributeList;
	private ArrayList<String> typesList;
	private ArrayList<String> foreignKeyList;
	
	private String tableName = "";
	
	// NEW DATA ARCHITECTURE (SOON)
	private Map<String, ArrayList<String>> attributeDatas; 
	
	private boolean isIntermediaryTable;

	public TableData() {
		
		this.tableName = "";
		this.isIntermediaryTable = false;
		this.attributeList = new ArrayList<String>();
		this.typesList = new ArrayList<String>();
		this.foreignKeyList = new ArrayList<String>();

		// STRUCTURE IN MAP (HashMap) - pair KEY / VALUE => SOON - NOT USED (WE USE OLD IMPLEMENTATION FOR THE MOMENT)
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
	 * getTableName() : get name of current table
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * setTableName() : set name of current table
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * getAttributeList() : get the attribute list of the current table
	 * @return the attributeList
	 */
	public ArrayList<String> getAttributeList() {
		return attributeList;
	}
	
	/**
	 * pushInAttributeList() : insert new attribute into the attribute list of the current table
	 * @param attributeList the attributeList to set
	 */
	public void pushInAttributeList(String attribute) {
		this.attributeList.add(attribute);
	}

	/**
	 * setAttributeList() : set the new list of attribute into the current attributeList
	 * @param attributeList the attributeList to set
	 */
	public void setAttributeList(ArrayList<String> attributeList) {
		this.attributeList = attributeList;
	}

	/**
	 * getTypesList() : return the array of types (int, varchar etc)
	 * @return the typesList
	 */
	public ArrayList<String> getTypesList() {
		return typesList;
	}
	
	/**
	 * pushInTypesList() : insert new type into the typeList of the current table
	 * @param typesList the typesList to set
	 */
	public void pushInTypesList(String type) {
		this.typesList.add(type);
	}

	/**
	 * setTypesList() : set the new list of types into the current typesList
	 * @param typesList the typesList to set
	 */
	public void setTypesList(ArrayList<String> typesList) {
		this.typesList = typesList;
	}

	/**
	 * isIntermediaryTable() : return if the table is intermediary (pivot) or not
	 * @return the isIntermediaryTable
	 */
	public boolean isIntermediaryTable() {
		return isIntermediaryTable;
	}

	/**
	 * @param isIntermediaryTable : the isIntermediaryTable variable to set
	 */
	public void setIntermediaryTable(boolean isIntermediaryTable) {
		this.isIntermediaryTable = isIntermediaryTable;
	}

	/**
	 * getForeignKeyList() : return the list of the foreign keys (if the table is intermediary)
	 * @return {ArrayList<String>}
	 */
	public ArrayList<String> getForeignKeyList() {
		return foreignKeyList;
	}

	/**
	 * pushInForeignKeyList() : insert new foreign key into the foreignKeyList of the current table
	 * @param foreignKey : the attributeList to set
	 */
	public void pushInForeignKeyList(String foreignKey) {
		this.foreignKeyList.add(foreignKey);
	}

	/**
	 * setForeignKeyList() : set the new list of foreign keys into the current foreignKeyList
	 * @param newForeignKeyList
	 */
	public void setForeignKeyList(ArrayList<String> newForeignKeyList) {
		this.foreignKeyList = newForeignKeyList;
	}

	/**
	 * getAttributeDatas() : SOON - NOT USED
	 * @return {Map<String, ArrayList<String>>}
	 */
	public Map<String, ArrayList<String>> getAttributeDatas() {
		return attributeDatas;
	}

	/**
	 * setAttributeDatas() : SOON - NOT USED
	 * @param attributeDatas
	 */
	public void setAttributeDatas(HashMap<String, ArrayList<String>> attributeDatas) {
		this.attributeDatas = attributeDatas;
	}
	
	/**
	 * pushInAttributeDatas() : insert new values into the attribute map
	 * @param attributeDatas
	 */
	public void pushInAttributeDatas(String attribute, HashMap<String, ArrayList<String>> attributeDatas) {
		attributeDatas.put(attribute, attributeList);
	}
	
}
