﻿$(function(){$("#datepicker").datepicker(),$(".one").click(function(){$(".one").removeClass("one-hover"),$(this).addClass("one-hover"),$(this).parent().find(".kid").toggle()});var e=$(".left_c"),t=$(".right_c"),n=$(".conframe");$(".nav-tip").click(function(){e.css("left")=="8px"?(e.animate({left:-300},500),t.animate({left:21},500),n.animate({left:29},500),$(this).animate({"background-position-x":"-12"},300)):(e.animate({left:8},500),t.animate({left:260},500),n.animate({left:268},500),$(this).animate({"background-position-x":"0"},300))}),$(".top-menu-nav li").click(function(){$(".kidc").hide(),$(this).find(".kidc").show()}),$(".kidc").bind("mouseleave",function(){$(".kidc").hide()});var brow=$.browser;if(brow.msie){if(brow.version < 9){var conframeDiv_width =$("#conframeDiv").width();var conframeDiv_height =$("#conframeDiv").height();$("#conframe").width(conframeDiv_width);$("#conframe").height(conframeDiv_height);return;}}$("#conframe").width("100%");$("#conframe").height("100%");})