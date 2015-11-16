package util;

import model.Event;

/* @author BertGoens */
public final class eventDisplay {

    public static String eventToHtml(Event evt) {
        String result = "";

        StringBuilder sb = new StringBuilder(result);

        sb.append("<article>");
        opmaakTitel(evt, sb);
        opmaakBody(evt, sb);
        sb.append("</article>");
        return result;
    }

    private static void opmaakTitel(Event evt, StringBuilder sb) {
        sb.append("<h2>");
        sb.append(evt.getName());

        sb.append(" @ ");

        sb.append("<i>\"</i>");
        sb.append(evt.getCity());
        sb.append("<i>\"</i>");

        sb.append("<span class=\"subtitle\"> / ");
        sb.append(evt.getDate());
        sb.append("</span>");

        sb.append("</h2>");
    }

    private static void opmaakBody(Event evt, StringBuilder sb) {
        sb.append("<div class=\"border\"></div>");

        //afbeelding
        sb.append("<p>");
        sb.append("img width=\"300\" src=\"");
        sb.append(evt.getImagePath());
        sb.append("\">");

        //korte beschrijving
        // !TODO database veranderen? korte beschrijving naar event ipv agenda
        //sb.append(evt.getAgenda().get(0).getShortDescription());
        sb.append("Korte beschrijving");
        sb.append("</p>");

        //event link
        sb.append("<div class=\"button normalButton\"><a href=\"Event?id=");
        sb.append(evt.getId());
        sb.append("\">View more</a></div>");

        sb.append("<div style=\"clear:both\"></div>");
    }
}
