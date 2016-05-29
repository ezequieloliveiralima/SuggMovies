package com.br.suggmovies.taglibs;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author ezequieloliveira
 */
public class DateTag extends SimpleTagSupport {
    private Date date;
    private String name;

    public void setDate(Date date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void doTag() throws JspException, IOException {
        super.doTag();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String value = format.format(date);
        
        JspWriter out = getJspContext().getOut();
        out.println("<input type=\"date\" name=\"" + name + "\" value=\"" + value + "\">");
    }
}
