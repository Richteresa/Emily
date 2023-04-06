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
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import advanced_excel.Utils;
import org.apache.poi.ss.usermodel.Workbook;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import com.mendix.systemwideinterfaces.core.IMendixObject;

/**
 * Write WorkBook in Advanced Excel object
 */
public class Workbook_WriteFile extends CustomJavaAction<java.lang.Boolean>
{
	private IMendixObject __ExcelFile;
	private advanced_excel.proxies.AdvancedExcel ExcelFile;

	public Workbook_WriteFile(IContext context, IMendixObject ExcelFile)
	{
		super(context);
		this.__ExcelFile = ExcelFile;
	}

	@java.lang.Override
	public java.lang.Boolean executeAction() throws Exception
	{
		this.ExcelFile = this.__ExcelFile == null ? null : advanced_excel.proxies.AdvancedExcel.initialize(getContext(), __ExcelFile);

		// BEGIN USER CODE
		try
		{
			Workbook workbook = Utils.GetWorkBook();
			if (workbook == null) {
				logger.error("ERROR in Advanced_Excel.Workbook_WriteFile:\nWorkbook object not found\nyou have to use Workbook_Create, Workbook_Open or Workbook_LoadFile to create the Workbook object");
				return false;
			} 
			
			try (ByteArrayOutputStream outStream = new ByteArrayOutputStream()) {
				workbook.write(outStream);
				try (ByteArrayInputStream inputStream = new ByteArrayInputStream(outStream.toByteArray())) {
					Core.storeFileDocumentContent(getContext(), ExcelFile.getMendixObject(), inputStream);
				}
			}
			
			return true;
		} catch (Exception e) {
			logger.error("ERROR in Advanced_Excel.Workbook_WriteFile: " + e.getMessage() + "\n" + e.toString(), e);
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
		return "Workbook_WriteFile";
	}

	// BEGIN EXTRA CODE
	protected static ILogNode logger = Core.getLogger("Advanced_Excel");
	// END EXTRA CODE
}
