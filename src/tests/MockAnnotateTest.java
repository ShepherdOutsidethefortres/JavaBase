package tests;

import frameworkdemo.MockAnnotate;
import frameworkdemo.MockSpy;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MockAnnotateTest extends Mockito {

    /**
     * 省的每次都去mock(xxx.class)
     */
    @Mock
    private MockAnnotate mockAnnotate;

    @Spy
    private MockSpy mockSpy;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMockData() {

        //定制了就按照定制输出；没有定制，也不会调用到真实方法。输出结果：null，null
        System.out.println(mockAnnotate.getTitle());
        System.out.println(mockAnnotate.getDesc());

        //spy mock，定制了，就按照定制输出；没有定制，调用真实方法。
        //未定制，按照真实方法
        System.out.println(mockSpy.getName());
        System.out.println(mockSpy.getTag());

        //定制
        when(mockSpy.getName()).thenReturn("系哈哈");
        when(mockSpy.getTag()).thenReturn("tag is :hahaha");

        System.out.println(mockSpy.getName());
        System.out.println(mockSpy.getTag());

        //定制后，又想调用真实方法
        Mockito.reset(mockSpy);

        System.out.println(mockSpy.getName());//又变回了name
    }

    @AfterMethod
    public void tearDown() {
    }
}