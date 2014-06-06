<%@ page import="java.util.*"%>

<html>
<head>
<title>Server Information</title>
</head>
<body>
<pre>
<%

		Set<Map.Entry<Thread,StackTraceElement[]>> stackTrace = Thread.getAllStackTraces().entrySet();
		Iterator<Map.Entry<Thread,StackTraceElement[]>> it = stackTrace.iterator();
		
		while (it.hasNext())
		{
			Map.Entry<Thread,StackTraceElement[]> nextElementEntry = it.next();
			Thread thread = (Thread) nextElementEntry.getKey();
			StackTraceElement[] stack = (StackTraceElement[]) nextElementEntry.getValue();
			if (thread.equals(Thread.currentThread())) {
				continue;
			}
		    out.println("<h1> thread: "+thread.getName() + "</h1>");
			for (int i=0;i<stack.length;i++) {
			   out.println("<tr>"+stack[i].toString()+"</tr>");
			}
	    }
%>
</pre>
</body>
</html>