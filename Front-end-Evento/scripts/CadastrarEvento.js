document.getElementById("formCadastro").addEventListener("submit", async (e) => {
  e.preventDefault();

  const nome = document.getElementById("nome").value;
  const data = document.getElementById("data").value;
  const descricao = document.getElementById("descricao").value;
  const token = localStorage.getItem("token");

  try {
    const response = await fetch("http://localhost:8080/eventos", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": token ? `Bearer ${token}` : ""
      },
      body: JSON.stringify({ nome, data, descricao })
    });

    if (!response.ok) throw new Error("Erro ao cadastrar evento");

    document.getElementById("msg").innerText = "Evento cadastrado com sucesso!";
    document.getElementById("formCadastro").reset();
  } catch (error) {
    document.getElementById("msg").innerText = "Falha no cadastro do evento!";
  }
});
