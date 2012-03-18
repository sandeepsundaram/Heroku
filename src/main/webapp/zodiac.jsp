<!DOCTYPE html>
<html lang="en">
<%@ page import="model.Zodiac" %>
<% 
	Zodiac zod = (Zodiac) session.getAttribute("zodiac");
%>

<head>    
    <title>ZodM</title>
    <link rel="stylesheet" href="style.css" type="text/css" media="screen" />
</head>

<body>

    <header>    
    	<div id="headercontainer">    
    		<h1><a class="introlink anchorLink" href="#intro"><%= zod.getName() %></a></h1>    		
    		<nav> 
    			<ul>
    				<li><a class="introlink anchorLink" href="#intro">Intro</a></li>
    				<li><a class="portfoliolink anchorLink" href="#portfolio">Portfolio</a></li>
    			</ul>				
    		</nav>    	
    	</div>    
    </header>

    <section id="contentcontainer"> 
    
    	<section id="intro">
    	
    		<h2 class="intro"><%= zod.getSunSign() %></h2>
    		
    		<p>s</p>
    	
    	</section>
 
		<footer> <!-- HTML5 footer tag -->
    	
    		<ul>
    			<li><a href="http://twitter.com/mssandy">Follow me on Twitter</a></li>
    			
    		</ul>
    	
    	</footer>	
    
    </section>
    
</body>

</html>