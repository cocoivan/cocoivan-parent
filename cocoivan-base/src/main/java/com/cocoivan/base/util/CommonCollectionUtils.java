package com.cocoivan.base.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * 集合通用工具类
 *
 */
public class CommonCollectionUtils {
	/**
	 * 
	 * @param itemList 需要转换的集合
	 * @param idStr 期望转换的Map的键在实体类中所对应的属性名
	 * @param clazz 期望转换的Map的键所对应的Class类
	 * @return 键和实体类所对应的Map
	 * @see prop2EntityMap 反射无法直接获取父类属性值,采用递归获取.
	 */
	@Deprecated
	public static <K,T> Map<K,T> transferCollection2idMap(Collection<T> itemList, String idStr,Class<K> clazz){
		if (!CollectionUtils.isEmpty(itemList)) {
			try {
				Map<K,T> id2Item = new HashMap<K, T>();
				for (T item : itemList) {
					Field idField = item.getClass().getDeclaredField(idStr);
					idField.setAccessible(true);
					K val =clazz.cast(idField.get(item));
					id2Item.put(val, item);
				}
				return id2Item;
			}
			catch (Exception e) {
				throw new RuntimeException("transferCollection2idMap error", e);
			}
		}
		return new HashMap<K, T>();
	}
	
	/**
	 * 根据给出的实体类获取期待的实体类某单个属性的集合
	 * @param itemList 实体类集合
	 * @param propertyName 期待获取的属性的属性名
	 * @param clazz 属性的Class对象
	 * @return 属性的集合
	 * @see propList 
	 */
	@Deprecated
	public static <K,T> List<K> getItemSinglePropertiesList(Collection<T> itemList,
			String propertyName, Class<K> clazz)  {
		List<K> al = new ArrayList<>();
		if(!CollectionUtils.isEmpty(itemList)) {
			try {
				for (T item : itemList) {
					Field propertyField = item.getClass().getDeclaredField(propertyName);
					propertyField.setAccessible(true);
					al.add(clazz.cast(propertyField.get(item)));
				}
				return al;
			} catch(Exception e) {
				throw new RuntimeException("getItemSinglePropertiesList error", e);
			}
		}
		return new ArrayList<K>();
	}
	/**
	 * 将用一系列元素用逗号隔开的字符串转化为该元素的集合(非空的会被忽略)
	 * @param strList 一系列用逗号隔开的某一元素集合的字符串
	 * @param clazz 元素的类型,支持Integer,Long,Byte,String
	 * @return 元素的List
	 */
	public static <T> List<T> parseStrList2Array(String strList,Class<T> clazz) {
		if(StringUtils.isBlank(strList)) {
			return new ArrayList<T>();
		}
		if(StringUtils.isNotEmpty(strList.trim())) {
			String[] strArrays = strList.split(",");
			List<T> list = new ArrayList<>();
			for (String str : strArrays) {
				if(StringUtils.isNotEmpty(str))
					list.add(transferStringToSomeEntity(str,clazz));
			}
			return list;
		} else {
			return new ArrayList<T>();
		}
	}
	/**
	 *  集合根据某一字段进行排序
	 * @param  集合
	 * @param fieldName 字段
	 * @param fieldClass 字段对应的Class,Class必须实现Comparable接口
	 * @param ordered false代表倒序
	 */
	public static <T,K> void sortByField(List<T> list,final String fieldName,
			final Class<? extends Comparable<K>> fieldClass,boolean ordered) {
		if(CollectionUtils.isEmpty(list)) {
			return;
		}
		if(ordered) {
			Collections.sort(list, new Comparator<T>() {
				@Override
				public int compare(T o1, T o2) {
					if (o1 == null && o2 == null) return 0;
					if (o1 == null) return -1;
					if (o2 == null) return 1;
					if (o1 == o2) return 0;
					//获取对应的属性值进行比较.
					Field field = null;
					try {
						field = getancestorsField(o1.getClass(),fieldName);
						field.setAccessible(true);
						Comparable<K> val1 = fieldClass.cast(field.get(o1));
						K val2 = (K)(field.get(o2));
						return val1.compareTo(val2);
					}
					catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
				
			});
		} else {
			Collections.sort(list, new Comparator<T>() {
				@Override
				public int compare(T o1, T o2) {
					if (o1 == null && o2 == null) return 0;
					if (o1 == null) return 1;
					if (o2 == null) return -1;
					if (o1 == o2) return 0;
					//获取对应的属性值进行比较.
					Field field = null;
					try {
						field = getancestorsField(o1.getClass(),fieldName);
						field.setAccessible(true);
						Comparable<K> val2 = fieldClass.cast(field.get(o2));
						K val1 = (K)(field.get(o1));
						return val2.compareTo(val1);
					}
					catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
				
			});
		}
	}
	
	/**
	 *  <p>Description:获取以某一属性的值为键,以实体类的List为值的Map</p>
	 * 	<p>createdate:2017年7月10日</p>
	 * 	@param itemList 实体类的集合
	 * 	@param propertyName 该属性的属性名
	 * 	@param clazz 该属性的Class对象
	 * 	@return 该属性==以该属性进行分类的Map
	 *  @see prop2ListMap
	 */
	@Deprecated
	public static <K,T> Map<K,List<T>> getProperty2ListMap(Collection<T> itemList,
			String propertyName, Class<K> clazz)  {
		Map<K,List<T>> map = new HashMap<>();
		if(!CollectionUtils.isEmpty(itemList)) {
			try {
				for (T item : itemList) {
					Field propertyField = item.getClass().getDeclaredField(propertyName);
					propertyField.setAccessible(true);
					K fieldValue = clazz.cast(propertyField.get(item));
					List<T> list = map.get(fieldValue);
					if(list == null) {
						list = new ArrayList<T>();
						map.put(fieldValue, list);
					}
					list.add(item);
				}
				return map;
			} catch(Exception e) {
				throw new RuntimeException("getItemSinglePropertiesList error", e);
			}
		}
		return new HashMap<>();
	}
	
	/**
	 * 
	 * @param itemList 需要转换的集合
	 * @param idStr 期望转换的Map的键在实体类中所对应的属性名
	 * @param clazz 期望转换的Map的键所对应的Class类
	 * @return 键和实体类所对应的Map
	 */
	public static <K,T> Map<K,T> prop2EntityMap(Collection<T> itemList, String idStr,Class<K> clazz){
		if (!CollectionUtils.isEmpty(itemList)) {
			try {
				Map<K,T> id2Item = new HashMap<K, T>();
				for (T item : itemList) {
					Field idField = getancestorsField(item.getClass(),idStr);
					idField.setAccessible(true);
					K val =clazz.cast(idField.get(item));
					id2Item.put(val, item);
				}
				return id2Item;
			}
			catch (Exception e) {
				throw new RuntimeException("transferCollection2idMap error", e);
			}
		}
		return new HashMap<K, T>();
	}
	
	/**
	 * 根据给出的实体类获取期待的实体类某单个属性的集合
	 * @param itemList 实体类集合
	 * @param propertyName 期待获取的属性的属性名
	 * @param clazz 属性的Class对象
	 * @return 属性的集合
	 */
	public static <K,T> List<K> propList(Collection<T> itemList,
			String propertyName, Class<K> clazz)  {
		List<K> al = new ArrayList<>();
		if(!CollectionUtils.isEmpty(itemList)) {
			try {
				for (T item : itemList) {
					Field propertyField = getancestorsField(item.getClass(),propertyName);
					propertyField.setAccessible(true);
					al.add(clazz.cast(propertyField.get(item)));
				}
				return al;
			} catch(Exception e) {
				throw new RuntimeException("getItemSinglePropertiesList error", e);
			}
		}
		return new ArrayList<K>();
	}
	
	/**
	 *  <p>Description:获取以某一属性的值为键,以实体类的List为值的Map</p>
	 * 	<p>createdate:2017年7月10日</p>
	 * 	@param itemList 实体类的集合
	 * 	@param propertyName 该属性的属性名
	 * 	@param clazz 该属性的Class对象
	 * 	@return 该属性==以该属性进行分类的Map
	 */
	public static <K,T> Map<K,List<T>> prop2ListMap(Collection<T> itemList,
			String propertyName, Class<K> clazz)  {
		Map<K,List<T>> map = new HashMap<>();
		if(!CollectionUtils.isEmpty(itemList)) {
			try {
				for (T item : itemList) {
					Field propertyField = getancestorsField(item.getClass(),propertyName);
					propertyField.setAccessible(true);
					K fieldValue = clazz.cast(propertyField.get(item));
					List<T> list = map.get(fieldValue);
					if(list == null) {
						list = new ArrayList<T>();
						map.put(fieldValue, list);
					}
					list.add(item);
				}
				return map;
			} catch(Exception e) {
				throw new RuntimeException("getItemSinglePropertiesList error", e);
			}
		}
		return new HashMap<>();
	}
	/**
	 * 向集合里批量插入元素的简化方法.
	 * 	createdate:2017年6月9日
	 *  createby :hulonghai
	 * 	@param clazz:想要转换的集合数据类型,只支持ArrayList,LinkedList,HashSet,TreeSet
	 * 	@param elements:向集合中添加的元素
	 */
	@SafeVarargs
	public static <L,T> Collection<T> addSomeElements4Collection(Class<L> clazz,T... elements) {
		Collection<T> coll = null;
		if(clazz.getSimpleName().equals("ArrayList")) {
			coll = new ArrayList<T>();
		} else if(clazz.getSimpleName().equals("LinkedList")) {
			coll = new LinkedList<T>();
		} else if(clazz.getSimpleName().equals("HashSet")) {
			coll = new HashSet<T>();
		} else if(clazz.getSimpleName().equals("TreeSet")) {
			coll = new TreeSet<T>();
		} 
		if(coll == null) {
			throw new RuntimeException("不支持的数据类型!");
		}
		if(elements == null || elements.length ==0) {
			return coll;
		} 
		for(T ele : elements) {
			coll.add(ele);
		}
		return coll;
	}
	
	/**
	 * 向Map中添加大量元素的方法
	 * 	createdate:2017年6月9日
	 *  createby :hulonghai
	 * 	@param map
	 * 	@param keys
	 * 	@param vals
	 */
	public static <K,V> void addSomeElements2Map(Map<K,V> map,K[] keys,V[] vals) {
		if(map == null || keys == null || keys.length == 0 || vals== null || vals.length == 0 || keys.length != vals.length) {
			return;
		}
		for(int i=0;i<keys.length;i++) {
			K key = keys[i];
			V val = vals[i];
			map.put(key, val);
		}
	}
	private static Field getancestorsField(Class<? extends Object> class1, String fieldName) {
		if(class1 == null) {
			return null;
		}
		Field field = null;
		try {
			field = class1.getDeclaredField(fieldName);
		} catch(Exception e) {
			Class<?> superClass = class1.getSuperclass();
			if(superClass == null) {
				throw new RuntimeException("找不到对应的属性");
			} else {
				return getancestorsField(superClass,fieldName);
			}
		}
		return field;
	}
	
	private static <T> T transferStringToSomeEntity(String str,Class<T> clazz) {
		try {
			T t = null;
			if (clazz.getName().equals(String.class.getName())) {
				t = clazz.cast(str);
			} else if (clazz.getName().equals(Integer.class.getName())) {
				t = clazz.cast(Integer.parseInt(str));
			} else if (clazz.getName().equals(Long.class.getName())) {
				t = clazz.cast(Long.parseLong(str));
			} else if (clazz.getName().equals(Byte.class.getName())) {
				t = clazz.cast(Byte.parseByte(str));
			} else {
				throw new RuntimeException("不支持的数据类型!");
			}
			return t;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 列表分页
	 * @param list 需要分页的列表
	 * @param size 分页大小
	 * @return
	 */
	public static <T> List<List<T>> split(List<T> list, int size) {
		if (size <= 0) {
			throw new RuntimeException("size必须大于0!");
		}
		List<List<T>> result = new ArrayList<>();
		if (list == null || list.size() == 0) {
			return result;
		}
		if (list.size() <= size) {
			result.add(list);
			return result;
		}
		List<T> tmp = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			tmp.add(list.get(i));
			if (tmp.size() == size) {
				result.add(tmp);
				tmp = new ArrayList<>();
			}
		}
		if (tmp.size() > 0) {
			result.add(tmp);
		}
		return result;
	}
	
}



