package com.ws.easyexcel.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ws.easyexcel.entity.po.Book;
import com.ws.easyexcel.mapper.BookMapper;
import com.ws.easyexcel.service.BookService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 书服务实现
 *
 * @author wangsen
 * @date 2024/05/19
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Override
    public void export(HttpServletResponse response) throws IOException {

        // 设置响应头
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("书籍信息数据", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        // 查询数据库 获取书籍信息
        // List<Book> bookList = list();

        // 使用easyExcel写入
        // 写法一
        // EasyExcel.write(response.getOutputStream(), Book.class).sheet("书籍信息数据").doWrite(bookList);
        // 写法二
        // 查询数据库
        EasyExcel.write(response.getOutputStream(), Book.class).sheet("书籍信息数据").doWrite(this::list);
    }

}
