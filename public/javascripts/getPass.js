function busqueda(){
	console.log("me dieron click");
	$(function() {
	var valorABuscar=$("#plataformaBusc").val().toLowerCase();
  console.log(valorABuscar);
  return $.get("/passwordsList", function(passwords) {
    console.log(passwords.length);
    for(var i=0; i<passwords.length; i++){
        if(valorABuscar.localeCompare(passwords[i].plataforma.toLowerCase())==0){
        var lastString= "<td>"+ passwords[i].plataforma + "</td>" + "<td>"+ passwords[i].cuenta + "</td>" + "<td>"+ passwords[i].password + "</td>";
        $('#passwords').html(lastString);
      }
    }
  });
});
}

$(function(){
  $("#agregar").on("click", function(){
    $("#formAgregarPass").validate ({
      rules:{
        plataforma:{required:true,maxlength:21},
        cuenta:{required:true,maxlength:58},
        password:{required:true,maxlength:39},
      },
      messages:{
        plataforma:{required:"Campo Obligatorio",maxlength:"Maximo 21"},
        cuenta:{required:"Campo Obligatorio",maxlength:"Maximo 58"},
        password:{required:"Campo Obligatorio",maxlength:"Maximo 39"},
      }
    });
  });
});
