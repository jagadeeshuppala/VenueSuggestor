package com.premierinn.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ClientDetailsFilter implements Filter {

    private final String clientId = "MTUC2ZENFCL43K2RYF3A5CJG0GT0DGDBENYF4YS41LH2NGHM";
    private final String clientSecret = "5FIOYNLMZB1MGMTSHQRRY4H5KZ2MZUDUGCB5HGKCYVY5AJ1H";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    String formatDateTime = LocalDate.now().format(formatter);


    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain)
            throws IOException, ServletException {

        request.setAttribute("clientId",clientId);
        request.setAttribute("clientSecret", clientSecret);
        request.setAttribute("dateRequested", formatDateTime);
        filterchain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterconfig) throws ServletException {}
}
