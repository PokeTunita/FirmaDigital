

<%@page contentType="text/html" pageEncoding="UTF-8" session="true" language="java" %>
<% 
    HttpSession sesion = request.getSession(true);
    boolean seguir = false;
    if(sesion.getAttribute("llave")!= null ){
        seguir = true;
    }

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cifrar</title>
    <link rel="stylesheet" href="css/stylecifrar.css">
    <script src="js/validar.js"></script>
     <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
</head>
<body background="./multimedia/267464-werecat.jpg">
    <header class="header">
        <div class="cabecera logo-container">
            Cifrado AES Gutierrez Bueno Elizabeth Andrea - Vasco Giraldo Juan Esteban (5IV7)
        </div>
    </header> 
    <section class="main">
        <% 
            if(seguir){
        %>
        <center><h1>Cifrar y Firmar documento</h1></center>
        <form action="cifrar" method="POST" name="formulario" enctype="multipart/form-data">
            <div class="contenedor" >
                <p><h3>Ingrese El texto que desea Cifrar</h3></p>
                <div class="input__row uploader" style="margin-left: 35%;">
                <div id="inputval2" class="input-value"></div>
                <label for="file_2"></label>
                <input id="file_2" name="file_2" class="upload" type="file" >
                </div>
                <br>
                <center><h3>Escoge EL tipo de Llave que Deseas para el cifrado</h3></center>
                <label class="radio"><input type="radio" name ="tipo" value="128" checked>128 Bits (16 Caracteres)</label>
                <br>
                <br>
                <label class="radio"><input type="radio" name ="tipo" value="192">192 Bits (24 Caracteres)</label>
                <br>
                <br>
                <label class="radio"><input type="radio" name ="tipo" value="256">256 Bits (32 Caracteres)</label>
                <br>
                <br>
                <input type="text" id="llave" name="llave" maxlength="32" minlength="16" class="llave" placeholder="Introduzca la llave">
                <br>
                <br>
                <button type="button" onclick="Cifrar()" class="boton">Cifrar</button>
                <br>
                <a href="descifrar.html"><button type="button" class="boton"><center>Descifrar y Comprobar firmar</center></button></a>
                <br>
                
                <br>
                <br>
            </div>
        </form>
        <% 
            }else{
        %>
        <a href="Crearllaves"><button type="button" class="boton"><center>Crear llaves</center></button></a>
        <% } %>
    </section>
    <script>
        $('#file_2').on('change',function(){
        $('#inputval2').text( $(this).val());
        });
    </script>
</body>
</html>
