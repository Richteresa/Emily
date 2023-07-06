// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package japanesedelivery.actions;

import com.google.common.collect.Maps;
import com.mendix.logging.ILogNode;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.core.Core;
import cn.hutool.json.JSONUtil;
import com.zto.intl.common.model.PostBody;
import com.zto.intl.common.util.DesUtil;
import com.zto.intl.common.util.HttpInvoke;
import com.zto.intl.common.util.HttpUtil;
import com.zto.intl.common.util.MD5;
import japanesedelivery.proxies.ZtoIntlImportOrderRes;
import japanesedelivery.proxies.ZtoIntlOrderItem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZtoAddBcImportOrder extends CustomJavaAction<IMendixObject>
{
	private IMendixObject __ztoImportBcOrder;
	private japanesedelivery.proxies.ZtoImportBcOrder ztoImportBcOrder;
	private java.util.List<IMendixObject> __ztoIntlOrderItemList;
	private java.util.List<japanesedelivery.proxies.ZtoIntlOrderItem> ztoIntlOrderItemList;
	private IMendixObject __ztoOrderEntity;
	private japanesedelivery.proxies.ZtoOrderEntity ztoOrderEntity;

	public ZtoAddBcImportOrder(IContext context, IMendixObject ztoImportBcOrder, java.util.List<IMendixObject> ztoIntlOrderItemList, IMendixObject ztoOrderEntity)
	{
		super(context);
		this.__ztoImportBcOrder = ztoImportBcOrder;
		this.__ztoIntlOrderItemList = ztoIntlOrderItemList;
		this.__ztoOrderEntity = ztoOrderEntity;
	}

	@java.lang.Override
	public IMendixObject executeAction() throws Exception
	{
		this.ztoImportBcOrder = this.__ztoImportBcOrder == null ? null : japanesedelivery.proxies.ZtoImportBcOrder.initialize(getContext(), __ztoImportBcOrder);

		this.ztoIntlOrderItemList = java.util.Optional.ofNullable(this.__ztoIntlOrderItemList)
			.orElse(java.util.Collections.emptyList())
			.stream()
			.map(__ztoIntlOrderItemListElement -> japanesedelivery.proxies.ZtoIntlOrderItem.initialize(getContext(), __ztoIntlOrderItemListElement))
			.collect(java.util.stream.Collectors.toList());

		this.ztoOrderEntity = this.__ztoOrderEntity == null ? null : japanesedelivery.proxies.ZtoOrderEntity.initialize(getContext(), __ztoOrderEntity);

		// BEGIN USER CODE
		Map<String, Object> ztoImportBcOrderMap = new HashMap<>();
		ztoImportBcOrderMap.put("orderId",ztoImportBcOrder.getorderId());
//		ztoImportBcOrderMap.put("logisticsId", ztoImportBcOrder.getlogisticsId());
		ztoImportBcOrderMap.put("consignee", ztoImportBcOrder.getconsignee());
		ztoImportBcOrderMap.put("consigneeAddress", ztoImportBcOrder.getconsigneeAddress());
		ztoImportBcOrderMap.put("consigneeCity", ztoImportBcOrder.getconsigneeCity());
		ztoImportBcOrderMap.put("consigneeCountry", ztoImportBcOrder.getconsigneeCountry());
		ztoImportBcOrderMap.put("consigneeDistrict", ztoImportBcOrder.getconsigneeDistrict());
		ztoImportBcOrderMap.put("consigneeMobile", ztoImportBcOrder.getconsigneeMobile());
		ztoImportBcOrderMap.put("consigneeProv", ztoImportBcOrder.getconsigneeProv());
//		ztoImportBcOrderMap.put("consigneeTelephone", ztoImportBcOrder.getconsigneeTelephone());
		ztoImportBcOrderMap.put("consigneeZipCode", ztoImportBcOrder.getconsigneeZipCode());
		ztoImportBcOrderMap.put("customsCode", ztoImportBcOrder.getcustomsCode());
		ztoImportBcOrderMap.put("customerId", ztoImportBcOrder.getcustomerId());
		ztoImportBcOrderMap.put("idType", ztoImportBcOrder.getidType());
		ztoImportBcOrderMap.put("ieType", ztoImportBcOrder.getieType());
		ztoImportBcOrderMap.put("netWeight", ztoImportBcOrder.getnetWeight());
		ztoImportBcOrderMap.put("platformSource", "10661");
		ztoImportBcOrderMap.put("shipType", ztoImportBcOrder.getshipType());
		ztoImportBcOrderMap.put("shipper", ztoImportBcOrder.getshipper());
		ztoImportBcOrderMap.put("shipperAddress", ztoImportBcOrder.getshipperAddress());
		ztoImportBcOrderMap.put("shipperCity", ztoImportBcOrder.getshipperCity());
		ztoImportBcOrderMap.put("shipperCountry", ztoImportBcOrder.getshipperCountry());
		ztoImportBcOrderMap.put("shipperDistrict", ztoImportBcOrder.getshipperDistrict());
		ztoImportBcOrderMap.put("shipperMobile", ztoImportBcOrder.getshipperMobile());
		ztoImportBcOrderMap.put("shipperProv", ztoImportBcOrder.getshipperProv());
//		ztoImportBcOrderMap.put("shipperTelephone", ztoImportBcOrder.getshipperTelephone());
//		ztoImportBcOrderMap.put("shipperZipCode", ztoImportBcOrder.getshipperZipCode());
		ztoImportBcOrderMap.put("stockFlag", ztoImportBcOrder.getstockFlag());
		ztoImportBcOrderMap.put("warehouseCode", "au001");
		ztoImportBcOrderMap.put("weight", ztoImportBcOrder.getweight());

		Map<String, Object> orderEntityMap = new HashMap<>();
		orderEntityMap.put("payableWeight", ztoOrderEntity.getpayableWeight());
		orderEntityMap.put("remark", ztoOrderEntity.getremark());
		orderEntityMap.put("totalShippingFee", ztoOrderEntity.gettotalShippingFee());
		orderEntityMap.put("totalShippingFeeUnit", ztoOrderEntity.gettotalShippingFeeUnit());
		orderEntityMap.put("tradeOrderValue", ztoOrderEntity.gettradeOrderValue());
		orderEntityMap.put("tradeOrderValueUnit", ztoOrderEntity.gettradeOrderValueUnit());

		ztoImportBcOrderMap.put("orderEntity", orderEntityMap);

		List<Map<String, Object>> intlOrderItemList2 = new ArrayList<>();

		for( ZtoIntlOrderItem intlOrderItem : ztoIntlOrderItemList) {
			Map<String, Object> intlOrderItemMap = new HashMap<>();
			intlOrderItemMap.put("currencyType", intlOrderItem.getcurrencyType());
			intlOrderItemMap.put("itemId", intlOrderItem.getitemId());
			intlOrderItemMap.put("itemName", intlOrderItem.getitemName());
			intlOrderItemMap.put("itemQuantity", intlOrderItem.getitemQuantity());
			intlOrderItemMap.put("itemUnit", intlOrderItem.getitemUnit());
			intlOrderItemMap.put("itemUnitPrice", intlOrderItem.getitemUnitPrice());
			intlOrderItemList2.add(intlOrderItemMap);
		}
		ztoImportBcOrderMap.put("intlOrderItemList", intlOrderItemList2);

		ILogNode logger = Core.getLogger("JapaneseDelivery");
		logger.info("ZTO request: "+JSONUtil.toJsonStr(ztoImportBcOrderMap));
		String  secretKey = "7r*cQSA#";

		long timestamp = System.currentTimeMillis();
		// 接口测试地址: https://izop-test.zt-express.com/oms/api
		// 接口生产地址: https://izop.zt-express.com/oms/api
		String url = buildUrl("https://izop-test.zt-express.com/oms/api?", "addBcImportOrder", "10661", timestamp);
		String encodeData = getEncodeData(secretKey, JSONUtil.toJsonStr(ztoImportBcOrderMap), timestamp);
		logger.info("ZTO url:"+url+ "; request encodeData: " + encodeData);

		String invokeResult = HttpUtil.sendPostJson(url, encodeData);
		logger.info("ZTO response: "+invokeResult);

		String responseBody = HttpInvoke.getDecodeData(secretKey, invokeResult);
		logger.info("ZTO response DecodeData: "+invokeResult);

		ZtoIntlImportOrderRes ztoIntlImportOrderRes = JSONUtil.toBean(responseBody, ZtoIntlImportOrderRes.class);

		IMendixObject respVO= Core.instantiate(getContext(), ZtoIntlImportOrderRes.getType());
		respVO.setValue(getContext(), String.valueOf(ZtoIntlImportOrderRes.MemberNames.logisticsId), responseBody);
		respVO.setValue(getContext(), String.valueOf(ZtoIntlImportOrderRes.MemberNames.orderId), responseBody);
		respVO.setValue(getContext(), String.valueOf(ZtoIntlImportOrderRes.MemberNames.orderNo), responseBody);
		respVO.setValue(getContext(), String.valueOf(ZtoIntlImportOrderRes.MemberNames.sortContent), responseBody);
		respVO.setValue(getContext(), String.valueOf(ZtoIntlImportOrderRes.MemberNames.bagAddress), responseBody);
		respVO.setValue(getContext(), String.valueOf(ZtoIntlImportOrderRes.MemberNames.extended), responseBody);

		return  respVO;
		// throw new com.mendix.systemwideinterfaces.MendixRuntimeException("Java action was not implemented");
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "ZtoAddBcImportOrder";
	}

	// BEGIN EXTRA CODE
	private static String buildUrl(String uri, String method, String appCode, Long timestamp) throws IOException {
		Map<String, Object> map = Maps.newHashMap();
		map.put("method", method);
		map.put("timestamp", timestamp);
		map.put("appCode", appCode);
		return HttpUtil.buildRealUrl(uri, map, "UTF-8");
	}

	public static String getEncodeData(String secretKey, String data, long timestamp) throws Exception {
		String sendData = getSendData(secretKey, data, timestamp);
		DesUtil desUtil = DesUtil.setDesKey(secretKey);
		return desUtil.encode(sendData);
	}

	private static String getSendData(String secretKey, String data, long timestamp) throws Exception {
		PostBody body = new PostBody();
		body.setSign(getSign(secretKey, data, timestamp));
		body.setData(data);
		return JSONUtil.toJsonStr(body);
	}

	private static String getSign(String secretKey, String data, long timestamp) throws Exception {
		String md5Encode = timestamp + secretKey + data;
		return MD5.MD5Encode(md5Encode);
	}
	// END EXTRA CODE
}
