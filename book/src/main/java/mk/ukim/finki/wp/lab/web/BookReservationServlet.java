package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "BookReservationServlet", urlPatterns = "/bookReservation")

public class BookReservationServlet extends HttpServlet {
    private final BookReservationService bookReservationService;
    private final SpringTemplateEngine springTemplateEngine;
    public BookReservationServlet( SpringTemplateEngine springTemplateEngine,BookReservationService bookReservationService) {
        this.bookReservationService = bookReservationService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("ipAddress", req.getRemoteAddr());
        context.setVariable("readerName", req.getParameter("readerName"));
        context.setVariable("readerAddress", req.getParameter("readerAddress"));
        context.setVariable("numCopies", req.getParameter("numCopies"));
        context.setVariable("bookTitle", req.getParameter("bookTitle"));
        String bookTitle = req.getParameter("bookTitle");
        String readerName = req.getParameter("readerName");
        String readerAddress = req.getParameter("readerAddress");
        int numCopies = Integer.parseInt(req.getParameter("numCopies"));
        this.bookReservationService.placeReservation(bookTitle,readerName,readerAddress,numCopies);
        springTemplateEngine.process("reservationConfirmation.html", context, resp.getWriter());
    }
}
