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
    	
    		<h2 class="intro">Hand-coded <strong>HTML</strong> and <strong>CSS</strong> is what I do.</h2>
    		
    		<p>Now this is a story all about how my life got twisted upside down and I'd like to take a minute just sit right there I'll tell you how I became the prince of a town called Bel-Air. In West Philadelphia born and raised on the playground my momma said most of my days chilling out, maxing and relaxing all cool and all shooting some b-ball outside of school when a couple of guys they were up to no good started making trouble in our neighbourhood I got in one little fight and my mom got scared, she said your moving in with your auntie and uncle in Bel-Air</p>
    	
    	</section>
 
		<footer> <!-- HTML5 footer tag -->
    	
    		<ul>
    			<li><a href="http://twitter.com/tkenny">Follow me on Twitter</a></li>
    			
    		</ul>
    	
    	</footer>	
    
    </section>
    
</body>

</html>