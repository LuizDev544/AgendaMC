document.getElementById("formCadastro").addEventListener("submit", async (e) => {
    e.preventDefault();

    const msgElement = document.getElementById("msg");

    try {
        const nomeEvento = document.getElementById("nomeEvento").value;
        const descricaoDoEvento = document.getElementById("descricaoDoEvento").value;
        const dataDoEvento = document.getElementById("dataDoEvento").value;
        const localDoEvento = document.getElementById("localDoEvento").value;
        const precoDoEvento = Number(document.getElementById("precoDoEvento").value);
        const capacidadeDePessoasNoEvento = Number(document.getElementById("capacidadeDePessoasNoEvento").value);
        const tipoDoEvento = document.getElementById("tipoDoEvento").value;
        const apresentadorDoEvento = document.getElementById("apresentadorDoEvento").value;
        const duracaoDoEvento = document.getElementById("duracaoDoEvento").value;


        if (isNaN(precoDoEvento) || isNaN(capacidadeDePessoasNoEvento)) {
            msgElement.innerText = "Erro: Preço e Capacidade devem ser números válidos.";
            return;
        }

        const response = await fetch("http://localhost:8080/api/admin/eventos", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            credentials: "include", 
            body: JSON.stringify({
                nomeEvento,
                descricaoDoEvento,
                dataDoEvento,
                localDoEvento,
                precoDoEvento,
                capacidadeDePessoasNoEvento,
                tipoDoEvento,
                apresentadorDoEvento,
                duracaoDoEvento
            })
        });

        if (response.ok) {
            msgElement.innerText = "Evento cadastrado com sucesso!";
            document.getElementById("formCadastro").reset();
        } else {

            let errorMessage = "Erro ao cadastrar evento!";

            const contentType = response.headers.get("content-type");
            if (contentType && contentType.includes("application/json")) {
                const errorData = await response.json();
                errorMessage = errorData.message || JSON.stringify(errorData);
            } else if (response.status === 403) {
                errorMessage = "Acesso Negado. Verifique sua permissão de ADMIN. (403)";
            } else if (response.status === 401) {
                errorMessage = "Sessão expirada ou usuário não logado. Por favor, faça login novamente. (401)";
            } else if (response.status === 400) {
                errorMessage = "Dados Inválidos. Verifique se todos os campos estão preenchidos corretamente. (400)";
            }

            msgElement.innerText = `Erro (${response.status}): ${errorMessage}`;
        }
    } catch (error) {
        console.error("Erro na requisição ou processamento:", error);
        msgElement.innerText = "Falha de conexão. Verifique se o servidor está ativo.";
    }
});