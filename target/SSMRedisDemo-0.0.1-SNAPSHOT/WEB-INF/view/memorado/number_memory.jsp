<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ include file="/include.inc.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<c:set var="ctx" value="${pageContext['request'].contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>数字记忆</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0 user-scalable=no maximum-scale=1.0">
<script type="text/javascript" src="${ctx}/js/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="${ctx}/css/bootstrap.css" />
<script>
	//刷新时间（单位:毫秒）	
	refresh_times_interval = 3000;
	//是否自动刷新
	auto_refresh_flag = 'N';
</script>
</head>
<body class="container">
	<h3 class="text-center">随机数字</h3>
	<form role="form" class="form-horizontal">
		<div class="form-group">
			<label for="refresh_time_id" class="col-xs-4 col-sm-4 control-label">刷新间隔(毫秒)</label>
			<div class="col-xs-8  col-sm-8">
				<input type="text" onchange="refresh_num_change()"
					class="form-control" id="refresh_time_id">
			</div>
		</div>

		<div class="form-group">
			<label for="random_num_id" class="col-xs-4 col-sm-4 control-label">随机数字</label>
			<div class="col-xs-8  col-sm-8">
				<input type="text" disabled class="form-control" id="random_num_id">
			</div>
		</div>
		<div class="form-group">
			<label for="random_word_id" class="col-xs-4 col-sm-4 control-label">随机词汇</label>
			<div class="col-xs-8  col-sm-8">
				<input type="text" disabled class="form-control" id="random_word_id">
			</div>
		</div>
	</form>


	<div class="row">
		<div class="col-xs-4 col-sm-4">
			<button type="button" id="start_random_num"
				onclick="start_random_num()" class="btn btn-primary">数字</button>
		</div>
		<div class="col-xs-4 col-sm-4">
			<button type="button" id="start_random_word"
				onclick="start_random_word()" class="btn btn-primary">词汇</button>
		</div>
		<div class="col-xs-4 col-sm-4">
			<button type="button" id="start_random_num_and_word"
				onclick="start_random_num_and_word()" class="btn btn-primary">数字+词汇</button>
		</div>
	</div>
	<script>
		//刷新数字
		function refresh_num() {
			if (auto_refresh_flag == 'N') {
				clearInterval(interval);
				return;
			}

			$.ajax({
				type : "POST",
				url : "${ctx}/memorado/get_random_number",
				datatype : "json",
				async : false,
				scriptCharset : "utf-8",
				data : {},
				success : function(data) {
					debugger;
					$('#random_num_id').val(data);
				},
				error : function() {
					clearInterval(interval);
				}
			});
		}

		//刷新词汇
		function refresh_word() {
			if (auto_refresh_flag == 'N') {
				clearInterval(interval);
				return;
			}
			$.ajax({
				type : "POST",
				url : "${ctx}/memorado/get_random_word",
				datatype : "json",
				async : false,
				scriptCharset : "utf-8",
				data : {},
				success : function(data) {
					debugger;
					$('#random_word_id').val(data);
				},
				error : function() {
					clearInterval(interval);
				}
			});
		}

		//刷新数字+词汇
		function refresh_num_and_word() {
			if (auto_refresh_flag == 'N') {
				clearInterval(interval);
				return;
			}

			$.ajax({
				type : "POST",
				url : "${ctx}/memorado/get_random_num_and_word",
				datatype : "json",
				async : false,
				scriptCharset : "utf-8",
				data : {},
				success : function(data) {
					debugger;
					$('#random_num_id').val(data.num);
					$('#random_word_id').val(data.word);
				},
				error : function() {
					clearInterval(interval);
				}
			});
		}

		//开、停按钮
		function start_random_num() {
			$('#refresh_time_id').val(refresh_times_interval);

			if (auto_refresh_flag == 'N') {
				auto_refresh_flag = 'Y';
				$('#start_random_num').addClass('active');
				//自动更新数字
				interval = setInterval(function() {
					refresh_num();
				}, refresh_times_interval);
			} else {
				auto_refresh_flag = 'N';
				$('#start_random_num').removeClass('active');
				$('#random_num_id').text('');
				clearInterval(interval);
			}
		};

		//开、停按钮
		function start_random_word() {
			$('#refresh_time_id').val(refresh_times_interval);

			if (auto_refresh_flag == 'N') {
				auto_refresh_flag = 'Y';
				$('#start_random_word').addClass('active');
				//自动更新数字
				interval = setInterval(function() {
					refresh_word();
				}, refresh_times_interval);
			} else {
				auto_refresh_flag = 'N';
				$('#start_random_word').removeClass('active');
				$('#random_word_id').text('');
				clearInterval(interval);
			}
		};

		//开、停按钮，随机数字加词汇
		function start_random_num_and_word() {
			$('#refresh_time_id').val(refresh_times_interval);

			if (auto_refresh_flag == 'N') {
				auto_refresh_flag = 'Y';
				$('#start_random_num_and_word').addClass('active');
				//自动更新数字
				interval = setInterval(function() {
					refresh_num_and_word();
				}, refresh_times_interval);
			} else {
				auto_refresh_flag = 'N';
				$('#start_random_num_and_word').removeClass('active');
				clearInterval(interval);
			}
		};

		//数字修改
		function refresh_num_change() {
			refresh_times_interval = $('#refresh_time_id').val();
		}

		$(document).ready(function() {
			$('#refresh_time_id').val(refresh_times_interval);
		});
	</script>
</body>
</html>