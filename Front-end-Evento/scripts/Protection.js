(function () {
  const isAdmin = localStorage.getItem("isAdmin");
  if (!isAdmin || isAdmin !== "true") {
    alert("Acesso não autorizado! Faça login primeiro.");
    window.location.href = "Login.html";
  }
})();