<#-- @ftlvariable name="campo" type="com.example.models.Campo" -->
<#import "_layout.ftl" as layout />
<@layout.header>
    <div>
        <h3>Create campo</h3>
        <form action="/campos" method="post">
            <p>
                <input type="text" name="name" placeholder="Nombre">
            </p>
            <p>
                <textarea name="value" placeholder="Valor"></textarea>
            </p>
            <p>
                <textarea name="descripcion" placeholder="descripcion"></textarea>
            </p>
            <p>
                <textarea name="sectionId" placeholder="sectionId"></textarea>
            </p>
            <p>
                <input type="number" name="order" placeholder="order">
            </p>
            <p>
                <input type="submit">
            </p>
        </form>
    </div>
</@layout.header>
