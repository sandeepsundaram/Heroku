<!DOCTYPE html>

<%@ page import="model.Zodiac" %>
<html lang="en">

<head>    
    <title>My Page</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />    
    <link rel="stylesheet" href="style.css" type="text/css" media="screen" />        
</head>

<body>
<% 
	Zodiac zod = (Zodiac) session.getAttribute("zodiac");
%>
    <header>
    	<div id="headercontainer">    
    		<h1><a class="introlink anchorLink"><%= zod.getName() %></a></h1>    	
    	</div>    
    </header>

    <section id="contentcontainer"> 
    
    	<section id="intro">
    		<h2 class="intro"><div class="sub"><%= zod.getSunSign() %></div></h2>				
			<ul>
    			<li>
    				Chinese Zodiac: <%= zod.getChineseSign() %>
    			</li>
    			<li>
    				Element: <%= zod.getElement() %>
    			</li>
			</ul>
						
			<span class="block">
				<h2 class="about">About Me</h2>    		
				<p><%= zod.getCharactor() %></p>			
    	    </span>	
    	</section>
    			
    </section>
    
</body>

</html>