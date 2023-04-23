
package com.example.clouddisk.common;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.clouddisk.enums.BusinessCodeEnum;


import java.util.HashMap;
import java.util.Map;


public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	/**
	 * @param typeReference
	 * @param <T>
	 * @return
	 */
	public <T> T getData(String key, TypeReference<T> typeReference) {
		Object data = get(key);//默认是map类型
		String jsonString = JSONObject.toJSONString(data);
		T t = JSONObject.parseObject(jsonString, typeReference);
		return t;
	}

	/**
	 * @param typeReference
	 * @param <T>
	 * @return
	 */
	public <T> T getData(TypeReference<T> typeReference){
		Object data = get("data");//默认是map类型
		String jsonString = JSONObject.toJSONString(data);
		T t = JSONObject.parseObject(jsonString, typeReference);
		return t;
	}

	public R setData(Object data){
		put("data", data);
		return this;
	}

	public R() {
		put("code", 0);
		put("msg", "success");
	}

	public static HashMap<String,Object> putList(Object object) {
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("list",object);
		return hashMap;
	}

	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R error(BusinessCodeEnum be) {
		R r = new R();
		r.put("code", be.getCode());
		r.put("msg", be.getMsg());
		return r;
	}

	/**
	 * 构建错误信息对象
	 * @param be 枚举对象
	 * @param map 错误map（字段+错误信息）
	 * @return
	 */
	public static R errorDataMap(BusinessCodeEnum be, Map map) {
		R r = new R();
		r.put("code", be.getCode());
		r.put("msg", be.getMsg());
		r.put("data",map);
		return r;
	}

	/**
	 * 构建错误信息对象
	 * @param be 枚举对象
	 * @param field 报错字段
	 * @param msg 报错信息
	 * @return
	 */
	public static R errorDataMap(BusinessCodeEnum be, String field, String msg) {
		R r = new R();
		r.put("code", be.getCode());
		r.put("msg", be.getMsg());
		Map map=new HashMap();
		map.put(field,msg);
		r.put("data",map);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}

	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}

	public static R ok() {
		return new R();
	}

	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	public  Integer getCode() {
		return (Integer) this.get("code");
	}

}
