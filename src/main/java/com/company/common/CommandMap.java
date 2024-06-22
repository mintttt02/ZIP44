package com.company.common;
 
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
 
public class CommandMap {
    Map<String,Object> map = new HashMap<String,Object>();
     
    public Object get(String key){
        return map.get(key); //key에 맞는 value를 가져옴 
    }
     
    public void put(String key, Object value){
        map.put(key, value); //key와 value를 map에 넣음
    }
     
    public Object remove(String key){
        return map.remove(key); // 해당key와 value를 삭제함 
    }
     
    public boolean containsKey(String key){ //map안에 해당 key가 있는지 없는지 판단 boolean리턴 
        return map.containsKey(key);
    }
     
    public boolean containsValue(Object value){  //map안에 해당 value가 있는지 없는지 판단 boolean리턴 
        return map.containsValue(value);
    }
     
    public void clear(){
        map.clear(); //map의 모든 데이터를 삭제함 
    }
     
    public Set<Entry<String, Object>> entrySet(){ //HashMap에 저장된 키와 값을 엔트리(키와 값의 결합)의 형태로 Set에 저장하여 반환한다.

        return map.entrySet(); 
    }
     
    public Set<String> keySet(){
        return map.keySet(); //map속의 모든 key 
    }
     
    public boolean isEmpty(){ //map이 비어있는지 여부
        return map.isEmpty();
    }
     
    public void putAll(Map<? extends String, ?extends Object> m){ //Map에 해당하는 모든 요소를 HashMap에 저장 한다.
        map.putAll(m);
    }
     
    public Map<String,Object> getMap(){
        return map;
    }
    
    public int size() { //map에 저장된 요소의 갯수 반환
    	return map.size();
    }
}