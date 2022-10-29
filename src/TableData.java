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
	private ArrayList<String> databaseEquivalenceList;
	private ArrayList<String> typesList;
	private ArrayList<String> foreignKeyList;
	private ArrayList<String> isNullList;
	
	private String tableName = "";
	
	// NEW DATA ARCHITECTURE 
	private Map<String, ArrayList<String>> attributeDatas; 

	private boolean isIntermediaryTable;

	public TableData() {
		
		this.tableName = "";
		
		// ARRAYS VERSION
		this.isIntermediaryTable = false;
		this.attributeList = new ArrayList<String>();
		this.databaseEquivalenceList = new ArrayList<String>();
		this.typesList = new ArrayList<String>();
		this.foreignKeyList = new ArrayList<String>();
		this.isNullList = new ArrayList<String>();
		
		// HASHMAP VERSION
		this.attributeDatas = new HashMap<String, ArrayList<String>>();
		
		// attributeDatas ???
		
		// Explication de la nouvelle architecture 
		// Une map qui contiendrait toute les informations par attributs
		
		// Pour la partie de gauche La cle sera le nom de l'attribut. ex attributeDatas["Prenom"] - attributeDatas["telephone_mobile"]...
		// Pour le reste, il faut juste bien gerer l'ordre...
		
		// Ordre :
		
		// 1) l'equivalent en DB (firstname, lastname etc) - pour les types primitifs, on utilisera l'enum en place (MariaTypes)
		// 2) le type (evidemment - int, varchar etc)
		// 3) la longueur (si varchar... sinon 0)
		// 4) NULL / NOT NULL (evidemment)
		
		// Exemple :
		
		// attributeDatas["id_drone"] = ["auto_increment", "varchar", "0", null]
		// attributeDatas["Prenom"] = ["firstname", "varchar", "255", not-null]
		// attributeDatas["nom_de_famille"] = ["lastname", "varchar", "255", not-null]
		// attributeDatas["age"] = ["number", "number", "0", not-null]
		// attributeDatas["isDeleted"] = ["boolean", "boolean", "0", not-null]
		
		// Seule difficulte a prevoir : la modification de la map
	}
	
	// ------------------- GETTERS / SETTERS --------------------- //
	
	/**
	 * getTableName() : get name of current table
	 * @return {String} the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * setTableName() : set name of current table
	 * @param {String} tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * getAttributeList() : get the attribute list of the current table
	 * @return {ArrayList<String>} the attributeList
	 */
	public ArrayList<String> getAttributeList() {
		return attributeList;
	}
	
	/**
	 * pushInAttributeList() : insert new attribute into the attribute list of the current table
	 * @param {String} attributeList the attributeList to set
	 */
	public void pushInAttributeList(String attribute) {
		this.attributeList.add(attribute);
	}

	/**
	 * setAttributeList() : set the new list of attribute into the current attributeList of the current table
	 * @param {ArrayList<String>} attributeList the attributeList to set
	 */
	public void setAttributeList(ArrayList<String> attributeList) {
		this.attributeList = attributeList;
	}

	/**
	 * getTypesList() : return the array of types (int, varchar etc)
	 * @return {ArrayList<String>} the typesList
	 */
	public ArrayList<String> getTypesList() {
		return typesList;
	}
	
	/**
	 * pushInTypesList() : insert new type into the typeList of the current table
	 * @param {String} typesList the typesList to set
	 */
	public void pushInTypesList(String type) {
		this.typesList.add(type);
	}

	/**
	 * setTypesList() : set the new list of types into the current typesList
	 * @param {ArrayList<String>} typesList the typesList to set
	 */
	public void setTypesList(ArrayList<String> typesList) {
		this.typesList = typesList;
	}

	/**
	 * isIntermediaryTable() : return if the table is intermediary (pivot) or not
	 * @return {boolean} the isIntermediaryTable
	 */
	public boolean isIntermediaryTable() {
		return isIntermediaryTable;
	}

	/**
	 * setIntermediaryTable() : set the table intermediary state of the current table
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
	 * pushInForeignKeyList() : insert new foreign key into the foreignKeyList of the current tableData
	 * @param foreignKey : the attributeList to set
	 */
	public void pushInForeignKeyList(String foreignKey) {
		this.foreignKeyList.add(foreignKey);
	}

	/**
	 * setForeignKeyList() : set the new list of foreign keys into the current foreignKeyList of the current tableData
	 * @param newForeignKeyList
	 */
	public void setForeignKeyList(ArrayList<String> newForeignKeyList) {
		this.foreignKeyList = newForeignKeyList;
	}
	
	/**
	 * getAttributeDatas() : print all datas for the current TableData
	 * @return
	 */
	public Map<String, ArrayList<String>> getAttributeDatas() {
		return attributeDatas;
	}

	/**
	 * setAttributeDatas() : 
	 * @param attributeAndLength
	 */
	public void setAttributeDatas(Map<String, ArrayList<String>> attributeAndLength) {
		this.attributeDatas = attributeAndLength;
	}
	
	/**
	 * pushInAttributeDatas() : push for each attributes the datas in the current TableData
	 * @param attributeKey
	 * @param attributeDatas
	 */
	public void pushInAttributeDatas(String attributeKey, ArrayList<String> attributeDatas) {
		this.attributeDatas.put(attributeKey, attributeDatas);
	}
	
	/**
	 * getDatabaseEquivalenceList() : return the equivalence list for all the attribute
	 * @return
	 */
	public ArrayList<String> getDatabaseEquivalenceList() {
		return databaseEquivalenceList;
	}

	/**
	 * 
	 * @param databaseEquivalenceList
	 */
	public void setDatabaseEquivalenceList(ArrayList<String> databaseEquivalenceList) {
		this.databaseEquivalenceList = databaseEquivalenceList;
	}
}
