import java.util.LinkedList;

public class TableData {

	private String tableName = "";
	
	private LinkedList<String> attributeList;
	private LinkedList<String> typesList;
	private LinkedList<String> foreignKeyList;
	
	private boolean isPivot;


	public TableData() 
	{
		this.tableName = " ";
		this.isPivot = false;
		
		this.attributeList = new LinkedList<String>();
		this.typesList = new LinkedList<String>();
		this.foreignKeyList = new LinkedList<String>();
	}


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
	 * @return the isPivot
	 */
	public boolean isPivot() {
		return isPivot;
	}


	/**
	 * @param isPivot the isPivot to set
	 */
	public void setPivot(boolean isPivot) {
		this.isPivot = isPivot;
	}


	public LinkedList<String> getForeignKeyList() {
		return foreignKeyList;
	}

	/**
	 * @param attributeList the attributeList to set
	 */
	public void pushInForeignKeyList(String attribute) {
		this.foreignKeyList.add(attribute);
	}


	public void setForeignKeyList(String foreignKey) {
		this.foreignKeyList.add(foreignKey);
	}





}
