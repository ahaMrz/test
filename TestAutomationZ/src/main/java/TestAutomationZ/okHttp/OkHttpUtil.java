package TestAutomationZ.okHttp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.tools.ant.taskdefs.email.Header;

import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
	
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.FormBody.Builder;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
//import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtil {

	OkHttpClient okHttpClient = new OkHttpClient();
	public static final MediaType type = MediaType
			.parse("application/x-www-form-urlencoded;charset=utf-8;application/json");
    //拼装geturl
	public String MergeParam(String url, JSONObject param) {
		// JSONObject paramJO = JSONObject.parseObject(param);
		StringBuffer stringBuffer = new StringBuffer(url);
		stringBuffer.append("?");
		boolean isFrist = true;
		for (String key : param.keySet()) {
			if (!isFrist) {
				stringBuffer.append("&");
			} else {
				isFrist = false;
			}
			stringBuffer.append(key);
			stringBuffer.append("=");
			stringBuffer.append(param.get(key));
		}
		url = stringBuffer.toString();
		return url;
	}
  
	public String post(String url, String param) {
		JSONObject paramHeaderJO = JSONObject.parseObject(param);
		JSONObject paramJO = (JSONObject) paramHeaderJO.get("param");
		JSONObject headerJO = (JSONObject) paramHeaderJO.get("headers");
		Builder formBuilder = new FormBody.Builder();
		//遍历添加请求参数
		if (null != paramJO) {
			for (String key : paramJO.keySet()) {
				formBuilder.add(key, paramJO.getString(key));
			}
		}

		FormBody formBody = formBuilder.build();
		// RequestBody body = RequestBody.create(type, param);
		// System.out.println(param);

		okhttp3.Request.Builder requestBuilder = new Request.Builder().url(url).post(formBody);
		//遍历添加header参数
		if (null != headerJO) {
			for (String key : headerJO.keySet()) {
				requestBuilder.addHeader(key, headerJO.getString(key));
			}
		}
		// Headers headers = new Headers(null);
		Request request = requestBuilder.build();

		Response response;
		Call call = okHttpClient.newCall(request);
		try {
			response = call.execute();
			response.headers();
			System.out.println(response.headers());
			return response.body().string();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println();
			return "请求错误aaa";
		}

	}

	public String get(String url, String param) {
		JSONObject paramHeaderJO = JSONObject.parseObject(param);
		JSONObject paramJO = (JSONObject) paramHeaderJO.get("param");
		JSONObject headerJO = (JSONObject) paramHeaderJO.get("headers");
		String mergeUrl = MergeParam(url, paramJO);
		okhttp3.Request.Builder requestBuilder = new Request.Builder().url(mergeUrl);
		
		//遍历添加header
		if (null != headerJO) {
			for (String key : headerJO.keySet()) {
				requestBuilder.addHeader(key, headerJO.getString(key));
			}
		}

		Request request = requestBuilder.build();
		Call call = okHttpClient.newCall(request);
		Response response;
		// request.
		try {
			response = call.execute();
			// System.out.println(response.code());
			return response.body().string();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "异常：请求错误发生";
		}
		

	}

	public static void main(String[] args) {
		String string = "{'headerds':{'Cookie': 'JSESSIONID2=bdcaa413-d2a4-465f-a5f0-62357bb3fffd; JSESSIONID=bdcaa413-d2a4-465f-a5f0-62357bb3fffd'},'param': {"
				+ "'operNo':'IT001','password':'827ccb0eea8a706c4c34a16891f84e7b','vCode':'vCode'}}";
		JSONObject jsonObject = JSONObject.parseObject(string);
		JSONObject headerds = (JSONObject) jsonObject.get("headerds");
		JSONObject param = (JSONObject) jsonObject.get("param");
		System.out.println(headerds);
		System.out.println(param);

		OkHttpUtil okHttpUtil = new OkHttpUtil();
		okHttpUtil.post("https://web.newfinance.xyect.com/appconfsys/v1/xy/login", string);
		// System.out.println();
		// Headers.of(headerds.toString());
		// @SuppressWarnings("unchecked")
		System.out.println(headerds);
		// HashMap<String, String> hashMap = (HashMap<String, String>)
		// JSONObject.toJavaObject(param, Map.class);
		// System.out.println(Headers.of(hashMap));
		// System.out.println(okHttpUtil.post("https://web.newfinance.xyect.com/appconfsys/v1/xy/login",
		// param.toString()));
	}
}
