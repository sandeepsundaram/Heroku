<!DOCTYPE html>
<html lang="en">

<%@ page import="model.Zodiac" %>

<head>    
    <title>One Page Portfolio with HTML5 and CSS3</title>    
    <link rel="stylesheet" href="style.css" type="text/css" media="screen" />    
</head>

<body>
<% 
	Zodiac zod = (Zodiac) session.getAttribute("zodiac");
%>
    <header> 
    	<div id="headercontainer">    
    		<h1><a class="introlink anchorLink" href="#intro">Web Design Portfolio</a></h1>    		
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
				<span class="about">				
					<% 
						String charactor = zod.getCharactor(); 
						String[] props = charactor.split(",");
						for(String c : props) {
							out.print(c);
					%><br> 
					<% } %>
				</span>				
			</span>	
    	
    	</section>
 
		<footer> <!-- HTML5 footer tag -->
    	
    		<ul>
    			<li><a href="http://twitter.com/tkenny">Follow me on Twitter</a></li>
    			
    		</ul>
    	
    	</footer>	
    
    </section>
    
</body>

</html>