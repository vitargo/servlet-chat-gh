package com.github.chat.handlers;

import javax.servlet.annotation.WebFilter;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter {//implements Filter {

//    private ServletContext context;
//
//    public void init(FilterConfig fConfig) {
//        this.context = fConfig.getServletContext();
//        this.context.log("AuthenticationFilter initialized");
//    }
//
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        System.out.println("Enter doFilter");
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//
//        String uri = req.getRequestURI();
//        this.context.log("Requested Resource::" + uri);
//        String str = req.getHeader("Content_Length");
//
//        if (req.getMethod().equals("GET") && Integer.parseInt(str) == 0){
//            this.context.log("Request without Token!");
//            RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
//            dispatcher.forward(request, response);
//        } else {
//            chain.doFilter(request, response);
//        }
//    }
//
//    public void destroy() {
//    }

}