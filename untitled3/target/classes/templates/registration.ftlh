<#import "parts/common.ftlh" as common>
<#import "parts/authorization.ftlh" as auth>
<@common.page>
    <div class="mb-1">Add new user</div>
    ${message?ifExists}
    <@auth.login "/registration" true />
</@common.page>