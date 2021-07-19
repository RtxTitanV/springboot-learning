<#ftl output_format="HTML">
<#--<#ftl output_format="HTML" auto_esc=false>-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>autoesc、noautoesc</title>
</head>
<body>
<#--    <div>-->
<#--        <#autoesc>-->
<#--            <p>启用auto-escaping：${hello}</p>-->
<#--            &lt;#&ndash; noautoesc指令可以嵌套在autoesc指令中使用，以重新禁用auto-escaping功能 &ndash;&gt;-->
<#--            <#noautoesc>-->
<#--                <p>重新禁用auto-escaping：${hello}</p>-->
<#--            </#noautoesc>-->
<#--            <p>?no_esc也可以在autoesc指令中使用，不对单个插值进行转义：${hello?no_esc}</p>-->
<#--        </#autoesc>-->
<#--        &lt;#&ndash; 在auto-escaping（自动转义）被禁用的情况下，只转义单个插值时可以使用${expression?esc}代替autoesc指令 &ndash;&gt;-->
<#--        <p>只转义单个插值的情况下代替autoesc指令的语法：${hello?esc}</p>-->
<#--        <p>由于禁用了auto-escaping，不使用autoesc指令或?esc则不会转义：${hello}</p>-->
<#--    </div>-->
    <div>
        <#noautoesc>
            <p>禁用auto-escaping：${hello}</p>
            <#-- autoesc指令可以嵌套在noautoesc指令中使用，以重新启用auto-escaping功能 -->
            <#autoesc>
                <p>重新启用auto-escaping：${hello}</p>
            </#autoesc>
            <p>?esc也可以在noautoesc指令中使用，转义单个插值：${hello?esc}</p>
        </#noautoesc>
        <#-- 如果只是不想对单个插值进行转义，可以使用${expression?no_esc}代替noautoesc指令 -->
        <p>只是不想对单个插值进行转义情况下代替noautoesc指令的语法：${hello?no_esc}</p>
        <p>默认启用了auto-escaping，不使用noautoesc指令或?no_esc则会转义：${hello}</p>
    </div>
<#--    <div>-->
<#--        &lt;#&ndash; 使用auto-escaping的地方不允许使用escape指令 &ndash;&gt;-->
<#--        <#escape x as x?html>-->
<#--            <p>开启转义：${hello}</p>-->
<#--        </#escape>-->
<#--    </div>-->
</body>
</html>