package shop.linyh.miniProgramDemo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *  是否绑定第三方账号
     */
    private Boolean isBindOtherAccount;

    /**
     * 用户微信返回的openid
     */
    private String openid;

    /**
     * 微信返回的sessionKey
     */
    private String sessionKey;

    /**
     * 用户名
     */
    private String username;

    /**
     * 头像url
     */
    private String avatarUrl;

    /**
     * 性别（0位置 1男 2女）
     */
    private Integer gender;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}