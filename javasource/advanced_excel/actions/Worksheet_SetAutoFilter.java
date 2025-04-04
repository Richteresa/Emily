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
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import com.mendix.systemwideinterfaces.core.IMendixObject;

/**
 * Set auto filter on a sheet
 * example:
 * 
 *     0 1 2 3 4 5 6  
 * 0 | -  -  h h h -  - |   h = header
 * 1 | -  -  + + + -  - |   + = values
 * 2 | -  -  + + + -  - |   - = empty fields
 * 3 | -  -  + + + -  - |
 * 4 | -  -  -  -  -  -  - |
 * 
 * to filter this table, first row will be 0, last row: 3, first col: 2 and last col: 4
 */
public class Worksheet_SetAutoFilter extends CustomJavaAction<java.lang.Boolean>
{
	private java.lang.String SheetName;
	private java.lang.Long FirstRow;
	private java.lang.Long LastRow;
	private java.lang.Long FirstCol;
	private java.lang.Long LastCol;

	public Worksheet_SetAutoFilter(IContext context, java.lang.String SheetName, java.lang.Long FirstRow, java.lang.Long LastRow, java.lang.Long FirstCol, java.lang.Long LastCol)
	{
		super(context);
		this.SheetName = SheetName;
		this.FirstRow = FirstRow;
		this.LastRow = LastRow;
		this.FirstCol = FirstCol;
		this.LastCol = LastCol;
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
				logger.error("ERROR in Advanced_Excel.Worksheet_SetAutoFilter:\nWorkbook object not found\nyou have to use Workbook_Create, Workbook_Open or Workbook_LoadFile to create the Workbook object");
				return false;
			}
			
			if (docType == DocumentType.XLS) {
				HSSFSheet sheet = ((HSSFWorkbook)workbook).getSheet(this.SheetName);
				if (sheet == null) { throw new Exception("Sheet: " + this.SheetName + " not found!"); }
				
				sheet.setAutoFilter(new CellRangeAddress(FirstRow.intValue(), LastRow.intValue(), FirstCol.intValue(), LastCol.intValue()));
			} else {
				XSSFSheet sheet = ((XSSFWorkbook)workbook).getSheet(this.SheetName);
				if (sheet == null) { throw new Exception("Sheet: " + this.SheetName + " not found!"); }
				
				sheet.setAutoFilter(new CellRangeAddress(FirstRow.intValue(), LastRow.intValue(), FirstCol.intValue(), LastCol.intValue()));
			}
			
			return true;
		} catch (Exception e) {
			logger.error("ERROR in Advanced_Excel.Worksheet_SetAutoFilter: " + e.getMessage() + "\n" + e.toString(), e);
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
		return "Worksheet_SetAutoFilter";
	}

	// BEGIN EXTRA CODE
	protected static ILogNode logger = Core.getLogger("Advanced_Excel");
	// END EXTRA CODE
}
