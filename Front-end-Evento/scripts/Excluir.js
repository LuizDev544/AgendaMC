async function deletarEvento() {
    const id = document.querySelector('#eventId').value.trim();

    if (id.length < 1) {
        alert("ID inválido. Insira outro novamente.");
        return;
    }

    const url = `http://127.0.0.1:8080/api/admin/eventos/${id}`;

    try {
        const resposta = await fetch(url, {
            method: 'DELETE',
            headers: {
                'Authorization': 'Basic ' + btoa('admin:senha123')
            }
        });

        if (resposta.ok) {
            alert(`Evento ${id} excluído com sucesso!`);
            document.querySelector('#deleteForm').reset();
        } else if (resposta.status === 404) {
            alert(`ID ${id} não encontrado.`);
        } else {
            const erro = await resposta.text();
            alert(`Erro ao excluir: ${erro}`);
        }
    } catch (erro) {
        console.error(erro);
        alert("Erro na requisição: " + erro.message);
    }
}

// Intercepta o submit para não recarregar a página
document.getElementById('deleteForm').addEventListener('submit', function (e) {
    e.preventDefault();
    deletarEvento();
});
