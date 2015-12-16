<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我迷路了</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="<%=basePath%>resource/css/treecss/style.css" type="text/css" media="screen, projection" />
	<script type="text/javascript" src="<%=basePath%>resource/js/treejs/verlet-1.0.0.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/css/notFound.css">
  </head>
  
  <body>
  		<center>
  		<span id="time" class="numStyle">46</span><span style="color:orange;">秒种后自动跳转,如果不跳转,请点击下面链接</span>
		<script language="JavaScript1.2" type="text/javascript">
			//  Place this in the 'head' section of your page.  

			function delayURL(url) {
				var delay = document.getElementById("time").innerHTML;
				if (delay > 0) {
					delay--;
					document.getElementById("time").innerHTML = delay;
				} else {
					window.top.location.href = url;
				}
				setTimeout("delayURL('" + url + "')", 1000);

			}
		//-->
		</script>

		<a href="<%=basePath%>">返回首页</a>
		<script type="text/javascript">
			delayURL("index.html");
		</script>
		
		
			<h1 style="color:pink;">额 404<span style="color:black;font-family: '黑体';">:(</span>迷路了...</h1>
		</center>    
		
		<div style="text-align:center;clear:both;">
<script src="/gg_bd_ad_720x90.js" type="text/javascript"></script>
<script src="/follow.js" type="text/javascript"></script>
</div>
<canvas id="scratch" style="width: 800px; height: 500px;"></canvas>
<script type="text/javascript">

	function lerp(a, b, p) {
		return (b - a) * p + a;
	}

	VerletJS.prototype.tree = function (origin, depth, branchLength, segmentCoef, theta) {

		var lineCoef = 0.7;
		this.origin = origin;
		this.base = new Particle(origin);
		this.root = new Particle(origin.add(new Vec2(0, 10)));


		var composite = new this.Composite();
		composite.particles.push(this.base);
		composite.particles.push(this.root);
		composite.pin(0);
		composite.pin(1);


		var branch = function (parent, i, nMax, coef, normal) {
			var particle = new Particle(parent.pos.add(normal.scale(branchLength * coef)));
			composite.particles.push(particle);

			var dc = new DistanceConstraint(parent, particle, lineCoef);
			dc.p = i / nMax; // a hint for drawing
			composite.constraints.push(dc);

			particle.leaf = !(i < nMax);

			if (i < nMax) {
				var a = branch(particle, i + 1, nMax, coef * coef, normal.rotate(new Vec2(0, 0), -theta));
				var b = branch(particle, i + 1, nMax, coef * coef, normal.rotate(new Vec2(0, 0), theta));


				var jointStrength = lerp(0.7, 0, i / nMax);
				composite.constraints.push(new AngleConstraint(parent, particle, a, jointStrength));
				composite.constraints.push(new AngleConstraint(parent, particle, b, jointStrength));
			}

			return particle;
		}

		var firstBranch = branch(this.base, 0, depth, segmentCoef, new Vec2(0, -1));

		composite.constraints.push(new AngleConstraint(this.root, this.base, firstBranch, 1));

		// animates the tree at the beginning
		var noise = 10;
		var i;
		for (i = 0; i < composite.particles.length; ++i)
			composite.particles[i].pos.mutableAdd(new Vec2(Math.floor(Math.random() * noise, Math.floor(Math.random() * noise))));

		this.composites.push(composite);
		return composite;
	}

	window.onload = function () {
		var canvas = document.getElementById("scratch");

		// canvas dimensions
		var width = parseInt(canvas.style.width);
		var height = parseInt(canvas.style.height);

		// retina
		var dpr = window.devicePixelRatio || 1;
		canvas.width = width * dpr;
		canvas.height = height * dpr;
		canvas.getContext("2d").scale(dpr, dpr);

		// simulation
		var sim = new VerletJS(width, height, canvas);
		sim.gravity = new Vec2(0, 0);
		sim.friction = 0.98;

		// entities
		var tree1 = sim.tree(new Vec2(width / 4, height - 120), 5, 70, 0.95, (Math.PI / 2) / 3);
		var tree2 = sim.tree(new Vec2(width - width / 4, height - 120), 5, 70, 0.95, (Math.PI / 2) / 3);

		tree2.drawParticles = function (ctx, composite) {
			var i;
			for (i = 0; i < composite.particles.length; ++i) {
				var particle = composite.particles[i];
				if (particle.leaf) {

					ctx.beginPath();
					ctx.arc(particle.pos.x, particle.pos.y, 25, 0, 2 * Math.PI);
					ctx.fillStyle = "#679d7c";
					ctx.fill();
				}
			}
		}

		tree2.drawConstraints = function (ctx, composite) {
			var i;

			ctx.save();
			ctx.strokeStyle = "#543324";
			ctx.lineCap = "round";

			for (i = 0; i < composite.constraints.length; ++i) {
				var constraint = composite.constraints[i];
				if (!(constraint instanceof DistanceConstraint && typeof constraint.p != "undefined"))
					continue;

				ctx.beginPath();
				ctx.moveTo(constraint.a.pos.x, constraint.a.pos.y);
				ctx.lineTo(constraint.b.pos.x, constraint.b.pos.y);
				ctx.lineWidth = lerp(10, 2, constraint.p);
				ctx.stroke();
			}

			ctx.restore();
		}


		// animation loop
		var loop = function () {
			sim.frame(16);
			sim.draw();
			requestAnimFrame(loop);
		};

		loop();
	};

	
</script>
  </body>
</html>
