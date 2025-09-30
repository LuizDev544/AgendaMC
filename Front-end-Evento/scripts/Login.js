document.getElementById("loginForm").addEventListener("submit", async (e) => {
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
      localStorage.setItem("auth", btoa(username + ":" + password)); 
      document.getElementById("msg").innerText = "Login bem-sucedido!";
      window.location.href = "PainelADM.html";
    } else {
      document.getElementById("msg").innerText = "Usuário ou senha inválidos!";
    }
  } catch (error) {
    document.getElementById("msg").innerText = "Erro ao conectar no servidor.";
  }
});
