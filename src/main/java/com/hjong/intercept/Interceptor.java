package com.hjong.intercept;

import com.hjong.entity.ExceptionCodeMsg;
import com.hjong.entity.WebException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class Interceptor implements HandlerInterceptor {

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{

        //判断是否登录
        boolean verifyPermissions = verifyPermissions(request);
        //判断是否有权限
        boolean competence = competence(request);
        if (!verifyPermissions) {
            throw new WebException(ExceptionCodeMsg.NOT_LOGIN);
        }

        return true;//如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
        //如果设置为true时，请求将会继续执行后面的操作
    }

    public boolean verifyPermissions(HttpServletRequest request) {
        if(request.getAttribute("userId") != null){
            return true;
        }else {
            return false;
        }
    }

    public boolean competence(HttpServletRequest request) {
//        //获取当前登录对象的全部信息
//        People people = peopleMapper.selectById(getUserId(request.getHeader(Constant.TOKEN)));
//        //管理员拥有全部权限
//        if (Constant.SUPER_ADMIN.equals(people.getUserName())) {
//            return true;
//        }
//        //判断是否被授权
//        //防止空指针
//        if (people.getStartDate() != null && people.getEndDate() != null) {
//            if (dateUtils.ifDate(people.getStartDate(), people.getEndDate(), new Date())) {
//                return true;
//            }
//        }
//        //从请求头中获取的地址
//        String requestURI = request.getRequestURI();
//        //通过角色id查询当前登陆对象的所有权限
//        List<Power> list = powerMapper.selectUrl(people.getRoleid());
//        ArrayList<String> stringList = new ArrayList<>();
//        if (!StringUtils.isEmpty(list)) {
//            list.forEach(r -> {
//                stringList.add(r.getUrl());
//            });
//            return lsitUtils.ifcontainString(stringList, requestURI);
//        }
        return false;
    }
}
