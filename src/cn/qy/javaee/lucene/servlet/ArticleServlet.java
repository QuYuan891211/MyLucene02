package cn.qy.javaee.lucene.servlet;

import cn.qy.javaee.lucene.entity.Article;
import cn.qy.javaee.lucene.entity.Pagination;
import cn.qy.javaee.lucene.service.ArticleService;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(name = "ArticleServlet")
public class ArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String currPageNum = request.getParameter("page");

        if(currPageNum == null && currPageNum.isEmpty()){
            currPageNum = "1";
        }
        Integer currPageNo = Integer.parseInt(currPageNum);
        String keyWords  = request.getParameter("keyWords");

        if (keyWords != null && keyWords.length()>0){
            ArticleService articleService = new ArticleService();
            try{
                 Pagination pagination = articleService.fy(keyWords,currPageNo);
                 Map<String,Object> map = new LinkedHashMap<String, Object>();

                map.put("total",pagination.getTotalNum());
                map.put("rows",pagination.getList());

                JSONArray jsonArray = JSONArray.fromObject(map);
                String json = jsonArray.toString();
                json = json.substring(1,json.length()-1);
                //用流的方式
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write(json);
                response.getWriter().flush();
                response.getWriter().close();

            }catch (Exception e){
                throw new RuntimeException(e);
            }

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
