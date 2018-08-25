package com.example.springaopandaspectjlearn;

import com.example.springaopandaspectjlearn.cglib.sample1.bean.FinalTargetObject;
import com.example.springaopandaspectjlearn.cglib.sample1.bean.PrivateTargetObject;
import com.example.springaopandaspectjlearn.cglib.sample1.bean.SampleBean;
import com.example.springaopandaspectjlearn.cglib.sample1.bean.TargetObject;
import com.example.springaopandaspectjlearn.cglib.sample1.callback.TargetInterceptor;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;
import net.sf.cglib.beans.BulkBean;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.Mixin;
import net.sf.cglib.reflect.*;
import net.sf.cglib.util.ParallelSorter;
import net.sf.cglib.util.StringSwitcher;
import org.junit.Test;

import java.lang.reflect.Method;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class SpringAopAndAspectjLearnApplicationTests {

    /**
     * java.lang.IllegalArgumentException: No visible constructors in class
     * java.lang.IllegalArgumentException: Superclass has no null constructors but no arguments were given
     */
    @Test
    public void testPrivate() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PrivateTargetObject.class);
        enhancer.setCallback(new TargetInterceptor());
        PrivateTargetObject targetObject = (PrivateTargetObject) enhancer.create();
    }

    /**
     * java.lang.IllegalArgumentException: Cannot subclass final class
     */
    @Test
    public void testFinal() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(FinalTargetObject.class);
        enhancer.setCallback(new TargetInterceptor());
        FinalTargetObject targetObject = (FinalTargetObject) enhancer.create();
    }

    @Test
    public void testInvocationHandler() throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetObject.class);
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                    return "Hello cglib!";
                }
                throw new RuntimeException("Do not know what to do.");
            }
        });
        TargetObject proxy = (TargetObject) enhancer.create();
        assertEquals("Hello cglib!", proxy.method1(null));
        System.out.println(proxy.method1(null));
    }

    @Test
    public void testBeanGenerator1() throws Exception {
        BeanGenerator beanGenerator = new BeanGenerator();
        beanGenerator.addProperty("value", String.class);
        Object myBean = beanGenerator.create();

        Method setter = myBean.getClass().getMethod("setValue", String.class);
        setter.invoke(myBean, "Hello cglib!");
        Method getter = myBean.getClass().getMethod("getValue");
        assertEquals("Hello cglib!", getter.invoke(myBean));
    }

    public class OtherSampleBean {
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public class A1 {
        public B b;

        public B getB() {
            return b;
        }

        public void setB(B b) {
            this.b = b;
        }
    }
    public class A2 {
        public B b;

        public B getB() {
            return b;
        }

        public void setB(B b) {
            this.b = b;
        }
    }

    public class B {
        public String name;



        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Test
    public void testBeanCopier2() throws Exception {
        BeanCopier copier = BeanCopier.create(A1.class, A2.class, false);
        A1 bean = new A1();
        B b = new B();
        b.setName("aaaa");
        bean.setB(b);
        A2 otherBean = new A2();
        copier.copy(bean, otherBean, null);
        assertEquals("aaaa", otherBean.getB().getName());
    }


    @Test
    public void testBeanCopier() throws Exception {
        BeanCopier copier = BeanCopier.create(SampleBean.class, OtherSampleBean.class, false);
        SampleBean bean = new SampleBean();
        bean.setValue("Hello cglib!");
        OtherSampleBean otherBean = new OtherSampleBean();
        copier.copy(bean, otherBean, null);
        assertEquals("Hello cglib!", otherBean.getValue());
    }

    @Test
    public void testBulkBean() throws Exception {
        BulkBean bulkBean = BulkBean.create(SampleBean.class,
                new String[]{"getValue"},
                new String[]{"setValue"},
                new Class[]{String.class});
        SampleBean bean = new SampleBean();
        bean.setValue("Hello world!");
        assertEquals(1, bulkBean.getPropertyValues(bean).length);
        assertEquals("Hello world!", bulkBean.getPropertyValues(bean)[0]);
        bulkBean.setPropertyValues(bean, new Object[]{"Hello cglib!"});
        assertEquals("Hello cglib!", bean.getValue());
    }


    @Test
    public void testBeanGenerator() throws Exception {
        SampleBean bean = new SampleBean();
        BeanMap map = BeanMap.create(bean);
        bean.setValue("Hello cglib");
        assertEquals("Hello cglib", map.get("value"));
    }

    public interface SampleKeyFactory {
        Object newInstance(String first, int second);
    }

//    @Test
//    public void testKeyFactory() throws Exception {
//        SampleKeyFactory keyFactory = (SampleKeyFactory) KeyFactory.create(Key.class);
//        Object key = keyFactory.newInstance("foo", 42);
//        Map<Object, String> map = new HashMap<>();
//        map.put(key, "Hello cglib!");
//        assertEquals("Hello cglib!", map.get(keyFactory.newInstance("foo", 42)));
//    }

    public interface Interface1 {
        String first();
    }

    public interface Interface2 {
        String second();
    }

    public class Class1 implements Interface1 {
        @Override
        public String first() {
            return "first";
        }
    }

    public class Class2 implements Interface2 {
        @Override
        public String second() {
            return "second";
        }
    }

    public interface MixinInterface extends Interface1, Interface2 {

    }

    @Test
    public void testMixin() throws Exception {
        Mixin mixin = Mixin.create(new Class[]{Interface1.class, Interface2.class, MixinInterface.class}, new Object[]{new Class1(), new Class2()});
        MixinInterface mixinDelegate = (MixinInterface) mixin;
        assertEquals("first", mixinDelegate.first());
        assertEquals("second", mixinDelegate.second());
    }

    @Test
    public void testStringSwitcher() throws Exception {
        String[] strings = new String[]{"one", "two"};
        int[] values = new int[]{10, 20};
        StringSwitcher stringSwitcher = StringSwitcher.create(strings, values, true);
        assertEquals(10, stringSwitcher.intValue("one"));
        assertEquals(20, stringSwitcher.intValue("two"));
        assertEquals(-1, stringSwitcher.intValue("three"));
    }

    public interface BeanDelegate {
        String getValueFromDelegate();
    }

    @Test
    public void testMethodDelegate() throws Exception {
        SampleBean bean = new SampleBean();
        bean.setValue("Hello cglib!");
        BeanDelegate delegate = (BeanDelegate) MethodDelegate.create(bean, "getValue", BeanDelegate.class);
        assertEquals("Hello cglib!", delegate.getValueFromDelegate());
    }

    public interface DelegationProvider {
        void setValue(String value);
    }

    public class SimpleMultiCastBean implements DelegationProvider {
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    @Test
    public void testMultiCastDelegate() throws Exception {
        MulticastDelegate multicastDelegate = MulticastDelegate.create(DelegationProvider.class);
        SimpleMultiCastBean first = new SimpleMultiCastBean();
        SimpleMultiCastBean second = new SimpleMultiCastBean();
        multicastDelegate = multicastDelegate.add(first);
        multicastDelegate = multicastDelegate.add(second);

        DelegationProvider provider = (DelegationProvider) multicastDelegate;
        provider.setValue("Hello world!");
        assertEquals("Hello world!", first.getValue());
        assertEquals("Hello world!", second.getValue());
    }

    public interface SampleBeanConstructorDelegate {
        Object newInstance();
    }

    @Test
    public void testConstructorDelegate() throws Exception {
        SampleBeanConstructorDelegate constructorDelegate = (SampleBeanConstructorDelegate) ConstructorDelegate.create(SampleBean.class, SampleBeanConstructorDelegate.class);
        SampleBean bean = (SampleBean) constructorDelegate.newInstance();
        assertTrue(SampleBean.class.isAssignableFrom(bean.getClass()));
    }

    @Test
    public void testParallelSorter() throws Exception {
        Integer[][] value = {
                {4, 3, 9, 0},
                {2, 1, 6, 0}
        };
        ParallelSorter.create(value).mergeSort(0);
        for (Integer[] row : value) {
            int former = -1;
            for (int val : row) {
                assertTrue(former < val);
                former = val;
            }
        }
    }


    @Test
    public void testFastClass() throws Exception {
        TargetObject targetObject = new TargetObject();
        targetObject.setValue("Hello cglib!");

        FastClass fastClass = FastClass.create(TargetObject.class);
        FastMethod fastMethod = fastClass.getMethod(TargetObject.class.getMethod("getValue"));
        System.out.println(fastMethod.invoke(targetObject, new Object[0]));
    }

}
