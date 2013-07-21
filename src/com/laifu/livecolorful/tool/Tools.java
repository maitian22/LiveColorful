package com.laifu.livecolorful.tool;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import com.laifu.livecolorful.net.DefaultTools;


/**
 * 
 * @author wangfang 创建日期：2011-9-26 类名：Tools.java 备注：公共方法
 */
public class Tools extends DefaultTools {


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.android.library.tools.DefaultTools#createHttpHeaderMapData(android
	 * .content.Context)
	 */
	@Override
	public Map<String, String> createHttpHeaderMapData(Context context) {
		return null;
		}
	

	public String createHttpHeaderCookData(Context context) {
		Map<String, String> HttpMap = new HashMap<String, String>();
		SharedPreferences preferences = context.getSharedPreferences(Constant.HTTPHEAD, Context.MODE_PRIVATE);	
		HttpMap.put(Constant.PLATFORM,  "android");		
		HttpMap.put(Constant.CLIENT_OS, Constant.CLIENT_OS_VALUE);		
		HttpMap.put(Constant.CLIENT, preferences.getString(Constant.CLIENT, Constant.CLIENTVER));
		HttpMap.put(Constant.MODEL,  Constant.MODEL_VALUE);
		HttpMap.put(Constant.IMSI, preferences.getString(Constant.IMSI, ""));
		HttpMap.put(Constant.IMEI,  preferences.getString(Constant.IMEI, getDeviceId(context)));
		HttpMap.put(Constant.SOURCE, Constant.SOURCE_VALUE);
		HttpMap.put(Constant.JUMEI_PRODUCT, "jumei");
		HttpMap.put(Constant.LANGUAGE, preferences.getString(Constant.LANGUAGE, "zh"));
		HttpMap.put(Constant.CARRIER, preferences.getString(Constant.CARRIER, ""));
		HttpMap.put(Constant.SMSCENTER, preferences.getString(Constant.SMSCENTER, getServiceCenterAddress()));
		HttpMap.put(Constant.RESOLUTION, preferences.getString(Constant.RESOLUTION, "320*480"));
		HttpMap.put(Constant.ACCOUNT, preferences.getString(Constant.ACCOUNT, ""));
		HttpMap.put(Constant.NICKNAME, preferences.getString(Constant.NICKNAME, ""));
//		HttpMap.put(Constant.POSTCODE, preferences.getString(Constant.POSTCODE, ""));
		if(preferences.getString(Constant.PHPSESSID, "") != null && !"".equals(preferences.getString(Constant.PHPSESSID, ""))){
				HttpMap.put(Constant.PHPSESSID, preferences.getString(Constant.PHPSESSID, "")); 
				HttpMap.put(Constant.JUMEI_JHC, preferences.getString(Constant.JUMEI_JHC, "")); 
		 }	

		HttpMap.put(Constant.TK, preferences.getString(Constant.TK, ""));
		Iterator<Entry<String, String>> it = HttpMap.entrySet().iterator(); 
		int count = 0;
		String contentStr = "";
		while(it.hasNext()){ 
			@SuppressWarnings("rawtypes")
			java.util.Map.Entry entry = it.next(); 
			count ++ ;
			if(count == 1){
				contentStr = entry.getKey()+"="+entry.getValue();
			}else{
				contentStr += ";"+entry.getKey()+"="+entry.getValue();
			}			
		} 
		
		Log.i("TAG", "--createHttpHeaderCookData:ACCOUNT: " +preferences.getString(Constant.ACCOUNT, "") 
				+ " |NICKNAME(" + preferences.getString(Constant.NICKNAME, "") + ") TK("
				+ preferences.getString(Constant.TK, "") + ")");
		
		return contentStr;
	}
	
	
	public Map<String, String> createHttpHeaderCookDataForMap(Context context) {
		Map<String, String> HttpMap = new HashMap<String, String>();
		SharedPreferences preferences = context.getSharedPreferences(Constant.HTTPHEAD, Context.MODE_PRIVATE);	
		HttpMap.put(Constant.PLATFORM,  "android");		
		HttpMap.put(Constant.CLIENT_OS, Constant.CLIENT_OS_VALUE);		
		HttpMap.put(Constant.CLIENT, preferences.getString(Constant.CLIENT, Constant.CLIENTVER));
		HttpMap.put(Constant.MODEL,  Constant.MODEL_VALUE);
		HttpMap.put(Constant.IMSI, preferences.getString(Constant.IMSI, ""));
		HttpMap.put(Constant.IMEI,  preferences.getString(Constant.IMEI, getDeviceId(context)));
		HttpMap.put(Constant.SOURCE, Constant.SOURCE_VALUE);
		HttpMap.put(Constant.JUMEI_PRODUCT, "jumei");
		HttpMap.put(Constant.LANGUAGE, preferences.getString(Constant.LANGUAGE, "zh"));
		HttpMap.put(Constant.CARRIER, preferences.getString(Constant.CARRIER, ""));
		HttpMap.put(Constant.SMSCENTER, preferences.getString(Constant.SMSCENTER, getServiceCenterAddress()));
		HttpMap.put(Constant.RESOLUTION, preferences.getString(Constant.RESOLUTION, "320*480"));
		HttpMap.put(Constant.ACCOUNT, preferences.getString(Constant.ACCOUNT, ""));
		HttpMap.put(Constant.NICKNAME, preferences.getString(Constant.NICKNAME, ""));
//		HttpMap.put(Constant.POSTCODE, preferences.getString(Constant.POSTCODE, ""));
		if(preferences.getString(Constant.PHPSESSID, "") != null && !"".equals(preferences.getString(Constant.PHPSESSID, ""))){
				HttpMap.put(Constant.PHPSESSID, preferences.getString(Constant.PHPSESSID, "")); 
				HttpMap.put(Constant.JUMEI_JHC, preferences.getString(Constant.JUMEI_JHC, "")); 
		 }	

		HttpMap.put(Constant.TK, preferences.getString(Constant.TK, ""));
		return HttpMap;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.android.library.tools.DefaultTools#setHttpHeader(android.content
	 * .Context)
	 */
	@Override
	public void setHttpHeader(Context context) {
		SharedPreferences preferences = context.getSharedPreferences(Constant.HTTPHEAD, Context.MODE_PRIVATE);
		preferences.edit().putString(Constant.CARRIER, getCarrier(context))				
				.putString(Constant.IMEI, getDeviceId(context))
				.putString(Constant.IMSI, getImsiInfo(context))
				.putString(Constant.SMSCENTER, getServiceCenterAddress())
				.putString(Constant.MODEL, android.os.Build.MODEL).commit();
	}


	
	/**
	 * 根据url地址解析成BitmapDrawable
	 */
	public static BitmapDrawable getImg(String url) {
		byte data[] = getImage(url);
		if (data != null) {
			BitmapDrawable bd = new BitmapDrawable(BitmapFactory
					.decodeByteArray(data, 0, data.length));
			return bd;
		}
		return null;
	}
	/**
	 * 从URL地址中获取 图片 数据
	 * 
	 * @param urlPath
	 *            地址
	 * @return
	 */
	public static byte[] getImage(String urlPath) {
			try {
				URL url = new URL(urlPath);
				HttpURLConnection huc = (HttpURLConnection) url
						.openConnection();
				huc.setRequestMethod("GET");// GET方式
				huc.setReadTimeout(1 << 12);// 设置一个时间，防止读取时间太快
				if (huc.getResponseCode() == 200) {
					InputStream is = huc.getInputStream();
					return readStream(is);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		return null;
	}

	/**
	 * 读出并返回 读入流 中的数据
	 * 
	 * @param is
	 *            读入流
	 * @return
	 */
	public static byte[] readStream(InputStream is) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int len = -1;
		try {
			while ((len = is.read(data)) != -1) {
				baos.write(data, 0, len);
			}
			baos.close();
			is.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return baos.toByteArray();
	}
	
	/**
	 * 将流转成字节数组
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public byte[] stream2Bytes(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = is.read(buffer, 0, 1024)) != -1) {
            baos.write(buffer, 0, length);
        }
        baos.flush();
        return baos.toByteArray();
    }
	
	/**
	 * 加载网络图片
	 * @param info
	 * @return Bitmap
	 */
	public Bitmap loadImageWithUrl(String UrlStr) {
	    
        Bitmap bitmap = null;
        InputStream is = null;
        try {
            URL url = new URL(UrlStr);
            if (url == null || url.getContent() == null) {
                return null;
            }
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(15 * 1000);
            conn.connect();
            is = conn.getInputStream();
            byte[] bytes = stream2Bytes(is);
            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//            
//            if (bytes != null && bitmap != null) {
//                updateImageQueue(info, bitmap);
//                storeImageToSdCard(info, bitmap);
//            }
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                    is = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return bitmap;
	}
}
