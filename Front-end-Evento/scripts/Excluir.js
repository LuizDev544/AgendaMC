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

        if (resposta.status === 404) {
            document.querySelector('#mensagem').innerText = "Evento não encontrado ❌";
        } else if (resposta.ok) {
            document.querySelector('#mensagem').innerText = "Evento excluído com sucesso! ✅";
        }

        document.querySelector('#deleteForm').reset();
    } catch (erro) {
        document.querySelector('#mensagem').innerText = "Esqueceu de iniciar a aplicação burro!";
        console.error(erro);
    }
}

document.getElementById('deleteForm').addEventListener('submit', function (e) {
    e.preventDefault();
    deletarEvento();
});

document.getElementById('Recarregar').addEventListener('click', function () {
    location.reload();
});
