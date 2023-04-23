package com.example.clouddisk.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName docshare
 */
@TableName(value ="docshare")
@Data
public class Docshare implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer shareid;

    /**
     * 
     */
    private String userid;

    /**
     * 
     */
    private Integer docid;

    /**
     * 
     */
    private String docplace;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Docshare other = (Docshare) that;
        return (this.getShareid() == null ? other.getShareid() == null : this.getShareid().equals(other.getShareid()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getDocid() == null ? other.getDocid() == null : this.getDocid().equals(other.getDocid()))
            && (this.getDocplace() == null ? other.getDocplace() == null : this.getDocplace().equals(other.getDocplace()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getShareid() == null) ? 0 : getShareid().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getDocid() == null) ? 0 : getDocid().hashCode());
        result = prime * result + ((getDocplace() == null) ? 0 : getDocplace().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", shareid=").append(shareid);
        sb.append(", userid=").append(userid);
        sb.append(", docid=").append(docid);
        sb.append(", docplace=").append(docplace);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}