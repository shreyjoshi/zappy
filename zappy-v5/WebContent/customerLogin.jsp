<%@page import="java.util.ArrayList,beans.ProductView,beans.Customer" %>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<title>Customer login</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<style type="text/css">

@import url('https://fonts.googleapis.com/css?family=Nunito');
@import url('https://fonts.googleapis.com/css?family=Poiret+One');

body, html {
	height: 100%;
	background-repeat: no-repeat;    /*background-image: linear-gradient(rgb(12, 97, 33),rgb(104, 145, 162));*/
	background:black;
	position: relative;
}
#login-box {
	position: absolute;
	top: 0px;
	left: 50%;
	transform: translateX(-50%);
	width: 350px;
	margin: 150px 0;
	border: 1px solid black;
	background: rgba(48, 46, 45, 1);
	min-height: 250px;
	padding: 20px;
	z-index: 9999;
}
#login-box .logo .logo-caption {
	font-family: 'Poiret One', cursive;
	color: white;
	text-align: center;
	margin-bottom: 0px;
}
#login-box .logo .tweak {
	color: #ff5252;
}
#login-box .controls {
	padding-top: 30px;
}
#login-box .controls input {
	border-radius: 0px;
	background: rgb(98, 96, 96);
	border: 0px;
	color: white;
	font-family: 'Nunito', sans-serif;
}
#login-box .controls input:focus {
	box-shadow: none;
}
#login-box .controls input:first-child {
	border-top-left-radius: 2px;
	border-top-right-radius: 2px;
}
#login-box .controls input:last-child {
	border-bottom-left-radius: 2px;
	border-bottom-right-radius: 2px;
}
#login-box button.btn-custom {
	border-radius: 2px;
	margin-top: 8px;
	background:#ff5252;
	border-color: rgba(48, 46, 45, 1);
	color: white;
	font-family: 'Nunito', sans-serif;
}
#login-box button.btn-custom:hover{
	-webkit-transition: all 500ms ease;
	-moz-transition: all 500ms ease;
	-ms-transition: all 500ms ease;
	-o-transition: all 500ms ease;
	transition: all 500ms ease;
	background: rgba(48, 46, 45, 1);
	border-color: #ff5252;
}
#login-box button.btn-custom2 {
	border-radius: 2px;
	margin-top: auto;
	width:130px;
	background:#ff5252;
	border-color: rgba(48, 46, 45, 1);
	color: white;
	font-family: 'Nunito', sans-serif;
}
#login-box button.btn-custom2:hover{
	-webkit-transition: all 500ms ease;
	-moz-transition: all 500ms ease;
	-ms-transition: all 500ms ease;
	-o-transition: all 500ms ease;
	transition: all 500ms ease;
	background: rgba(48, 46, 45, 1);
	border-color: #ff5252;
}
#particles-js{
  	width: 100%;
  	height: 400px;
  	 background-size: cover; 
  	background-position: 50% 50%;
  	/* position: fixed;  */
  	top: 10px;
  	z-index:1;
}

.my_menu{ 
	margin-top:27px;}
.my_menu .navbar-default{
	background:none; border:none;}
.my_menu .navbar-nav .active a, .my_menu .navbar-nav .active a:hover{background:#006C3A; color:#fff;}
.my_menu .navbar-nav li a:hover{background:#006C3A; color:#fff;}
@media(max-width:1200px){
	.my_menu .pull-right{float:none !important;}
	.cartvalue .pull-left{float:none !important;}
	}
	


</style>
<script type="text/javascript">

$.getScript("https://cdnjs.cloudflare.com/ajax/libs/particles.js/2.0.0/particles.min.js", function(){
    particlesJS('particles-js',
      {
        "particles": {
          "number": {
            "value": 80,
            "density": {
              "enable": true,
              "value_area": 800
            }
          },
          "color": {
            "value": "#ffffff"
          },
          "shape": {
            "type": "circle",
            "stroke": {
              "width": 0,
              "color": "#000000"
            },
            "polygon": {
              "nb_sides": 5
            },
            "image": {
              "width": 100,
              "height": 100
            }
          },
          "opacity": {
            "value": 0.5,
            "random": false,
            "anim": {
              "enable": false,
              "speed": 1,
              "opacity_min": 0.1,
              "sync": false
            }
          },
          "size": {
            "value": 5,
            "random": true,
            "anim": {
              "enable": false,
              "speed": 40,
              "size_min": 0.1,
              "sync": false
            }
          },
          "line_linked": {
            "enable": true,
            "distance": 150,
            "color": "#ffffff",
            "opacity": 0.4,
            "width": 1
          },
          "move": {
            "enable": true,
            "speed": 6,
            "direction": "none",
            "random": false,
            "straight": false,
            "out_mode": "out",
            "attract": {
              "enable": false,
              "rotateX": 600,
              "rotateY": 1200
            }
          }
        },
        "interactivity": {
          "detect_on": "canvas",
          "events": {
            "onhover": {
              "enable": true,
              "mode": "repulse"
            },
            "onclick": {
              "enable": true,
              "mode": "push"
            },
            "resize": true
          },
          "modes": {
            "grab": {
              "distance": 400,
              "line_linked": {
                "opacity": 1
              }
            },
            "bubble": {
              "distance": 400,
              "size": 40,
              "duration": 2,
              "opacity": 8,
              "speed": 3
            },
            "repulse": {
              "distance": 200
            },
            "push": {
              "particles_nb": 4
            },
            "remove": {
              "particles_nb": 2
            }
          }
        },
        "retina_detect": true,
        "config_demo": {
          "hide_card": false,
          "background_color": "#b61924",
          "background_image": "",
          "background_position": "50% 50%",
          "background_repeat": "no-repeat",
          "background_size": "cover"
        }
      }
    );

});

</script>
</head>

<body>
<br/>

			<div class="container1-fluid">
			<div class="row">
			<div class="col-sm-3">
			<a href="ProductCustomerView"><img src="images/logo1.jpg" alt="zappyhome" ></img></a>
			</div>
			
			
			<div class="col-sm-8 my_menu">
			<nav class="navbar navbar-default">
			<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#mynavbar">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			</button>
			</div>
			
			
			<div class="collapse navbar-collapse" id="mynavbar">
			<ul class="nav navbar-nav pull-right">
			<li><a href="Home.jsp">Home</a></li>
			
			<%		
	if(session.getAttribute("cid")==null)
	{
			%>
	
			<li><a href="index.jsp">Admin Login</a></li>
			
			<li  class="active"><a href="customerLogin.jsp">Log In</a></li>
			
			<%	}
			else
			{
		
			%>	
			<li><a href="customerHome.jsp"> your profile</a></li>
			<li><a href="logout.jsp">Log out</a></li>&nbsp;
	 		<% } %>
	 		</ul>
	 		</div>
			</nav>		
			</div>	
			
			
			</div>
			</div>
<div class="container">
	<div id="login-box">
		<div class="logo">
			<img src="http://lorempixel.com/output/people-q-c-100-100-1.jpg" class="img img-responsive img-circle center-block"/>
			<h1 class="logo-caption"><span class="tweak">L</span>ogin</h1>
		</div><!-- /.logo -->
		<div class="controls">
		<form action="CheckCustomerLogin" method="post">
			<input type="email" name="cid" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,63}$" placeholder="Username" class="form-control" required />
			<input type="password" name="cpass" placeholder="Password" class="form-control" required />
			<button type="submit" class="btn btn-default btn-block btn-custom">Login</button>
			</form>
			<p align="right"><a href="forgetpass.jsp"><font color="white">forget pass?</font></a></p>
			<div>
			<a href="SignUp.jsp"><button class="btn btn-default btn-block btn-custom"></a>Sign up</button>
			<!-- <a href="forgetpass.jsp"><button class="btn btn-default btn-block btn-custom2"></a>forget password</button> -->
			</div>
		</div><!-- /.controls -->
	</div><!-- /#login-box -->
</div><!-- /.container -->
<div id="particles-js"></div>
<!--<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/particles.js/2.0.0/particles.min.js"></script>-->
</body>
</html>