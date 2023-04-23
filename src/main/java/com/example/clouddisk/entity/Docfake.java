package com.example.clouddisk.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName docfake
 */
@TableName(value ="docfake")
@Data
public class Docfake implements Serializable {
    /**
     * 文件id
     */
    @TableId
    private String docid;

    /**
     * 文件真实名
     */
    private String docname;

    /**
     * 文件虚假名
     */
    private String docfakename;

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
        Docfake other = (Docfake) that;
        return (this.getDocid() == null ? other.getDocid() == null : this.getDocid().equals(other.getDocid()))
            && (this.getDocname() == null ? other.getDocname() == null : this.getDocname().equals(other.getDocname()))
            && (this.getDocfakename() == null ? other.getDocfakename() == null : this.getDocfakename().equals(other.getDocfakename()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDocid() == null) ? 0 : getDocid().hashCode());
        result = prime * result + ((getDocname() == null) ? 0 : getDocname().hashCode());
        result = prime * result + ((getDocfakename() == null) ? 0 : getDocfakename().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", docid=").append(docid);
        sb.append(", docname=").append(docname);
        sb.append(", docfakename=").append(docfakename);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}