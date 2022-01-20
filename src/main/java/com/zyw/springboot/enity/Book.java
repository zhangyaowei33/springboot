package com.zyw.springboot.enity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@TableName("book")
@Data
public class Book {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String bookName;
    private BigDecimal price;
    private String author;
    private Date createTime;


}
