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
	private static int i;
	
	/**
	 * printMainMenu()
	 */
	public static void printMainMenu() { 
		
		out.println("-----------------------------------------");
		out.println("--------- ECI INSERT GENERATOR ----------");
		out.println("-----------------------------------------");

	}
	
	/**
	 * printMariaTypes()
	 */
//	 public static void printMariaTypes() { 
//		 
//		 	i = 1;
//			for (EnumList.MariaAttributeTypesListEnum type : EnumList.MariaAttributeTypesListEnum.values()) {
//				out.println(i + " - " + type);
//				i++;
//			}
//	}
	
}	
