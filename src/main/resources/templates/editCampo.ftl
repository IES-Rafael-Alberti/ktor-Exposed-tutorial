<#-- @ftlvariable name="campo" type="com.example.models.Campo" -->
<#import "_layout.ftl" as layout />
<@layout.header>
    <div>
        <h3>Edit campos</h3>
        <form action="/campos/${campo.id}" method="post">
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
                <input type="submit" name="_actionCampo" value="update">
            </p>
        </form>
    </div>
    <div>
        <form action="/campos/${campo.id}" method="post">
            <p>
                <input type="submit" name="_actionCampo" value="delete">
            </p>
        </form>
    </div>
</@layout.header>