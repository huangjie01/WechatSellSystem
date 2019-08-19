package com.huangjie.sell.dataobject;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * CREATE TABLE seller_info(
 *   id VARCHAR(32) NOT NULL ,
 *    username VARCHAR(32) NOT NULL ,
 *     password VARCHAR(64) NOT NULL ,
 *     openid VARCHAR(64) NOT NULL ,
 *     create_time TIMESTAMP NOT NULL DEFAULT current_timestamp,
 *      update_time TIMESTAMP NOT NULL  DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP ,
 *   PRIMARY KEY (id)
 * ) COMMENT '卖家信息表';
 */

/**
 * @author huangjie
 * @date 2019/08/19
 * @blame 黄杰
 **/

@Data
@Entity
@DynamicUpdate
public class SellerInfo {
    @Id
    private String id;
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String passWord;

    @Column(name = "openid")
    private String openId;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
}
