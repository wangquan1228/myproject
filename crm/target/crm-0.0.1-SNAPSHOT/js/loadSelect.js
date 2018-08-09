 //使用ajax进行后台交互加载数据字典,生成select
//参数1:数据字典类型(dict_type_code)
//参数2: 将下拉框放入标签id
//参数3:生成下拉框时,select标签的name属性值
//参数4: 需要回显,选中哪个option

function loadSelect(typeCode,postionId,selectName,selectedId){
    //1.创建设select对象,name属性值
   var $select= $("<select name="+selectName+"></select>")
    
    //2.添加提示选项
     $select.append( $("<option value=''>----请选择----</option>"))
    //3.使用jQuery的ajax方法,访问后台的Action
    $.post("${pageContext.request.contextPath }/BaseDictAction", { dict_type_code:typeCode},
          function(data){
    	 //4.使用jQuery数组对象,对其遍历
        //每次遍历创建一个option对象,判断是否需要回显,并添加到select对象
        //回调函数拥有两个参数：第一个为对象的成员或数组的索引，第二个为对应变量或内容。如果需要退出 each 循环可使回调函数返回 false，其它返回值将被忽略。
    	$.each( data, function(i, json){
    		 var $option=$("<option value='"+json['dict_id']+"'>"+json["dict_item_name"]+"</option>")
    		 if(json['dict_id'] == selectedId){
                 //判断是否需要回显,如果需要使其被选择中
                 $option.attr("selected","selected");
             }
             $select.append($option);
    		});
          }, "json");
    //5,将组装好的select对象放入指定的页面
    $("#"+postionId).append($select);
}