package cn.wlcloudy.shiro.entity.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName BasePO
 * @Description TODO
 * @Author wangxiong
 * @Date 2018/10/25 11:06
 * @Version 1.0
 **/
@MappedSuperclass
public class BasePO implements Serializable {

    private static final long serialVersionUID = -6013643834380150081L;

    @Column(name = "create_time",columnDefinition = "datetime COMMENT '创建时间'")
    private Date createTime;

    @Column(name = "update_time",columnDefinition = "datetime COMMENT '更新时间'")
    private Date updateTime;

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
