package com.example.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.user.entity.UserVo;
import com.example.utils.http.HttpUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

//@SpringBootTest

/**
 * 使用spock框架
 *
 * 核心utils逻辑，写mock单测 无需回滚
 * 核心业务逻辑，写容器单测   可回滚
 *
 * 对通常的业务逻辑，写功能测试(接口测试) 不能回滚
 */
public class UserTest {

    @Test
    public void crudTest() {
        String id = crudAdd();
        update(id);
        queryDetail(id);
        queryList();
        delete(id);
    }

    private String crudAdd() {

        String url = TestParam.host + "/api/user/add";

        Map bodyParam = new HashMap();
        bodyParam.put("userAccount", "junit test add [userAccount]");
        bodyParam.put("userName", "junit test add [userName]");

        Map headers = new HashMap();
        headers.put("content-type", "application/json");

        String result = HttpUtil.post(url, bodyParam, null, headers);
        System.out.println(result);

        JSONObject rstObj = JSON.parseObject(result);
        int code = (int) rstObj.get("code");
        Assertions.assertEquals(code, 200);
        String user = rstObj.get("data").toString();
        String id = JSONObject.parseObject(user, UserVo.class).getUserId();
        Assertions.assertNotNull(id);

        return id;
    }

    private void update(String id) {

        String url = TestParam.host + "/api/user/update";

        Map bodyParam = new HashMap();
        bodyParam.put("userAccount", "junit test add [userAccount]");
        bodyParam.put("userName", "junit test add [userName]");
        bodyParam.put("userId", id);

        Map headers = new HashMap();
        headers.put("content-type", "application/json");

        String result = HttpUtil.post(url, bodyParam, null, headers);
        System.out.println(result);

        JSONObject rstObj = JSON.parseObject(result);
        int code = (int) rstObj.get("code");
        Assertions.assertEquals(code, 200);

        String user = rstObj.get("data").toString();
        String updateId = JSONObject.parseObject(user, UserVo.class).getUserId();
        Assertions.assertNotNull(updateId);
    }

    private void queryDetail(String id) {

        String url = TestParam.host + "/api/user/" + id;

        String result = HttpUtil.get(url, null, null);
        System.out.println(result);

        JSONObject rstObj = JSON.parseObject(result);
        int code = (int) rstObj.get("code");
        Assertions.assertEquals(code, 200);
        String userName = ((JSONObject) rstObj.get("data")).get("userName").toString();
        Assertions.assertNotNull(userName);
    }

    private void queryList() {

        String url = TestParam.host + "/api/user/list";

        Map bodyParam = new HashMap();
//        bodyParam.put("userName", "啊啊");

        Map urlParam = new HashMap();
        urlParam.put("page", "1");
        urlParam.put("pageSize", "5");

        Map headers = new HashMap();
        headers.put("content-type", "application/json");

        String result = HttpUtil.post(url, bodyParam, urlParam, headers);
        System.out.println(result);

        JSONObject rstObj = JSON.parseObject(result);

        int code = (int) rstObj.get("code");
        Assertions.assertEquals(code, 200);
        Object data = rstObj.get("data");
        Assertions.assertNotNull(data);
    }

    private void delete(String id) {

        String url = TestParam.host + "/api/user/delete";

        Map bodyParam = new HashMap();
        bodyParam.put("userId", id);

        Map headers = new HashMap();
        headers.put("content-type", "application/json");

        String result = HttpUtil.post(url, bodyParam, null, headers);
        System.out.println(result);

        JSONObject rstObj = JSON.parseObject(result);

        int code = (int) rstObj.get("code");
        Assertions.assertEquals(code, 200);
        Boolean data = (Boolean) rstObj.get("data");
        Assertions.assertTrue(data);
    }

    //    @Test
    public void zz() {

        for (int i = 0; i < 100000; i++) {

            String url = TestParam.host + "/api/user/add";

            Map bodyParam = new HashMap();
            bodyParam.put("userAccount", "import test add [userAccount]");
            bodyParam.put("userName", "import test add [userName]");

            Map headers = new HashMap();
            headers.put("content-type", "application/json");

            String result = HttpUtil.post(url, bodyParam, null, headers);
            System.out.println(result);
        }


    }

}


/**
 * 测试驱动开发(不是测试人员驱动开发) 不实际
 * 应该是面向接口开发 面向接口测试
 *      面向业务开发 面向业务测试
 */








