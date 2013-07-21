
package com.laifu.livecolorful.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.laifu.livecolorful.parser.DefaultJSONData;
import com.laifu.livecolorful.tool.Constant;
import com.laifu.livecolorful.tool.Tools;
import com.laifu.livecolorful.tools.CurrentTime;



/**
 * 类名：JuMeiConnective.java
 * 备注：联网方法
 */
public class LaifuConnective {
	/**是否调试*/
//	private static final boolean debug = true;	
	
	/** 网络连接超时时间，默认是15S */
	public static int TIMEOUT_TIME = 15000;

	/** 传给服务器的编码格式,默认是UTF-8 */
	public static String encoding = HTTP.UTF_8;
	
	
	private static Lock lockPostRequestAndParseByMemCache = new ReentrantLock();
	/**
	 * 
	 * @param context   Activity
	 * @param url       接口地址
	 * @param method    接口方法名称
	 * @param param     接口参数
	 * @param jsonData  jsonData数据
	 * @return
	 * responseValue  1代表正常，0代表其它错误 ，2代表服务端相应错误，3代表解析错误
	 */

	public static int PostRequestAndParse(Context contextSrc, String path,
			Map<String, String> param, DefaultJSONData jsonData) {
		
		int nRet = 0;
		if (null == contextSrc) {
			nRet = 90000 + 1;
			return nRet;
		}
		if (null == path) {
			nRet = 90000 + 2;
			return nRet;
		}
		if (null == param) {
			nRet = 90000 + 3;
			return nRet;
		}
		if (null == jsonData) {
			nRet = 90000 + 4;
			return nRet;
		}
		
		Context context = contextSrc.getApplicationContext();
		if (null == context) {
			nRet = 90000 + 6;
			return nRet;
		}
		
		StringBuffer sResponseBuffer = new StringBuffer();
		nRet  = PostRequestT(context, Constant.URL_PREFIX, path, param, sResponseBuffer);
		if (1 == nRet) {
			nRet = ParseRespData(sResponseBuffer.toString(), jsonData);
		}
		return nRet;
	}	
	
	
	public static int PostRequestAndParse(Context contextSrc, String URL_PREFIX, String path,
			Map<String, String> param, DefaultJSONData jsonData) {
		
		int nRet = 0;
		if (null == contextSrc) {
			nRet = 90000 + 1;
			return nRet;
		}
		if (null == path) {
			nRet = 90000 + 2;
			return nRet;
		}
		if (null == param) {
			nRet = 90000 + 3;
			return nRet;
		}
		if (null == jsonData) {
			nRet = 90000 + 4;
			return nRet;
		}		
		
		Context context = contextSrc.getApplicationContext();
		if (null == context) {
			nRet = 90000 + 6;
			return nRet;
		}
		
		StringBuffer sResponseBuffer = new StringBuffer();
		nRet  = PostRequestT(context, URL_PREFIX, path, param, sResponseBuffer);
		if (1 == nRet) {
			nRet = ParseRespData(sResponseBuffer.toString(), jsonData);
		}
		return nRet;
	}	
	
	
	/**
	 * 
	 * @param context   Activity
	 * @param url       接口地址
	 * @param method    接口方法名称
	 * @param param     接口参数
	 * @param jsonData  jsonData数据
	 * @param isUseCache  是否可以使用缓存中的数据
	 * @param maxCacheTimeSecond  缓存的有效时间
	 * @return
	 * responseValue  1代表正常，0代表其它错误 ，2代表服务端相应错误，3代表解析错误
	 * 注意: 使用此方法时 参数 jsonData 变量一定要先new 一个, 因为不 new 的话存储为 原先的地址. 相当把原有的内容改变了
	 */

	public static int PostRequestAndParseByMemCache(
			Context contextSrc, String path, Map<String, String> param,
			DefaultJSONData jsonData, 
			List<DefaultJSONData> lsRet, 
			boolean isMemUseCache,
			long maxCacheTimeSecond, boolean isUserSDCache) {

		DefaultJSONData jsonDataRet = null;
		

		int nRet = 0;
		if (null == contextSrc) {
			nRet = 90000 + 1;
			return nRet;
		}
		if (null == path) {
			nRet = 90000 + 2;
			return nRet;
		}
		if (null == param) {
			nRet = 90000 + 3;
			return nRet;
		}
		if (null == jsonData) {
			nRet = 90000 + 4;
			return nRet;
		}
		if (null == lsRet) {
			nRet = 90000 + 5;
			return nRet;
		}
		
		Context context = contextSrc.getApplicationContext();
		if (null == context) {
			nRet = 90000 + 6;
			return nRet;
		}
		
		SharedPreferences preferencesHTTPHEAD = context.getSharedPreferences(
				Constant.HTTPHEAD, Context.MODE_PRIVATE);
		String strKey = DefaultJSONDataCacheManager.getInstance().GetHashKey(
				path, param,"全国");
		/*preferencesHTTPHEAD.getString(Constant.POSTCODE, "")*/

		Log.i("connect",
				"--PostRequestAndParseByMemCache:" + path + " | param:"
						+ param.toString() + " |key(" + strKey + ")");

		
		lockPostRequestAndParseByMemCache.lock();
		try {
			if (isMemUseCache) {
				DefaultJSONData temp = DefaultJSONDataCacheManager
						.getInstance().LoadDefaultJSONData(strKey,
								maxCacheTimeSecond);
				if (null == temp) {

					nRet = PostRequestAndParseBySDCache(contextSrc, path, param,
							jsonData, isUserSDCache, 30 * 24 * 60 * 60, true);

					if (null != jsonData && 1 == nRet) {
						jsonDataRet = jsonData;
						DefaultJSONDataCacheManager.getInstance()
								.SaveDefaultJSONData(strKey, jsonData);
//						Log.i("aa", "save---"+strKey+"--"+jsonData.toString());
					}

				} else {

					jsonDataRet = temp;
//					Log.i("aa", "get---"+strKey+"--"+jsonData.toString());
					nRet = 1;
				}

			} else {
				nRet = PostRequestAndParseBySDCache(contextSrc, path, param,
						jsonData, isUserSDCache, 30 * 24 * 60 * 60, true);
				if (null != jsonData && 1 == nRet) {
					jsonDataRet = jsonData;
					DefaultJSONDataCacheManager.getInstance()
							.SaveDefaultJSONData(strKey, jsonData);
				}
//				return nRet;
			}
		} finally {
			lockPostRequestAndParseByMemCache.unlock();
		}

		if(jsonDataRet!=null){
			lsRet.add( jsonDataRet );
		}
		
		return nRet;
	}
	
	
	public static int PostRequestAndParseBySDCache(Context contextSrc,
			String path, Map<String, String> param,
			DefaultJSONData jsonData, //
			boolean isUseCache, 
			long maxCacheTimeSecond,
			boolean isSaveResponseToSD) {

		int nRet = 0;
		if (null == contextSrc) {
			nRet = 90000 + 1;
			return nRet;
		}
		if (null == path) {
			nRet = 90000 + 2;
			return nRet;
		}
		if (null == param) {
			nRet = 90000 + 3;
			return nRet;
		}
		if (null == jsonData) {
			nRet = 90000 + 4;
			return nRet;
		}
		
		Context context = contextSrc.getApplicationContext();
		if (null == context) {
			nRet = 90000 + 6;
			return nRet;
		}
		
		StringBuffer sResponseBuffer = new StringBuffer();

		ArrayList<BasicNameValuePair> postData = new ArrayList<BasicNameValuePair>();
		for (Map.Entry<String, String> m : param.entrySet()) {
			postData.add(new BasicNameValuePair(m.getKey(), m.getValue()));
		}
		String strRequstUrl = Constant.URL_PREFIX + path;

		SharedPreferences preferencesHTTPHEAD = context.getSharedPreferences(
				Constant.HTTPHEAD, Context.MODE_PRIVATE);

		String strHashKey = HttpResponseCacheManager.getInstance().GetHashKey(
				strRequstUrl, postData.toString(),
				"全国");// 城市参数
		/*preferencesHTTPHEAD.getString(Constant.POSTCODE, "")*/
		if (isUseCache) {
			HttpResponseInfo responseInfo = HttpResponseCacheManager
					.getInstance().LoadHttpResponseInfo(strHashKey,
							maxCacheTimeSecond);
			if (null != responseInfo) {
				String strResponse = responseInfo.getResponseData();
				nRet = ParseRespData(strResponse, jsonData);
				
			
				return nRet;
			}
		}

		nRet = PostRequestT(context, Constant.URL_PREFIX, path, param, sResponseBuffer);
		if (1 == nRet) {
			
			nRet = ParseRespData(sResponseBuffer.toString(), jsonData);
			if (1 == nRet) {
				if (isUseCache || isSaveResponseToSD) {
					// 把http请求结果加入缓存 能成功解析才加入
					HttpResponseCacheManager.getInstance()
							.SaveHttpResponseInfo(
									new HttpResponseInfo(strHashKey, sResponseBuffer
											.toString()));
				}
			}
		}
		
	
		return nRet;
	}	
	
	/**
	 * 如果不能从服务器获取正常回应包,会重试一次
	 * @param context
	 * @param URL_PREFIX
	 * @param path
	 * @param param
	 * @param sResponseBuffer
	 * @return
	 */
	private static int PostRequestT(Context context, String URL_PREFIX,
			String path, Map<String, String> param, StringBuffer sResponseBuffer) {

		int nRet = PostRequestOnce(context, Constant.URL_PREFIX, path, param,
				sResponseBuffer);
		if (1 != nRet && nRet < 30000) // 解析错误为30000+ ,出现解析错误不重试
		{
			nRet = PostRequestOnce(context, Constant.URL_PREFIX, path, param,
					sResponseBuffer);
		}
		return nRet;
	}
	
	private static int PostRequestOnce(Context context, String URL_PREFIX , String path ,
			Map<String, String> param,
			StringBuffer sResponseBuffer) {		
		int nRet = 10000;
		
		StringBuilder sLogBuilder = new StringBuilder();
		StringBuilder sLogCookiesBuilder =  new StringBuilder();
		StringBuilder sTimeLogBuilder = new StringBuilder();
		StringBuilder sExceptionLogBuilder = new StringBuilder();
		
		
		CurrentTime.startTime("请求前网络时间");	
			
		
		long startTimeHTTP = System.currentTimeMillis();		
		

		// 封装传送的数据
		ArrayList<BasicNameValuePair> postData = new ArrayList<BasicNameValuePair>();
			for (Map.Entry<String, String> m : param.entrySet()) {
				postData.add(new BasicNameValuePair(m.getKey(), m.getValue()));
		}
		
		String strRequstUrl = URL_PREFIX + path;	
		Log.d("URL_PREFIX", strRequstUrl);
	
		SharedPreferences preferencesHTTPHEAD = context.getSharedPreferences(
				Constant.HTTPHEAD, Context.MODE_PRIVATE);
		String strHashKey = HttpResponseCacheManager.getInstance().GetHashKey(
				strRequstUrl, postData.toString(),
				"全国");
		/*preferencesHTTPHEAD.getString(Constant.POSTCODE, "")*/
					
		HttpPost httpPost = new HttpPost(strRequstUrl);
		String strHeader = (new Tools()).createHttpHeaderCookData(context);
		httpPost.setHeader(Constant.COOKIE, strHeader);
		if(Constant.isUseGZIP)
		{
			httpPost.addHeader("Accept-Encoding", "GZIP"); 
		}
		// 设置连接网络超时时间
		BasicHttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams,
				TIMEOUT_TIME);
		HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_TIME);

		DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);
		HttpResponse response = null;
		
		try {
			
			nRet = 20000 ;
			
			sLogBuilder.append("--PostRequest:").append(path)
					.append(" |param: ").append(param.toString())
					.append(" |request head(").append(strHeader.toString())
					.append(")");
			
			Log.i("connect", sLogBuilder.toString());
			
			// 对请求的数据进行UTF-8转码
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postData,
					encoding);
			httpPost.setEntity(entity);
			
			
			response = httpClient.execute(httpPost);
			
			Log.e("connect", response.getAllHeaders().toString() + "");
			
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				
				nRet = 1;
			} else {
				nRet = 10000 + response.getStatusLine().getStatusCode();
				httpPost.abort();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			nRet = 20000 + 1;
			
			sExceptionLogBuilder.append(e.toString());

		} catch (ClientProtocolException e) {
			e.printStackTrace();
			nRet = 20000 + 2;
			
			sExceptionLogBuilder.append(e.toString());

		} catch (java.net.UnknownHostException e) {
			e.printStackTrace();
			nRet = 20000 + 10;
			sExceptionLogBuilder.append(e.toString());
		} catch (ConnectTimeoutException e) {
			e.printStackTrace();
			nRet = 20000 + 11;
			sExceptionLogBuilder.append(e.toString());
		} catch (IOException e) {
			e.printStackTrace();
			nRet = 20000 + 3;

			sExceptionLogBuilder.append(e.toString());
		} catch(Exception e){
			e.printStackTrace();
			nRet = 40000 + 3;
			
			sExceptionLogBuilder.append(e.toString());
		}
		
		long endTimeHTTP = System.currentTimeMillis();
		
		// 响应正常	
		if ( 1 == nRet) {
			try {
				HttpEntity httpEntity = response.getEntity();
				InputStream inputStream = httpEntity.getContent();

				if (null != httpEntity.getContentEncoding() && null != httpEntity.getContentEncoding().getValue()) {
					if (httpEntity.getContentEncoding().getValue()
							.toUpperCase().contains("GZIP")) {
						inputStream = new GZIPInputStream(inputStream);
					}
				}
				
				
				List<Cookie> cookies  = httpClient.getCookieStore().getCookies();
                if(cookies.isEmpty()){
//                	JuMeiLogMng.getInstance().d("cookies  is  ", "null");
                }else{
                	
                	sLogCookiesBuilder.append(cookies.toString());
                	
                	SharedPreferences preferences = context.getSharedPreferences(Constant.HTTPHEAD, Context.MODE_PRIVATE);	
                	Editor edit = preferences.edit();
                	String sName = "";
                	String sValue ="";
                	for (int i = 0; i < cookies.size(); i++) {
                		// 打印cookie
						sName = cookies.get(i).getName();
						sValue = cookies.get(i).getValue();						
//                		JuMeiLogMng.getInstance().i("aa", sName+"--->"+sValue);                		
                		
						
						if( Constant.PHPSESSID.equalsIgnoreCase(sName)){
							if (!preferences.getString(Constant.PHPSESSID, "").equals(sValue)) {
								edit.putString(Constant.PHPSESSID, sValue).commit();
							}
						}else if(Constant.JUMEI_JHC.equalsIgnoreCase(sName)){
							if (!preferences.getString(Constant.JUMEI_JHC, "").equals(sValue)) {
							 edit.putString(Constant.JUMEI_JHC, sValue).commit();
							}
						}else if(Constant.UID.equalsIgnoreCase(sName)){
							if (!preferences.getString(Constant.UID, "").equals(sValue)) {
							 edit.putString(Constant.UID, sValue).commit();
							}
						}else if(Constant.ACCOUNT.equalsIgnoreCase(sName)){
							if (!preferences.getString(Constant.ACCOUNT, "").equals(sValue)) {
							 edit.putString(Constant.ACCOUNT, sValue).commit();
							}
						}else if(Constant.NICKNAME.equalsIgnoreCase(sName)){
							
							if (!preferences.getString(Constant.NICKNAME, "").equals(sValue)) {	
								
							 edit.putString(Constant.NICKNAME, sValue).commit();
							}
							
						}else if(Constant.TK.equalsIgnoreCase(sName)){
							if (!preferences.getString(Constant.TK, "").equals(sValue)) {
							edit.putString(Constant.TK, sValue).commit();
							}
						}else if(Constant.COOKIE_VER.equalsIgnoreCase(sName)){
							if (!preferences.getString(Constant.COOKIE_VER, "").equals(sValue)) {
							edit.putString(Constant.COOKIE_VER, sValue).commit();
							}
						}
					}                	
                	
                }

				//StringBuffer buff = new StringBuffer();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(inputStream));
				String temp = null;
				while ((temp = reader.readLine()) != null) {
					sResponseBuffer.append(temp);
				}				

				if (null != inputStream) { 
					inputStream.close();
				}
				

				Log.e("connect", sResponseBuffer.toString() + "");
 
				nRet = 1;// 解析成功

			} catch (IllegalStateException e) {
				e.printStackTrace();
				nRet = 20000 +4;
				
				sExceptionLogBuilder.append(e.toString());
			} catch (IOException e) {
				e.printStackTrace();
				nRet = 20000 + 5;
				
				sExceptionLogBuilder.append(e.toString());
			} catch (Exception e) {
				e.printStackTrace();
				nRet = 20000 + 6;
				
				sExceptionLogBuilder.append(e.toString());
			} 
		}
		
		long endTimeSaveCookie  = System.currentTimeMillis();
		
		CurrentTime.endTime();	
	
		sLogBuilder.append("--PostRequest:").append(path).append(" |param: ")
				.append(param.toString()).append(" |return responseValue(")
				.append(nRet).append(") cookie(")
				.append(sLogCookiesBuilder.toString())
				.append(") response(")
				.append(sResponseBuffer.toString()).append(") exception(")
				.append(sExceptionLogBuilder.toString()).append(")");
				
		Log.i("connect", sLogBuilder.toString());	
		
		sTimeLogBuilder.append("--PostRequest:").append(path).append(" |param: ")
		.append(param.toString()).append(" |return responseValue(")
		.append(nRet).append(")")
		.append("http use(").append(endTimeHTTP - startTimeHTTP)
		.append("ms)").append("http savecookie(")
		.append(endTimeSaveCookie - endTimeHTTP).append("ms)");
		Log.i("connectTime", sTimeLogBuilder.toString());	
		

		
		return nRet;
	}
	
	private static int ParseRespData(String sResponse, DefaultJSONData jsonData) {
		int nRet = 30000;
		try {
			int index=sResponse.indexOf("{");
			sResponse=sResponse.substring(index);
			if (sResponse.startsWith("{")) {
				JSONObject object = new JSONObject(sResponse); 
				jsonData.parse(object);
				nRet = 1;

			} else if (sResponse.startsWith("[")) {
				JSONArray object = new JSONArray(sResponse.toString());
				jsonData.parse(object);
				nRet = 1;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			nRet = 30000 + 1;
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			nRet = 30000 + 2;
		} catch (Exception e) {
			e.printStackTrace();
			nRet = 30000 + 3;
		}
		return nRet;
	}
	
	public static String getPromptMsg(int nResponseValue)
	{
		String sMsg = "";
		String sErrCode = "(错误代码：" + nResponseValue + ")";
		switch(nResponseValue)
		{
		case 1:
			sMsg = "获取数据成功";
			break;
			
		case 10400:
		case 10401:
		case 10402:
			sMsg = "错误的请求.请稍后再试" ;
			break;
		case 10403:
			sMsg = "您请求服务器过于频繁, 服务器暂时拒绝您访问" ;
			break;

		case 10404:
			sMsg = "找不到请求的接口地址" ;
			break;

		case 10405:
			sMsg = "您访问服务器太频繁,服务器暂时禁止您访问" ;
			break;

		case 10408:
		case 10503:
			sMsg = "服务器请求超时!请稍后再试" ;
			break;
			
		case 10500:
		case 10501:
		case 10502:
		case 10504:
		case 10505:
			sMsg = "对不起,服务器正在维护中!请稍后再试" ;
//			sMsg = "服务器暂时无法连接，请稍后再试";
			break;
			
		case 20001:
			sMsg = "内容的编码方式不支持" ;
			break;
		case 20002:
			sMsg = "客户端处理协议出错" ;
			break;
		case 20003:
			sMsg = "文件处理出错" ;
			break;
		case 20004:
			sMsg = "状态非法" ;
			break;
		case 20005:
			sMsg = "读取输出流时文件处理出错" ;
			break;
		case 20006:
			sMsg = "处理时出现错误" ;
			break;
		case 20007:
		case 20008:
		case 20009:
			
//			sMsg = "对不起,请求服务器时出现异常,获取数据失败!请您呆会儿再刷新";
			sMsg = "网络传输数据时出现了错误，请稍后再试" ;
			break;
			
		case 20010:
//			sMsg = "对不起,请求服务器时出现异常,获取数据失败!请您呆会儿再刷新";
			sMsg = "域名无法解析，请检查您网络的DNS设置" ;
			break;			
		case 20011:
//			sMsg = "对不起,请求服务器时出现异常,获取数据失败!请您呆会儿再刷新";
			sMsg = "请求服务器超时，请检查您网络" ;
			break;
			
		case 30001:
		case 30002:
		case 30003:
		case 30004:
		case 30005:
		case 30006:
		case 30007:
		case 30008:
		case 30009:
		case 30010:
//			sMsg = "数据解析失败，请稍后再试";
			sMsg = "服务器返回数据异常，请稍后再试" ;
			break;
			
			
		case 90001:
		case 90002:
		case 90003:
		case 90004:
		case 90005:	
		case 90006:	
			sMsg = "参数错误，请稍后再试" ;
			break;
		default:
//			sMsg = "获取数据失败!请您呆会儿再刷新";
			sMsg = "服务器暂时无法连接，请稍后再试" ;
			
			break;
		}
		
		sMsg = "当前网络异常，请稍后刷新！";
		
		return  sMsg + "\n"+ sErrCode ;
	}


	public static int RemoveCache(
			Context context, String path, Map<String, String> param) {

		int nRet = 0;
		
		SharedPreferences preferencesHTTPHEAD = context.getSharedPreferences(
				Constant.HTTPHEAD, Context.MODE_PRIVATE);
		String strMemKey = DefaultJSONDataCacheManager.getInstance().GetHashKey(
				path, param,
				"全国");
		/*preferencesHTTPHEAD.getString(Constant.POSTCODE, "")*/
		
		ArrayList<BasicNameValuePair> postData = new ArrayList<BasicNameValuePair>();
		for (Map.Entry<String, String> m : param.entrySet()) {
			postData.add(new BasicNameValuePair(m.getKey(), m.getValue()));
		}
		String strRequstUrl = Constant.URL_PREFIX + path;		

		String strSDHashKey = HttpResponseCacheManager.getInstance().GetHashKey(
				strRequstUrl, postData.toString(),
				"全国");
		/*preferencesHTTPHEAD.getString(Constant.POSTCODE, "")*/
		
		lockPostRequestAndParseByMemCache.lock();

		try {
			DefaultJSONDataCacheManager.getInstance().RemoveDefaultJSONData(
					strMemKey);
			HttpResponseCacheManager.getInstance().RemoveHttpResponseInfo(
					strSDHashKey);

		} finally {
			lockPostRequestAndParseByMemCache.unlock();
		}

		nRet = 1;
		
		return nRet;
	}
	
	
}

