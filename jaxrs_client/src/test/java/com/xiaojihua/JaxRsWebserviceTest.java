package com.xiaojihua;

import com.xiaojihua.domain.User;
import com.xiaojihua.utils.HttpClientUtil;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.Collection;

public class JaxRsWebserviceTest {

    @Test
    public void testPost(){
        WebClient client = WebClient.create("http://localhost:8080/jaxrswebservice/ws/userService/user/");
        User user = new User();
        user.setUsername("张三");
        user.setCity("杭州");
        client.type(MediaType.APPLICATION_JSON);
        client.post(user);
    }

    @Test
    public void testPut(){
        WebClient client = WebClient.create("http://localhost:8080/jaxrswebservice/ws/userService/user/");
        User user = new User();
        user.setUsername("张三");
        user.setCity("北京");
        client.type(MediaType.APPLICATION_JSON);
        client.put(user);
    }

    @Test
    public void testList(){
        WebClient client = WebClient.create("http://localhost:8080/jaxrswebservice/ws/userService/user/");
        User user = new User();
        user.setUsername("张三");
        user.setCity("北京");
        client.type(MediaType.APPLICATION_JSON);
        client.accept(MediaType.APPLICATION_JSON);
        Collection<? extends User> collection = client.getCollection(User.class);
        System.out.println(collection);
    }

    @Test
    public void testDelete(){
        //根据请求路径来传输参数
        WebClient client = WebClient.create("http://localhost:8080/jaxrswebservice/ws/userService/user/1");
        client.type(MediaType.APPLICATION_JSON);
        client.delete();
    }

    @Test
    public void testGetOne(){
        //根据请求路径来传输参数
        WebClient client = WebClient.create("http://localhost:8080/jaxrswebservice/ws/userService/user/1");
        client.type(MediaType.APPLICATION_JSON);
        client.accept(MediaType.APPLICATION_JSON);
        User user = client.get(User.class);
        System.out.println(user);
    }

    /**
     * jax_rs风格的webservice可以使用httpclient来请求，作用是一样的
     * 只不过返回来的是原始的数据，没有经过解析。
     * 如果使用webclient则返回来的数据会自动进行解析
     */
    @Test
    public void testGetOneByHttpClient(){
        //根据请求路径来传输参数
        String s = HttpClientUtil.doGet("http://localhost:8080/jaxrswebservice/ws/userService/user/1");
        System.out.println(s);
    }
}
