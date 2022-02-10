package com.zyw.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyw.springboot.common.Result;
import com.zyw.springboot.enity.Book;
import com.zyw.springboot.enity.User;
import com.zyw.springboot.mapper.BookMapper;
import com.zyw.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/book")
public class BookController {


    @Resource
    BookMapper bookMapper;

    @PostMapping("/save")//新增保存
    public Result<?> save(@RequestBody Book book){
        bookMapper.insert(book);
        return Result.success();
    }

    @PutMapping(path = "/update")//编辑保存
    public Result<?> update(@RequestBody Book book){

        bookMapper.updateById(book);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")//删除
    public Result<?> delete(@PathVariable Long id){
        bookMapper.deleteById(id);
        return Result.success();
    }
    @PostMapping("/deleteBatch")
    public Result<?> deleteBath(@RequestBody  List<Integer> ids){
        bookMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Book> wrapper = Wrappers.<Book>lambdaQuery();
        if(StrUtil.isNotBlank(search)){
            wrapper.like(Book::getBookName, search);
        }
        Page<Book> userPage = bookMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);

        return Result.success(userPage);
    }
}
