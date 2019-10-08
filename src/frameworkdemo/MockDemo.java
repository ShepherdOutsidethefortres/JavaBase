package frameworkdemo;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.util.*;

public class MockDemo extends Mockito {

    /**
     * 创建mock对象，伪装使用
     */
    @Test
    public void createMockObject() {
        Map map = mock(Map.class);

        Assert.assertTrue(map instanceof Map);

        HashMap hashmap = mock(HashMap.class);
        Assert.assertTrue(hashmap instanceof Map);
        Assert.assertTrue(hashmap instanceof HashMap);

    }

    @Test
    public void configMock() {
        Map map = mock(Map.class);

        //当调用map.size()方法时候，返回100
        when(map.size()).thenReturn(100);
        Assert.assertEquals(map.size(), 100);

        //当调用map.put(1,2)方法时候，返回true，参数要匹配
        when(map.put(1, 2)).thenReturn(true);
        Assert.assertTrue((Boolean) map.put(1, 2));

        //当调用map.get(1)方法时候，抛空指针
        doThrow(new NullPointerException()).when(map).get(1);

        //表示调用clear()方法什么都不做，可以用在依赖组件或者依赖方法返回void的情况下
        doNothing().when(map).clear();

        map.size();
        verify(map, times(2)).size();//verify检测方法调用，这表明size方法调用2次
    }

    /**
     * 部分模拟spy
     */
    @Test
    public void testSpy() {
        List list = new LinkedList<>();
        List spy = spy(list);

        //对spy.size()进行定制
        when(spy.size()).thenReturn(100);

        spy.add("one");
        spy.add("two");

        //因为我们没有对 get(0), get(1) 方法进行定制,所有这两个调用的是真实方法
        Assert.assertEquals(spy.get(0), "one");
        Assert.assertEquals(spy.get(1), "two");
        Assert.assertEquals(spy.size(), 100);
    }

    /**
     * 方法调用参数捕获
     */
    @Test
    public void testCaptureArgument() {
        String param = "test";

        List mockObject = mock(List.class);
        ArgumentCaptor<String> paramObject = ArgumentCaptor.forClass(String.class);

        mockObject.add(param);

        verify(mockObject).add(paramObject.capture());//校验mockObject是否调用了add方法

        Assert.assertEquals("test", paramObject.getValue());//校验捕获的参数
    }

    /**
     * mock对象中不带参数的方法
     */
    @Test
    public void testNoParamInObject() {
        Iterator i = mock(Iterator.class);
        when(i.next()).thenReturn("Hello").thenReturn("World");

        String result = i.next() + " " + i.next();

        Assert.assertEquals("Hello World", result);
    }

    /**
     * mock对象中带参数的方法，参数为固定值
     */
    @Test
    public void testParamInObject() {
        Comparable c = mock(Comparable.class);
        when(c.compareTo("Test")).thenReturn(1);
        Assert.assertEquals(1, c.compareTo("Test"));
    }

    /**
     * mock对象中带参数的方法，参数值不固定，类型固定
     */
    @Test
    public void testParamInObjectButNotFix() {
        Comparable c = mock(Comparable.class);
        when(c.compareTo(anyInt())).thenReturn(-1);
        Assert.assertEquals(-1, c.compareTo(5));
    }

    /**
     * 对于无返回值的方法，如何mock参数值
     */
    @Test
    public void testNoAnswer() {
        HashMap<String, Integer> map = new HashMap<>();
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                HashMap<String, Integer> hashMap = (HashMap<String, Integer>) args[0];

                map.putAll(createArrayList(hashMap));

                return map;
            }
        }).when(anyMap()).putAll(map);
    }

    public HashMap<String, Integer> createArrayList(HashMap<String, Integer> hashMap) {
        hashMap.put("first", 1);
        hashMap.put("second", 2);

        return hashMap;
    }

    /**
     * 抛出异常
     *
     * @throws IOException
     */
    @Test(expectedExceptions = IOException.class)
    public void testException() throws IOException {
        OutputStream mock = mock(OutputStream.class);
        OutputStreamWriter osw = new OutputStreamWriter(mock);
        doThrow(new IOException()).when(mock).close();
        osw.close();
    }

    /**
     * mock三方类库/源码 方法返回数据
     * 定义一个子类继承父类，可以直接修改变量（public,protected），mock数据返回
     */
    @Test
    public void testModifyThirdSource() {
        String name = "mrzhao";
        ThirdClassChild child = new ThirdClassChild(name);

        ThirdClass mock = mock(ThirdClass.class);
        when(mock.soutName()).thenReturn("这是mock数据");
    }

    /**
     * 修改私有成员变量
     * 反射
     */
    @Test
    public void testModifyThirdVariable() throws NoSuchFieldException, IllegalAccessException {
        String email="azhaohuiwei@163.com";
        ThirdClass thirdClass = new ThirdClass();
        Field emailField = thirdClass.getClass().getDeclaredField("email");
        emailField.setAccessible(true);
        emailField.set(thirdClass,email);

        when(thirdClass.soutName()).thenReturn("哈哈哈");
    }
}
