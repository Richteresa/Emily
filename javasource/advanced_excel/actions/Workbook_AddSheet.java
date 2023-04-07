// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package advanced_excel.actions;

import com.mendix.core.Core;
import com.mendix.logging.ILogNode;
import java.io.IOException;
import advanced_excel.proxies.DocumentType;
import advanced_excel.Utils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import com.mendix.systemwideinterfaces.core.IMendixObject;

/**
 * Add a worksheet
 */
public class Workbook_AddSheet extends CustomJavaAction<java.lang.Boolean>
{
	private java.lang.String SheetName;

	public Workbook_AddSheet(IContext context, java.lang.String SheetName)
	{
		super(context);
		this.SheetName = SheetName;
	}

	@java.lang.Override
	public java.lang.Boolean executeAction() throws Exception
	{
		// BEGIN USER CODE
		try
		{
			DocumentType docType = Utils.GetDocumentType();
			Workbook workbook = Utils.GetWorkBook();
			if (workbook == null) {
				logger.error("ERROR in Advanced_Excel.Workbook_AddSheet:\nWorkbook object not found\nyou have to use Workbook_Create, Workbook_Open or Workbook_LoadFile to create the Workbook object");
				return false;
			} 
			
			if (docType == DocumentType.XLS) {
				HSSFSheet sheet = ((HSSFWorkbook)workbook).createSheet(this.SheetName);
			} else {
				XSSFSheet sheet = ((XSSFWorkbook)workbook).createSheet(this.SheetName);
			}
			
			return true;
		} catch (Exception e) {
			logger.error("ERROR in Advanced_Excel.Workbook_AddSheet: " + e.getMessage() + "\n" + e.toString(), e);
			return false;
		} 
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "Workbook_AddSheet";
	}

	// BEGIN EXTRA CODE
	protected static ILogNode logger = Core.getLogger("Advanced_Excel");
	// END EXTRA CODE
}