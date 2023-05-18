<#-- @ftlvariable name="campo" type="com.example.models.Campo" -->

<#import "_layout.ftl" as layout />
<@layout.header>
    <div>
        <h3>
            ${campo.name}
        </h3>
        <p>
            ${campo.value}
        </p>
        <p>
            ${campo.descripcion}
        </p>
        <p>
            ${campo.sectionId}
        </p> <p>
            ${campo.order}
        </p>
        <hr>
        <p>
            <a href="/campos/${campo.id}/edit">Edit campo</a>
        </p>
    </div>
</@layout.header>