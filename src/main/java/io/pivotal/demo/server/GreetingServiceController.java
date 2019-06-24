package io.pivotal.demo.server;

import io.pivotal.demo.shared.FieldVerifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/gwtdemo/rest")
public class GreetingServiceController {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public GreetingServiceController(HttpServletRequest request) {
        this.request = request;
    }

    @GetMapping("/greet")
    public String greetServer(String input, HttpServletResponse response) throws IOException {
        // Verify that the input is valid.
        if (!FieldVerifier.isValidName(input)) {
            // If the input is not valid, return a Bad Request status back to
            // the client.
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Name must be at least 4 characters long");
        }

        String serverInfo = request.getServletContext().getServerInfo();
        String userAgent = request.getHeader("User-Agent");

        // Escape data from the client to avoid cross-site script vulnerabilities.
        input = escapeHtml(input);
        userAgent = escapeHtml(userAgent);

        return "Hello, " + input + "!<br><br>I am running " + serverInfo
                + ".<br><br>It looks like you are using:<br>" + userAgent;
    }

    /**
     * Escape an html string. Escaping data received from the client helps to
     * prevent cross-site script vulnerabilities.
     *
     * @param html the html string to escape
     * @return the escaped string
     */
    private String escapeHtml(String html) {
        if (html == null) {
            return null;
        }
        return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
                ">", "&gt;");
    }
}
