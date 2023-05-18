<#-- @ftlvariable name="campos" type="kotlin.collections.List<com.example.models.Campo>" -->

<#-- @ftlvariable name="articles" type="kotlin.collections.List<com.example.models.Article>" -->
<#import "_layout.ftl" as layout />
<@layout.header>
    <hr>
    <#list articles?reverse as article>
        <div>
            <h3>
                <a href="/articles/${article.id}">${article.title}</a>
            </h3>
            <p>
                ${article.body}
            </p>


        </div>
    </#list>
    <hr>
    <p>
        <a href="/articles/new">Create article</a>
        <br>
        <br>
        <a href="/campos">Campos</a>

    </p>
</@layout.header>