// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package japanesedelivery.actions;

import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import com.mendix.systemwideinterfaces.core.IMendixObject;

public class PdfToJpg extends CustomJavaAction<IMendixObject>
{
	private IMendixObject __sourceFile;
	private system.proxies.FileDocument sourceFile;

	public PdfToJpg(IContext context, IMendixObject sourceFile)
	{
		super(context);
		this.__sourceFile = sourceFile;
	}

	@java.lang.Override
	public IMendixObject executeAction() throws Exception
	{
		this.sourceFile = this.__sourceFile == null ? null : system.proxies.FileDocument.initialize(getContext(), __sourceFile);

		// BEGIN USER CODE
		throw new com.mendix.systemwideinterfaces.MendixRuntimeException("Java action was not implemented");
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "PdfToJpg";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
