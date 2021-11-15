function Cifrar(){
   var radio = document.formulario.tipo.value;
   var key = document.formulario.llave.value;
    if(key.length != 0){
        if(radio == "128"){
            if(key.length == 16){
                document.formulario.submit();
            }else{
                alert("Para una llave de 126 bits solamente se pueden ingresar 16 caracteres")
            }
        }else if(radio == "192"){
            if(key.length == 24){
                document.formulario.submit();
            }else{
                alert("Para una llave de 192 bits solamente se pueden ingresar 24 caracteres")
            }
        }else if(radio == "256"){
            if(key.length == 32){
                document.formulario.submit();
            }else{
                alert("Para una llave de 256 bits solamente se pueden ingresar 32 caracteres")
            }
        }
    }else{
        alert("rellena todos los campos")
    }
}