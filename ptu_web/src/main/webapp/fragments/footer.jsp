<%-- 
    Document   : footer
    Created on : 3 ??? 2020 ?., 17:04:49
    Author     : Skakun
--%>
        <%@page import="ru.sfedu.ptu_web.Constants"%>
        <%@page import="java.text.SimpleDateFormat"%>
        <%@page import="java.util.Date"%>
        <%
            Date dNow = new Date( );
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy");
        %>
        </main>
        <footer class="card-footer text-muted navbar-dark bg-dark fixed-bottom">
            <!-- Copyright -->
            <div class="footer-copyright text-center py-1">© Dmitry Skakun <%=ft.format(dNow)%> Copyright:
              <a href="<%=Constants.URL%>"><%=Constants.URL%></a>
            </div>
            <!-- Copyright -->

        </footer>
    </body>
</html>
