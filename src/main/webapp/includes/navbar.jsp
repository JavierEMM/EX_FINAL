<nav class="navbar navbar-expand-md navbar-light bg-light">
    <a class="navbar-brand" href="#">Gestion HR</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link"
                   href="<%=request.getContextPath()%>/">
                    Estadisticas
                </a>
            </li>
            <li class="nav-item">
                <span class="nav-link text-dark">
                    Bienvenido  (<a
                        href="<%=request.getContextPath()%>/">Cerrar sesion</a>)
                </span>
            </li>
        </ul>
    </div>
</nav>
