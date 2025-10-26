package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "BookListServlet", urlPatterns = "")
public class BookListServlet extends HttpServlet {
    private final BookService bookService;
    private final SpringTemplateEngine springTemplateEngine;

    public BookListServlet(SpringTemplateEngine springTemplateEngine,BookService bookService) {
        this.bookService = bookService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("book_list",this.bookService.listAll());

        String searchText = "";
        double minRating = 0.0;
        if (req.getParameter("minRating") != null && req.getParameter("searchText")!= null) {
            searchText = req.getParameter("searchText");
            minRating = Double.parseDouble(req.getParameter("minRating"));
        }
        
        context.setVariable("searchBook",this.bookService.searchBooks(searchText,minRating));
        
        springTemplateEngine.process("listBooks.html", context, resp.getWriter());
    }
}

