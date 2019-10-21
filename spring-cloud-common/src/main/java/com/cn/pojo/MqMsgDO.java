package com.cn.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;

import java.io.Serializable;

/**
 * @author ngcly
 */
@Getter
@Setter
public class MqMsgDO<T> implements Serializable {
    /**服务名*/
    private String serveName;
    /**访问路径*/
    private String url;
    /**方法类型*/
    private HttpMethod method;
    /**请求参数*/
    private T param;
    /**失败次数*/
    private Integer failNum;

    public MqMsgDO(){}

    public MqMsgDO(String serveName,String url,HttpMethod method,T param,Integer failNum){
        this.serveName = serveName;
        this.url = url;
        this.method = method;
        this.param = param;
        this.failNum = failNum;
    }

    @Override
    public String toString() {
        return "{" +
                "serveName='" + serveName + '\'' +
                ", url='" + url + '\'' +
                ", method=" + method +
                ", param=" + param +
                ", failNum=" + failNum +
                '}';
    }
}
