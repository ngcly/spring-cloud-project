package com.cn.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author chenning
 * @Classname User
 * @Description 用户实体
 * @Date 2019/7/2 20:45
 */
@ApiModel
@Getter
@Setter
@Entity
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@Table(name="user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "uuid")
    @Column(name="id")
    private Integer id ;
    @Column(name="username",unique = true)
    @ApiModelProperty("用户名")
    private String username;       //用户名
    private String password;       //密码
    private String nickName;       //昵称
    private Byte gender ;          //性别
    private String address;        //地址
    private String realName;       //真实姓名
    private String personDesc;     //个人简介
    private String signature;      //个性签名
    private String avatar;         //头像
    private String phone;          //手机号
    private String email;          //邮箱地址
    //用户状态,0:不可用 1:正常状态,2:异常.
    @Column(columnDefinition="enum('0','1','2')")
    private Byte state;

    //立即从数据库中进行加载数据
    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns ={@JoinColumn(name = "role_id") })
    @Where(clause = "available=true")
    private Set<Role> roleList;

}
