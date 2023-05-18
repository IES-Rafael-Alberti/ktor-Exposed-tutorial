<#-- @ftlvariable name="articles" type="kotlin.collections.List<com.example.models.Article>" -->
<#-- @ftlvariable name="campos" type="kotlin.collections.List<com.example.models.Campo>" -->

<#import "_layout.ftl" as layout />
<@layout.header>
    <hr>
    <#list campos as campo>
        <div>
            <h3>
                <a href="/campos/${campo.id}">${campo.id}</a>
            </h3>
            <p>
                ${campo.descripcion}
            </p>
        </div>
    </#list>
    <hr>
    <p>
        <a href="/campos/new">Create campo</a>
        <br>
        <br>
        <a href="/articles">Articulos</a>
    </p>
</@layout.header>
