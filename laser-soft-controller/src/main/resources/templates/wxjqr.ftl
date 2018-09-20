
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<title>个人机器人</title>
		<style type="text/css">
			body,html,div,h1,h2,h3,h4,h5,p,a,img{
				padding: 0;
				margin: 0;
				font-size: 100%;
				font-weight: normal;
			}
			body,html{
				height:100%;
				background: #186aa9 url(images/sky-background.png) top repeat-x;	
				overflow:hidden;
				padding:0;
				margin:0;
				font-family:Arial, Helvetica, sans-serif;
			}
			*, *:before, *:after {
				-moz-box-sizing: border-box;
				-webkit-box-sizing: border-box;
				box-sizing: border-box;
			}
			a{
				text-decoration: none;
			}
			.netwarp{
				width:980px;
				margin:0px auto;
				position:relative;
				height:100%;
				background: url(images/sky-shine.jpg) top center no-repeat;
			}
			.network_pad{
				padding-top: 100px;
				text-align: center;
			}
			.network_pad img{
				width: 560px;
				margin-bottom: 55px;
			}
			.network_pad h3{
				font-size: 30px;
				color: #FFFFFF;
				margin-bottom: 20px;
			}
			.network_pad h4{
				font-size: 24px;
				color: #FFFFFF;
				margin-bottom: 30px;
			}
			.network_btn{
				display: inline-block;
				width: 150px;
				padding: 10px;
				text-align: center;
				line-height: 20px;
				font-size: 20px;
				background-color: transparent;
				color: #FFFFFF;
				border-radius: 5px;
				margin: 0 15px;
				background-color: rgba(255,255,255,.3);
			}
			@media screen and (max-width: 980px) {
				.netwarp{
					width:100%;
				}
			}
			@media screen and (max-width: 840px) {
				.network_pad img{
					width: 450px;
					margin-bottom: 40px;
				}
				.network_pad h3{
					font-size: 26px;
				}
				.network_pad h4{
					font-size: 20px;
				}
				.network_btn{
					font-size: 18px;
					width: 120px;
					padding: 8px 10px;
				}
			}
			@media screen and (max-width: 480px) {
				.network_pad{
					padding-top: 90px;
				}
				.network_pad img{
					width: 85%;
					margin-bottom: 30px;
				}
				.network_pad h3{
					font-size: 20px;
					margin-bottom: 10px;
				}
				.network_pad h4{
					font-size: 16px;
					margin-bottom: 20px;
				}
				.network_btn{
					font-size: 16px;
					width: 100px;
					padding: 6px 8px;
					margin: 0 10px;
				}
			}
		</style>
	</head>
	<body>
		<div class="netwarp">
			<div class="network_pad">
				<img src="${path}" />
			</div>
		</div>
	</body>

</html>