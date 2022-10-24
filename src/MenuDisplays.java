import static java.lang.System.*;
import java.io.InputStreamReader;
import java.util.Scanner;


/**
 * MenuDisplays => Class for display all the menus (no interaction - only print)
 * @author Java Generator Team
 *
 */
public class MenuDisplays {

	private static final Helpers helper = new Helpers();
	private static final Scanner scanner = new Scanner(new InputStreamReader(System.in));
	
	/**
	 * printMainMenu()
	 */
	public void printMainMenu() { 
		
		out.println("-----------------------------------------");
		out.println("---- ECI INSERT / UPDATE GENERATOR ------");
		out.println("-----------------------------------------");

	}
	
	/**
	 * printMariaTypes()
	 */
	 public void printMariaTypes() { 
		 
			int i = 1;
			for (EnumList.mariaAttributeTypesListEnum attribute : EnumList.mariaAttributeTypesListEnum.values()) {
				out.println(i + " - " + attribute);
				i++;
			}
	}
}	
