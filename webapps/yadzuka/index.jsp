<%@
  page contentType="text/html; charset=UTF-8"
  import="java.util.*"
  import="java.io.*"
%>

<%!

private static int count = 0;
java.util.Vector names = new Vector();

%>

Hello! JSP!

<%

count++;

out.println("This is dynamic output, call_count : " + count);
out.println("<ul>");
for(int i=0; i< names.size(); i++)
{
 out.print("<li>");
 out.println((String)names.get(i));
}

out.println("</ul>");
if(request.getParameter("name") != null)
{
 names.add(request.getParameter("name"));
 out.println("New name added : " + request.getParameter("name"));
}
%>

<div>
<form action="/yadzuka/" method="GET">
     <input id="name" name="name" type="text">
     <button id="button" ">add</button>
</form>

</div>
