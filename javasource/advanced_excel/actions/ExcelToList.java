// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package advanced_excel.actions;

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
 * Converts an Excel object to a Record List.
 * To use this function you need an Entity that matches the Excel file content you want to import. The function matches the attributes of the Entity specified in the ListType property with column headings in the Excel file.
 * If the Entity doesn't match, you can use the AttributesMapping and AttributesToSkip properties to define the mapping between attributes of the Entity and column headings of the Excel file.
 */
public class ExcelToList extends CustomJavaAction<java.util.List<IMendixObject>>
{
	private java.lang.String SheetName;
	private java.lang.String ListType;
	private java.lang.Long TitleRowNum;
	private java.lang.String AttributesMapping;
	private java.lang.String AttributesToSkip;

	public ExcelToList(IContext context, java.lang.String SheetName, java.lang.String ListType, java.lang.Long TitleRowNum, java.lang.String AttributesMapping, java.lang.String AttributesToSkip)
	{
		super(context);
		this.SheetName = SheetName;
		this.ListType = ListType;
		this.TitleRowNum = TitleRowNum;
		this.AttributesMapping = AttributesMapping;
		this.AttributesToSkip = AttributesToSkip;
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
			
			String[] MappingArray = AttributesMapping == null ? new String[0] : AttributesMapping.split(";");
			String[] SkipArray = AttributesToSkip == null ? new String[0] : AttributesToSkip.split(";");
			
			Cell cell;
			Row row;
			String field;
			
			DataFormatter formatter = new DataFormatter();
			IMetaObject metaEntity = Core.getMetaObject(ListType);
			Sheet sheet = workbook.getSheet(this.SheetName);
			if (sheet == null) { throw new Exception("Sheet: " + this.SheetName + " not found!"); }
			
			int rowTotal = sheet.getPhysicalNumberOfRows();
			logger.info("Row Total: " + rowTotal);
			
			// Get field names
			row = sheet.getRow(TitleRowNum.intValue() - 1);
			if (row == null) { throw new Exception("Row 0 not found"); }
			int cellTotal = row.getPhysicalNumberOfCells();
			logger.info("Cell Total: " + cellTotal);
			
			List<String> Fields = new ArrayList<String>();
			for (int i = 0; i < cellTotal; i++)
			{
				cell = row.getCell(i);
				if (cell == null) { throw new Exception("Cell 0 - " + i + " not found"); }
				
				// Manage Attribute skipped
				field = formatter.formatCellValue(cell);
				if (Arrays.asList(SkipArray).contains(field))
				{ Fields.add(""); continue; }
				
				// Manage attribute mapping
				for (String mapping : MappingArray)
				{
					if (mapping.contains(field + "="))
					{ field = mapping.substring(field.length() + 1); break; }
				}
					
				IMetaPrimitive attribute = metaEntity.getMetaPrimitive(field);
				if (attribute == null) { throw new Exception("Attribute " + field + " not found in the entity"); }
					
				Fields.add(field);
			}

			List<IMendixObject> EntityList = new ArrayList<IMendixObject>();
			IMendixObject object;
			for (int i = TitleRowNum.intValue(); i < rowTotal; i++)
			{
				row = sheet.getRow(i);
				if (row == null) { continue; }
				
				object = Core.instantiate(context, ListType);
				for (int j = 0; j < Fields.size(); j++)
				{
					if (Fields.get(j) == "") { continue; } // Attribute skipped

					cell = row.getCell(j);
					if (cell == null) { continue; }
					
					object.setValue​(context, Fields.get(j), formatter.formatCellValue(cell));
				}
				EntityList.add(object);
			}
			
			return EntityList;
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
		return "ExcelToList";
	}

	// BEGIN EXTRA CODE
	protected static ILogNode logger = Core.getLogger("Advanced_Excel");
	// END EXTRA CODE
}
