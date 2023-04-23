package com.example.clouddisk.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName doc
 */
@TableName(value ="doc")
@Data
public class Doc implements Serializable {
    /**
     * 文件id
     */
    @TableId(type = IdType.AUTO)
    private Integer docid;

    /**
     * 文件虚假名
     */
    private String docfakename;

    /**
     * 拥有者id
     */
    private String docowner;

    /**
     * 文件大小
     */
    private String docsize;

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
        Doc other = (Doc) that;
        return (this.getDocid() == null ? other.getDocid() == null : this.getDocid().equals(other.getDocid()))
            && (this.getDocfakename() == null ? other.getDocfakename() == null : this.getDocfakename().equals(other.getDocfakename()))
            && (this.getDocowner() == null ? other.getDocowner() == null : this.getDocowner().equals(other.getDocowner()))
            && (this.getDocsize() == null ? other.getDocsize() == null : this.getDocsize().equals(other.getDocsize()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDocid() == null) ? 0 : getDocid().hashCode());
        result = prime * result + ((getDocfakename() == null) ? 0 : getDocfakename().hashCode());
        result = prime * result + ((getDocowner() == null) ? 0 : getDocowner().hashCode());
        result = prime * result + ((getDocsize() == null) ? 0 : getDocsize().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", docid=").append(docid);
        sb.append(", docfakename=").append(docfakename);
        sb.append(", docowner=").append(docowner);
        sb.append(", docsize=").append(docsize);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}