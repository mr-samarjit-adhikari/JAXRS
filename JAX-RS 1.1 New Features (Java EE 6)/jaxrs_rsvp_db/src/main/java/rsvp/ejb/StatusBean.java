/*
 * Copyright 2011 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developers.sun.com/license/berkeley_license.html
 */


package rsvp.ejb;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import rsvp.entity.Event;
import rsvp.entity.Person;
import rsvp.entity.Response;


@Stateless
@Named
@Path("/status/{eventId}/")
public class StatusBean {
    private static final Logger logger = Logger.getLogger(
                "rsvp.ejb.StatusBean");
    
    @PersistenceContext
    private EntityManager em;
    
    private List<Event> allCurrentEvents;

    @GET
    @Produces("text/html")
    public String getResponse(@PathParam("eventId")
    Long eventId) {
        StringBuilder output = new StringBuilder();
        Event event = em.find(Event.class, eventId);
        output.append("<html lang=\"en\"><head><title>Current responses for ")
              .append(event.getName())
              .append("</title>")
              .append(
                "<link href=\"/rsvp/css/default.css\" rel=\"stylesheet\" type=\"text/css\" />")
              .append("</head>");
        output.append("<body><h1>Current responses for ")
              .append(event.getName())
              .append("</h1>");
        output.append("<p><b>Location: ")
              .append(event.getLocation())
              .append("</p>");
        output.append("<p>");
        output.append("<table summary=\"Current responses\">");
        output.append("<tr>");
        output.append("<th scope=\"col\">Name</th>");
        output.append("<th scope=\"col\">Response</th>");
        output.append("</tr>");

        Iterator<Response> i = event.getResponses()
                                    .iterator();

        while (i.hasNext()) {
            Response response = i.next();
            Person person = response.getPerson();
            output.append("<tr>");
            output.append("<td><a href=\"/jaxrs_rsvp_db/resources/")
                  .append(event.getId())
                  .append("/")
                  .append(person.getId())
                  .append("\">")
                  .append(person.getFirstName())
                  .append(" ")
                  .append(person.getLastName())
                  .append("</a></td>");
            output.append("<td>")
                  .append(response.getResponseText())
                  .append("</td>");
            output.append("</tr>");
        }

        output.append("</table></p>");
        output.append(
                "<p><a href=\"/rsvp/faces/index.xhtml\">Back to main page.</a>");
        output.append("</body></html>");

        return output.toString();
    }

    public List<Event> getAllCurrentEvents() {
        logger.info("Calling getAllCurrentEvents");
        this.allCurrentEvents = (List<Event>) em.createNamedQuery(
                    "rsvp.entity.Event.getAllUpcomingEvents")
                                                .getResultList();

        if (this.allCurrentEvents == null) {
            logger.warning("No current events!");
        }

        return this.allCurrentEvents;
    }

    public void setAllCurrentEvents(List<Event> events) {
        this.allCurrentEvents = events;
    }
}
