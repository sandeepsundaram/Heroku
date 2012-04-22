<!DOCTYPE html>

<%@ page import="model.Zodiac" %>
<html lang="en">

<head>    
    <title>ZodM</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />    
    <link rel="stylesheet" href="style.css" type="text/css" media="screen" />        
</head>

<body>
<% 
	Zodiac zod = (Zodiac) session.getAttribute("zodiac");
%>
    <header>
    	<div id="headercontainer">    
    		<h1><a class="introlink anchorLink" ><%= zod.getName() %></a></h1>   
			<nav>
			<ul>
				<li><a class="introlink anchorLink" href="#intro">Intro</a></li>
				<li><a class="portfoliolink anchorLink" href="#portfolio">Portfolio</a></li>
			</ul>	
			</nav>			
    	</div>    
    </header>

    <section id="contentcontainer"> 
    
    	<section id="zodiac" class="rounded-corners">	

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
    			
		<footer>
			<ul>
				<li><a href="http://twitter.com/mssandy">Follow me on Twitter</a></li>			
			</ul>	
		</footer>
	
    </section>
    
</body>

</html>