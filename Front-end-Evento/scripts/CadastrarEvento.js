document.getElementById("formCadastro").addEventListener("submit", async (e) => {
  e.preventDefault();

  const evento = {
    nomeEvento: document.getElementById("nomeEvento").value,
    descricaoDoEvento: document.getElementById("descricaoDoEvento").value,
    dataDoEvento: document.getElementById("dataDoEvento").value,
    localDoEvento: document.getElementById("localDoEvento").value,
    precoDoEvento: parseFloat(document.getElementById("precoDoEvento").value),
    capacidadeDePessoasNoEvento: parseInt(document.getElementById("capacidadeDePessoasNoEvento").value),
    tipoDoEvento: document.getElementById("tipoDoEvento").value,
    apresentadorDoEvento: document.getElementById("apresentadorDoEvento").value,
    duracaoDoEvento: document.getElementById("duracaoDoEvento").value,
  };

  try {
    const response = await fetch("http://localhost:8080/api/admin/eventos", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Basic " + btoa("admin:senha123")
      },
    body: JSON.stringify(evento)
    });

    if (!response.ok) throw new Error("Erro ao cadastrar evento");

    document.getElementById("msg").innerText = "Evento cadastrado com sucesso!";
    document.getElementById("formCadastro").reset();
  } catch (error) {
    console.error(error);
    document.getElementById("msg").innerText = "Falha no cadastro do evento!";
  }
});
