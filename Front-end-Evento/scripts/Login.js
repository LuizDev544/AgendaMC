document.getElementById("formLogin").addEventListener("submit", async (e) => {
  e.preventDefault();

  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;

  try {
    const response = await fetch("http://localhost:8080/api/admin/eventos", {
      method: "GET",
      headers: {
        "Authorization": "Basic " + btoa(username + ":" + password)
      }
    });

    if (response.ok) {
      // salva credenciais e flag de sessão
      localStorage.setItem("authHeader", "Basic " + btoa(username + ":" + password));
      localStorage.setItem("isAdmin", "true");

      window.location.href = "PainelADM.html";
    } else {
      document.getElementById("msg").innerText = "❌ Login inválido!";
    }
  } catch (error) {
    document.getElementById("msg").innerText = "❌ Erro ao conectar ao servidor!";
  }
});
