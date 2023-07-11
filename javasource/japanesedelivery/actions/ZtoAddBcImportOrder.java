// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package japanesedelivery.actions;

import cn.hutool.json.JSONObject;
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
import japanesedelivery.proxies.ZtoImportBcOrder;
import japanesedelivery.proxies.ZtoIntlImportOrderResp;
import japanesedelivery.proxies.ZtoIntlOrderItem;
import japanesedelivery.proxies.ZtoOrderEntity;
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
        Map<String, Object> ztoImportBcOrderMap = addBcImportOrder(ztoImportBcOrder,ztoOrderEntity,ztoIntlOrderItemList);
        ILogNode logger = Core.getLogger("JapaneseDelivery");
        String secretKey = "7r*cQSA#";
        long timestamp = System.currentTimeMillis();
        String code = "";
        String message = "";
        String messageDetail = "";
        String logisticsId = "";
        String orderId = "";
        String orderNo = "";
        String extended = "";
        String mark = "";

        // 接口测试地址: https://izop-test.zt-express.com/oms/api
        // 接口生产地址: https://izop.zt-express.com/oms/api
        String urlAddress = "https://izop-test.zt-express.com/oms/api?";
        String addBcImportOrderMethod = "addBcImportOrder";
        String queryBigMarkMethod = "queryBigMark";
        IMendixObject respVO = Core.instantiate(getContext(), ZtoIntlImportOrderResp.getType());
        try {
            String invokeResult = invokeZto(urlAddress,addBcImportOrderMethod,"10661",secretKey, JSONUtil.toJsonStr(ztoImportBcOrderMap));
            logger.info("ZTO "+addBcImportOrderMethod+" response: " + invokeResult);
            String responseData = "";
            JSONObject zTOResponseBody = new JSONObject(invokeResult);
            Boolean success = (Boolean) zTOResponseBody.get("success");
            if (success) {
                responseData = HttpInvoke.getDecodeData(secretKey, (String) zTOResponseBody.get("data"));
                logger.info("ZTO " + addBcImportOrderMethod + " response Decode Data: " + responseData);
                JSONObject zTOResponseData = new JSONObject(responseData);
                logisticsId = (String) zTOResponseData.get("logisticsId");
                orderId = (String) zTOResponseData.get("orderId");
                orderNo = (String) zTOResponseData.get("orderNo");
                Map<String, Object> ztoQueryBigMarkMap =queryBigMark(ztoImportBcOrder);
                invokeResult = invokeZto(urlAddress,queryBigMarkMethod,"10661",secretKey, JSONUtil.toJsonStr(ztoQueryBigMarkMap));
                logger.info("ZTO " + queryBigMarkMethod + " response: " + invokeResult);
                zTOResponseBody = new JSONObject(invokeResult);
                success = (Boolean) zTOResponseBody.get("success");
                if (success) {
                    responseData = HttpInvoke.getDecodeData(secretKey, (String) zTOResponseBody.get("data"));
                    logger.info("ZTO " + queryBigMarkMethod + " response Decode Data: " + responseData);
                    extended = (String) zTOResponseData.get("responseData");
                    mark =(String) new JSONObject(responseData).get("mark");
                }
            }

            if(!success) {
                JSONObject errorBody = new JSONObject(zTOResponseBody.get("error"));
                if (errorBody != null) {
                    JSONObject zTOResponseError = new JSONObject(errorBody);
                    code = (String) zTOResponseError.get("code");
                    message = (String) zTOResponseError.get("message");
                    messageDetail = zTOResponseError.get("validationError")+"";
                }
            }
            respVO.setValue(getContext(), String.valueOf(ZtoIntlImportOrderResp.MemberNames.logisticsId), logisticsId);
            respVO.setValue(getContext(), String.valueOf(ZtoIntlImportOrderResp.MemberNames.orderId), orderId);
            respVO.setValue(getContext(), String.valueOf(ZtoIntlImportOrderResp.MemberNames.orderNo), orderNo);
            respVO.setValue(getContext(), String.valueOf(ZtoIntlImportOrderResp.MemberNames.extended), extended);
            respVO.setValue(getContext(), String.valueOf(ZtoIntlImportOrderResp.MemberNames.mark), mark);
            respVO.setValue(getContext(), String.valueOf(ZtoIntlImportOrderResp.MemberNames.message), message);
            respVO.setValue(getContext(), String.valueOf(ZtoIntlImportOrderResp.MemberNames.messageDetail),messageDetail);
            respVO.setValue(getContext(), String.valueOf(ZtoIntlImportOrderResp.MemberNames.code), code);
            respVO.setValue(getContext(), String.valueOf(ZtoIntlImportOrderResp.MemberNames.success), success);
            return respVO;
        } catch (Exception e) {
            throw new com.mendix.systemwideinterfaces.MendixRuntimeException("Abnormal interface of the courier company!");
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
		return "ZtoAddBcImportOrder";
	}

	// BEGIN EXTRA CODE
    public String invokeZto(String uri, String method, String appCode,String secretKey,String data) throws Exception {
        ILogNode logger = Core.getLogger("JapaneseDelivery");
        long timestamp = System.currentTimeMillis();
        String url = buildUrl(uri, method, appCode, timestamp);
        String encodeData = getEncodeData(secretKey, data, timestamp);
        logger.info("ZTO request "+method+" : url:" + url  + "; request Data: " + data+ "; encode Data: " + encodeData);
        return HttpUtil.sendPostJson(url, encodeData);
    }

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

    private static Map<String, Object> addBcImportOrder(ZtoImportBcOrder ztoImportBcOrder, ZtoOrderEntity ztoOrderEntity, java.util.List<ZtoIntlOrderItem> ztoIntlOrderItemList) throws Exception {
        // addBcImportOrder（创建进口订单-直邮）
        Map<String, Object> ztoImportBcOrderMap = new HashMap<>();
        ztoImportBcOrderMap.put("orderId", ztoImportBcOrder.getorderId());
        ztoImportBcOrderMap.put("consignee", ztoImportBcOrder.getconsignee());
        ztoImportBcOrderMap.put("consigneeAddress", ztoImportBcOrder.getconsigneeAddress());
        ztoImportBcOrderMap.put("consigneeCity", ztoImportBcOrder.getconsigneeCity());
        ztoImportBcOrderMap.put("consigneeCountry", ztoImportBcOrder.getconsigneeCountry());
        ztoImportBcOrderMap.put("consigneeDistrict", ztoImportBcOrder.getconsigneeDistrict());
        ztoImportBcOrderMap.put("consigneeMobile", ztoImportBcOrder.getconsigneeMobile());
        ztoImportBcOrderMap.put("consigneeProv", ztoImportBcOrder.getconsigneeProv());
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
        for (ZtoIntlOrderItem intlOrderItem : ztoIntlOrderItemList) {
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
        return ztoImportBcOrderMap;
    }

    private static Map<String, Object> queryBigMark(japanesedelivery.proxies.ZtoImportBcOrder ztoImportBcOrder) throws Exception {
        // queryBigMark(获取大头笔),客户打印中通电子面单时，需要调用此接口获取大头笔信息，大头笔用于国内运输中转。
        // 收件人地址
        Map<String, Object> ztoReceiverAddressMap = new HashMap<>();
        ztoReceiverAddressMap.put("address", ztoImportBcOrder.getconsigneeAddress());
        ztoReceiverAddressMap.put("city", ztoImportBcOrder.getconsigneeCity());
        ztoReceiverAddressMap.put("district", ztoImportBcOrder.getconsigneeDistrict());
        ztoReceiverAddressMap.put("province", ztoImportBcOrder.getconsigneeProv());
        // 发件人地址
        Map<String, Object> ztoSenderAddressMap = new HashMap<>();
        ztoSenderAddressMap.put("address", "花山镇启源大道10号中通快递");
        ztoSenderAddressMap.put("city", "广州市");
        ztoSenderAddressMap.put("district", "花都区");
        ztoSenderAddressMap.put("province", "广东省");

        Map<String, Object> ztoQueryBigMarkMap = new HashMap<>();
        ztoQueryBigMarkMap.put("receiverAddress", ztoReceiverAddressMap);
        ztoQueryBigMarkMap.put("senderAddress", ztoSenderAddressMap);
        ztoQueryBigMarkMap.put("unionCode", ztoImportBcOrder.getorderId());

        return ztoQueryBigMarkMap;
    }
	// END EXTRA CODE
}
