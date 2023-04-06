// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package advanced_excel.actions;

import advanced_excel.proxies.Line;
import advanced_excel.proxies.DocumentType;
import advanced_excel.Utils;
import com.mendix.core.Core;
import com.mendix.logging.ILogNode;
import com.mendix.core.CoreException;
import com.mendix.webui.CustomJavaAction;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.systemwideinterfaces.core.meta.IMetaObject;
import com.mendix.systemwideinterfaces.core.meta.IMetaPrimitive;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Converts an Excel object to a List of line objects
 */
public class ExcelToLines extends CustomJavaAction<java.util.List<IMendixObject>>
{
	private java.lang.String SheetName;
	private java.lang.Long TitleRowNum;

	public ExcelToLines(IContext context, java.lang.String SheetName, java.lang.Long TitleRowNum)
	{
		super(context);
		this.SheetName = SheetName;
		this.TitleRowNum = TitleRowNum;
	}

	@java.lang.Override
	public java.util.List<IMendixObject> executeAction() throws Exception
	{
		// BEGIN USER CODE
		try
		{
			IContext context = this.getContext();
			DocumentType docType = Utils.GetDocumentType();
			Workbook workbook = Utils.GetWorkBook();
			if (workbook == null) {
				logger.error("ERROR in Advanced_Excel.ExcelToList:\nWorkbook object not found\nyou have to use Workbook_Create, Workbook_Open or Workbook_LoadFile to create the Workbook object");
				return null;
			}
			
			Cell cell;
			Row row;
			String field;
			
			DataFormatter formatter = new DataFormatter();
			Sheet sheet = workbook.getSheet(this.SheetName);
			if (sheet == null) { throw new Exception("Sheet: " + this.SheetName + " not found!"); }
			
			int rowTotal = sheet.getPhysicalNumberOfRows();
			logger.info("Row Total: " + rowTotal);
			
			// Get field names
			row = sheet.getRow(TitleRowNum.intValue() - 1);
			if (row == null) { throw new Exception("Title Row not found"); }
			int cellTotal = row.getPhysicalNumberOfCells();
			logger.info("Cell Total: " + cellTotal);

			List<IMendixObject> LineList = new ArrayList<IMendixObject>();
			IMendixObject object;
			for (int i = TitleRowNum.intValue(); i < rowTotal; i++)
			{
				row = sheet.getRow(i);
				if (row == null) { continue; }
				
				object = Core.instantiate(context, "Advanced_Excel.Line");
				for (int j = 0; j < cellTotal; j++)
				{
					cell = row.getCell(j);
					if (cell == null) { continue; }
					
					object.setValue​(context, "Cell_" + (j + 1), formatter.formatCellValue(cell));
				}
				LineList.add(object);
			}
			
			return LineList;
		} catch (Exception e) {
			logger.error("ERROR in Advanced_Excel.ExcelToList: " + e.getMessage() + "\n" + e.toString(), e);
			return null;
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
		return "ExcelToLines";
	}

	// BEGIN EXTRA CODE
	protected static ILogNode logger = Core.getLogger("Advanced_Excel");
	// END EXTRA CODE
}
