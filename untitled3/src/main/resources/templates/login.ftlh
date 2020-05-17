<#import "parts/common.ftlh" as common>
<#import "parts/authorization.ftlh" as auth>
<@common.page>
    <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
        <div class="alert alert-danger" role="alert">
            ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
        </div>
    </#if>
    <@auth.login "/login" false/>
</@common.page>