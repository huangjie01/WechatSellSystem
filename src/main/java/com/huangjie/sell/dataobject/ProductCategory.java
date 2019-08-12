package com.huangjie.sell.dataobject;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by huangjie on 2019/7/17.
 * category_id INT  AUTO_INCREMENT,
 * category_name VARCHAR(64) NOT NULL COMMENT '类目名称',
 * category_type INT NOT NULL COMMENT '类目编号',
 * create_time TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
 * update_time
 */

@Entity
@DynamicUpdate
public class ProductCategory {
    /**
     * 类目ID
     */
    @Id
    @GeneratedValue
    private Integer categoryId;
    /**
     * 类目名称
     */
    private String categoryName;
    /**
     * 类目编号
     */
    private Integer categoryType;
    /**
     * 创建时间
     */
    private Date createTime;

    @Override
    public String toString() {
        return "ProductCategory{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryType=" + categoryType +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    /**
     * 更新时间
     */
    private Date updateTime;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
