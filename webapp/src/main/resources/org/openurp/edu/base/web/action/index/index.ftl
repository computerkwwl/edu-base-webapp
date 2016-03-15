[@b.head/]
<style>
body { padding-top: 50px; }
.container{
  width:100%;
}
#menu .nav > li > a {
   padding: 10px 10px;
}
#urp_app_nav{
    float: left;
    height:50px;
    width:50px;
    border-right: 1px solid gray;
}
.nav_logo {
    position: absolute;
    top: 18px;
    left: 18px;
    vertical-align: top;
}
.app_logo{
    height:30px;
}
</style>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="margin-bottom: 0px;">
   <div class="navbar-header">
       <a id="urp_app_nav" href="#" title="" data-toggle="popover" data-placement="bottom" data-original-title="更多应用..."> <span class="nav_logo glyphicon glyphicon-th"></span></a>
 <a href="/edu/base/index" class="navbar-brand" onclick="return bg.Go(this,null)">教学基础信息管理</a>
   </div>
   
   <!--顶部导航-->
   <div><ul class="nav navbar-nav" id="top_nav_bar" ></ul></div>
</nav>


<div class="container">
  <div class="row">
    <div id="menu" class="ajax_container col-md-1" style="padding-left: 0px; padding-right: 0px;">
      <ul class="nav nav-pills" id="left_nav_bar"></ul>
    </div>
    <div id="main" class="ajax_container col-md-11" style="padding-left: 0px; padding-right: 0px;">选择一个菜单</div>
  </div>
</div>

<script>
 var menus = ${menus}
 for(var i=0;i< menus[0].menus.length;i++){
   var li = "<li"+(i==0?" class='active'":"")+"><a href='#' onclick='return switchMenu("+i+",this.id)' id='"+menus[0].menus[i].id+"'>"+menus[0].menus[i].name+"</a></li>"
   jQuery("#top_nav_bar").append(li)
 }
 var fm = menus[0].menus[0].children
 for(i=0;i<fm.length;i++){
   addMenu(fm[i],jQuery("#left_nav_bar"));
 }
 function switchMenu(menuIdex,id){
    jQuery("#left_nav_bar").html("")
    fm = menus[0].menus[menuIdex].children
    for(i=0;i<fm.length;i++){
      addMenu(fm[i],jQuery("#left_nav_bar"));
    }
    jQuery('#'+id).parent().siblings().each(function(i,li){jQuery(li).removeClass('active')});
    jQuery('#'+id).parent().addClass('active');
    return false;
 }
 function addMenu(menu,container){
   if(menu.entry){
     container.append("<li><a href='${base}"+menu.entry+"' onclick='return accessMe(this.id)' id='menu"+menu.id+"'>"+menu.name+"</a></li>")
   }else if(menu.children && menu.children.length > 0){
     var nestedId="menu"+menu.id
     container.append("<li><a href='#'>"+menu.name+"</a><ul class='nav nav-pills' id='"+nestedId+"'></ul></li>")
     for(i=0;i< menu.children.length;i++){
       addMenu(menu.children[i],jQuery("#"+nestedId));
     }
   }
 }
 
 function accessMe(id){
   jQuery('#'+id).parent().siblings().each(function(i,li){jQuery(li).removeClass('active')});
   jQuery('#'+id).parent().addClass('active');
   return bg.Go(document.getElementById(id),'main');
 }
</script>
[@b.foot/]