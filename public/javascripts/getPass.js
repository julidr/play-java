$(function() {
  return $.get("http://localhost:9000/passwordsList?plataforma=Twitter", function(passwords) {
    return $.each(passwords, function(plataforma, password) {
      return $('#passwords').append($("<td>").text(password));
    });
  });
});