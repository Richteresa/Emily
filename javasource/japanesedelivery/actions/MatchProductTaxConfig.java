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
import japanesedelivery.proxies.ProductTaxConfig;

public class MatchProductTaxConfig extends CustomJavaAction<IMendixObject>
{
	private java.lang.String itemName;
	private java.util.List<IMendixObject> __productTaxConfigList;
	private java.util.List<japanesedelivery.proxies.ProductTaxConfig> productTaxConfigList;

	public MatchProductTaxConfig(IContext context, java.lang.String itemName, java.util.List<IMendixObject> productTaxConfigList)
	{
		super(context);
		this.itemName = itemName;
		this.__productTaxConfigList = productTaxConfigList;
	}

	@java.lang.Override
	public IMendixObject executeAction() throws Exception
	{
		this.productTaxConfigList = java.util.Optional.ofNullable(this.__productTaxConfigList)
			.orElse(java.util.Collections.emptyList())
			.stream()
			.map(__productTaxConfigListElement -> japanesedelivery.proxies.ProductTaxConfig.initialize(getContext(), __productTaxConfigListElement))
			.collect(java.util.stream.Collectors.toList());

		// BEGIN USER CODE
//		throw new com.mendix.systemwideinterfaces.MendixRuntimeException("Java action was not implemented");
		for(ProductTaxConfig perConfig : productTaxConfigList){
			if(perConfig.getproductName().contains(itemName)
					|| itemName.contains(perConfig.getproductName())){
				return perConfig.getMendixObject();
			}
		}
		return null;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "MatchProductTaxConfig";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
