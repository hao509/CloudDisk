package com.example.clouddisk.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName friendship
 */
@TableName(value ="friendship")
@Data
public class Friendship implements Serializable {
    /**
     * 好友关系标签
     */
    @TableId
    private String friendid;

    /**
     * 好友1的id
     */
    private String user1;

    /**
     * 好友2的id
     */
    private String user2;

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
        Friendship other = (Friendship) that;
        return (this.getFriendid() == null ? other.getFriendid() == null : this.getFriendid().equals(other.getFriendid()))
            && (this.getUser1() == null ? other.getUser1() == null : this.getUser1().equals(other.getUser1()))
            && (this.getUser2() == null ? other.getUser2() == null : this.getUser2().equals(other.getUser2()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFriendid() == null) ? 0 : getFriendid().hashCode());
        result = prime * result + ((getUser1() == null) ? 0 : getUser1().hashCode());
        result = prime * result + ((getUser2() == null) ? 0 : getUser2().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", friendid=").append(friendid);
        sb.append(", user1=").append(user1);
        sb.append(", user2=").append(user2);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}