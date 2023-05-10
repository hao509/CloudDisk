package com.example.clouddisk.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName op
 */
@TableName(value ="op")
@Data
public class Op implements Serializable {
    /**
     * 操作id
     */
    @ExcelProperty("操作id")
    @TableId(value = "opid",type = IdType.AUTO)
    private Integer opid;

    /**
     * 操作角色
     */
   // private String oprole;

    /**
     * 操作方式
     */
    @ExcelProperty("操作方式")
    private String opmethod;

    /**
     * 操作时间
     */
    @ExcelProperty("操作时间")
    private Date opdate;

    /**
     * 操作结果
     */
    @ExcelProperty("操作结果")
    private String opres;
    @ExcelProperty("操作地址")
    private String opip;

    @ExcelIgnore
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
        Op other = (Op) that;
        return (this.getOpid() == null ? other.getOpid() == null : this.getOpid().equals(other.getOpid()))
           // && (this.getOprole() == null ? other.getOprole() == null : this.getOprole().equals(other.getOprole()))
            && (this.getOpmethod() == null ? other.getOpmethod() == null : this.getOpmethod().equals(other.getOpmethod()))
            && (this.getOpdate() == null ? other.getOpdate() == null : this.getOpdate().equals(other.getOpdate()))
            && (this.getOpres() == null ? other.getOpres() == null : this.getOpres().equals(other.getOpres()))
            && (this.getOpip() == null ? other.getOpip() == null : this.getOpip()).equals(other.getOpip());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOpid() == null) ? 0 : getOpid().hashCode());
       // result = prime * result + ((getOprole() == null) ? 0 : getOprole().hashCode());
        result = prime * result + ((getOpmethod() == null) ? 0 : getOpmethod().hashCode());
        result = prime * result + ((getOpdate() == null) ? 0 : getOpdate().hashCode());
        result = prime * result + ((getOpres() == null) ? 0 : getOpres().hashCode());
        result = prime * result + ((getOpip() == null) ? 0 : getOpip().hashCode());

        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", opid=").append(opid);
       // sb.append(", oprole=").append(oprole);
        sb.append(", opmethod=").append(opmethod);
        sb.append(", opdate=").append(opdate);
        sb.append(", opres=").append(opres);
        sb.append(", opip=").append(opip);

        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}